package LeetCodeSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Backtracking_LC {

	//https://leetcode.com/problems/combination-sum/discuss/16502/A-general-approach-to-backtracking-questions-in-Java-(Subsets-Permutations-Combination-Sum-Palindrome-Partitioning)
	
//	//Subsets : https://leetcode.com/problems/subsets/
//
//		public List<List<Integer>> subsets(int[] nums) {
//		    List<List<Integer>> list = new ArrayList<>();
//		    Arrays.sort(nums);
//		    backtrack(list, new ArrayList<>(), nums, 0);
//		    return list;
//		}
//
//		private void backtrack(List<List<Integer>> list , List<Integer> tempList, int [] nums, int start){
//		    list.add(new ArrayList<>(tempList));
//		    for(int i = start; i < nums.length; i++){
//		        tempList.add(nums[i]);
//		        backtrack(list, tempList, nums, i + 1);
//		        tempList.remove(tempList.size() - 1);
//		    }
//		}
//		
//		//Subsets II (contains duplicates) : https://leetcode.com/problems/subsets-ii/
//
//		public List<List<Integer>> subsetsWithDup(int[] nums) {
//		    List<List<Integer>> list = new ArrayList<>();
//		    Arrays.sort(nums);
//		    backtrack(list, new ArrayList<>(), nums, 0);
//		    return list;
//		}
//
//		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int start){
//		    list.add(new ArrayList<>(tempList));
//		    for(int i = start; i < nums.length; i++){
//		        if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
//		        tempList.add(nums[i]);
//		        backtrack(list, tempList, nums, i + 1);
//		        tempList.remove(tempList.size() - 1);
//		    }
//		} 
//		//Permutations : https://leetcode.com/problems/permutations/
////
//		public List<List<Integer>> permute(int[] nums) {
//		   List<List<Integer>> list = new ArrayList<>();
//		   // Arrays.sort(nums); // not necessary
//		   backtrack(list, new ArrayList<>(), nums);
//		   return list;
//		}
//
//		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
//		   if(tempList.size() == nums.length){
//		      list.add(new ArrayList<>(tempList));
//		   } else{
//		      for(int i = 0; i < nums.length; i++){ 
//		         if(tempList.contains(nums[i])) continue; // element already exists, skip
//		         tempList.add(nums[i]);
//		         backtrack(list, tempList, nums);
//		         tempList.remove(tempList.size() - 1);
//		      }
//		   }
//		} 
//		//Permutations II (contains duplicates) : https://leetcode.com/problems/permutations-ii/
//
//		public List<List<Integer>> permuteUnique(int[] nums) {
//		    List<List<Integer>> list = new ArrayList<>();
//		    Arrays.sort(nums);
//		    backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
//		    return list;
//		}
//
//		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
//		    if(tempList.size() == nums.length){
//		        list.add(new ArrayList<>(tempList));
//		    } else{
//		        for(int i = 0; i < nums.length; i++){
//		            if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
//		            used[i] = true; 
//		            tempList.add(nums[i]);
//		            backtrack(list, tempList, nums, used);
//		            used[i] = false; 
//		            tempList.remove(tempList.size() - 1);
//		        }
//		    }
//		}
//		//Combination Sum : https://leetcode.com/problems/combination-sum/
//
//		public List<List<Integer>> combinationSum(int[] nums, int target) {
//		    List<List<Integer>> list = new ArrayList<>();
//		    Arrays.sort(nums);
//		    backtrack(list, new ArrayList<>(), nums, target, 0);
//		    return list;
//		}
//
//		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
//		    if(remain < 0) return;
//		    else if(remain == 0) list.add(new ArrayList<>(tempList));
//		    else{ 
//		        for(int i = start; i < nums.length; i++){
//		            tempList.add(nums[i]);
//		            backtrack(list, tempList, nums, remain - nums[i], i); // not i + 1 because we can reuse same elements
//		            tempList.remove(tempList.size() - 1);
//		        }
//		    }
//		}
//		//Combination Sum II (can't reuse same element) : https://leetcode.com/problems/combination-sum-ii/
//
//		public List<List<Integer>> combinationSum2(int[] nums, int target) {
//		    List<List<Integer>> list = new ArrayList<>();
//		    Arrays.sort(nums);
//		    backtrack(list, new ArrayList<>(), nums, target, 0);
//		    return list;
//		    
//		}
//
//		private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
//		    if(remain < 0) return;
//		    else if(remain == 0) list.add(new ArrayList<>(tempList));
//		    else{
//		        for(int i = start; i < nums.length; i++){
//		            if(i > start && nums[i] == nums[i-1]) continue; // skip duplicates
//		            tempList.add(nums[i]);
//		            backtrack(list, tempList, nums, remain - nums[i], i + 1);
//		            tempList.remove(tempList.size() - 1); 
//		        }
//		    }
//		} 
//	//	Palindrome Partitioning : https://leetcode.com/problems/palindrome-partitioning/
//
//		public List<List<String>> partition(String s) {
//		   List<List<String>> list = new ArrayList<>();
//		   backtrack(list, new ArrayList<>(), s, 0);
//		   return list;
//		}
//
//		public void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
//		   if(start == s.length())
//		      list.add(new ArrayList<>(tempList));
//		   else{
//		      for(int i = start; i < s.length(); i++){
//		         if(isPalindrome(s, start, i)){
//		            tempList.add(s.substring(start, i + 1));
//		            backtrack(list, tempList, s, i + 1);
//		            tempList.remove(tempList.size() - 1);
//		         }
//		      }
//		   }
//		}
//
//		public boolean isPalindrome(String s, int low, int high){
//		   while(low < high)
//		      if(s.charAt(low++) != s.charAt(high--)) return false;
//		   return true;
//		} 
//	
	
	// 22. Generate Parentheses 
	    public List<String> generateParenthesis(int n) {
	        List<String> ans = new ArrayList();
	        backtrack(ans, new StringBuilder(), 0, 0, n);
	        return ans;
	    }

	    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max){
	        if (cur.length() == max * 2) {
	            ans.add(cur.toString());
	            return;
	        }

	        if (open < max) {
	            cur.append("(");
	            backtrack(ans, cur, open+1, close, max);
	            cur.deleteCharAt(cur.length() - 1);
	        }
	        if (close < open) {
	            cur.append(")");
	            backtrack(ans, cur, open, close+1, max);
	            cur.deleteCharAt(cur.length() - 1);
	        }
	    }
	    
	    //17. Letter Combinations of a Phone Number
	       
	        
	        public List<String> letterCombinations(String digits) {
	        	
	        	  List<String> combinations = new ArrayList<>();
	 	         Map<Character, String> letters = Map.of(
	 	            '2', "abc", '3', "def", '4', "ghi", '5', "jkl", 
	 	            '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");
	 	         String phoneDigits;
	 	         //----------------------
	        	
	            // If the input is empty, immediately return an empty answer array
	            if (digits.length() == 0) {
	                return combinations;
	            }
	            
	            // Initiate backtracking with an empty path and starting index of 0
	            phoneDigits = digits;
	           // backtrack(0, new StringBuilder());
	            backtrack(combinations, new StringBuilder(),phoneDigits ,letters,  0 );
	            return combinations;
	        }
	        
	        private void backtrack(List<String> oList, StringBuilder path ,String inputDigits,Map<Character, String> letters,  int index) {
	            // If the path is the same length as digits, we have a complete combination
	            if (path.length() == inputDigits.length()) {
	            	oList.add(path.toString());
	                return; // Backtrack
	            }
	            
	            // Get the letters that the current digit maps to, and loop through them
	            String possibleLetters = letters.get(inputDigits.charAt(index));
	            for (char letter: possibleLetters.toCharArray()) {
	                // Add the letter to our current path
	                path.append(letter);
	                // Move on to the next digit
	                backtrack(oList, path, inputDigits , letters, index + 1);
	                // Backtrack by removing the letter before moving onto the next
	                path.deleteCharAt(path.length() - 1);
	            }
	        }
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
