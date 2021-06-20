package br.com.newstation.dominio;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.newstation.daos.PedidoDao;

@Model
public class grafico implements Serializable {

	@Inject
	private PedidoDao dao;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static final long serialVersionUID = 1L;
	private static String min = null;
	private static String max = null;
	private static String filtro = "cartas";
	private LinkedHashSet<String> datas = new LinkedHashSet<String>();
	private Set<String> nomes = new HashSet<String>();
	private Map<String, List<Integer>> label = new HashMap<String, List<Integer>>();
	private List<Integer> data = new ArrayList<Integer>();
	GraficoItem gi = new GraficoItem();
	private int limitador;

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	private void createAreaModel() {
		List<Pedido> itens = dao.grafico();

		for (Pedido i : itens) {
			datas.add(dateFormat.format(i.getDataAtualizacao().getTime()).toString());
			for (CartaPedido c : i.getItens()) {
				if (filtro.equals("cartas"))
					nomes.add(c.getCarta().getNome());
				else
					nomes.add(c.getCarta().getRaridade().toString());
			}
		}

		for (int i = 0; i < datas.size(); i++) {
			data.add(0);
		}
		for (String n : nomes) {
			List<Integer> copy = new ArrayList<>(data);
			label.put(n, copy);
		}

		for (int i = 0; i < datas.size(); i++) {
			for (Pedido p : itens) {
				String datas_texto = dateFormat.format(p.getDataAtualizacao().getTime()).toString();
				if (datas.toArray()[i].equals(datas_texto)) {
					for (CartaPedido c : p.getItens()) {
						if (filtro.equals("cartas")) {
							int prev = label.get(c.getCarta().getNome()).get(i);
							int soma = prev + c.getQuantidade();
							label.get(c.getCarta().getNome()).set(i, soma);
							soma = 0;
							prev = 0;
						} else {
							int prev = label.get(c.getCarta().getRaridade().toString()).get(i);
							int soma = prev + c.getQuantidade();
							label.get(c.getCarta().getRaridade().toString()).set(i, soma);
							soma = 0;
							prev = 0;
						}

					}
				}
			}
		}
		for (String s : nomes)
//			System.out.println(label.get(s).size());
			gi.setLabel(label);
		gi.setDatas(datas);
		gi.setLimitador(limitador);

	}

	public void createAreaModel_2() throws ParseException {
		nomes = new HashSet<String>();
		datas = new LinkedHashSet<String>();
		label = new HashMap<String, List<Integer>>();
		data = new ArrayList<Integer>();

		List<Pedido> itens = dao.grafico();
		if (max.isEmpty() || max == null) {
			max = "31/12/2100";
		}
		if (min.isEmpty() || min == null) {
			min = "31/12/1980";
		}

		Date data_min = dateFormat.parse(min);
		Date data_max = dateFormat.parse(max);

		for (Pedido i : itens) {
			Date cal = i.getDataAtualizacao().getTime();
			if (data_min.before(cal) && data_max.after(cal)) {
				datas.add(dateFormat.format(i.getDataAtualizacao().getTime()).toString());
				for (CartaPedido c : i.getItens()) {
					if (filtro.equals("cartas"))
						nomes.add(c.getCarta().getNome());
					else
						nomes.add(c.getCarta().getRaridade().toString());
				}
			}
		}

		for (int i = 0; i < datas.size(); i++) {
			data.add(0);
		}
		for (String n : nomes) {
			List<Integer> copy = new ArrayList<>(data);
			label.put(n, copy);
		}

//		System.out.println(datas.size());

		for (int i = 0; i < datas.size(); i++) {
			for (Pedido p : itens) {
				String datas_texto = dateFormat.format(p.getDataAtualizacao().getTime()).toString();
				if (datas.toArray()[i].equals(datas_texto)) {
					for (CartaPedido c : p.getItens()) {
						if (filtro.equals("cartas")) {
							int prev = label.get(c.getCarta().getNome()).get(i);
							int soma = prev + c.getQuantidade();
							label.get(c.getCarta().getNome()).set(i, soma);
							soma = 0;
							prev = 0;
						} else {
							int prev = label.get(c.getCarta().getRaridade().toString()).get(i);
							int soma = prev + c.getQuantidade();
							label.get(c.getCarta().getRaridade().toString()).set(i, soma);
							soma = 0;
							prev = 0;
						}

					}
				}
			}
		}
		for (String s : nomes)
//			System.out.println(s + " " + label.get(s).size());
			gi.setLabel(label);
		gi.setDatas(datas);
		gi.setLimitador(limitador);
		if (max == "31/12/2100")
			max = "";
		if (min == "31/12/1980")
			min = "";

	}

	public void setData() {
		try {
			createAreaModel_2();
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("erro");
		}
	}

	public String getJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(gi);
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		grafico.min = min;
	}

	public String getMax() {
		return max;
	}

	public void setMax(String max) {
		grafico.max = max;
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		grafico.filtro = filtro;
	}

	public int getLimitador() {
		return limitador;
	}

	public void setLimitador(int limitador) {
		this.limitador = limitador;
	}

}
