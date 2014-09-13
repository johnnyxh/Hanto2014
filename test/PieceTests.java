import static org.junit.Assert.*;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentjxhernandez.common.Piece;

import org.junit.Test;


public class PieceTests {

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
	
	@Test
	public void HantoPieceGetters() {
		HantoPiece piece1 = new Piece(HantoPieceType.BUTTERFLY, HantoPlayerColor.BLUE);
		assertEquals(HantoPieceType.BUTTERFLY, piece1.getType());
		assertEquals(HantoPlayerColor.BLUE, piece1.getColor());
	}

}
