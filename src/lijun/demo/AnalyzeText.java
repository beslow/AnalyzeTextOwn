package lijun.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class AnalyzeText {
	
	final String FOR = "&&&for";
	final String WHILE = "&&&while";
	final String IF = "&&&if";
	final String VARIABLE = "&&&variable";
	final String TEXTSTART = "\"";
	final String TEXTEND = "\"";
	
	private int level;
	
	private VariableTable variableTable;
	
	public void setVariableTable( VariableTable variableTable ) {
		this.variableTable = variableTable;
	}
	
	public AnalyzeText( int level, VariableTable variableTable) {
		this.level = level;
		this.variableTable = new VariableTable( variableTable );
	}
	
	/**
	 * 判断给定字符串是否包含关键词  for if while
	 * <p>
	 * 例
	 * <blockquote><pre>
	 * HaveForWhileIf ( "&&&for{" ) return true
	 * HaveForWhileIf ( "just a test" ) return false
	 * </pre></blockquote>
	 * @param string
	 * @return
	 */
	boolean HaveForWhileIf ( String string ) { 
		String[] keys = { "&&&for", "&&&if", "&&&while" };
		int i;
		for ( i = 0; i < keys.length; i++ ) { 
			if ( string.contains( keys[i] ) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 分析整个文本
	 * @param string
	 * @return
	 */
	public String Analyze ( String string ) {
		String[] text = string.split( "\r" );
		String result = "";
		try {
			result = AnalyzeBlock ( text, 0, text.length - 1 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		} finally {
			
		}
		return result;
	}
	
	/**
	 * 分析文本块
	 * @param text
	 * @param beginIndex
	 * @param endIndex
	 * @return
	 */
	public String AnalyzeBlock ( String[] text, int beginIndex, int endIndex ) throws ExceptionMessage {
		if ( beginIndex < 0 || endIndex <0 || beginIndex > endIndex ) {
			return "";
		}
		String result = "";
		int index = beginIndex;
		while ( index <= endIndex ) {
			String string = text[index];
			int flag = DistinguishFirstKey( string );
			if ( flag == -1 ) { //字符串
				string = string.replace('\t', ' ' ).trim();
				if ( SharedMethods.isString( string ) ) {
					result += string.replace('"', ' ');
				} else if ( string.contains( "=" ) ) { //表达式，如，a=0
					String tempName = string.substring( 0, string.indexOf('=') ).trim();
					String tempValue = string.substring( string.indexOf( '=' ) + 1 ).trim();
					if ( this.variableTable.IsExist(tempName) ) { //
						Calculate calculate = new Calculate( this.variableTable, index );
						this.variableTable.SetValueByName(tempName, calculate.SimpleCalculate( tempValue ) );
					} else {
						throw new ExceptionMessage( "不合法", index );
					}
				} else if ( variableTable.IsExist( string ) ) {
					 result += variableTable.GetValueByName( string );
				} else if ( !string.equals( "" ) ) {
					Calculate calculate = new Calculate( this.variableTable, index );
					result += calculate.SimpleCalculate( string );
				}
				index ++;
			} else if ( flag == 0 ) { //if
				
			} else if ( flag == 1 ) { //for
				int start = string.indexOf( '(' );
				int end = SharedMethods.indexOfMatching( string, start, '(', ')' );
				String tempString = string.substring( start + 1, end );
				
				String[] tempArray = tempString.split(";");
				int endForIndex = IndexOfMatchLine( text, index);
				ForBlock forBlock = new ForBlock(text, index, endForIndex , 
						this.level + 1, this.variableTable, tempArray[0], tempArray[1], tempArray[2]);
				result += forBlock.Result();
				index = endForIndex + 1;
			} else if ( flag == 2 ) { //while 
				
			} else if ( flag == 3 ) { //variable
				DeclareVariable( text, index );
				index ++;
			}
		}
		return result;
	}
	

	
	/**
	 * 根据字符串声明变量，若有赋值符“=”，要赋值
	 * <p>
	 * example
	 * String[] text = { "&&&variable a=0" };
	 * DeclareVariable(text,0);
	 * getVarValue("a").getValue() return "0"
	 * @param text
	 * @param currentIndex
	 * @return
	 */
	public boolean DeclareVariable( String[] text, int currentIndex ) {
		if ( currentIndex < 0 || currentIndex >= text.length )
			return false;
		String string = text[currentIndex].trim();
		if ( !string.contains("&&&variable") )
			return false;
		string = string.replaceFirst( "&&&variable", "" ).trim();
		int endIndex = IndexOfMatchLine( text, currentIndex );
		if ( string.contains( "=" ) ) {
			String name = string.substring( 0, string.indexOf( '=' ) ).trim();
			String value = string.substring( string.indexOf( '=' )+1 ).trim();
			if ( !variableTable.IsExist( name ) )
				variableTable.AddVariable( new Variable(name, StringToValue(value), 0, this.level, 0, 0 ) );
		} else {
			if ( !variableTable.IsExist( string ) )
				variableTable.AddVariable( new Variable( string, "", 0, this.level, 0, 0 ) );
		}
		return true;
	}
	
	/**
	 * 判断string是否是变量名，若是，返回变量的值，否，返回string
	 * @param string
	 * @return
	 */
	private String StringToValue( String string ) {
		string = string.trim();
		if ( variableTable.IsExist( string ) ) {
			return variableTable.GetValueByName( string );
		} 
		return string;
	} 
	
	/**
	 * 找出关键字后‘{’匹配的‘}’所在的行的行号，即数组text的下标
	 * <p>
	 * example
	 * <blockquote><pre>
	 * String[] text = { "&&&if(a>b){", "max = a", "}" };
	 * IndexOfMatchLine(text, 0) return 2
	 * </pre></blockquote>
	 * @param text
	 * @param fromIndex
	 * @return
	 */
	int IndexOfMatchLine ( String[] text, int fromIndex ) {
		if ( DistinguishFirstKey(text[fromIndex]) < 0 ) {
			return -1;
		}
		String string = "";
		int index = fromIndex;
		while ( index < text.length ) {
			string += text[index];
			if ( SharedMethods.indexOfMatching( string, 0, '{', '}' ) > -1 )
				return index;
			index ++;
		}
		return -1;
	}
	
	/**
	 * 区分该字符串从左到右遇到的第一个关键字是什么
	 * <p>
	 * example
	 * <blockquote><pre>
	 * DistinguishFirstKey("&&&if{") return 0
	 * DistinguishFirstKey("&&&for{") return 1
	 * DistinguishFirstKey("&&&while{") return 2
	 * </pre></blockquote>
	 * @param string
	 * @return 
	 */
	int DistinguishFirstKey ( String string ) {
		int posOfIf = string.indexOf( IF );
		int posOfFor = string.indexOf( FOR );
		int posOfWhile = string.indexOf( WHILE );
		int posOfVariable = string.indexOf( VARIABLE );
		int flag = -1;
		int minPos = Integer.MAX_VALUE;
		if ( posOfIf != -1 ) {
			flag = 0;
			minPos = posOfIf;
		}
		if ( posOfFor != -1 && posOfFor < minPos ) {
			flag = 1;
			minPos = posOfFor;
		}
		if ( posOfWhile != -1 && posOfWhile < minPos ) {
			flag = 2;
		}
		if ( posOfVariable != -1 )
			flag = 3;
		return flag;
	}
    
}
