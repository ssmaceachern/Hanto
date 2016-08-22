package hanto.student.ssmaceachern.delta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoTestGame;
import hanto.common.HantoTestGameFactory;
import hanto.common.MoveResult;
import hanto.common.HantoTestGame.PieceLocationPair;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoGameFactory;
import hanto.studentssmaceachern.delta.DeltaHantoGame;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DeltaHantoTestGame {
	/** The hanto game to use for testing */
	private HantoTestGame game;
	
	@Before
	public void setup() {
		game = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLUE);
	}
	
	private void initWithButterflies() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,0)); //blue butteryfly
		game.makeMove(HantoPieceType.BUTTERFLY, null, new HantoCoordinateImpl(0,1)); //red butterfly
	}
	
	/** Test gamma ends after 20 turns
	 * 
	 * @throws HantoException if the move failed
	 */
	@Test
	public void gameShouldEndAfterResign() throws HantoException {
		assertEquals(MoveResult.RED_WINS, game.makeMove(null, null, null));
	}
	
	@Test (expected = HantoException.class)
	public void gameIsOverAfterResign() throws HantoException {
		game.makeMove(null, null, null);
		game.makeMove(HantoPieceType.BUTTERFLY, null, createCoord(0,0));
	}
	
	@Test
	public void sparrowCanFly() throws HantoException {
		List<PieceLocationPair> initialPieces = new ArrayList<PieceLocationPair>();
		
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,0)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,1)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, createCoord(0,2)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, createCoord(0,3)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,4)));
		
		game.initializeBoard(initialPieces.toArray(new PieceLocationPair[0]));
		assertEquals(MoveResult.OK, game.makeMove(HantoPieceType.SPARROW, createCoord(0,0), createCoord(0,5)));
	}
	
	@Test (expected = HantoException.class)
	public void flyToOccupiedSpaceThrowsException() throws HantoException {
		List<PieceLocationPair> initialPieces = new ArrayList<PieceLocationPair>();
		
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,0)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,1)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, createCoord(0,2)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.RED, HantoPieceType.SPARROW, createCoord(0,3)));
		initialPieces.add(new PieceLocationPair(HantoPlayerColor.BLUE, HantoPieceType.SPARROW, createCoord(0,4)));
		
		game.initializeBoard(initialPieces.toArray(new PieceLocationPair[0]));
		game.makeMove(HantoPieceType.SPARROW, createCoord(0,0), createCoord(0,4));
	}
	
	@Test
	public void crabsWork() throws HantoException {
		game.makeMove(HantoPieceType.CRAB, null, createCoord(0,0));
		game.makeMove(HantoPieceType.CRAB, null, createCoord(0,1));
		game.makeMove(HantoPieceType.CRAB, createCoord(0,0), createCoord(1,0));
	}
	
	@Test
	public void butterfliesWork() throws HantoException {
		game.makeMove(HantoPieceType.BUTTERFLY, null, createCoord(0,0));
		game.makeMove(HantoPieceType.BUTTERFLY, null, createCoord(0,1));
		game.makeMove(HantoPieceType.BUTTERFLY, createCoord(0,0), createCoord(1,0));
	}
	
	@Test (expected = HantoException.class)
	public void invalidPieceThrowsException() throws HantoException {
		game.makeMove(HantoPieceType.HORSE, null, createCoord(0,0));
	}
	
	@Test
	public void gameFactoryMakesDeltaHanto() {
		HantoGame deltaGame = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.DELTA_HANTO);
		assertTrue(deltaGame instanceof DeltaHantoGame);
	}
	
	public HantoCoordinate createCoord(int x, int y) {
		return new HantoCoordinateImpl(x,y);
	}
}
