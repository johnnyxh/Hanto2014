/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package alphaBetaTests;
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
/**
 * Alpha Hanto tests
 * @author johnny
 *
 */
public class AlphaHantoGameTest {

	/**
	 * First move test
	 * @throws HantoException
	 */
	@Test
	public void alphaHantoGameGoodFirstMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		assertEquals(MoveResult.OK, alphaGame.makeMove(
				HantoPieceType.BUTTERFLY, null, firstMovePos));
	}
	
	/**
	 * Bad first move test
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void alphaHantoGameBadFirstMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(1, 0);
		assertEquals(MoveResult.OK, alphaGame.makeMove(
				HantoPieceType.BUTTERFLY, null, firstMovePos));
	}

	/**
	 * Get piece test
	 * @throws HantoException
	 */
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

	/**
	 * Not butterfly test
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void alphaHantoGameNotButterfly() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		alphaGame.makeMove(HantoPieceType.SPARROW, null, firstMovePos);
	}

	/**
	 * Good second move test
	 * @throws HantoException
	 */
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

	/**
	 * Bad second move test
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void alphaHantoGameBadSecondMove() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate secondMovePos = new HantoPosition(0, 2);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, firstMovePos);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, null, secondMovePos);
	}

	/**
	 * Can't move existing piece
	 * @throws HantoException
	 */
	@Test(expected = HantoException.class)
	public void alphaHantoGameMoveExisting() throws HantoException {
		HantoGame alphaGame = HantoGameFactory.getInstance().makeHantoGame(
				HantoGameID.ALPHA_HANTO);
		HantoCoordinate firstMovePos = new HantoPosition(0, 0);
		HantoCoordinate originalPos = new HantoPosition(1, 0);
		alphaGame.makeMove(HantoPieceType.BUTTERFLY, originalPos, firstMovePos);
	}

	/**
	 * Printable board test
	 * @throws HantoException
	 */
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
