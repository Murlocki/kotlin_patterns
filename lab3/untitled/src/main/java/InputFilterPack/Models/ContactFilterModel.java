package InputFilterPack.Models;

import InputFilterPack.Controllers.ContactFieldController;
import InputFilterPack.States.FilterState;
import InputFilterPack.States.FilterStateNo;
import InputFilterPack.States.FilterStateNoMatter;
import InputFilterPack.States.FilterStateYes;
import MainPack.UpdateDataInterface;

import java.util.HashMap;
import java.util.LinkedList;

public class ContactFilterModel {
    public ContactFieldController contactFieldController;
    public FilterState filterState;


    public int selectedOptionIndex = 2;
    public String filterInput = "";
    private FilterState[] states;


    public LinkedList<UpdateDataInterface> subs = new LinkedList<>();
    public ContactFilterModel(ContactFieldController contactFieldController,String fieldName){
        this.contactFieldController = contactFieldController;


        this.states = new FilterState[]{new FilterStateYes(fieldName), new FilterStateNo(fieldName), new FilterStateNoMatter(fieldName)};
        this.filterState = this.states[selectedOptionIndex];
    }
    public void subscribe(UpdateDataInterface sub){
        this.subs.add(sub);
    }

    public void setSelectedOption(int selectedOptionIndex) {
        this.selectedOptionIndex = selectedOptionIndex;
        this.filterState = this.states[selectedOptionIndex];
        System.out.println(this.filterState);
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
