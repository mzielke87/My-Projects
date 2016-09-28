package problem2;

public class StateTableClass {

	public void nextState (StateTable state, boolean a, StateTableOutputData outputs) {

		switch (state) {
		
		case S0:if (a) 
					outputs.setOutputs(1,2,StateTable.S1);
				else 
					outputs.setOutputs(2,1,StateTable.S0);		
				break;
				
		case S1:if (a) 
					outputs.setOutputs(2,2,StateTable.S2);
				else 
					outputs.setOutputs(2,1,StateTable.S1);		
				break;		
				
		case S2:if (a) 
					outputs.setOutputs(3,2,StateTable.S3);
				else 
					outputs.setOutputs(3,1,StateTable.S2);		
				break;
				
		case S3:if (a) 
					outputs.setOutputs(3,0,StateTable.S1);
				else 
					outputs.setOutputs(4,3,StateTable.S3);		
				break;
		
		// no Default is required for this switch because it is not possible for the
		// enum to go out of range and we are testing each state value in the enum, so
		// a missing state will also be detected
		}
	}
}
