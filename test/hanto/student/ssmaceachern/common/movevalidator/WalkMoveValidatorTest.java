/**
 * 
 */
package hanto.student.ssmaceachern.common.movevalidator;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.validator.IMoveValidator;
import hanto.studentssmaceachern.common.validator.WalkValidator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Sean
 *
 */
public class WalkMoveValidatorTest {
	
	IMoveValidator moveValidator;
	GameBoard board;
	
	@Before
	public void setup() {
		this.board = new GameBoard();
		moveValidator = WalkValidator.getInstance();
	}
	
	@Test
	public void testWalkMoveValidatorWithNoPieces() throws HantoException {
		HantoPieceImpl piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		List<HantoCoordinateImpl> expectedList = new ArrayList<HantoCoordinateImpl>();
		expectedList.add(createCoord(1, 0));
		expectedList.add(createCoord(0, 1));
		expectedList.add(createCoord(-1, 1));
		expectedList.add(createCoord(-1, 0));
		expectedList.add(createCoord(0, -1));
		expectedList.add(createCoord(1, -1));
		assertTrue(moveValidator.getValidMoveLocations(board, piece, createCoord(0, 0)).containsAll(expectedList));
	}
	
	@Test
	public void testWalkMoveValidatorWithNoMoves() throws HantoException {
		HantoPieceImpl piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		HantoPieceImpl rpiece = new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		board.addPieceToBoard(rpiece, createCoord(0, 1));
		board.addPieceToBoard(rpiece, createCoord(-1, 0));
		board.addPieceToBoard(rpiece, createCoord(1, -1));
		assertTrue(moveValidator.getValidMoveLocations(board, piece, createCoord(0, 0)).isEmpty());
	}
	
	public HantoCoordinateImpl createCoord(int x, int y) {
		return new HantoCoordinateImpl(x,y);
	}

}
