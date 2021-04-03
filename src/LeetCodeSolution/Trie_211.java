package LeetCodeSolution;

import java.util.HashMap;

public class Trie_211 {


	Trie_211  root ;
	Trie_211[] rootArray ;
	boolean isEnd ;
	int alphabetLenth = 26;

	/** Initialize your data structure here. */
	public Trie_211() {

		rootArray = new Trie_211[alphabetLenth];
		for (int i = 0 ; i<26 ; i++) rootArray[i] = null ; 
		isEnd = false ;
	}

	/** Inserts a word into the trie. */
	public void addWord(String word) {
		if (root == null ) root = new Trie_211();
		Trie_211 head = root ;
		if (word == null) return ;
		word = word.toLowerCase(); // for use case 2 in which T is capital 
		for (int i = 0 ;i < word.length() ; i ++) {
			int index = (int)word.charAt(i) - 'a';
			//System.out.println("index = " + index );
			if (head.rootArray[index] ==null) head.rootArray[index] = new Trie_211();
			head = head.rootArray[index] ;

		}
		head.isEnd = true;

	}

	public boolean search(String word) {
		if (root == null) return false ;
		
		return searchDot(word, root);
	}
	public boolean searchDot(String word , Trie_211 node) {
		
		Trie_211 head = node ;
		word = word.toLowerCase(); 
		for (int i = 0 ;i < word.length() ; i ++) {
			// if '.ad'
			if (word.charAt(i) == '.') {
				for (int j = 0 ; j < 26 ; j ++) {
					if (head.rootArray[j] != null) {
						return searchDot (word.substring(i+1), head.rootArray[j]);
					}
				}
				return false ;
				
			}else {
				int index = (int)word.charAt(i) - 'a';
				if (head.rootArray[index] == null ) return false ;
				head = head.rootArray[index] ;
			}
		}
		return (head.isEnd == true )? true : false  ;
		
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// use case 1 
		Trie_211 wordDictionary = new Trie_211();
//		wordDictionary.addWord("bad");
//		wordDictionary.addWord("dad");
//		wordDictionary.addWord("mad");
//		System.out.println(wordDictionary.search("pad")); // return False
//		System.out.println(wordDictionary.search("bad")); // return True
//		System.out.println(wordDictionary.search(".ad")); // return True
//		System.out.println(wordDictionary.search("b..")); // return True
		wordDictionary.addWord("bat");
		//wordDictionary.addWord("a");
		
//		System.out.println(wordDictionary.search(".")); // return true
//		System.out.println(wordDictionary.search("aa")); // return false
//		System.out.println(wordDictionary.search(".a")); // return false
		System.out.println(wordDictionary.search(".at")); // return false
	}

}
