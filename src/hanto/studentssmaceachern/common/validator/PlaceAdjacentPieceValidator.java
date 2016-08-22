/**
 * 
 */
package hanto.studentssmaceachern.common.validator;

import hanto.common.HantoPiece;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;

import java.util.List;

/**
 * @author Sean
 *
 */
public class PlaceAdjacentPieceValidator extends PlacePieceValidator{

	/** The singleton instance */
	private static PlaceAdjacentPieceValidator instance = new PlaceAdjacentPieceValidator();
	
	/** Gets the singleton instance
	 * 
	 * @return The singleton instance
	 */
	
	public static PlaceAdjacentPieceValidator getInstance() {
		return instance;
	}
	
	/** Protected constructor
	 * 
	 */
	protected PlaceAdjacentPieceValidator() {
	}
	
	
	/** Checks to  see if the board is empty and move is to 0,0, The piece has an adjacent piece, and it is not being placed
	 * 	on an existing piece
	 */
	
	public boolean isPlacementValid(GameBoard board, HantoPiece toPlace, HantoCoordinateImpl to) {		
		if (!super.isPlacementValid(board, toPlace, to)) {
			return false;
		}
		
		if (board.getPieceCount() >= 2) {
			List<HantoPieceImpl> adjacentPeices = board.getAdjacentPieces(to);
			//check that the color of all the adjacent peices are same color
			for (HantoPieceImpl piece : adjacentPeices) {
				if (!piece.getColor().equals(toPlace.getColor())) {
					return false; //placed a peice next to a color not his own
				}
			}
		}
		 
		 return true;
		
	}
	
	

}
