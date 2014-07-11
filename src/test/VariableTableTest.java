package test;
import lijun.demo.*;

public class VariableTableTest {
	private Test test = new Test();
	
	public VariableTableTest () {
		System.out.println("VariableTableµÄ²âÊÔ£º");
		System.out.println("¿½±´²âÊÔ");
		VariableTable variableTable = new VariableTable();
		Variable var0 = new Variable("a", "1", 0, 0, 0, 0);
		Variable var1 = new Variable("b", "2", 0, 0, 0, 0);
		Variable var2 = new Variable("c", "3", 0, 0, 0, 0);
		variableTable.AddVariable(var0);
		variableTable.AddVariable(var1);
		variableTable.AddVariable(var2);
		VariableTable variableTableCopy = new VariableTable(variableTable);
		test.status.set(0, "VariableTable²âÊÔ");
		test.doTest( variableTableCopy.GetValueByName("a").equals("1"), test.status, 1);
		variableTableCopy.SetValueByName("a", "99");
		test.doTest( variableTable.GetValueByName("a").equals("1"), test.status, 2);
		
		System.out.println("SetValueByName");
		test.status.set(0, "SetValueByName²âÊÔ");
		variableTable.SetValueByName("a", "99");
		test.doTest(variableTable.GetValueByName("a").equals("99"), test.status, 1);
		
		System.out.println("É¾³ı²âÊÔ");
		variableTable.RemoveVariableByName("b");
		test.doTest( variableTable.IsExist("b") == false, test.status, 1);
		variableTable.RemoveVariableByLevel(0);
		test.doTest( variableTable.IsExist("a") == false, test.status, 2);
		test.doTest( variableTable.IsExist("c") == false, test.status, 3);
		
		
		//ÏÂÃæÊä³ö²âÊÔ½á¹û
    	System.out.println(test.status.get(1)+"¸ö²âÊÔ");
    	System.out.println(test.status.get(2)+"¸ö³É¹¦");
    	System.out.println(test.status.get(3)+"¸öÊ§°Ü\n");
	}
}
