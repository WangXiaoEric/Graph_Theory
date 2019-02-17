package com.thoughtwork.train.model;

/**
 * 用于存储路径数据
 * @author wangxiao
 *
 */
public class TrainTrackEdge implements Edge{
	
	//if from A station to B station, then the trackName should be AB;
	private String trackId;
	
	private String trackName;
	
	private String startStationId;
	
	private String endStationId;
	
	private Integer distance;
	
	private Integer trackTime;

	public TrainTrackEdge(String trackId, String trackName, String startStationId, String endStationId,
			Integer distance, Integer trackTime) {
		super();
		this.trackId = trackId;
		this.trackName = trackName;
		this.startStationId = startStationId;
		this.endStationId = endStationId;
		this.distance = distance;
		this.trackTime = trackTime;
	}

	public String getTrackId() {
		return trackId;
	}

	public void setTrackId(String trackId) {
		this.trackId = trackId;
	}

	public String getStartStationId() {
		return startStationId;
	}

	public void setStartStationId(String startStationId) {
		this.startStationId = startStationId;
	}

	public String getEndStationId() {
		return endStationId;
	}

	public void setEndStationId(String endStationId) {
		this.endStationId = endStationId;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getTrackTime() {
		return trackTime;
	}

	public void setTrackTime(Integer trackTime) {
		this.trackTime = trackTime;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}
}
