package problem2;

public class StateTableOutputData {

	private int x=0,y=0; StateTable nextState;
	
	public StateTableOutputData (int x, int y, StateTable nextState) {
		this.x=x;
		this.y=y;
		this.nextState=nextState;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public StateTable getNextState() {
		return nextState;
	}

	public void setOutputs(int x, int y, StateTable nextState) {
		this.x=x;
		this.y=y;
		this.nextState=nextState;
	}
}

