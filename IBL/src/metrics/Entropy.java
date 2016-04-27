package metrics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entropy
{
	// calculates the entropy for a given input
	public double CalculateEntropy(List<String> input)
	{
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		for (int i = 0; i < input.size(); i++)
		{
			if (map.containsKey(input.get(i)))
			{
				int value = map.get(input.get(i));
				value += 1;
				map.put(input.get(i), value);
			}
			else
				map.put(input.get(i), 1);
		}
		
		ArrayList<Integer> entropyInput = new ArrayList<Integer>();
		for (Map.Entry<String, Integer> entry : map.entrySet())
			entropyInput.add(entry.getValue());
		
		return _CalculateEntropyInt(entropyInput);
	}
	
	private double _CalculateEntropyInt(ArrayList<Integer> input)
	{	
		double entropy = 0;
		double numberElements = 0;
		
		for (int i = 0; i < input.size(); i++)
			numberElements += input.get(i);
		
		for (int i = 0; i < input.size(); i++)
			entropy -= (input.get(i)/numberElements) * 
					Math.log10(input.get(i)/numberElements) / Math.log10(2);
			
		return entropy;
	}
}
