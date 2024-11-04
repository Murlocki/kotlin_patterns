package InputFilterPack.Controllers;

import InputFilterPack.ContactField;
import InputFilterPack.InputField;
import InputFilterPack.Models.ContactFilterModel;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.util.Objects;

public class ContactFieldController {
    public ContactField contactField;
    public ContactFilterModel contactFilterModel;
    public ContactFieldController(ContactField contactField){
        this.contactField = contactField;

        this.contactField.comboBox.addItemListener(this::changeSelectedItem);
        this.contactField.inputField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Действия при получении фокуса (не обязательно)
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Действия при потере фокуса
                ContactFieldController.this.changeInputText();
            }
        });


        this.contactFilterModel = new ContactFilterModel(this);
        this.contactFilterModel.subscribe(this.contactField);

    }
    public void clearInput(){
        this.contactFilterModel.clearFilterInput();
        this.setParams();
    }

    public void changeSelectedItem(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            this.contactFilterModel.setSelectedOptionIndex(this.contactField.comboBox.getSelectedIndex());
        }
    }
    public void setParams(){
        this.contactField.comboBox.setSelectedIndex(this.contactFilterModel.selectedOptionIndex);
        this.contactField.inputField.setText(this.contactFilterModel.filterInput);
        this.contactField.inputField.setEnabled(this.contactFilterModel.selectedOptionIndex != 1);
    }

    public void changeInputText(){
        System.out.println("Текущий текст: " + this.contactField.inputField.getText());
        this.contactFilterModel.setFilterInput(this.contactField.inputField.getText());
    }

}
