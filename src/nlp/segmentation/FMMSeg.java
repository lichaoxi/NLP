package nlp.segmentation;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * 基于词典的正向最大匹配算法
 * @author 杨尚川
 */
public class FMMSeg {
	private static final List<String> DIC = new ArrayList<String>();
	private static int MAX_LENGTH = 0;
	static{
		//手动添加try-catch
		try {
			System.out.println("开始初始化词典");
			int count = 0;
			int max = 1;
			//java8读取文件方式
			List<String> lines = Files.readAllLines(Paths.get("C:/Users/Administrator/Desktop/robot/笔记/dic.txt"), StandardCharsets.UTF_8);
			for (String line : lines) {
				DIC.add(line);
				count ++;
				if(max < line.length()){
					max = line.length();
				}
			}
			MAX_LENGTH = max;
			System.out.println("完成初始化词典，词数目：" + count);
			System.out.println("最大分词长度：" + MAX_LENGTH);
		} catch (IOException e) {
			System.err.println("词典装载失败：" + e.getMessage());
		}
	}
    
	public static List<String> seg(String text){
		List<String> result = new ArrayList<String>();
		while(text.length() > 0){
			int len = MAX_LENGTH;
			if(text.length() < len){
				len = text.length();
			}
			//只取词典中最长分词的长度的子串进行匹配
			String tryWord = text.substring(0, 0 + len);
			while(!DIC.contains(tryWord)){
				//如果长度为一且在词典中未找到匹配，则按长度为一切分
				if(tryWord.length() == 1){
					break;
				}
				tryWord = tryWord.substring(0, tryWord.length() - 1);
			}
			result.add(tryWord);
			text=text.substring(tryWord.length());
		}
		return result;
	}	
    
    public static void main(String[] args){
        String text = "杨尚川是APDPlat应用级产品开发平台的作者";  
        System.out.println(seg(text));
    }
    
}