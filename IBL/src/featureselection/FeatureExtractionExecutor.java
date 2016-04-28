package featureselection;

import java.util.List;

import model.Example;

public class FeatureExtractionExecutor
{
	// execute the feature selection
	public List<Example> execute(List<Example> data, int featuresToRemoveNumber)
	{
		if (featuresToRemoveNumber > 0)
		{
			List<Example> result = null;
			
			// calculates the information gain with the input data
			List<Double> infogains = FeatureSelectionService.calculateInformationGain(data);
			
			if (featuresToRemoveNumber >= infogains.size())
				throw new IllegalArgumentException("number of features to be removed equal or higher to the actual attribute numbers");
			
			// selects indices to be removed
			List<Integer> indicesToBeRemoved = FeatureSelectionService.getIndicesToBeRemoved(infogains, featuresToRemoveNumber);
			
			indicesToBeRemoved.toString();
			
			// removes the data related to the attribute found with the indices
			result = FeatureSelectionService.removeAttributesData(data, indicesToBeRemoved);
			
			return result;
		}
		
		return data;
	}
}
