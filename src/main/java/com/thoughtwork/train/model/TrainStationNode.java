package com.thoughtwork.train.model;

/**
 * 用于存储火车站数据
 * @author wangxiao
 *
 */
public class TrainStationNode implements Node{
	
	private String stationId;
	
	//Each station should have exculsive station name;
	private String stationName;
	
	//stop time by minute, default is 2 minutes
	private Integer stopTime;
	
	public TrainStationNode(String stationId, String stationName, Integer stopTime) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
		this.stopTime = stopTime;
	}

	public String getStationId() {
		return stationId;
	}

	public void setStationId(String stationId) {
		this.stationId = stationId;
	}



	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public Integer getStopTime() {
		return stopTime;
	}

	public void setStopTime(Integer stopTime) {
		this.stopTime = stopTime;
	}
	
	
}
