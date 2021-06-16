package br.com.newstation.dominio;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import br.com.newstation.daos.PedidoDao;

@Model
public class grafico implements Serializable {

	@Inject
	private PedidoDao dao;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private Map<String, Integer> cartas = new HashMap<String, Integer>();
	private static final long serialVersionUID = 1L;
	private LineChartModel areaModel;
	private static String min = null;
	private static String max = null;
	private static String filtro = "cartas";

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

//	public HorizontalBarChartModel getModel() {
//		return model;
//	}

	private void createAreaModel() {
		cartas = new HashMap<String, Integer>();
		List<Pedido> itens = dao.grafico();
		if (filtro.equals("cartas")) {
			for (Pedido i : itens) {
				for (CartaPedido c : i.getItens()) {
					if (cartas.containsKey(c.getCarta().getNome()))
						cartas.put(c.getCarta().getNome(), (c.getQuantidade() + cartas.get(c.getCarta().getNome())));
					else
						cartas.put(c.getCarta().getNome(), c.getQuantidade());
				}
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
		LineChartSeries vendas = new LineChartSeries();
		vendas.setFill(true);
		vendas.setFillAlpha(0.5);
//        
		for (Map.Entry<String, Integer> pair : cartas.entrySet()) {
			vendas.set(pair.getKey(), pair.getValue());
		}

		areaModel.addSeries(vendas);

		if ((max == null || max == "31/12/2100") && (min == null || min == "31/12/1980")) {
			if(filtro.equals("raridade"))
				areaModel.setTitle("Quantidade Geral de Cartas por Raridade");
			else
				areaModel.setTitle("Quantidade Cartas Geral");
		} else {
			if(filtro.equals("raridade"))
				areaModel.setTitle("Quantidade Geral de Cartas por Raridade entre: " + min + " e " + max );
			else
				areaModel.setTitle("Quantidade cartas vendidas entre: " + min + " e " + max);
		}
		max = null;
		min = null;
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

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

}
