package MainPack;

import MainPack.Controllers.TabbedPanelController;
import TableGridPack.TableView;

import javax.swing.*;
import java.awt.*;

public class TabbedPanel extends JTabbedPane {

    public TabbedPanelController tabbedPanelController;
    public TabbedPanel(){
        this.tabbedPanelController = new TabbedPanelController(this);


        this.setBorder(BorderFactory.createEmptyBorder(10, 1, 0, 1));
        //Создаем вкладки
        JPanel panel1 = new TableView();

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("Содержимое Вкладки 2"));

        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Содержимое Вкладки 3"));

        // Установка цвета фона для содержимого вкладок
        panel1.setBackground(Color.WHITE);
        panel2.setBackground(Color.WHITE);
        panel3.setBackground(Color.WHITE);

        // Добавление вкладок
        this.addTab("Student View List", panel1);
        this.addTab("Tab 2", panel2);
        this.addTab("Tab 3", panel3);

        // Создаем пользовательский компонент вкладки с увеличенным текстом
        this.setTabComponentAt(0, new CustomTabComponent("Student View List", 14,Color.CYAN)); // Вкладка с текстом размера 20
        this.setTabComponentAt(1, new CustomTabComponent("Tab 2", 18,Color.CYAN)); // Вкладка с текстом размера 18

    }

}