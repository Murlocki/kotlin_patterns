package TableGridPack.Navigator.Models;

import MainPack.UpdateDataInterface;

import java.awt.event.ItemEvent;
import java.util.LinkedList;

public class NavigationPageModel {
    public int currentPage = 1;
    public int maxCountOfPages;

    public int elementsPerPage = 10;

    public LinkedList<UpdateDataInterface> subscribers = new LinkedList<>();
    public NavigationPageModel(int maxRecordsCount){
        this.maxCountOfPages = (int) Math.ceil((double) maxRecordsCount /elementsPerPage);
    }

    public void setMaxCountOfPages(int maxRecordsCount) {
        this.maxCountOfPages = (int) Math.ceil((double) maxRecordsCount /elementsPerPage);
    }

    public void subscribe(UpdateDataInterface subscriber){
        subscribers.add(subscriber);
    }

    public void notifySubs(){
        for(UpdateDataInterface sub:this.subscribers){
            sub.updatePage();
        }
    }

    public void nextPage(){
        if(this.currentPage<this.maxCountOfPages){
            this.currentPage +=1;
            this.notifySubs();
        }
    }
    public void prevPage(){
        if(this.currentPage>1){
            this.currentPage -=1;
            this.notifySubs();
        }
    }
    public void elementPerPageSelected(ItemEvent e){
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println("Количество элементов на странице"+e.getItem());
            this.elementsPerPage = (int) e.getItem();
            this.currentPage = 1;
            this.notifySubs();
        }
    }

    public void setDefaultParams(){
        this.currentPage = 1;
        this.elementsPerPage = 10;
        this.notifySubs();
    }
}
