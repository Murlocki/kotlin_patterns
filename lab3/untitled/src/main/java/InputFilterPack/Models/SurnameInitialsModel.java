package InputFilterPack.Models;
import InputFilterPack.Controllers.SurnameInitialsFieldController;
import InputFilterPack.States.FilterState;
import InputFilterPack.States.FilterStateSurname;
import InputFilterPack.States.FilterStateYes;
import MainPack.UpdateDataInterface;

import java.util.LinkedList;

public class SurnameInitialsModel {
    public String filterInput = "";
    public LinkedList<UpdateDataInterface> subs = new LinkedList<>();
    public SurnameInitialsFieldController surnameInitialsFieldController;
    public FilterState filterState;
    public SurnameInitialsModel(SurnameInitialsFieldController surnameInitialsFieldController){
        this.surnameInitialsFieldController = surnameInitialsFieldController;
        this.filterState = new FilterStateSurname();
    }
    public void subscribe(UpdateDataInterface sub){
        this.subs.add(sub);
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
    }
}
