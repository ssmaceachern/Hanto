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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.validator.PlaceAdjacentPieceValidator;

/**
 * 
 * @author Sean
 *
 */
public class HantoInventory {

	HashMap<HantoPieceType, Integer> pieces;
	
	public Map<HantoPieceType, Integer> getPieces() {
		return pieces;
	}
	
	/**
	 * Constructor for building a HantoInventory
	 * @param Butterflies - Starting Butterflies
	 * @param Sparrows - Starting Sparrows
	 * @param Crabs - Starting Crabs
	 * @param Horses - Starting Horses
	 */
	public HantoInventory(int Butterflies, int Sparrows, int Crabs, int Horses) {
		pieces = new HashMap<HantoPieceType, Integer>();
		
		if(Butterflies > 0){
			pieces.put(HantoPieceType.BUTTERFLY, Butterflies);
		}
		
		if(Sparrows > 0){
			pieces.put(HantoPieceType.SPARROW, Sparrows);
		}
		
		if(Crabs > 0){
			pieces.put(HantoPieceType.CRAB, Crabs);
		}
		
		if(Horses > 0){
			pieces.put(HantoPieceType.HORSE, Horses);
		}
		
		
	}
	
	/**
	 * Checks whether or not a piece can be placed from the inventory onto the board
	 * @param color
	 * @param board
	 * @return true of able to place a piece
	 */
	public boolean canPlacePiece(HantoPlayerColor color, GameBoard board) {
		int piecesAvailable = 0;
		for(HantoPieceType type: pieces.keySet()) {
			piecesAvailable += pieces.get(type);
		}
		if(piecesAvailable <= 0) {
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
	
	/** Determines if the player has any pieces of the specified type remaining
	 * 
	 * @param pieceType The type of piece to check
	 * @return True if the player has atleast one of the given piece, false otherwise
	 */
	public boolean hasPiece(HantoPieceType pieceType) {
		return pieces.get(pieceType) > 0;
	}
	
	/** Determines if the player has any pieces remaining
	 * 
	 * @return True if the player has pieces remaining, false otherwise
	 */
	public boolean hasPiecesRemaining() {
		for (HantoPieceType type : pieces.keySet()) {
			if (pieces.get(type) > 0) {
				return true;
			}
		}
		return false;
	}

	public boolean isEmpty() {
		return pieces.isEmpty();
	}
	
	/**
	 * Function wrapper for the Pieces HashMap
	 * @param pieceType
	 * @return specified pieceType
	 */
	public int get(HantoPieceType pieceType) {
		return pieces.get(pieceType);
	}
	
	/**
	 * Function wrapper for the Pieces HashMap
	 * @param pieceType
	 * @param i
	 * @return value for valid put
	 */
	public Integer put(HantoPieceType pieceType, int i) {
		return pieces.put(pieceType, i);
	}
}
