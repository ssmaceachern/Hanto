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
 * 
 * @author Sean
 *
 */
public class JumpValidator extends MoveValidator {
	
	private static JumpValidator instance = new JumpValidator();
	
	/** 
	 * Gets the instance
	 * @return The singleton instance
	 */
	public static JumpValidator getInstance() {
		return instance;
	}
	
	protected JumpValidator() {}
	
	@Override
	public boolean validateMove(GameBoard board, HantoPiece piece, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		if (!super.validateMove(board, piece, from, to)) {
			return false;
		}
		if (from.getY() == to.getY()) {
			for (int i = (Math.min(from.getX(), to.getX()) + 1);
					i < Math.max(from.getX(), to.getX()); i++) {
				if (board.isLocationEmpty(new HantoCoordinateImpl(i, to.getY()))) {
					return false;
				}
			}
			return Math.abs(from.getX() - to.getX()) > 1;
		}
		else if (from.getX() == to.getX()) {
			for (int i = (Math.min(from.getY(), to.getY()) + 1);
					i < Math.max(from.getY(), to.getY()); i++) {
				if (board.isLocationEmpty(new HantoCoordinateImpl(to.getX(), i))) {
					return false;
				}
			}
			return Math.abs(from.getY() - to.getY()) > 1;
		}
		else if ((to.getX() - from.getX()) == -1 * (to.getY() - from.getY())) {
			int startX = Math.min(to.getX(), from.getX());
			int startY = startX == to.getX() ? to.getY() : from.getY();
			for (int i = 1; i < Math.abs(to.getX() - from.getX()); i++) {
				if (board.isLocationEmpty(new HantoCoordinateImpl(startX + i, startY - i))) {
					return false;
				}
			}
			//check distance
			return Math.abs(from.getY() - to.getY()) > 1;
		}
		return false;
	}
	
	
	@Override
	public boolean isMoveLegal(GameBoard board, HantoPiece piece, HantoCoordinateImpl currentPosition) {
		if (hasValidMoveInLine(board, piece, currentPosition, 0, 1)) {
			return true;
		}
		else if (hasValidMoveInLine(board, piece, currentPosition, 0, -1)) {
			return true;
		}
		else if (hasValidMoveInLine(board, piece, currentPosition, 1, 0)) {
			return true;
		}
		else if (hasValidMoveInLine(board, piece, currentPosition, -1, 0)) {
			return true;
		}
		else if (hasValidMoveInLine(board, piece, currentPosition, 1, -1)) {
			return true;
		}
		else if (hasValidMoveInLine(board, piece, currentPosition, -1, 1)) {
			return true;
		}		
		
		return false;
	}
	
	/** Checks if there is a valid move at the end of the specified line
	 * 
	 * @param board The board
	 * @param piece The piece
	 * @param startingCoord The starting coordinate of the line
	 * @param xinc The x increment for each iteration
	 * @param yinc The y increment for each iteration
	 * @return True if the move is legal, false otherwise
	 */
	private boolean hasValidMoveInLine(GameBoard board, HantoPiece piece, HantoCoordinateImpl startingCoord, int xinc, int yinc) {
		HantoCoordinateImpl currentCoord = getFirstEmptyCoordInLine(board, startingCoord, xinc, yinc);
		return validateMove(board, piece, startingCoord, currentCoord);
	}
	
	/** Gets the first empty coord in a line, with the given x and y increments
	 * 
	 * @param board The board
	 * @param startingCoord The starting coordinate of the line
	 * @param xinc The x increment for each iteration
	 * @param yinc The y increment for each iteration
	 * @return The coordinate of the first empty coordinate in the line
	 */
	private HantoCoordinateImpl getFirstEmptyCoordInLine(GameBoard board, HantoCoordinateImpl startingCoord, int xinc, int yinc) {
		HantoCoordinateImpl currentCoord = new HantoCoordinateImpl(startingCoord.getX() + xinc, startingCoord.getY() + yinc);
		while (!board.isLocationEmpty(currentCoord)) {
			currentCoord.shiftX(xinc);
			currentCoord.shiftY(yinc);
		}
		return currentCoord;
	}
	
	/** Returns a list of all the places the specified piece is able to move
	 * 
	 * @param board The board
	 * @param piece The piece to move
	 * @param currentPosition The current position of the piece
	 * @return
	 */
	public List<HantoCoordinateImpl> getValidMoveLocations(GameBoard board, HantoPiece piece, HantoCoordinateImpl currentPosition) {
		List<HantoCoordinateImpl> validDestinations = new ArrayList<HantoCoordinateImpl>();
		//x equals x
		HantoCoordinateImpl currentCoord = getFirstEmptyCoordInLine(board, currentPosition, 0, 1);
		if (validateMove(board, piece, currentPosition, currentCoord)) {
			validDestinations.add(currentCoord);
		}
		currentCoord = getFirstEmptyCoordInLine(board, currentPosition, 0, -1);
		if (validateMove(board, piece, currentPosition, currentCoord)) {
			validDestinations.add(currentCoord);
		}
		//y equals y
		currentCoord = getFirstEmptyCoordInLine(board, currentPosition, 1, 0);
		if (validateMove(board, piece, currentPosition, currentCoord)) {
			validDestinations.add(currentCoord);
		}
		currentCoord = getFirstEmptyCoordInLine(board, currentPosition, -1, 0);
		if (validateMove(board, piece, currentPosition, currentCoord)) {
			validDestinations.add(currentCoord);
		}
		//diagnolly
		currentCoord = getFirstEmptyCoordInLine(board, currentPosition, 1, -1);
		if (validateMove(board, piece, currentPosition, currentCoord)) {
			validDestinations.add(currentCoord);
		}
		currentCoord = getFirstEmptyCoordInLine(board, currentPosition, -1, 1);
		if (validateMove(board, piece, currentPosition, currentCoord)) {
			validDestinations.add(currentCoord);
		}
		
		return validDestinations;
	}
	
}
