import java.sql.*;
import java.util.Scanner;

public class Main4 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String pass = "password";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n1.Insert 2.Search 3.Display Available 4.Change Availability 5.Exit");
                int ch = sc.nextInt();
                if (ch == 5) break;

                switch (ch) {
                    case 1:
                        System.out.print("ID Title Author Price Available(true/false): ");
                        int id = sc.nextInt();
                        String title = sc.next();
                        String author = sc.next();
                        double price = sc.nextDouble();
                        boolean avail = sc.nextBoolean();
                        PreparedStatement p1 = con.prepareStatement("INSERT INTO Book VALUES(?,?,?,?,?)");
                        p1.setInt(1, id); p1.setString(2, title); p1.setString(3, author); p1.setDouble(4, price); p1.setBoolean(5, avail);
                        p1.executeUpdate();
                        break;

                    case 2:
                        System.out.print("Book ID: ");
                        PreparedStatement p2 = con.prepareStatement("SELECT * FROM Book WHERE BookID=?");
                        p2.setInt(1, sc.nextInt());
                        ResultSet r2 = p2.executeQuery();
                        if (r2.next()) System.out.println(r2.getInt(1) + " " + r2.getString(2) + " " + r2.getString(3) + " " + r2.getDouble(4) + " " + r2.getBoolean(5));
                        else System.out.println("Not Found");
                        break;

                    case 3:
                        ResultSet r3 = con.createStatement().executeQuery("SELECT * FROM Book WHERE Availability=true");
                        while (r3.next()) System.out.println(r3.getInt(1) + " " + r3.getString(2) + " " + r3.getString(3));
                        break;

                    case 4:
                        System.out.print("Book ID & New Availability(true/false): ");
                        PreparedStatement p4 = con.prepareStatement("UPDATE Book SET Availability=? WHERE BookID=?");
                        p4.setBoolean(1, sc.nextBoolean());
                        p4.setInt(2, sc.nextInt());
                        p4.executeUpdate();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
