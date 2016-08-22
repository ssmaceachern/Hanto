/**
 * 
 */
package hanto.tournament;

import hanto.studentssmaceachern.epsilon.EpsilonHantoGame;
import hanto.studentssmaceachern.tournament.HantoAI;
import hanto.studentssmaceachern.tournament.HantoPlayer;

/**
 * @author Sean
 *
 */
public class TestHantoPlayer extends HantoPlayer {

	/** Returns the current hanto game
	 * 
	 * @return The hanto game being used
	 */
	public EpsilonHantoGame getHantoGame() {
		return game;
	}
	
	/** Returns the Hanto AI currently being used
	 * 
	 * @return The hanto AI
	 */
	public HantoAI getHantoAI() {
		return hantoAI;
	}
}
