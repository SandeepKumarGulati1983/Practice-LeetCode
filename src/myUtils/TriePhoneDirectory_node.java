package myUtils;

public class TriePhoneDirectory_node {
	
	public TriePhoneDirectory_node[] nodeArray ;
	
	public TriePhoneDirectory_node(int numberLength){
		this.nodeArray = new TriePhoneDirectory_node[numberLength];
		for (int i = 0 ; i <numberLength; i ++) {
			nodeArray[i] = null;
		}
	}

}
