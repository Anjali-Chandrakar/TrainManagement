package com.anjali.train;
public class Station {
	
	
	private Integer id;
	private String station_name;
	
	public Station()
	{
		super();
	}
	
	public Station(String station_name) {
		super();
		this.station_name = station_name;
	}
	
	public Station(Integer id, String station_name) {
		super();
		this.id = id;
		this.station_name = station_name;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getStation_name() {
		return station_name;
	}
	public void setStation_name(String station_name) {
		this.station_name = station_name;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", station_name=" + station_name + "]";
	}
	
	

}
