package LeetCodeSolution;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class Google_Int_prep_pre {


	//200. Number of Islands
	//
	//	public int numIslands(char[][] grid) {
	//
	//	}

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

	// static final int NO_OF_CHARS = 256;
	// 
	// static int longestUniqueSubsttr(String str)
	// {
	//     int n = str.length();
	//
	//     int res = 0; // result
	//
	//     int [] lastIndex = new int[NO_OF_CHARS];
	//     Arrays.fill(lastIndex, -1);
	//
	//     int left = 0;
	//     for (int right = 0; right < n; right++) {
	//
	//         left = Math.max(left, lastIndex[str.charAt(right)] + 1);
	//         res = Math.max(res, right - left + 1);
	//         lastIndex[str.charAt(right)] = right;
	//     }
	//     return res;
	// }

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
