package com.anjali.train.vo;

import java.io.Serializable;

public class TrainVo implements Serializable{
	private static final long serialVersionUID = 7777743823810981013L;
	
	private Integer id;
	private String trainName;
	private Integer capacity;
	private String path;
	
	public TrainVo() {
		super();
	}
	public TrainVo(String trainName, Integer capacity, String path) {
		super();
		this.trainName = trainName;
		this.capacity = capacity;
		this.path = path;
	}
	public TrainVo(Integer id, String trainName, Integer capacity, String path) {
		super();
		this.id = id;
		this.trainName = trainName;
		this.capacity = capacity;
		this.path = path;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "TrainVo [id=" + id + ", trainName=" + trainName + ", capacity=" + capacity + ", path=" + path + "]";
	}
}