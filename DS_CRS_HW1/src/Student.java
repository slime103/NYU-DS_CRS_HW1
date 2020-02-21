import java.util.ArrayList;

public class Student extends User implements StudentView, Comparable<Student>{
	
	ArrayList<Course> enrolledCourses = new ArrayList<Course>();
	
	public Student(String first, String last) {
		firstName = first;
		lastName = last;
		fullName = first + " " + last;
		username = first + "." + last;
		password = "$" + last + "$";
	}
	
	public String viewOpenCourses() {
		String open = "";
		for (Course c : CourseData.courses) {
			if (!c.isCourseFull()) {
				open += c.getName() + "\n";
			}
		}
		return open;
	}
	
	public void registerInCourse(Course c) {
		if (!c.isCourseFull()) {
			c.addStudent(this);
		}
		else {
			System.out.println("Sorry, the course you've selected is FULL.");
		}
	}
	
	public void withdraw(Course c) {
		c.removeStudent(this);
		System.out.println(fullName + " has been successfully removed from " + c.getName());
	}
	
	public String viewEnrolledCourses() {
		String enrolled = "";
		for (Course c : enrolledCourses) {
			enrolled += c.getName() + "\n";
		}
		return enrolled;
	}

	@Override
	public int compareTo(Student o) {
		if (lastName.compareToIgnoreCase(o.lastName) == 0) {
			return firstName.compareToIgnoreCase(o.firstName);
		}
		return lastName.compareToIgnoreCase(o.lastName);
	}

}
