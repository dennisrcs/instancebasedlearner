package experimentation;

import java.util.ArrayList;
import java.util.List;

import input.UserInputReader;
import metrics.Metrics;
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
		List<Double> accuracies = new ArrayList<Double>();
		System.out.println("Running...");
		for (int iteration = 0; iteration < K_FOLD; iteration++)
		{
			// wraps the neural network execution
			InstanceBasedExecutor executor = new InstanceBasedExecutor();
			
			// preprocess phase
			DataSplitter data = executor.preprocess(userInput.getFilename(), userInput.getMissingDataSymbol(), iteration);
			
			Metrics.CalculateInformationGain(data.getTestingData());
			
			// testing phase
			double accuracy = executor.evaluate(userInput.getNeighborsSize(), data.getAttributeTypes(), data.getTrainingData(), data.getTestingData());
			accuracies.add(accuracy);
			
			//System.out.println(accuracy);
		}
		
		StatisticsCalculator calc = new StatisticsCalculator();
		calc.performStatistics(accuracies);
	}
}

