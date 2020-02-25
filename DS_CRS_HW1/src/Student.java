import java.io.Serializable;
import java.util.ArrayList;

public class Student extends User implements StudentView, Comparable<Student>, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	ArrayList<Course> enrolledCourses = new ArrayList<Course>();
	
	public Student(String first, String last) {
		firstName = first;
		lastName = last;
		fullName = first + " " + last;
		username = first + "." + last;
		password = "$" + last + "$";
	}
	
	//Tested
	//Matches login info to existing student with matching name
	public Student login(String username, String password) {
		for (Student s : CourseData.students) {
			if (username.contentEquals(s.username) && (password.contentEquals(s.password))) {
				return s;
			}
		}
		//System.out.println("returned null");
		return null;
	}
	
	//shows all courses that are not full
	public String viewOpenCourses() {
		String open = "";
		for (Course c : CourseData.courses) {
			if (!c.isCourseFull()) {
				open += c.getName() + "\n";
			}
		}
		return open;
	}
	
	//Tested
	//Adds the student to a specified course if it is open
	public void registerInCourse(String nameOfCourse) {
			try {
				Course c = findCourse(nameOfCourse);
				if (c.getName().equalsIgnoreCase(nameOfCourse)) {
					if (!c.isCourseFull()) {
						c.addStudent(this);
						enrolledCourses.add(c);
						System.out.println(this.getName() + " has been succesfully registered in " + c.getName()+ "\n");
					}
					else {
						System.out.println("Sorry, the course you've selected is FULL.\n");
					}
				}
			} catch (NullPointerException e){
				//Course does not exist has been printed
			}
	}
	
	//Tested
	//Removes the student from a course if they are enrolled in such course
	public void withdraw(String courseName) {
		try {
			Course c = findCourse(courseName);
			if (c.hasStudent(this.getName())) {
				c.removeStudent(this);
				enrolledCourses.remove(c);
				System.out.println(fullName + " has been successfully removed from " + c.getName() + "\n");
			}
			else {
				System.out.println("Student or Course not found.\n");
			} 
		} catch (NullPointerException e){
			//Course does not exist has been printed
		}
	}
	
	//Removes the course from student enrolled list
	public void withdraw(Course c) {
		enrolledCourses.remove(c);
	}
	
	//Displays all courses if a student is enrolled in any courses
	public String viewEnrolledCourses() {
		if (!enrolledCourses.isEmpty()) {
			String enrolled = "";
			for (Course c : enrolledCourses) {
				enrolled += c.getName() + "\n";
			}
			return enrolled;
		}
		else {
			return "The student is not enrolled in any courses.\n";
		}
	}

	//compares alphabetically based on name
	@Override
	public int compareTo(Student o) {
		if (lastName.compareToIgnoreCase(o.lastName) == 0) {
			return firstName.compareToIgnoreCase(o.firstName);
		}
		return lastName.compareToIgnoreCase(o.lastName);
	}

}
