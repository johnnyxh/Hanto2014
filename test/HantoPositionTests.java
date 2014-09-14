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
		HantoPosition pos1 = new HantoPosition(0, 0);
		HantoPosition samePos = new HantoPosition(0, 0);
		HantoPosition diffY = new HantoPosition(0, 1);
		HantoPosition diffX = new HantoPosition(1, 0);
		HantoPosition nullPos = null;
		String diffObject = "different";
		assertTrue(pos1.equals(samePos));
		assertTrue(pos1.equals(pos1));
		assertFalse(pos1.equals(diffY));
		assertFalse(pos1.equals(diffX));
		assertFalse(pos1.equals(nullPos));
		assertFalse(pos1.equals(diffObject));
	}
	
	@Test
	public void HantoPositionNextTo() {
		HantoPosition pos1 = new HantoPosition(1, 2);
		HantoPosition nextToPos1 = new HantoPosition(0, 3);
		HantoPosition notNextToPos1 = new HantoPosition(2, 0);
		assertTrue(pos1.isNextTo(nextToPos1));
		assertFalse(pos1.isNextTo(notNextToPos1));
	}

}
