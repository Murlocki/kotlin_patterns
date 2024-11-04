package MainPack;

import MainPack.Controllers.TabbedPanelController;
import TableGridPack.TableView;

import javax.swing.*;
import java.awt.*;

public class TabbedPanel extends JTabbedPane {

    public TabbedPanelController tabbedPanelController;
    public TableView tableView;
    public JPanel tab2;
    public JPanel tab3;
    public TabbedPanel(){


        this.setBorder(BorderFactory.createEmptyBorder(10, 1, 0, 1));
        //Создаем вкладки
        this.tableView= new TableView();

        this.tab2 = new JPanel();
        this.tab2.add(new JLabel("Содержимое Вкладки 2"));

        this.tab3= new JPanel();
        this.tab3.add(new JLabel("Содержимое Вкладки 3"));

        // Установка цвета фона для содержимого вкладок
        this.tableView.setBackground(Color.WHITE);
        this.tab2.setBackground(Color.WHITE);
        this.tab3.setBackground(Color.WHITE);

        // Добавление вкладок
        this.addTab("Student View List", this.tableView);
        this.addTab("Tab 2", this.tab2);
        this.addTab("Tab 3", this.tab3);

        // Создаем пользовательский компонент вкладки с увеличенным текстом
        this.setTabComponentAt(0, new CustomTabComponent("Student View List", 14,Color.CYAN)); // Вкладка с текстом размера 20
        this.setTabComponentAt(1, new CustomTabComponent("Tab 2", 18,Color.CYAN)); // Вкладка с текстом размера 18

        //Создаем контроллер окна
        this.tabbedPanelController = new TabbedPanelController(this,this.tableView.tableViewController);

    }

}