import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 用正则表达式统计有效代码行数，注释行数，空行等
 * @author zengli
 * @date 2016/5/31
 */
public class CodeCounter {
	static long normalLines = 0;
	static long commentLines = 0;
	static long whiteLines = 0;
	
	public static void main(String[] args) {
		//利用正则表达式将\\替换为/
		String s = "E:\\MyEclipseWorkspace\\SXTTest\\src\\cn\\edu\\hit\\";
		Pattern p = Pattern.compile("\\\\");
		Matcher m = p.matcher(s);
		StringBuffer sb = new StringBuffer();
		while(m.find()){
			m.appendReplacement(sb, "/");
		}
		m.appendTail(sb);
	//	System.out.println(sb);
		String ss = sb.toString();
		
		/*
		 * 【注意】：这里s输出是—— E:\MyEclipseWorkspace\SXTTest\src\cn\edu\hit\
		 *          因为在java中 默认\是转义字符，因此在程序中需要\\，但是输出仍是正常输出。  
		 */
	//	System.out.println(s);
		
		//这里传s和ss均可，s是window识别的，ss是linux和windows均识别的
		File f = new File(ss);
		File[] codeFiles = f.listFiles();
		for(File child : codeFiles){
			if(child.getName().matches(".*\\.java$")){
				parse(child);
			}
		}
		
		
		System.out.println("normalLines:"+normalLines+", commentLines:"+commentLines+", whiteLines:"+whiteLines);
		System.out.println("SUMLines："+(normalLines+commentLines+whiteLines));
	}
	
	private static void parse(File f){
		BufferedReader br = null;
		boolean comment = false;
		try{
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while((line=br.readLine())!=null){
				//trim()方法去掉字符串开始和结尾的空格
				line = line.trim();
				if(line.matches("^[\\s&&[^\\n]]*$")){
					whiteLines ++;
				}else if(line.startsWith("/*")&& !line.endsWith("*/")){
					commentLines ++;
					comment = true;
				}else if(line.startsWith("/*")&&line.endsWith("*/")){
					commentLines ++;
				}else if(true == comment){
					commentLines ++;
					if(line.endsWith("*/")){
						comment = false;
					}
				}else if(line.startsWith("////")){
					commentLines ++;
				}else {
					normalLines ++;
				}
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(br!=null){
				try {
					br.close();
					br = null;
				} catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}
	}
}
