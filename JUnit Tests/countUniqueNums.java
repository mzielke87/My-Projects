package problem4;

public class CountUniqueNums {

	public int countUnique (int[] nums) {
		int unique=0;
		int [] intVal = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		for (int i = 0; i<nums.length; i++) {
			intVal[nums[i]+9]++;
			if (intVal[nums[i]+9]==1)
				unique++;
			else
				if (intVal[nums[i]+9]==2)
					unique--;
		}
		return unique;
	 }
}