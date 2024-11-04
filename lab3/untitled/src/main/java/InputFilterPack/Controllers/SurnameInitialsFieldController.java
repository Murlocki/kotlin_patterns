package InputFilterPack.Controllers;

import InputFilterPack.Models.SurnameInitialsModel;
import InputFilterPack.SurnameInitialsField;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SurnameInitialsFieldController {
    public SurnameInitialsField surnameInitialsField;

    public SurnameInitialsModel surnameInitialsModel;
    public SurnameInitialsFieldController(SurnameInitialsField surnameInitialsField){
        this.surnameInitialsField = surnameInitialsField;

        this.surnameInitialsField.nameField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                // Действия при получении фокуса (не обязательно)
            }

            @Override
            public void focusLost(FocusEvent e) {
                // Действия при потере фокуса
                SurnameInitialsFieldController.this.changeInputText();
            }
        });


        this.surnameInitialsModel = new SurnameInitialsModel(this);
        this.surnameInitialsModel.subscribe(this.surnameInitialsField);

    }
    public void clearInput(){
        this.surnameInitialsModel.clearFilterInput();
        this.setParams();
    }

    public void setParams(){
        this.surnameInitialsField.nameField.setText(this.surnameInitialsModel.filterInput);
    }
    public void changeInputText(){
        System.out.println("Текущий текст: " + this.surnameInitialsField.nameField.getText());
        this.surnameInitialsModel.setFilterInput(this.surnameInitialsField.nameField.getText());
    }
}
