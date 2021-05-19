package LeetCodeSolution;

public class Google_DP {

	//	//5. Longest Palindromic Substring

	public String longestPalindrome(String s) {
		if (s == null || s.length() < 1) return "";
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
	
	//121. Best Time to Buy and Sell Stock
	    public int maxProfit(int prices[]) {
	        int minprice = Integer.MAX_VALUE;
	        int maxprofit = 0;
	        for (int i = 0; i < prices.length; i++) {
	            if (prices[i] < minprice)
	                minprice = prices[i];
	            else if (prices[i] - minprice > maxprofit)
	                maxprofit = prices[i] - minprice;
	        }
	        return maxprofit;
	    }

	    // kadense algo for max subarray 
	    public int maxSubArray(int[] nums) {
	        
	        int currSum = nums[0];
	        int sum = nums[0];
	        
	        for (int i = 1; i < nums.length; i ++){
	            
	            currSum = Math.max(nums[i], currSum+ nums[i]);
	         sum = Math.max(sum , currSum);
	            
	        }
	        return sum;
	        
	        
	    }
	    // kadence algo for maximum contigous subarray 
	    public int maxSubArray1(int[] nums) {
	        
	        int currSum = 0;
	        int sum = 0;
	        
	        for (int i = 0; i < nums.length; i ++){
	            currSum += nums[i];
	            if (currSum <0 ) currSum = 0;
	            sum  = Math.max(sum , currSum);
	            
	        }
	        return sum;
	        
	        
	    }


}
