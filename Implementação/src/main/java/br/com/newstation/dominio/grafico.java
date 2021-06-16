package br.com.newstation.dominio;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.newstation.daos.PedidoDao;

@Model
public class grafico implements Serializable {

	@Inject
	private PedidoDao dao;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Map<String, Integer> cartas = new HashMap<String, Integer>();
	private Map<String, Map<String, Integer>> temp;
	private static final long serialVersionUID = 1L;
	private LineChartModel areaModel;
	private LineChartModel model;
	private static String min = null;
	private static String max = null;
	private static String filtro = "cartas";
	private List<Pedido> itens;
	private GraficoItem gi = new GraficoItem();
	private Set<String> datas = new HashSet<String>();
	private ArrayList<String> label = new ArrayList<String>();
	private ArrayList<String> data = new ArrayList<String>();

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

	public LineChartModel getModel() {
		return model;
	}

	private void createAreaModel() {
		cartas = new HashMap<String, Integer>();
		setTemp(new LinkedHashMap<String, Map<String, Integer>>());
		itens = dao.grafico();
		if (filtro.equals("cartas")) {
			for (Pedido i : itens) {
				String datas_texto = dateFormat.format(i.getDataAtualizacao().getTime()).toString();
				for (CartaPedido c : i.getItens()) {
//					if (cartas.containsKey(c.getCarta().getNome()))
//						cartas.put(c.getCarta().getNome(), (c.getQuantidade() + cartas.get(c.getCarta().getNome())));
//					else
//						cartas.put(c.getCarta().getNome(), c.getQuantidade());
					datas.add(datas_texto);
					label.add(c.getCarta().getNome());
					data.add(c.getQuantidade().toString());
					cartas.put(c.getCarta().getNome(), c.getQuantidade()); 
					temp.put(datas_texto, cartas);
					System.out.println(data+" "+
							c.getCarta().getNome() +" "+
							c.getQuantidade());

				}
				gi.setData(data);
				gi.setLabel(label);
				gi.setDatas(datas);

			}
		} else {
			for (Pedido i : itens) {
				for (CartaPedido c : i.getItens()) {
					if (cartas.containsKey(c.getCarta().getRaridade().toString()))
						cartas.put(c.getCarta().getRaridade().toString(),
								(c.getQuantidade() + cartas.get(c.getCarta().getRaridade().toString())));
					else
						cartas.put(c.getCarta().getRaridade().toString(), c.getQuantidade());
				}
			}
		}

		geraGrafico();
	}

	public void createAreaModel_2() throws ParseException {
		List<Pedido> itens = dao.grafico();
		cartas = new HashMap<String, Integer>();

		if (max.isEmpty() || max == null) {
			max = "31/12/2100";
		}
		if (min.isEmpty() || min == null) {
			min = "31/12/1980";
		}
		Date data_min = dateFormat.parse(min);
		Date data_max = dateFormat.parse(max);

//		Date cal = itens.get(0).getDataAtualizacao().getTime();

		for (Pedido i : itens) {
			Date cal = i.getDataAtualizacao().getTime();
			if (data_min.before(cal) && data_max.after(cal)) {
				if (filtro.equals("cartas")) {
					for (CartaPedido c : i.getItens()) {
						if (cartas.containsKey(c.getCarta().getNome()))
							cartas.put(c.getCarta().getNome(),
									(c.getQuantidade() + cartas.get(c.getCarta().getNome())));
						else
							cartas.put(c.getCarta().getNome(), c.getQuantidade());

					}
				} else {
					for (CartaPedido c : i.getItens()) {
						if (cartas.containsKey(c.getCarta().getRaridade().toString()))
							cartas.put(c.getCarta().getRaridade().toString(),
									(c.getQuantidade() + cartas.get(c.getCarta().getRaridade().toString())));
						else
							cartas.put(c.getCarta().getRaridade().toString(), c.getQuantidade());
					}
				}
			}
		}

		geraGrafico();

	}

	private void geraGrafico() {
		areaModel = new LineChartModel();
		LineChartSeries vendas = null;
//		vendas.setFill(true);
//		vendas.setFillAlpha(0.5);

		for (Map.Entry<String, Integer> pair : cartas.entrySet()) {
			vendas = new LineChartSeries();
			vendas.setLabel(pair.getKey());
			vendas.set(pair.getKey(), pair.getValue());
			areaModel.addSeries(vendas);

		}

//		roda cada item e add a data

		if ((max == null || max == "31/12/2100") && (min == null || min == "31/12/1980")) {
			if (filtro.equals("raridade"))
				areaModel.setTitle("Quantidade Geral de Cartas por Raridade");
			else
				areaModel.setTitle("Quantidade Cartas Geral");
		} else {
			if (filtro.equals("raridade"))
				areaModel.setTitle("Quantidade Geral de Cartas por Raridade entre: " + min + " e " + max);
			else
				areaModel.setTitle("Quantidade cartas vendidas entre: " + min + " e " + max);
		}
		max = null;
		min = null;
//		areaModel.setStacked(true);
//		areaModel.setShowPointLabels(true);
//		areaModel.setLegendPosition("e");
//		
		Axis xAxis = new CategoryAxis(filtro);
		xAxis.setTickAngle(15);
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade Total");

	}

	public void setData() {
		try {
			createAreaModel_2();
		} catch (ParseException e) {
			e.printStackTrace();
			System.out.println("erro");
		}
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

	public Map<String, Map<String, Integer>> getTemp() {
		return temp;
	}

	public String getJson() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		return gson.toJson(temp);
	}

	public void setTemp(Map<String, Map<String, Integer>> temp) {
		this.temp = temp;
	}

}
