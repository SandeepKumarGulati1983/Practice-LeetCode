package LeetCodeSolution;

import java.util.HashSet;

public class TwoPointer {


	/*
	 * 3. Longest Substring Without Repeating Characters ===============================================
	 * 
	 * Brute force solution   - O(n3) 
	 * 			-- make differnyt substring - O(n2)
	 * 			-- check each substring for uniqe character - O(n)
	 * 
	 *With space - T - O(n), S - O(n)  - 2 pointer approch 
	 *
	 *Without space - O(n2)  - sliding window example 
	 */


	//O(N3) solution 
	//-- https://www.geeksforgeeks.org/length-of-the-longest-substring-without-repeating-characters/
	public static Boolean areDistinct(String str,
			int i, int j)
	{

		// Note : Default values in visited are false
		boolean[] visited = new boolean[26];

		for(int k = i; k <= j; k++)
		{
			if (visited[str.charAt(k) - 'a'] == true)
				return false;

			visited[str.charAt(k) - 'a'] = true;
		}
		return true;
	}

	public static int longestUniqueSubsttr(String str)
	{
		//Returns length of the longest substring
		//with all distinct characters.
		int n = str.length();

		// Result
		int res = 0;

		for(int i = 0; i < n; i++)
			for(int j = i; j < n; j++)
				if (areDistinct(str, i, j))
					res = Math.max(res, j - i + 1);

		return res;
	}


	// O (N2) solution of sliding window 
	public static int longestUniqueSubsttr1(String str)
	{
		int n = str.length();

		// Result
		int res = 0;

		for(int i = 0; i < n; i++)
		{

			// Note : Default values in visited are false
			boolean[] visited = new boolean[256];

			for(int j = i; j < n; j++)
			{

				// If current character is visited
				// Break the loop
				if (visited[str.charAt(j)] == true)
					break;

				// Else update the result if
				// this window is larger, and mark
				// current character as visited.
				else
				{
					res = Math.max(res, j - i + 1);
					visited[str.charAt(j)] = true;
				}
			}

			// Remove the first character of previous
			// window
			visited[str.charAt(i)] = false;
		}
		return res;
	}

	//O(N) solution 
	public int lengthOfLongestSubstring(String s) {        
		int maxLen = 0;
		HashSet<Character> window = new HashSet<>(); 

		int left = 0, right = 0;
		while(right < s.length()) { 
			while(window.contains(s.charAt(right)))
				window.remove(s.charAt(left++));  
			window.add(s.charAt(right++)); 
			maxLen = Math.max(maxLen, right - left);
		}

		return maxLen;
	}



	//992. Subarrays with K Different Integers

	//	public int subarraysWithKDistinct(int[] A, int K) {
	//
	//	}
	//
	//	void subarray(int[] a , int index , int size ) {
	//		if (index + size + 1 >= a.length) return ;
	//
	//		check(a, index , index+size);
	//		subarray(a , index+1 , size);
	//
	//	}
	//
	//	void check (int[] a ,  int startIndex, int endIndex) {
	//
	//		for (int i = 0; i < endIndex ; i++) {
	//
	//		}
	//
	//	}

	/*
	 * 11. Container With Most Water========================================================================
	 * 
	 * logic is check one by one max volume from each indics  which will be -- min value * distance between 2 indics 
	 * 
	 * Brute force  -- can be done in o(n2)
	 */


	// o(n2) solution 

	public int maxArea1(int[] height) {

		int maxVolume =0 ;

		for (int i = 0 ; i < height.length ; i++) {

			for (int j = i +1 ; j < height.length ; j++ ) {

				maxVolume = Math.max(maxVolume, Math.min(height[i], height[j])*(j-i));
			}
		}

		return maxVolume;

	}

	//O(n) solution 

	public int maxArea(int[] height) {

		int left = 0, right =height.length-1 ;

		int maxVolume =0;

		while (left<right) {

			maxVolume = Math.max(maxVolume, Math.min(height[left],height[right] )*(right-left));
			if (height[left] <height[right] || height[left] ==height[right]) {
				left++;
			}else right--;

		}

		return maxVolume;
	}


	//42. Trapping Rain Water
	//https://www.youtube.com/watch?v=Uog2Jmyb3iY
	/*
	 * just find the water which can accomodate over it  --> which is 
	 * equal to the height of minimum of its left highest building and right highest building. 
	 */

	// o(n2)
	public int trap1(int[] height) {

		int leftheighest =0, rightHeighest =0;
		int totalWaterAccomodation =0;

		for  (int i = 0 ; i < height.length; i++) {

			// left highest 
			for (int left = i ; left>=0 ; left--) {	
				leftheighest = Math.max(leftheighest ,height[left]);
			}
			System.out.println("left heighest "+leftheighest);

			// right highest 
			for (int right = i+1 ; right<height.length-1; right++) {
				rightHeighest = Math.max(rightHeighest ,height[right]);
			}
			System.out.println("right heighest "+rightHeighest);

			totalWaterAccomodation += Math.min(leftheighest, rightHeighest) -height[i];

			System.out.println("water over building "+i+ " = "+ (Math.min(leftheighest, rightHeighest) -height[i]));

		}
		return totalWaterAccomodation;

	}

	// BEST SOLUTION - O(N)
	public int trap(int[] height) {
		// time : O(n)
		// space : O(1)
		if (height.length==0) return 0; 
		int left = 0, right = height.length-1; 
		int leftMax=0, rightMax=0; 
		int ans = 0; 
		while (left < right) {
			if (height[left] > leftMax) leftMax = height[left]; 
			if (height[right] > rightMax) rightMax = height[right];
			if (leftMax < rightMax) {
				ans += Math.max(0, leftMax-height[left]); 
				left++; 
			} else {
				ans += Math.max(0, rightMax-height[right]); 
				right--; 
			}
		}
		return ans; 
	}


	//344. Reverse String
	public void reverseString(char[] s) {

		int left=0, right =s.length-1;
		
		while (left <right) {
			char temp = s[left];
			s[left] = s[right];
			s[right] = temp ;
			left++;
			right--;
		}
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TwoPointer instance = new TwoPointer();

		int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
		System.out.println(instance.trap(height));
		//11
		//		int[] height = {1,8,6,2,5,4,8,3,7};
		//		System.out.println(instance.maxArea(height));

		//		String d = "abcabcbb";  // 3
		//		String c = "bbbbb";  // 1
		//		String a = "dvdf";  // 3  important case 
		//		String s = "pwwkew";  // 3  
		//		System.out.println(instance.lengthOfLongestSubstring(d));
		//		System.out.println(instance.lengthOfLongestSubstring(c));
		//		System.out.println(instance.lengthOfLongestSubstring(a));
		//		System.out.println(instance.lengthOfLongestSubstring(s));


	}

}
