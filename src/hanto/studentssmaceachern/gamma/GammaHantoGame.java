/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/


package hanto.studentssmaceachern.gamma;

import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoInventory;
import hanto.studentssmaceachern.common.piecefactory.GammaPieceFactory;


/** Gamma version of Hanto
 * @author Sean
 *
 */
public class GammaHantoGame extends GameState {
	
	/** Creates a new Gamma Hanto Game
	 * 
	 * @param firstPlayer The player that will move first
	 */
	public GammaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}
	
	/** Initialize Gamma */
	private void init() {		
		MaxTurns = 20;
		pieceFactory = new GammaPieceFactory();
	}
	
	/** Defines the starting inventory for this game
	 * 
	 * 	@return The starting inventory for this game
	 */
	
	protected HantoInventory getStartingInventory() {		
		//1 Butterfly, 5 Sparrows, 0 Crabs, and 0 Horses
		HantoInventory startingPieces = new HantoInventory(1, 5, 0, 0);
		return startingPieces;
	}

}
