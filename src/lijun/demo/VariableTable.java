package lijun.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


public class VariableTable {
	
	private HashMap<String, Variable> variableTable = null;
	
	public VariableTable() {
		// TODO Auto-generated constructor stub
		this.variableTable = new HashMap<String, Variable>(10);
	}
	/**
	 * ¿½±´¹¹Ôìº¯Êý
	 * @param vTable
	 */
	public VariableTable(VariableTable vTable)
	{
		if ( vTable == null ) {
			this.variableTable = null;
		} else {
			this.variableTable = new HashMap<String, Variable>();
			Iterator<Entry<String, Variable>> iter= vTable.variableTable.entrySet().iterator();
			  while(iter.hasNext())
			  {
				  Map.Entry<String,Variable> entry = (Map.Entry<String,Variable>)iter.next();
				  this.variableTable.put( entry.getKey(), new Variable( entry.getValue() ) ); 
			  }	
		}
	}
	public HashMap<String, Variable> getVariableTable() {
		return variableTable;
	}
	public void setVariableTable(
			HashMap<String,Variable> variableTable) {
		this.variableTable = variableTable;
	}
	public boolean IsExist(String str)
	{
		try {
			return variableTable.containsKey(str);
		} catch (NullPointerException e) {
			// TODO: handle exception
			return false;
		}	
		
	}
	
	public String GetValueByName(String variableName)
	{
		if (this.variableTable.get( variableName ) == null ) {
			return null;
		} else {
		    return this.variableTable.get( variableName ).getValue();
		}	 	
	}
	
	public int GetTypeByName(String variableName)
	{  
		if (this.variableTable.get( variableName ) == null ) {
			return -1;
		} else {
			return this.variableTable.get( variableName ).getType();
		}	 	
	}
	
	public int GetLevelByName(String variableName)
	{
		if (this.variableTable.get( variableName ) == null ) {
			return 99;
		} else {
			return this.variableTable.get( variableName ).getLevel();
		}
	}

	public void AddVariable(Variable variable)
	{
		 this.variableTable.put(variable.getName(), variable);
	}
	
	public void RemoveVariableByName(String str)
	{
		this.variableTable.remove(str);
	}
	public void RemoveVariableByLevel(int level)
	{   
	  Iterator<Entry<String, Variable>> iter= this.variableTable.entrySet().iterator();
	  while(iter.hasNext())
	  {
		  Map.Entry<String,Variable> entry = (Map.Entry<String,Variable>)iter.next();
		  if(entry.getValue().getLevel() == level)
		  {
			  iter.remove();
		  }		  
	  }		
	}
	
	public void SetValueByName(String variableName,String variableValue)
	{
		if (IsExist(variableName)) {
			this.variableTable.get(variableName).setValue(variableValue);
		}
	}
}
