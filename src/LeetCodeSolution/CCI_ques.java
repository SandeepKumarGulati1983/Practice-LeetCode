package LeetCodeSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CCI_ques {

	/*
	 * CTCI - Arrays and Strings		
	1. Rotate Image		
	2. Set Matrix Zeroes		
	3. One Edit Distance		
	4. Palindrome Permutation		
	5. is uniqe 
	6. String Compression		
	check permutation 		https://leetcode.com/problems/permutation-in-string/
	Rotate string 		https://leetcode.com/problems/rotate-string/
	 */

	/*
	 * hash map  -- S- O n 
	 * character array or bit vector  - for less space 
	 * 
	 * only lowercase  a - z 
	 */

	// 387. First Unique Character in a String

	public int firstUniqChar_WRONG_SOLUTION(String s) { 

		int checker = 0; 
		//if (s.length() >=26 )

		for (int i = 0 ; i < s.length() ; i ++) {
			int pos = s.charAt(i)-'a';
			if ((checker & (1<<pos)) == 1 ) checker &= (0<<pos) ;
			checker |=  (1<<pos) ;
		}

		for (int i = 0 ; i < s.length() ; i ++) {
			int pos = s.charAt(i)-'a';
			if ((checker & (1<<pos)) == 1 ) return i;
			// if (((checker >> pos) & 1 ) == 0 ) return i;
		}

		return -1; 
		/*
		 * this will not be able to handle case of "eee" odd number of  duplicates  , because 1st 
		 * e will set the bit to 1 
		 * 2nd e -  will unset and 3rd will again set  -- so it will look like a non repetitive char
		 * 
		 * Bit vector should use only in case of true and false 
		 */
	}
	public int firstUniqChar(String s) { 
		int[] fArray = new int[26];
		for (int i = 0 ; i < s.length() ; i ++) {
			int index  = (int)s.charAt(i)-'a';
			fArray[index] ++ ;
		}
		for (int i = 0 ; i < s.length() ; i ++) {
			int index  = (int)s.charAt(i)-'a';
			if (fArray[index]==1) return i;
		}

		return -1 ;

	}

	// best solution given on leet code 
	class Solution {
		public int firstUniqChar(String s) {
			if(s==null || s.length()==0) return -1;
			int min=Integer.MAX_VALUE;
			for(char c='a';c<='z';c++){
				int index=s.indexOf(c);
				if(index!=-1 && index==s.lastIndexOf(c))
					min=Math.min(min,index);
			}
			return min==Integer.MAX_VALUE?-1:min;
		}
	}


	//567. Permutation in String
	public boolean checkInclusion_too_SLOW (String s1, String s2) {

		if (s2.length() <s1.length()) return false ;
		if (s1 == null || s2 == null ) return false ;
		//abc , ab  -- s2 > s1 
		// null , null  -- false 
		// null , ab  -- false 
		// ab , null  -- false 

		int subStringLength =  s1.length();

		//abc , cbad --> cba , bad  --> true  -- sort 
		char[] s1CharArray = s1.toCharArray();
		Arrays.sort(s1CharArray);
		s1 = new String(s1CharArray);
		for (int endIndex = subStringLength  ; endIndex <= s2.length() ; endIndex++) {
			char[] subStringArray = s2.substring(endIndex-subStringLength, endIndex).toCharArray();
			Arrays.sort(subStringArray);
			String SortedSubString = new String(subStringArray);
			if (s1.equals(SortedSubString)) return true;

		}
		return false ;


	}

	// can be easily handled by frequency array 

	public boolean checkInclusion_WRONG(String s1, String s2) {

		if (s2.length() <s1.length()) return false ;
		if (s1 == null || s2 == null ) return false ;

		int[] count =  new int[128]; //Q: ASCAI or UNICODE   

		for (int i = 0 ; i < s2.length(); i ++) {

			int index = s2.charAt(i);
			count[index]++ ;
		}

		for (int i = 0 ; i < s1.length() ; i++) {
			int index = s1.charAt(i);
			count[index]--;
			if (count[index] <0) return false ;
		}
		return true ;
		/*
		 * not able to handle this case - "ab" "eidboaoo" -- false 
		 */

	}

	public boolean checkInclusion_StillSlow (String s1, String s2) {

		if (s2.length() <s1.length()) return false ;
		if (s1 == null || s2 == null ) return false ;

		int[] count =  new int[26 ]; //Q: ASCAII or UNICODE  , ASCAII and Lowercase  

		for (int i = 0 ; i < s1.length(); i ++) {

			int index = s1.charAt(i) - 'a';
			count[index]++ ;
		}

		for (int endIndex = s1.length()  ; endIndex <= s2.length() ; endIndex++) {
			char[] subStringArray = s2.substring(endIndex-s1.length(), endIndex).toCharArray();

			int[] cCount = count.clone(); // imp point 
			int counter = 0;
			for (char c : subStringArray) {
				counter++;
				int index = (int) c - 'a';
				cCount[index]-- ;
				if (cCount[index] <0)break ;
				if (counter== s1.length())return true;
			}	

		}
		return false  ;


	}

	public boolean checkInclusion_wrong (String s1, String s2) { // not able to handle "hello", "ooolleoooleh"

		if (s2.length() <s1.length()) return false ;
		if (s1 == null || s2 == null ) return false ;

		int[] count =  new int[26 ]; //Q: ASCAII or UNICODE  , ASCAII and Lowercase  

		for (int i = 0 ; i < s1.length(); i ++) {

			int index = s1.charAt(i) - 'a';
			count[index]++ ;
		}

		for (int endIndex = s1.length()  ; endIndex <= s2.length() ; endIndex++) {
			char[] subStringArray = s2.substring(endIndex-s1.length(), endIndex).toCharArray();


			int counter = 0;
			for (char c : subStringArray) {
				counter++;
				int index = (int) c - 'a';

				if (count[index]-1 <0) {

					break ;
				}

				if (counter== s1.length())return true;
			}	
		}
		return false  ;
	}

	// CCI - 1.3 
	public String urLify_need_improvment (String s , int trueLength) {

		char[] input = s.toCharArray();
		int outputSize = (input.length- trueLength)*3 ; 
		char[] output = new char[input.length+outputSize];
		int i = 0 ;
		for (char c : input) {
			if (c != ' ') {
				output[i++] = c ;
			}else if (i<trueLength){
				output[i++] = '%';
				output[i++] = '2';
				output[i++] = '0';
			}
		}


		return new String(output);

	}

	public String urLify(String s , int trueLength) {

		char[] input = s.toCharArray();
		int outputSize = (input.length- trueLength)*3 ; 
		char[] output = new char[input.length+outputSize];

		int j = 0;
		for (int i = 0 ; i < trueLength ; i++) {
			char c = s.charAt(i);
			if (c != ' ') {
				output[j++] = c ;
			}else {
				output[j++ ] = '%';
				output[j++] = '2';
				output[j++] = '0';
			}
		}



		return new String(output);

	}

	// CCI - 1.4  -- palindrom permutation 

	boolean palindromPermutation_notBest(String s) {

		// if total character n-1 have double occurence then its a palindrom . ex tat 

		char[] count = new char[26];

		//s.replaceAll(" ", "");
		char[] cArray = s.toCharArray();

		int trueLength = 0 ;
		int totalChar =0;
		for (char c : cArray) {
			if (c != ' ') {
				count[Character.toLowerCase(c)-'a']++;
				trueLength ++;
			}

		}

		for (int v : count) {
			if (v >0)totalChar++ ;
		}

		int i = 0;
		if (totalChar == Math.abs(trueLength/2) ) {

			int alldouble = 0;
			for (int j = 0 ; j <count.length ; j ++ ) {
				if (count[i]%2 == 0) alldouble ++ ;
			}
			return (totalChar-alldouble>1 )? false  : true  ;

		}else return false ;

		/*
		 * what we need is that weather total no of characer are appearing twice or not 
		 * so take an int array and flip the bit , if in end we get only one set bit then thats means True 
		 */

	}
	boolean palindromPermutation(String s) {

		//we first need to flip the bit 


		int checker = 0 ;
		int index = 0;
		for (char c : s.toCharArray()) {

			if (c != ' ') { // for avoiding space 
				index = Character.toLowerCase(c) -'a';
				int mask = (1<<index);
				if ((checker & mask) == 0) { // bit is 0
					checker |= mask ;
				}else checker &= ~mask;

			}

		}
		/*
		 * then need to check that only one bit is set 
		 * 
		 * which means if checker and -checker == 0 thats means only one bit is set 
		 */

		return ((checker & (checker-1)) == 0) ? true : false ;

	}

	// CCI -- 1.5 -- one edit away 

	boolean oneEditAway(String s1 , String s2) {
		int editCount = 0 ;
		if (s1.length() == s2.length()) {

			for (int i = 0 ; i < s2.length(); i ++) {
				if (s1.charAt(i) != s2.charAt(i)) editCount++ ;
			}

		}else if (s1.length() == s2.length()-1) {
			int p1 = 0; 
			int p2 = 0 ; 
			while(p1 < s1.length() && p2 < s2.length()) {
				if (s1.charAt(p1) == s2.charAt(p2)) {
					p1++;
					p2++;
				}else {
					editCount++ ;
					p2++;
				}
			}


		}
		return (editCount==1)? true : false ;

	}
	
	//----------------------------day 2 ----------------------------------------
	
	/*
	 * String compression 
	 *  input  - aabcccccaaa
	 *  output  - a2blc5a3
	 */

	
	String strCompression(String inputStr) {
		
		char[] cArray = inputStr.toCharArray();
		
		char[] alpha = new char[inputStr.length()];
		int[] num = new int[inputStr.length()];
		
		int i = 0;
		for (char c : inputStr.toCharArray()) {
			
			if(i > 0 && c == alpha[i-1]) {
				num[i-1]++;
			}else {
				alpha[i] = c;
				num[i]++ ;
			}
			i++;
		}
		
		StringBuffer output = new StringBuffer();
		for (int j = i ; j < i ; j ++) {
			output.append(alpha[i]);
			output.append(num[i]);
		}
		
		return output.toString();
	}
	
	
	
	public static void main(String[] args) {

		CCI_ques className = new CCI_ques();

		// 387. First Unique Character in a String
		//		System.out.println(className.firstUniqChar("sandeep"));
		//		System.out.println(className.firstUniqChar("leetcode"));
		//		System.out.println(className.firstUniqChar("loveleetcode"));
		//		System.out.println(className.firstUniqChar("aabb"));
		//		System.out.println(className.firstUniqChar("eee"));

		//567. Permutation in String
		//System.out.println(className.checkInclusion("ab", "aidbaooo"));
		//		System.out.println(className.checkInclusion("adc", "dcda")); // true 
		//		System.out.println(className.checkInclusion("ab", "eidbaooo")); // true 
		//		System.out.println(className.checkInclusion("ab", "eidboaoo"));  // false 
		//System.out.println(className.checkInclusion("hello", "ooolleoooleh"));  // false

		// URLify
		//System.out.println(className.urLify("Mr John Smith     ", 13)); //"Mr%20John%20Smith"

		// CCI - 1.4  -- palindrom permutation  - A palindrome is a word or phrase that is the same forwards and backwards
		//System.out.println(className.palindromPermutation("Tact Coa")); // true 
		//System.out.println(className.palindromPermutation("geek for geek r"));
		
		//CCI 1.5 
		//System.out.println(className.oneEditAway("sandeep", "sandeep"));
		//System.out.println(className.oneEditAway("sandeep", "sandeed"));
		//System.out.println(className.oneEditAway("sanded", "sandeed"));
		
		// string compression 
		System.out.println(className.strCompression("aabcccccaaa"));
		
	}

}
