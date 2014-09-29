package gammaDeltaTests;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGameFactory;

public class DeltaHantoGameTests {

	@Test
	public void resignationAcceptRed() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
		assertEquals(MoveResult.BLUE_WINS, testGame.makeMove(null, null, null));
	}
	
	@Test
	public void resignationAcceptBlue() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.BLUE);
		assertEquals(MoveResult.RED_WINS, testGame.makeMove(null, null, null));
	}

}
