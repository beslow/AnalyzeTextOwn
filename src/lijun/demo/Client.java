package lijun.demo;
//import java.lang.*;
public class Client {

    public static void main(String[] args) {
    	int a =5;
    	int b =3;
    	Constant i = new Constant(a>b);
    	Constant ii = new Constant(a ==b);
        Context ctx = new Context();
        Variable x = new Variable("x");
        Variable y = new Variable("y");
        boolean z = 5>3 ;
        Constant c = new Constant(z);
       // Constant c = new Constant(true);
        ctx.assign(x, false);
        ctx.assign(y, true);
        
        Expression exp1 = new Or(new And(c,x) , new And(y,new Not(x)));
        
       // Expression exp2 = new And(x, new Or(y, c));
        
        Expression exp2 = new Or(new And(x, y),ii);
        
        Expression exp3 = new Not(x);
        
        Expression exp4 = new Or(i,ii);
        
        System.out.println("x=" + x.interpret(ctx));
        System.out.println("y=" + y.interpret(ctx));
        System.out.println(exp1.toString() + "=" + exp1.interpret(ctx));
        System.out.println(exp2.toString() + "=" + exp2.interpret(ctx));
        
        System.out.println(exp3.toString() + "=" + exp3.interpret(ctx));
        
        System.out.println(exp4.toString() + "=" + exp4.interpret(ctx));
        
        System.out.println(ii.toString() + ii.interpret(ctx));
        
        ValueOfCondition aa = new ValueOfCondition();
        
        aa.test();
    }

}
