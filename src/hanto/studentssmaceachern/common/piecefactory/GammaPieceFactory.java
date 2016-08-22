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
import hanto.studentssmaceachern.common.validator.PlaceAdjacentPieceValidator;
import hanto.studentssmaceachern.common.validator.WalkValidator;

/**
 * @author Sean
 *
 */
public class GammaPieceFactory implements HantoPieceFactory {

	/** The placement validator for Gamma */
	private IPlacePieceValidator placementValidator;
	
	/** A Piece factory for Gamma
	 * 
	 */
	public GammaPieceFactory() {
		placementValidator = PlaceAdjacentPieceValidator.getInstance();
	}
	
	@Override
	public HantoPieceImpl makePiece(HantoPlayerColor color, HantoPieceType type)
			throws HantoException {
		HantoPieceImpl piece = null;
		
		switch (type) {
			case BUTTERFLY:
				piece = new HantoPieceImpl(color, type, placementValidator, WalkValidator.getInstance());
				break;
			case SPARROW:
				piece = new HantoPieceImpl(color, type, placementValidator, WalkValidator.getInstance());
				break;		
				
			default:
				throw new PieceTypeException(type, "Unavailable Piece Type");
		}	
		
		return piece;
	}

}
