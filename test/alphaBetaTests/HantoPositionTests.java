/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design. The course was
 * taken at Worcester Polytechnic Institute. All rights reserved. This program and the
 * accompanying materials are made available under the terms of the Eclipse Public License
 * v1.0 which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package alphaBetaTests;
import static org.junit.Assert.*;
import hanto.jxhernandez.common.HantoPosition;

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
