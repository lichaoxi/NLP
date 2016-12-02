package nlp.test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TrieV3 {  
	private static class TrieNode implements Comparable{  
        private char character;  
        private boolean terminal;  
        private TrieNode[] children = new TrieNode[0];  
        public TrieNode(char character){  
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
        public Collection<TrieNode> getChildren() {  
            return Arrays.asList(children);              
        }  
        /**
         * 二分搜索算法
         * 1.要求children数组有序(insert实现有序数组)2.节点类可比较(实现comparable借口)
         * @param character
         * @return
         */
        public TrieNode getChild(char character) {  
            int index = Arrays.binarySearch(children, character);
            if(index >= 0){
            	return children[index];
            }
            return null;
        }          
        public TrieNode getChildIfNotExistThenCreate(char character) {  
            TrieNode child = getChild(character);  
            if(child == null){  
                child = new TrieNode(character);  
                addChild(child);  
            }  
            return child;  
        }  
        public void addChild(TrieNode child) {  
        	children = insert(children, child);
        }  
        /** 
         * 将一个字符追加到有序数组 
         * @param array 有序数组 
         * @param element 字符 
         * @return 新的有序数字 
         */  
        private TrieNode[] insert(TrieNode[] array, TrieNode element){  
            int length = array.length;  
            if(length == 0){  
                array = new TrieNode[1];  
                array[0] = element;  
                return array;  
            }  
            TrieNode[] newArray = new TrieNode[length+1];  
            boolean insert=false;  
            for(int i=0; i<length; i++){  
                if(element.getCharacter() <= array[i].getCharacter()){  
                    //新元素找到合适的插入位置  
                    newArray[i]=element;  
                    //将array中剩下的元素依次加入newArray即可退出比较操作  
                    System.arraycopy(array, i, newArray, i+1, length-i);  
                    insert=true;  
                    break;  
                }else{  
                    newArray[i]=array[i];  
                }  
            }  
            if(!insert){  
                //将新元素追加到尾部  
                newArray[length]=element;  
            }  
            return newArray;  
        }
		@Override
		public int compareTo(Object o) {
			return this.getCharacter() - (char)o;
		}  
    }  
	
    private final TrieNode ROOT_NODE = new TrieNode('/');  
  
    public boolean contains(String item){  
        //去掉首尾空白字符  
        item=item.trim();  
        int len = item.length();  
        if(len < 1){  
            return false;  
        }  
        //从根节点开始查找  
        TrieNode node = ROOT_NODE;  
        for(int i=0;i<len;i++){  
            char character = item.charAt(i);  
            TrieNode child = node.getChild(character);  
            if(child == null){  
                //未找到匹配节点  
                return false;  
            }else{  
                //找到节点，继续往下找  
                node = child;  
            }  
        }  
        if(node.isTerminal()){  
            return true;  
        }  
        return false;  
    }  
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
        //从根节点开始添加  
        TrieNode node = ROOT_NODE;  
        for(int i=0;i<len;i++){  
            char character = item.charAt(i);  
            TrieNode child = node.getChildIfNotExistThenCreate(character);  
            //改变顶级节点  
            node = child;  
        }  
        //设置终结字符，表示从根节点遍历到此是一个合法的词  
        node.setTerminal(true);  
    }       
    public void show(){  
        show(ROOT_NODE,"");  
    }  
    private void show(TrieNode node, String indent){  
        if(node.isTerminal()){  
            System.out.println(indent+node.getCharacter()+"(T)");  
        }else{  
            System.out.println(indent+node.getCharacter());  
        }          
        for(TrieNode item : node.getChildren()){  
            show(item,indent+"\t");  
        }  
    }  
    public static void main(String[] args){  
        TrieV3 trie = new TrieV3();  
        trie.add("APDPlat");  
        trie.add("APP");  
        trie.add("APD");  
        trie.add("杨尚川");  
        trie.add("杨尚昆");  
        trie.add("杨尚喜");  
        trie.add("中华人民共和国");  
        trie.add("中华人民打太极");  
        trie.add("中华");  
        trie.add("中心思想");  
        trie.add("杨家将");          
        trie.show();  
    }  
}  
