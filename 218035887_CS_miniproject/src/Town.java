
/**
 * @author Cebo Sithole 218035887
 * Eskom Problem Solution
 *
 */
public class Town implements Comparable<Town>, java.io.Serializable {

	/**
	 * An id for serialising the object
	 */
	private static final long serialVersionUID = 1L;
	//Instance variables
	private String name;
	private int houseHolds;
	private final int DEFAULT_appliPowerRatingSum = 32367; //Watts, on average, default, //estimate‬
	private int appliPowerRatingSum;
	private final int usageTime = 10; //10 seconds, time unit for consumption is second

	//Empty constructor
	public Town()
	{
		name = "";
		houseHolds = 19560; //estimate
		appliPowerRatingSum = DEFAULT_appliPowerRatingSum;
	}
	
	/**
	 * @param name, name of the town
	 * @param homes, number of households in the town
	 * @param Applratingsum, average appliance power rating sum of a household
	 */
	public Town(String name, int homes, int Applratingsum)
	{
		this.name = name;
		this.houseHolds = homes;
		this.appliPowerRatingSum = Applratingsum;
	}
	public Town(String name, int homes)
	{
		this.name = name;
		this.houseHolds = homes;
		this.appliPowerRatingSum = DEFAULT_appliPowerRatingSum;
	}
	
	/**
	 * @return the power usage of this town based on three variables, in kW(megaWatts)
	 */
	public double calculateConsumption()
	{
		//consumption = number of households x sum of appliances power rating x time used = kW/sec
		return houseHolds * (appliPowerRatingSum/1000) * usageTime;
	}
	
	//Getters and setters
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHouseHolds() {
		return houseHolds;
	}

	public void setHouseHolds(int houseHolds) {
		this.houseHolds = houseHolds;
	}


	@Override
	public int compareTo(Town o) {
		if(o.getName().equals(this.getName()) && o.getHouseHolds() == this.getHouseHolds())
		{
			return 0;
		} else {
			return -1;
		}
		
	}

	@Override
	public String toString() {
		return "<"+this.name+"> HouseHolds: <"+ this.houseHolds + ">";
	}

	public int getAppliPowerRatingSum() {
		return appliPowerRatingSum;
	}

	public void setAppliPowerRatingSum(int appliPowerRatingSum) {
		this.appliPowerRatingSum = appliPowerRatingSum;
	}
	
	
}
