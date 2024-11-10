package EditCreateForm;

import EditCreateForm.Controllers.EditCreateWindowController;
import MainPack.UpdateDataInterface;
import Student.StudentValidator;
import StudentList.StudentList;
import TableGridPack.TableView;

import javax.swing.*;
import java.awt.*;

public class EditCreateWindow extends JDialog implements UpdateDataInterface {
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

    public EditCreateWindowController editCreateWindowController;

    public EditCreateWindow(StudentList studentList){
        super();
        this.setSize(300,600);
        this.setTitle("Student edit form");

        this.setModal(true);

        this.createWindow(studentList);
        // ƒелаем дополнительное окно видимым
        this.setVisible(true); // Ёто блокирует основное окно до закрыти€ диалога
    }

    //ƒобавл€ем компоненты
    public void createWindow(StudentList studentList){
        this.setLayout(new FlowLayout());
        this.mainPanel = new JPanel(new GridLayout(8,1,0,10));

        this.surnameField = new InputField(StudentValidator.Companion::isValidSurname$untitled,"Surname",false);
        this.nameField = new InputField(StudentValidator.Companion::isValidName$untitled,"Name",false);
        this.patronymicField = new InputField(StudentValidator.Companion::isValidPatronymic$untitled,"Patronymic",false);
        this.phoneNumberField = new InputField(StudentValidator.Companion::isValidPhone$untitled,"PhoneNumber",true);
        this.telegramField = new InputField(StudentValidator.Companion::isValidTelegram$untitled,"Telegram",true);
        this.emailField = new InputField(StudentValidator.Companion::isValidEmail$untitled,"Email",true);
        this.gitHubField = new InputField(StudentValidator.Companion::isValidGitHub$untitled,"GitHub",true);

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

        this.editCreateWindowController = new EditCreateWindowController(this,studentList);
    }

    @Override
    public void updatePage() {
        this.editCreateWindowController.updatePage();
    }
}
