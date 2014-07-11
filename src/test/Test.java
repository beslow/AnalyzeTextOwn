package test;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Test {
	
	ArrayList status;
	
	public Test() {
		status = new ArrayList();
		status.add("functionName");
		status.add(new Integer(0));
		status.add(new Integer(0));
		status.add(new Integer(0));
		status.add(new Integer(0));
	}
	
	
	public void doTest(boolean result,ArrayList status, int index){
    	if(result){
    		status.set(1, (Integer)status.get(1)+1);
    		status.set(2, (Integer)status.get(2)+1);
    	}else{
    		System.out.println(status.get(0)+"下第"+index+"个测试出现错误");
    		status.set(1, (Integer)status.get(1)+1);
    		status.set(3, (Integer)status.get(3)+1);
    	}
    }
	
	public String[] ReadFile( String filePath ) {
		ArrayList<String> list=new ArrayList<String>();
		try {
			String encoding="GBK";
			File file=new File(filePath);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(
				new FileInputStream(file),encoding);//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while((lineTxt = bufferedReader.readLine()) != null){
					list.add( lineTxt );
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}		
		int size=list.size();  
		return (String[])list.toArray(new String[size]);
	}
}
