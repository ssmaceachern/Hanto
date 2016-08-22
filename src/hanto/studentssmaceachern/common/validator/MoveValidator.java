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

import java.util.ArrayList;
import java.util.List;

import hanto.common.HantoPiece;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;

/**
 * @author Sean
 *
 */
public class MoveValidator implements IMoveValidator {
	
	@Override
	public boolean validateMove(GameBoard board, HantoPiece piece, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		HantoPiece toMove = board.getPieceAt(from);
		//check that the piece on the board, is the same as the piece we were told to move
		if (toMove == null || toMove.getColor() != piece.getColor() || toMove.getType() != piece.getType()) {
			return false; //given piece differsnt from board piece
		}
		if (!board.isLocationEmpty(to)) {
			return false; //destination is not empty
		}
		return isMoveContiguous(board, piece, from, to);
	}
	
	/** Determines if the move is contigious or not
	 * 
	 * @param board The board to make the move on
	 * @param piece The piece to move
	 * @param from The coordinate to move from
	 * @param to The coordinate to mvoe to
	 * @return True if the board is contigous after the move, false otherwise
	 */
	public boolean isMoveContiguous(GameBoard board, HantoPiece piece, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		GameBoard testBoard = board.copy();
		testBoard.movePiece(from, to);
		
		HantoCoordinateImpl current = to;
		List<HantoCoordinateImpl> visited = new ArrayList<HantoCoordinateImpl>();
		List<HantoCoordinateImpl> toVisit =  new ArrayList<HantoCoordinateImpl>();
		
		visited.add(current);
		toVisit.addAll(testBoard.getAdjacentLocationsWithPieces(current));
		
		while (toVisit.size() > 0) {
			current = toVisit.remove(0);			
			if (visited.contains(current)) {
				continue;
			}
			visited.add(current);			
			List<HantoCoordinateImpl> adjCoords = testBoard.getAdjacentLocationsWithPieces(current);
			for (HantoCoordinateImpl coord : adjCoords) {
				if (!visited.contains(coord)) {
					toVisit.add(coord);
				}
			}
		}		
		//if we visited every spot on the board is is contigous
		return testBoard.getPieceCount() == visited.size();
	}

	@Override
	public boolean isMoveLegal(GameBoard board, HantoPiece piece,
			HantoCoordinateImpl start) {
		// TODO Auto-generated method stub
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
