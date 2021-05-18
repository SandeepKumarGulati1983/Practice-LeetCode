package LeetCodeSolution;

public class google_recursion_prac {

	//212. Word Search II  -- pseducode via backtrack
	 public List<String> findWords(char[][] board, String[] words) {
	        
	        
	        backtrack (oList , previousString , tempCharacter, board , words , r+1, c )
	        backtrack (oList , previousString , tempCharacter, board , words , r, c+1 )
	        
	            return oList;
	    }
	    
	    backtrack(-----){
	        
	        if (r>board.length || c> board.length) return 
	        
	        previousString.append ( copy of tempCharacter)
	            for (String word : words){
	                if (word.equals(oList.get(oList.size()-1)))
	                    oList.add 
	                    
	        
	        backtrack -- r+1,c
	        backtrack -- r, c+1
	        
	    }
	
}
