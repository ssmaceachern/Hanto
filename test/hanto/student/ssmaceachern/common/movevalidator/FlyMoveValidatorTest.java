/**
 * 
 */
package hanto.student.ssmaceachern.common.movevalidator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.validator.FlyValidator;
import hanto.studentssmaceachern.common.validator.IMoveValidator;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/** Tests No Movement Validator
 * @author Sean
 *
 */
public class FlyMoveValidatorTest {

	/** The movement validator to use */
	IMoveValidator moveValidator;
	GameBoard board;
	HantoPieceImpl testPiece;
	
	@Before
	public void init() throws HantoException {
		moveValidator = new FlyValidator(2);
		board = new GameBoard();
		testPiece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		
		board.addPieceToBoard(testPiece, new HantoCoordinateImpl(0, 0));
	}
	
	@Test
	public void testFlyValidatorValidDestinations() {
		List<HantoCoordinateImpl> validDestinations = new ArrayList<HantoCoordinateImpl>();
		validDestinations.add(createCoord(0, 1));
		validDestinations.add(createCoord(1, 0));
		validDestinations.add(createCoord(1, -1));
		validDestinations.add(createCoord(0, -1));
		validDestinations.add(createCoord(-1, 0));
		validDestinations.add(createCoord(-1, 1));
		validDestinations.add(createCoord(0, 2));
		validDestinations.add(createCoord(1, 1));
		validDestinations.add(createCoord(2, 0));
		validDestinations.add(createCoord(2, -1));
		validDestinations.add(createCoord(2, -2));
		validDestinations.add(createCoord(1, -2));
		validDestinations.add(createCoord(0, -2));
		validDestinations.add(createCoord(-1, -1));
		validDestinations.add(createCoord(-2, 0));
		validDestinations.add(createCoord(-2, 1));
		validDestinations.add(createCoord(-2, 2));
		validDestinations.add(createCoord(-1, 2));
		
		List<HantoCoordinateImpl> destinations = moveValidator.getValidMoveLocations(board, 
				testPiece, new HantoCoordinateImpl(0, 0));
		assertTrue(destinations.containsAll(validDestinations));
		assertEquals(validDestinations.size(), destinations.size());
	}
	
	@Test
	public void testValidatorCanMove() {
		boolean canMove = moveValidator.isMoveLegal(board, 
				testPiece, new HantoCoordinateImpl(0, 0));
		assertTrue(canMove);
	}
	
	@Test
	public void testValidatorCanMoveInfinitivly() {
		moveValidator = new FlyValidator(0);
		boolean canMove = moveValidator.isMoveLegal(board, 
				testPiece, new HantoCoordinateImpl(0, 0));
		assertTrue(canMove);
	}
	
	@Test
	public void testCanNotMoveDifferentPiece() {
		HantoPieceImpl piece = new HantoPieceImpl(HantoPlayerColor.RED, HantoPieceType.HORSE);
		boolean isMoveLegal = moveValidator.validateMove(board, piece, createCoord(0, 0), createCoord(0, 1));
		assertFalse(isMoveLegal);
	}
	
	public HantoCoordinateImpl createCoord(int x, int y) {
		return new HantoCoordinateImpl(x,y);
	}
	
	
}
