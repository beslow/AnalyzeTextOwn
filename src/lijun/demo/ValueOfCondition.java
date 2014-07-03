package lijun.demo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ValueOfCondition {
	
	/**
	 * 从给出条件conditionString来判断条件true还是false
	 * <P>
	 * 例
	 * <blockquote><pre>
	 * GetValueOfCondition("0&&1") return false
	 * GetValueOfCondition("0||1") return true
	 * </pre></blockquote>
	 * @param conditionString
	 * @return 条件的真假
	 */
	static private Map<String,VarValue> map = new HashMap<String,VarValue>();
	
	public void push(String var, VarValue value) {
		map.put(var, value);
	}

	public VarValue getVarValue(String var) throws IllegalArgumentException {

		VarValue value = map.get(var);
		if (value == null) {
			throw new IllegalArgumentException();
		}

		return value;

	}
	
    static boolean GetValueOfCondition ( String conditionString ) {
        conditionString = removeParentheses ( conditionString );
        boolean resultOr = false;
        boolean resultAnd = true;
        int i;
        ArrayList<String> arrayOr = GenArrayFromCondition( conditionString, "\\|\\|" );
        if ( arrayOr.size() > 1 ) {
        	for ( i = 0; i < arrayOr.size(); i++ ) {
        		resultOr = resultOr || GetValueOfCondition( arrayOr.get(i) );
        	}
        	return resultOr;
        } else {
        	ArrayList<String> arrayAnd = GenArrayFromCondition(conditionString, "&&");
        	if ( arrayAnd.size() > 1 ) {
        		for ( i = 0; i < arrayAnd.size(); i++ ) {
        			resultAnd = resultAnd && GetValueOfCondition( arrayAnd.get(i) );
        		}
        		return resultAnd;
        	} else if ( arrayAnd.get(0).contains("!") && !arrayAnd.get(0).contains("!=") ) {
        		return !GetValueOfCondition( arrayAnd.get(0).substring(1) );
        	} else {
        		return ValueOfExpression( arrayAnd.get(0) );
        	}
        }
    }
    
    static boolean ValueOfExpression ( String expression ) {
    	expression = removeParentheses( expression );
    	if ( expression.equals("1") ) {
    		return true;
    	} else {
    		return false;
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
    static String removeParentheses(String string){
    	string = string.trim();
    	while(string.startsWith("(") && string.endsWith(")") && indexOfMatching(string, 0,'(',')') == string.length()-1)
    		string = string.substring(1,string.length()-1);
    	return string;
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
    static int indexOfMatching(String string, int fromIndex, char matched, char matcher){
    	if(string.trim()==""){
    		return -1;
    	}else if(fromIndex<0){
    		fromIndex = 0;
    	}else if(fromIndex>=string.length()){
    		return -1;
    	}    		 	
    	int matchedIndex;
    	int mark = fromIndex;
    	do{
    		matchedIndex = string.indexOf(matcher, mark+1);
    		mark = matchedIndex;
    	}while(count(string.substring(fromIndex+1, matchedIndex),matched) != count(string.substring(fromIndex+1, matchedIndex),matcher));
    	return matchedIndex;
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
    static ArrayList<String> GenArrayFromCondition(String conditionString, String type){
    	String[] arr = conditionString.split(type);
    	ArrayList<String> result = new ArrayList<String>();
    	int i = 0;
    	String tempString = "";
    	while(i<arr.length){
    		tempString = arr[i];
    		while( count(tempString,'(') != count(tempString,')') ){
    			i++;
    			if(i >= arr.length)
    				break;
    			tempString+= type + arr[i];
    		}
    		result.add(tempString);
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
    static int count(String string, char target){
    	int num = 0;
    	for(char temp:string.toCharArray())
    		if(temp == target)
    			num++;
       	return num;
    }
    
    /**
     * 测试
     */
    public void test(){
    	ArrayList status = new ArrayList();
    	status.add("functionName");
    	status.add(new Integer(0));
    	status.add(new Integer(0));
    	status.add(new Integer(0));
    	status.add(new Integer(0));
    	
    	VarValue varValue1 = new VarValue("a","5", "int", "G", 0, 0);
    	VarValue varValue2 = new VarValue("b","10", "int", "G", 0, 0);
    	VarValue varValue3 = new VarValue("c","15", "int", "G", 0, 0);
    	
    	push("a", varValue1);
    	push("b", varValue2);
    	push("c", varValue3);
    	
    	
    	//在这里添加测试用例
    	//VarValue test
    	System.out.println("VarValue test");
    	status.set(0, "VarValue test");
    	
        System.out.println("VarValue's value:" + getVarValue("a").getValue());
        
        System.out.println( Integer.parseInt(getVarValue("a").getValue()) > Integer.parseInt(getVarValue("b").getValue()) );
        
    	//count
    	System.out.println("count");
    	status.set(0, "count");
    	doTest(count("aaabbccc",'a') == 3, status,1);
    	doTest(count("aaabbccc",'e') == 0, status,2);
    	doTest(count("",'a') == 0, status,3);
    	//GenArrayFromCondition
    	System.out.println("GenArrayFromCondition");
    	status.set(0, "GenArrayFromCondition");
    	ArrayList<String> result = new ArrayList<String>();
    	result.add("1");
    	result.add("0");
    	doTest(GenArrayFromCondition("1||0","\\|\\|").equals(result), status,1);
    	result.clear();
    	result.add("1");
    	result.add("0");
    	doTest(GenArrayFromCondition("1&&0","&&").equals(result), status,2);
    	result.clear();
    	result.add("(1\\|\\|0)");
    	result.add("1&&0");
    	result.add("0");
    	//result.trimToSize();
    	doTest(GenArrayFromCondition("(1||0)||1&&0||0","\\|\\|").equals(result), status,3);
    	result.clear();
    	result.add("!(1\\|\\|0)");
    	result.add("1&&0");
    	result.add("(a>0)");
    	doTest(GenArrayFromCondition("!(1||0)||1&&0||(a>0)","\\|\\|").equals(result), status,4);
    	//indexOfMatching
    	System.out.println("indexOfMatching");
    	status.set(0, "indexOfMatching");
    	doTest(indexOfMatching("(a>b)",0,'(',')') == 4, status,1);
    	doTest(indexOfMatching("((a>b))",0,'(',')') == 6, status,2);
    	doTest(indexOfMatching("(a>b&&(c>d))||(e<f)",0,'(',')') == 11, status,3);
    	doTest(indexOfMatching("((a>b&&(c>d))||(e<f))",0,'(',')') == 20, status,4);
    	//removeParentheses
    	status.set(0, "removeParentheses");
    	System.out.println("removeParentheses");//String t = removeParentheses(" (a>b&&c>d) ");
    	doTest(removeParentheses(" (a>b&&c>d) ").equals("a>b&&c>d"), status,1);
    	doTest(removeParentheses(" (a>b&&c>d)||(e==f) ").equals("(a>b&&c>d)||(e==f)"), status,2);
    	doTest(removeParentheses(" (a>b&&c>d) ").equals("a>b&&c>d"), status,3);
    	//GetValueOfCondition
    	status.set(0, "GetValueOfCondition");
    	System.out.println("GetValueOfCondition");
    	doTest(GetValueOfCondition("1&&0") == false , status, 1);
    	doTest(GetValueOfCondition("1||0") == true , status, 1);
    	doTest(GetValueOfCondition("(1||0)||(0)||1") == true , status, 1);
    	doTest(GetValueOfCondition("(1||0)&&(0||0)") == false , status, 1);
    	
    	//下面输出测试结果
    	System.out.println(status.get(1)+"个测试");
    	System.out.println(status.get(2)+"个成功");
    	System.out.println(status.get(3)+"个失败");
    	
    	// add by lijun
    	
    	
    	
    }
    static void doTest(boolean result,ArrayList status, int index){
    	if(result){
    		status.set(1, (Integer)status.get(1)+1);
    		status.set(2, (Integer)status.get(2)+1);
    	}else{
    		System.out.println(status.get(0)+"下第"+index+"个测试出现错误");
    		status.set(1, (Integer)status.get(1)+1);
    		status.set(3, (Integer)status.get(3)+1);
    	}
    }
    
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		test();
//	}
}
