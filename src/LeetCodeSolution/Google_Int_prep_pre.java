package LeetCodeSolution;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Google_Int_prep_pre {


	//longest substring with no repititive character 

	public int lengthOfLongestSubstring(String s) {   

		int left = 0 , right =0;
		int maxLength = 0;

		HashMap<Character , Integer > map = new HashMap<>();

		while (right <s.length()){


			if (map.containsKey(s.charAt(right))){
				left = Math.max(left, map.get(s.charAt(right))) ;
			}
			maxLength = Math.max(maxLength , right-left) ;
			map.put(s.charAt(right), right++);


		}

		return maxLength;

	}
/*
	// longest substring with k distinct character 
	    public int lengthOfLongestSubstringKDistinct(String s, int k) {
		        
		        int n = s.length();
		        if (n*k ==0) return 0; // +1 

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
	    


	//with 2 distinct character 
	    public int lengthOfLongestSubstringTwoDistinct(String s) {
		        int k =2; 
		        int n = s.length();
		        //if (n*k ==0) return 0; // +1  // return 0 and not n 
		        if (n<=1) return n;
		        int left =0 , right =0;
		        int maxlength =1;
		        HashMap<Character, Integer> map = new HashMap<>();

		        while (right<n){
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

*/

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Google_Int_prep_pre instance = new Google_Int_prep_pre();
		//String s = "abcabcbb"; //3
		String s = "dvdf";  //3
		System.out.println(instance.lengthOfLongestSubstring(s));

		//		char[][] grid = {
		//				{'1','1',"1","1","0"},
		//				{'1','1',"0","1","0"},
		//				{'1','1',"0","0","0"},
		//				{"0","0","0","0","0"}
		//		};

	}



}
