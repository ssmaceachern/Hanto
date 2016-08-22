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

import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.exceptions.MoveException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/** Game Board
 * 
 * @author Sean
 *
 */

public class GameBoard {

	private Map<HantoCoordinateImpl, HantoPieceImpl> pieces;
	
	/** Creates a mew Hanto Board and initializes the board
	 * 
	 */
	public GameBoard() {
		pieces = new HashMap<HantoCoordinateImpl, HantoPieceImpl>();
	}
	
	/** Creates a new Hanto board with the given pieces 
	 * 
	 * @param pieces The initial pieces on the board
	 */
	public GameBoard(Map<HantoCoordinateImpl, HantoPieceImpl> pieces) {
		this.pieces = pieces;
	}
	
	/** Returns the piece at the given location
	 * 
	 * @param where The coordinate to get the piece of
	 * @return The piece at the given coordinate
	 */
	public HantoPieceImpl getPieceAt(HantoCoordinateImpl where) {
		return pieces.get(where);
	}
	
	/** Checks if the piece at the given location is empty
	 * 
	 * @param where The location to check
	 * @return True if the spot is empty, false otherwise
	 */
	public boolean isLocationEmpty(HantoCoordinateImpl where) {
		return getPieceAt(where) == null;
	}
	
	/** Adds a piece to the board, and determines if is a valid spot
	 * 
	 * @param piece The piece to add
	 * @param to The position to add the piece to
	 * @throws HantoException if the spot is invalid  
	 */
	public void addPieceToBoard(HantoPieceImpl piece, HantoCoordinateImpl to) throws HantoException {		
		if (isValidMove(piece, to)) {
			pieces.put(to, piece);
		}
		else {
			throw new MoveException("Invalid Move");
		}
	}
	
	/** Pops a piece from the board
	 * 
	 * @param at The location of the piece to remove
	 * @return A copy of the piece removed
	 */
	public HantoPieceImpl removePeiceFromBoard(HantoCoordinateImpl at) {
		return pieces.remove(at);
	}
	
	/** Moves the peice from the specified location to the given location
	 * 
	 * @param from The location to move the piece from
	 * @param to The location to move the peice to
	 */
	public void movePiece(HantoCoordinateImpl from, HantoCoordinateImpl to) {
		pieces.put(to, removePeiceFromBoard(from));
	}
	
	/** Checks if moving the specified piece is valid
	 * 
	 * @param piece The piece to add
	 * @param to The coordinate to move the piece to
	 * @return True if it is a valid move
	 */
	public boolean isValidMove(HantoPieceImpl piece, HantoCoordinateImpl to){
		if (pieces.isEmpty()) { //if the board is empty only valid move is 0,0
			return to.equals(new HantoCoordinateImpl(0, 0));	
		}
		if (getPieceAt(to) != null) {
			return false; //if there is a piece on the spot it is invalid
		}
		return hasAdjacentPiece(to);
		
	}
	
	/** Determines if the given coordinate has an adjacent piece
	 * 
	 * @param coord The coordinate to test
	 * @return True if there is an adjacent piece, false otherwise
	 */
	public boolean hasAdjacentPiece(HantoCoordinateImpl coord) {		
	
		List<HantoCoordinateImpl> adjacentCoordinates = coord.Neighbors();		
		for (HantoCoordinateImpl other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				return true;
			}
		}
		return false;
		
	}
	
	/** Get a list of pieces adjacent to a coordinate
	 * @param coord the coordinate of the desired piece
	 * @return the pieces that are adjacent to the coordinate
	 */
	public List<HantoPieceImpl> getAdjacentPieces(HantoCoordinateImpl coord) {
		List<HantoPieceImpl> adjacentPieces = new ArrayList<HantoPieceImpl>();
		List<HantoCoordinateImpl> adjacentCoordinates = coord.Neighbors();
		for (HantoCoordinateImpl other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				adjacentPieces.add(getPieceAt(other));
			}
		}
		return adjacentPieces;
	}
	
	/** Gets a list of locations adjacent to the given coordinate with pieces
	 * 
	 * @param coord The coord of the spot to check
	 * @return The list of adjacent coords with pieces
	 */
	public List<HantoCoordinateImpl> getAdjacentLocationsWithPieces(HantoCoordinateImpl coord) {
		List<HantoCoordinateImpl> adjacentCoords = new ArrayList<HantoCoordinateImpl>();
		List<HantoCoordinateImpl> adjacentCoordinates = coord.Neighbors();
		for (HantoCoordinateImpl other : adjacentCoordinates) {
			if (getPieceAt(other) != null) {
				adjacentCoords.add(other);
			}
		}
		return adjacentCoords;
	}
	
	/** Returns the number of pieces currently on the board
	 * 
	 * @return The number of pieces on the board
	 */
	public int getPieceCount() {
		return pieces.size();
	}
	
	/** Returns the list of pieces that belong to the player of the given color on the board
	 * 
	 * @param color The color of the player
	 * @return The list of pieces belonging to the specified player
	 */
	public List<HantoCoordinateImpl> getPiecesForPlayer(HantoPlayerColor color) {
		List<HantoCoordinateImpl> playerPieces = new ArrayList<HantoCoordinateImpl>();
		for(HantoCoordinateImpl coord: pieces.keySet()) {
			if(pieces.get(coord).getColor() == color) {
				playerPieces.add(coord);
			}
		}
		return playerPieces;
	}
	
	/** Returns a string containing a printable copy of the board
	 * 
	 * @return The printabl copy of the board
	 */
	public String getPrintableBoard() {
		
		StringBuilder sb = new StringBuilder();
		for(Entry<HantoCoordinateImpl, HantoPieceImpl> hc : pieces.entrySet())
		{
			HantoCoordinateImpl coord = hc.getKey();
			HantoPiece piece = hc.getValue();
			
			sb.append(piece.getColor().toString());
			sb.append(" : ");
			sb.append(piece.getType().toString());
			sb.append(" @ ");
			sb.append(coord.toString());
			sb.append("\n");
			
		}
		return sb.toString();
	}
	
	/** Copies this board
	 * 	@return a copy of this board
	 */
	public GameBoard copy() {
		GameBoard board = new GameBoard();
		board.pieces = new HashMap<HantoCoordinateImpl, HantoPieceImpl>(pieces);
		return board;
	}
	
}
