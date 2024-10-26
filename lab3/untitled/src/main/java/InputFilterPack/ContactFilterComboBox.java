package InputFilterPack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ContactFilterComboBox extends JComboBox {
    private ContactField mainField;
    public ContactFilterComboBox(ContactField mainField){
        super(new String[]{"Да","Нет","Не важно"});
        this.setPreferredSize(new Dimension(80, 30));

        this.mainField = mainField;
        this.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                mainField.changeInputEnable(e.getItem() != "Нет");
            }
        });
    }
}
