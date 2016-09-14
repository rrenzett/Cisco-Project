package com.renzetti.gnode;

import java.util.ArrayList;
import java.util.List;

public class DefaultGNode implements GNode {

	private String name;

	private List<GNode> children = new ArrayList<GNode>();

	private static int count = 0;

	public DefaultGNode(String name) {
		this.name = (name != null) ? name : "";
	}

	public String getName() {
		return name;
	}

	public GNode[] getChildren() {
		GNode[] gnodeArray = new GNode[children.size()];
		gnodeArray = children.toArray(gnodeArray);
		return (gnodeArray);
	}

	/**
	 * This method is used to add a child node to the children of this node.
	 * 
	 * @param node
	 */
	public void addChild(GNode node) {

		if (!children.contains(node)) {
			children.add(node);
		}
	}

	/**
	 * This method is used to remove a child node from the children of this
	 * node.
	 * 
	 * @param node
	 */
	public void removeChild(GNode node) {

		if (children.contains(node)) {
			children.remove(node);
		}
	}

	/**
	 * 
	 * @param node
	 * @return
	 */
	public static ArrayList<GNode> walkGraph(GNode node) {
		ArrayList<GNode> result = new ArrayList<GNode>();

		GNode[] children = node.getChildren();

		if (children.length > 0) {

			for (GNode child : children) {

				// Add immediate child to list first
				if (!result.contains(child)) {
					result.add(child);

					// Add child's children next if there are any
					if (child.getChildren().length > 1) {
						addChildsChildren(result, child);
					}
				}

			}
		}

		return result;
	}

	/**
	 * Recursive method to add all children (including descendant's children) of
	 * a given node to a list.
	 * 
	 * @param list
	 * @param child
	 */
	private static void addChildsChildren(List<GNode> list, GNode child) {
		GNode[] childList = child.getChildren();

		for (int i = 0; i < childList.length; i++) {

			if (!list.contains(childList[i])) {
				list.add(childList[i]);

				addChildsChildren(list, childList[i]);
			}
		}
	}

	public static ArrayList<ArrayList<GNode>> paths(GNode node) {
		ArrayList<ArrayList<GNode>> result = new ArrayList<ArrayList<GNode>>();
		getPaths(result, node);

		// getPaths() could be more efficient
		System.out.println("# of adds: " + count);
		return result;
	}

	private static void getPaths(ArrayList<ArrayList<GNode>> list, GNode node) {
		GNode[] children = node.getChildren();

		if (children.length > 0) {

			for (GNode child : children) {

				if (child.getChildren().length == 0) {
					// This is a leaf node
					ArrayList<GNode> newPath = new ArrayList<GNode>();
					newPath.add(node);
					newPath.add(child);
					list.add(newPath);
					count++;
				} else {
					ArrayList<ArrayList<GNode>> myLists = new ArrayList<ArrayList<GNode>>();
					getPaths(myLists, child);

					// Simple collection with node in it
					ArrayList<GNode> nodePath = new ArrayList<GNode>();
					nodePath.add(node);

					// Add node to the beginning of each list
					for (ArrayList<GNode> myList : myLists) {
						myList.addAll(0, nodePath);
						list.add(myList);
						count++;
					}
				}
			}
		}
	}

	@Override
	public String toString() {
		return "name = " + name;
	}
}
