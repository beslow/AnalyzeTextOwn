package lijun.demo;

public class Calculate {
	/**
	 * 成员变量
	 */
	private VariableTable variableTable;
	private int lineNumber;
	
	public Calculate(VariableTable variableTable, int lineNumber) {
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
				//throw new ExceptionMessage( array[0] + "和" + array[1] + "类型不同", this.lineNumber );
				String A = array[0];
				String B = array[1];
				if ( SharedMethods.isString( A ) ) {
					A = A.replace("\"", "");
				} else {
					A = StringToValue( A );
				}
				if ( SharedMethods.isString( B ) ) {
					B = B.replace("\"", "");
				} else {
					B = StringToValue( B );
				}
				return A + B;
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
    
}
