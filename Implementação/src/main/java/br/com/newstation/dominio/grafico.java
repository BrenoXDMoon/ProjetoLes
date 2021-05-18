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

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

	private void createAreaModel() {
		cartas = new HashMap<String, Integer>();
		List<Pedido> itens = dao.grafico();
		for (Pedido i : itens) {
			for (CartaPedido c : i.getItens()) {
				if (cartas.containsKey(c.getCarta().getNome()))
					cartas.put(c.getCarta().getNome(), (c.getQuantidade() + cartas.get(c.getCarta().getNome())));
				else
					cartas.put(c.getCarta().getNome(), c.getQuantidade());
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
				for (CartaPedido c : i.getItens()) {
					if (cartas.containsKey(c.getCarta().getNome()))
						cartas.put(c.getCarta().getNome(), (c.getQuantidade() + cartas.get(c.getCarta().getNome())));
					else
						cartas.put(c.getCarta().getNome(), c.getQuantidade());

//				System.out.println( c.getCarta().getNome());
//				System.out.println(c.getQuantidade());
				}
			}
		}
		max = null;
		min = null;
		geraGrafico();
//		List<Carta> cartas = dao.grafico();
//		for(Carta c: cartas) {
//			System.out.println(c.getValorCusto());
//		}
//		int antes = dao.grafico().get(0).getDataAtualizacao().getTime().getMonth();
//		double soma[] = new double[12];
//		int data[] = new int[12];
//		int index = 0;
//		for (Carta p : dao.grafico()) {
//
//			if (p.getDataAtualizacao().getTime().getMonth() != antes) {
//				index++;
//				data[index] = p.getDataAtualizacao().getTime().getMonth();
//				soma[index] += p.getTotal().doubleValue();
//
//				antes = p.getDataAtualizacao().getTime().getMonth();
//
//			} else {
//				data[index] = p.getDataAtualizacao().getTime().getMonth();
//				soma[index] += p.getTotal().doubleValue();
//			}
//		}
//		for (int j = 0; j <= index; j++) {
//		vendas.set((data[j] + 1), soma[j]);
//	}

	}

	private void geraGrafico() {
		areaModel = new LineChartModel();
		ChartSeries vendas = new ChartSeries();

		for (Map.Entry<String, Integer> pair : cartas.entrySet()) {
			vendas.set(pair.getKey(), pair.getValue());
		}

		areaModel.addSeries(vendas);

		areaModel.setTitle("Quantidade Cartas");
		areaModel.setLegendPosition("ne");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Cartas");
		xAxis.setTickAngle(15);
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade Total");
	}

	public void setData() {
		try {
			createAreaModel_2();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}
