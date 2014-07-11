package lijun.demo;


public class BaseBlock {
	/**
	 * 当前读入的行
	 */
	public String[] strline = null;
	public int beginIndex;
	public int endIndex;
	public int level = 0;
	public  VariableTable variableTable;
	
	
	/**
	 * 
	 * @param strline
	 * @param beginIndex
	 * @param endIndex
	 * @param level
	 * @param variableTable
	 */
	public BaseBlock(String[] strline,int beginIndex,int endIndex,int level, VariableTable variableTable ) {
		// TODO Auto-generated constructor stub
		this.strline = strline;
		this.beginIndex = beginIndex;
		this.endIndex = endIndex;
		this.level = level;
		this.variableTable = new VariableTable(variableTable);	
	}
}
