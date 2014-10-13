package hanto.jxhernandez.common;

import java.util.List;
import java.util.Map;

public class HantoJump implements HantoMoveValidator {

	int numMoves;
	
	public HantoJump(int numMoves) {
		this.numMoves = numMoves;
	}
	
	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest,
			Map<HantoPosition, Piece> board) {
		List<HantoPosition> hexLine = orig.hexesBetweenLine(dest);
		// No spaces being jumped, needs to jump over at least one (or not going in straight line so returned empty)
		if (hexLine.size() == 0) {
			return false;
		}
		// If there isn't a piece return false;
		for(int i=0; i < hexLine.size(); i++) {
			if (board.get(hexLine.get(i)) == null) {
				return false;
			}
		}
		return true;
	}

}
