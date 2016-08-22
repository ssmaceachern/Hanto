/**
 * 
 */
package hanto.studentssmaceachern.common.validator;

import hanto.common.HantoPiece;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;

/**
 * 
 * @author Sean
 *
 */
public interface IPlacePieceValidator {
	
	/**
	 * Checks whether or not a placement is valid
	 * @param board
	 * @param piece
	 * @param loc
	 * @return true if player can place a piece at the given location
	 */
	boolean isPlacementValid(GameBoard board, HantoPiece piece, HantoCoordinateImpl loc);
	
}
