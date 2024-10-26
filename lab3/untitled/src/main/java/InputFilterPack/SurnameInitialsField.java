package InputFilterPack;

import javax.swing.*;
import java.awt.*;

public class SurnameInitialsField extends JPanel {
    private InputField nameField;
    public SurnameInitialsField(){
        //��������� ������
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.WHITE);

        //��������� ���� �����
        this.nameField = new InputField();

        //������� ���������
        JLabel label = new JLabel("������� � ��������:");
        label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 15));

        this.add(label);
        this.add(this.nameField);
    }
}
