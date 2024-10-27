package TableGridPack.Navigator;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;

public class ElemsForPageSelector extends JComboBox {
    public ElemsForPageSelector(){
        int[] options = {10,15,20,25};
        for(int opt:options){
            this.addItem(opt);
        }
    }
}
