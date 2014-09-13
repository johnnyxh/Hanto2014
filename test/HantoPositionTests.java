import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.studentjxhernandez.common.HantoPosition;
import hanto.studentjxhernandez.common.Piece;

import org.junit.Test;


public class HantoPositionTests {

	@Test
	public void HantoPositionEqualsTests() {
		HantoCoordinate pos1 = new HantoPosition(0, 0);
		HantoCoordinate samePos = new HantoPosition(0, 0);
		HantoCoordinate diffY = new HantoPosition(0, 1);
		HantoCoordinate diffX = new HantoPosition(1, 0);
		HantoCoordinate nullPos = null;
		String diffObject = "different";
		assertTrue(pos1.equals(samePos));
		assertTrue(pos1.equals(pos1));
		assertFalse(pos1.equals(diffY));
		assertFalse(pos1.equals(diffX));
		assertFalse(pos1.equals(nullPos));
		assertFalse(pos1.equals(diffObject));
	}

}
