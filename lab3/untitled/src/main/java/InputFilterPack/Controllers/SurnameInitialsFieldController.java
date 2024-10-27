package InputFilterPack.Controllers;

import InputFilterPack.SurnameInitialsField;

public class SurnameInitialsFieldController {
    public SurnameInitialsField surnameInitialsField;
    public SurnameInitialsFieldController(SurnameInitialsField surnameInitialsField){
        this.surnameInitialsField = surnameInitialsField;
    }
    public void clearInput(){
        this.surnameInitialsField.nameField.setText("");
    }
}
