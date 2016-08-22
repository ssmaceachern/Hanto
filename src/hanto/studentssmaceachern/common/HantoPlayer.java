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

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.validator.PlaceAdjacentPieceValidator;

import java.util.List;


/** Class to represent a HantoPlayer
 * 
 * @author Sean, James Megin
 *
 */
public class HantoPlayer {

	/** The color of this player */
	private HantoPlayerColor color;
	
	/** The location of the player's butterfly */
	private HantoCoordinateImpl butterflyLocation;
	
	/** The pieces that the player has in thier inventory */
	private HantoInventory Inventory;
	
	/** Creates a new Hanto Player with the given color and starting inventory
	 * 
	 * @param color - The color of this player
	 * @param startingInventory - The player's starting inventory
	 */
	public HantoPlayer(HantoPlayerColor color, HantoInventory startingInventory) {
		this.color = color;
		Inventory = new HantoInventory(0, 0, 0, 0);
		initInventory(startingInventory);
	}
	
	private void initInventory(HantoInventory startingInvetory) {
		for(HantoPieceType type : HantoPieceType.values()) {
			Inventory.getPieces().put(type, 0);
		}
		Inventory.getPieces().putAll(startingInvetory.getPieces());
	}
	
	
	/** Notifies the player that the piece has been placed on the board
	 * 
	 * @param pieceType The type of piece that has been placed
	 * @param location The location that the piece was placed
	 */
	
	public void placePiece(HantoPieceType pieceType, HantoCoordinateImpl location) {
		Inventory.put(pieceType, Inventory.get(pieceType) - 1);
		if (pieceType == HantoPieceType.BUTTERFLY) {
			butterflyLocation = location;
		}
	}
	
	/** Notifies the player that a piece of the given type has been moved
	 * 
	 * @param pieceType The type of the piece that has been moved
	 * @param from The starting position of the piece
	 * @param to The destination position of the piece
	 */

	public void movePiece(HantoPieceType pieceType, HantoCoordinateImpl from, HantoCoordinateImpl to) {
		if (pieceType == HantoPieceType.BUTTERFLY) {
			butterflyLocation = to;
		}
	}
	
	/** Checks to see if the player has a legal move
	 * @param board the game board
	 * @return True if there is a legal move for the player, false otherwise
	 */
	public boolean hasLegalMove(GameBoard board) {
		// check if have pieces, if so can they be places
		// check if pieces on board have legal moves
		return canPlacePiece(board) || canMovePiece(board);
	}
	
	/** Determines if a player can place any of thier pieces
	 * 
	 * @param board The board to place the pieces on
	 * @return True if the player can place a piece, false otherwise
	 */
	
	public boolean canPlacePiece(GameBoard board) {
		int totalPieceCount = 0;
		for(HantoPieceType type: Inventory.getPieces().keySet()) {
			totalPieceCount += Inventory.get(type);
		}
		if(totalPieceCount <= 0) {
			return false;
		}
		PlaceAdjacentPieceValidator apv = PlaceAdjacentPieceValidator.getInstance();
		HantoPieceImpl dummyPiece = new HantoPieceImpl(color, HantoPieceType.CRAB);
		List<HantoCoordinateImpl> pieceCoords = board.getPiecesForPlayer(color);
		if (pieceCoords.size() == 0) {
			return true; //player has not yet placed a piece, no way he cannot place one
		}
		for(HantoCoordinateImpl coord: pieceCoords) {
			for(HantoCoordinateImpl c: coord.Neighbors()) {
				if(apv.isPlacementValid(board, dummyPiece, c)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/** Determines if the player can move a piece on the given board
	 * 
	 * @param board The board
	 * @return True if the player can move any of thier pieces
	 */
	
	public boolean canMovePiece(GameBoard board) {
		List<HantoCoordinateImpl> pieceCoords = board.getPiecesForPlayer(color);
		for(HantoCoordinateImpl coord: pieceCoords) {
			if(board.getPieceAt(coord).hasLegalMove(board, coord)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	/** Determines if the player has placed their butterfly yet
	 * 
	 * @return True if the player's butterfly has been placed, false otherwise
	 */
	public boolean hasPlacedButterfly() {
		return butterflyLocation != null; //if butterfly is not null, then it has been placed
	}
	
	/** Gets the color of this hanto player
	 * 
	 * @return The color of this hanto player
	 */
	public HantoPlayerColor getColor() {
		return color;
	}
	
	/** Gets the location of this players butterfly
	 * 
	 * @return The location of this players butterfly
	 */
	public HantoCoordinateImpl getButterflyLocation() {
		return butterflyLocation;
	}
	
	/** Gets this players piece inventory
	 * 
	 * @return The players piece inventory
	 */
	public HantoInventory getPieceInventory() {
		return Inventory;
	}
}
