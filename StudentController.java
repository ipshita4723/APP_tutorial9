import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StudentModel {
    private String name;
    private double m1, m2, m3, total, avg;
    private String grade;

    public void calculate(String name, double m1, double m2, double m3) {
        this.name = name;
        this.m1 = m1;
        this.m2 = m2;
        this.m3 = m3;
        this.total = m1 + m2 + m3;
        this.avg = total / 3.0;

        if (avg >= 90) grade = "A";
        else if (avg >= 75) grade = "B";
        else if (avg >= 60) grade = "C";
        else if (avg >= 50) grade = "D";
        else grade = "F";
    }

    public String getResult() {
        return String.format("Total: %.2f | Avg: %.2f | Grade: %s", total, avg, grade);
    }
}

class StudentView extends JFrame {
    JTextField nameTxt = new JTextField(10);
    JTextField m1Txt = new JTextField(5);
    JTextField m2Txt = new JTextField(5);
    JTextField m3Txt = new JTextField(5);
    JButton calcBtn = new JButton("Calculate Result");
    JLabel resLbl = new JLabel("Result: ");

    public StudentView() {
        setTitle("Grade Calculator");
        setLayout(new FlowLayout());

        add(new JLabel("Name:"));
        add(nameTxt);
        add(new JLabel("M1:"));
        add(m1Txt);
        add(new JLabel("M2:"));
        add(m2Txt);
        add(new JLabel("M3:"));
        add(m3Txt);
        add(calcBtn);
        add(resLbl);

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class StudentController {
    public StudentController(StudentModel model, StudentView view) {
        view.calcBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = view.nameTxt.getText();
                    double m1 = Double.parseDouble(view.m1Txt.getText());
                    double m2 = Double.parseDouble(view.m2Txt.getText());
                    double m3 = Double.parseDouble(view.m3Txt.getText());

                    model.calculate(name, m1, m2, m3);
                    view.resLbl.setText("Result: " + model.getResult());
                } catch (Exception ex) {
                    view.resLbl.setText("Invalid Input!");
                }
            }
        });
    }
}

public class Main {
    public static void main(String[] args) {
        StudentModel model = new StudentModel();
        StudentView view = new StudentView();
        new StudentController(model, view);
    }
}
