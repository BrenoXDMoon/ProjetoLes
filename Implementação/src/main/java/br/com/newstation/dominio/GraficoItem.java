package br.com.newstation.dominio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GraficoItem {

	private Set<String> datas = new HashSet<String>();
	private ArrayList<String> label = new ArrayList<String>();
	private ArrayList<String> data = new ArrayList<String>();

	public Set<String> getDatas() {
		return datas;
	}

	public void setDatas(Set<String> datas) {
		this.datas = datas;
	}

	public ArrayList<String> getLabel() {
		return label;
	}

	public void setLabel(ArrayList<String> label) {
		this.label = label;
	}

	public ArrayList<String> getData() {
		return data;
	}

	public void setData(ArrayList<String> data) {
		this.data = data;
	}
}
