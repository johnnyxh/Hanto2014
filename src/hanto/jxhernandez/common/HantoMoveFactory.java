/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package hanto.jxhernandez.common;

/**
 * The move factory in charge of creating specific movement HantoMoveValidator objects
 */
public class HantoMoveFactory {
	
	private static final HantoMoveFactory INSTANCE = new HantoMoveFactory();

	private HantoMoveFactory() {}
	
	public static HantoMoveFactory getInstance() {
		return INSTANCE;
	}
	
	/**
	 * Method makeMoveValidator.
	 * @param moveId HantoMove
	 * @return HantoMoveValidator
	 */
	public HantoMoveValidator makeMoveValidator(HantoMove moveId) {
		return makeMoveValidator(moveId, 1);
	}
	
	/**
	 * Method makeMoveValidator.
	 * @param moveId HantoMove
	 * @param num int
	 * @return HantoMoveValidator
	 */
	public HantoMoveValidator makeMoveValidator(HantoMove moveId, int num) {
		HantoMoveValidator moveValidator= null;
		switch (moveId) {
		case WALK:
			moveValidator = new HantoWalk(num);
			break;
		case FLY:
			moveValidator = new HantoFly();
			break;
		}
		return moveValidator;
	}
}
