/**
 * 
 */
package hanto.tournament;

import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentssmaceachern.common.GameState;
import hanto.studentssmaceachern.common.HantoGameFactory;
import hanto.studentssmaceachern.tournament.HantoPlayer;
import hanto.tournament.HantoGamePlayer;
import hanto.tournament.HantoMoveRecord;

/**
 * @author Sean
 *
 */
public class TournamentRunner {

	private HantoGamePlayer playerOne;
	private HantoGamePlayer playerTwo;
	private HantoPlayerColor firstPlayer;
	private HantoGame game;
	private HantoGamePlayer currentPlayer;
	private int turnCount;
	private static final int TURN_LIMIT = 200;
	
	public TournamentRunner(HantoPlayerColor firstPlayer) {
		this.firstPlayer = firstPlayer;
	}
	
	private void initializePlayers() {
		playerOne = new HantoPlayer();
		playerTwo = new HantoPlayer();
		
		game = HantoGameFactory.getInstance().makeHantoGame(HantoGameID.EPSILON_HANTO, firstPlayer);
		
		playerOne.startGame(HantoGameID.EPSILON_HANTO, firstPlayer, true);
		playerTwo.startGame(HantoGameID.EPSILON_HANTO, GameState.opposingPlayerColor(firstPlayer), false);
	}
	
	public MoveResult runTournament() throws HantoException {
		initializePlayers();
		
		turnCount = 0;
		MoveResult moveResult = MoveResult.OK;
		
		HantoMoveRecord lastMove = null;
		
		while (moveResult == MoveResult.OK && turnCount < TURN_LIMIT) {
			HantoMoveRecord currentMove = getNextPlayer().makeMove(lastMove);
			moveResult = game.makeMove(currentMove.getPiece(), currentMove.getFrom(), currentMove.getTo());
			lastMove = currentMove;
			turnCount ++;
		}
		
		if (moveResult == MoveResult.OK) {
			moveResult = MoveResult.DRAW;
		}
		return moveResult;
		
	}
	
	private HantoGamePlayer getNextPlayer() {
		if (currentPlayer == playerOne) {
			currentPlayer = playerTwo;
		}
		else {
			currentPlayer = playerOne;
		}
		return currentPlayer;
	}
	
}
