package lijun.demo;

public class ForBlock extends BaseBlock {
	 private String init;
	 private String condition;
	 private String change;
	 public ForBlock(
			String[] strline, 
			int beginIndex, 
			int endIndex,
			int level,
			VariableTable variableTable,
			String init,
			String condition,
			String change) {
		super(strline, beginIndex, endIndex, level, variableTable);
		// TODO Auto-generated constructor stub
		this.init = init;
		this.condition  = condition;
		this.change = change;
	 }
	 
	 public  String Result() throws ExceptionMessage {
		 Condition condition = new Condition( this.variableTable, this.beginIndex);
		 if ( init.contains( "=" ) ) {
			 String tempName = init.substring( 0, init.indexOf('=') ).trim();
			 String tempValue = init.substring( init.indexOf( '=' ) + 1 ).trim();
			 if ( this.variableTable.IsExist(tempName) ) {
				 
				 this.variableTable.SetValueByName(tempName, condition.SimpleCalculate( tempValue ) );
			 } else if ( !init.trim().equals( "" ) ) {
				 throw new ExceptionMessage(" 初始化条件 " + init + " 有误 ", this.beginIndex);
			 }
		 }
		 
		 String tempName = change.substring( 0, change.indexOf('=') ).trim();
		 String tempValue = change.substring( change.indexOf( '=' ) + 1 ).trim();
		 String result = "";
		 while( condition.Result( this.condition ) ) {
			 AnalyzeText analyzeText = new AnalyzeText( this.level, this.variableTable );
			 result += analyzeText.AnalyzeBlock( this.strline, this.beginIndex + 1, this.endIndex - 1 );
			 if ( this.variableTable.IsExist(tempName) ) {
				 this.variableTable.SetValueByName(tempName, condition.SimpleCalculate( tempValue ) );
			 }
			 condition = new Condition( this.variableTable, this.beginIndex);
		 }
		 
		 return result;
	 }
	 
	 
}
