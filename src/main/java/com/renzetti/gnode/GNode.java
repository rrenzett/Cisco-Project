package com.renzetti.gnode;

/*
 * The GNode object can be thought of as defining a graph.
 * In implementing the functions below, you can assume that any graph defined by a GNode is acyclic.
 */
public interface GNode {

	public String getName();

	/**
	 * This method returns an array that contains the children of this node.
	 * 
	 * @return Array of children. Cannot be null.
	 */
	public GNode[] getChildren();
}
