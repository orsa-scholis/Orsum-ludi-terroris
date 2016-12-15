package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.Stopwatch;

import logic.Game;
import logic.algorithm.Path;
import logic.graph.Point;
import sun.net.www.content.audio.x_aiff;

public class TestAlgorithm {

	private int[][] field = new int[][] {
		// Bottom
		{ 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 1, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 1, 1, 0, 0, 1, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0, 0 }
		// Top
	};
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPerformance() {
		long start = System.currentTimeMillis();
		
		Game game = new Game(field);
		game.setMonsterPosition(new Point(0.9, 0.82));
		System.out.println(game.getQuadController().toStringWithIndices());
		Path path = game.getPathForMonster();
		if (null == path) {
			fail("Can't measure performance. Missing returned path");
		}
		
		long end = System.currentTimeMillis();
		long elapsedTime = end - start;
	    System.out.println("Performance of algorithm: " + elapsedTime + "ms elapsed");
	}
	
	@Test
	public void testPerformancePerCalculation() {
		Game game = new Game(field);
		game.setMonsterPosition(new Point(0.9, 0.82));
		
		long avg = 0;
		int itr = 10;
		for (int i = 0; i < itr; i++) {
			long start = System.currentTimeMillis();
			
			Path path = game.getPathForMonster();
			if (null == path) {
				fail("Can't measure performance. Missing returned path");
			}
			
			long end = System.currentTimeMillis();
			long elapsedTime = end - start;
		    avg += elapsedTime;
		}
		
		System.out.println("Average time elapsed per calculation: " + ((double)avg / (double)itr) + " ms.");
	}

}
