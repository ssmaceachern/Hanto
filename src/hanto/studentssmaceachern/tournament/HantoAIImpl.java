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

import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.epsilon.EpsilonHantoGame;

/** The Base Hanto AI, contains common functionality for each AI
 * 
 * @author Sean
 *
 */
public abstract class HantoAIImpl implements HantoAI {

	protected int turnCount;
	protected final EpsilonHantoGame game;
	protected final HantoPlayerColor myColor;
	protected GameBoard board;
	
	/** Creates a new Base Hanto AI with the given game, color, and turn count
	 * 
	 * @param game The game to use to play Hanto
	 * @param myColor The color of the player's pieces
	 * @param turnCount The current turn count
	 */
	protected HantoAIImpl(EpsilonHantoGame game, HantoPlayerColor myColor, int turnCount) {
		this.game = game;
		this.myColor = myColor;
		this.turnCount = turnCount;
		board = game.getBoard();
	}
	
}
