/**
 * 
 */
package hanto.studentssmaceachern.common.validator;

import hanto.common.HantoPiece;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;

import java.util.ArrayList;
import java.util.List;

/** Move Validator for when movement is not allowed
 * 
 * @author Sean
 *
 */

public class NonMoveValidator implements IMoveValidator {

	/** The singleton instance */
	private static NonMoveValidator instance = new NonMoveValidator();
	
	/** Get the singleton instance
	 * 
	 * @return the singleton instance
	 */
	public static NonMoveValidator getInstance() {
		return instance;
	}
	
	/** Protected constructor
	 * 
	 */
	protected NonMoveValidator() {
		
	}
	
	@Override
	public boolean validateMove(GameBoard board, HantoPiece piece,
			HantoCoordinateImpl from, HantoCoordinateImpl to) {
		return false; // move ment is not allowed
	}

	@Override
	public boolean isMoveLegal(GameBoard board, HantoPiece piece,
			HantoCoordinateImpl start) {
		return false;
	}
	
	/** Returns a list of all the places the specified piece is able to move
	 * 
	 * @param board The board
	 * @param piece The piece to move
	 * @param currentPosition The current position of the piece
	 * @return
	 */
	public List<HantoCoordinateImpl> getValidMoveLocations(GameBoard board, HantoPiece piece, HantoCoordinateImpl currentPosition) {
		return new ArrayList<HantoCoordinateImpl>();
	}

	
	
}
