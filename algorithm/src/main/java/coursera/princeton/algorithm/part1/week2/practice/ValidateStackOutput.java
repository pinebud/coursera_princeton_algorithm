package coursera.princeton.algorithm.part1.week2.practice;

import org.junit.Assert;
import org.junit.Test;

public class ValidateStackOutput {
	
	/**
	 * O(N^3)
	 * @param toBeValidated
	 */
	public static boolean validate(int[] toBeValidated){
		int len = toBeValidated.length;
		for(int i =0;i<len;i++){
			if(!isClear(toBeValidated, i, findMax(toBeValidated, 0, i))){
				System.out.println("Not a valid stack output when validate "+toBeValidated[i]);
				return false;
			}
		}	
		return true;
	}
	
	/**
	 * O(N)
	 * @param array
	 * @param start included
	 * @param end excluded
	 * @return
	 */
	private static int findMax(int[] array, int start, int end){
		int max = -1;
		for(int i = start;i<end;i++){
			if(array[i] > max){
				max = array[i];
			}
		}
		return max;
	}
	

	/**
	 * isEmptySet?(A-B), A=[base, base+1, ... max], B= array[0, 1, ... ,start]
	 * O(N^2)
	 * Can be O(N) in time effort with involving a cloned writable array
	 */
	public static boolean isClear(int[] array, int start, int max){
		int base = array[start];
		for(int j = base+1;j<max;j++){
			boolean found = false;
			for(int k=0;k<start;k++){
				if(array[k] == j){
					found = true;
					break;
				}
			}
			if(!found)
				return false;
		}
		return true;
	}

//	@Test
	public void test() {		
		Assert.assertFalse(validate(new int[]{4,6,5,3,2,8,1,7,9,0}));
		Assert.assertTrue(validate(new int[]{5,4,3,2,1,0,6,7,8,9}));
		Assert.assertTrue(validate(new int[]{0,2,3,4,7,8,9,6,5,1}));
		Assert.assertTrue(validate(new int[]{2,1,3,0,5,6,7,4,8,9}));
		Assert.assertFalse(validate(new int[]{3,2,4,5,1,7,0,9,8,6}));
	}

}
