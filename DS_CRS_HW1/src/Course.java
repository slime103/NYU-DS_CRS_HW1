import java.util.ArrayList;
import java.util.Collections;

public class Course implements Comparable<Course>{

	private String courseName;
	private String courseId;
	private int maxStudents;
	private int currentNumOfStudents;
	private ArrayList<Student> students = new ArrayList<Student>();
	private String instructor;
	private int sectionNum;
	private String courseLocation;
	
	
	public Course(String name, String id, int max, String prof, int section, String location) {
		courseName = name;
		courseId = id;
		maxStudents = max;
		currentNumOfStudents = 0;
		instructor = prof;
		sectionNum = section;
		courseLocation = location;
	}
	
	public String getName() {
		return courseName;
	}
	
	public String getID() {
		return courseId;
	}
	
	public void addStudent(Student s)  {
		students.add(s);
	}
	
	//Iterate through all students, if the matches, remove it
	public void removeStudent(Student s) {
		for (Student name : students) {
			if(s.getName().equalsIgnoreCase(name.getName()))
				students.remove(s);
		}
	}

	@Override
	public int compareTo(Course o) {
		System.out.println();
		if (currentNumOfStudents < o.currentNumOfStudents) {
			return -1;
		}
		else if (currentNumOfStudents == o.currentNumOfStudents) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	public String getStudents() {
		String studentList = "";
		Collections.sort(students);
		for (Student s : students) {
			studentList += s.getName() + "\n";
		}
		return studentList;
	}
	
	public boolean isCourseFull() {
		if (currentNumOfStudents == maxStudents) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public String toString() {
		return "Course Name: " + courseName + "\n"
				+ "Course ID: " + courseId + "\n"
				+ "Max Capacity: " + maxStudents + "\n"
				+ "Number of Currently Enrolled Students: " + currentNumOfStudents + "\n"
				+ "Instructor: " + instructor + "\n"
				+ "Section Number: " + sectionNum + "\n"
				+ "Location: " + courseLocation + "\n";
	}

}
