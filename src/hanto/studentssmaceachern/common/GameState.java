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

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.exceptions.GameNotActiveException;
import hanto.studentssmaceachern.common.exceptions.MoveException;
import hanto.studentssmaceachern.common.exceptions.MoveLocationException;
import hanto.studentssmaceachern.common.exceptions.PieceTypeException;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Sean
 *
 */
public abstract class GameState implements HantoGame{

	protected GameBoard board;
	protected Map<HantoPlayerColor, HantoPlayer> players;
	
	protected int turnCount;
	
	protected final HantoPlayerColor firstPlayerColor;
	protected HantoPlayer currentPlayer;
	
	protected int MaxTurns;
	protected boolean resigned;
	
	protected HantoPieceFactory pieceFactory;
	
	/** Creates a new abstract hanto
	 * 
	 * 	@param firstPlayerColor The color of the player who will go first
	 */
	protected GameState(HantoPlayerColor firstPlayerColor) {
		this.firstPlayerColor = firstPlayerColor;
		board = new GameBoard();
		turnCount = 1;
		MaxTurns = 0;
		resigned = false;
		
		//initialize the players
		initializePlayers();
	}
	
	/** Initializes the Hanto players
	 * 
	 */
	private void initializePlayers() {
		players = new HashMap<HantoPlayerColor, HantoPlayer>();
		
		for (HantoPlayerColor color : HantoPlayerColor.values()) {
			HantoPlayer player = new HantoPlayer(color, getStartingInventory());
			players.put(color, player);
		}
		
		currentPlayer = players.get(firstPlayerColor);
	}
	
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {		
		if (to == null) {
			throw new MoveException("Move destination cannot be null");
		}
		HantoCoordinateImpl toCoord = ChangeExistingCoord(to);
		HantoCoordinateImpl fromCoord = null;
		if (from != null) {
			 fromCoord = ChangeExistingCoord(from);
		}
		return makeMove(pieceFactory.makePiece(currentPlayer.getColor(), pieceType), 
				fromCoord, toCoord);	
	}
	
	/**
	 * Override of the Make Move from HantoGame
	 * @param piece
	 * @param from
	 * @param to
	 * @return MoveResult
	 * @throws HantoException
	 */
	protected MoveResult makeMove(HantoPieceImpl piece, HantoCoordinateImpl from,
			HantoCoordinateImpl to) throws HantoException {
		if (isGameOver()) {
			throw new GameNotActiveException();
		}
		if (from != null) {
			movePiece(piece, from, to);
		}
		else {
			placePiece(piece, to);
			
		}	
		finalizeMove();
		return getMoveResult();		
	}
	

	/**
	 * Override of the Make Move from HantoGame
	 * @param piece
	 * @param from
	 * @param to
	 * @throws HantoException
	 */
	protected void movePiece(HantoPieceImpl piece, HantoCoordinateImpl from,
			HantoCoordinateImpl to) throws HantoException {
	
		if (piece.isMoveValid(board, from, to)) {
			board.movePiece(from, to);
			currentPlayer.movePiece(piece.getType(), from, to);
		}
		else {
			throw new PieceTypeException(piece.getType(), "Invalid piece movement");
		}
	}
	
	/**
	 * Calls upon the piece factory to place a piece down
	 * @param piece - The piece to place
	 * @param to - Desired location to place piece
	 * @throws HantoException
	 */
	protected void placePiece(HantoPieceImpl piece, HantoCoordinateImpl to) throws HantoException {
		if (canPlayPieceType(piece.getType()) && piece.isPlacementValid(board, to)) {
			board.addPieceToBoard(pieceFactory.makePiece(currentPlayer.getColor(), piece.getType()), to);		
			currentPlayer.placePiece(piece.getType(), to);
		}
		else {
			throw new MoveLocationException(to, "Invalid piece placement");
		}
	}
	
	/**
	 * Converts an existing HantoCoordinate into a new HantoCoordinateImpl
	 * @param coord
	 * @return Changed Coordinate
	 */
	protected HantoCoordinateImpl ChangeExistingCoord(HantoCoordinate coord) {
		return new HantoCoordinateImpl(coord);
	}
	
	/**
	 * @return a printable representation of the board.
	 */	
	public String getPrintableBoard() {
		return board.getPrintableBoard();
	}
	

	public HantoPiece getPieceAt(HantoCoordinate where) {
		return getPieceAt(ChangeExistingCoord(where));
	}
	
	/**
	 * Gets a specific piece at the specified location on the board
	 * @param where
	 * @return Piece
	 */
	protected HantoPiece getPieceAt(HantoCoordinateImpl where) {
		return board.getPieceAt(where);
	}
	
	/**
	 * Setter for Player Color
	 * @param color
	 */
	public void setCurrentPlayerColor(HantoPlayerColor color) {
		currentPlayer = players.get(color);
	}
	
	/**
	 * Method for alternating existing players
	 * @return The current HantoPlayer
	 */
	protected HantoPlayer updateHantoPlayer() {
		currentPlayer = players.get(opposingPlayerColor(currentPlayer.getColor()));
		return currentPlayer;
	}
	
	/** Sets the hanto board to use
	 * 
	 * @param board The board to use
	 */
	public void setHantoBoard(GameBoard board) {
		this.board = board;
	}
	
	/** Sets the current turn count where 1 is the first turn. And one turn is when both players have moved.
	 * 
	 * @param turnCount The new turn count
	 */
	public void setTurnCount(int turnCount) {
		this.turnCount = turnCount;
	}
	
	/** Returns the Hanto Player of the given color
	 * 
	 * @param color The color of the player to fetch
	 * @return The Hanto Player
	 */
	public HantoPlayer getHantoPlayer(HantoPlayerColor color) {
		return players.get(color);
	}
	
	/** Determines if the given piece type can be placed on this turn
	 * 
	 * @param type The type of the piece
	 * @return True if the piece is valid, false otherwise
	 */
	protected boolean canPlayPieceType(HantoPieceType type) {		
		//check if is greater than the 4th turn and the player has not, or is not, placing thier butterfly
		if((turnCount) > 3 &&
				!currentPlayer.hasPlacedButterfly() &&
				type != HantoPieceType.BUTTERFLY) {
			return false;
		}
		//if they player has one of the pieces, then they can place it
		return currentPlayer.getPieceInventory().hasPiece(type);
	}
	
	/** Returns the opposite color of the given color
	 * 
	 * @param color The color to get the opposite of
	 * @return The opposite color
	 */
	public static HantoPlayerColor opposingPlayerColor(HantoPlayerColor color) {
		if(color == HantoPlayerColor.BLUE) {
			return HantoPlayerColor.RED;
		}
		else {
			return HantoPlayerColor.BLUE;
		}
	}
	
	/** Gest the move result of the last move (aka check win condition)
	 * 
	 * @return The movie result of the last move 
	 */
	protected MoveResult getMoveResult() {
		boolean blueWon = hasPlayerWon(HantoPlayerColor.BLUE);
		boolean redWon = hasPlayerWon(HantoPlayerColor.RED);
		if(resigned) {
			if(currentPlayer.getColor() == HantoPlayerColor.BLUE) {
				return MoveResult.RED_WINS;
			}
			else {
				return MoveResult.BLUE_WINS;
			}
		}
		if(redWon && blueWon) {
			return MoveResult.DRAW;
		}
		else if(redWon) {
			return MoveResult.RED_WINS;
		}
		else if(blueWon) {
			return MoveResult.BLUE_WINS;
		}
		else if (MaxTurns > 0 && turnCount > MaxTurns) {
			return MoveResult.DRAW;
		}
		else {
			return MoveResult.OK;
		}
	}
	
	/** Determines whether the player of the given color has won the game
	 * 
	 * @param color The color of the player
	 * @return True/False if the player won/loss
	 */
	private boolean hasPlayerWon(HantoPlayerColor color) {		
		HantoPlayer otherPlayer = players.get(opposingPlayerColor(color));
		if (otherPlayer.hasPlacedButterfly()) {
			HantoCoordinateImpl butterflyLocation = otherPlayer.getButterflyLocation();
			if(board.getAdjacentPieces(butterflyLocation).size() >= 6) {
				return true;
			}			
		}
		return false;
	}
	
	/** Returns if the game is over or not
	 * 
	 * @return True if the game is over, false otherwise
	 */
	protected boolean isGameOver() {
		return (getMoveResult() != MoveResult.OK);
	}
	
	/** Returns the starting pieces for a player
	 * 
	 * @return The starting inventory for a player
	 */
	protected abstract HantoInventory getStartingInventory();
	
	/** Updates the turn count + player at the end of a move
	 * 
	 */
	protected void finalizeMove() {
		if(currentPlayer.getColor() != firstPlayerColor) {
			turnCount ++;
		}
		updateHantoPlayer();
	}
	
	/** Returns the board that the game is currently using
	 * 
	 * @return The board
	 */
	public GameBoard getBoard() {
		return board;
	}	
	
	/** Returns the piece factory this Hanto Game uses for creating pieces
	 * 
	 * @return the piece factory
	 */
	public HantoPieceFactory getPieceFactory() {
		return pieceFactory;
	}
	
	
}
