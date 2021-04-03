package LeetCodeSolution;

import java.util.HashMap;

public class Trie_208 {

	Trie_208  root ;
	HashMap<Character, Trie_208> map ;
	//Trie_208[] rootArray ;
	boolean isEnd ;
	int alphabetLenth = 26;

	/** Initialize your data structure here. */
	public Trie_208() {
		
		//rootArray = new Trie_208[alphabetLenth];
		//for (int i = 0 ; i<26 ; i++) rootArray[i] = null ; 
		map = new HashMap<>();
		isEnd = false ;
	}

	/** Inserts a word into the trie. */
	public void insert(String word) {
		if (root == null ) root = new Trie_208();
		Trie_208 head = root ;
		if (word == null) return ;
		word = word.toLowerCase(); // for use case 2 in which T is capital 
		for (int i = 0 ;i < word.length() ; i ++) {
			//int index = (int)word.charAt(i) - 'a';
			//System.out.println("index = " + index );
			//if (head.rootArray[index] ==null) head.rootArray[index] = new Trie_208();
			//head = head.rootArray[index] ;
			char c = word.charAt(i);
			if (!head.map.containsKey(c)) head.map.put(c,new Trie_208());
			head = head.map.get(c);
		}
		head.isEnd = true;

	}

	/** Returns if the word is in the trie. */
	public boolean search(String word) {
		if (root == null) return false ;
		Trie_208 head = root ;
		word = word.toLowerCase(); 
		for (int i = 0 ;i < word.length() ; i ++) {
//			int index = (int)word.charAt(i) - 'a';
//			if (head.rootArray[index] == null) return false ;
//			head = head.rootArray[index] ;
			char c = word.charAt(i);
			if (!head.map.containsKey(c)) return false ;
			head = head.map.get(c);
		}

		return (head.isEnd == true )? true : false  ;

	}

	/** Returns if there is any word in the trie that starts with the given prefix. */
	public boolean startsWith(String prefix) {
		if (root == null) return false ;
		Trie_208 head = root ;
		prefix = prefix.toLowerCase(); 
		for (int i = 0 ;i < prefix.length() ; i ++) {
//			int index = (int)prefix.charAt(i) - 'a';
//			if (head.rootArray[index] == null) return false ;
//			head = head.rootArray[index] ;
			char c = prefix.charAt(i);
			if (!head.map.containsKey(c)) return false ;
			head = head.map.get(c);
		}
		return true ;

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Trie_208 trie = new Trie_208();
		// test  case 1 
//		trie.insert("apple");
//		System.out.println(trie.search("apple"));   // return True
//		System.out.println(trie.search("app"));     // return False
//		System.out.println(trie.startsWith("app")); // return True
//		trie.insert("app");
//		System.out.println(trie.search("app"));     // return True
		
		/*
		 *  test case 2  - 
		 *  ["Trie","search"]
		 *  [[],["a"]]
		 */
		
//		trie.insert("Trie");
//		trie.insert("search");
//		System.out.println(trie.search(""));
//		System.out.println(trie.search("a"));
		
		//211 , leet code 
		// use case 1 
		Trie_208 wordDictionary = new Trie_208();
		wordDictionary.insert("bad");
		wordDictionary.insert("dad");
		wordDictionary.insert("mad");
		System.out.println(wordDictionary.search("pad")); // return False
		System.out.println(wordDictionary.search("bad")); // return True
		System.out.println(wordDictionary.search(".ad")); // return True
		System.out.println(wordDictionary.search("b..")); // return True
	}

}
