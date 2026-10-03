import java.sql.*;
import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "root";
        String pass = "password";

        try (Connection con = DriverManager.getConnection(url, user, pass);
             Scanner sc = new Scanner(System.in)) {

            while (true) {
                System.out.println("\n1.Insert Product 2.Retrieve Product 3.Update Qty 4.Low Stock (<10) 5.Exit");
                int ch = sc.nextInt();
                if (ch == 5) break;

                switch (ch) {
                    case 1:
                        System.out.print("ID Name Price Qty: ");
                        PreparedStatement p1 = con.prepareStatement("INSERT INTO Product VALUES(?,?,?,?)");
                        p1.setInt(1, sc.nextInt());
                        p1.setString(2, sc.next());
                        p1.setDouble(3, sc.nextDouble());
                        p1.setInt(4, sc.nextInt());
                        p1.executeUpdate();
                        break;

                    case 2:
                        System.out.print("Product ID: ");
                        PreparedStatement p2 = con.prepareStatement("SELECT * FROM Product WHERE ProductID=?");
                        p2.setInt(1, sc.nextInt());
                        ResultSet r2 = p2.executeQuery();
                        if (r2.next()) System.out.println(r2.getInt(1) + " " + r2.getString(2) + " " + r2.getDouble(3) + " " + r2.getInt(4));
                        else System.out.println("Not Found");
                        break;

                    case 3:
                        System.out.print("Product ID & New Qty: ");
                        int id = sc.nextInt();
                        int qty = sc.nextInt();
                        PreparedStatement p3 = con.prepareStatement("UPDATE Product SET Quantity=? WHERE ProductID=?");
                        p3.setInt(1, qty);
                        p3.setInt(2, id);
                        p3.executeUpdate();
                        break;

                    case 4:
                        ResultSet r4 = con.createStatement().executeQuery("SELECT * FROM Product WHERE Quantity < 10");
                        while (r4.next()) System.out.println(r4.getInt(1) + " " + r4.getString(2) + " Qty: " + r4.getInt(4));
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
