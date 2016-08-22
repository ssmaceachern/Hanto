/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.beta;

import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoInventory;
import hanto.studentssmaceachern.common.piecefactory.BetaPieceFactory;

/** Beta Version of Hanto Game
 * 
 * @author Sean
 *
 */
public class BetaHantoGame extends GameState {
	
	/** Creates a new Beta Hanto Game
	 * 
	 * @param firstPlayer The color of the player to go first
	 */
	public BetaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}	
	/** Initialize the settings for this hanto game
	 * 
	 */
	private void init() {
		MaxTurns = 6;	
		pieceFactory = new BetaPieceFactory();
	}
	
	/** Defines the starting inventory for this game
	 * 
	 * @return The starting inventory
	 */	
	protected HantoInventory getStartingInventory() {
		HantoInventory startingPieces = new HantoInventory(1, 5, 0, 0);
		return startingPieces;
	}

}
