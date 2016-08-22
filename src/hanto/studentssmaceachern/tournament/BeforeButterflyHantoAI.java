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
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.validator.PlaceAdjacentPieceValidator;
import hanto.studentssmaceachern.common.validator.PlacePieceValidator;
import hanto.studentssmaceachern.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

/** The Hanto AI for playing before our butterfly has been placed, and after our first move has been made
 * 
 * @author Sean
 *
 */

public class BeforeButterflyHantoAI extends HantoAIImpl {
	
	/** Creates a new Hanto AI for early game
	 * 
	 * @param game The hanto game currently being played
	 * @param myColor The piece color of the player
	 * @param turnCount The current turn count
	 */
	public BeforeButterflyHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor,
			int turnCount) {
		super(game, myColor, turnCount);
	}

	@Override
	public HantoAIResult getNextMove() {
		HantoPieceType piece = HantoPieceType.CRAB;
		HantoAI nextAI = this;
		turnCount++;
		if(turnCount == 4) {
			piece = HantoPieceType.BUTTERFLY;
			nextAI = new AfterButterflyHantoAI(game, myColor, turnCount);
		}
		HantoMoveRecord nextMove = new HantoMoveRecord(piece, null, getBestPlacementCoordinate());
		return new HantoAIResult(nextAI, nextMove);
	}
	
	/** Get the furest coordinate from (0, 0) where we can place a piece
	 * @param board the game board
	 * @param myColor my player color
	 * @return the coordinate
	 */
	private HantoCoordinateImpl getBestPlacementCoordinate() {
		List<HantoCoordinateImpl> myPieceLocations = board.getPiecesForPlayer(myColor);
		HantoCoordinateImpl furthestCoord = null;
		int furthestDistance = 0;
		PlacePieceValidator placementValidator = PlaceAdjacentPieceValidator.getInstance();
		for(HantoCoordinateImpl coord: myPieceLocations) {
			List<HantoCoordinateImpl> unoccupiedCoords = coord.Neighbors();
			unoccupiedCoords.removeAll(board.getAdjacentLocationsWithPieces(coord));
			for(HantoCoordinateImpl c: unoccupiedCoords) {
				if(c.distFromCoordinate(new HantoCoordinateImpl(0, 0)) > furthestDistance &&
						placementValidator.isPlacementValid(board, new HantoPieceImpl(myColor, HantoPieceType.CRAB), c)) {
					furthestCoord = c;
					furthestDistance = c.distFromCoordinate(new HantoCoordinateImpl(0, 0));
				}
			}
		}
		return furthestCoord;
	}

}
