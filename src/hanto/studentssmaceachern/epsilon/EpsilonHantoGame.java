/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.studentssmaceachern.epsilon;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoInventory;
import hanto.studentssmaceachern.common.piecefactory.EpsilonHantoPieceFactory;

/** Epsilon version of Hanto
 * 
 * @author Sean
 *
 */
public class EpsilonHantoGame extends GameState {

	/** Create a new epsilon hanto game
	 * @param firstPlayerColor the color of the player that goes first
	 */
	public EpsilonHantoGame(HantoPlayerColor firstPlayerColor) {
		super(firstPlayerColor);
		init();
	}
	
	/** Initialize Epsilon */
	private void init() {
		pieceFactory = new EpsilonHantoPieceFactory();
	}

	/** Defines the starting inventory for this game
	 * 	
	 * @return The starting inventory for this game
	 */	
	@Override
	protected HantoInventory getStartingInventory() {
		//1 Butterfly, 2 Sparrows, 6 Crabs, and 4 Horses
		HantoInventory startingPieces = new HantoInventory(1, 2, 6, 4);
		return startingPieces;
	}
	
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		//check if the player resigns
		if(pieceType == null && from == null && to == null) {
			if (currentPlayer.hasLegalMove(board)) {
				throw new HantoPrematureResignationException();
			}
			resigned = true;
			return getMoveResult();
		}
		HantoCoordinateImpl toCoord = ChangeExistingCoord(to);
		HantoCoordinateImpl fromCoord = null;
		if (from != null) {
			 fromCoord = ChangeExistingCoord(from);
		}
		return makeMove(pieceFactory.makePiece(currentPlayer.getColor(), pieceType), fromCoord, toCoord);
		
	}

}
