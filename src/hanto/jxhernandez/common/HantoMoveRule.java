package hanto.jxhernandez.common;

import hanto.common.HantoPieceType;

public class HantoMoveRule {

	private HantoPieceType piece;
	private HantoMoveValidator moveValidator;
	
	public HantoMoveRule(HantoPieceType piece, HantoMove move, int numSpaces) {
		this.piece = piece;
		this.moveValidator = HantoMoveFactory.getInstance().makeMoveValidator(move, numSpaces);
	}
	
	public HantoMoveRule(HantoPieceType piece, HantoMove move) {
		this.piece = piece;
		this.moveValidator = HantoMoveFactory.getInstance().makeMoveValidator(move);
	}

	public HantoPieceType getPiece() {
		return piece;
	}
	
	public HantoMoveValidator getMoveValidator() {
		return moveValidator;
	}
} 
