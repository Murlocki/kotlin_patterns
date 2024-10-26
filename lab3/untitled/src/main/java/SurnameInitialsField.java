import javax.swing.*;
import java.awt.*;

public class SurnameInitialsField extends JPanel {
    private JTextField nameField;
    public SurnameInitialsField(){
        super();
        this.setLayout(new GridLayout(2,1));
        this.nameField = new JTextField();

        this.add(new JLabel("Фамилия и инициалы:"));
        this.add(this.nameField);
    }
}
