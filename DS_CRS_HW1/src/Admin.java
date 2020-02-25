import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class Admin extends User implements CourseManager {
	
	private static final long serialVersionUID = 1L;

	public Admin() {
		firstName = "ALANKIRTH";
		lastName = "KRISHNAN";
		username = "Admin";
		password = "Admin001";
	}
	
	//creates a course and adds it to the master array
	public void addCourse(String courseName, String id, int max, String prof, int section, String location) {
		Course c = new Course(courseName, id, max, prof, section, location);
		CourseData.courses.add(c);
		System.out.println(c.getName() + " has been created successfully.\n" + c);
	}
	
	//removes course from the master array and removes all enrolled students
	public void deleteCourse(String name) {
		for (Course c : CourseData.courses) {
			if (name.equalsIgnoreCase(c.getName())) {
				c.unenrollAll();
				CourseData.courses.remove(c);
				System.out.println(c.getName() + " has been successfully deleted.\n");
				return;
			}
		}
		System.out.println("Course NOT found.");
	}
	
	//Allows you to edit a course based on the specified field given
	public void editCourse(String nameOfCourse, int n, String field) {
		try {
			Course c = findCourse(nameOfCourse);
			
			switch (n) {
				case 1: {
					c.setName(field);
					break;
				}
				case 2:{
					c.setID(field);
					break;
				}
				case 3: {
					try {
						int a = Integer.parseInt(field);
						if (a > 0) {
							c.setMaxStudents(a);
							System.out.println("Max Students set to " + a + "\n");
						}
						else {
							System.out.println("Max Students must be greater than zero.\n");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid integer for Max Students.\n");
					}
					break;
				}				
				case 4: {
					c.setInstructor(field);
				}
				case 5: {
					try {
						int a = Integer.parseInt(field);
						c.setSection(a);
					} catch (NumberFormatException e) {
						System.out.println("Invalid integer for Section Number.\n");
					}
					break;
				}
				case 6: {
					c.setLocation(field);
					break;
				}
			}
		} catch (NullPointerException e) {
			//Course DNE printed
		}
	}
	
	//Prints the courses toString given ID
	public String courseInfo(String id) {
		for (Course c : CourseData.courses) {
			if (id.equalsIgnoreCase(c.getID())) {
				return c.toString();
			}
		}
		return "Course NOT found.";
	}
	
	//Creates a new student and adds it to the master array
	public void registerNewStudent(String first, String last) {
		Student s = new Student(first, last);
		CourseData.students.add(s);
		System.out.println(s.getName() + " has been successfully registered.\n");
	}
	
	// Tested
	// Displays all courses that are full
	public String viewFullCourses() {
		String fullCourses = "";
		for (Course c : CourseData.courses) {
			if (c.isCourseFull())
				fullCourses += c.getName() + "\n";
		}
		if (fullCourses.isEmpty()) {
			return "There are no Full Courses.\n";
		}
		else {
			return fullCourses;
		}
	}
	
	// Tested
	// Writes all full courses to file
	public void writeFullCoursesToFile() {
		
		int n = 0;
		String fileName = "ListOfFullCourses";
		File report = new File(fileName + ".txt");
		while (report.exists()) {
			report = new File(fileName + "(" + (n++) + ").txt");
		}
		
		try {
			FileWriter writer = new FileWriter(report);
			String output = this.viewFullCourses();
			for (int i = 0; i < output.length();i++) {
				writer.write(output.charAt(i));
			}
			writer.close();
			
			System.out.println("File Successfully Written.\n");
			
		} catch (IOException e) {
			e.getStackTrace();
		}
		
	}
	
	//sorts the courses based on the number of students
	public void sortCourses() {
		Collections.sort(CourseData.courses);
		System.out.println("Courses sorted by number of Students.\n");
	}
	
	// Tested
	// shows all courses by their toString and the students registered in each course
	public String viewCourses() {
		String courseList = "";
		for (Course c : CourseData.courses) {
			courseList += c + "\n" + "Enrolled Students: " + c.getStudents() + "\n\n\n";
		}
		return courseList;
	}
	
	// returns the list of students in the master array
	public String viewStudents() {
		String studentList = "\nStudents: \n";
		for (Student s : CourseData.students) {
			studentList += s.getName() + "\n";
		}
		return studentList;
	}
	
	// returns a list of the students enrolled courses
	public String studentEnrolledCourses(String nameOfStudent) {
		try {
			Student s = findStudent(nameOfStudent);
			return s.viewEnrolledCourses();
		} catch (NullPointerException e) {
			return "";
		}
	}
	
	// returns all students in a given course
	public String studentsInCourse(String nameOfCourse) {
		try {
			Course c = findCourse(nameOfCourse);
			return c.getStudents();
		} catch (NullPointerException e) {
			//Course DNE printed
			return "";
		}
	}
	
	//finds a student in the master array given its name
	public Student findStudent(String name) {
		if (!CourseData.students.isEmpty()) {
			for (Student s : CourseData.students) {
				if (s.getName().equalsIgnoreCase(name)) {
					return s;
				}
			}
			System.out.println("Student not found.\n");
			return null;
		}
		System.out.println("There are no students registered.\n");
		return null;
	}

}
