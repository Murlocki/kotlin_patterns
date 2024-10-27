package ButtonCRUDPanel.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import DataListPack.DataList;
import MainPack.UpdateDataInterface;
import Student.StudentShort;

public class ButtonPanelController implements UpdateDataInterface {
    public ButtonPanel buttonPanel;
    public ButtonPanelController(ButtonPanel buttonPanel){
        this.buttonPanel = buttonPanel;
        turnOffButtons(0);
    }
    public DataList<StudentShort> dataListModel;
    public void turnOnEditButton(int count){
        if(count>=1){
            this.buttonPanel.updateButton.setEnabled(true);
        }
    }
    public void turnOnDeleteButton(int count){
        if(count==1){
            this.buttonPanel.deleteButton.setEnabled(true);
        }
    }
    public void turnOffButtons(int stringCount){
        this.buttonPanel.updateButton.setEnabled(false);
        this.buttonPanel.deleteButton.setEnabled(false);
        this.turnOnDeleteButton(stringCount);
        this.turnOnEditButton(stringCount);
    }

    @Override
    public void updatePage() {
        System.out.println(this.dataListModel.getSelectedIds().length);
        turnOffButtons(this.dataListModel.getSelectedIds().length);
    }
}
