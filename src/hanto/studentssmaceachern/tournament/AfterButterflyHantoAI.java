/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.tournament;

import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.HantoPlayer;
import hanto.studentssmaceachern.common.validator.PlaceAdjacentPieceValidator;
import hanto.studentssmaceachern.common.validator.PlacePieceValidator;
import hanto.studentssmaceachern.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;
import java.util.Map;

/** The Hanto AI for playing after the butterfly has been placed
 * 
 * @author Sean, James Megin
 *
 */

public class AfterButterflyHantoAI extends HantoAIImpl {
	
	/** Creates a new Hanto AI for playing the game after the butterfly placement
	 * 
	 * @param game The game to use for playing Hanto
	 * @param myColor The color of the player's pieces
	 * @param turnCount The current turn count
	 */
	
	public AfterButterflyHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor, int turnCount) {
		super(game, myColor, turnCount);
	}
	
	@Override
	public HantoAIResult getNextMove() {
		turnCount++;
		HantoMoveRecord move;
		HantoAI nextAI = this;
		
		//check if we need to resign
		if (!hasValidMove()) {
			//no valid moves, time to resign
			move = createResignMove();
		}
		else if (shouldPlacePiece()) {
			move = placePiece();
		}
		else {
			move = movePiece();
		}	
		
		return new HantoAIResult(nextAI, move);
	}
	
	/** Determines if we have any valid moves left
	 * 
	 * @param game The current game
	 * @param myColor Our color
	 * @return True if we have valid moves, false otherwise
	 */
	private boolean hasValidMove() {
		return game.getHantoPlayer(myColor).hasLegalMove(board);
	}
	
	/** Determines if we will place a piece
	 * 
	 * @param game The hanto game
	 * @param myColor Our color
	 * @return True if we should place a piece, False if we should move a piece
	 */
	private boolean shouldPlacePiece() {
		HantoPlayer player = game.getHantoPlayer(myColor);
		//if we can't place a piece then we have to move
		if (!player.canPlacePiece(board)) {
			return false;
		}
		//if we can't move a piece then we have to play
		if (!player.canMovePiece(board)) {
			return true;
		}		
		//we can both place + move, 50% chance of either
		return Math.random() < 0.5;		
	}
	
	/** Places a piece
	 * 
	 * @param game
	 * @param myColor
	 * @return
	 */
	private HantoMoveRecord placePiece() {
		HantoCoordinateImpl to = getBestPlacementCoordinate();
		HantoPieceType type = getRandomPiece();
		return new HantoMoveRecord(type, null, to);		
	}
	
	/** Get the coordinate closest to the opponents butterfly on which we can place a piece
	 * @param board the game board
	 * @param myColor my player color
	 * @return the coordinate
	 */
	private HantoCoordinateImpl getBestPlacementCoordinate() {
		HantoCoordinateImpl opponentButterflyLocation = game.getHantoPlayer(GameState.opposingPlayerColor(myColor)).getButterflyLocation();
		List<HantoCoordinateImpl> myPieceLocations = board.getPiecesForPlayer(myColor);
		HantoCoordinateImpl shortestCoord = null;
		int shortestDistance = Integer.MAX_VALUE;
		PlacePieceValidator placementValidator = PlaceAdjacentPieceValidator.getInstance();
		for(HantoCoordinateImpl coord: myPieceLocations) {
			List<HantoCoordinateImpl> unoccupiedCoords = coord.Neighbors();
			unoccupiedCoords.removeAll(board.getAdjacentLocationsWithPieces(coord));
			for(HantoCoordinateImpl c: unoccupiedCoords) {
				if(c.distFromCoordinate(opponentButterflyLocation) < shortestDistance &&
						placementValidator.isPlacementValid(board, new HantoPieceImpl(myColor, HantoPieceType.CRAB), c)) {
					shortestCoord = c;
					shortestDistance = c.distFromCoordinate(new HantoCoordinateImpl(0, 0));
				}
			}
		}
		return shortestCoord;
	}
	
	/** Selects a random piece from the players remaining pieces
	 * 
	 * @param game The game
	 * @param color Our color
	 * @return The piece selected
	 */
	private HantoPieceType getRandomPiece() {
		Map<HantoPieceType, Integer> piecesRemaining = game.getHantoPlayer(myColor).getPieceInventory().getPieces();
	
		double num = Math.random() * countPiecesRemaining(piecesRemaining);
		
		int cur = 0;
		//weights each piece, and selects one randomly
		for (HantoPieceType type : piecesRemaining.keySet()) {
			cur += piecesRemaining.get(type);
			if (num <= cur) {
				return type;
			}
		}	
		
		return HantoPieceType.CRAB;
	}
	
	/** Counts the number of pieces reamining in the given inventory
	 * 
	 * @param inventory The inventory of pieces to count
	 * @return The number of pieces remaining
	 */
	private int countPiecesRemaining(Map<HantoPieceType, Integer> inventory) {
		int rem = 0;
		for (HantoPieceType type : inventory.keySet()) {
			rem += inventory.get(type);
		}
		return rem;
	}
	
	/** Moves a piece
	 * 
	 * @param game
	 * @param myColor
	 * @return
	 */
	private HantoMoveRecord movePiece() {
		if (!moveOffensive()) {
			//defensive moving
			//check if we can move butterfly, if so, we move it anywhere.
			HantoCoordinateImpl myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
			if(canMoveOwnButterfly()) {
				HantoCoordinateImpl destination = getButterflyMoveCoord();
				return new HantoMoveRecord(HantoPieceType.BUTTERFLY, myButterflyLocation, destination);
			}

		}	
		
		return movePieceClosestToButterfly();
	}
	
	/** Finds the piece that we can move closest to the opponents butterfly
	 * 
	 * @param game The current game
	 * @param myColor Our color
	 * @return The move record containing the piece + desitnation closest to opponenets butterfly
	 */
	private HantoMoveRecord movePieceClosestToButterfly() {
		List<HantoCoordinateImpl> ourPiecesCoords = board.getPiecesForPlayer(myColor);
		
		CoordinateMoveDistance bestMove = null;
		
		for (HantoCoordinateImpl pieceCoord : ourPiecesCoords) {
			HantoPieceImpl piece = board.getPieceAt(pieceCoord);
			for (HantoCoordinateImpl destCoord : piece.getValidMovementCoordinates(board, pieceCoord)) {
				CoordinateMoveDistance nextMove = new CoordinateMoveDistance(destCoord, pieceCoord);
				bestMove = compareMovements(bestMove, nextMove);
			}
		}
		
		if (bestMove != null) {
			HantoPieceType type = board.getPieceAt(bestMove.getCurrentPosition()).getType();
			return new HantoMoveRecord(type, bestMove.getCurrentPosition(), bestMove.getDestination());
		}
		else {
			return createResignMove();
		}
	}
	
	/** Compares two moves to see which one is the best
	 * @param moveOne one of the moves
	 * @param moveTwo the other move
	 * @return the best move
	 */
	private CoordinateMoveDistance compareMovements(CoordinateMoveDistance moveOne, CoordinateMoveDistance moveTwo) {
		if(moveOne == null) {
			return moveTwo;
		}
		if(moveTwo == null) {
			return moveOne;
		}
		CoordinateMoveDistance bestMove = moveOne;
		// check if either are adjacent to the opponents butterfly, if so, return the other one
		HantoCoordinateImpl opponentButterflyCoord = game.getHantoPlayer(GameState.opposingPlayerColor(myColor)).getButterflyLocation();
		int moveOneDistance = moveOne.getDestination().distFromCoordinate(opponentButterflyCoord);
		int moveTwoDistance = moveTwo.getDestination().distFromCoordinate(opponentButterflyCoord);
		HantoCoordinateImpl myButterflyCoord = game.getHantoPlayer(myColor).getButterflyLocation();
		if(moveOne.getCurrentPosition().isAdjacentTo(opponentButterflyCoord) &&
				!moveTwo.getCurrentPosition().isAdjacentTo(opponentButterflyCoord)) {
			bestMove = moveTwo;
		}
		else if(!moveOne.getCurrentPosition().isAdjacentTo(opponentButterflyCoord) &&
				moveTwo.getCurrentPosition().isAdjacentTo(opponentButterflyCoord)) {
			bestMove = moveOne;
		}
		// check to see if one will be placed next to the opponents butterfly, if so, choose that one
		else if(moveOneDistance == 1 && moveTwoDistance > 1) {
			bestMove = moveOne;
		}
		else if(moveTwoDistance == 1 && moveOneDistance > 1) {
			bestMove = moveTwo;
		}
		// check to see if one is next to our butterfly, if so, choose that one
		else if(moveOne.getCurrentPosition().isAdjacentTo(myButterflyCoord) &&
				!moveTwo.getCurrentPosition().isAdjacentTo(myButterflyCoord)) {
			bestMove = moveTwo;
		}
		else if(!moveOne.getCurrentPosition().isAdjacentTo(myButterflyCoord) &&
				moveTwo.getCurrentPosition().isAdjacentTo(myButterflyCoord)) {
			bestMove = moveOne;
		}
		// if we get here, pick the one that will get us closest to their butterfly
		else if(moveOneDistance > moveTwoDistance) {
			bestMove = moveOne;
		}
		else {
			bestMove = moveTwo;
		}
		return bestMove;
	}
	
	/** See if we can move our own butterfly
	 * @param game the game we are playing
	 * @param myColor our color
	 * @return true if we can legally move our butterfly, false otherwise
	 */
	private boolean canMoveOwnButterfly() {
		HantoCoordinateImpl myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		if(board.getPieceAt(myButterflyLocation).hasLegalMove(board, myButterflyLocation)) {
			return true;
		}
		return false;
	}
	
	/** Get the best move for our butterfly that will result with the least number of adjacent pieces
	 * @param game the game we are playing
	 * @param myColor our color
	 * @return the coordinate to move to
	 */
	private HantoCoordinateImpl getButterflyMoveCoord() {
		HantoCoordinateImpl myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		HantoPieceImpl myButterfly = board.getPieceAt(myButterflyLocation);
		HantoCoordinateImpl bestCoord = null;
		int minAdjCount = Integer.MAX_VALUE;
		for(HantoCoordinateImpl coord: myButterfly.getValidMovementCoordinates(board, myButterflyLocation)) {			
			if(board.getAdjacentPieces(coord).size() < minAdjCount) {
				bestCoord = coord;
				minAdjCount = board.getAdjacentPieces(coord).size();
			}

		}
		return bestCoord;
	}
	
	/**
	 * Offensive move
	 * @return
	 */
	private boolean moveOffensive() {
		HantoCoordinateImpl myButterflyLocation = game.getHantoPlayer(myColor).getButterflyLocation();
		HantoCoordinateImpl opponentButterflyLocation = game.getHantoPlayer(GameState.opposingPlayerColor(myColor)).getButterflyLocation();
		
		int myButterflyAdjCount = board.getAdjacentLocationsWithPieces(myButterflyLocation).size();
		int opponentButterflyAdjCount = board.getAdjacentLocationsWithPieces(opponentButterflyLocation).size();	
	
		double chance = (opponentButterflyAdjCount) / (double) (myButterflyAdjCount + opponentButterflyAdjCount);		
		
		return Math.random() <= chance;
	}
	
	/** Creates the hanto move for resigning
	 * 
	 * @return The hanto move for resigning
	 */
	private HantoMoveRecord createResignMove() {
		return new HantoMoveRecord(null, null, null);
	}

}
