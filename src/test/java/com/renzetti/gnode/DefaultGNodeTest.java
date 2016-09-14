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
		assertEquals(root.getChildren().length, 11);
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

		ArrayList<ArrayList<GNode>> myLists = DefaultGNode.paths(root);
		assertEquals(myLists.size(), 8);
		// Need more tests to verify content of myLists
	}
}
