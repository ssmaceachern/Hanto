package hanto.student.ssmaceachern.common.movevalidator;

import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.validator.IMoveValidator;
import hanto.studentssmaceachern.common.validator.JumpValidator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class JumpMoveValidatorTest {
	IMoveValidator moveValidator;
	GameBoard board;
	
	@Before
	public void setup() {
		this.board = new GameBoard();
		moveValidator = JumpValidator.getInstance();
	}
	
	@Test
	public void testJumpMoveValidatorWithNoPieces() throws HantoException {
		HantoPieceImpl piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		board.addPieceToBoard(piece, createCoord(0, 0));
		assertTrue(moveValidator.getValidMoveLocations(board, piece, createCoord(0, 0)).isEmpty());
	}
	
	@Test
	public void testJumpMoveValidatorWhenSurrounded() throws HantoException {
		HantoPieceImpl piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		HantoPieceImpl rpiece = new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		board.addPieceToBoard(rpiece, createCoord(0, 1));
		board.addPieceToBoard(rpiece, createCoord(-1, 0));
		board.addPieceToBoard(rpiece, createCoord(1, -1));
		board.addPieceToBoard(rpiece, createCoord(1, 0));
		board.addPieceToBoard(rpiece, createCoord(0, -1));
		board.addPieceToBoard(rpiece, createCoord(-1, 1));
		List<HantoCoordinateImpl> expectedList = new ArrayList<HantoCoordinateImpl>();
		expectedList.add(createCoord(0, 2));
		expectedList.add(createCoord(0, -2));
		expectedList.add(createCoord(2, -2));
		expectedList.add(createCoord(-2, 2));
		expectedList.add(createCoord(2, 0));
		expectedList.add(createCoord(-2, 0));
		assertTrue(moveValidator.getValidMoveLocations(board, piece, createCoord(0, 0)).containsAll(expectedList));
	}
	
	@Test
	public void testJumpHasLegalMove() throws HantoException {
		HantoPieceImpl piece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.HORSE);
		HantoPieceImpl rpiece = new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.CRAB);
		board.addPieceToBoard(piece, createCoord(0, 0));
		board.addPieceToBoard(rpiece, createCoord(0, 1));
		assertTrue(moveValidator.isMoveLegal(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(0, 1));
		
		board.addPieceToBoard(rpiece, createCoord(-1, 0));
		assertTrue(moveValidator.isMoveLegal(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(-1, 0));
		
		board.addPieceToBoard(rpiece, createCoord(1, -1));
		assertTrue(moveValidator.isMoveLegal(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(1, -1));
		
		board.addPieceToBoard(rpiece, createCoord(1, 0));
		assertTrue(moveValidator.isMoveLegal(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(1, 0));
		
		board.addPieceToBoard(rpiece, createCoord(0, -1));
		assertTrue(moveValidator.isMoveLegal(board, piece, createCoord(0, 0)));
		board.removePeiceFromBoard(createCoord(0, -1));
		
		board.addPieceToBoard(rpiece, createCoord(-1, 1));
		assertTrue(moveValidator.isMoveLegal(board, piece, createCoord(0, 0)));
	}
	
	public HantoCoordinateImpl createCoord(int x, int y) {
		return new HantoCoordinateImpl(x,y);
	}
}
