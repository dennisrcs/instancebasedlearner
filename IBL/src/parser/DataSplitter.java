package parser;

import java.util.ArrayList;
import java.util.List;

import model.Example;

// splits the data between training, validation, and testing data
public class DataSplitter
{
	// constants
	private final double TRAINING_TESTING_PROPORTION = 0.9;
	
	// members
	private List<Example> trainingData;
	private List<Example> testingData;
	private List<Example> inputData;
	
	// constructor
	public DataSplitter()
	{
		this.trainingData = new ArrayList<Example>();
		this.testingData = new ArrayList<Example>();
	}
	
	// splits the data between training and validation data
	public void split(List<Example> data, int cv_iteration)
	{
		this.setInputData(data);
		int dataSize = data.size();
		int testingDataSize = dataSize - (int)Math.floor(dataSize * TRAINING_TESTING_PROPORTION);
		
		int startingIndex = testingDataSize * cv_iteration + 1;
		int endIndx = testingDataSize*(cv_iteration+1) + 1;
		
		for (int i = 0; i < dataSize; i++)
		{
			Example aux = data.get(i);
			if (i >= startingIndex && i < endIndx)
				this.testingData.add(aux);
			else
				this.trainingData.add(aux);
		}
	}

	// getters and setters
	public List<Example> getTrainingData() {
		return trainingData;
	}

	public void setTrainingData(List<Example> trainingData) {
		this.trainingData = trainingData;
	}

	public List<Example> getTestingData() {
		return testingData;
	}

	public void setTestingData(List<Example> testingData) {
		this.testingData = testingData;
	}

	public List<Example> getInputData() {
		return inputData;
	}

	public void setInputData(List<Example> inputData) {
		this.inputData = inputData;
	}
}
