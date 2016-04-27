package metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Example;

public class InformationGain
{
	// calculates information gain for each attribute
	public List<Double> CalculateInformationGainAllAttributes(List<Example> dataset)
	{
		List<Double> infoGains = new ArrayList<Double>();
		Example firstEx = dataset.get(0);

		for (int i = 0; i < firstEx.getInput().size(); i++)
			infoGains.add(CalculateInformationGain(dataset, i));
		
		return infoGains;
	}
	
	// calculates the information gain of the set
	public double CalculateInformationGain(List<Example> dataset, int colIdx)
	{
		double infoGain = 0;
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		buildAttributeOutputMap(dataset, colIdx, map);
		
		ArrayList<String> outputColumn = new ArrayList<String>();

		for (int i = 1; i < dataset.size(); i++)
		{
			String output = dataset.get(i).getTarget();
			outputColumn.add(output);
		}
		
		infoGain = Metrics.CalculateEntropy(outputColumn);
		for (Map.Entry<String, List<String>> entry : map.entrySet())
		{
			List<String> value = entry.getValue();
			double proportion = (double)value.size() / outputColumn.size();
			infoGain -= proportion * Metrics.CalculateEntropy(value);;
		}
			
		return infoGain;
	}
	
	// maps different values of an attribute for its corresponding output
	public void buildAttributeOutputMap(List<Example> set, int colIdx, Map<String, List<String>> map)
	{
		for (int i = 1; i < set.size(); i++)
		{
			String key = set.get(i).getInput().get(colIdx);
			String value = set.get(i).getTarget();
			
			List<String> outputs = null;

			if (map.containsKey(key))
				outputs = map.get(key);
			else
				outputs = new ArrayList<String>();
			
			outputs.add(value);
			map.put(key, outputs);
		}
	}
}
