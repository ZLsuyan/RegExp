import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��������ʽͳ����Ч����������ע�����������е�
 * @author zengli
 * @date 2016/5/31
 */
public class CodeCounter {
	static long normalLines = 0;
	static long commentLines = 0;
	static long whiteLines = 0;
	
	public static void main(String[] args) {
		//����������ʽ��\\�滻Ϊ/
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
		 * ��ע�⡿������s����ǡ��� E:\MyEclipseWorkspace\SXTTest\src\cn\edu\hit\
		 *          ��Ϊ��java�� Ĭ��\��ת���ַ�������ڳ�������Ҫ\\����������������������  
		 */
	//	System.out.println(s);
		
		//���ﴫs��ss���ɣ�s��windowʶ��ģ�ss��linux��windows��ʶ���
		File f = new File(ss);
		File[] codeFiles = f.listFiles();
		for(File child : codeFiles){
			if(child.getName().matches(".*\\.java$")){
				parse(child);
			}
		}
		
		
		System.out.println("normalLines:"+normalLines+", commentLines:"+commentLines+", whiteLines:"+whiteLines);
		System.out.println("SUMLines��"+(normalLines+commentLines+whiteLines));
	}
	
	private static void parse(File f){
		BufferedReader br = null;
		boolean comment = false;
		try{
			br = new BufferedReader(new FileReader(f));
			String line = "";
			while((line=br.readLine())!=null){
				//trim()����ȥ���ַ�����ʼ�ͽ�β�Ŀո�
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
