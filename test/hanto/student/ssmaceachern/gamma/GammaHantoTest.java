package hanto.student.ssmaceachern.gamma;

import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.HantoGameFactory;
import static hanto.common.HantoPieceType.*;
import static hanto.common.MoveResult.*;
import static hanto.common.HantoPlayerColor.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GammaHantoTest {
	
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
		game = factory.makeHantoGame(HantoGameID.GAMMA_HANTO);
	}
	
	@Test //1
	public void bluePlacesButterflyFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(makeCoordinate(0, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(BUTTERFLY, piece.getType());
	}
	
	@Test //2
	public void redPlacesSparrowFirst() throws HantoException
	{
		final MoveResult mr = game.makeMove(SPARROW, null, makeCoordinate(0, 0));
		assertEquals(OK, mr);
	}
	
	@Test //3
	public void blueMovesSparrowTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, -1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
		final MoveResult mr = game.makeMove(SPARROW, makeCoordinate(0, -1), makeCoordinate(-1, 0));
		assertEquals(OK, mr);
		final HantoPiece piece = game.getPieceAt(makeCoordinate(-1, 0));
		assertEquals(BLUE, piece.getColor());
		assertEquals(SPARROW, piece.getType());
	}
	
	@Test(expected=HantoException.class) //4
	public void moveToDisconnectConfigurationTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class) //5
	public void attemptToMoveAPieceFromAnEmptyHexTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(1, 0), makeCoordinate(0, -1));
	}
	
	@Test(expected=HantoException.class) //6
	public void attemptToMoveWrongPieceTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, makeCoordinate(0, 0), makeCoordinate(1, 0));
	}
	
	@Test(expected=HantoException.class) //7
	public void attemptToPlacePieceNextToOpponentPieceTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(SPARROW, null, makeCoordinate(0, 2));
	}
	
	@Test(expected=HantoException.class) //8
	public void attemptToMoveMoreThanOneHexTest() throws HantoException
	{
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 0));
		game.makeMove(BUTTERFLY, null, makeCoordinate(0, 1));
		game.makeMove(BUTTERFLY, makeCoordinate(0, 0), makeCoordinate(0, 2));
	}
	
	@Test(expected=Exception.class) //9
	public void blueResignsImmediatelyTest() throws HantoException
	{
		assertEquals(RED_WINS, game.makeMove(null, null, null));
	}
	
	// Helper methods
	private HantoCoordinate makeCoordinate(int x, int y)
	{
		return new TestHantoCoordinate(x, y);
	}
	
}