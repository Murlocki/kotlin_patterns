import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {
    private SurnameInitialsField surnameInitialsField;
    public FilterPanel(){
        super();
        //������ ��������� ������
        this.setLayout(new GridLayout(1,5));

        //������� ���� ��� ����� ������� � ���������
        this.surnameInitialsField = new SurnameInitialsField();
        this.add(surnameInitialsField);
    }
}
