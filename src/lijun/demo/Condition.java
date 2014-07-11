package lijun.demo;
import Exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Condition {
	
	/**
	 * 成员变量
	 */
	private VariableTable variableTable;
	private int lineNumber;
	
	public Condition(VariableTable variableTable, int lineNumber) {
		this.variableTable = new VariableTable(variableTable);
		this.lineNumber = lineNumber;
	}
	
	/**
	 * 简单计算，暂时只考虑了数值的情况
	 * @param string
	 * @return
	 * @throws Exception 
	 */
	public String SimpleCalculate( String string ) throws ExceptionMessage {
		String[] array;
		string = string.trim();
		if ( string.contains( "+" ) ) {
			array = SharedMethods.Split(string, '+');
			int flag = typeCompare( array[0], array[1] );
			if ( flag == -1 ) {
				throw new ExceptionMessage( array[0] + "或" + array[1] + "未定义", this.lineNumber );
			} else if ( flag == 0 ) {
				throw new ExceptionMessage( array[0] + "和" + array[1] + "类型不同", this.lineNumber );
			} else {
				if ( SharedMethods.isString( array[0] ) ) {
					return "\"" + array[0].replace('"', ' ').trim() + StringToValue( array[1] ).replace('"', ' ').trim() + "\"";
				} else if ( variableTable.IsExist( array[0]) && variableTable.GetTypeByName( array[0] ) == 2 && SharedMethods.isString( array[1] ) ){
					return "\"" + StringToValue( array[0] ) + array[1].replace('"', ' ').trim() + "\"";
				} else if ( variableTable.GetTypeByName( array[0] ) == 2 && variableTable.GetTypeByName( array[1] ) == 2 ) {
					return "\"" + StringToValue( array[0] ) + StringToValue( array[1] ) + "\"";
				} else {
					return String.valueOf( Float.parseFloat( StringToValue( array[0] ) ) + Float.parseFloat( StringToValue( array[1] ) ) );
				}
			}
		} else if ( string.contains( "-" ) ) {
			array = SharedMethods.Split(string, '-');
			int flag = typeCompare( array[0], array[1] );
			if ( flag == -1 ) {
				throw new ExceptionMessage( array[0] + "或" + array[1] + "未定义", this.lineNumber );
			} else if ( flag == 0 ) {
				throw new ExceptionMessage( array[0] + "和" + array[1] + "类型不同", this.lineNumber );
			} else {
				if ( SharedMethods.isNumeric( array[0] ) || variableTable.GetTypeByName( array[0] ) != 2 ) {
					return String.valueOf( Float.parseFloat( StringToValue( array[0] ) ) - Float.parseFloat( StringToValue( array[1] ) ) );
				} else {
					throw new ExceptionMessage( "字符串类型的数据不能相减", this.lineNumber );
				}
			}
		} else if ( string.contains( "*" ) ) {
			array = SharedMethods.Split(string, '*');
			int flag = typeCompare( array[0], array[1] );
			if ( flag == -1 ) {
				throw new ExceptionMessage( array[0] + "或" + array[1] + "未定义", this.lineNumber );
			} else if ( flag == 0 ) {
				throw new ExceptionMessage( array[0] + "和" + array[1] + "类型不同", this.lineNumber );
			} else {
				if ( SharedMethods.isNumeric( array[0] ) || variableTable.GetTypeByName( array[0] ) != 2 ) {
					return String.valueOf( Float.parseFloat( StringToValue( array[0] ) ) * Float.parseFloat( StringToValue( array[1] ) ) );
				} else {
					throw new ExceptionMessage( "字符串类型的数据不能相乘", this.lineNumber );
				}
			}
		} else if ( string.contains( "/" ) ) {
			array = SharedMethods.Split(string, '/');
			int flag = typeCompare( array[0], array[1] );
			if ( flag == -1 ) {
				throw new ExceptionMessage( array[0] + "或" + array[1] + "未定义", this.lineNumber );
			} else if ( flag == 0 ) {
				throw new ExceptionMessage( array[0] + "和" + array[1] + "类型不同", this.lineNumber );
			} else {
				if ( SharedMethods.isNumeric( array[0] ) || variableTable.GetTypeByName( array[0] ) != 2 ) {
					if ( Float.parseFloat( StringToValue( array[1] ) ) == 0 ) {
						throw new ExceptionMessage( "除数不能为0", this.lineNumber );
					} else {
						return String.valueOf( Float.parseFloat( StringToValue( array[0] ) ) / Float.parseFloat( StringToValue( array[1] ) ) );
					}
				} else {
					throw new ExceptionMessage( "字符串类型的数据不能相除", this.lineNumber );
				}
			}
		} else if ( string.contains( "%" ) ) {
			array = SharedMethods.Split(string, '%');
			int flag = typeCompare( array[0], array[1] );
			if ( flag == -1 ) {
				throw new ExceptionMessage( array[0] + "或" + array[1] + "未定义", this.lineNumber );
			} else if ( flag == 0 ) {
				throw new ExceptionMessage( array[0] + "和" + array[1] + "类型不同", this.lineNumber );
			} else {
				if ( SharedMethods.isNumeric( array[0] ) || variableTable.GetTypeByName( array[0] ) != 2 ) {
					if ( Float.parseFloat( StringToValue( array[1] ) ) == 0 ) {
						throw new ExceptionMessage( "求余时除数不能为0", this.lineNumber );
					} else {
						return String.valueOf( Float.parseFloat( StringToValue( array[0] ) ) % Float.parseFloat( StringToValue( array[1] ) ) );
					}
				} else {
					throw new ExceptionMessage( "字符串类型的数据不能求余", this.lineNumber );
				}
			}
		} else if ( string.equals( "" ) ) {
			throw new ExceptionMessage( "计算表达式不能为空", this.lineNumber );
		} else if ( variableTable.IsExist( string ) && variableTable.GetTypeByName( string ) == 2 ) {
			return "\"" + StringToValue( string ) + "\"";
		} else {
			return StringToValue( string );
		}
	}
    
	/**
	 * 比较两个元素的类型，这个元素有可能是变量，有可能是字符串，有可能是数值，有可能是未定义变量，有可能是非法字符
	 * 类型相同时返回 <code>1</code>
	 * 类型不同时返回 <code>0</code>
	 * 未定义或非法时返回 <code>-1</code>
	 * @param A
	 * @param B
	 * @return 1 or 0 or -1
	 */
	public int typeCompare( String A, String B ) {
		A = A.trim();
		B = B.trim();
		if ( !SharedMethods.isNumeric( A ) && !SharedMethods.isString( A ) && !variableTable.IsExist( A ) || 
			 !SharedMethods.isNumeric( B ) && !SharedMethods.isString( B ) && !variableTable.IsExist( B ) ) {
			return -1;
		} else if ( variableTable.IsExist( A ) && variableTable.IsExist( B ) && variableTable.GetTypeByName( A ) == variableTable.GetTypeByName( B ) ) {
			return 1;
		} else if ( variableTable.IsExist( A ) && variableTable.GetTypeByName( A ) == 2 && SharedMethods.isString( B ) ) {
			return 1;
		} else if ( variableTable.IsExist( A ) && variableTable.GetTypeByName( A ) != 2 && SharedMethods.isNumeric( B ) ) {
			return 1;
		} else if ( variableTable.IsExist( B ) && variableTable.GetTypeByName( B ) == 2 && SharedMethods.isString( A ) ) {
			return 1;
		} else if ( variableTable.IsExist( B ) && variableTable.GetTypeByName( B ) != 2 && SharedMethods.isNumeric( A ) ) {
			return 1;
		} else if ( SharedMethods.isString( A ) && SharedMethods.isString( B ) ) {
			return 1;
		} else if ( SharedMethods.isNumeric( A ) && SharedMethods.isNumeric( B ) ) {
			return 1;
		} else {
			return 0;
		}
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
	 * 判断条件表达式的真假
	 * <p>
	 * example
	 * <blockquote><pre>
	 * 数值的比较
	 * 若数值型变量num的值是20
	 * ValueOfExpression("num>=20") return true
	 * ValueOfExpression("num-1>=20") return false
	 * ValueOfExpression("1>0") return true
	 * ValueOfExpression("")
	 * ValueOfExpression("1") return true
	 * ValueOfExpression("0") return false
	 * 字符串的比较
	 * ValueOfExpression("\"a\"==\"a\"") return true
	 * ValueOfExpression("\"a\"!=\"b\"") return true
	 * </pre></blockquote>
	 * @param expression
	 * @return
	 */
    public boolean ValueOfExpression ( String expression ) throws ExceptionMessage {
    	expression = removeParentheses( expression );
    	
    	if ( expression.contains( ">=" ) ) {
    		String left = SimpleCalculate( expression.substring( 0, expression.indexOf( ">=" ) ) );
    		String right = SimpleCalculate( expression.substring( expression.indexOf( ">=" ) + 2 ) );
    		if ( SharedMethods.isString( left ) || SharedMethods.isString( right ) ) {
    			throw new ExceptionMessage( "字符串不能用于比较大于等于(>=)", this.lineNumber );
    		} else {
    			return Float.parseFloat( left ) >= Float.parseFloat( right ); 
    		}
    	} else if ( expression.contains( "<=" ) ) {
    		String left = SimpleCalculate( expression.substring( 0, expression.indexOf( "<=" ) ) );
    		String right = SimpleCalculate( expression.substring( expression.indexOf( "<=" ) + 2 ) );
    		if ( SharedMethods.isString( left ) || SharedMethods.isString( right ) ) {
    			throw new ExceptionMessage( "字符串不能用于比较小于等于(<=)", this.lineNumber );
    		} else {
    			return Float.parseFloat( left ) <= Float.parseFloat( right ); 
    		}
    	} else if ( expression.contains( "==" ) ) {
    		String left = SimpleCalculate( expression.substring( 0, expression.indexOf( "==" ) ) );
    		String right = SimpleCalculate( expression.substring( expression.indexOf( "==" ) + 2 ) );
    		if ( SharedMethods.isString( left ) && SharedMethods.isString( right ) ) {
    			return left.equals( right );
    		} else if ( SharedMethods.isNumeric( left ) && SharedMethods.isNumeric( right ) ) {
    			return Float.parseFloat( left ) == Float.parseFloat( right ); 
    		} else {
    			throw new ExceptionMessage( "‘==’两边类型不同，不能比较是否相等", this.lineNumber );
    		}
    	} else if ( expression.contains( "!=" ) ) {
    		String left = SimpleCalculate( expression.substring( 0, expression.indexOf( "!=" ) ) );
    		String right = SimpleCalculate( expression.substring( expression.indexOf( "!=" ) + 2 ) );
    		if ( SharedMethods.isString( left ) && SharedMethods.isString( right ) ) {
    			return !left.equals( right );
    		} else if ( SharedMethods.isNumeric( left ) && SharedMethods.isNumeric( right ) ) {
    			return Float.parseFloat( left ) != Float.parseFloat( right ); 
    		} else {
    			throw new ExceptionMessage( "‘!=’两边类型不同，不能比较是否相等", this.lineNumber );
    		}
    	} else if ( expression.contains( "<" ) ) {
    		String left = SimpleCalculate( expression.substring( 0, expression.indexOf( "<" ) ) );
    		String right = SimpleCalculate( expression.substring( expression.indexOf( "<" ) + 1 ) );
    		if ( SharedMethods.isString( left ) || SharedMethods.isString( right ) ) {
    			throw new ExceptionMessage( "字符串不能用于比较小于等于(<)", this.lineNumber );
    		} else {
    			return Float.parseFloat( left ) < Float.parseFloat( right ); 
    		}
    	} else if ( expression.contains( ">" ) ) {
    		String left = SimpleCalculate( expression.substring( 0, expression.indexOf( ">" ) ) );
    		String right = SimpleCalculate( expression.substring( expression.indexOf( ">" ) + 1 ) );
    		if ( SharedMethods.isString( left ) || SharedMethods.isString( right ) ) {
    			throw new ExceptionMessage( "字符串不能用于比较小于等于(>)", this.lineNumber );
    		} else {
    			return Float.parseFloat( left ) > Float.parseFloat( right ); 
    		}
    	} else if ( expression.equals( "1" ) ) {
    		return true;
    	} else if ( expression.equals( "0" ) ) {
    		return false;
    	} else {
    		throw new ExceptionMessage( expression + " 表达式有错误" , this.lineNumber );
    	}
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
    private String removeParentheses(String string){
    	string = string.trim();
    	while(string.startsWith("(") && string.endsWith(")") && SharedMethods.indexOfMatching(string, 0,'(',')') == string.length()-1)
    		string = string.substring(1,string.length()-1);
    	return string;
    }
    
    
    /**
    *作用:用type分割字符串
    *<p>
    *例
    *<blockquote><pre>
    *  ArrayList<String> result = GenArrayFromCondition("!(1||0)||1&&0||(a>0)","\\|\\|");
    *  result.get(0);
    *=> !(1||0)  
    *  result.get(1);
    *=> 1&&0
    *  result.get(2);
    *=> (a>0)
    *</pre></blockquote>
    * @param      conditionString   要分解的字符串.
    * @param      type     分割的标志.
    * @return     ArrayList<String>.
    */
    private ArrayList<String> GenArrayFromCondition(String conditionString, String type){
    	String[] arr = conditionString.split(type);
    	ArrayList<String> result = new ArrayList<String>();
    	int i = 0;
    	String tempString = "";
    	while(i<arr.length){
    		tempString = arr[i];
    		while( Count(tempString,'(') != Count(tempString,')') ){
    			i++;
    			if(i >= arr.length)
    				break;
    			tempString+= type + arr[i];
    		}
    		result.add(tempString.replace("\\|\\|", "||"));//modified by chenxp on 2014-7-9
    		i++;
    	}
    	return result;
    }
    
    
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
    private int Count(String string, char target){
    	int num = 0;
    	for(char temp:string.toCharArray())
    		if(temp == target)
    			num++;
       	return num;
    }
    


// public  public public  public public  public public  public public  public public  public public  public
    
    
	public VariableTable getVariableTable() {
		return this.variableTable;
	}
	
	public void setVariableTable( VariableTable variableTable ) {
		this.variableTable = variableTable;
	}
	
	/**
	 * 从给出条件Result来判断条件true还是false
	 * <P>
	 * 例
	 * <blockquote><pre>
	 * Result("0&&1") return false
	 * Result("0||1") return true
	 * </pre></blockquote>
	 * @param conditionString
	 * @return 条件的真假
	 */
    public boolean Result ( String conditionString ) throws ExceptionMessage {
    	
        conditionString = removeParentheses ( conditionString );
        boolean resultOr = false;
        boolean resultAnd = true;
        int i;
        ArrayList<String> arrayOr = GenArrayFromCondition( conditionString, "\\|\\|" );
        if ( arrayOr.size() > 1 ) {
        	for ( i = 0; i < arrayOr.size(); i++ ) {
        		resultOr = resultOr || Result( arrayOr.get(i) );
        	}
        	return resultOr;
        } else {
        	ArrayList<String> arrayAnd = GenArrayFromCondition(conditionString, "&&");
        	if ( arrayAnd.size() > 1 ) {
        		for ( i = 0; i < arrayAnd.size(); i++ ) {
        			resultAnd = resultAnd && Result( arrayAnd.get(i) );
        		}
        		return resultAnd;
        	} else if ( arrayAnd.get(0).contains("!") && !arrayAnd.get(0).contains("!=") ) {
        		return !Result( arrayAnd.get(0).substring(1) );
        	} else {
        		return ValueOfExpression( arrayAnd.get(0) );
        	}
        }
    }
    
}
