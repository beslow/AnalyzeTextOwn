package lijun.demo;

public class SharedMethods {
	
	/**
     * 统计指定字符串中指定字符的个数
     * <p>
     * 例
     * <blockquote><pre>
     * count("aaabbcc",'a') return 3
     * count("aaabbcc",'c') return 2
     * </pre></blockquote>
     * @param string
     * @param target
     * @return string 中 target 的个数
     */
    public static int Count(String string, char target){
    	int num = 0;
    	for(char temp:string.toCharArray())
    		if(temp == target)
    			num++;
       	return num;
    }
    /**
     * 判断是否是数值型字符串
     * @param str
     * @return
     */
    public static boolean isNumeric( String str ) {
    	for ( int i = str.length();--i>=0; ) {   
    		if ( !Character.isDigit( str.charAt(i) ) ) {
    				return false;
    		}
    	}
    	return true;
    }
    
    public static boolean isString( String str ) {
    	str = str.trim();
    	if ( CountOfValidKey( str, '"') == 2 && str.charAt(0) == '"' && str.charAt( str.length() - 1 ) == '"' ) {
    		return true;
    	}
    	return false;
    }
	/**
     * 在目标字符串中从<code>fromIndex</code>开始查找匹配的字符的位置
     * <p>
     * 例
     * <blockquote><pre>
     * indexOfMatching("(a>b)",0,'(',')') return 4
     * indexOfMatching("(a>b||(c>d))",0,'(',')') return 11
     * </pre></blockquote>
     * @param string  目标字符串
     * @param fromIndex  开始查找的位置
     * @param matched   被匹配的字符
     * @param matcher   匹配的字符
     * @return 匹配的字符的位置
     */
    public static int indexOfMatching( String string, int fromIndex, char matched, char matcher ) {
    	if ( string.trim() == "" ) {
    		return -1;
    	} else if ( fromIndex < 0 ) {
    		fromIndex = 0;
    	} else if ( fromIndex >= string.length() ) {
    		return -1;
    	}
    	fromIndex = fromIndex < string.indexOf( matched, fromIndex ) ? string.indexOf( matched, fromIndex ) : fromIndex;
    	int matchedIndex = fromIndex;
    	int countOfMatched = -1;
    	int countOfMatcher = 0;
    	do {
    		matchedIndex = string.indexOf( matcher, matchedIndex + 1 );
    		if ( matchedIndex == -1 )
    			break;
    		if ( string.charAt(matchedIndex-1) == '\\')
    			continue;
    		countOfMatched = CountOfValidKey( string.substring( fromIndex + 1, matchedIndex ), matched );
    		countOfMatcher = CountOfValidKey( string.substring( fromIndex + 1, matchedIndex ), matcher );
    	} while ( countOfMatched != countOfMatcher );
    	return matchedIndex;
    }
    
    
    /**
     * 返回有效的key的个数，纯文本包含的key是无效的。
     * <p>
     * example
     * <blockquote><pre>
     * CountOfValidKey("{abc^^^ab{{{cdefg$$$ksdfjdsl}",'{') return 1
     * CountOfValidKey("{abc^^^ab{{{cdefg$$$ksdfjdsl}",'{') return 1
     * </pre></blockquote>
     * @param string
     * @param key
     * @return
     */
    public static int CountOfValidKey( String string, char key ) {
    	return Count( string, key) - CountOfKeysInText(string, key);	
    }
    
    
    /**
     * 返回string中纯文本(用^^^和$$$包围的文本)包含字符key的个数
     * <p>
     * example
     * <blockquote><pre>
     * CountOfKeysInText("^^^abcdef{jsdlfjls}$$$",'{') return 1
     * CountOfKeysInText("^^^abcdef{jsdlfjls}",'{') return 0
     * CountOfKeysInText("^^^abcdefjsdlfjls$$$",'{') return 0
     * CountOfKeysInText("^^^abcdef{jsd{lf}jls}$$$",'}') return 2
     * </pre></blockquote>
     * @param string
     * @param key
     * @return
     */
    public static int CountOfKeysInText( String string, char key ) {
    	int start = 0;
    	int end = 0;
    	if ( string.indexOf( MainKeys.TEXTSTART ) == -1 || string.indexOf( MainKeys.TEXTEND ) == -1 ) {
    		return 0;
    	}
		start = string.indexOf( MainKeys.TEXTSTART );
		if ( start == -1 ) {
			return 0;
		}
		if ( start > 0 ) {
			while ( string.charAt( start -1 ) == '\\' ) {
				start = string.indexOf(MainKeys.TEXTSTART, start + MainKeys.TEXTSTART.length() );
				if ( start == -1 ) {
					return 0;
				}
			}
		} 
		
		end = string.indexOf( MainKeys.TEXTEND, start + MainKeys.TEXTSTART.length() );
		if ( end == -1 )
			return 0;
		while ( string.charAt( end - 1 ) == '\\' ) {
			end = string.indexOf( MainKeys.TEXTEND, end + MainKeys.TEXTEND.length() );
    	}
		int tempCount = CountOfKeysInText(string.substring( end + MainKeys.TEXTEND.length() < string.length() ? end + MainKeys.TEXTEND.length() : string.length() - 1 ), key );
		return Count( string.substring( start + 1, end ), key ) + tempCount;
    }  
    
    /**
     * 将字符串根据ch分割
     * <p>
     * example
     * <blockquote><pre>
     * Split("ab",'+') return {"ab",""}
     * Split("a+b",'+') return {"a","b"}
     * Split("+ab",'+') return {"ab",""}
     * Split("ab+",'+') return {"ab",""}
     * </pre></blockquote>
     * @param string
     * @param ch
     * @return
     */
    public static String[] Split( String string, char ch ) {
    	string = string.trim();
    	String[] a = new String[2];
    	if ( !string.contains( String.valueOf( ch ) ) ) {
    		a[0] = string;
    		a[1] = "";
    	} else {
    		int pos = string.indexOf( ch );
    		if ( pos == 0 ) {
    			a[0] = string.substring( 1 );
    			a[1] = "";
    		} else if ( pos == string.length() - 1 ) {
    			a[0] = string.substring( 0, string.length() - 1 );
    			a[1] = "";
    		} else {
    			a[0] = string.substring( 0, pos );
    			a[1] = string.substring( pos + 1, string.length() );
    		}
    	}
    	return a;
    }
    
    
    
    /**
     * 作用:去除无用的括号对(),同时也会去除字符串前后的空格
     * <p>
     * 例
     * <blockquote><pre>
     * removeParentheses("(a>b)") return a>b
     * removeParentheses("((a>b&&(c>d))||(e<f))") return (a>b&&(c>d))||(e<f)
     * </pre></blockquote>
     * @param string
     * @return 去除了无用括号对的字符串
     */
    public static String removeParentheses(String string){
    	string = string.trim();
    	while(string.startsWith("(") && string.endsWith(")") && indexOfMatching(string, 0,'(',')') == string.length()-1)
    		string = string.substring(1,string.length()-1);
    	return string;
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
	public static String DistinguishSplitKey ( String string ) {
		string = string.trim();
		int i;
		String key = "";
		for( i = 0; i < MainKeys.Keywords.size(); i++) {
			if ( string.indexOf( MainKeys.Keywords.get( i ) ) == 0 ) {
				key = MainKeys.Keywords.get( i );
				break;
			}
		}
		return key;
	}
    
}


