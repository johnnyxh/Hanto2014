import static org.junit.Assert.*;
import hanto.HantoGameFactory;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPieceType;
import hanto.common.HantoPlayerColor;
import hanto.common.MoveResult;
import hanto.studentjxhernandez.common.HantoPosition;
import hanto.studentjxhernandez.common.Piece;

import org.junit.Test;

public class AlphaHantoGameTest {

	@Test
	public void alphaHantoGameGoodFirstMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		assertEquals(MoveResult.OK, alphaGame.makeMove(
				HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	@Test(expected = HantoException.class)
	public void alphaHantoGameBadFirstMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(1, 0);
		assertEquals(MoveResult.OK, alphaGame.makeMove(
				HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	@Test
	public void alphaHantoGetPieceAfterFirstMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		HantoPiece firstPiece = new Piece(HantoPieceType.BUTTERFLY,
				HantoPlayerColor.BLUE);
		assertEquals(firstPiece, alphaGame.getPieceAt(firstMovePos));
	}

	@Test(expected = HantoException.class)
	public void alphaHantoGameNotButterfly() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		alphaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
	}

	@Test
	public void alphaHantoGameGoodSecondMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 1);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		assertEquals(MoveResult.DRAW, alphaGame.makeMove(
				HantoPieceType.BUTTERFLY, null, secondMovePos));
	}

	@Test(expected = HantoException.class)
	public void alphaHantoGameBadSecondMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 2);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos);
	}

	@Test(expected = HantoException.class)
	public void alphaHantoGameMoveExisting() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate originalPos = new HantoPosition(1, 0);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, originalPos, firstMovePos);
	}

	@Test
	public void alphaHantoGamePrintableBoard() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 1);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos);
		System.out.println(alphaGame.getPrintableBoard());
		// Due to how iterated sets do not guarantee an order, I'm not entirely
		// sure how to test this. As long as a BLUE Butterfly is on 0,0 and a
		// RED Butterfly is on 0,1 then this works.
	}
}
