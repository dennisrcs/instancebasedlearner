package util;

import java.util.List;

// math utilities
public class MathUtil
{
	// constant sigmoid function alpha
	public static List<String> ATTRIBUTES_TYPE;
	private final static String NUMERIC = "numeric";
	private final static String DISCRETE = "discrete";
	
	// find minimum value within a data collection
	public static double findMin(List<Double> data)
	{
		double min = Double.POSITIVE_INFINITY;
		
		for (int i = 0; i < data.size(); i++)
			if (data.get(i) < min)
				min = data.get(i);

		return min;
	}
	
	// find minimum value within a data collection a return its index
	public static int findMinIndex(List<Double> data)
	{
		double min = Double.POSITIVE_INFINITY;
		int index = 0;
		for (int i = 0; i < data.size(); i++)
		{
			if (data.get(i) < min)
			{
				min = data.get(i);
				index = i;
			}
		}
			
		return index;
	}

	// find maximum value within a data collection
	public static double findMax(List<Double> data)
	{
		double max = Double.NEGATIVE_INFINITY;
		
		for (int i = 0; i < data.size(); i++)
			if (data.get(i) > max)
				max = data.get(i);

		return max;
	}
	
	public static double simpleEuclideanDistance(List<String> data1, List<String> data2)
	{
		if (data1.size() != data2.size())
			throw new IllegalArgumentException("data1 and data2 have different sizes");
		else
		{
			double sum = 0;
			String el1;
			String el2;
			
			for (int i = 0; i < data1.size(); i++)
			{
				el1 = data1.get(i);
				el2 = data2.get(i);
				
				if (ATTRIBUTES_TYPE.get(i).equals(NUMERIC))
					sum += Math.pow(Double.parseDouble(el1) - Double.parseDouble(el2), 2);	
				else if (ATTRIBUTES_TYPE.get(i).equals(DISCRETE))
					sum += (el1.equals(el2)) ? 0 : 1;
				else
					throw new IllegalArgumentException("Attribute type should be either numeric or discrete");
			}
			
			return Math.sqrt(sum);
		}
	}
}
