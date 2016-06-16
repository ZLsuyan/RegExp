import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取网页中的email地址
 * @author zengli
 * @date 2016/5/31
 */
public class EmailSpider {
	public static void main(String[] args) {
		 try {
			BufferedReader br = new BufferedReader(new FileReader("E:/MyEclipseWorkspace/RegExp/email.txt"));
			String line = "";
			while((line=br.readLine())!=null){
				parse(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		 
	}
	
	private static void parse(String str) {
		Pattern p = Pattern.compile("[\\w[.-]]+@[\\w[.-]]+\\.[\\w]+");
		Matcher m = p.matcher(str);
		while(m.find()){
			System.out.println(m.group());
		}
	}
}
