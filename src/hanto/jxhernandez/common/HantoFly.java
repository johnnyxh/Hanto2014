package hanto.jxhernandez.common;

import java.util.Map;

public class HantoFly implements HantoMoveValidator {
	
	public HantoFly() {}
	
	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest, Map<HantoPosition,Piece> board) {
		// The destination check will be sure it lands on a valid spot
		return true;
	}
	
}
