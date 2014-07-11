package test;

import lijun.demo.*;

public class ConditionTest {
	private Test test = new Test();
	
	public ConditionTest( ) { 
		VariableTable variableTable = new VariableTable();
		Variable var0 = new Variable("a", "1", 0, 0, 0, 0);
		Variable var1 = new Variable("b", "2", 0, 0, 0, 0);
		Variable var2 = new Variable("c", "cc", 2, 0, 0, 0);
		Variable var3 = new Variable("d", "dd", 2, 0, 0, 0);
		variableTable.AddVariable(var0);
		variableTable.AddVariable(var1);
		variableTable.AddVariable(var2);
		variableTable.AddVariable(var3);
		Condition condition = new Condition( variableTable, 10 );
		System.out.println("Condition的测试：");
		System.out.println("typeCompare");
		test.status.set( 0, "typeCompare" );
		test.doTest(condition.typeCompare("a", "b") == 1, test.status , 1 );
		test.doTest(condition.typeCompare("a", "c") == 0, test.status , 2 );
		test.doTest(condition.typeCompare("c", "\"b\"") == 1, test.status , 3 );
		test.doTest(condition.typeCompare("a", "\"b\"") == 0, test.status , 4 );
		test.doTest(condition.typeCompare("a", "2") == 1, test.status , 5 );
		test.doTest(condition.typeCompare("c", "2") == 0, test.status , 6 );
		test.doTest(condition.typeCompare("a", "1b") == -1, test.status , 7 );
		test.doTest(condition.typeCompare("a", "e") == -1, test.status , 8 );
		
		test.doTest(condition.typeCompare("\"b\"", "c") == 1, test.status , 9 );
		test.doTest(condition.typeCompare("\"b\"", "a") == 0, test.status , 10 );
		test.doTest(condition.typeCompare("\"b\"", "\"b\"") == 1, test.status , 11 );
		test.doTest(condition.typeCompare("\"b\"", "2") == 0, test.status , 12 );
		test.doTest(condition.typeCompare("\"b\"", "1b") == -1, test.status , 13 );
		test.doTest(condition.typeCompare("\"b\"", "e") == -1, test.status , 14 );
		
		test.doTest(condition.typeCompare("1", "a") == 1, test.status , 15 );
		test.doTest(condition.typeCompare("1", "c") == 0, test.status , 16 );
		test.doTest(condition.typeCompare("1", "\"b\"") == 0, test.status , 17 );
		test.doTest(condition.typeCompare("1", "2") == 1, test.status , 18 );
		test.doTest(condition.typeCompare("1", "1b") == -1, test.status , 19 );
		test.doTest(condition.typeCompare("1", "e") == -1, test.status , 20 );
		
		test.doTest(condition.typeCompare("1a", "b") == -1, test.status , 21 );
		test.doTest(condition.typeCompare("1a", "\"b\"") == -1, test.status , 22 );
		test.doTest(condition.typeCompare("1a", "2") == -1, test.status , 23 );
		test.doTest(condition.typeCompare("1a", "1b") == -1, test.status , 24 );
		test.doTest(condition.typeCompare("1a", "e") == -1, test.status , 25 );
		
		test.doTest(condition.typeCompare("e", "b") == -1, test.status , 26 );
		test.doTest(condition.typeCompare("e", "\"b\"") == -1, test.status , 27 );
		test.doTest(condition.typeCompare("e", "2") == -1, test.status , 28 );
		test.doTest(condition.typeCompare("e", "1b") == -1, test.status , 29 );
		test.doTest(condition.typeCompare("e", "e") == -1, test.status , 30 );
		//Variable var0 = new Variable("a", "1", 0, 0, 0, 0);
		//Variable var1 = new Variable("b", "2", 0, 0, 0, 0);
		//Variable var2 = new Variable("c", "cc", 2, 0, 0, 0);
		//Variable var3 = new Variable("d", "dd", 2, 0, 0, 0);
		System.out.println("SimpleCalculate");
		test.status.set( 0, "SimpleCalculate" );
		try {
			test.doTest(condition.SimpleCalculate("a+b").equals("3.0"), test.status , 1 );
			test.doTest(condition.SimpleCalculate("c+\"b\"").equals("\"ccb\""), test.status , 2 );
			test.doTest(condition.SimpleCalculate("a+2").equals("3.0"), test.status , 3 );
			test.doTest(condition.SimpleCalculate("\"b\"+c").equals("\"bcc\""), test.status , 4 );
			test.doTest(condition.SimpleCalculate("\"b\"+\"b\"").equals("\"bb\""), test.status , 5 );
			test.doTest(condition.SimpleCalculate("1+a").equals("2.0"), test.status , 6 );
			test.doTest(condition.SimpleCalculate("1+2").equals("3.0"), test.status , 7 );
			
			test.doTest(condition.SimpleCalculate("a-b").equals("-1.0"), test.status , 8 );
			test.doTest(condition.SimpleCalculate("a-3").equals("-2.0"), test.status , 9 );
			test.doTest(condition.SimpleCalculate("1-b").equals("-1.0"), test.status , 10 );
			test.doTest(condition.SimpleCalculate("5-2").equals("3.0"), test.status , 11 );
			
			test.doTest(condition.SimpleCalculate("a*b").equals("2.0"), test.status , 12 );
			test.doTest(condition.SimpleCalculate("a*3").equals("3.0"), test.status , 13 );
			test.doTest(condition.SimpleCalculate("1*b").equals("2.0"), test.status , 14 );
			test.doTest(condition.SimpleCalculate("5*2").equals("10.0"), test.status , 15 );
			
			test.doTest(condition.SimpleCalculate("a/b").equals("0.5"), test.status , 16 );
			test.doTest(condition.SimpleCalculate("a/5").equals("0.2"), test.status , 17 );
			test.doTest(condition.SimpleCalculate("1/b").equals("0.5"), test.status , 18 );
			test.doTest(condition.SimpleCalculate("5/2").equals("2.5"), test.status , 19 );
			
			test.doTest(condition.SimpleCalculate("b%a").equals("0.0"), test.status , 20 );
			test.doTest(condition.SimpleCalculate("b%1").equals("0.0"), test.status , 21 );
			test.doTest(condition.SimpleCalculate("5%b").equals("1.0"), test.status , 22 );
			test.doTest(condition.SimpleCalculate("13%10").equals("3.0"), test.status , 23 );
			
		} catch ( ExceptionMessage e) {
			System.out.println(e.getMessage());
		} 
		
		try {
			condition.SimpleCalculate("a+c");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			condition.SimpleCalculate("a+\"b\"");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("c+2");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("a+1b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("a+e");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			condition.SimpleCalculate("\"b\"+a");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("\"b\"+2");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("\"b\"+1b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("\"b\"+e");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			condition.SimpleCalculate("1+c");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1+\"b\"");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1+1b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1+e");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1a+b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1a+\"b\"");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1a+2");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1a+1b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("1a+e");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("e+b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("e+\"b\"");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("e+2");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("e+1b");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("e+e");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("a/0");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.SimpleCalculate("a%0");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("ValueOfExpression");
		test.status.set( 0, "ValueOfExpression" );
		//Variable var0 = new Variable("a", "1", 0, 0, 0, 0);
		//Variable var1 = new Variable("b", "2", 0, 0, 0, 0);
		//Variable var2 = new Variable("c", "cc", 2, 0, 0, 0);
		//Variable var3 = new Variable("d", "dd", 2, 0, 0, 0);
		try {
			test.doTest(condition.ValueOfExpression("a==1") == true, test.status , 1 );
			test.doTest(condition.ValueOfExpression("a>=b") == false, test.status , 2 );
			test.doTest(condition.ValueOfExpression("a+5<=b") == false, test.status , 3 );
			test.doTest(condition.ValueOfExpression("a!=b") == true, test.status , 4 );
			test.doTest(condition.ValueOfExpression("\"a\"==\"a\"") == true, test.status , 5 );
			test.doTest(condition.ValueOfExpression("\"a\"!=\"b\"") == true, test.status , 6 );
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		try {
			condition.ValueOfExpression("c>=d");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		try {
			condition.ValueOfExpression("");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		try {
			condition.ValueOfExpression("\"a\"<5");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		try {
			condition.ValueOfExpression("\"a\"==5");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		try {
			condition.ValueOfExpression("\"a\"!=5");		
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		try {
			condition.ValueOfExpression("c==5");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		try {
			condition.ValueOfExpression("a==\"5\"");
		} catch (ExceptionMessage e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println("Result");
		test.status.set( 0, "Result" );
		//Variable var0 = new Variable("a", "1", 0, 0, 0, 0);
		//Variable var1 = new Variable("b", "2", 0, 0, 0, 0);
		//Variable var2 = new Variable("c", "cc", 2, 0, 0, 0);
		//Variable var3 = new Variable("d", "dd", 2, 0, 0, 0);
		try {
			test.doTest(condition.Result("a>0") == true, test.status , 1 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		try {
			test.doTest(condition.Result("a>0&&0") == false, test.status , 2 );
			
			
			
			
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		try {
			test.doTest(condition.Result("a>0&&0||(b==2)") == true, test.status , 3 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		try {
			test.doTest(condition.Result("a>0&&0||(b==2&&(0||c!=\"cc\"))") == false, test.status ,4 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		try {
			test.doTest(condition.Result("(a<b)||a>0&&0||(b==2&&(0||c!=\"cc\"))") == true, test.status , 5 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		try {
			test.doTest(condition.Result("(a<b)||a>0&&0||(b==2&&(0||c!=\"cc\"))") == true, test.status , 6 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		
		try {
			test.doTest(condition.Result("!1&&((a<b)||a>0&&0||(b==2&&(0||c!=\"cc\")))") == false, test.status , 7 );
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		//下面输出测试结果
    	System.out.println(test.status.get(1)+"个测试");
    	System.out.println(test.status.get(2)+"个成功");
    	System.out.println(test.status.get(3)+"个失败\n");
	}
}
