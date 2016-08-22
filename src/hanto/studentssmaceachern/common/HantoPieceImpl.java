/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.common;

import java.util.List;

import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.validator.IMoveValidator;
import hanto.studentssmaceachern.common.validator.IPlacePieceValidator;
import hanto.studentssmaceachern.common.validator.MoveValidator;
import hanto.studentssmaceachern.common.validator.PlacePieceValidator;

/**
 * 
 * @author Sean
 *
 */
public class HantoPieceImpl implements HantoPiece {

	private HantoPlayerColor color;
	private HantoPieceType type;
	
	private IPlacePieceValidator placementValidator;
	private IMoveValidator moveValidator;
	
	/** Creates a new Hanto Piece Implementation with the given color and type, And the default move and placement validators
	 * 
	 * @param color The color of the piece
	 * @param type The type of the piece 
	 */
	
	public HantoPieceImpl(HantoPlayerColor color, HantoPieceType type) {
		this(color, type, new PlacePieceValidator(), new MoveValidator());
	}
	
	/** Creates a new Hanto Piece Implementation with the given color, type, placement validator, and move validator
	 * 
	 * @param color The color of the piece
	 * @param type The type of the piece
	 * @param placementValidator The placement validator for the piece
	 * @param moveValidator The movement validator for the piece
	 */
	public HantoPieceImpl(HantoPlayerColor color, HantoPieceType type, IPlacePieceValidator placementValidator, IMoveValidator moveValidator) {
		this.color = color;
		this.type = type;
		this.placementValidator = placementValidator;
		this.moveValidator = moveValidator;
	}
	
	public HantoPlayerColor getColor() {
		return color;
	}

	public HantoPieceType getType() {
		return type;
	}
	
	/** Determines if placing this at a specified location is valid
	 * 
	 * @param board The board to place the piece on
	 * @param to The position to place the piece
	 * @return True if the placement is valid, false otherwise
	 */
	public boolean isPlacementValid(GameBoard board, HantoCoordinateImpl to) {	
		return placementValidator.isPlacementValid(board, this, to);
	}
	
	/** Determines if moving this piece from a location to a location is valid
	 * 
	 * @param board The board the piece will be moving on
	 * @param from The starting coordinate of the move
	 * @param to The destination coordinate of the move
	 * @return True if the movement is valid, false otherwise
	 */
	public boolean isMoveValid(GameBoard board, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		return moveValidator.validateMove(board, this, from, to);
	}
	
	/** Determines if the piece has any legal moves on the given board
	 * 
	 * @param board The board to check for legal moves on
	 * @param position The position of the piece on the board
	 * @return True if the piece has any legal moves, false otherwise
	 */
	
	public boolean hasLegalMove(GameBoard board, HantoCoordinateImpl position) {
		return moveValidator.isMoveLegal(board, this, position);
	}
	
	/** Returns a list of all the places this piece is able to move
	 * 
	 * @param board The board
	 * @param currentPosition The current position of the piece
	 * @return The list of coordinates that this piece can legally move to
	 */
	public List<HantoCoordinateImpl> getValidMovementCoordinates(GameBoard board, HantoCoordinateImpl currentPosition) {
		return moveValidator.getValidMoveLocations(board, this, currentPosition);
	}
		

	
	
}
