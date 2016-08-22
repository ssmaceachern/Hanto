/**
 * 
 */
package hanto.tournament;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.studentssmaceachern.common.HantoCoordinateImpl;
import hanto.studentssmaceachern.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Sean
 *
 */
public class HantoPlayerTest {

	private HantoPlayer playerBlue;
	private TestHantoPlayer testPlayerBlue;
	private TestHantoPlayer testPlayerRed;
	private HantoPlayer playerRed;
	
	@Before
	public void setup() {
		testPlayerBlue = new TestHantoPlayer();
		testPlayerRed = new TestHantoPlayer();
		
		playerRed = testPlayerRed;
		playerBlue = testPlayerBlue;
		
		playerBlue.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		playerRed.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
	}
	
	
	@Test
	public void testFirstMoveShouldBeZeroZero() {
		HantoMoveRecord moveResult = playerBlue.makeMove(null);
		assertNull(moveResult.getFrom());
		assertEquals(new HantoCoordinateImpl(0, 0), moveResult.getTo());
	}
	
	@Test
	public void testTwoMovesShouldHaveTwoPiecesOnBoard() {
		HantoMoveRecord moveResult = playerBlue.makeMove(null);
		playerRed.makeMove(moveResult);
		
		//red, should have two pieces on its board
		assertEquals(2, testPlayerRed.getHantoGame().getBoard().getPieceCount());

	}
	
	@Test
	public void firstMoveAsResignShouldReturnResign() {
		HantoMoveRecord resignMove = new HantoMoveRecord(null, null, null);
		playerBlue.makeMove(null);
		HantoMoveRecord redMove = playerRed.makeMove(resignMove);
		assertNull(redMove.getFrom());
		assertNull(redMove.getTo());
		assertNull(redMove.getPiece());
	}
	
	
}
