package problem3;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class AutoGDWarnClassTest {
	
	@SuppressWarnings("unused")
	private static final Object[] parametersForAutoGDWarnClassTest() {
		return $(
				$(false, 100, 50, false, 0),
				$(true, 100, 50, false, 0),
				$(false, 99, 50, false, 0),
				$(true, 99, 50, true, 15),
				$(false, 100, 49, false, 0),
				$(true, 100, 49, true, 10),
				$(false, 99, 49, false, 0),
				$(true, 99, 49, true, 5)
		);
	}

	@Test
	@Parameters(method="parametersForAutoGDWarnClassTest")
	public void testAutoGearDownfunction(boolean expInGearUp, int expAirspeed, int expAltitude, boolean expOutGDWarning, int expOutTimer) {
		AutoGDWarnClass test = new AutoGDWarnClass();
		test.autoGearDownfunction(expInGearUp, expAirspeed, expAltitude);
		assertEquals(expOutGDWarning, test.isAutoGDWarn());
		assertEquals(expOutTimer, test.getTimer());
	}

}
