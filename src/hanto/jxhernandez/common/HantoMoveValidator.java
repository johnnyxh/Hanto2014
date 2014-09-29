package hanto.jxhernandez.common;

import java.util.Map;

public interface HantoMoveValidator {

	public boolean isMoveValid(HantoPosition orig, HantoPosition dest,
			Map<HantoPosition, Piece> board);
}
