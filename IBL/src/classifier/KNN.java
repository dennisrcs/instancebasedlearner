package classifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Example;
import util.MathUtil;

// KNN classifier class
public class KNN
{
	// members
	private int k;

	// constructor
	public KNN (int neighborsNumber)
	{
		k = neighborsNumber;
	}
	
	// computes the distances between the example and the data set
	private List<Double> computeDistances(List<Example> data, Example example)
	{
		List<Double> result = new ArrayList<Double>();
		
		for (int i = 0; i < data.size(); i++)
			result.add(MathUtil.simpleEuclideanDistance(data.get(i).getInput(), example.getInput()));
		
		return result;
	}
	
	// creates a histogram of the classes found for the K nearest neighbors
	private Map<String, Integer> getClassesHistogram(List<Example> data, List<Double> distances)
	{
		Map<String, Integer> classHistogram = new HashMap<>();
		List<Example> dataBkp = new ArrayList<Example>();

		// clone data
		for (int i = 0; i < data.size(); i++)
			dataBkp.add(data.get(i).clone());
		
		for (int i = 0; i < this.k; i++)
		{
			int index = MathUtil.findMinIndex(distances);
			
			// get class found
			String classFound = dataBkp.get(index).getTarget();
			int value;
			
			// updates class histogram
			if (classHistogram.containsKey(classFound))
				value = classHistogram.get(classFound) + 1;
			else
				value = 1;
			
			classHistogram.put(classFound, value);
			
			// removes element found
			distances.remove(index);
			dataBkp.remove(index);
		}
		
		return classHistogram;
	}
	
	// returns the nearest class from that example given
	public String getNearestClass(List<Example> data, Example example)
	{
		String result = "";
		List<Double> distances = computeDistances(data, example);
		Map<String, Integer> classHistogram = getClassesHistogram(data, distances);
		
		int highestClassNumber = 0;
		for (Map.Entry<String, Integer> entry : classHistogram.entrySet())
		{
			if (entry.getValue() > highestClassNumber)
			{
				highestClassNumber = entry.getValue();
				result = entry.getKey();
			}
		}
		
		return result;
	}
}
