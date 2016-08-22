package hanto.common;

import static org.junit.Assert.*;

import org.junit.Test;

public class HantoGameIDTest {

	@Test
	public void test() {
		assertNotNull(HantoGameID.valueOf("ALPHA_HANTO"));
		assertNotNull(HantoGameID.valueOf("BETA_HANTO"));
		assertNotNull(HantoGameID.valueOf("GAMMA_HANTO"));
		assertNotNull(HantoGameID.valueOf("DELTA_HANTO"));
		assertNotNull(HantoGameID.valueOf("EPSILON_HANTO"));
		assertNotNull(HantoGameID.valueOf("THETA_HANTO"));
		assertNotNull(HantoGameID.valueOf("ZETA_HANTO"));
		assertNotNull(HantoGameID.valueOf("IOTA_HANTO"));
	}

}
