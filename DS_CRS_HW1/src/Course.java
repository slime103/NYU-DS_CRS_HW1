import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Course implements Comparable<Course>, Serializable{

	private static final long serialVersionUID = 1L;
	
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
	
	public void setName(String name) {
		courseName = name;
	}
	
	public String getID() {
		return courseId;
	}
	
	public void setID(String id) {
		courseId = id;
	}
	
	public void setMaxStudents(int n) {
		maxStudents = n;
	}
	
	public void setInstructor(String prof) {
		instructor = prof;
	}
	
	public void setSection(int n) {
		sectionNum = n;
	}
	
	public void setLocation(String placeName) {
		courseLocation = placeName;
	}
	
	//Adds a student to the course and increments the number of enrolled students
	public void addStudent(Student s)  {
		students.add(s);
		currentNumOfStudents++;
	}
	
	//finds a student in this course
	public Student findStudent(String name) {
		if (!students.isEmpty()) {
			for (Student s : students) {
				if (s.getName().equalsIgnoreCase(name)) {
					return s;
				}
			}
			System.out.println("Student not found.\n");
			return null;
		}
		System.out.println("There are no students in the course selected.\n");
		return null;
	}
	
	//checks if a student exists in the course given their name
	public boolean hasStudent(String studentName) {
		if (!students.isEmpty()) {
			for (Student s : students) {
				if (s.getName().equalsIgnoreCase(studentName)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}
	
	//Iterate through all students, if the name matches, remove it
	public void removeStudent(Student s) {
		for (Student name : students) {
			if(s.getName().contentEquals(name.getName())) {
				students.remove(s);
				currentNumOfStudents--;
				return;
			}
		}
	}

	@Override
	//Compares Based on Number of Students
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
	
	//returns a list of all the students enrolled in this course
	public String getStudents() {
		if (!students.isEmpty()) {
			String studentList = "Enrolled Students: \n";
			Collections.sort(students);
			for (Student s : students) {
				studentList += s.getName() + "\n";
			}
			return studentList;
		}
			return "There are no students enrolled in this course.\n";
	}
	
	//Checks to see if this course is full
	public boolean isCourseFull() {
		if (currentNumOfStudents < maxStudents) {
			return false;
		}
		else {
			return true;
		}
	}
	
	//un-enrolls every student in this course, useful during deletion
	public void unenrollAll() {
		for (Student s : students) {
			s.withdraw(this);
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
