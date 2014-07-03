package lijun.demo;

//一个Constant对象代表一个布尔常量
public class Constant  extends Expression{

	private boolean value;
	
    public Constant(boolean value) {
		// TODO Auto-generated constructor stub
    	this.value = value;
	}
	
	
	@Override
	public boolean interpret(Context ctx) {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (obj != null && obj instanceof Constant) {
			 
			return this.value = ((Constant)obj).value;
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return this.toString().hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return new Boolean(value).toString();
	}

}
