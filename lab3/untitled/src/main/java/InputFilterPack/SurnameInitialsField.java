package InputFilterPack;

import InputFilterPack.Controllers.SurnameInitialsFieldController;

import javax.swing.*;
import java.awt.*;

public class SurnameInitialsField extends JPanel {
    public InputField nameField;
    public SurnameInitialsFieldController surnameInitialsFieldController;
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

        //������� ����������
        this.surnameInitialsFieldController = new SurnameInitialsFieldController(this);
    }

}