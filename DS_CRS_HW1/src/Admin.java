import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Admin extends User implements CourseManager {
	
	public Admin() {
		firstName = "ALANKIRTH";
		lastName = "KRISHNAN";
		username = "Admin";
		password = "Admin001";
	}
	
	public void addCourse(String courseName, String id, int max, String prof, int section, String location) {
		Course c = new Course(courseName, id, max, prof, section, location);
		CourseData.courses.add(c);
	}
	
	public void deleteCourse(String name) {
		for (Course c : CourseData.courses) {
			if (name.equalsIgnoreCase(c.getName())) {
				CourseData.courses.remove(c);
				System.out.println(name + " has been successfully removed.\n");
				return;
			}
		}
		System.out.println("Course NOT found.");
	}
	
	public void editCourse(String name) {
		int n = 0;
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number corresponding to the field you would"
				+ "like to edit.");
		
		while (scan.hasNext()) {
			if (scan.hasNextInt()) {
				n = scan.hashCode();
			}
			else {
				System.out.println("Please enter a corresponding Integer value.");
			}
		}
		
		switch (n) {
			case 1: 
				
			case 2:
				
			case 3:
				
			case 4:
		}
	}
	
	public String courseInfo(String id) {
		for (Course c : CourseData.courses) {
			if (id.equalsIgnoreCase(c.getID())) {
				return c.toString();
			}
		}
		return "Course NOT found.";
	}
	
	public void registerNewStudent(String first, String last) {
		Student s = new Student(first, last);
		CourseData.students.add(s);
		System.out.println(s.getName() + " has been successfully registered.\n");
	}
	
	// Tested
	public String viewFullCourses() {
		String fullCourses = "";
		for (Course c : CourseData.courses) {
			if (c.isCourseFull())
				fullCourses += c.getName() + "\n";
		}
		return fullCourses;
	}
	
	// Tested
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
			
		} catch (IOException e) {
			e.getStackTrace();
		}
		
	}
	
	// Tested
	public String viewCourses() {
		String courseList = "";
		for (Course c : CourseData.courses) {
			courseList += c + "\n" + "Enrolled Students: " + c.getStudents() + "\n\n\n";
		}
		return courseList;
	}
	
	public String studentEnrolledCourses(String name) {
		for (Student s : CourseData.students) {
			if (name.equalsIgnoreCase(s.getName())) {
				System.out.println(s.getName() + " is currently enrolled in: ");
				return s.viewEnrolledCourses();
			}
		}
		return "Student NOT found.";
	}

}
