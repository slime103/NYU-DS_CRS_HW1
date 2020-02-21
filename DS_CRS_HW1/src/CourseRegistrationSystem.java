
public class CourseRegistrationSystem {
	
	public static void main(String[] args) {
		
		//Create and Populate Data
		CourseData data = new CourseData();
		CourseData.deserialize(data);
		
		User user = new User();
		System.out.println(user.viewCourses());
		
		//User Log-in
		
	}
}
