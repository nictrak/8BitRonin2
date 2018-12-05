package App;

public class KeyHandle {
	private boolean isJumpPressed;
	private boolean isMoveRightPressed;
	private boolean isAlreadyMoveRight;
	private boolean isMoveLeftPressed;
	private boolean isAlreadyMoveLeft;
	private boolean isMoveDownPressed;
	private boolean isAlreadyMoveDown;
	private boolean isAlreadyJump;
	
	
	//getter setter
	public boolean isJumpPressed() {
		return isJumpPressed;
	}
	public void setJumpPressed(boolean isJumpPressed) {
		this.isJumpPressed = isJumpPressed;
	}
	public boolean isMoveRightPressed() {
		return isMoveRightPressed;
	}
	public void setMoveRightPressed(boolean isMoveRightPressed) {
		this.isMoveRightPressed = isMoveRightPressed;
	}
	public boolean isMoveLeftPressed() {
		return isMoveLeftPressed;
	}
	public void setMoveLeftPressed(boolean isMoveLeftPressed) {
		this.isMoveLeftPressed = isMoveLeftPressed;
	}
	public boolean isMoveDownPressed() {
		return isMoveDownPressed;
	}
	public void setMoveDownPressed(boolean isMoveDownPressed) {
		this.isMoveDownPressed = isMoveDownPressed;
	}
	public boolean isAlreadyJump() {
		return isAlreadyJump;
	}
	public void setAlreadyJump(boolean isAlreadyJump) {
		this.isAlreadyJump = isAlreadyJump;
	}
	public boolean isAlreadyMoveRight() {
		return isAlreadyMoveRight;
	}
	public void setAlreadyMoveRight(boolean isAlreadyMoveRight) {
		this.isAlreadyMoveRight = isAlreadyMoveRight;
	}
	public boolean isAlreadyMoveLeft() {
		return isAlreadyMoveLeft;
	}
	public void setAlreadyMoveLeft(boolean isAlreadyMoveLeft) {
		this.isAlreadyMoveLeft = isAlreadyMoveLeft;
	}
	public boolean isAlreadyMoveDown() {
		return isAlreadyMoveDown;
	}
	public void setAlreadyMoveDown(boolean isAlreadyMoveDown) {
		this.isAlreadyMoveDown = isAlreadyMoveDown;
	}
	
	
}
