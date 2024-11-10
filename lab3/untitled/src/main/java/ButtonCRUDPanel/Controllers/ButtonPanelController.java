package ButtonCRUDPanel.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import DataListPack.DataList;
import EditCreateForm.EditCreateWindow;
import MainPack.UpdateDataInterface;
import Student.StudentShort;
import TableGridPack.Controllers.TableViewController;
import TableGridPack.Navigator.Models.NavigationPageModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonPanelController implements UpdateDataInterface {
    public ButtonPanel buttonPanel;
    public TableViewController tableViewController;
    public DataList<StudentShort> dataListModel;
    public ButtonPanelController(ButtonPanel buttonPanel){
        this.buttonPanel = buttonPanel;

        this.buttonPanel.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.createButtonClick();
            }
        });

        this.buttonPanel.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.clearButtonClick();
            }
        });

        turnOffButtons(0);
    }
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

    public void clearButtonClick(){
        this.tableViewController.setDefaultParams();
    }
    public void createButtonClick(){
        new EditCreateWindow();
    }
    @Override
    public void updatePage() {
        System.out.println(this.dataListModel.getSelectedIds().length);
        turnOffButtons(this.dataListModel.getSelectedIds().length);
    }
}
