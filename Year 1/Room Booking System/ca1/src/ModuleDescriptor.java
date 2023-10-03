import java.util.Arrays;
public class ModuleDescriptor {

	private String code;

	private String name;

	private double[] continuousAssignmentWeights;

	//Constructor for a module descriptor
	public ModuleDescriptor (String code, String name, double[] continuousAssignmentWeights) {
		//check if the code is null
		if (code == null) {
			System.err.println("Module descriptor code cannot be null ");
			System.exit(1);
		}
		//check if the name is null
		if (name == null) {
			System.err.println("Module descriptor name cannot be null ");
			System.exit(1);
		}
		this.code = code;
		this.name = name;

		//Check that the continuous assignment weights sum up to 1
		double sum = 0;
		for (double i: continuousAssignmentWeights) {
			//Check that each individual continuous assignment weight is non-negative
			if (i < 0) {
				System.err.println("A continuous assignment weight of a module descriptor with the code " + code + " is non-negative.");
				System.exit(1);
			} // no else necessary since program will stop if an error occurs, if not then it will continue as normal
			sum += i;
		}
		if (Math.abs(sum - 1) > 0.00001) {
			System.err.println("The continuous assignment weights of a module descriptor with the code " + code + " is " + sum + ". The continuous assignment weights must sum up to 1.");
			System.exit(1);
		} // no else necessary since program will stop if an error occurs, if not then it will continue as normal
		this.continuousAssignmentWeights = continuousAssignmentWeights;
	}

	//methods:
	//method: get the code of a module
	public String getCode(){
		return code;
	}
	//method: get the name of a module
	public String getName() {
		return name;
	}
	//method: get the continuous assignment weights of a module
	public double[] getContinuousAssignmentWeights(){
		System.out.println(Arrays.toString(continuousAssignmentWeights));
		return continuousAssignmentWeights;
	}
	//method: to string method
	public String toString() {return "Module descriptor[code= " + code + ", name = " + name + ", continuous assignment weights= " + Arrays.toString(continuousAssignmentWeights) + "]";}

}
