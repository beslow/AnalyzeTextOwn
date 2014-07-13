package lijun.demo;

import java.util.ArrayList;
import java.util.HashMap;

public class Pretreatment {
	
	private HashMap< Integer, Integer > LineNoMap; 

	public Pretreatment() {
		this.LineNoMap = new HashMap< Integer, Integer >();
	}

	public String[] Result( String code ) throws ExceptionMessage {
		ArrayList<String> list = new ArrayList<String>();
		String[] text = code.split( "\r" );
		int index = 0;
		while ( index < text.length ) {
			StringToLine( text[index].trim(), list, index );
			index ++;
		}
		return (String[])list.toArray(new String[list.size()]);
	}
	
	
	public void StringToLine( String currentLine, ArrayList<String> list, int index ) throws ExceptionMessage {
		currentLine = currentLine.trim();
		String key = SharedMethods.DistinguishSplitKey( currentLine );
		if ( key.equals( MainKeys.INVALID ) ) {
			throw new ExceptionMessage("\"" + currentLine + "\"有语法错误", index);
		} else if ( key.equals( MainKeys.TEXTSTART ) ) {
			int endOfTEXTSTART = SharedMethods.indexOfMatching(currentLine, 0, '"', '"');
			if ( endOfTEXTSTART == -1 ) {
				throw new ExceptionMessage("\"缺少匹配的双引号", index);
			}  else if ( endOfTEXTSTART != currentLine.length() - 1 ) {
				throw new ExceptionMessage("此行有语法错误，字符串之后多余", index);
			} else {
				list.add( currentLine.substring( 0, endOfTEXTSTART + 1 ) );
				LineNoMap.put( list.size() - 1, index );
			}
		} else if ( key.equals( MainKeys.FOR ) ) {
			int posOfLeftParentheses = currentLine.indexOf( '(' );
			if ( posOfLeftParentheses == -1 ) {
				throw new ExceptionMessage( MainKeys.FOR + "语句需要使用\"(\"", index);
			} else if ( !currentLine.substring( 0, posOfLeftParentheses ).replaceFirst( MainKeys.FOR, "").trim().equals("") ) {
				throw new ExceptionMessage( currentLine.substring( 0, posOfLeftParentheses+1 ) + " 有语法错误", index);
			} else {
				int posOfRightParentheses = SharedMethods.indexOfMatching( currentLine, 0, '(', ')' );
				if ( posOfRightParentheses == -1 ) {
					throw new ExceptionMessage( MainKeys.FOR + "语句中(需要使用匹配的\")\"", index);
				} else {
					String threePa = currentLine.substring( posOfLeftParentheses + 1, posOfRightParentheses );
					if ( SharedMethods.Count( threePa, ';') != 2 ) {
						throw new ExceptionMessage( MainKeys.FOR + "的循环条件有语法错", index );
					} else {
						list.add( currentLine.substring( 0, posOfRightParentheses + 1 ) );
						LineNoMap.put( list.size() - 1, index );
						if ( posOfRightParentheses < currentLine.length() - 1 )
							StringToLine( currentLine.substring( posOfRightParentheses + 1 ), list, index );
					}
				}
			}
		} else if ( key.equals( MainKeys.WHILE ) ) {
			HandleWhileAndIf(currentLine, MainKeys.WHILE, list, index);
		} else if ( key.equals( MainKeys.IF ) ) {
			HandleWhileAndIf(currentLine, MainKeys.IF, list, index);
		} else if ( key.equals( MainKeys.VARIABLE ) ) {
			list.add(currentLine);
			LineNoMap.put( list.size() - 1, index );
		} else if ( key.equals( MainKeys.LABEL ) ) {
			HandleSpecialString( currentLine, MainKeys.LABEL, MainKeys.ENDLABEL, list, index );
		} else if ( key.equals( MainKeys.GOTO ) ) {
			HandleSpecialString( currentLine, MainKeys.GOTO, MainKeys.ENDGOTO, list, index );
		} else if ( key.equals( MainKeys.INCLUDE ) ) {
			HandleSpecialString( currentLine, MainKeys.INCLUDE, MainKeys.ENDINCLUDE, list, index );
		} else if ( key.equals( MainKeys.EXIT ) ) {
			HandleSpecialString( currentLine, MainKeys.EXIT, MainKeys.ENDEXIT, list, index );
		} else if ( key.equals( MainKeys.BREAK ) ) {
			HandleMustBeOneLine(currentLine, MainKeys.BREAK, list, index);
		} else if ( key.equals( MainKeys.NEWLINE ) ) {
			HandleMustBeOneLine(currentLine, MainKeys.NEWLINE, list, index);
		} else if ( key.equals( MainKeys.INDENT ) ) {
			HandleSpecialString( currentLine, MainKeys.INDENT, MainKeys.ENDINDENT, list, index);
		} else if ( key.equals( MainKeys.STARTCAL ) ) {
			HandleSpecialString( currentLine, MainKeys.STARTCAL, MainKeys.ENDSTARTCAL, list, index);
		} else if ( key.equals( MainKeys.ELSE ) ) {
			HandleToBeOneLine(currentLine, MainKeys.ELSE, list, index);
		} else if ( key.equals( MainKeys.RETURN ) ) {
			HandleSpecialString( currentLine, MainKeys.RETURN, MainKeys.ENDRETURN, list, index);
		} else if ( key.equals( MainKeys.BRACESTART ) ) {
			HandleToBeOneLine(currentLine, MainKeys.BRACESTART, list, index);
		} else if ( key.equals( MainKeys.BRACEEND ) ) {
			HandleToBeOneLine(currentLine, MainKeys.BRACEEND, list, index);
		}
	}
	
	public void HandleWhileAndIf( String currentLine, String key, ArrayList<String> list, int index ) throws ExceptionMessage {
		int posOfLeftParentheses = currentLine.indexOf( '(' );
		if ( posOfLeftParentheses == -1 ) {
			throw new ExceptionMessage( key + "语句需要使用\"(\"", index);
		} else if ( !currentLine.substring( 0, posOfLeftParentheses ).replaceFirst( key, "").trim().equals("") ) {
			throw new ExceptionMessage( currentLine.substring( 0, posOfLeftParentheses+1 ) + " 有语法错误", index);
		} else {
			int posOfRightParentheses = SharedMethods.indexOfMatching( currentLine, 0, '(', ')' );
			if ( posOfRightParentheses == -1 ) {
				throw new ExceptionMessage( key + "语句中(需要使用匹配的\")\"", index);
			} else {
				list.add( currentLine.substring( 0, posOfRightParentheses + 1 ) );
				LineNoMap.put( list.size() - 1, index );
				if ( posOfRightParentheses < currentLine.length() - 1 )
					StringToLine( currentLine.substring( posOfRightParentheses + 1 ), list, index );
			}
		}
	}
	
	public void HandleMustBeOneLine( String currentLine, String key, ArrayList<String> list, int index ) throws ExceptionMessage { 
		if ( currentLine.length() != key.length() ) {
			throw new ExceptionMessage( key + " 后面有多余的代码  ", index );
		} else {
			list.add( currentLine );
			LineNoMap.put( list.size() - 1, index );
		}
	}
	
	public void HandleToBeOneLine( String currentLine, String key, ArrayList<String> list, int index ) throws ExceptionMessage {
		list.add( key );
		LineNoMap.put( list.size() - 1, index );
		if ( currentLine.length() > key.length() ) {
			StringToLine( currentLine.substring( key.length() ).trim(), list, index);
		}
	}
	
	public void HandleSpecialString( String currentLine, String start, String end, ArrayList<String> list, int index ) throws ExceptionMessage {
		int posOfEnd = currentLine.indexOf( end, start.length() );
		if ( posOfEnd == -1 ) {
			throw new ExceptionMessage( start + " 需要有结束符  " + end, index );
		} else if ( posOfEnd + end.length() != currentLine.length() ) {
			throw new ExceptionMessage( "此行有语法错误，" + start +"的结束符" + end + "之后多余", index );
		} else {
			list.add( currentLine );
			LineNoMap.put( list.size() - 1, index );
		}
	}
		
}
