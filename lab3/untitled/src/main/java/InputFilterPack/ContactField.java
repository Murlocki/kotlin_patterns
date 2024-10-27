package InputFilterPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import InputFilterPack.Controllers.ContactFieldController;
public class ContactField extends JPanel{
    public InputField inputField ;
    public ContactFilterComboBox comboBox;
    public ContactFieldController  contactFieldController;
    public ContactField(String title){
        //Настройка окошка
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.WHITE);

        //Настройка поля ввода
        this.inputField = new InputField();


        //Создаем заголовок
        JLabel label = new JLabel(title);
        label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 15));

        //Создаем комбобокс
        this.comboBox = new ContactFilterComboBox();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.X_AXIS));
        panel.add(this.inputField);
        panel.add(this.comboBox);



        this.add(label);
        this.add(panel);

        //Создаем контроллер
        this.contactFieldController = new ContactFieldController(this);
    }
}
