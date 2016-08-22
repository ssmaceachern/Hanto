/**
 * 
 */
package hanto.student.ssmaceachern.common.movevalidator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.validator.IMoveValidator;
import hanto.studentssmaceachern.common.validator.NonMoveValidator;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/** Tests No Movement Validator
 * @author Sean
 *
 */
public class NoMovementValidatorTest {

	/** The movement validator to use */
	IMoveValidator moveValidator;
	GameBoard board;
	HantoPieceImpl testPiece;
	
	@Before
	public void init() throws HantoException {
		moveValidator = NonMoveValidator.getInstance();
		board = new GameBoard();
		testPiece = new HantoPieceImpl(HantoPlayerColor.BLUE, HantoPieceType.CRAB);
		
		board.addPieceToBoard(testPiece, new HantoCoordinateImpl(0, 0));
	}
	
	@Test
	public void testValidatorHasNoValidDestinations() {
		List<HantoCoordinateImpl> destinations = moveValidator.getValidMoveLocations(board, 
				testPiece, new HantoCoordinateImpl(0, 0));
		assertTrue(destinations.isEmpty());
	}
	
	@Test
	public void testValidatorCannotMove() {
		boolean canMove = moveValidator.isMoveLegal(board, 
				testPiece, new HantoCoordinateImpl(0, 0));
		assertFalse(canMove);
	}
	
}
