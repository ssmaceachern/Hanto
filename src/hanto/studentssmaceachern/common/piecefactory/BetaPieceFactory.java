/**
 * 
 */
package hanto.studentssmaceachern.common.piecefactory;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.HantoPieceFactory;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.exceptions.PieceTypeException;
import hanto.studentssmaceachern.common.validator.IPlacePieceValidator;
import hanto.studentssmaceachern.common.validator.NonMoveValidator;
import hanto.studentssmaceachern.common.validator.PlacePieceValidator;

/**
 * @author Sean
 *
 */
public class BetaPieceFactory implements HantoPieceFactory {

	/** The Placement Validator to use for Beta Hanto */
	private IPlacePieceValidator placementValidator;
	
	public BetaPieceFactory() {
		placementValidator = new PlacePieceValidator();
	}

	public HantoPieceImpl makePiece(HantoPlayerColor color, HantoPieceType type)
			throws HantoException {

		HantoPieceImpl piece = null;
		
		switch(type) {
		case BUTTERFLY:
			piece = new HantoPieceImpl(color, type, placementValidator, NonMoveValidator.getInstance());
			break;
		case SPARROW:
			piece = new HantoPieceImpl(color, type, placementValidator, NonMoveValidator.getInstance());
			break;
			
			default:
				throw new PieceTypeException(type, "Invalid Piece Type");
		}
		
		return piece;
	}

}
