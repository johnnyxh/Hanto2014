/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package alphaBetaTests;
import static org.junit.Assert.*;
import hanto.common.HantoCoordinate;
import hanto.common.HantoException;
import hanto.common.HantoGame;
import hanto.common.HantoGameID;
import hanto.common.HantoPiece;
import hanto.common.HantoPlayerColor;
import hanto.common.HantoPrematureResignationException;
import static hanto.common.HantoPieceType.*;
import static hanto.common.HantoPlayerColor.*;
import hanto.common.MoveResult;
import hanto.jxhernandez.HantoGameFactory;
import hanto.jxhernandez.alpha.AlphaHantoGame;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PolliceAlphaHantoTests

{

	/**
	 * 
	 * Internal class for these test cases.
	 * 
	 * @version Sep 13, 2014
	 */

	class TestHantoCoordinate implements HantoCoordinate

	{

		private final int x, y;

		public TestHantoCoordinate(int x, int y)

		{

			this.x = x;

			this.y = y;

		}

		/*
		 * 
		 * @see hanto.common.HantoCoordinate#getX()
		 */

		@Override
		public int getX()

		{

			return x;

		}

		/*
		 * 
		 * @see hanto.common.HantoCoordinate#getY()
		 */

		@Override
		public int getY()

		{

			return y;

		}

	}

	private static HantoGameFactory factory;

	private HantoGame game;

	@BeforeClass
	public static void initializeClass()

	{

		factory = HantoGameFactory.getInstance();

	}

	@Before
	public void setup()

	{

		game = factory.makeHantoGame(HantoGameID.ALPHA_HANTO);

	}

	@Test
	public void getAnAlphaHantoGameFromTheFactory()

	{

		assertTrue(game instanceof AlphaHantoGame);

	}

	@Test
	public void blueMakesValidFirstMove() throws HantoException, HantoPrematureResignationException

	{

		final MoveResult mr = game.makeMove(BUTTERFLY, null,
				new TestHantoCoordinate(0, 0));

		assertEquals(MoveResult.OK, mr);

	}

	@Test
	public void afterFirstMoveBlueButterflyIsAt0_0() throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));

		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 0));

		assertEquals(BUTTERFLY, p.getType());

		assertEquals(HantoPlayerColor.BLUE, p.getColor());

	}

	@Test(expected = HantoException.class)
	public void bluePlacesNonButterfly() throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(SPARROW, null, new TestHantoCoordinate(0, 0));

	}

	@Test
	public void redPlacesButterflyNextToBlueButterfly() throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 1));

		final HantoPiece p = game.getPieceAt(new TestHantoCoordinate(0, 1));

		assertEquals(BUTTERFLY, p.getType());

		assertEquals(HantoPlayerColor.RED, p.getColor());

	}

	@Test(expected = HantoException.class)
	public void blueAttemptsToPlaceButterflyAtWrongLocation()
			throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(-1, 1));

	}

	@Test
	public void redMakesValidSecondMoveAndGameIsDrawn() throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));

		final MoveResult mr = game.makeMove(BUTTERFLY, null,
				new TestHantoCoordinate(-1, 1));

		assertEquals(MoveResult.DRAW, mr);

	}

	@Test(expected = HantoException.class)
	public void redPlacesButterflyNonAdjacentToBlueButterfly()
			throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 0));

		game.makeMove(BUTTERFLY, null, new TestHantoCoordinate(0, 2));

	}

	@Test(expected = HantoException.class)
	public void attemptToMoveRatherThanPlace() throws HantoException, HantoPrematureResignationException

	{

		game.makeMove(BUTTERFLY, new TestHantoCoordinate(0, 1),
				new TestHantoCoordinate(0, 0));

	}

}