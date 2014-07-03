package lijun.demo;

//一个Variable对象代表一个有名变量

public class Variable extends Expression {
	
	
	private String name;
	
	public  Variable(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public boolean interpret(Context ctx) {
		// TODO Auto-generated method stub
		
		return ctx.lookup(this);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null && obj instanceof Variable )
		{
			return this.name.equals((Variable)obj);
		}
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		
		return this.name.hashCode();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.name;
	}
	

}
