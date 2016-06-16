import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 简单测试正则表达式
 * @author zengli
 * @date 2016/5/30
 */
public class Test {
	public static void main(String[] args) {
		//java.lang.String类的boolean matches(String regex)方法
		System.out.println("例子1：");
		p("abc".matches("..."));
		p("a8432158a".replaceAll("\\d", "-"));
		Pattern p1 = Pattern.compile("[a-z]{3}");
		Matcher m1 = p1.matcher("fgh");
		p(m1.matches());
		p("fsgh".matches("[a-z]{3}"));
		System.out.println();
		
		
		
		//空格替换
		System.out.println("例子2：");
		Pattern p2 = Pattern.compile("\\s");
		//一个\t只代表一个空白字符
		String s2 = "我是   一个   好孩子   !\t\t";
		Matcher m2 = p2.matcher(s2);
		System.out.println(m2.replaceAll("空格"));	
		System.out.println();
		
		
		//将单数的java全改成大写，双数的全改成小写（匹配的时候忽略大小写）
		System.out.println("例子3：");
		Pattern p3 = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		Matcher m3 = p3.matcher("java Java JaVa jAVa IloveJaVA! fhds");
//		System.out.println(m3.replaceAll("号"));
		StringBuffer sb3 = new StringBuffer();
		int i = 0;
		while(m3.find()){
			i++;
			if(i%2==0){
				m3.appendReplacement(sb3, "java");
			}else{
				m3.appendReplacement(sb3, "JAVA");
			}
		}
		//添加尾巴
		m3.appendTail(sb3);
		System.out.println(sb3);
		System.out.println();
		
		
		
		System.out.println("例子4：");
		//正则表达式的分组
		Pattern p4 = Pattern.compile("(another) (test)");
	    StringBuffer sb4 = new StringBuffer();

	    String s4 = "This is another test and and and another test.";

	    String replacement = "$1 AAA $2";
	    Matcher m4 = p4.matcher(s4);
	    while(m4.find()){
//	    	System.out.println(m4.group());
//	    	System.out.println(m4.group(0));
//	    	System.out.println(m4.group(1));
//	    	System.out.println(m4.group(2));
	    	m4.appendReplacement(sb4, replacement);
	    }
	    System.out.println(m4.appendTail(sb4));
	}	
	
	
	public static void p(Object o) {
		System.out.println(o);
	}
}
