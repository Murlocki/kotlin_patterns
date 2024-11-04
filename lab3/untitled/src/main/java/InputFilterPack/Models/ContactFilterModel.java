package InputFilterPack.Models;

import InputFilterPack.Controllers.ContactFieldController;
import MainPack.UpdateDataInterface;

import java.util.LinkedList;

public class ContactFilterModel {
    public ContactFieldController contactFieldController;

    public int selectedOptionIndex = 2;
    public String filterInput = "";

    public LinkedList<UpdateDataInterface> subs = new LinkedList<>();
    public ContactFilterModel(ContactFieldController contactFieldController){
        this.contactFieldController = contactFieldController;
    }
    public void subscribe(UpdateDataInterface sub){
        this.subs.add(sub);
    }

    public void setSelectedOptionIndex(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
        this.notifySubs();
    }
    public void setFilterInput(String text) {
        this.filterInput = text;
        this.notifySubs();
    }

    public void notifySubs(){
        for(UpdateDataInterface sub:this.subs){
            sub.updatePage();
        }
    }

    public void clearFilterInput(){
        this.filterInput = "";
        this.selectedOptionIndex = 2;
    }
}
