
public class Admin extends User implements CourseManager {
	
	public Admin() {
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
				return;
			}
		}
		System.out.println("Course NOT found.");
	}
	
	public void editCourse(String name) {
		//use switch
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
	}
	
	public String viewFullCourses() {
		String fullCourses = "";
		for (Course c : CourseData.courses) {
			fullCourses += c.getName() + "\n";
		}
		return fullCourses;
	}
	
	public void writeFullCoursesToFile() {
		
	}
	
	public String viewCourses() {
		String courseList = "";
		for (Course c : CourseData.courses) {
			courseList += c + "\n" + "Students: " + c.getStudents() + "\n\n\n";
		}
		return courseList;
	}
	
	public String studentEnrolledCourses(String name) {
		for (Student s : CourseData.students) {
			if (name.equalsIgnoreCase(s.getName())) {
				return s.viewEnrolledCourses();
			}
		}
		return "Student NOT found.";
	}

}
