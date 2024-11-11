package ButtonCRUDPanel;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import MainPack.UpdateDataInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ButtonPanel extends JPanel implements UpdateDataInterface {
    public JButton createButton;
    public JButton deleteButton;
    public JButton updateButton;

    public JButton updateGitButton;
    public JButton updateEmailButton;
    public JButton updateTelegramButton;
    public JButton updatePhoneButton;

    public JButton reloadButton;
    public JButton clearButton;

    //Создаем контроллер панели
    public ButtonPanelController buttonPanelController;
    public ButtonPanel(){

        //Создаем кнопки панели
        this.createButton = new CrudButton("Create");

        this.deleteButton = new CrudButton("Delete");

        this.updateButton = new CrudButton("Update");

        this.updateGitButton = new CrudButton("Update Git");
        this.updateEmailButton = new CrudButton("Update Email");
        this.updateTelegramButton = new CrudButton("Update Telegram");
        this.updatePhoneButton = new CrudButton("Update phone");


        this.reloadButton = new CrudButton("Reload");

        this.clearButton = new CrudButton("Clear");

        //Настройки отступов панели
        this.setBorder(new EmptyBorder(10,20,10,20));

        //Настройка верстки
        GridLayout layout = new GridLayout(9,1);
        layout.setVgap(10);
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        panel.add(createButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(updateGitButton);
        panel.add(updateEmailButton);
        panel.add(updateTelegramButton);
        panel.add(updatePhoneButton);
        panel.add(reloadButton);
        panel.add(clearButton);

        this.setLayout(new GridLayout(1,1));

        this.add(panel);
        //Создание контроллера
        this.buttonPanelController = new ButtonPanelController(this);
    }

    @Override
    public void updatePage() {
        this.buttonPanelController.updatePage();
    }
}
