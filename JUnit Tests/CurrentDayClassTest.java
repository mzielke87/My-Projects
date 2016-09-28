package problem1;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CurrentDayClassTest {
	
	@SuppressWarnings("unused")
	private static final Object[] parametersForCurrentDayClassTest() {
		return $(
				$(new DateClass(0,0,0), 0),
				$(new DateClass(1,1,1), 1),
				$(new DateClass(31,12,2015), 365),
				$(new DateClass(31,12,2016), 366),
				$(new DateClass(1,1,2015), 1),
				$(new DateClass(1,1,2016), 1),
				$(new DateClass(2,3,2015), 61),
				$(new DateClass(2,3,2016), 62),
				$(new DateClass(10,10,2015), 283),
				$(new DateClass(10,10,2016), 284)
		);
	}

	@Test
	@Parameters(method="parametersForCurrentDayClassTest")
	public void testCalcCurrentDay(DateClass ExpIn, int ExpOut) {
		int ActOut;
		CurrentDayClass test = new CurrentDayClass();
		ActOut = test.calcCurrentDay(ExpIn);
		assertEquals(ExpOut, ActOut, 0.1);
	}

}
