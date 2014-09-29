package hanto.jxhernandez.common;

public class HantoMoveFactory {
	
	private static final HantoMoveFactory instance = new HantoMoveFactory();

	private HantoMoveFactory() {}
	
	public static HantoMoveFactory getInstance() {
		return instance;
	}
	
	public HantoMoveValidator makeMoveValidator(HantoMove moveId) {
		return makeMoveValidator(moveId, 1);
	}
	
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
