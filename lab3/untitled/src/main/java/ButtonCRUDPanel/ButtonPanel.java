package ButtonCRUDPanel;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import MainPack.UpdateDataInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ButtonPanel extends JPanel implements UpdateDataInterface {
    public JButton createButton;
    public JButton deleteButton;
    public JButton updateButton;

    public JButton reloadButton;
    public JButton clearButton;

    //Создаем контроллер панели
    public ButtonPanelController buttonPanelController;
    public ButtonPanel(){

        //Создаем кнопки панели
        this.createButton = new CrudButton("Create");

        this.deleteButton = new CrudButton("Delete");

        this.updateButton = new CrudButton("Update");

        this.reloadButton = new CrudButton("Reload");

        this.clearButton = new CrudButton("Clear");

        //Настройки отступов панели
        this.setBorder(new EmptyBorder(10,20,10,20));

        //Настройка верстки
        GridLayout layout = new GridLayout(5,1);
        layout.setVgap(40);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100,400));
        panel.setLayout(layout);
        panel.add(createButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(reloadButton);
        panel.add(clearButton);

        this.add(panel);

        //Создание контроллера
        this.buttonPanelController = new ButtonPanelController(this);
    }

    @Override
    public void updatePage() {

    }
}
