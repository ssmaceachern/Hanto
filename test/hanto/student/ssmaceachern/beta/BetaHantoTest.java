/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.student.ssmaceachern.beta;

import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;
import static org.junit.Assert.*;
import hanto.common.*;
import hanto.studentssmaceachern.common.HantoGameFactory;

import org.junit.*;

/**
 * 
 * @author Sean
 *
 */
public class BetaHantoTest
{
	
	class TestHantoCoordinate implements HantoCoordinate
	{
		private final int x, y;
		
		public TestHantoCoordinate(int x, int y)
		{
			this.x = x;
			this.y = y;
		}
		/*
		 * @see hanto.common.HantoCoordinate#getX()
		 */
		@Override
		public int getX()
		{
			return x;
		}

		/*
		 * @see hanto.common.HantoCoordinate#getY()
		 */
		@Override
		public int getY()
		{
			return y;
		}

	}
	
	private static HantoGameFactory factory;
	private HantoGame game;
	
	@BeforeClass
	public static void initializeClass()
	{
		factory = HantoGameFactory.getInstance();
	}
	
	@Before
	public void setup()
	{
		// By default, blue moves first.
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO, BLUE);
	}
	
	@Test	// 1
	public void testBluePlacesInitialButterflyAtOrigin() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test(expected=HantoException.class)	// 2
	public void testPlaceTwoButterflies() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(1, 0));
	}
	
	@Test	// 3
	public void testRedPlacesButterfly() throws HantoException
	{
		game = factory.makeHantoGame(HantoGameID.BETA_HANTO, RED);
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(RED, p.getColor());
		assertEquals(BUTTERFLY, p.getType());
	}
	
	@Test	// 4
	public void testFirstPlayerPlacesSparrow() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece p = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, p.getColor());
		assertEquals(SPARROW, p.getType());
	}
	
	@Test(expected=HantoException.class)	// 5
	public void testBluePlayerDoesNotPlaceButterflyDownAfterFourTurns() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 1)); // Red
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 3)); // Red
		game.makeMove(SPARROW, null, makeCoordinate(0, 4));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 5)); // Red
		game.makeMove(SPARROW, null, makeCoordinate(0, 6));	// Blue
	}
	
	@Test(expected=HantoException.class)	// 6
	public void testRedPlayerDoesNotPlaceButterflyDownAfterFourTurns() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 1)); // Red
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 3)); // Red
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 4));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 5)); // Red
		game.makeMove(SPARROW, null, makeCoordinate(0, 6));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 7)); // Red
	}
	
	@Test(expected=HantoException.class)	// 7
	public void testPlacePieceInNonAdjacentPosition() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(3, -2));
	}
	
	@Test(expected=HantoException.class)	// 8
	public void testFirstMoveNotAtOrigin() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(-1, 0));
	}
	
	@Test(expected=HantoException.class)	// 9
	public void testAttemptToMoveAPiece() throws HantoException
	{
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));	// Blue
		game.makeMove(SPARROW, null, makeCoordinate(0, 1)); // Red
		game.makeMove(SPARROW, makeCoordinate(0,0), makeCoordinate(0, -1));	// Blue
	}
	
	
	
	@Test(expected=HantoException.class)	// 10
	public void testPlacePieceOnLocationWithPiece() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(SPARROW, null, makeCoordinate(0, 0));
	}
	
	@Test	// 11
	public void testRedIsAbleToWin() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(1, -1));
		assertEquals(RED_WINS, mr);
	}
	
	@Test	// 12
	public void testBlueIsFirstToMoveAndWins() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(1,-1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(-1, -1));
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		assertEquals(BLUE_WINS, mr);
	}
	
	@Test	// 13
	public void testRedWinsOnLastMove() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); 	// 1
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1)); 	// 2
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2)); 	// 3
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3)); 	// 4
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(1, 0)); 	// 5
		game.makeMove(SPARROW, null, makeCoordinate(-1, 1));
		game.makeMove(SPARROW, null, makeCoordinate(1, -1)); 	// 6
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(-1, 0));
		assertEquals(RED_WINS, mr);
	}
	
	@Test	// 14
	public void testGameTurnsIntoDrawAfterSixMoves() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0)); // 1
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 1)); // 2
		game.makeMove(SPARROW, null, makeCoordinate(0, -2));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2)); // 3
		game.makeMove(SPARROW, null, makeCoordinate(0, -3));
		game.makeMove(SPARROW, null, makeCoordinate(0, 3)); // 4
		game.makeMove(SPARROW, null, makeCoordinate(0, -4));
		game.makeMove(SPARROW, null, makeCoordinate(0, 4)); // 5
		game.makeMove(SPARROW, null, makeCoordinate(0, -5));
		game.makeMove(SPARROW, null, makeCoordinate(0, 5)); // 6
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, -6));
		assertEquals(DRAW, mr);
	}
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
}
