package experimentation;

import java.util.ArrayList;
import java.util.List;

import input.UserInputReader;
import model.Example;
import parser.DataSplitter;
import testing.StatisticsCalculator;

// wraps all execution phases (preprocess, training, and testing)
public class Experimenter
{
	// constant
	private static final int K_FOLD = 10;
	
	// executes the experiment
	public void execute(UserInputReader userInput)
	{
		// wraps the neural network execution
		InstanceBasedExecutor executor = new InstanceBasedExecutor();
		
		// preprocess phase
		List<Example> data = executor.preprocess(userInput.getFilename(), userInput.getMissingDataSymbol());

		// extract features
		List<Example> dataWithExtractedFeatures = executor.extractFeatures(data, userInput.getFeaturesToRemoveNumber());
	
		List<Double> accuracies = new ArrayList<Double>();
		for (int iteration = 0; iteration < K_FOLD; iteration++)
		{
			// splits the data
			DataSplitter dataSplitter = executor.splitData(dataWithExtractedFeatures, iteration);
			
			// testing phase
			double accuracy = executor.evaluate(userInput.getNeighborsSize(), dataSplitter.getTrainingData(), dataSplitter.getTestingData());
			accuracies.add(accuracy);
		}
		
		StatisticsCalculator calculator = new StatisticsCalculator();
		calculator.performStatistics(accuracies);
		
	}
}

