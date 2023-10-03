import java.util.Arrays;
public class University {

	private ModuleDescriptor[] moduleDescriptors;

	private Student[] students;

	private Module[] modules;

//Constructors for the University class
public University (ModuleDescriptor[] moduleDescriptors, Student[] students, Module[] modules) {
	//Check that each student id is unique
	for (int i = 0; i < students.length; i++){
		//Checks each student id with every student id that comes after it in the array, prevents repetitions
		for (int k = i + 1; k < students.length; k++){
			if (students[i].getId() == students[k].getId()){
				System.err.println("The students " + students[i].getName() + " and " + students[k].getName() + " have the same ID. Each student requires a unique ID.");
				System.exit(1);
			}
		}
	}
	//Check that each module is only offered once per year, per term
	for (int i = 0; i < modules.length; i++){
		//Checks each module with every module that comes after it in the array, prevents repetitions
		for (int k = i + 1; k < modules.length; k++){
			if (modules[i].getYear() == modules[k].getYear() && modules[i].getTerm() == modules[k].getTerm() && modules[i].getModuleDescriptor() == modules[k].getModuleDescriptor()){
				System.err.println("The module " + modules[i].getModuleDescriptor().getCode() + " cannot be offered twice in the same year and term.");
				System.exit(1);
			}
		}
	}
	this.moduleDescriptors = moduleDescriptors;
	this.students = students;
	this.modules = modules;
}

	/**
	 * @return The number of students registered in the system.
	 */
	public int getTotalNumberStudents() {
		//return the length of the array of students, this will be the total number of students in the system
		return students.length;
	}

	/**
	 * @return The student with the highest GPA.
	 */
	public Student getBestStudent() {
		//initializing a best student in order to be able to set the 1st students gpa as the best student
		//initialize a temporary best student without a student record so a student record can be added to it
		Student bestStudenttemp = new Student(0, "bestStudent", 'X');
		//Create a student record including the best student
		StudentRecord bestStudentRecord[] = {new StudentRecord (bestStudenttemp, null, new double[] {0}, 0, false)};
		//Initialize the final best student, containing its student record
		Student bestStudent = new Student(0, "bestStudent", 'X', bestStudentRecord);

		//Iterate through every student in the array students, if a student has a higher gpa than the previous highest gpa then set the respective students gpa as the new highest gpa.
		for (Student i: students) {
			if (i.getGPA() > bestStudent.getGPA()) {
				bestStudent.setGPA(i.getGPA());
			}
		}
		//Iterate through every student in the array of students and if they have the same gpa as the highest return that student as being the best student.
		//If 2 students have the same GPA and the GPA is the highest then the student that comes first in the Array will automatically be set as the highest GPA and be returned as the best student.
		for (Student i: students) {
			if (Math.abs(i.getGPA() - bestStudent.getGPA()) < .00001){
				return i;
			}
		}
		return null;
	}

	/**
	 * @return The module with the highest average score.
	 */
	public Module getBestModule() {
		//Similar to finding the best student this first initializes a module to compare the first module to and then it iterates through the array and if a module has a higher final average grade as the current highest final average grade, the modules higher final average grade is set as the new highest final average grade.
		Module bestModule = new Module(0, (byte) 0, null, null, 0);
		for (Module i: modules) {
			if (i.getFinalAverageGrade() > bestModule.getFinalAverageGrade()) {
				bestModule.setFinalAverageGrade(i.getFinalAverageGrade());
			}
		}
		//Similar to finding the best student iterate through an array of modules and if the module has the same highest final average grade then return that module.
		//If 2 modules have the same final average grade and it is the highest then the module that comes first in the Array will automatically be set as the highest final average grade and be returned as the best module.
		for (Module i: modules) {
			if (Math.abs(i.getFinalAverageGrade() - bestModule.getFinalAverageGrade()) < .00001){
				return i;
			}
		}
		return null;
	}

	public ModuleDescriptor[] getModuleDescriptor() {
		return moduleDescriptors;
	}
	public Student[] getStudents() {
		return students;
	}
	public Module[] getModules() {
		return modules;
	}
	public String toString (){
		return "[" + Arrays.toString(students) + " modules are " + Arrays.toString(modules) + " moduledsecriptors are " + moduleDescriptors + "]";
	}

	public static void main(String[] args) {

		//Create an array of module descriptors
		ModuleDescriptor[] moduleDescriptors = new ModuleDescriptor[6];
		moduleDescriptors[0]  = new ModuleDescriptor("ECM002", "Real World Mathematics", new double[] {0.1,0.3,0.6});
		moduleDescriptors[1]  = new ModuleDescriptor("ECM1400", "Programming", new double[] {0.25,0.25,0.25,0.25});
		moduleDescriptors[2]  = new ModuleDescriptor("ECM1406", "Data Structures", new double[] {0.25,0.25,0.5});
		moduleDescriptors[3]  = new ModuleDescriptor("ECM1410", "Object-Oriented Programming", new double[] {0.2,0.3,0.5});
		moduleDescriptors[4]  = new ModuleDescriptor("BEM2027", "Information Systems", new double[] {0.1,0.3,0.3,0.3});
		moduleDescriptors[5]  = new ModuleDescriptor("PHY2023", "Thermal Physics", new double[] {0.4,0.6});

		//Check that each module descriptor code is unique
		for (int i = 0; i < moduleDescriptors.length; i++){
			//Checks each module descriptor code with every module descriptor code that comes after it in the array, prevents repetitions
			for (int k = i + 1; k < moduleDescriptors.length; k++){
				if (moduleDescriptors[i].getCode().equals(moduleDescriptors[k].getCode())){
					System.err.println("The module descriptors " + moduleDescriptors[i].getName() + " and " + moduleDescriptors[k].getName() + " have the same code, each module descriptor code needs to be unique.");
					System.exit(1);
				}
			}
		}

		Student student1 = new Student(1000, "Ana", 'F');
		Student student2 = new Student (1001, "Oliver", 'M');
		Student student3 = new Student (1002, "Mary", 'F');
		Student student4 = new Student (1003, "John", 'M');
		Student student5 = new Student (1004, "Noah", 'M');
		Student student6 = new Student (1005, "Chico", 'M');
		Student student7 = new Student (1006, "Maria", 'F');
		Student student8 = new Student (1007, "Mark", 'X');
		Student student9 = new Student (1008, "Lia", 'F');
		Student student10 = new Student (1009, "Rachel", 'F');

		//Create modules that students then enrol onto
		Module module1 = new Module (2019, (byte) 1, moduleDescriptors[1]); //ECM1400
		Module module2 = new Module (2019, (byte) 1, moduleDescriptors[5]); //PHY2023
		Module module3 = new Module (2019, (byte) 2, moduleDescriptors[4]); //BEM2027
		Module module4 = new Module (2019, (byte) 2, moduleDescriptors[1]); //ECM1400
		Module module5 = new Module (2020, (byte) 1, moduleDescriptors[2]); //ECM1406
		Module module6 = new Module (2020, (byte) 1, moduleDescriptors[3]); //ECM1410
		Module module7 = new Module (2020, (byte) 2, moduleDescriptors[0]); //ECM002

		//Create student records and grades for modules that students enrolled in
		//each student took 2 modules, 2 in 2019 and 2 in 2020
		//all students took module 5

		//student 1
		StudentRecord student1Records1= new StudentRecord (student1, module1, new double[] {9,10,10,10}, 97.5, true);
		StudentRecord student1Records2 = new StudentRecord (student1, module3, new double[] {10,10,9.5,10}, 98.5, true);
		StudentRecord student1Records3= new StudentRecord (student1, module5, new double[] {10,10,10}, 100, true);
		StudentRecord student1Records4= new StudentRecord (student1, module6, new double[] {10,9,10}, 97, true);

		//student 2
		StudentRecord student2Records1= new StudentRecord (student2, module1, new double[] {8, 8, 8, 9}, 82.5, true);
		StudentRecord student2Records2 = new StudentRecord (student2, module3, new double[] {7, 8.5, 8.2, 8}, 81.1, true);
		StudentRecord student2Records3= new StudentRecord (student2, module5, new double[] {8, 7.5, 7.5}, 76.25, false);
		StudentRecord student2Records4= new StudentRecord (student2, module6, new double[] {8.5,9,7.5}, 81.5, true);

		//student 3
		StudentRecord student3Records1= new StudentRecord (student3, module1, new double[] {5,5,6,5}, 52.5, false);
		StudentRecord student3Records2 = new StudentRecord (student3, module3, new double[] {6.5,7.0,5.5,8.5}, 69.5, false);
		StudentRecord student3Records3= new StudentRecord (student3, module5, new double[] {9,7,7}, 75, false);
		StudentRecord student3Records4= new StudentRecord (student3, module6, new double[] {10,10,5.5}, 77.5, false);

		//student 4
		StudentRecord student4Records1= new StudentRecord (student4, module1, new double[] {6,4,7,9}, 65, false);
		StudentRecord student4Records2 = new StudentRecord (student4, module3, new double[] {5.5,5,6.5,7}, 61, false);
		StudentRecord student4Records3= new StudentRecord (student4, module5, new double[] {9,8,7}, 77.5, false);
		StudentRecord student4Records4= new StudentRecord (student4, module6, new double[] {7,7,7}, 70, false);

		//student 5
		StudentRecord student5Records1= new StudentRecord (student5, module1, new double[] {10,9,10,9}, 95, true);
		StudentRecord student5Records2 = new StudentRecord (student5, module3, new double[] {7,5,8,6}, 64, false);
		StudentRecord student5Records3= new StudentRecord (student5, module5, new double[] {2,7,7}, 57.5, false);
		StudentRecord student5Records4= new StudentRecord (student5, module6, new double[] {5,6,10}, 78, false);

		//student 6
		StudentRecord student6Records1= new StudentRecord (student6, module2, new double[] {9, 9}, 90, true);
		StudentRecord student6Records2= new StudentRecord (student6, module4, new double[] {9 ,10, 10, 10}, 97.5, true);
		StudentRecord student6Records3= new StudentRecord (student6, module5, new double[] {10 ,10, 10}, 100, true);
		StudentRecord student6Records4= new StudentRecord (student6, module7, new double[] {8 ,9, 8}, 83, false);

		//student 7
		StudentRecord student7Records1= new StudentRecord (student7, module2, new double[] {6, 9}, 78, true);
		StudentRecord student7Records2= new StudentRecord (student7, module4, new double[] {8, 8, 8, 9}, 82.5, true);
		StudentRecord student7Records3= new StudentRecord (student7, module5, new double[] {8, 7.5, 7.5}, 76.25, false);
		StudentRecord student7Records4= new StudentRecord (student7, module7, new double[] {6.5,9,9.5}, 90.5, true);

		//student 8
		StudentRecord student8Records1= new StudentRecord (student8, module2, new double[] {5, 6}, 56, false);
		StudentRecord student8Records2= new StudentRecord (student8, module4, new double[] {5,5,6,5}, 52.5, false);
		StudentRecord student8Records3= new StudentRecord (student8, module5, new double[] {10,10,10}, 100, true);
		StudentRecord student8Records4= new StudentRecord (student8, module7, new double[] {8.5,10,8.5}, 89.5, true);

		//student 9
		StudentRecord student9Records1= new StudentRecord (student9, module2, new double[] {9, 7}, 78, true);
		StudentRecord student9Records2= new StudentRecord (student9, module4, new double[] {6,4,7,9}, 65, false);
		StudentRecord student9Records3= new StudentRecord (student9, module5, new double[] {9,8,7}, 77.5, false);
		StudentRecord student9Records4= new StudentRecord (student9, module7, new double[] {7.5,8,10}, 91.5, true);

		//student 10
		StudentRecord student10Records1= new StudentRecord (student10, module2, new double[] {8, 5}, 62, false);
		StudentRecord student10Records2= new StudentRecord (student10, module4, new double[] {10,9,8,9}, 90, true);
		StudentRecord student10Records3= new StudentRecord (student10, module5, new double[] {8,9,10}, 92.5, true);
		StudentRecord student10Records4= new StudentRecord (student10, module7, new double[] {10,6,10}, 88, false);

		//Create arrays of student records for each student
		StudentRecord[] studentRecordsstudent1 = {student1Records1, student1Records2, student1Records3, student1Records4};
		StudentRecord[] studentRecordsstudent2 = {student2Records1, student2Records2, student2Records3, student2Records4};
		StudentRecord[] studentRecordsstudent3 = {student3Records1, student3Records2, student3Records3, student3Records4};
		StudentRecord[] studentRecordsstudent4 = {student4Records1, student4Records2, student4Records3, student4Records4};
		StudentRecord[] studentRecordsstudent5 = {student5Records1, student5Records2, student5Records3, student5Records4};
		StudentRecord[] studentRecordsstudent6 = {student6Records1, student6Records2, student6Records3, student6Records4};
		StudentRecord[] studentRecordsstudent7 = {student7Records1, student7Records2, student7Records3, student7Records4};
		StudentRecord[] studentRecordsstudent8 = {student8Records1, student8Records2, student8Records3, student8Records4};
		StudentRecord[] studentRecordsstudent9 = {student9Records1, student9Records2, student9Records3, student9Records4};
		StudentRecord[] studentRecordsstudent10 = {student10Records1, student10Records2, student10Records3, student10Records4};

		//Create arrays of student records for each module
		//students 1-5 took the same modules
		//students 6-10 took the same modules
		//all students took module 5
		StudentRecord[] studentRecordsmodule1 = {student1Records1, student2Records1, student3Records1, student4Records1, student5Records1};
		StudentRecord[] studentRecordsmodule2 = {student6Records1, student7Records1, student8Records1, student9Records1, student10Records1};
		StudentRecord[] studentRecordsmodule3 = {student1Records2, student2Records2, student3Records2, student4Records2, student5Records2};
		StudentRecord[] studentRecordsmodule4 = {student6Records2, student7Records2, student8Records2, student9Records2, student10Records2};
		StudentRecord[] studentRecordsmodule5 = {student1Records3, student2Records3, student3Records3, student4Records3, student5Records3, student6Records3, student7Records3, student8Records3, student9Records3, student10Records3};
		StudentRecord[] studentRecordsmodule6 = {student1Records4, student2Records4, student3Records4, student4Records4, student5Records4};
		StudentRecord[] studentRecordsmodule7 = {student6Records4, student7Records4, student8Records4, student9Records4, student10Records4};

		//Initialize students complete with an array of student records, each refering to a module taken
		//GPA is calculated in the constructor, can be checked with getGPA
		Student student1Final = new Student(student1.getId(), student1.getName(), student1.getGender(), studentRecordsstudent1);
		Student student2Final = new Student (student2.getId(), student2.getName(), student2.getGender(), studentRecordsstudent2);
		Student student3Final = new Student (student3.getId(), student3.getName(), student3.getGender(), studentRecordsstudent3);
		Student student4Final = new Student (student4.getId(), student4.getName(), student4.getGender(), studentRecordsstudent4);
		Student student5Final = new Student (student5.getId(), student5.getName(), student5.getGender(), studentRecordsstudent5);
		Student student6Final = new Student (student6.getId(), student6.getName(), student6.getGender(), studentRecordsstudent6);
		Student student7Final = new Student (student7.getId(), student7.getName(), student7.getGender(), studentRecordsstudent7);
		Student student8Final = new Student (student8.getId(), student8.getName(), student8.getGender(), studentRecordsstudent8);
		Student student9Final = new Student (student9.getId(), student9.getName(), student9.getGender(), studentRecordsstudent9);
		Student student10Final = new Student (student10.getId(), student10.getName(), student10.getGender(), studentRecordsstudent10);

		//Initialize modules with an array of student records, each referring to a different student
		Module module1Final = new Module (module1.getYear(), module1.getTerm(), module1.getModuleDescriptor(), studentRecordsmodule1, 78.5 );
		Module module2Final = new Module (module2.getYear(), module2.getTerm(), module2.getModuleDescriptor(), studentRecordsmodule2, 72.8 );
		Module module3Final = new Module (module3.getYear(), module3.getTerm(), module3.getModuleDescriptor(), studentRecordsmodule3, 74.82 );
		Module module4Final = new Module (module4.getYear(), module4.getTerm(), module4.getModuleDescriptor(), studentRecordsmodule4, 77.5 );
		Module module5Final = new Module (module5.getYear(), module5.getTerm(), module5.getModuleDescriptor(), studentRecordsmodule5, 83.25 );
		Module module6Final = new Module (module6.getYear(), module6.getTerm(), module6.getModuleDescriptor(), studentRecordsmodule6, 80.8 );
		Module module7Final = new Module (module7.getYear(), module7.getTerm(), module7.getModuleDescriptor(), studentRecordsmodule7, 88.5 );

		//create final array of students including student records
		Student[] students = {student1Final, student2Final, student3Final, student4Final, student5Final, student6Final, student7Final, student8Final, student9Final, student10Final};



		//Create final modules with an array of student records and final grades
		Module[] modules = {module1Final, module2Final, module3Final, module4Final, module5Final, module6Final, module7Final};

		//Create university object containing an array of module descriptors, students and modules
		University university = new University(moduleDescriptors, students, modules);

		// Print Reports
		System.out.println("The UoK has " + university.getTotalNumberStudents() + " students.");

		// best module
		System.out.println("The best module is:");
		System.out.println(university.getBestModule());

		// best student
		System.out.println("The best student is:");
		System.out.println(university.getBestStudent().printTranscript());
	}
}
