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
public class WalkValidator extends MoveValidator {

	private static WalkValidator instance = new WalkValidator();
	
	/** 
	 * Gets the instance
	 * @return The singleton instance
	 */
	public static WalkValidator getInstance() {
		return instance;
	}
	
	protected WalkValidator() {
	}
	
	/** Determines if the given move is valid or not
	 * 
	 * @param board The current board
	 * @param piece The piece to move
	 * @param from The from coordinate to move the piece from
	 * @param to The coordinate to move the piece too
	 * @return True if the move is valid, false otherwise
	 */
	@Override
	public boolean validateMove(GameBoard board, HantoPiece piece, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		if (!super.validateMove(board, piece, from, to)) {
			return false;
		}
		List<HantoCoordinateImpl> fromAdj = from.Neighbors();
		List<HantoCoordinateImpl> toAdj = to.Neighbors();
		
		fromAdj.retainAll(toAdj);
		if (fromAdj.size() == 2) {
			int freeSpots = 0;
			for (HantoCoordinateImpl coord : fromAdj) {
				if (board.isLocationEmpty(coord)) {
					freeSpots++;
				}
			}			
			return freeSpots > 0;			
		}
		
		return false;
	}
	
	public boolean isMoveLegal(GameBoard board, HantoPiece piece, HantoCoordinateImpl start) {
		List<HantoCoordinateImpl> adjCoords = start.Neighbors();
		for(HantoCoordinateImpl coord : adjCoords) {
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
		List<HantoCoordinateImpl> adjCoords = currentPosition.Neighbors();
		for(HantoCoordinateImpl coord : adjCoords) {
			if(validateMove(board, piece, currentPosition, coord)) {
				validDesitnations.add(coord);
			}
		}
		return validDesitnations;
	}
	
}
