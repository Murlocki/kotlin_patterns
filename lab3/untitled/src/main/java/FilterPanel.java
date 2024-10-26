import javax.swing.*;
import java.awt.*;

public class FilterPanel extends JPanel {
    private SurnameInitialsField surnameInitialsField;
    public FilterPanel(){
        super();
        //Задали раскладку блоков
        this.setLayout(new GridLayout(1,5));

        //Создаем поле для ввода фамилии и инициалов
        this.surnameInitialsField = new SurnameInitialsField();
        this.add(surnameInitialsField);
    }
}
