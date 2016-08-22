/**
 * 
 */
package hanto.tournament;


import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Sean
 *
 */
public class HantoTournamentTest {

	private TournamentRunner tournamentRunner;
	
	@Before
	public void setup() {
		tournamentRunner = new TournamentRunner(HantoPlayerColor.BLUE);
	}
	
	@Test
	public void testAIPlaysValidGame() throws HantoException {
		MoveResult res = tournamentRunner.runTournament();
		assertTrue( res == MoveResult.DRAW ||
					res == MoveResult.RED_WINS ||
					res == MoveResult.BLUE_WINS);
	}
	
}
