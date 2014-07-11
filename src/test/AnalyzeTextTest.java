package test;

import lijun.demo.*;

public class AnalyzeTextTest {
	private Test test = new Test();
	
	public AnalyzeTextTest() {
		AnalyzeText analyzeText = new AnalyzeText( 0, new VariableTable() );
		System.out.println("AnalyzeTextTestµÄ²âÊÔ£º");
		System.out.println("Analyze");
		test.status.set(0, "Analyze" );
		//String string= "&&&variable i = 0 \r &&&for(i=0;i<5;i=i+1){\r \"a\" \r }";
		//System.out.println(AnalyzeTextTest.class.getResource("").getPath()+"test.txt"  );
		String[] lineArr = test.ReadFile( AnalyzeTextTest.class.getResource("").getPath()+"test.txt" ); 
		String string = "";
		for (int i = 0; i < lineArr.length; i++ ) {
			string += lineArr[i] + "\r";
		}
		System.out.println( analyzeText.Analyze( string ) );
	}
}
