package experimentation;

import java.util.List;

import model.Example;
import parser.DataSplitter;
import preprocessing.PreprocessTaskExecutor;
import testing.EvaluationTaskExecutor;

public class InstanceBasedExecutor
{
	// preprocessing phase
	public DataSplitter preprocess(String filename, String missingDataSymbol, int iteration)
	{
		PreprocessTaskExecutor executor = new PreprocessTaskExecutor();
		return executor.execute(filename, missingDataSymbol, iteration);
	}

	public double evaluate(int neighborsSize, List<String> attributesType, List<Example> trainingData, List<Example> testingData) 
	{
		EvaluationTaskExecutor executor = new EvaluationTaskExecutor();
		return executor.execute(neighborsSize, attributesType, trainingData, testingData);
	}
	
}
