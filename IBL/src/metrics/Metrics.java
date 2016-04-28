package metrics;

import java.util.List;

import model.Example;

// metrics facade
public class Metrics
{
	// calculates the entropy of the data set
	public static double CalculateEntropy(List<String> set)
	{
		Entropy entropy = new Entropy();
		return entropy.CalculateEntropy(set);
	}
	
	// calculates the information gain of a specific column of the data set
	public static List<Double> CalculateInformationGain(List<Example> set)
	{
		InformationGain infoGainCalculator = new InformationGain();
		return infoGainCalculator.CalculateInformationGainAllAttributes(set);
	}
}