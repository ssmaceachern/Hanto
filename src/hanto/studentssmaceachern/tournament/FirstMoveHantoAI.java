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
import hanto.studentssmaceachern.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoMoveRecord;

import java.util.List;

/** The Hanto AI to determine what our first move will be
 * 
 * @author Sean
 *
 */
public class FirstMoveHantoAI extends HantoAIImpl {
	
	/** Creates a new First Move Hanto AI
	 * 
	 * @param game The game to use to play Hanto
	 * @param myColor The color of the player's pieces
	 */
	public FirstMoveHantoAI(EpsilonHantoGame game, HantoPlayerColor myColor) {
		super(game, myColor, 0);
	}
	
	/** Makes a move on the given game
	 * 
	 * @param game The game to make the move on
	 * @param myColor The color that we are
	 * @return The result of the move
	 */
	public HantoAIResult getNextMove() {
		HantoMoveRecord move;
		
		//check if we are moving first
		if (game.getBoard().getPieceCount() == 0) {
			//we are moving first, place crab on first spot
			move = new HantoMoveRecord(HantoPieceType.CRAB, null, new HantoCoordinateImpl(0, 0));
		}
		else {
			//we are moving second
			move = new HantoMoveRecord(HantoPieceType.CRAB, null, getRandomNeighborCoord(
					new HantoCoordinateImpl(0, 0)));			
		}			
		return new HantoAIResult(new BeforeButterflyHantoAI(game, myColor, turnCount + 1), move);
	}
	
	/** Gets a random coord adjacent to the given coordinate
	 * 
	 * @param coord The coordinate to get the adjacent coord of
	 * @return  The random coord
	 */
	private HantoCoordinateImpl getRandomNeighborCoord(HantoCoordinateImpl coord) {
		List<HantoCoordinateImpl> adjCoords = coord.Neighbors();
		int num = (int)(Math.random() * adjCoords.size() - 1);
		return adjCoords.get(num);
	}

	
	
}
