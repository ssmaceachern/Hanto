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

import java.util.ArrayList;
import java.util.List;


/** 
 * 
 * @author Sean
 *
 */
public class FlyValidator extends MoveValidator {
	
	private int maxMoveDistance;
	
	public FlyValidator() {
		this(0);
	}
	
	/** Creates a new Move Validator for Flying
	 * @param maxMoveDistance The maximum number of coordinates that can be flown over, 0 for infinite
	 */
	public FlyValidator(int maxMoveDistance) {
		this.maxMoveDistance = maxMoveDistance;
	}
	
	@Override
	public boolean validateMove(GameBoard board, HantoPiece piece, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		if (!super.validateMove(board, piece, from, to)) {
			return false;
		}
		return maxMoveDistance == 0 || from.distFromCoordinate(to) <= maxMoveDistance;
	}
	
	@Override
	public boolean isMoveLegal(GameBoard board, HantoPiece piece,
			HantoCoordinateImpl start) {
		if(maxMoveDistance == 0) {
			return true;
		}
		List<HantoCoordinateImpl> adjCoords = start.getAdjacentCoordsRadius(maxMoveDistance);
		for(HantoCoordinateImpl coord: adjCoords) {
			if(validateMove(board, piece, start, coord)) {
				return true;
			}
		}
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
		List<HantoCoordinateImpl> validDesitnations = new ArrayList<HantoCoordinateImpl>();
		if (maxMoveDistance == 0) {
			return validDesitnations; //TODO IMPLEMENT AT SOME POINT... MAYBE.
		}
		List<HantoCoordinateImpl> adjCoords = currentPosition.getAdjacentCoordsRadius(maxMoveDistance);
		for(HantoCoordinateImpl coord: adjCoords) {
			if(validateMove(board, piece, currentPosition, coord)) {
				validDesitnations.add(coord);
			}
		}
		return validDesitnations;
	}

}
