
public interface CourseManager {
	
	public void addCourse(String courseName, String id, int max, String prof, int section, String location);
	
	public void deleteCourse(String name);
	
	public void editCourse(String nameOfCourse, int n, String field);
	
	public String courseInfo(String id);
}
