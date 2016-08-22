/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.alpha;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.exceptions.GameNotActiveException;
import hanto.studentssmaceachern.common.exceptions.MoveException;
import hanto.studentssmaceachern.common.exceptions.PieceTypeException;

/** Alpha Version for Hanto
 * 
 * @author Sean
 *
 */
public class AlphaHantoGame implements HantoGame {

	private int turnCount;
	
	private GameBoard board;
	
	public AlphaHantoGame() {
		turnCount = 0;
		board = new GameBoard();
	}	
	
	/**
	 * Override of the Make Move from HantoGame
	 * @param piece
	 * @param from
	 * @param to
	 * @return MoveResult
	 * @throws HantoException
	 */
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {		
		if (to == null) {
			throw new MoveException("Move destination cannot be null");
		}
		HantoCoordinateImpl toCoord = convertHantoCoordinate(to);
		HantoCoordinateImpl fromCoord = null;
		if (from != null) {
			 fromCoord = convertHantoCoordinate(from);
		}
		return makeMove(pieceType, fromCoord, toCoord);
		
	}
	

	/**
	 * Override of the Make Move from HantoGame
	 * @param piece
	 * @param from
	 * @param to
	 * @return MoveResult
	 * @throws HantoException
	 */
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinateImpl from,
			HantoCoordinateImpl to) throws HantoException {
		
		//check if the game is over
		if (isGameOver()) {
			throw new GameNotActiveException();
		}
		
		//check that pieces are not moving
		if(from != null) {
			throw new HantoException("Pieces cannot move in Alpha Hanto");
		}
		
		//check if it is a valid piece type
		if (isValidPieceType(pieceType)) {		
			//add piece to the board
			board.addPieceToBoard(new HantoPieceImpl(getTurnColor(), pieceType), to);		
			turnCount ++;			
			if (turnCount > 1) {
				return MoveResult.DRAW;
			}
			return MoveResult.OK;
		}
		else {
			throw new PieceTypeException(pieceType, "Invalid piece type");
		}
	}
	

	/** Returns the player color for the current turn
	 * 
	 * @return The player color
	 */
	private HantoPlayerColor getTurnColor() {
		if (turnCount % 2 == 0) {
			return HantoPlayerColor.BLUE;
		}
		else {
			return HantoPlayerColor.RED;
		}
	}
	
	/** Determines if the given piece is a valid piece type for the game
	 * 
	 * @param type The type of the piece
	 * @return True if the piece is valid, false otherwise
	 */
	private boolean isValidPieceType(HantoPieceType type) {
		return type == HantoPieceType.BUTTERFLY;
	}
	
	/** Returns if the game is over or not
	 * 
	 * @return True if the game is over, false otherwise
	 */

	protected boolean isGameOver() {
		return turnCount >= 2;
	}
	
	/** Converts the given hanto coordinate into a Hanto Coord implementation
	 * 
	 * @param coord The coordinate to convert
	 * @return The HantoCoord impl of this coord
	 */
	private HantoCoordinateImpl convertHantoCoordinate(HantoCoordinate coord) {
		return new HantoCoordinateImpl(coord);
	}

	/**
	 * @return a printable representation of the board.
	 */	
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
	
	/**
	 * @param where the coordinate to query
	 * @return the piece at the specified coordinate or null if there is no 
	 * 	piece at that position
	 */
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return board.getPieceAt(convertHantoCoordinate(where));
	}

}
