/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package alphaBetaTests;
import static org.junit.Assert.*;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.jxhernandez.common.Piece;

import org.junit.Test;

/**
 * Tests for the piece class
 * @author johnny
 *
 */
public class PieceTests {

	/**
	 * Equals tests
	 */
	@Test
	public void HantoPieceEqualsTests() {
		HantoPiece piece1 = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		HantoPiece samePiece = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		HantoPiece diffColor = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.RED);
		HantoPiece diffPiece = new Piece(HantoPieceType.CRANE, HantoPlayerColor.BLUE);
		HantoPiece nullPiece = null;
		String diffObject = "different";
		assertTrue(piece1.equals(samePiece));
		assertTrue(piece1.equals(piece1));
		assertFalse(piece1.equals(diffColor));
		assertFalse(piece1.equals(diffPiece));
		assertFalse(piece1.equals(nullPiece));
		assertFalse(piece1.equals(diffObject));
	}
	
	/**
	 * Hash tests
	 */
	@Test
	public void HantoPieceHashTests() {
		HantoPiece piece1 = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		HantoPiece piece2 = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		HantoPiece diffColor = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.RED);
		HantoPiece diffPiece = new Piece(HantoPieceType.CRANE, HantoPlayerColor.BLUE);
		assertTrue(piece1.hashCode() == piece2.hashCode());
		assertFalse(piece1.hashCode() == diffColor.hashCode());
		assertFalse(piece1.hashCode() == diffPiece.hashCode());
	}
	
	/**
	 * Getters tests
	 */
	@Test
	public void HantoPieceGetters() {
		HantoPiece piece1 = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		assertEquals(HantoPieceType.BUTTERFLY, piece1.getType());
		assertEquals(HantoPlayerColor.BLUE, piece1.getColor());
	}

}
