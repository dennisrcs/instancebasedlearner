package experimentation;

import java.util.List;

import featureselection.FeatureExtractionExecutor;
import model.Example;
import parser.DataSplitter;
import preprocessing.PreprocessTaskExecutor;
import testing.EvaluationTaskExecutor;

public class InstanceBasedExecutor
{
	// preprocessing phase
	public List<Example> preprocess(String filename, String missingDataSymbol)
	{
		PreprocessTaskExecutor executor = new PreprocessTaskExecutor();
		return executor.execute(filename, missingDataSymbol);
	}
	
	// splits the input data into training and testing data
	public DataSplitter splitData(List<Example> examples, int iteration)
	{
		DataSplitter dataSplitter = new DataSplitter();
		dataSplitter.split(examples, iteration);
		
		return dataSplitter;
	}

	// extract features from input data
	public List<Example> extractFeatures(List<Example> data, int featuresToRemoveNumber)
	{
		FeatureExtractionExecutor executor = new FeatureExtractionExecutor();
		return executor.execute(data, featuresToRemoveNumber);
	}
	
	// evaluates the testing data in respect to the training data
	public double evaluate(int neighborsSize, List<Example> trainingData, List<Example> testingData) 
	{
		EvaluationTaskExecutor executor = new EvaluationTaskExecutor();
		return executor.execute(neighborsSize, trainingData, testingData);
	}
	
}
