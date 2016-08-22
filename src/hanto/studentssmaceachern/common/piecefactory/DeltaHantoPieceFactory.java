/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.common.piecefactory;

import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.HantoPieceFactory;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.exceptions.PieceTypeException;
import hanto.studentssmaceachern.common.validator.FlyValidator;
import hanto.studentssmaceachern.common.validator.IPlacePieceValidator;
import hanto.studentssmaceachern.common.validator.PlaceAdjacentPieceValidator;
import hanto.studentssmaceachern.common.validator.WalkValidator;

/** Piece Factory for Delta Hanto
 * 
 * @author Sean
 *
 */
public class DeltaHantoPieceFactory implements HantoPieceFactory {

	/** The placement validator to use for pieces */
	private IPlacePieceValidator placementValidator;
	/** The flying movement validator */
	private FlyValidator flyMoveValidator;
	
	/** Creates a new Piece Factory for Delta Hanto
	 * 
	 */
	public DeltaHantoPieceFactory() {
		placementValidator = PlaceAdjacentPieceValidator.getInstance();
		flyMoveValidator = new FlyValidator();
	}
	
	@Override
	public HantoPieceImpl makePiece(HantoPlayerColor color,
			HantoPieceType type) throws HantoException {
		HantoPieceImpl piece = null;
		switch(type) {
		case BUTTERFLY:
			piece = new HantoPieceImpl(color, type, placementValidator, WalkValidator.getInstance());
			break;
		case CRAB:
			piece = new HantoPieceImpl(color, type, placementValidator, WalkValidator.getInstance());
			break;
		case SPARROW:
			piece = new HantoPieceImpl(color, type, placementValidator, flyMoveValidator);
			break;
		default:
			throw new PieceTypeException(type, "Unavailable Piece Type");
		}
		return piece;
	}

}
