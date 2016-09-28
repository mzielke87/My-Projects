package problem4;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import junitparams.JUnitParamsRunner;
import static junitparams.JUnitParamsRunner.$;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class CountUniqueNumsTest {
	
	@SuppressWarnings("unused")
	private static final Object[] parametersForCountUniqueNumesTest() {
		return $(
				$(new int[]{1,2}, 2),
				$(new int[]{1,1}, 0),
				$(new int[]{1,2,1,2,3}, 1),
				$(new int[]{1,2,1,2}, 0),
				$(new int[]{1,2,1,2,1,2,1,2}, 0),
				$(new int[]{1,2,1,2,1,2,1,2,3}, 1)
		);
	}

	@Test
	@Parameters(method="parametersForCountUniqueNumesTest")
	public void testCountUnique(int[] expIn, int expOut) {
		int actOut;
		CountUniqueNums test = new CountUniqueNums();
		actOut = test.countUnique(expIn);
		assertEquals(expOut, actOut);
	}

}
