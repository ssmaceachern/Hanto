/**
 * 
 */
package hanto.studentssmaceachern.common.validator;

import hanto.common.HantoPiece;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;

/** The basic piece placement validator	
 * 
 * @author Sean
 *
 */
public class PlacePieceValidator implements IPlacePieceValidator{
	
	/** Checks to  see if the board is empty and move is to 0,0, The piece has an adjacent piece, and it is not being placed
	 * 	on an existing piece
	 */
	
	public boolean isPlacementValid(GameBoard board, HantoPiece toPlace, HantoCoordinateImpl to) {		
		//if the board is empty, the piece must be at 0,0
		if (board.getPieceCount() == 0) {
			return to.equals(new HantoCoordinateImpl(0, 0));
		}
		if (board.getPieceAt(to) != null) {
			return false; // there is a piece on this location
		}
		//only allow placement if it has an adjacent piece
		return board.hasAdjacentPiece(to);
	}

}
