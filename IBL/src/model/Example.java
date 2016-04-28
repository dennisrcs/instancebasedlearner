package model;

import java.util.ArrayList;
import java.util.List;

// wraps up a training example
public class Example
{
	// members
	private List<String> input;
	private String target;
	
	// constructor
	public Example(List<String> input, String target)
	{
		this.input = input;
		this.target = target;
	}
	
	// getters and setters
	public List<String> getInput() {
		return input;
	}
	public void setInput(List<String> input) {
		this.input = input;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String toString()
	{
		return input.toString() + " => " +  target;
	}
	
	public Example clone()
	{
		List<String> newInput = new ArrayList<String>();
		for (int i = 0; i < input.size(); i++)
			newInput.add(input.get(i) + "");
		
		Example ex = new Example(newInput, this.target + "");
		return ex;
	}
}
