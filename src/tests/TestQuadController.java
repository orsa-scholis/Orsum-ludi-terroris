package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logic.Game;
import logic.graph.*;

public class TestQuadController {
	
	private Game game;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		int[][] field = new int[][] {
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
		
		game = new Game(field);
		System.out.println(game.getQuadController().toStringWithIndices());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLineTestForObstacles() {
		Point[] point1s = new Point[] {
				new Point(1.0/8.0, 4.0/8.0),
				new Point(1.0/8.0, 3.0/8.0),
				new Point(1.0/8.0, 2.5/8.0),
				new Point(1.0/8.0, 3.5/8.0),
				new Point(4.0/8.0, 4.0/8.0),
				new Point(4.0/8.0, 4.0/8.0),
				new Point(0.0/8.0, 0.0/8.0),
				new Point(0.0/8.0, 0.0/8.0),
				new Point(1.0/8.0, 7.0/8.0)
		};
		
		Point[] point2s = new Point[] {
				new Point(5.0/8.0, 3.0/8.0),
				new Point(4.0/8.0, 1.0/8.0),
				new Point(3.0/8.0, 2.5/8.0),
				new Point(3.0/8.0, 3.5/8.0),
				new Point(4.0/8.0, 6.0/8.0),
				new Point(1.0/8.0, 7.0/8.0),
				new Point(2.0/8.0, 7.0/8.0),
				new Point(1.0/8.0, 7.0/8.0),
				new Point(0.0/8.0, 0.0/8.0)
		};
		
		boolean[] solutions = new boolean[] {
				false, true, true, false, false, true, true, false, false
		};
		
		for (int i = 0; i < point1s.length; i++) {
			Point point1 = point1s[i];
			Point point2 = point2s[i];
			boolean solution = solutions[i];
			boolean hasObsti = game.getQuadController().testLineForObstacles(point1, point2);
			
			if (hasObsti != solution) {
				fail("Function returned wrong value for line " + i + ".");
			}
		}
	}

}
