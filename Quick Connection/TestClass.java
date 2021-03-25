
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;

/**
 * Tests the Project 3 application of team HE
 * 
 */
public class TestClass {

	private CS400Graph<Integer> graph;

	@BeforeEach
	/**
	 * Instantiate graph to test values for airports and destinations
	 */
	public void createGraph() {
		graph = new CS400Graph<>();
		// insert airports 1, 2, 3, 4, 5

		graph.insertVertex(1);
		graph.insertVertex(2);
		graph.insertVertex(3);
		graph.insertVertex(4);
		graph.insertVertex(5);

		// insert edges (destinations) for the 5 airports
		graph.insertEdge(1, 2, 2800);
		graph.insertEdge(1, 3, 800);
		graph.insertEdge(2, 3, 2015);
		graph.insertEdge(3, 4, 95);
		graph.insertEdge(4, 5, 855);
		graph.insertEdge(5, 1, 626);
	}

	/**
	 * Tests the frontend (and the backend, DataLoader implicitly) by simulating
	 * user input and seeing if the results are satisfactory
	 * 
	 * @return boolean - determines the success of the test
	 */
	@Test
	public void frontEndSimTest() {

		// Commands that create a new user. Continues with
		// standard program flow and quits
		String simulatedUserInput = "2" + System.getProperty("line.separator") + "ORD"
				+ System.getProperty("line.separator") + "LAX" + System.getProperty("line.separator") + "4"
				+ System.getProperty("line.separator") + "5";

		System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
		String[] args = null;
		FrontEnd.main(args);
	}

	/**
	 * Checks the distance/total weight cost from the airport vertex labelled 1 to 4
	 * (should be 895), and from the airport vertex labelled 5 to 2 (should be
	 * 3426).
	 */
	@Test
	public void providedTestToCheckPathCosts() {
		assertTrue(graph.getPathCost(1, 4) == 895);
		assertTrue(graph.getPathCost(5, 2) == 3426);
	}

	/**
	 * Checks the ordered sequence of data within vertices from the vertex labelled
	 * 1 to 4, and from the vertex labelled 5 to 2.
	 */
	@Test
	public void providedTestToCheckPathContents() {
		assertTrue(graph.shortestPath(1, 4).toString().equals("[1, 3, 4]"));
		assertTrue(graph.shortestPath(5, 2).toString().equals("[5, 1, 2]"));
	}

	/**
	 * Tests to see the distance of the longest shortest path from 5 to 2
	 */
	@Test
	public void individualPathDistanceTest() {
		assertTrue(graph.getPathCost(5, 2) == 3426);
	}

	/**
	 * Tests to make sure getPath throws the correct exceptions and has the correct
	 * return value
	 */
	@Test
	public void testGetPathCost() {
		assertThrows(NoSuchElementException.class, () -> graph.shortestPath(73, 6));
		int c = graph.getPathCost(1, 4);
		assertTrue(c == 895);
		c = graph.getPathCost(3, 4);
		assertTrue(c == 95);

	}

	/*
	 * Checks if the shortest path method throws a NoSuchElementException
	 */
	@Test
	public void extraTest1() {
		assertThrows(NoSuchElementException.class, () -> graph.shortestPath(73, 6));
	}
}
