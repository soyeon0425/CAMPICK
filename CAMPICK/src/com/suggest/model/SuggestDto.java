package com.suggest.model;

public class SuggestDto {
	private int sug_id;
	private int borad_id;
	private String id;
	private String name;
	private int checked;
	
	public int getSug_id() {
		return sug_id;
	}
	public void setSug_id(int sug_id) {
		this.sug_id = sug_id;
	}
	public int getBorad_id() {
		return borad_id;
	}
	public void setBorad_id(int borad_id) {
		this.borad_id = borad_id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	
	
}
