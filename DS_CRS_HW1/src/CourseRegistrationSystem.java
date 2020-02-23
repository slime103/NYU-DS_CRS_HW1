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
		
		Student student = new Student("tempVal","tempVal");
		Admin admin = new Admin();
		
		boolean loggedIn = false;
		boolean studentUser = false;
		boolean adminUser = false;
		
		//PROGRAM START
		System.out.println("Hello! Welcome to Albert 1993.\n"
				+ "Please enter the integer corresponding to your correct login credientials:\n");
		
		//LOGIN
		while (!loggedIn) {
			System.out.println("1. Student\n2. Admin\n");
			
			int n = 0;
			
			while(n != 1 && n != 2) {
				while (!scan.hasNextInt()) {
					System.out.println("Please enter '1' or '2'.");
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
				try {
					student = student.login(username, password);
					System.out.println("Succesfully logged in as " + student.getName());
					loggedIn = true;
					studentUser = true;
				} catch (NullPointerException e) {
					System.out.println("Invalid user credentials or Student does not exist.\n");
				}
			}
			else {
				if (username.contentEquals("Admin") && password.contentEquals("Admin01")) {
					System.out.println("Succesfully logged in as Admin.\n");
					loggedIn = true;
					adminUser = true;
				}
				else {
					System.out.println("Invalid user credentials.\n");
				}
			}
		}
		
		//Display STUDENT commands
		while (studentUser) {
			System.out.println("1. View All Courses \n2. View Open Courses \n3. Register in a Course"
					+ "\n4. Withdraw from Course \n5. View All Currently Registered Courses\n 6. Exit");
			
			int n = 0;
			
			while(n > 6 && n < 1) {
				while (!scan.hasNextInt()) {
					System.out.println("Please enter a vaild integer.");
					scan.next();
				}	
				n = scan.nextInt();
			}
			
			switch (n) {
				case 1: {
					System.out.println(student.viewCourses());
				}
				case 2: {
					System.out.println(student.viewOpenCourses());
				}
				case 3: {
					System.out.println("Please enter the name of the course you would like to Register in: ");
					student.registerInCourse(scan.next());
				}
				case 4: {
					System.out.println("Please enter the name of the course you would like to withdraw from: ");
					student.withdraw(scan.next());
				}
				case 5: {
					System.out.println(student.viewEnrolledCourses());
				}
				case 6: {
					studentUser = false;
					loggedIn = false;
					student = null;
					System.out.println("Succesfully logged out.");
				}
			}
		}

		
		//Display ADMIN commands
		while (adminUser) {
			System.out.println("1. Course Management \n2. Reports\n");
			
			int n = 0;
			
			while(n != 1 && n != 2) {
				while (!scan.hasNextInt()) {
					System.out.println("Please enter '1' or '2'.");
					scan.next();
				}	
				n = scan.nextInt();
			}
		}
		
	}
}
