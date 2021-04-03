package Utils;


public class TrieNode {

	//to-do --> can be eficently implemented by hashmap having key as character value
	public TrieNode[] nodeArray = new TrieNode[26] ; // array of array and not 2D array 

	public TrieNode() {
	    for (int i = 0 ; i<26 ; i++) nodeArray[i] = null ; 
	}
	
}
