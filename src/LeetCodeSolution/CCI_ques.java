package LeetCodeSolution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class CCI_ques {
	CCI_ques(){

	}

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


	String strCompression_NoNeedOf2Arrays(String inputStr) {

		char[] cArray = inputStr.toCharArray();

		char[] alpha = new char[inputStr.length()];
		int[] num = new int[inputStr.length()];

		int i = 0;
		int lastindex = 0;
		for (char c : inputStr.toCharArray()) {

			if(lastindex > 0 && c == alpha[lastindex-1]) {
				num[lastindex-1]++;
			}else {

				alpha[lastindex] = c;
				num[lastindex]++ ;
				lastindex++;
			}
			i++;
		}

		StringBuilder output = new StringBuilder();
		for (int j = 0 ; j < i ; j ++) {
			if (alpha[j] !=0) {
				output.append(alpha[j]);
				output.append(num[j]);
			}
		}

		return output.toString();
	}

	String strCompression(String iStr) {

		StringBuilder oStr = new StringBuilder();

		int count = 0 ; 

		for (int i = 0 ; i < iStr.length() ; i ++) {

			count++;

			if (i+1 >= iStr.length() || iStr.charAt(i) != iStr.charAt(i+1)) {
				oStr.append(iStr.charAt(i));
				oStr.append(count);
				count = 0 ;
			}
		}

		return oStr.toString();

	}
	//Leet code - 443. String Compression

	public int compress(char[] chars) {

		// aaaaabbbbb
		int count = 0;
		int newArrayIndex = 0 ;
		for (int i = 0 ; i < chars.length; i ++) {
			count++;
			if (i+1 >= chars.length ||chars[i] != chars[i+1]) {
				chars[newArrayIndex++] = chars[i] ;
				if (count >1 ) {
					char[] digit = Integer.toString(count).toCharArray();
					if (digit.length>1) {
						for (char c : digit) {
							chars[newArrayIndex++] = c;
						}
					}else {
						chars[newArrayIndex++] = digit[0];
					}
				}
				count=0;
			}
		}


		return newArrayIndex;
	}

	public void rotate_WRONG(int[][] matrix) {


		int n = matrix.length;
		int[][] rotatedmatrix = new int[n][n];
		int rowCount = 0;
		for (int[] row : matrix) {

			// convert 1st row in to last column 
			for (int rowItem : row) {
				rotatedmatrix[rowCount][n-1] = rowItem;
			}
			rowCount++;

		}
		/*
		 * need to do 'in-memory' , means rotate the actual matrix 
		 */
	}

	public void rotate_Wrong(int[][] matrix) {


		/*
		 * with pen and pencil cn be solved easily 
		 */

		for (int layer = 0 ; layer <matrix.length/2 ;layer ++) {

			// first = layer 
			// last = n-1 -layer 
			// offset = i - first  = i - layer 
			//right = last , last-offset  -->
			//bottom = last-offset , first 
			// left = first , i 
			for (int i = layer ; i < matrix.length-layer-1; i++) {

				int top = matrix[layer][i-layer];
				int right = matrix[matrix.length-layer-1][matrix.length-1-i];
				int bottom = matrix[matrix.length-1-i][layer];
				int left = matrix [layer][i];

				int temp = matrix[layer][i-layer];
				matrix[layer][i-layer] = matrix [layer][i] ; 
				matrix [layer][i] = matrix[matrix.length-1-i][layer] ;
				matrix[matrix.length-1-i][layer] = matrix[matrix.length-layer-1][matrix.length-1-i];
				matrix[matrix.length-layer-1][matrix.length-1-i] = temp;	



			}

		}

	}
	//73. Set Matrix Zeroes
	public void setZeroes(int[][] matrix) {

		// how to male row and column with int 


		int n = matrix.length;
		int[] zeroPlacesArray = new int[n*n];

		// save zero places in an array 
		int zeroArrayMIndex = 0;
		for (int i = 0 ; i < matrix.length ; i++) {
			for (int j = 0 ; j < matrix.length ; j++) {
				if (matrix[i][j] == 0) {
					zeroPlacesArray[zeroArrayMIndex++]=i*n+j;	
				}
			}
		}

		int index = 0 ;
		while (index < zeroArrayMIndex) {
			int row = zeroPlacesArray[index] /n ;
			int column = zeroPlacesArray[index] % n ;

			for (int i = 0 ; i <n ; i++) {
				matrix[row][i]	=0;
				matrix[i][column]	=0;
			}
			index++;
		}

		for (int i = 0 ; i < matrix.length ; i++) {
			for (int j = 0 ; j < matrix.length ; j++) {
				System.out.println(matrix[i][j]) ;
			}
		}


	}
	//796. Rotate String

	public boolean rotateString(String A, String B) { // sandeep , deepsan


		if (A.length() != B.length()) return false ;

		int Aindex = 0;
		String subString ;
		for (int i = 0; i < A.length() ; i ++) {

			if (A.charAt(Aindex) == B.charAt(i)) {
				Aindex++;
			}else {
				i = i -Aindex ;
				Aindex = 0;
				System.out.println(i);
			}

		}

		String newString = B.substring(0,B.length() -Aindex);
		System.out.println(newString);
		for (int i = 0 ;  i< newString.length() ; i++) {
			if (A.charAt(Aindex++) != newString.charAt(i)) {
				return false ; 
			}
		}
		return true;
	}



	//=================================day 3 ================================

	// remove dups from unsorted linked list 

	class LinkNode{
		LinkNode next;
		int val ;

		LinkNode(){}
		LinkNode(int v){ this.val = v;}
		//LinkNode(int v, LinkNode node){this.val = v ; this.next = node ;}

	}


	LinkNode rootNode ; 
	LinkNode  insert(int value) {

		LinkNode head ;

		if (rootNode != null ) {
			head = rootNode;
			while(head.next != null) {
				head = head.next;
			}
			head.next = new LinkNode(value);
		}else rootNode = new LinkNode(value);
		return rootNode;
	}
	//CCI - 2.1  -- remove dups ,, if temporary buffer are not allowed 
	LinkNode removeDups() {

		// O n approaches are hashmap or array or frequency table 
		// without buffer  --  O n2 

		LinkNode p1 = rootNode  ;
		LinkNode p2 ;

		while (p1 != null && p1.next != null) {
			p2 = p1 ;
			while (p2 != null && p2.next != null ){

				if (p1.val == p2.next.val) {
					p1.next = p2 ;
					p2.next = p2.next.next;
				}else 
					p2 = p2.next ;
			}

			p1 = p1.next;

		}
		return p1;

	}

	//CCI --  2.2  Return Kth to Last

	int kthTolast_recursive(LinkNode n , int k) {

		if (n == null ) return 0;

		int index = kthTolast_recursive(n.next, k ) + 1 ;
		if (index == k ) System.out.println(n.val);

		return index ;
	}

	void kthTolast_iterative(LinkNode n , int k) {


		LinkNode p1 = n;
		LinkNode p2 =n; 

		while (k >0 && p2 != null) {
			p2 = p2.next;
			k--;
		}

		while (p2 != null) {
			p2 = p2.next;
			p1 = p1.next ;
		}

		System.out.println(p1.val);

	}

	// cci 2.4 -- partition 

	LinkNode partition_havingSpaceComplexity (int p) {
		LinkNode left =null;
		LinkNode right = null ;
		LinkNode leftHead = left;
		LinkNode rightHead = right;

		LinkNode head = rootNode;
		// boundry conditions 
		if (head == null || head.next == null) return head ;

		while (head != null) {

			LinkNode next = head.next;
			head.next = null ;
			if (head.val < p) {
				if (left == null ) {
					left = head ;
					leftHead = left ;
				}else {
					left.next = head;
					left = left.next;
				}
			}else {
				if (right == null ) {
					right = head ;
					rightHead = right ;
				}else {
					right.next = head;
					right = right .next ;
				}
			}
			head = next ;
		}
		// boundry conditions 
		if (left != null ){
			left.next = rightHead;
			return leftHead ;
		}else return rightHead;

		/*
		 * above solution is having the space complexity of O(N)  with Time complexity also of  O(N)
		 */

	}


	public LinkNode partition(LinkNode head, int x) {

		if (head == null || head.next == null) return head ;
		
		
		LinkNode  lastNode = null;
		LinkNode firstnode =head;
		LinkNode  parent = head ;
		//LinkNode  root = head ;
		
		int count =0;
		while (head != null ) {
			count++;
			lastNode = head ;
			head = head.next;
		}
		
		
		while (count !=0) {
			count--;
			LinkNode next = parent.next;
			
			if (parent.val>x) {
				firstnode   = parent.next ;
				parent.next = null ;
				lastNode.next  = parent;
				lastNode = lastNode.next ;
				parent = next ;
				
			}else if (next != null && next.val >x){
				parent.next = next.next ;
				parent  =parent.next;
				
				next.next = null;
				lastNode.next  = next;
				lastNode = lastNode.next ;
				
			}else {
				parent = parent.next;
			}
			
			
		}
		
		return firstnode;
		
		
	}

	public static void main(String[] args) {

		CCI_ques className = new CCI_ques();

		// remove dups from unsorted linked list 
		className.insert(3);
		className.insert(5);
		className.insert(8);
		className.insert(5);
		className.insert(10);
		className.insert(2);
		LinkNode ll = className.insert(1);
		LinkNode list = className.partition(ll,5);

		while (list != null) {
			System.out.println(list.val);
			list = list.next;
		}

		//className.kthTolast_iterative(ll, 2 ) ;
		//className.kthTolast(ll, 3);
		//className.removeDups();

		//		while (ll != null) {
		//			System.out.println(ll.val);
		//			ll = ll.next;
		//		}

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
		//System.out.println(className.strCompression("aabcccccaaa"));

		//String s = "aabcccccaaa";
		//		String s = "aabbbbbbbbbbbb";
		//		char[] input = s.toCharArray();
		//		System.out.println(className.compress(input));

		//int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
		//		int[][] matrix = {{1,1,1},{1,0,1},{1,1,1}};
		//		className.setZeroes(matrix); // Output: [[1,0,1],[0,0,0],[1,0,1]]
		//		
		//		System.out.println(className.rotateString("sandeep", "deepsan")); // true 
		//		System.out.println(className.rotateString("abcde", "cdeab")); //  true 
		//		System.out.println(className.rotateString("abcde", "abced")); // false
		//		System.out.println(className.rotateString("a", "ab"));
		//		System.out.println(className.rotateString("", ""));
		//		System.out.println(className.rotateString("", "a"));
		//		System.out.println(className.rotateString("bbbacddceeb", "ceebbbbacdd")); // true


	}

}
