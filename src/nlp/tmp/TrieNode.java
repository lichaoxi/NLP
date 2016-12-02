package nlp.tmp;

import java.util.Arrays;
import java.util.Collection;

public class TrieNode {
	
	private char character;
	private boolean terminal;
	private TrieNode[] children = new TrieNode[0];
	
	public TrieNode(char character){
		this.character = character;
	}
	
	public char getCharacter() {
		return character;
	}
	public void setCharacter(char character) {
		this.character = character;
	}
	public boolean isTerminal() {
		return terminal;
	}
	public void setTerminal(boolean terminal) {
		this.terminal = terminal;
	}
	
	public Collection<TrieNode> getChildren(){
		return Arrays.asList(children);
	}
	
	public TrieNode getChild(char character){
		for (TrieNode child : children) {
			if(child.getCharacter() == character){
				return child;
			}
		}
		return null;
	}
	public TrieNode getChildIfNotExistThenCreate(char character){
		TrieNode child = getChild(character);
		if(child == null){
			child = new TrieNode(character);
			addChild(child);
		}
		return child;
	}
    public void addChild(TrieNode child){
    	children = Arrays.copyOf(children, children.length + 1);
    	children[children.length - 1] = child;
    }       
 
}
