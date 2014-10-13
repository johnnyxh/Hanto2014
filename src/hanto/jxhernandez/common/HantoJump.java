package hanto.jxhernandez.common;

import java.util.Map;

public class HantoJump implements HantoMoveValidator {

	int numMoves;
	
	public HantoJump(int numMoves) {
		this.numMoves = numMoves;
	}
	
	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest,
			Map<HantoPosition, Piece> board) {
		// TODO Auto-generated method stub
		return false;
	}

}
