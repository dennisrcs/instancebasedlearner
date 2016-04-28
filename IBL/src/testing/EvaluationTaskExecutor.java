package testing;

import java.util.List;

import classifier.KNN;
import model.Example;

// Executes the evaluation of the KNN
public class EvaluationTaskExecutor
{
	// computes the accuracy of the classifier given the training and testing data
	public double execute(int neighborsSize, List<Example> trainingData, List<Example> testingData)
	{
		// initialization
		KNN knn = new KNN(neighborsSize);
		double accuracy = 0;
		int correctClassifications = 0;
		
		// compute how many correct classifications knn made
		for (int i = 0; i < testingData.size(); i++)
		{
			String nearestClass = knn.getNearestClass(trainingData, testingData.get(i));
			
			if (nearestClass.equals(testingData.get(i).getTarget()))
				correctClassifications += 1;
		}
		
		accuracy = correctClassifications / (double) testingData.size();
		return accuracy;
	}
}
