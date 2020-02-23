
public class User {
	
	protected String firstName;
	protected String lastName;
	protected String fullName;
	protected String username;
	protected String password;
	
	public User() {
	}
	
	public String getName() {
		return fullName;
	}
	
	public void setName(String name) {
		fullName = name;
		String[] names = name.split(" ");
		firstName = names[0];
		lastName = names[1];
	}
	
	//Tested
	public String viewCourses() {
		String courseList = "";
		for (Course c : CourseData.courses) {
			courseList += c.getName() + "\n";	
		}
		return courseList;
	}
	
	public void exit() {
		CourseData.serialize();
		System.exit(0);
	}
	
	public Course findCourse(String nameOfCourse) {
		for (Course c : CourseData.courses) {
			if (c.getName().equalsIgnoreCase(nameOfCourse)) {
				return c;
			}
		}
		System.out.println("Course does not exist.");
		return null;
	}
	
}
