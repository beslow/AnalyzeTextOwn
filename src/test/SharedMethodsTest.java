package test;
import lijun.demo.*;
public class SharedMethodsTest {
	private Test test = new Test();
	
	public SharedMethodsTest() {
		System.out.println("SharedMethods的测试：");
		System.out.println("isString");
		test.status.set(0, "isString测试");
		test.doTest( SharedMethods.isString( "\"abc\"" ) == true, test.status, 1);
		test.doTest( SharedMethods.isString( "\"a\"bc\"" ) == false, test.status, 2);
		
		System.out.println("Split");
		test.status.set(0, "Split");
		test.doTest( SharedMethods.Split( "ab", '+' )[0].equals("ab"), test.status, 1);
		test.doTest( SharedMethods.Split( "a+b", '+' )[0].equals("a"), test.status, 2);
		test.doTest( SharedMethods.Split( "a+b", '+' )[1].equals("b"), test.status, 3);
		test.doTest( SharedMethods.Split( "+ab", '+' )[0].equals("ab"), test.status, 4);
		test.doTest( SharedMethods.Split( "ab+", '+' )[0].equals("ab"), test.status, 5);
		
		System.out.println("CountOfKeysInText");
		test.status.set(0, "CountOfKeysInText");
		System.out.println(SharedMethods.CountOfKeysInText("\"aa{aaa\"",'{'));
		test.doTest( SharedMethods.CountOfKeysInText("{{{\"aa{aaa\"",'{') == 1, test.status, 1);
		System.out.println(SharedMethods.CountOfKeysInText("{{{\"aaaaa\"",'"'));
		test.doTest( SharedMethods.CountOfKeysInText("{{{\"aaaaa\"",'"') == 0, test.status, 2);
		System.out.println(SharedMethods.CountOfKeysInText("{{{\"aa\\\"aaa\"",'"'));
		test.doTest( SharedMethods.CountOfKeysInText("{\\\"{{\"aa\\\"aaa\"",'"') == 1, test.status, 3);
		
		System.out.println("indexOfMatching");
		test.status.set(0, "indexOfMatching");
		System.out.println(SharedMethods.indexOfMatching("\"aa\\\"aaa\"", 0, '"', '"'));
		test.doTest( SharedMethods.indexOfMatching("\"aa\\\"aaa\"", 0, '"', '"') == 8, test.status, 1);
		System.out.println(SharedMethods.indexOfMatching("\"aa\\\"a\\\"aa\"", 0, '"', '"'));
		test.doTest( SharedMethods.indexOfMatching("\"aa\\\"a\\\"aa\"", 0, '"', '"') == 10, test.status, 2);
		test.doTest( SharedMethods.indexOfMatching("\"aa\\\"", 0, '"', '"') == -1, test.status, 3);
		
		System.out.println("DistinguishSplitKey");
		test.status.set(0, "DistinguishSplitKey");
		test.doTest( SharedMethods.DistinguishSplitKey("&&&for") == MainKeys.FOR, test.status, 1);
		test.doTest( SharedMethods.DistinguishSplitKey("j&&&for") == MainKeys.INVALID, test.status, 2);
		test.doTest( SharedMethods.DistinguishSplitKey("&&&if") == MainKeys.IF, test.status, 3);
		test.doTest( SharedMethods.DistinguishSplitKey("&&&else{ &&&if }") == MainKeys.ELSE, test.status, 4);
		test.doTest( SharedMethods.DistinguishSplitKey("&&&break") == MainKeys.BREAK, test.status, 5);
		test.doTest( SharedMethods.DistinguishSplitKey(" ***newline***") == MainKeys.NEWLINE, test.status, 6);
		test.doTest( SharedMethods.DistinguishSplitKey("%%%") == MainKeys.STARTCAL, test.status, 7);
		
		//下面输出测试结果
    	System.out.println(test.status.get(1)+"个测试");
    	System.out.println(test.status.get(2)+"个成功");
    	System.out.println(test.status.get(3)+"个失败\n");
	}
}
