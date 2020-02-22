import java.util.Scanner;

public class CourseRegistrationSystem {
	
	public static void main(String[] args) {
		
		
		/*Testing Checklist
		 * 
		 * Importation Working
		 * Serialization Working
		 * 
		 */
		
		//IMPORT DATA
		CourseData.deserialize();
		Scanner scan = new Scanner(System.in);
		
		//User user = new User();
		//System.out.println(user.viewCourses());
		
		//Admin admin = new Admin();
		//System.out.println("\n\n\n" + admin.viewCourses());
		//admin.writeFullCoursesToFile();
		
		//PROGRAM START
		System.out.println("Hello! Welcome to Albert 1993.\n"
				+ "Please enter the integer corresponding to your correct login credientials:\n");
		
		boolean loggedIn = false;
		//LOGIN
		while (!loggedIn) {
			System.out.println("1. Student\n2. Admin\n");
			
			int n = 0;
			
			while(n != 1 && n != 2) {
				while (!scan.hasNextInt()) {
					scan.next();
				}	
				n = scan.nextInt();
			}
			
			System.out.println("Please enter your username: ");
			if (n == 1) {
				System.out.println("(Hint: username is firstname.lastname\n"
						+ "password is $lastname$\n");
			}
			
			String username = scan.next();
			
			System.out.println("Please enter your password: ");
			
			String password = scan.next();
			
			if (n == 1) {
				Student student = new Student("tempVal","tempVal");
				try {
					student = student.login(username, password);
					System.out.println("Succesfully logged in as " + student.getName());
					loggedIn = true;
				} catch (NullPointerException e) {
					System.out.println("Invalid user credentials or Student does not exist.\n");
				}
			}
			else {
				if (username.contentEquals("Admin") && password.contentEquals("Admin01")) {
					Admin admin = new Admin();
					System.out.println("Succesfully logged in as Admin.\n");
					loggedIn = true;
				}
				else {
					System.out.println("Invalid user credentials.\n");
				}
			}
		}
		
		//Display STUDENT commands
		
		//Display ADMIN commands
		
	}
}
