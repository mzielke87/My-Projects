package problem2;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class StateTableClassTest {
	
	@SuppressWarnings("unused")
	private static final Object[] parametersForStateTableClassTest() {
		return $(
				$(StateTable.S0, false, 2, 1, StateTable.S0),
				$(StateTable.S0, true, 1, 2, StateTable.S1),
				$(StateTable.S1, false, 2, 1, StateTable.S1),
				$(StateTable.S1, true, 2, 2, StateTable.S2),
				$(StateTable.S2, false, 3, 1, StateTable.S2),
				$(StateTable.S2, true, 3, 2, StateTable.S3),
				$(StateTable.S3, false, 4, 3, StateTable.S3),
				$(StateTable.S3, true, 3, 0, StateTable.S1)
		);
	}

	@Test
	@Parameters(method="parametersForStateTableClassTest")
	public void testNextState(StateTable currentState, boolean expIn, int expX, int expY, StateTable expNextState) {
		StateTableOutputData actOutputs = new StateTableOutputData(0, 0, StateTable.S0);
		StateTableClass test = new StateTableClass();
		test.nextState(currentState, expIn, actOutputs);
		assertEquals(expX, actOutputs.getX());
		assertEquals(expY, actOutputs.getY());
		assertEquals(expNextState, actOutputs.getNextState());
	}

}
