package preprocessing;

import java.util.ArrayList;
import java.util.List;

import model.Example;
import parser.DataInput;
import parser.DataParser;
import parser.DataSplitter;
import util.DataUtil;

// Preprocess phase executor
public class PreprocessTaskExecutor
{
	// members
	private DataParser parser;
	
	// constructor
	public PreprocessTaskExecutor()
	{
		this.parser = new DataParser();
	}
	
	// executes the preprocess phase
	public DataSplitter execute(String filename, String missingDataSymbol, int iteration)
	{
		List<List<String>> parsedData = parser.parse(filename);
		DataInput inputData = new DataInput(parsedData, missingDataSymbol);
		List<Example> examples = new ArrayList<Example>();

		PreprocessingHelper helper = new PreprocessingHelper(inputData);

		// executes all preprocessing tasks
		helper.removeUselessData();
		helper.handleMissingData();
		helper.handleTargetAttribute();
		helper.normalizeData();

		// populates example and split data accordingly
		populateExamples(examples, inputData);

		DataSplitter dataSplitter = new DataSplitter(inputData.getAttributeTypes());
		dataSplitter.split(examples, iteration);
		
		return dataSplitter;
	}
	
	// populate examples according to the data set
	private void populateExamples(List<Example> examples, DataInput inputData)
	{
		List<List<String>> data = inputData.getData();
		String targetColumn = inputData.getTargetAttributeColumn();
		int targetColIdx = DataUtil.findIndex(data, targetColumn);
		
		// sets the data to the correct portion (splits between input and target)
		for (int i = 1; i < data.size(); i++)
		{
			List<String> inputValues = new ArrayList<String>();
			String targetValues = null;
			
			for (int j = 0; j < data.get(i).size(); j++)
				if (targetColIdx == j)
					targetValues = data.get(i).get(j);
				else
					inputValues.add(data.get(i).get(j));
			
			Example ex = new Example(inputValues, targetValues);
			examples.add(ex);
		}
	}
}
