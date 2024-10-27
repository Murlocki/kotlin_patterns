package InputFilterPack;

import InputFilterPack.Controllers.SurnameInitialsFieldController;

import javax.swing.*;
import java.awt.*;

public class SurnameInitialsField extends JPanel {
    public InputField nameField;
    public SurnameInitialsFieldController surnameInitialsFieldController;
    public SurnameInitialsField(){
        //Настройка окошка
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.WHITE);

        //Настройка поля ввода
        this.nameField = new InputField();

        //Создаем заголовок
        JLabel label = new JLabel("Фамилия и инициалы:");
        label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 15));

        this.add(label);
        this.add(this.nameField);

        //Создаем контроллер
        this.surnameInitialsFieldController = new SurnameInitialsFieldController(this);
    }

}
