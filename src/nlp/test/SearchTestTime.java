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
public class SearchTestTime {  
    //为了生成随机查询的词列表  
    private static final List<String> DIC_FOR_TEST = new ArrayList<>(); 
    
    //通过更改这里DIC的实现来比较不同实现之间的性能  
    //private static final List<String> DIC = new ArrayList<>();  
    //private static final List<String> DIC = new LinkedList<>(); 
    
    //private static final HashSet<String> DIC = new HashSet<String>();
    //private static final Trie DIC = new Trie(); 
    //private static final TrieV3 DIC = new TrieV3();
    
    private static final TernarySearchTrie DIC = new TernarySearchTrie();
    
    static{  
        try {  
            System.out.println("开始初始化词典"); 
            int count=0;  
            List<String> lines = Files.readAllLines(Paths.get("C:/Users/Administrator/Desktop/robot/博客/dic.txt"), StandardCharsets.UTF_8);  
            for(String line : lines){  
                DIC.add(line);  
                DIC_FOR_TEST.add(line);  
                count++;  
            }  
            System.out.println("完成初始化词典，词数目："+count);  
        } catch (IOException ex) {  
            System.err.println("词典装载失败:"+ex.getMessage());  
        }          
    }  
    
    public static void main(String[] args){  
    	
        //选取随机值  
        List<String> words = new ArrayList<>();  
        for(int i=0;i<100000;i++){  
            words.add(DIC_FOR_TEST.get(new Random(System.nanoTime()+i).nextInt(427452)));  
        }  
        long start = System.currentTimeMillis();  
        for(String word : words){  
            DIC.contains(word);  
        }  
        long cost = System.currentTimeMillis()-start;  
        
        System.out.println("cost time:"+cost+" ms");  
        
    }  
} 