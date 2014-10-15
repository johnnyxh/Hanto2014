package epsilonTests;

import static org.junit.Assert.*;
import hanto.common.HantoException;
import hanto.common.HantoGameID;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import hanto.common.MoveResult;
import hanto.studentjxhernandez.tournament.HantoPlayer;
import hanto.tournament.HantoMoveRecord;

import org.junit.Test;

import common.HantoTestGame;
import common.HantoTestGameFactory;

public class HantoPlayerTests {

	@Test
	public void epsilonHantoTestAIGame1() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		HantoPlayer player1 = new HantoPlayer();
		HantoPlayer player2 = new HantoPlayer();
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord firstMove = player1.makeMove(null);
		assertEquals(MoveResult.OK, testGame.makeMove(firstMove.getPiece(), firstMove.getFrom(), firstMove.getTo()));
		HantoMoveRecord secondMove = player2.makeMove(firstMove);
		assertEquals(MoveResult.OK, testGame.makeMove(secondMove.getPiece(), secondMove.getFrom(), secondMove.getTo()));
		HantoMoveRecord thirdMove = player1.makeMove(secondMove);
		assertEquals(MoveResult.OK, testGame.makeMove(thirdMove.getPiece(), thirdMove.getFrom(), thirdMove.getTo()));
		HantoMoveRecord fourthMove = player2.makeMove(thirdMove);
		testGame.makeMove(fourthMove.getPiece(), fourthMove.getFrom(), fourthMove.getTo());
	}
	
	@Test
	public void epsilonHantoTestAIGame2() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		HantoPlayer player1 = new HantoPlayer();
		HantoPlayer player2 = new HantoPlayer();
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord firstMove = player1.makeMove(null);
		assertEquals(MoveResult.OK, testGame.makeMove(firstMove.getPiece(), firstMove.getFrom(), firstMove.getTo()));
		HantoMoveRecord secondMove = player2.makeMove(firstMove);
		assertEquals(MoveResult.OK, testGame.makeMove(secondMove.getPiece(), secondMove.getFrom(), secondMove.getTo()));
		HantoMoveRecord thirdMove = player1.makeMove(secondMove);
		assertEquals(MoveResult.OK, testGame.makeMove(thirdMove.getPiece(), thirdMove.getFrom(), thirdMove.getTo()));
		HantoMoveRecord fourthMove = player2.makeMove(thirdMove);
		testGame.makeMove(fourthMove.getPiece(), fourthMove.getFrom(), fourthMove.getTo());
	}
	
	@Test
	public void epsilonHantoTestAIGame3() throws HantoException {
		HantoTestGame testGame = HantoTestGameFactory.getInstance().makeHantoTestGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED);
		HantoPlayer player1 = new HantoPlayer();
		HantoPlayer player2 = new HantoPlayer();
		player1.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.BLUE, true);
		player2.startGame(HantoGameID.EPSILON_HANTO, HantoPlayerColor.RED, false);
		HantoMoveRecord firstMove = player1.makeMove(null);
		assertEquals(MoveResult.OK, testGame.makeMove(firstMove.getPiece(), firstMove.getFrom(), firstMove.getTo()));
		HantoMoveRecord secondMove = player2.makeMove(firstMove);
		assertEquals(MoveResult.OK, testGame.makeMove(secondMove.getPiece(), secondMove.getFrom(), secondMove.getTo()));
		HantoMoveRecord thirdMove = player1.makeMove(secondMove);
		assertEquals(MoveResult.OK, testGame.makeMove(thirdMove.getPiece(), thirdMove.getFrom(), thirdMove.getTo()));
		HantoMoveRecord fourthMove = player2.makeMove(thirdMove);
		testGame.makeMove(fourthMove.getPiece(), fourthMove.getFrom(), fourthMove.getTo());
	}

}
