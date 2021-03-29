package LeetCodeSolution;

public class LeetCodeArrayQuestions {

	//35. Search Insert Position
	public int searchInsert(int[] nums, int target) {

		for (int i = 0 ; i < nums.length ; i++) {

			if (nums[i] >=target) {
				return i ;
			}
		}

		return nums.length;

	}

	//53. Maximum Subarray
	public int maxSubArray(int[] nums) {

		int currentSum = 0 , sum = 0 ;
		
		int slow = 0 , fast = 0 ;
		
		while(fast <nums.length ) {
			
			currentSum += nums[fast];
			
			if (currentSum > sum ) {
				sum = currentSum ;
				fast++;
			}else if (currentSum < sum) {
				currentSum = 0 ;
				slow = fast++;
			}
			
		}
		
		System.out.println("--"+slow +"--"+fast);
		return sum;
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		LeetCodeArrayQuestions inst = new LeetCodeArrayQuestions();

		//53. Maximum Subarray
		int[] a = {-2,1,-3,4,-1,2,1,-5,4};
		System.out.println(inst.maxSubArray(a));
		//35. Search Insert Position
//		int[] a = {1,3,5,6};
//		int target = 5;

		//System.out.println(inst.searchInsert(a,target));

	}

}
