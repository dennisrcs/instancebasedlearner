package input;

// reads the input provided by the user
public class UserInputReader
{
	// members
	private String filename;
	private String missingDataSymbol;
	private int neighborsSize;
	
	// reads the input provided by the user
	public void read(String[] args)
	{
		// at least one attribute (filename)
		if (args.length >= 2)
		{
			this.setFilename(args[0]);
			this.setNeighborsSize(Integer.parseInt(args[1]));
			
			// if only one attribute, then missing data symbol is assigned its default value "?"
			if (args.length == 2)
				this.setMissingDataSymbol("?");
			else if (args.length == 3)
				this.setMissingDataSymbol(args[2]);
			else
				throw new IllegalArgumentException("Incorret number of arguments.");
		}
		else
			throw new IllegalArgumentException("Please provide data file and K");
	}

	// getters and setters
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMissingDataSymbol() {
		return missingDataSymbol;
	}

	public void setMissingDataSymbol(String missingDataSymbol) {
		this.missingDataSymbol = missingDataSymbol;
	}

	public int getNeighborsSize() {
		return neighborsSize;
	}

	public void setNeighborsSize(int neighborsSize) {
		this.neighborsSize = neighborsSize;
	}
}
