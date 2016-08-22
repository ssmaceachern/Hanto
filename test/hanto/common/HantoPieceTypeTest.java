package hanto.common;

import static org.junit.Assert.*;
import hanto.common.HantoPieceType;

import org.junit.Test;

public class HantoPieceTypeTest {

	@Test
	public void testToString() {
		assertTrue(HantoPieceType.BUTTERFLY.toString().equals("Butterfly"));
		assertTrue(HantoPieceType.CRAB.toString().equals("Crab"));
		assertTrue(HantoPieceType.HORSE.toString().equals("Horse"));
		assertTrue(HantoPieceType.CRANE.toString().equals("Crane"));
		assertTrue(HantoPieceType.DOVE.toString().equals("Dove"));
		assertTrue(HantoPieceType.SPARROW.toString().equals("Sparrow"));
	}
	
	@Test
	public void testGetSymbol() {
		assertTrue(HantoPieceType.BUTTERFLY.getSymbol().equals("B"));
		assertTrue(HantoPieceType.CRAB.getSymbol().equals("C"));
		assertTrue(HantoPieceType.HORSE.getSymbol().equals("H"));
		assertTrue(HantoPieceType.CRANE.getSymbol().equals("N"));
		assertTrue(HantoPieceType.DOVE.getSymbol().equals("D"));
		assertTrue(HantoPieceType.SPARROW.getSymbol().equals("S"));
	}
	

}
