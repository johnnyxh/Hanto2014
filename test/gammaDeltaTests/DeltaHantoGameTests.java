package gammaDeltaTests;

import static org.junit.Assert.*;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGameFactory;

public class DeltaHantoGameTests {

	@Test
	public void resignationAccept() {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.DELTA_HANTO, HantoPlayerColor.RED);
	}

}
