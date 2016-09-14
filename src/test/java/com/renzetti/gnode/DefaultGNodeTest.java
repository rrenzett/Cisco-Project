package com.renzetti.gnode;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class DefaultGNodeTest {

	@Test
	public void testGetChildren() {

		// No Children
		DefaultGNode root = new DefaultGNode("root");
		assertNotNull(root.getChildren());
		assertEquals(root.getChildren().length, 0);

		// One child
		DefaultGNode child1 = new DefaultGNode("child1");
		root.addChild(child1);
		assertNotNull(root.getChildren());
		assertEquals(root.getChildren().length, 1);
		assertTrue(root.getChildren()[0].equals(child1));

		// Two children
		DefaultGNode child2 = new DefaultGNode("child2");
		root.addChild(child2);
		assertNotNull(root.getChildren());
		assertEquals(root.getChildren().length, 2);
		assertTrue(root.getChildren()[0].equals(child1));
		assertTrue(root.getChildren()[1].equals(child2));

		// Test remove
		root.removeChild(child1);
		assertEquals(root.getChildren().length, 1);
		assertTrue(root.getChildren()[0].equals(child2));

		root.removeChild(child1); // remove again
		assertEquals(root.getChildren().length, 1);
		assertTrue(root.getChildren()[0].equals(child2));

		root.removeChild(child2); // remove again
		assertEquals(root.getChildren().length, 0);
	}

	@Test
	public void testWalkGraphWithManyChildren() {
		DefaultGNode root = new DefaultGNode("root");
		DefaultGNode child1 = new DefaultGNode("child1");
		DefaultGNode child2 = new DefaultGNode("child2");
		DefaultGNode child3 = new DefaultGNode("child3");
		DefaultGNode child4 = new DefaultGNode("child4");
		DefaultGNode child5 = new DefaultGNode("child5");
		DefaultGNode child6 = new DefaultGNode("child6");
		DefaultGNode child7 = new DefaultGNode("child7");
		DefaultGNode child8 = new DefaultGNode("child8");
		DefaultGNode child9 = new DefaultGNode("child9");

		root.addChild(child1);
		root.addChild(child2);

		// children 3 & 5 child of 1
		child1.addChild(child3);
		child1.addChild(child5);

		// children 4 & 6 child of 2
		child2.addChild(child4);
		child2.addChild(child6);

		// Child 6 has 1 child (8)
		child6.addChild(child8);

		// Child 3 has 1 child (9)
		child3.addChild(child9);

		// Children 4 & 5 have same children (7, 8, 9)
		child4.addChild(child7);
		child4.addChild(child8);
		child4.addChild(child9);
		child5.addChild(child7);
		child5.addChild(child8);
		child5.addChild(child9);

		assertEquals(DefaultGNode.walkGraph(root).size(), 9);
		assertEquals(DefaultGNode.walkGraph(child1).size(), 5);
		assertEquals(DefaultGNode.walkGraph(child2).size(), 5);
		assertEquals(DefaultGNode.walkGraph(child3).size(), 1);
		assertEquals(DefaultGNode.walkGraph(child4).size(), 3);
		assertEquals(DefaultGNode.walkGraph(child5).size(), 3);
		assertEquals(DefaultGNode.walkGraph(child6).size(), 1);
		assertEquals(DefaultGNode.walkGraph(child7).size(), 0);
		assertEquals(DefaultGNode.walkGraph(child8).size(), 0);
		assertEquals(DefaultGNode.walkGraph(child9).size(), 0);
	}

	@Test
	public void testPaths() {
		DefaultGNode root = new DefaultGNode("root");
		DefaultGNode child1 = new DefaultGNode("child1");
		DefaultGNode child2 = new DefaultGNode("child2");
		DefaultGNode child3 = new DefaultGNode("child3");
		DefaultGNode child4 = new DefaultGNode("child4");
		DefaultGNode child5 = new DefaultGNode("child5");
		DefaultGNode child6 = new DefaultGNode("child6");
		DefaultGNode child7 = new DefaultGNode("child7");
		DefaultGNode child8 = new DefaultGNode("child8");
		DefaultGNode child9 = new DefaultGNode("child9");

		root.addChild(child1);
		root.addChild(child2);

		// children 3 & 5 child of 1
		child1.addChild(child3);
		child1.addChild(child5);

		// children 4 & 6 child of 2
		child2.addChild(child4);
		child2.addChild(child6);

		// Child 6 has 1 child (8)
		child6.addChild(child8);

		// Child 3 has 1 child (9)
		child3.addChild(child9);

		// Children 4 & 5 have same children (7, 8, 9)
		child4.addChild(child7);
		child4.addChild(child8);
		child4.addChild(child9);
		child5.addChild(child7);
		child5.addChild(child8);
		child5.addChild(child9);

		ArrayList<ArrayList<GNode>> paths = DefaultGNode.paths(root);
		assertEquals(paths.size(), 8);

		// Verify content of myLists
		ArrayList<ArrayList<String>> solutions = getSolutionsList();
		assertEquals(solutions.size(), paths.size());

		for (ArrayList<GNode> path : paths) {
			ArrayList<String> solution = findSolution(solutions, path);
			assertNotNull(solution);
			solutions.remove(solution);
		}

		// Show all solutions were found
		assertEquals(solutions.size(), 0);
	}

	private ArrayList<ArrayList<String>> getSolutionsList() {
		// Store solutions
		ArrayList<String> solution1 = new ArrayList<String>();
		solution1.add("root");
		solution1.add("child1");
		solution1.add("child3");
		solution1.add("child9");
		ArrayList<String> solution2 = new ArrayList<String>();
		solution2.add("root");
		solution2.add("child1");
		solution2.add("child5");
		solution2.add("child9");
		ArrayList<String> solution3 = new ArrayList<String>();
		solution3.add("root");
		solution3.add("child1");
		solution3.add("child5");
		solution3.add("child7");
		ArrayList<String> solution4 = new ArrayList<String>();
		solution4.add("root");
		solution4.add("child1");
		solution4.add("child5");
		solution4.add("child8");
		ArrayList<String> solution5 = new ArrayList<String>();
		solution5.add("root");
		solution5.add("child2");
		solution5.add("child4");
		solution5.add("child9");
		ArrayList<String> solution6 = new ArrayList<String>();
		solution6.add("root");
		solution6.add("child2");
		solution6.add("child4");
		solution6.add("child7");
		ArrayList<String> solution7 = new ArrayList<String>();
		solution7.add("root");
		solution7.add("child2");
		solution7.add("child4");
		solution7.add("child8");
		ArrayList<String> solution8 = new ArrayList<String>();
		solution8.add("root");
		solution8.add("child2");
		solution8.add("child6");
		solution8.add("child8");

		ArrayList<ArrayList<String>> solutions = new ArrayList<ArrayList<String>>();
		solutions.add(solution1);
		solutions.add(solution2);
		solutions.add(solution3);
		solutions.add(solution4);
		solutions.add(solution5);
		solutions.add(solution6);
		solutions.add(solution7);
		solutions.add(solution8);

		return solutions;
	}

	private ArrayList<String> findSolution(ArrayList<ArrayList<String>> solutions, ArrayList<GNode> paths) {

		ArrayList<String> ret = null;

		// Look for a good solution
		for (ArrayList<String> solution : solutions) {

			// Size must match
			if (solution.size() == paths.size()) {

				// Initialize flag
				boolean solutionGood = true;

				// Check to make sure each node matches while flag is true
				for (int index = 0; index < solution.size() && solutionGood; index++) {

					// if one item doesn't match then change the flag to false
					if (!solution.get(index).equals(paths.get(index).getName())) {
						solutionGood = false;
					}
				}

				// If the solution is good then break out of the for loop
				if (solutionGood) {
					ret = solution;
					break;
				}
			}
		}

		return ret;
	}
}
