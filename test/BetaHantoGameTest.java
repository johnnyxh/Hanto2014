import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentjxhernandez.common.HantoPosition;

import org.junit.Test;

public class BetaHantoGameTest {

	@Test
	public void betaHantoGameRedFirst() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.RED);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.RED, betaGame.getPieceAt(firstMovePos).getColor());
	}

	@Test
	public void betaHantoGameBlueFirst() throws HantoException {
		HantoGame betaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.BETA_HANTO, HantoPlayerColor.BLUE);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		betaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(HantoPlayerColor.BLUE, betaGame.getPieceAt(firstMovePos).getColor());
	}

}
