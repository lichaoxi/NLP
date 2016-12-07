package nlp.test;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import nlp.datastructure.TernarySearchTrie;
import nlp.datastructure.Trie;
import nlp.datastructure.TrieV3;

/** 
 * 比较词典查询算法的性能 
 * 
 */  
public class SearchTestMemory {  
	
	static final Runtime runTime=Runtime.getRuntime();  
	
    //通过更改这里DIC的实现来比较不同实现之间的性能  
    //private static final List<String> DIC = new ArrayList<>();  
    //private static final List<String> DIC = new LinkedList<>(); 
    //private static final HashSet<String> DIC = new HashSet<String>();
    //private static final Trie DIC = new Trie(); 
    //private static final TrieV3 DIC = new TrieV3();
  
    private static final TernarySearchTrie DIC = new TernarySearchTrie();
    
    public static void main(String[] args){  
    	try {  
            
            long startU = getUsedMemory();
            System.out.println("开始装载词典前已使用内存：" + startU/1024.0 + "kb");
            
            int count=0;  
            List<String> lines = Files.readAllLines(Paths.get("C:/Users/Administrator/Desktop/robot/博客/dic.txt"), StandardCharsets.UTF_8);  
            
            for(int i=0;i<10;i++){
            	 for(String line : lines){  
                     DIC.add(line);  
                     count++;  
                 } 
            }  
            System.out.println("完成初始化词典，词数目："+count);  
            
            
            long endU = getUsedMemory();
            System.out.println("装载词典后使用内存：" + endU/1024.0 + "kb");
            System.out.println("差值：" + (endU - startU)/1024.0 + "kb");
            
        } catch (IOException ex) {  
            System.err.println("词典装载失败:"+ex.getMessage());  
        }
        
        
    } 
    static long getUsedMemory(){ 
    	System.gc();
        return runTime.totalMemory()-runTime.freeMemory();  
    } 
} 