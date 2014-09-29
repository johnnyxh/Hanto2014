package hanto.jxhernandez.common;

import hanto.common.HantoPieceType;

public class HantoMoveRule {

	private HantoPieceType piece;
	private HantoMoveValidator moveValidator;
	
	public HantoMoveRule(HantoPieceType piece, HantoMove move, int numSpaces) {
		this.piece = piece;
		if (numSpaces <= 0) {
			this.moveValidator = HantoMoveFactory.getInstance().makeMoveValidator(move);
		} else {
			this.moveValidator = HantoMoveFactory.getInstance().makeMoveValidator(move, numSpaces);
		}
	}

	public HantoPieceType getPiece() {
		return piece;
	}

	public void setPiece(HantoPieceType piece) {
		this.piece = piece;
	}
	
	public HantoMoveValidator getMoveValidator() {
		return moveValidator;
	}
} 
