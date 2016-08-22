/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.delta;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoInventory;
import hanto.studentssmaceachern.common.piecefactory.DeltaHantoPieceFactory;

/** Delta version of Hanto
 * 
 * @author Sean
 *
 */
public class DeltaHantoGame extends GameState {
	
	/** Creates a new Delta Hanto Game
	 * 
	 * @param firstPlayer The player that will move first
	 */
	public DeltaHantoGame(HantoPlayerColor firstPlayer) {
		super(firstPlayer);
		init();
	}
	
	/** Initialize Delta */
	private void init() {			
		pieceFactory = new DeltaHantoPieceFactory();
	}
	
	/** Defines the starting inventory for this game
	 * 	
	 * @return The starting inventory for this game
	 */	
	protected HantoInventory getStartingInventory() {
		//1 Butterfly, 4 Sparrows, 4 Crabs, and 0 Horses
		HantoInventory startingPieces = new HantoInventory(1, 4, 4, 0);
		return startingPieces;
	}

	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		
		if(pieceType == null && from == null && to == null) {
			resigned = true;
			return getMoveResult();
		}
		
		HantoCoordinateImpl toCoord = ChangeExistingCoord(to);
		HantoCoordinateImpl fromCoord = null;
		if (from != null) {
			 fromCoord = ChangeExistingCoord(from);
		}
		return makeMove(pieceFactory.makePiece(currentPlayer.getColor(), pieceType), 
				fromCoord, toCoord);
		
	}
	

}
