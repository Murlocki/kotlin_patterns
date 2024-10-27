package TableGridPack.Navigator.Models;

import MainPack.RefreshDataInterface;
import TableGridPack.Navigator.NavigatorPanel;

import java.awt.event.ItemEvent;
import java.util.LinkedList;

public class NavigationPageModel {
    public int currentPage = 1;
    public int maxCountOfPages;

    public int elementsPerPage = 15;

    public LinkedList<RefreshDataInterface> subscribers = new LinkedList<>();
    public NavigationPageModel(int maxCountOfPages){
        this.maxCountOfPages = maxCountOfPages;
    }


    public void subscribe(RefreshDataInterface subscriber){
        subscribers.add(subscriber);
    }

    public void nextPage(){
        if(this.currentPage<this.maxCountOfPages){
            this.currentPage +=1;
            for(RefreshDataInterface sub:this.subscribers){
                sub.refreshData();
            }
        }
    }
    public void prevPage(){
        if(this.currentPage>1){
            this.currentPage -=1;
            for(RefreshDataInterface sub:this.subscribers){
                sub.refreshData();
            }
        }
    }
    public void elementPerPageSelected(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("Количество элементов на странице"+e.getItem());
            this.elementsPerPage = (int) e.getItem();
            this.currentPage = 1;
            for(RefreshDataInterface sub:this.subscribers){
                sub.refreshData();
            }
        }
    }
}
