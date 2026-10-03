import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class VehicleModel {
    public double calculateCost(boolean gen, boolean oil, boolean brake, boolean bat) {
        double cost = 0;
        if (gen) cost += 1000;
        if (oil) cost += 800;
        if (brake) cost += 1200;
        if (bat) cost += 500;
        return cost;
    }
}

class VehicleView extends JFrame {
    JTextField regTxt = new JTextField(10);
    JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Two Wheeler", "Car"});
    JCheckBox genCb = new JCheckBox("General Service (1000)");
    JCheckBox oilCb = new JCheckBox("Oil Change (800)");
    JCheckBox brakeCb = new JCheckBox("Brake Service (1200)");
    JCheckBox batCb = new JCheckBox("Battery Check (500)");
    JButton calcBtn = new JButton("Calculate Cost");
    JLabel resLbl = new JLabel("Total Cost: ");

    public VehicleView() {
        setTitle("Service Estimator");
        setLayout(new FlowLayout());

        add(new JLabel("Reg No:"));
        add(regTxt);
        add(new JLabel("Type:"));
        add(typeCombo);
        add(genCb);
        add(oilCb);
        add(brakeCb);
        add(batCb);
        add(calcBtn);
        add(resLbl);

        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

class VehicleController {
    public VehicleController(VehicleModel model, VehicleView view) {
        view.calcBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double total = model.calculateCost(
                    view.genCb.isSelected(),
                    view.oilCb.isSelected(),
                    view.brakeCb.isSelected(),
                    view.batCb.isSelected()
                );
                view.resLbl.setText("Total Cost: Rs." + total);
            }
        });
    }
}

public class Main2 {
    public static void main(String[] args) {
        new VehicleController(new VehicleModel(), new VehicleView());
    }
}
