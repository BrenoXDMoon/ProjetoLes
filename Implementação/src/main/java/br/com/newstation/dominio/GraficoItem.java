package br.com.newstation.dominio;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GraficoItem {

	private Set<String> datas = new HashSet<String>();
	private Map<String, List<Integer>> label = new HashMap<String, List<Integer>>();
	private Map<String, List<Integer>> data = new HashMap<String, List<Integer>>();
	private Map<Map<String, Set<String>>, Map<String, List<Integer>>> cartas = new HashMap<Map<String, Set<String>>, Map<String, List<Integer>>>();
	private int limitador = 0;
	
	
	public Set<String> getDatas() {
		return datas;
	}

	public void setDatas(Set<String> datas) {
		this.datas = datas;
	}

	public Map<String, List<Integer>> getLabel() {
		return label;
	}

	public void setLabel(Map<String, List<Integer>> label) {
		this.label = label;
	}

	public Map<String, List<Integer>> getData() {
		return data;
	}

	public void setData(Map<String, List<Integer>> data) {
		this.data = data;
	}

	public Map<Map<String, Set<String>>, Map<String, List<Integer>>> getCartas() {
		return cartas;
	}

	public void setCartas(Map<Map<String, Set<String>>, Map<String, List<Integer>>> cartas) {
		this.cartas = cartas;
	}

	public void setCartas(Map<String, Set<String>> map_label, Map<String, List<Integer>> map_data) {
		this.cartas.put(map_label, map_data);
		
	}

	public int getLimitador() {
		return limitador;
	}

	public void setLimitador(int limitador) {
		this.limitador = limitador;
	}

	
}
