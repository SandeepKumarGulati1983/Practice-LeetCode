package LeetCodeSolution;


import Utils.TrieNode;

public class TrieDictionary_ques {

	TrieNode root ;
	
	TrieDictionary_ques(){
		root = new TrieNode();
	}
	
	TrieNode insert(String word) {
		
		TrieNode tNode = root;

		for (int i = 0 ; i < word.length() ; i++) {
			char c = word.charAt(i);
			tNode.nodeArray[(int)c -'a'] = new TrieNode();
			tNode = tNode.nodeArray[(int)c -'a'];
			
		}
		return root;


	}

	boolean isWordExist(String word , TrieNode root) {

		for (int i = 0 ; i <word.length() ; i ++) {

			int index = (int)word.charAt(i) - 'a' ;
			System.out.println(index);
			if (root.nodeArray[index] != null) {
				
				root = root.nodeArray[index] ;
			}else return false;
		}

		return true;
	}

	public static void main (String[] arg) {

		TrieDictionary_ques trie = new TrieDictionary_ques();
		
		TrieNode root = trie.insert("trie");
		trie.insert("sandeep");

		System.out.println(trie.isWordExist("sandeep", root));
		
	}

}
