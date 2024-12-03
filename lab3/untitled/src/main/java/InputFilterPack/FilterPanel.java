package InputFilterPack;

import InputFilterPack.ContactField;
import InputFilterPack.Controllers.FilterPanelController;
import InputFilterPack.SurnameInitialsField;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FilterPanel extends JPanel {
    public SurnameInitialsField surnameInitialsField;
    public ContactField githubField;
    public ContactField emailField;
    public ContactField phoneField;
    public ContactField telegramField;

    public FilterPanelController filterPanelController;

    public FilterPanel(){
        GridLayout layout =new GridLayout(1,5);
        layout.setHgap(30);
        this.setLayout(layout);
        this.setBackground(Color.WHITE);
        this.setBorder(new EmptyBorder(20, 20, 20, 20));

        //������� ���� ��� ����� ������� � ���������
        this.surnameInitialsField = new SurnameInitialsField();
        this.add(surnameInitialsField);

        //������� ���� ��� ���������� ����
        this.githubField = new ContactField("Git","gitHub");
        this.add(this.githubField);

        //������� ���� ��� ���������� �����
        this.emailField = new ContactField("Email","email");
        this.add(this.emailField);

        //������� ���� ��� ���������� ����������
        this.phoneField = new ContactField("Telegram","telegram");
        this.add(this.phoneField);


        //������� ���� ��� ���������� �������
        this.telegramField = new ContactField("Phone","phoneNumber");
        this.add(this.telegramField);

        //������� ����������
        this.filterPanelController = new FilterPanelController(
                this,
                this.surnameInitialsField.surnameInitialsFieldController,
                this.emailField.contactFieldController,
                this.phoneField.contactFieldController,
                this.githubField.contactFieldController,
                this.telegramField.contactFieldController
                );
    }
}
