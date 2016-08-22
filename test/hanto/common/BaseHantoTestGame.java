/**
 * 
 */
package hanto.common;

import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.GameBoard;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.common.HantoPieceFactory;
import hanto.studentssmaceachern.common.HantoPieceImpl;
import hanto.studentssmaceachern.common.HantoPlayer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Sean
 *
 */
public class BaseHantoTestGame implements HantoTestGame {
	
	/** The Hanto Game for this test game */
	protected GameState hantoGame;
	
	/** Creates a new BaseHantoTestGame with the given Hanto Gme implementation
	 * 
	 * @param hantoGame THe implementation of Hanto Game to use
	 */
	public BaseHantoTestGame(GameState hantoGame) {
		this.hantoGame = hantoGame;
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#makeMove(hanto.common.HantoPieceType, hanto.common.HantoCoordinate, hanto.common.HantoCoordinate)
	 */
	@Override
	public MoveResult makeMove(HantoPieceType pieceType, HantoCoordinate from,
			HantoCoordinate to) throws HantoException {
		return hantoGame.makeMove(pieceType, from, to);
	}

	/* (non-Javadoc)
	 * @see hanto.common.HantoGame#getPieceAt(hanto.common.HantoCoordinate)
	 */
	@Override
	public HantoPiece getPieceAt(HantoCoordinate where) {
		return hantoGame.getPieceAt(where); 
	}

	@Override
	public String getPrintableBoard() {
		return hantoGame.getPrintableBoard();
	}

	@Override
	public void initializeBoard(PieceLocationPair[] initialPieces) {
		
		Map<HantoCoordinateImpl, HantoPieceImpl> boardPieces = new HashMap<HantoCoordinateImpl, HantoPieceImpl>();
		Map<HantoPlayerColor, HantoPlayer> hantoPlayers = new HashMap<HantoPlayerColor, HantoPlayer>();
		HantoPieceFactory pieceFactory = hantoGame.getPieceFactory();
		
		// Put all of the hanto player colors into the map
		for (HantoPlayerColor color : HantoPlayerColor.values()) {
			hantoPlayers.put(color, hantoGame.getHantoPlayer(color));
		}
		try {
			//set up the board + peice counts
			for (PieceLocationPair pair : initialPieces) {
				boardPieces.put(new HantoCoordinateImpl(pair.location), pieceFactory.makePiece(pair.player, pair.pieceType));
				hantoPlayers.get(pair.player).placePiece(pair.pieceType, new HantoCoordinateImpl(pair.location));
			}
		}
		catch (HantoException e) {
			//invalid piece was added, silently fail.
		}
		
		//set the board
		GameBoard board = new GameBoard(boardPieces);		
		hantoGame.setHantoBoard(board);
		
	}

	/** Sets the turn number of the game. Where turn 1 is the first turn
	 * 
	 */
	@Override
	public void setTurnNumber(int turnNumber) {
		hantoGame.setTurnCount(turnNumber);
		
	}

	@Override
	public void setPlayerMoving(HantoPlayerColor player) {
		hantoGame.setCurrentPlayerColor(player);
		
	}

}
