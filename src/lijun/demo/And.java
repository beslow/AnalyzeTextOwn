package lijun.demo;

//　代表逻辑“与”操作的And类，表示由两个布尔表达式通过逻辑“与”操作给出一个新的布尔表达式的操作

public class And extends Expression{

	private Expression left, right;
	
	public  And(Expression left,Expression right) {
		// TODO Auto-generated constructor stub
		this.left = left;
		this.right = right;
	}
	
	
	@Override
	public boolean interpret(Context ctx) {
		// TODO Auto-generated method stub
		return left.interpret(ctx) && right.interpret(ctx);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj !=null && obj instanceof And)
		{
			return left.equals(((And)obj).left) && right.equals(((And)obj).right);
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
		return "(" + left.toString() + " && " + right.toString() + ")";
	}

}
