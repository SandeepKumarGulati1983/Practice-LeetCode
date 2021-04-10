package LeetCodeSolution;

import myUtils.TriePhoneDirectory_node;

public class TriePhoneDirectory {

	TriePhoneDirectory_node root ; 

	int indianNumber = 10;

	TriePhoneDirectory(){
		root = new TriePhoneDirectory_node(indianNumber);
	}

	TriePhoneDirectory_node insert(String phoneNumber) {

		TriePhoneDirectory_node child = root;
		for (int i = 0 ; i <phoneNumber.length() ; i ++) {
			int index = (int)phoneNumber.charAt(i) - '0';
			System.out.println("pn "+ index );
			if (child.nodeArray[index] == null)child.nodeArray[index] = new TriePhoneDirectory_node(indianNumber);
			child = child.nodeArray[index] ;
		}
		return root;
	}

	void printTrie(TriePhoneDirectory_node node, int phoneDigit) {
		
		if (node == null ) return ;
		
		System.out.println("..."+phoneDigit);
		for (int i = 0 ; i < 10 ; i ++) {
			if (node.nodeArray[i] !=null) {
				printTrie(node.nodeArray[i], i);
			}
		}
		
	}

	boolean isNumberExist(String phoneNumber) {

		TriePhoneDirectory_node child = root;
		for (int i = 0 ; i < 10 ; i ++) {

			int index = (int)phoneNumber.charAt(i) -'0';
			if (child.nodeArray[index] != null) {
				System.out.println(index);
				child =  child.nodeArray[index] ;
			}else return false ;
		}
		
		return true;
	}

	public static void main (String[] args) {

		TriePhoneDirectory trie = new TriePhoneDirectory();
		
		TriePhoneDirectory_node node = trie .insert("9958007900");
		trie.insert("9898294971");
		trie.printTrie(node, -1);
		System.out.println(trie.isNumberExist("9958007900"));
	}

}
