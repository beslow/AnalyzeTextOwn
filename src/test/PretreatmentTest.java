package test;

import lijun.demo.ExceptionMessage;
import lijun.demo.Pretreatment; 
public class PretreatmentTest {
	private Test test = new Test();
	public PretreatmentTest() {
		System.out.println("Pretreatment�Ĳ��ԣ�");
		System.out.println("Reslut");
		test.status.set(0, "Reslut");
		Pretreatment pretreatment = new Pretreatment();
		String[] lineArr = test.ReadFile( PretreatmentTest.class.getResource("").getPath()+"test.txt" ); 
		String string = "";
		for (int i = 0; i < lineArr.length; i++ ) {
			string += lineArr[i] + "\r";
		}
		try {
			String[] result = pretreatment.Result(string);
			for(int i = 0; i < result.length; i++ ) {
				System.out.println( result[i] );
			}
		} catch ( ExceptionMessage e ) {
			System.out.println( e.getMessage() );
		}
		
		//����������Խ��
    	System.out.println(test.status.get(1)+"������");
    	System.out.println(test.status.get(2)+"���ɹ�");
    	System.out.println(test.status.get(3)+"��ʧ��\n");
	}
}
