import java.sql.*;
import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String pass = "password";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Scanner sc = new Scanner(System.in)) {

            System.out.print("Enter Course Code: ");
            String code = sc.next();

            PreparedStatement ps = con.prepareStatement("SELECT * FROM CourseRegistration WHERE CourseCode=?");
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("StudentID") + 
                                   " | Name: " + rs.getString("StudentName") + 
                                   " | Course: " + rs.getString("CourseName") + 
                                   " | Sem: " + rs.getInt("Semester"));
            }

            if (!found) {
                System.out.println("No students registered for this course code.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
