package com.thoughtwork.train.model;

import java.util.Map;

public interface Graph {
	
	/**
	 * Get Edge Data
	 * @return
	 */
	public Map<String, Edge> getAllEdgeMap();
	
	/**
	 * Get Node Data
	 * @return
	 */
	public Map<String, Node> getAllNodeMap();
}
