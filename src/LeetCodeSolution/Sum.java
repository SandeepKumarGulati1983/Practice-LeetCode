package LeetCodeSolution;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sum {




	/*
	 * this will not work for leet code , because in leet code question , it want indices , which mean we can not sort , because sort will destroy the indices 
	 * 
	 * Its a modification of kaden's algo for finding the max sum in subarray 
	 * right pointer will move till the sum cross the target and then left pointer move , 
	 * in worst case  -- {1, 2, 3, ,4 , 5, , 6,7, 10 } - target - 17 
	 * right will move n time and then left will move n-1 time  so 0 (n-1)(n) = o (n^2) 
	 * so not better then hash table solution 
	 * 
	 * but if we start leftpointer from 0 and right pointer from end = array.length-1 then it will provide a solution in [O(n) + sorting complexity of O (nLogn) ]
	 * 
	 */

	public int[] twoSum1(int[] nums, int target) {


		ArrayList<Integer> list = new ArrayList<>();

		for (int i = 0; i < nums.length ; i++) {
			list.add(nums[i]);
		}
		Collections.sort(list);
		System.out.println(list);

		int leftIndex =0;
		int rightIndex=nums.length-1; // instead of rightIndex=1; lets start from last to avoid o(n2) case 

		while(leftIndex<rightIndex) {

			if ((list.get(leftIndex)+ list.get(rightIndex)) ==target) {
				return new int[] {leftIndex,rightIndex};
			}
			else if ((list.get(leftIndex)+ list.get(rightIndex)) >target) {
				rightIndex--;
			}else leftIndex++;
		}

		return new int[] {-1, -1};
	}

	// Solution 1 - two sum  - hash map  - O(n) - O(n)
	public int[] twoSum(int[] nums, int target) {

		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

		for(int i =0; i < nums.length ; i++ ){
			int remaningValue = target-nums[i];
			if (map.containsKey(remaningValue)){
				return new int[] {map.get(remaningValue), i};
			}else map.put(nums[i], i);
		}

		return new int[] {-1, -1};
	}
	/*
	 * now this question has not asked about indices so we can sort 
	 * [-1,0,1,2,-1,-4]
	 * 
	 * this solution is not providing the unique answer
	 * [[-1, 1, 0], [-1, 2, -1], [0, 1, -1], [1, -1, 0], [2, -1, -1], [2, -4, 2]]
Expected ans = [[-1,-1,2],[-1,0,1]]
	 */
	public List<List<Integer>> threeSum1(int[] nums) {
		int target = 0;

		List<List<Integer>> listOflist = new ArrayList<>();

		Map<Integer, Integer> map = new HashMap<>();
		map.put(nums[0], 0);
		for(int i = 0 ; i < nums.length-1; i ++){
			for(int j = i+1 ; j < nums.length; j ++){

				if (map.containsKey(target - (nums[i]+nums[j]))){
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[j]);
					list.add(target - (nums[i]+nums[j]));
					listOflist.add(list);
				}else map.put(nums[j], j);
			}

		}
		return listOflist;

	}

	public List<List<Integer>> threeSum2(int[] nums) {
		int target = 0;


		List<List<Integer>> listOflist = new ArrayList<>();
		Map<List<Integer>, Integer> soretedListMap = new HashMap<>(); 
		Map<Integer, Integer> map = new HashMap<>();
		map.put(nums[0], 0);


		for(int i = 0 ; i < nums.length-1; i ++){
			for(int j = i+1 ; j < nums.length; j ++){

				/*
				 * this is wrong logic as i am adding all element in hash map and after sum looking for third element 
				 * but hashmap can return an lement which we can already consider in first ans secod one 
				 */
				if (map.containsKey(target - (nums[i]+nums[j]))){
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[j]);
					list.add(target - (nums[i]+nums[j]));
					// now we have to remove duplicate entries in list 
					Collections.sort(list);
					soretedListMap.put(list,j);
					//-----------------------------------------------
				}else map.put(nums[j], j);
			}

		}
		for(List<Integer> l : soretedListMap.keySet()) {
			listOflist.add(l);
		}

		return listOflist;


	}

	/*
	 * not able to think of any algorithm for 3sum having time complexity lest then O(n^3)
	 * 
	 * solution at - https://www.geeksforgeeks.org/find-a-triplet-that-sum-to-a-given-value/
	 * 
	 * three solution 
	 * 1. O n3  - 3 nested for loop 
	 * 2. 0 n2  - slow and fast pointer 
	 * 3 - o n2 - with hash map  -- catch is we need to create a new hash map every time under first loop - means after fixing 1 element 
	 */


	public List<List<Integer>> threeSum3(int[] nums) {
		int target= 0;

		List<List<Integer>> listOfList = new ArrayList<>();

		Map<List<Integer>, Integer> map = new HashMap<>();

		// [-1,0,1,2,-1,-4]
		Arrays.sort(nums);
		//		System.out.print(Arrays.toString(nums));
		//[-4, -1, -1, 0, 1, 2]
		// a+b+c = target 
		for (int i = 0 ; i < nums.length-2; i ++) {

			// fix a here 
			int slowPointer = i+1;
			int fastPointer = nums.length-1;
			int expectedsum = target-nums[i];
			while  (slowPointer < fastPointer) {

				// implement slow and fast pointer approach for b and c 
				int actualSum = nums[slowPointer] + nums[fastPointer];

				if (actualSum == expectedsum) {
					List<Integer> list = new ArrayList<>();
					list.add(nums[i]);
					list.add(nums[slowPointer]);
					list.add(nums[fastPointer--]);

					// for removing duplicates 
					map.put(list,0);

				}else if (actualSum > expectedsum ) {
					fastPointer --;
				}else slowPointer++;

			}

		}

		for (List<Integer> l : map.keySet()) {
			listOfList.add(l);
		}

		return listOfList;


	}


	/*
	 * two problem in above solution 
	 * 
	 * 1. no need another list of array , we can simply add array as list 
	 * Arrays.asList(elements , elements, element);
	 * 
	 * 2. have not provide a way to take care of immediate duplicates. like -1,-1  or 1,1 
	 * 
	 * below code provide a way to remove dups without using HM  -- actually by skiping the dups values
	 * https://github.com/fishercoder1534/Leetcode/blob/master/src/main/java/com/fishercoder/solutions/_15.java  
	 */

	public List<List<Integer>> threeSum(int[] nums) {
		int target= 0;

		List<List<Integer>> listOfList = new ArrayList<>();


		Arrays.sort(nums);
		for (int i = 0 ; i < nums.length-2; i ++) {

			if (i >0 && nums[i-1] == nums[i]) {
				continue; // for skiping dupes 
			}
			// fix a here 
			int slowPointer = i+1;
			int fastPointer = nums.length-1;
			int expectedsum = target-nums[i];
			while  (slowPointer < fastPointer) {

				// implement slow and fast pointer approach for b and c 
				int actualSum = nums[slowPointer] + nums[fastPointer];
				if (actualSum == expectedsum) {			
					listOfList.add(Arrays.asList(nums[i], nums[slowPointer], nums[fastPointer]));
					// added this while to remove duplicacies in right values 

					while (slowPointer < fastPointer && nums[slowPointer] == nums[slowPointer + 1]) {
						slowPointer++;
					}
					while (slowPointer < fastPointer && nums[fastPointer] == nums[fastPointer - 1]) {
						fastPointer--;
					}
					slowPointer++;
					fastPointer--;
				}else if (actualSum > expectedsum ) {
					fastPointer --;
				}else slowPointer++;

			}

		}

		return listOfList;


	}

	/*
	 * On4 - soultion with 4 fpr loop 
	 * on3  solution with 3 loops 
	 * 
	 * 
	 */
	//	public List<List<Integer>> fourSum(int[] nums, int target) {
	//
	//		
	//	}

	// solution 26 
	// Brute force solution  -- sort and then compare 
	public int removeDuplicates1(int[] nums) {

		Arrays.sort(nums);
		int count=0;
		int i = 0;
		while ( i < nums.length-1- count){
			if (nums[i] == nums[i+1]){
				count++;
				int j = i+1;
				while ( j<nums.length-count){
					nums[j] = nums[j+1];
					j++;
				}
			}else i++;
		}
		System.out.println(Arrays.toString(nums));
		return nums.length - count;
	}

	/*
	 * above solution is taking too much of time and space  -- and consider array are already sorted 
	 * 
	 * best solution is O(n) with two pointer approch 
	 */

	public int removeDuplicates(int[] nums) {

		int p1 = 0 ;
		for (int p2 = 1 ; p2< nums.length; p2++) {

			if (nums[p1]!=nums[p2]) {
				p1++;
				nums[p1] = nums[p2]; // in this way we can loose first different no but that we can save in a variable if required 
			}
		}
		return p1+1;

	}

	//80. Remove Duplicates from Sorted Array II

	public int removeDuplicates2(int[] nums) {

		//given  a sorted array 
		int p1 = 0 ;
		int avoidSecondDupe =0;
		for (int p2 = 1 ; p2< nums.length; p2++) {


			if (nums[p1]!=nums[p2]) {
				avoidSecondDupe =0;
				p1++;
				nums[p1] = nums[p2]; // in this way we can loose first different no but that we can save in a variable if required 
			}else {
				avoidSecondDupe ++;
				if(avoidSecondDupe ==1) {
					p1++;
					nums[p1] = nums[p2];
				}
			}
		}
		System.out.println(Arrays.toString(nums));
		return p1+1;
	}

	// solution 1089  - duplicates zeros 

	public void duplicateZeros1(int[] arr) { // wrong solution 

		int i = 0 ;
		int temp1 = arr[i];
		int temp2 = arr[i+1];
		boolean lIntWasZero = false ;
		boolean zeroReplaced = true ;
		while(i < arr.length-2){

			if (temp1 == 0) {

				i++;
				if (zeroReplaced) { // one time only
					arr[i]= 0 ;
					zeroReplaced = false;
				}else {
					temp1 = arr[i];
					arr[i]= 0 ;
				}
				lIntWasZero = true ;

			}else { // {1,0,2,3,0,4,5,0}; // 110011 -> 110000
				i++;

				if (lIntWasZero) {
					temp2 = arr[i];
					arr[i] = temp1;
					temp1=temp2;
					lIntWasZero = false ;
				}else {

					temp1 = arr[i];
					arr[i] = temp2;

				}
			}



		}
		System.out.println(Arrays.toString(arr));


	}

	/*
	 * not able to code the right solution -- even brute force ... 
	 * 
	 * best solution is at geek for geek -- https://www.geeksforgeeks.org/insert-an-adjacent-k-in-place-for-every-occurrence-of-it-in-the-array-keeping-the-size-of-the-array-intact/
	 * 
	 */

	public void duplicateZeros(int[] arr) {


		int count = 0 ;
		for (int i : arr) {
			if (i == 0) {
				count++;
			}
		}

		int imaginarySize  = arr.length+ count-1;
		int actualSize  = arr.length-1;

		while (actualSize>=0 && imaginarySize >=0) {



			//			if (imaginarySize < arr.length) {
			//				arr[imaginarySize] = arr[actualSize]; 
			//			}
			//
			//			--imaginarySize;
			//
			//			if (arr[actualSize] == 0) 
			//			{ 
			//				if (imaginarySize < arr.length) 
			//					arr[imaginarySize] = 0; 
			//
			//				--imaginarySize;
			//			} 
			//			--actualSize; 

			//			if (imaginarySize < arr.length) {
			//				arr[imaginarySize] = arr[actualSize]; 
			//			}
			//
			////			--imaginarySize;

			if (arr[actualSize]==0) {
				if (imaginarySize < arr.length) {
					arr[imaginarySize --] =0;
					arr[imaginarySize --]=0;
				}else {
					imaginarySize --;
					if (imaginarySize < arr.length) {
						arr[imaginarySize --]=0;
					}else imaginarySize --;
				}
			}else {

				if (imaginarySize < arr.length) { 
					arr[imaginarySize--] = arr[actualSize];
				}else imaginarySize --;
			}
			actualSize--;

		}

		System.out.println(Arrays.toString(arr));


	}

	//217. Contains Duplicate
	// O nlog N  - because of soorting an array 
	public boolean containsDuplicate1(int[] nums) {

		if (nums.length <=1){
			return false;
		}
		Arrays.sort(nums);
		for (int i = 0 ; i < nums.length ; i ++) {

			if (nums[i] == nums[i=1]) return true;

		}
		return false;

	}

	// hash map approch for achieving O n complexity 
	public boolean containsDuplicate(int[] nums) {

		HashMap<Integer, Integer> map  = new HashMap<>();
		for (int n : nums ) {
			if (map.containsKey(n)) return true;
			map.put(n, 0);
		}
		return false ;
	}


	//219. Contains Duplicate II  -- nums[i]== nums[j] and j-i = k 

	public boolean containsNearbyDuplicate(int[] nums, int k) {
		// can't sort because we have to work on indices 
		HashMap<Integer, Integer> map = new HashMap<>();

		if (nums.length <=1) return false ;
		for (int i =0 ; i < nums.length ; i++) {
			if (map.containsKey(nums[i]) && i-map.get(nums[i]) <= k) {
				return true;

			}else map.put(nums[i], i);
		}

		return false ;


	}

	//	287. Find the Duplicate Number 
	public int findDuplicate1(int[] nums) {

		// its a mathmatical question - dup = Sum of N natural number - n*n-1/2
		//another constrain -- All the integers in nums appear only once except for precisely one integer which appears two or more times.

		int n = nums.length - 1;
		int sum = (n*(n+1))/2 ;
		int actualSum = 0;
		for (int i :nums){
			actualSum +=i;
		}


		return  actualSum-sum ;
	}

	// mathmatical  way  will not works for thia problem because a digit can appear moore than one  
	public int findDuplicate2(int[] nums) {
		// oen way is sorting  -- o o n log n   -- can't ort as we are not allow to modify the array .. and don't want to take another array 
		// another way is hashing -- o n 

		//only O n solution is fast and slow pointer approch 

		int slow = 0;
		int fast = 2 ;
		while (slow < nums.length) {
			if (fast > nums.length-1) fast = 0 ;
			if (nums[slow] == nums[fast]) {
				return nums[slow];	
			}else {
				slow++ ; fast +=2 ;
			}
		}
		return nums[slow];
	}  // wrong approch 

	// after sorting and Hash only solutionis floy'd algo of tortoise and hare 

	public int findDuplicate(int[] nums) {

		// one while loop to find the cycle intersection 
		// hare speed is twice of tortoise 
		int tortoise = nums[0];
		int hare  = nums[0];
		do {
			tortoise = nums[tortoise];
			hare = nums[nums[hare]];
		} while (tortoise != hare);

		// second while loop to find the duplicate 
		// hare speed is equal to tortoise 
		tortoise = nums[0];
		while (tortoise != hare) {
			tortoise = nums[tortoise];
			hare = nums[hare];
		}

		return hare;
	}


	// 442. Find All Duplicates in an Array
	// hash map solution for On 
	public List<Integer> findDuplicates1(int[] nums) {

		HashMap<Integer, Integer> map  = new HashMap<>();
		List<Integer> result = new ArrayList<>();

		if (nums.length <=1) {
			//result.add(-1);
			return result;
		}

		for (int i = 0 ; i < nums.length ; i ++) {

			if (map.containsKey(nums[i])) {
				result.add (nums[i]);
			}else map.put (nums[i], i);
		}
		return result;

	}

	// another better solution is to make frequency array ... that take less space then hash map 

	public List<Integer> findDuplicates2(int[] nums) {

		List<Integer> result = new ArrayList<>();
		int[] fArray = new int[nums.length+1 ]; // +1 because arr[i] <= size of array  and they will start from 1 

		for (int value : nums) {
			fArray[value] ++ ;
		}

		for (int i = 0 ; i < fArray.length ; i ++) {
			if (fArray[i] == 2 ) result.add(i);
		}

		return result;

	}


	// still its take a space of ON 
	// so the best solution is to visit each index value as new index and check weather we are visited again or only once 
	// only in case of duplicate value 1233 , we will visit an index (like 3) twice 

	public List<Integer> findDuplicates(int[] nums) {  // best solution ******

		List<Integer> result = new ArrayList<>();

		for (int i = 0 ; i < nums.length ; i ++ ) {

			int value = Math.abs(nums[i]); // take example if we visit first index 0 , value 4 , then we already made that value -7 , now if we will visit index = 4 , it will provide a value in -ve , and give an impression that its already visited 

			if (nums[value-1] < 0) {
				result.add (value);
			}else {
				nums[value-1] *= -1;
			}

		}

		return result;

	}

	//27. Remove Element -- two pointer approch 

	public int removeElement1(int[] nums, int val) {

		int p1 = 0; 
		int count = 0;


		while (p1<nums.length) {
			if (nums[p1] == val) {
				if (p1 != nums.length-1) {
					nums[p1] = nums[p1+1]; 
				}
				p1++;
				count++;
			}else p1++ ;
		}


		return nums.length -count;


	}

	// it required 2 pointer , but not in a way shoown above ... 
	// keep oone pointer at start and another at end adn keep swaping the no 
	public int removeElement(int[] nums, int val) {

		int sp = 0 ;
		int ep = nums.length -1 ;
		int count = 0 ;

		while (sp <= ep) {

			if (nums[sp] != val) {
				sp++;
			}else if (nums[ep] == val) {
				ep--;
				count++;
			}else {
				nums[sp++] = nums[ep--];
				count++;
			}


		}
		System.out.println(Arrays.toString(nums));
		return  nums.length - count;
	}

	//1619. Mean of Array After Removing Some Elements
	public double trimMean(int[] arr) {


		int minIndex ;
		minIndex  = arr.length /20 ;
		Arrays.sort(arr);
		double sum = 0;
		//System.out.println(40/20);
		for(int i = minIndex ; i < arr.length - minIndex ; i++ ) {
			sum += arr[i];
		}

		return sum/(arr.length-(2*minIndex )) ; 

	}

	//1233. Remove Sub-Folders from the Filesystem

	public List<String> removeSubfolders(String[] folder) {

		List<String> result = new ArrayList<>();
		
		Arrays.sort(folder);
		
		for (String dir : folder) {
			
			if (result.isEmpty() || !dir.startsWith(result.get(result.size()-1)+"/")) {
				result.add(dir);
			}
		}
		
		return result;
		
	}

	
	
	public static void main (String[] args) { 

		Sum sum = new Sum();

		//1233. Remove Sub-Folders from the Filesystem

		String[] a = {"/a","/a/b","/c/d","/c/d/e","/c/f"};
		System.out.println (sum.removeSubfolders(a));

		//1619. Mean of Array After Removing Some Elements
		//		int []  a = {1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3}; 
		//		System.out.println(sum.trimMean(a));

		//27. Remove Element 
		//		int []  a = {0,1,2,2,3,0,4,2}; int value = 2 ;
		//		int []  a = {0,1,2,2,3,0,4,2}; int value = 2 ;
		//		int [] a = {1} ; int value = 1 ;
		//		//		int []  a = {3,2,2,3}; int value = 3 ;
		//		System.out.println(sum.removeElement(a,value));

		// 442. Find All Duplicates in an Array
		//		int []  a = {4,3,2,7,8,2,3,1};
		//		System.out.println(sum.findDuplicates(a));
		// 4,3,2,7,8,2,3,1



		//287. Find the Duplicate Number 
		//int []  a = {2,2,2,2,2}; // 10-10
		//		int []  a = {1,2,3,4,1};
		//		int []  a = {4,3,1,4,2};

		//  11111  - 5 
		//  11234  -  11    - 10+1 
		//  11123  - 8 
		//  11114   - 8 
		//  - any combination with more than 2 one 
		//  22222 - 10
		//  22224 - 13 
		//  33333  - 15
		//  44444  - 20

		// 

		// actual  - 1234_ --> 10 +  


		//  12234  -  12 - 10+2 
		//  12334 - 13  - 10+3 
		//  12344 - 14  - 10 + 4 


		//		System.out.println(sum.findDuplicate(a));

		//219. Contains Duplicate II
		//		int[] a = {1,2,3,1}; int k = 3 ; // true 
		//		int[] b = {1,0,1,1} ; int k1 = 1; // true 
		//		int [] c = {-1,-1} ;  int k2 = 1 ; // true 
		//		int [] d = {-1,-1} ;  int k3 = 2 ; // flase 
		//		System.out.println(sum.containsNearbyDuplicate(d,k3));

		//217. Contains Duplicate
		//		int[] a = {1,2,3,1};
		//		System.out.println(sum.containsDuplicate(a));


		// solution 1089  - duplicates zeros 
		//		int[] a = {1,0,2,3,0,4,5,0};
		//		int[] a = {8,4,5,0,0,0,0,7};		
		//		sum.duplicateZeros(a);
		//System.out.println();

		// solution 80 - remove duplicate 2 
		//		int [] a = {1,1,1,1,2,2,3}; // expected - Output: 5, nums = [1,1,2,2,3]
		//		int[] b = {0,0,1,1,1,1,2,3,3}; // Output: 7, nums = [0,0,1,1,2,3,3]
		//		System.out.println(sum.removeDuplicates2(b));

		//solution 26 - remove duplicates 
		//				int[] a = {1,1,2};
		//				int[] a = {1,1,1,0}; // expected {0,1}
		//		int[] a = {1,2,3,4};
		//				int [] a = {1,1,1,1,1,1};
		//				System.out.println(sum.removeDuplicates(a));

		//		int[] a = {3,2,4,7};
		//		int[] a = {2,5,5,11};
		//		int[] a = {3,2,4};
		//		int target = 6 ;

		// solution 3 sum 
		//		int[] b = {-1,0,1,2,-1,-4}; // expected ans is - 
		//				int[] b = {0,0,0,0}; // expected ans is single set of [0,0,0]
		//		int[] b = {-2,0,1,1,2};//[-2,0,1,1,2]  / expected - [[-2,0,2],[-2,1,1]]
		//		System.out.println(sum.threeSum(b));

		//		int[] b = {1,0,-1,0,-2,2};
		//		System.out.println(sum.fourSum(b, 0));

		//solution - two sum 
		//		System.out.println(Arrays.toString(sum.twoSum(a,target)));






	}


}
