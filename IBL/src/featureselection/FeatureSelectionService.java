package featureselection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import metrics.Metrics;
import model.Example;
import util.MathUtil;

// provides services for features selection
public class FeatureSelectionService
{
	// calculates the information gain in the input data
	public static List<Double> calculateInformationGain(List<Example> data)
	{
		return Metrics.CalculateInformationGain(data);
	}

	// get the indices to be removed
	public static List<Integer> getIndicesToBeRemoved(List<Double> infogains, int featuresToBeRemovedNumber)
	{
		List<Integer> indicesToBeRemoved = new ArrayList<Integer>();
		List<Double> infoGainsClone = new ArrayList<Double>();
		
		// clones the infogains list
		for (int i = 0; i < infogains.size(); i++)
			infoGainsClone.add(infogains.get(i).doubleValue());
		
		while (featuresToBeRemovedNumber > 0)
		{
			int index = MathUtil.findMinIndex(infoGainsClone);
			indicesToBeRemoved.add(index);
			infoGainsClone.set(index, Double.MAX_VALUE);
			featuresToBeRemovedNumber--;
		}
		
		// sorts the indices to be removed
		Collections.sort(indicesToBeRemoved);
		return indicesToBeRemoved;
		
	}

	// removes the attributes with indices listed in 'indicesToBeRemoved'
	public static List<Example> removeAttributesData(List<Example> data, List<Integer> indicesToBeRemoved)
	{
		for (int i = 0; i < data.size(); i++)
			for (int j = 0; j < indicesToBeRemoved.size(); j++)
				data.get(i).getInput().remove(indicesToBeRemoved.get(j) - j);
		
		return data;
	}
}
