package lijun.demo;

//代表逻辑“非”操作的Not类，代表由一个布尔表达式通过逻辑“非”操作给出一个新的布尔表达式的操作
public class Not extends Expression{

	 private Expression exp;
	 
	 public Not(Expression exp) {
		// TODO Auto-generated constructor stub
		 this.exp = exp;
	}
	@Override
	public boolean interpret(Context ctx) {
		// TODO Auto-generated method stub
		return !exp.interpret(ctx);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null && obj instanceof Not )
		{
			return this.exp.equals(((Not)obj).exp);
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
		return "(! " + exp.toString() + ")";
	}

}
