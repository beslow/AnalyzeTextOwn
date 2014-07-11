package lijun.demo;

public class Variable {
	/*
	 * 符号名称
	 */
	private String name;
	
		
	/**
	 * 变量的值
	 */
	private String value;
	/*
	 * 常量，变量类型
	 * 0代表 int
	 * 1代表 float
	 * 2代表String
	 */
	private int type;
    
    /*
     * 符号所在层次
     */
	private int level;
    /**
     * 变量的起始位置
     */
	private int begin;
     /**
      * 变量的结束位置
      */
	private int end;
    /**
     * 
     * @param name
     * @param kind
     * @param value
     * @param type
     * @param level
     * @param begin
     * @param end
     */
    public Variable(String name,String value,int type,int level,int begin,int end) {
		// TODO Auto-generated constructor stub
    	this.name = name;    
    	this.value = value;
    	this.type = type;
    	this.level = level;
    	this.begin = begin;
    	this.end = end;
	}
    
    public Variable( Variable variable ) {
    	this.name = variable.name;    
    	this.value = variable.value;
    	this.type = variable.type;
    	this.level = variable.level;
    	this.begin = variable.begin;
    	this.end = variable.end;
    }
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getBegin() {
		return begin;
	}
	public void setBegin(int begin) {
		this.begin = begin;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
   
}
