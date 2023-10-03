public class Student {

	private int id;

	private String name;

	private char gender;

	private double gpa;

	private StudentRecord[] records;

	//Each students id is checked if it is unique in the university constructor

	//2 Overloaded constructors, 1 being the student before being enrolled to modules, 1 afterwards
	//Constructor of a new student, therefore they have no GPA or StudentRecord
	public Student (int id, String name, char gender){
		//if id is null it throws an error in compilation so no check is necessary
		this.id = id;

		//check if the name is null
		if (name == null) {
			System.err.println("Name input cannot be null ");
			System.exit(1);
		}
		// no else necessary since program will stop if an error occurs, if not then it will continue as normal
		this.name = name;

		//Check if the gender is either 'M', 'F' or X
		//If CompareM == 0 then the two values that were compared are equal
		int compareM = Character.compare('M', gender);
		int compareF = Character.compare('F', gender);
		int compareX = Character.compare('X', gender);
		//check if gender is one of these types
		if (compareM == 0 || compareF == 0 || compareX == 0) {
			this.gender = gender;
		} else {

			// Print a msg describing the problem.
			System.err.println("You input gender " + gender + " but the gender of a student must be F, M or X.");
			// This command will stop the execution of the program.
			System.exit(1);
		}
		}


	//constructor of a student with an array of student records, gpa calculated using student records
	public Student (int id, String name, char gender, StudentRecord[] records){
		//if id is null it throws an error in compilation so no check is necessary
		this.id = id;

		//check if the name is null
		if (name == null) {
			System.err.println("Name input cannot be null ");
			System.exit(1);
		}
		// no else necessary since program will stop if an error occurs, if not then it will continue as normal
		this.name = name;

		//Check if the gender is either 'M', 'F' or X
		//If CompareM == 0 then the two values that were compared are equal
		int compareM = Character.compare('M', gender);
		int compareF = Character.compare('F', gender);
		int compareX = Character.compare('X', gender);
		//check if gender is one of these types
		if (compareM == 0 || compareF == 0 || compareX == 0) {
			this.gender = gender;
		} else {
			System.err.println("You input gender " + gender + " but the gender of a student must be F, M or X.");
			System.exit(1);
		}

		//calculate the gpa of a student
		double gpa = 0;
		double counter = 0;
		//Sum up the values of all the final scores in a student record
		for (StudentRecord i: records) {
			//Check if the final score of a student in each student record is between 0 and 100
			if (0 > i.getFinalScore() || i.getFinalScore() > 100){
				System.err.println("You input final module score " + i.getFinalScore() + " but the finalscore of a student in a module must be between 0 and 100.");
				System.exit(1);
			}
			gpa += i.getFinalScore();
			counter++;
		}
		//Divide the sum of the final scores by the number of student records to give the gpa
		gpa = gpa/counter;
		this.gpa = gpa;

		//Check if any student has 2 records for a single module
		int temp1 = 0;//used as a counter
		for (StudentRecord a: records) {
			if (temp1 == 0) {//stores the 1st student record in the student record array as a
				for (StudentRecord b: records) {
					temp1 ++;
					if (temp1 == 2) {//stores the 2nd student record in the student record array as b
						for (StudentRecord c: records) {
							temp1 ++;
							if (temp1 == 5){//stores the 3rd student record in the student record array as c
								for (StudentRecord d: records) {
									temp1 ++;
									if (temp1 == 9) {//stores the 4th student record in the student record array as d
										//Compare the memory referenece of each module to each other module
										if (a.getModule() == b.getModule() || a.getModule() == c.getModule() || a.getModule() == d.getModule() || b.getModule() == c.getModule() || b.getModule() == d.getModule() || c.getModule() == d.getModule()) {
											System.err.println("The student with the ID " + id + " has 2 records for a  single module.");
											System.exit(1);
										}
									}
								}
							}
						}
					}
				}
			}
		}
		this.records = records;
}
	//methods:
	//method: get the name of a student
	public String getName (){
		return name;
	}
	//method: get the id of a student
	public int getId (){
		return id;
	}
	//method: get the gender of a student
	public char getGender(){
		return gender;
	}

	//method: get the GPA of a student
	public double getGPA() {
		return gpa;
	}

	//method: set the GPA of a student
	public void setGPA(double gpa) {
		this.gpa = gpa;
	}

	//method: set the GPA of a student
	public StudentRecord[] getRecords(){
		return records;
	}
	//method: to string method
	public String toString(){
		return "Student[id = " + id + ", name = " + name + ", gender = " + gender + ", gpa = " + gpa + ", student records = " + records + "]";
	}

	//method: Print the transcript of a student
	public String printTranscript() {
		return "University of Knowledge - Official Transcript\n\n" + "ID: " + id +"\nName: " + name + "\nGPA: " + gpa + "\n\n" + recordsToString(records);
	}

	//method: transform the student record array in a student into a string
	public String recordsToString (StudentRecord[] records) {
		StringBuilder sb = new StringBuilder("");
		int termTemp = 0;
		for (StudentRecord i: records) {
			//setting the string arguments equal to the required information
			String str1 = "| " + i.getModuleYear() + " | ";
			String str2 = i.getModuleTerm() + " | ";
			String str3 = i.getModule().getModuleDescriptor().getCode() + " | ";
			String str4 = i.getFinalScore() + " |";
			//check if the term of the current and previous modules are the same, if they are not add a new line between them
			if (Math.abs(i.getModuleTerm() - termTemp) > .1) {
				sb.append("\n");
			}
			//append the string arguments
			sb.append(str1);//module year
			sb.append(str2);//module term
			sb.append(str3);//module descriptor code
			sb.append(str4);//module final score
			sb.append("\n");
			//Make the tempTerm variable equal to the current term
			termTemp = i.getModuleTerm();
		}
		//delete the last new line character
		sb.deleteCharAt(sb.length()-1);
		return "" + sb;
	}
}
