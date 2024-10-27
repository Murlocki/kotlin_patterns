package TableGridPack.Models;

import MainPack.UpdateDataInterface;

import javax.swing.table.TableRowSorter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;

public class MainTableModel {
    private int clickCount = 0;
    private int lastSortedColumn = 1;

    public int order = 0;

    public LinkedList<UpdateDataInterface> subscribers = new LinkedList<>();
    public void sortOrder(int column){
        // Проверяем, был ли нажат тот же столбец
        if (this.lastSortedColumn == column) {
            this.clickCount += 1;
            if(this.clickCount==1)  this.order = -1;
            else if (this.clickCount==2) this.order = 1;
            else {
                this.order = 0;
                this.clickCount = 0;
            }
            this.notifySubs();

        }
    }
    public void notifySubs(){
        for(UpdateDataInterface in: subscribers){
            in.updatePage();
        }
    }
    public void subscribe(UpdateDataInterface obj){
        this.subscribers.add(obj);
    }
}
