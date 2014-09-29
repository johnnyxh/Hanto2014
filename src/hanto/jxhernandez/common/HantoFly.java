package hanto.jxhernandez.common;

import java.util.Map;

public class HantoFly implements HantoMoveValidator {
	
	public HantoFly() {}
	
	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest, Map<HantoPosition,Piece> board) {
		return true;
	}
	
}
