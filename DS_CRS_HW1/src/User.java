import java.io.Serializable;

public abstract class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
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
	//returns a list of all courses by name
	public String viewCourses() {
		String courseList = "\nCourse List: \n";
		for (Course c : CourseData.courses) {
			courseList += c.getName() + "\n";	
		}
		return courseList;
	}
	
	//serializes Course Data and quits the program
	public void quit() {
		CourseData.serialize();
		System.exit(0);
	}
	
	//finds a course given its name
	public Course findCourse(String nameOfCourse) {
		for (Course c : CourseData.courses) {
			if (c.getName().equalsIgnoreCase(nameOfCourse)) {
				return c;
			}
		}
		System.out.println("Course does not exist.\n");
		return null;
	}
	
}
