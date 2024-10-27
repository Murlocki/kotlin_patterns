package InputFilterPack.Controllers;

import InputFilterPack.ContactField;

import java.awt.event.ItemEvent;
import java.util.Objects;

public class ContactFieldController {
    public ContactField contactField;
    public ContactFieldController(ContactField contactField){
        this.contactField = contactField;

        this.contactField.comboBox.addItemListener(this::changeEnabling);
    }
    public void clearInput(){
        this.contactField.inputField.setText("");
        this.contactField.comboBox.setSelectedIndex(2);
    }

    public void changeEnabling(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.contactField.inputField.setEnabled(this.contactField.comboBox.getSelectedIndex()!= 1);
        }
    }


}
