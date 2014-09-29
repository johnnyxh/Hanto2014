package hanto.jxhernandez.common;

import java.util.List;
import java.util.Map;

public class HantoWalk implements HantoMoveValidator {

	int numMoves;

	public HantoWalk(int numMoves) {
		this.numMoves = numMoves;
	}

	@Override
	public boolean isMoveValid(HantoPosition orig, HantoPosition dest,
			Map<HantoPosition, Piece> board) {
		if (orig.surroundingHexes().contains(dest)) {
			List<HantoPosition> walkingSpaceHexes = orig.surroundingHexes();
			walkingSpaceHexes.retainAll(dest.surroundingHexes());
			if (board.get(walkingSpaceHexes.get(0)) == null
					|| board.get(walkingSpaceHexes.get(1)) == null) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

}
