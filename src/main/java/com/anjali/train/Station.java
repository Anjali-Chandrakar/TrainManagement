package com.anjali.train;
public class Station {
	
	
	private Integer id;
	private String stationName;
	
	public Station()
	{
		super();
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Override
	public String toString() {
		return "Station [id=" + id + ", station_name=" + stationName + "]";
	}
	
	

}
