package com.anjali.train.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "train")
public class Train implements java.io.Serializable{
	private static final long serialVersionUID = -2441770313733149100L;
	private Integer id;
	private String trainName;
	private Integer capacity;
	private String path;
	
	public Train() {
		super();
	}
	public Train(String trainName, Integer capacity, String path) {
		super();
		this.trainName = trainName;
		this.capacity = capacity;
		this.path = path;
	}
	public Train(Integer id, String trainName, Integer capacity, String path) {
		super();
		this.id = id;
		this.trainName = trainName;
		this.capacity = capacity;
		this.path = path;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name="train_name", nullable = false, length = 20)
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	@Column(name="capacity")
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	
	@Column(name="path")
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Train [id=" + id + ", trainName=" + trainName + ", capacity=" + capacity + ", path=" + path + "]";
	}
	
	
}