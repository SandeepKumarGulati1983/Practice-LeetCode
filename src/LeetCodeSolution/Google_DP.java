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

	    // Coin change problem 
	    static int  minSofar = Integer.MAX_VALUE;
	    public static void CoinChange(int[] nums, List<Integer> choosen, int start, int target) {

	        if (target==0) {
	            System.out.println(choosen);
	            if(minSofar>choosen.size())
	                minSofar=choosen.size();            
	        }
	        else if(target>1) {
	            for (int i = start; i < nums.length; i++) {
	                choosen.add(nums[i]);
	                CoinChange(nums, choosen, i,target-nums[i]); 
	                choosen.remove(choosen.size() - 1);

	            }
	        }
	    }

	    void mainOfCoinChange(String[] args) {    
	        int [] denominations = {1,2,5};             
	        CoinChange(denominations, new ArrayList<Integer>(), 0, 10 );        
	        System.out.println("Min : " + minSofar);        
	    } 
	    
	    // another solution for coin cahnge problem 
	    public int coinChange(int[] coins, int amount) {
	        if (amount < 1) return 0;
	        return coinChange(coins, amount, new int[amount]);
	      }

	      private int coinChange(int[] coins, int rem, int[] count) {
	        if (rem < 0) return -1;
	        if (rem == 0) return 0;
	        if (count[rem - 1] != 0) return count[rem - 1];
	        int min = Integer.MAX_VALUE;
	        for (int coin : coins) {
	          int res = coinChange(coins, rem - coin, count);
	          if (res >= 0 && res < min)
	            min = 1 + res;
	        }
	        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
	        return count[rem - 1];
	      }

}
