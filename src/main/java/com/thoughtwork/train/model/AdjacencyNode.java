package com.thoughtwork.train.model;

/**
 * 邻接表Adjacency List数据结构  邻接表不仅定义了节点信息，也表达了Edge信息
 * @author wangxiao
 *
 */
public class AdjacencyNode implements Node{
	
	String nodeIndex;
	
	//第一个节点是没有数据信息的
	private Integer distance;
	
	private Integer trackTime;
	
	private AdjacencyNode nextNode;
	
	
	/**
	 * 构造首个节点
	 * @param nodeIndex
	 */
	public AdjacencyNode(String nodeIndex) {
		super();
		this.nodeIndex = nodeIndex;
	}

	/**
	 * 构造后续节点
	 * @param nodeIndex
	 * @param distance
	 * @param trackTime
	 */
	public AdjacencyNode(String nodeIndex, Integer distance, Integer trackTime) {
		super();
		this.nodeIndex = nodeIndex;
		this.distance = distance;
		this.trackTime = trackTime;
	}

	public String getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(String nodeIndex) {
		this.nodeIndex = nodeIndex;
	}
	
	public AdjacencyNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(AdjacencyNode nextNode) {
		this.nextNode = nextNode;
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
}
