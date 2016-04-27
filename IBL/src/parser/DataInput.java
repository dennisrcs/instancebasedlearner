package parser;

import java.util.ArrayList;
import java.util.List;

// stores the data provided by input
public class DataInput
{
	// members
	private List<List<String>> data;
	private List<String> numericDataColumns;
	private List<String> uselessColumns;
	private List<String> attributeTypes;
	private String targetAttributeColumn;
	private String missingDataSymbol;
	
	// constructor
	public DataInput(List<List<String>> inputData, String missingDataSymbol)
	{
		this.setNumericDataColumns(RetrieveNumericData(inputData));
		this.setTargetAttributeColumn(RetrieveTargetAttribute(inputData));
		this.setUselessColumn(RetrieveUselessColumn(inputData));
		this.setMissingDataSymbol(missingDataSymbol);
		this.RemoveConfigInfoAndSetData(inputData);
	}

	private List<String> RetrieveUselessColumn(List<List<String>> inputData)
	{
		List<String> target = new ArrayList<String>();
		
		for (int i = 0; i < inputData.get(0).size(); i++)
			if (inputData.get(0).get(i).equals("useless"))
				target.add(inputData.get(1).get(i));
		
		return target;
	}

	// removes the header and set data
	private void RemoveConfigInfoAndSetData(List<List<String>> inputData)
	{
		RemoveConfigAndSetAttrubuteTypes(inputData.remove(0));
		setData(inputData);
	}

	// remove useless type from 
	private void RemoveConfigAndSetAttrubuteTypes(List<String> inputDataTypes)
	{
		for (int i = 0; i < inputDataTypes.size(); i++)
		{
			if (inputDataTypes.get(i).equals("useless") || inputDataTypes.get(i).equals("target"))
			{
				inputDataTypes.remove(i);
				i--;
			}
		}
		
		setAttributeTypes(inputDataTypes);
	}

	// returns the target attribute in the data set
	private String RetrieveTargetAttribute(List<List<String>> inputData)
	{
		String target = null;
		for (int i = 0; i < inputData.get(0).size(); i++)
		{
			if (inputData.get(0).get(i).equals("target"))
			{
				target = inputData.get(1).get(i);
				break;
			}
		}
			
		return target;
	}

	// returns the numeric columns in the data set
	private List<String> RetrieveNumericData(List<List<String>> inputData)
	{
		List<String> numericDataColumns = new ArrayList<String>();
		
		for (int i = 0; i < inputData.get(0).size(); i++)
			if (inputData.get(0).get(i).equals("numeric"))
				numericDataColumns.add(inputData.get(1).get(i));

		return numericDataColumns;
	}

	public List<List<String>> getData() {
		return data;
	}

	public void setData(List<List<String>> data) {
		this.data = data;
	}

	public List<String> getNumericDataColumns() {
		return numericDataColumns;
	}

	public void setNumericDataColumns(List<String> numericDataColumns) {
		this.numericDataColumns = numericDataColumns;
	}

	public String getTargetAttributeColumn() {
		return targetAttributeColumn;
	}

	public void setTargetAttributeColumn(String targetAttributeColumn) {
		this.targetAttributeColumn = targetAttributeColumn;
	}

	public String getMissingDataSymbol() {
		return missingDataSymbol;
	}

	public void setMissingDataSymbol(String missingDataSymbol) {
		this.missingDataSymbol = missingDataSymbol;
	}

	public List<String> getUselessColumn() {
		return uselessColumns;
	}

	public void setUselessColumn(List<String> uselessColumnData) {
		this.uselessColumns = uselessColumnData;
	}

	public List<String> getAttributeTypes() {
		return attributeTypes;
	}

	public void setAttributeTypes(List<String> attributeType) {
		this.attributeTypes = attributeType;
	}

}
