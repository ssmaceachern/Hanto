/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/


package hanto.studentssmaceachern.common.validator;

import hanto.common.HantoPiece;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;

import java.util.List;

/**
 * @author Sean
 *
 */
public interface IMoveValidator {

	/**
	 * Validates an attempted move
	 * @param board
	 * @param piece
	 * @param from
	 * @param to
	 * @return true if move was validated
	 */
	boolean validateMove(GameBoard board, HantoPiece piece, 
			HantoCoordinateImpl from, HantoCoordinateImpl to);
	
	/**
	 * Checks whether or not a move is legal
	 * @param board
	 * @param piece
	 * @param current
	 * @return true if move was legal
	 */
	boolean isMoveLegal(GameBoard board, HantoPiece piece, 
			HantoCoordinateImpl current);
	
	/**
	 * Gets a list of valid move locations for a specific piece.
	 * @param board
	 * @param piece
	 * @param currentPosition
	 * @return list of valid move locations
	 */
	List<HantoCoordinateImpl> getValidMoveLocations(GameBoard board, HantoPiece piece, HantoCoordinateImpl currentPosition);
}
