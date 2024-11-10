package EditCreateForm;

import Student.StudentValidator;
import TableGridPack.TableView;

import javax.swing.*;
import java.awt.*;

public class EditCreateWindow extends JDialog {
    public JPanel mainPanel;
    public InputField surnameField;
    public InputField nameField;
    public InputField patronymicField;
    public InputField phoneNumberField;
    public InputField emailField;
    public InputField telegramField;
    public InputField gitHubField;

    public JButton acceptButton;
    public JButton declineButton;

    public EditCreateWindow(){
        super();
        this.setSize(300,600);
        this.setTitle("Student edit form");

        this.setModal(true);

        this.createWindow();
        // ƒелаем дополнительное окно видимым
        this.setVisible(true); // Ёто блокирует основное окно до закрыти€ диалога
    }

    //ƒобавл€ем компоненты
    public void createWindow(){
        this.setLayout(new FlowLayout());
        this.mainPanel = new JPanel(new GridLayout(8,1,0,10));

        this.surnameField = new InputField(StudentValidator.Companion::isValidSurname$untitled,"Surname");
        this.nameField = new InputField(StudentValidator.Companion::isValidName$untitled,"Name");
        this.patronymicField = new InputField(StudentValidator.Companion::isValidPatronymic$untitled,"Patronymic");
        this.phoneNumberField = new InputField(StudentValidator.Companion::isValidPhone$untitled,"PhoneNumber");
        this.telegramField = new InputField(StudentValidator.Companion::isValidTelegram$untitled,"Telegram");
        this.emailField = new InputField(StudentValidator.Companion::isValidEmail$untitled,"Email");
        this.gitHubField = new InputField(StudentValidator.Companion::isValidGitHub$untitled,"GitHub");

        this.mainPanel.add(surnameField);
        this.mainPanel.add(nameField);
        this.mainPanel.add(patronymicField);
        this.mainPanel.add(phoneNumberField);
        this.mainPanel.add(telegramField);
        this.mainPanel.add(emailField);
        this.mainPanel.add(gitHubField);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        this.acceptButton = new JButton("Ok");
        Font font = this.acceptButton.getFont();
        this.acceptButton.setFont(new Font(font.getFontName(), font.getStyle(), 16));
        this.acceptButton.setPreferredSize(new Dimension(100,30));

        this.declineButton = new JButton("Decline");
        this.declineButton.setFont(new Font(font.getFontName(), font.getStyle(), 16));
        this.declineButton.setPreferredSize(new Dimension(100,30));

        buttonPanel.add(acceptButton,BorderLayout.WEST);
        buttonPanel.add(declineButton,BorderLayout.EAST);
        this.mainPanel.add(buttonPanel);


        this.getContentPane().add(mainPanel);
    }
}
