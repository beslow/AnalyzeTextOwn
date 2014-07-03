package lijun.demo;
//代表逻辑“或”操作的Or类，代表由两个布尔表达式通过逻辑“或”操作给出一个新的布尔表达式的操作
public class Or extends Expression {

  private Expression left, right;
	
   public Or(Expression left,Expression right) {
		// TODO Auto-generated constructor stub
	   this.left = left;
	   this.right = right;
	}
	@Override
	public boolean interpret(Context ctx) {
		// TODO Auto-generated method stub
		return  left.interpret(ctx) || right.interpret(ctx);
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(obj != null && obj instanceof Or)
		{
			
			return left.equals(((Or)obj).left) && right.equals(((Or)obj).right);
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
	    return "(" + left.toString() + " || " + right.toString() + ")";
	}

}
