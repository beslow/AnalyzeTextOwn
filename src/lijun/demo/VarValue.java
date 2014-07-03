package lijun.demo;

public class VarValue {
	
	private String varValueName;
	private String value;
	private String valueType;
	private String flag;
	private int begin=0;
	private int end=0;
	
	public VarValue(String varValueName,String value,String valueType,String flag,int begin,int end){
		
		this.varValueName = varValueName;
		this.value = value;
		this.valueType = valueType;
		this.flag  = flag;
		this.begin = begin;
		this.end  = end;
	}

	public String getVarValueName() {
		return varValueName;
	}

	public void setVarValueName(String varValueName) {
		this.varValueName = varValueName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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
