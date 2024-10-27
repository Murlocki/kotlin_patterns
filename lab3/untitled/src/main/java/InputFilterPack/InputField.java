package InputFilterPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class InputField extends JTextField {
    public InputField(){
        super();
        this.setMargin(new Insets(5, 5, 5, 5));
        this.setFont(new Font(this.getFont().getFontName(), Font.PLAIN, 15));

        this.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // �������� ��� ��������� ������ (�� �����������)
            }

            @Override
            public void focusLost(FocusEvent e) {
                // �������� ��� ������ ������
                System.out.println("������� �����: " + InputField.this.getText());
            }
        });


    }
}
