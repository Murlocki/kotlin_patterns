package InputFilterPack;

import InputFilterPack.Controllers.ContactFieldController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ContactFilterComboBox extends JComboBox {
    public ContactFilterComboBox(){
        super(new String[]{"��","���","�� �����"});
        this.setPreferredSize(new Dimension(80, 30));
    }
}
