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

import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.epsilon.EpsilonHantoGame;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/** Player for Hanto Epsilon Tournament
 * 
 * @author Sean
 *
 */
public class HantoPlayer implements HantoGamePlayer {

	/** The hanto game to use to play */
	protected EpsilonHantoGame game;
	
	/** The current Hanto AI to use for determining our next move */
	protected HantoAI hantoAI;
	
	
	/**
	 * This method must be called first after the player is constructed. It tells the
	 * player what version of the game to play, what the player's color is, and whether the
	 * player moves first.
	 * @param version the specific Hanto game to play
	 * @param myColor the color for this player
	 * @param doIMoveFirst true if the player will make the first move
	 */
	public void startGame(HantoGameID version, HantoPlayerColor myColor, boolean doIMoveFirst) {
		HantoPlayerColor firstColor = doIMoveFirst ? myColor : GameState.opposingPlayerColor(myColor);
		game = new EpsilonHantoGame(firstColor);
		hantoAI = new FirstMoveHantoAI(game, myColor);
	}
	
	/**
	 * Make the player's next move.
	 * @param opponentsMove this is the result of the opponent's last move, in response
	 * 	to your last move. This will be null if you are making the first move of the game.
	 * @return your move
	 */
	public HantoMoveRecord makeMove(HantoMoveRecord opponentsMove) {
		try {
			if (opponentsMove != null) {		
				makeHantoMove(opponentsMove);
			}
			HantoAIResult res = hantoAI.getNextMove();
			hantoAI = res.getAi();
			makeHantoMove(res.getMove());			
			return res.getMove();
		}
		catch (Exception e) {
			return new HantoMoveRecord(null, null, null); // something went wrong, just resign. *tear
		}	
	}
	
	/** Makes the specified move
	 * 
	 * @param move The move to make
	 * @throws HantoException If the move was invalid
	 */
	private void makeHantoMove(HantoMoveRecord move) throws HantoException {
		game.makeMove(move.getPiece(), move.getFrom(), move.getTo());
	}		
	
}
