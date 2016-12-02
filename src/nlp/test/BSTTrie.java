package nlp.test;
/**
 * 二叉搜索词典树
 * @author Administrator
 *
 */
public class BSTTrie {
	private static class TrieNode implements Comparable{
		private char character;
		private TrieNode left;
		private TrieNode right;
		
		//构造函数
		public TrieNode(char character){
			this.character = character;
		}
		
		public char getCharacter() {
			return character;
		}
		
		public void setCharacter(char character) {
			this.character = character;
		}
		
		@Override
		public int compareTo(Object o) {
			return this.character - (char)o;
		}
		
		public void insert(char character){
			
		}
		
	}

}
