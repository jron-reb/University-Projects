public class Module {

	private int year;

	private byte term;

	private ModuleDescriptor module;

	private StudentRecord[] records;

	private double finalAverageGrade;

	//Each module's is checked whether it is only offered once per year, per term in the University constructor

	//2 Overloaded constructors, 1 being a module before students are enrolled and one afterwards, including student records and a final average grade
	//Constructor of a new module, therefore it will have no students enrolled

	public Module (int year, byte term, ModuleDescriptor module) {
		this.year = year;
		this.term = term;
		this.module = module;
	}

	//Constructor of a module with students enrolled, therefore they have student records and a final average grade
	public Module (int year, byte term, ModuleDescriptor module, StudentRecord[] records, double finalAverageGrade) {
		this.year = year;
		this.term = term;
		this.module = module;
		this.finalAverageGrade = finalAverageGrade;
		this.records = records;
	}

	//methods:
	//method: get the year of a module
	public int getYear () {
		return year;
	}

	//method: get the term of a module
	public byte getTerm () {
		return term;
	}

	//method: get the module descriptor of a module
	public ModuleDescriptor getModuleDescriptor(){
		return module;
	}

	//method: get the final average grade of a module
	public double getFinalAverageGrade(){
		return finalAverageGrade;
	}

	//method: set the final average grade of a module
	public void setFinalAverageGrade(double finalAverageGrade) {
		this.finalAverageGrade = finalAverageGrade;
	}

	//method: get the student records of a module
	public StudentRecord[] getStudentRecords() {
		return records;
	}
	//method: to string method
	public String toString () {
		return "Module [year = " + year + ", term = " + term + ", module descriptor code = " + module.getCode() +  ", final average grade = " + finalAverageGrade +"]";
	}

}
