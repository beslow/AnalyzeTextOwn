package lijun.demo;
import java.util.ArrayList;

import org.omg.CORBA.PUBLIC_MEMBER;

/**
 * @author lijun
 *   存放关键字
 */
public class MainKeys {
	public static String TEXTSTART = "\"";
	public static String TEXTEND = "\"";
	public static String FOR = "&&&for";
	public static String WHILE = "&&&while";
	public static String IF = "&&&if";
	public static String VARIABLE = "&&&variable";
	public static String LABEL = "&&&label";
	public static String GOTO = "&&&goto";
	public static String INCLUDE = "&&&include";
	public static String EXIT = "&&&exit";
	public static String BREAK = "&&&break&&&";
	public static String NEWLINE = "***newline***";
	public static String INDENT = "***INDENT";
	public static String STARTCAL = "%%%";
	public static String ELSE = "&&&else";
	public static String RETURN = "&&&return";
	
	public static String BRACESTART = "{";
	public static String BRACEEND = "}&&&";
	
	public static String INVALID = "";
	public static String ENDLABEL = "&&&";
	public static String ENDGOTO = "&&&";
	public static String ENDINCLUDE = "&&&";
	public static String ENDEXIT = "&&&";
	public static String ENDINDENT = "***";
	public static String ENDSTARTCAL = "%%%";
	public static String ENDRETURN = "&&&";
	/**
	 * 用于存放关键字的ArrayList
	 */
	public  static ArrayList<String> Keywords = new ArrayList<String>(){
		{
			add(TEXTSTART);
			add(FOR);
			add(WHILE);
			add(IF);
			add(VARIABLE);
			add(LABEL);
			add(GOTO);
			add(INCLUDE);
			add(EXIT);
			add(BREAK);
			add(NEWLINE);
			add(INDENT);
			add(STARTCAL);
			add(ELSE);
			add(RETURN);
			add(BRACESTART);
			add(BRACEEND);
		}
	};

}
