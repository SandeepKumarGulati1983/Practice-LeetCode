package LeetCodeSolution;

import java.util.Collections;
import java.util.HashMap;

public class Google_practice_Summary {

	// 340. Longest Substring with At Most K Distinct Characters

	    public int lengthOfLongestSubstringKDistinct(String s, int k) {
	        
	        int n = s.length();
	        if (n*k ==0) return 0; // +1  // return 0 and not n 

	        int left =0 , right =0;
	        int maxlength =1;
	        HashMap<Character, Integer> map = new HashMap<>();

	        while (right<s.length()){
		        map.put(s.charAt(right), right++);
		        if (map.size() == k+1){
			        int minIndex = Collections.min(map.values());
			        left = minIndex+1;
			        map.remove(s.charAt(minIndex));
		        }
	            maxlength = Math.max(maxlength, right-left);
	        }
	        
	            
	        return maxlength;
	     }
	    



	//3. Longest Substring Without Repeating Characters

		int kDistinctElements(String  string){
			
			int left=0, right =0, maxLength=0;
			HashMap<Character, Integer> map = new HashMap<>();

			while (right < s.length()){
				if (map.containsKey(s.charAt(right)){
					left = Math.max(left, map.get(s.charAt(right)));
				}
				maxLength = Math.max(maxLength, right-length);
				map.put (s.charAt(right++));
			}

			return maxLength;
			
	}

	// next largest permutation 

	 public void nextPermutation(int[] nums) {
	        int i = nums.length - 2;
	        while (i >= 0 && nums[i + 1] <= nums[i]) {
	            i--;
	        }
	  // cover the case ..in case all are sorted in descending order 
	        if (i >= 0) { 
	            int j = nums.length - 1;
	            while (nums[j] <= nums[i]) {
	                j--;
	            }
	            swap(nums, i, j);
	        }
	    }


	
}
