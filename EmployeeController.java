import javax.swing.*;
import java.awt.*;

class EmployeeModel {
    private String user = "admin";
    private String pass = "admin123";

    public boolean validate(String u, String p) {
        return user.equals(u) && pass.equals(p);
    }

    public void updatePassword(String newP) {
        this.pass = newP;
    }

    public String getPass() {
        return pass;
    }
}

class EmployeeView extends JFrame {
    JTextField userTxt = new JTextField(10);
    JPasswordField passTxt = new JPasswordField(10);
    JButton loginBtn = new JButton("Login");

    public EmployeeView() {
        setTitle("Login");
        setLayout(new FlowLayout());

        add(new JLabel("User:"));
        add(userTxt);
        add(new JLabel("Pass:"));
        add(passTxt);
        add(loginBtn);

        setSize(250, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void showMainWindow(EmployeeModel model) {
        JFrame mainFrame = new JFrame("Portal");
        JMenuBar mb = new JMenuBar();

        JMenu empMenu = new JMenu("Employee");
        JMenuItem addEmp = new JMenuItem("Add Employee");
        JMenuItem viewEmp = new JMenuItem("View Employee");
        empMenu.add(addEmp);
        empMenu.add(viewEmp);

        JMenu toolsMenu = new JMenu("Tools");
        JMenuItem changePass = new JMenuItem("Change Password");
        toolsMenu.add(changePass);

        JMenu exitMenu = new JMenu("Exit");
        JMenuItem logout = new JMenuItem("Logout");
        JMenuItem exitApp = new JMenuItem("Exit Application");
        exitMenu.add(logout);
        exitMenu.add(exitApp);

        mb.add(empMenu);
        mb.add(toolsMenu);
        mb.add(exitMenu);
        mainFrame.setJMenuBar(mb);

        addEmp.addActionListener(e -> {
            JTextField id = new JTextField(), name = new JTextField(), dept = new JTextField();
            Object[] fields = {"ID:", id, "Name:", name, "Dept:", dept};
            JOptionPane.showConfirmDialog(mainFrame, fields, "Add Employee", JOptionPane.OK_CANCEL_OPTION);
        });

        changePass.addActionListener(e -> {
            JPasswordField oldP = new JPasswordField(), newP = new JPasswordField(), conP = new JPasswordField();
            Object[] fields = {"Old:", oldP, "New:", newP, "Confirm:", conP};
            int res = JOptionPane.showConfirmDialog(mainFrame, fields, "Change Password", JOptionPane.OK_CANCEL_OPTION);
            if (res == JOptionPane.OK_OPTION) {
                if (!String.valueOf(oldP.getPassword()).equals(model.getPass())) {
                    JOptionPane.showMessageDialog(mainFrame, "Incorrect Old Password!");
                } else if (!String.valueOf(newP.getPassword()).equals(String.valueOf(conP.getPassword()))) {
                    JOptionPane.showMessageDialog(mainFrame, "Passwords Do Not Match!");
                } else {
                    model.updatePassword(String.valueOf(newP.getPassword()));
                    JOptionPane.showMessageDialog(mainFrame, "Password Changed!");
                }
            }
        });

        logout.addActionListener(e -> { mainFrame.dispose(); setVisible(true); });
        exitApp.addActionListener(e -> System.exit(0));

        mainFrame.setSize(400, 300);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}

class EmployeeController {
    public EmployeeController(EmployeeModel model, EmployeeView view) {
        view.loginBtn.addActionListener(e -> {
            if (model.validate(view.userTxt.getText(), new String(view.passTxt.getPassword()))) {
                JOptionPane.showMessageDialog(view, "Login Successful!");
                view.setVisible(false);
                view.showMainWindow(model);
            } else {
                JOptionPane.showMessageDialog(view, "Invalid Credentials!");
            }
        });
    }
}

public class Main3 {
    public static void main(String[] args) {
        new EmployeeController(new EmployeeModel(), new EmployeeView());
    }
}
