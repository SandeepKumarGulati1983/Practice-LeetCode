package LeetCodeSolution;

public class GraphProblems {

	/*
	 * 997 - Find the town judge 
	 *  https://massivealgorithms.blogspot.com/2019/03/leetcode-997-find-town-judge.html 
	 *  Since town judge trusts nobody, can we say that
	 *  the point who has no out-degree and in-degree == N - 1 is the judge?
	 *  Time Complexity:
         Time O(T + N), space O(N)

	 */

	public int findJudge(int N, int[][] trust) {

		int[] count = new int[N+1];
		if (N == 1 ) return N;
		if (trust.length == 0) return -1 ;
		
		for (int[] eachTrust : trust) {
			
			count[eachTrust[0]]-- ; // inorder 
			count[eachTrust[1]]++ ;
		}
		
		for (int i = 0 ; i <=N ; i++) {
			if (count[i] == N-1) return i ;
		}
		return -1 ;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		GraphProblems ans = new GraphProblems();
		
		//997 
		//int[][]  trust = {{1,2}} ; int N = 2 ;
		//int[][]  trust = {{1,3},{2,3}} ;  int N = 3 ;//[[1,3],[2,3]];
		int[][]  trust = {{1,3},{2,3},{3,1}} ;  int N = 3 ;//[[1,3],[2,3],[3,1]]
		//int[][]  trust = {}; int N = 1 ; // expected 1 
		//int[][]  trust = {}; int N = 2 ; // expected -1
		System.out.println (ans.findJudge(N,trust));

	}

}
