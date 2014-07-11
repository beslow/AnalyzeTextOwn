package test;
import lijun.demo.*;

public class VariableTableTest {
	private Test test = new Test();
	
	public VariableTableTest () {
		System.out.println("VariableTable�Ĳ��ԣ�");
		System.out.println("��������");
		VariableTable variableTable = new VariableTable();
		Variable var0 = new Variable("a", "1", 0, 0, 0, 0);
		Variable var1 = new Variable("b", "2", 0, 0, 0, 0);
		Variable var2 = new Variable("c", "3", 0, 0, 0, 0);
		variableTable.AddVariable(var0);
		variableTable.AddVariable(var1);
		variableTable.AddVariable(var2);
		VariableTable variableTableCopy = new VariableTable(variableTable);
		test.status.set(0, "VariableTable����");
		test.doTest( variableTableCopy.GetValueByName("a").equals("1"), test.status, 1);
		variableTableCopy.SetValueByName("a", "99");
		test.doTest( variableTable.GetValueByName("a").equals("1"), test.status, 2);
		
		System.out.println("SetValueByName");
		test.status.set(0, "SetValueByName����");
		variableTable.SetValueByName("a", "99");
		test.doTest(variableTable.GetValueByName("a").equals("99"), test.status, 1);
		
		System.out.println("ɾ������");
		variableTable.RemoveVariableByName("b");
		test.doTest( variableTable.IsExist("b") == false, test.status, 1);
		variableTable.RemoveVariableByLevel(0);
		test.doTest( variableTable.IsExist("a") == false, test.status, 2);
		test.doTest( variableTable.IsExist("c") == false, test.status, 3);
		
		
		//����������Խ��
    	System.out.println(test.status.get(1)+"������");
    	System.out.println(test.status.get(2)+"���ɹ�");
    	System.out.println(test.status.get(3)+"��ʧ��\n");
	}
}
