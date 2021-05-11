package br.com.newstation.dominio;

import java.io.Serializable;

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

	private static final long serialVersionUID = 1L;
	private LineChartModel areaModel;

	@PostConstruct
	public void init() {
		createAreaModel();
	}

	public LineChartModel getAreaModel() {
		return areaModel;
	}

	@SuppressWarnings("deprecation")
	private void createAreaModel() {
		areaModel = new LineChartModel();

		int antes = dao.grafico().get(0).getDataAtualizacao().getTime().getMonth();
		double soma[] = new double[12];
		int data[] = new int[12];
		int index = 0;
		for (Pedido p : dao.grafico()) {

			if (p.getDataAtualizacao().getTime().getMonth() != antes) {
				index++;
				data[index] = p.getDataAtualizacao().getTime().getMonth();
				soma[index] += p.getTotal().doubleValue();

				antes = p.getDataAtualizacao().getTime().getMonth();

			} else {
				data[index] = p.getDataAtualizacao().getTime().getMonth();
				soma[index] += p.getTotal().doubleValue();
			}
		}

		ChartSeries vendas = new ChartSeries();
		for (int j = 0; j <= index; j++) {
			vendas.set((data[j] + 1), soma[j]);
		}

		areaModel.addSeries(vendas);

		areaModel.setTitle("Vendas por Mês");
		areaModel.setLegendPosition("ne");
		areaModel.setStacked(true);
		areaModel.setShowPointLabels(true);

		Axis xAxis = new CategoryAxis("Meses");
		areaModel.getAxes().put(AxisType.X, xAxis);
		Axis yAxis = areaModel.getAxis(AxisType.Y);
		yAxis.setLabel("Quantidade em R$");

	}

}
