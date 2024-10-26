package TableGridPack.Navigator;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.util.Arrays;

public class ElemsForPageSelector extends JComboBox {
    public ElemsForPageSelector(){
        int[] options = {5,10,15,20,25};
        for(int opt:options){
            this.addItem(opt);
        }
        this.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Количество элементов на странице"+e.getItem());
            }
        });
    }
}
