package nlp.datastructure;

import java.util.List;

public class TernarySearchTrie {
	
	private static class TSTNode {
		
		private TSTNode left;
		private TSTNode mid;
		private TSTNode right;
		private boolean terminal; 
		private char character;

		public TSTNode(char character) {
			this.character = character;
		}

		public boolean isTerminal() {
			return terminal;
		}

		public void setTerminal(boolean terminal) {
			this.terminal = terminal;
		}

		public char getCharacter() {
			return character;
		}

		public void setCharacter(char character) {
			this.character = character;
		}

	}
	
	private TSTNode ROOT_NODE = null;
		
	public void addAll(List<String> items){  
        for(String item : items){  
            add(item);  
        }  
    }  
    public void add(String item){  
        //去掉首尾空白字符  
        item=item.trim();  
        int len = item.length();  
        if(len < 1){  
            //长度小于1则忽略  
            return;  
        }  
        //根节点为空
        if (ROOT_NODE == null) {
        	ROOT_NODE = new TSTNode(item.charAt(0));
		}
        //从根节点开始添加  
        int charIndex = 0;
		TSTNode currentNode = ROOT_NODE;
		while (true) {
			int compa = (item.charAt(charIndex) - currentNode.character);
			if (compa == 0) {
				charIndex++;
				if (charIndex == item.length()) {
					//设置终结字符，表示从根节点遍历到此是一个合法的词  
					currentNode.setTerminal(true);  
					break;
				}
				if(currentNode.mid == null){
					currentNode.mid = new TSTNode(item.charAt(charIndex));
				}
				currentNode = currentNode.mid;
			} else if (compa < 0) {
				if(currentNode.left == null){
					currentNode.left = new TSTNode(item.charAt(charIndex));
				}
				currentNode = currentNode.left;
			} else {
				if(currentNode.right == null){
					currentNode.right = new TSTNode(item.charAt(charIndex));
				}
				currentNode = currentNode.right;
			}
			
		}
       
    }  
    
    public boolean contains(String item){  
        //去掉首尾空白字符  
        item=item.trim();  
        int len = item.length();  
        if(len < 1){  
            return false;  
        }  
        //从根节点开始查找  
        int charIndex = 0;
        TSTNode currentNode = ROOT_NODE;  
        while (true) {
			if (currentNode == null) {
				return false;
			}
			int charComp = item.charAt(charIndex) - currentNode.character;
			if (charComp == 0) {
				charIndex++;
				if (charIndex == item.length()) {
					return true; //已经匹配完
				}
				currentNode = currentNode.mid;
			} else if (charComp < 0) {
				currentNode = currentNode.left;
			} else {
				currentNode = currentNode.right;
			}
		}  
    }  
	
    public void show(){  
        show(ROOT_NODE,"");  
    }  
    //控制台打印，从下往上看
    private void show(TSTNode node, String indent){  
		if (node == null) {
			return;
		}
		show(node.right, indent + "\t");
		if(node.isTerminal()){  
			System.out.println(indent+node.getCharacter()+"(T)"); 
        }else{
        	System.out.println(indent+node.getCharacter()); 
        }
		show(node.mid, indent + "\t");
		show(node.left, indent + "\t");
    }  
    
    public static void main(String[] args){  
    	TernarySearchTrie trie = new TernarySearchTrie();  
        trie.add("APDPlat");  
        trie.add("APP");  
        trie.add("APD");  
        trie.add("Nutch");  
        trie.add("Lucene");  
        trie.add("Hadoop");  
        trie.add("Solr");  
        trie.add("杨尚川");  
        trie.add("杨尚昆");  
        trie.add("杨尚喜");  
        trie.add("中华人民共和国");  
        trie.add("中华人民打太极");  
        trie.add("中华");  
        trie.add("中心思想");  
        trie.add("杨家将");          
        //trie.contains("中心思想");
        trie.show();
    }  
	
	
	

}