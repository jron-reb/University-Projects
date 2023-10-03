public class StudentRecord {

	private Student student;

	private Module module;

	private double[] marks;

	private double finalScore;

	private Boolean isAboveAverage;

	//Constructor of a student record
	public StudentRecord (Student student, Module module, double[] marks, double finalScore, Boolean isAboveAverage) {
		this.student = student;
		this.module = module;
		this.marks = marks;
		if (0 > finalScore || finalScore > 100){
			System.err.println("You input final module score of " + finalScore + " for the student with an ID of " + student.getId() + " but the final score of a student in a module must be between 0 and 100.");
			System.exit(1);
		} // no else necessary since program will stop if an error occurs, if not then it will continue as normal
		this.finalScore = finalScore;
		this.isAboveAverage = isAboveAverage;
	}

	//methods:
	//method: get the module year of a student record
	public int getModuleYear () {
		return module.getYear();
	}
	//method: get the module term of a student record
	public byte getModuleTerm () {
		return module.getTerm();
	}
	//method: get the final score of a student record
	public double getFinalScore() {
		return finalScore;
	}
	//method: get the module of a student record
	public Module getModule() {
		return module;
	}

	//method: get the module of a student record
	public Student getStudent() {
		return student;
	}
	//method: to string method
	public String toString () {
		return "Student record[ " + student + ", " + module + ", marks" + marks + ", final score " + finalScore + ", is above average " + isAboveAverage + "]";
	}
}
