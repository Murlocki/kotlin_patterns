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

        //Create surname and initials
        this.surnameInitialsField = new SurnameInitialsField();
        this.add(surnameInitialsField);

        //Create git
        this.githubField = new ContactField("Git","gitHub");
        this.add(this.githubField);

        //Create email
        this.emailField = new ContactField("Email","email");
        this.add(this.emailField);

        //Create telegram
        this.phoneField = new ContactField("Telegram","telegram");
        this.add(this.phoneField);


        //Create phone number
        this.telegramField = new ContactField("Phone","phoneNumber");
        this.add(this.telegramField);

        //Create controller
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
