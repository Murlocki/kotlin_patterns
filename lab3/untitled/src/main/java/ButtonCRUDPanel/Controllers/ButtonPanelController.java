package ButtonCRUDPanel.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import DataListPack.DataList;
import DataListPack.DataTable;
import EditCreateForm.Controllers.EditCreateWindowController;
import EditCreateForm.Controllers.UpdateController;
import EditCreateForm.Controllers.UpdateFIOController;
import EditCreateForm.EditCreateWindow;
import EditCreateForm.Factory.ControllerFactory;
import MainPack.UpdateDataInterface;
import Student.StudentShort;
import StudentList.StudentList;
import TableGridPack.Controllers.TableViewController;
import TableGridPack.Navigator.Models.NavigationPageModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.LinkedList;

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

        this.buttonPanel.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });
        this.buttonPanel.updateGitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateGitController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });
        this.buttonPanel.updatePhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdatePhoneController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });
        this.buttonPanel.updateTelegramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateTelegramController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });
        this.buttonPanel.updateEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateEmailController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });


        this.buttonPanel.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.clearButtonClick();
            }
        });

        this.buttonPanel.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.deleteButtonClick();
            }
        });


        turnOffButtons(0);
    }
    public void turnOnEditButton(int count){
        if(count==1){
            this.buttonPanel.updateButton.setEnabled(true);
            this.buttonPanel.updateGitButton.setEnabled(true);
            this.buttonPanel.updateEmailButton.setEnabled(true);
            this.buttonPanel.updateTelegramButton.setEnabled(true);
            this.buttonPanel.updatePhoneButton.setEnabled(true);
        }
    }
    public void turnOnDeleteButton(int count){
        if(count>=1){
            this.buttonPanel.deleteButton.setEnabled(true);
        }
    }
    public void turnOffButtons(int stringCount){
        this.buttonPanel.updateButton.setEnabled(false);
        this.buttonPanel.deleteButton.setEnabled(false);
        this.buttonPanel.updateGitButton.setEnabled(false);
        this.buttonPanel.updateEmailButton.setEnabled(false);
        this.buttonPanel.updateTelegramButton.setEnabled(false);
        this.buttonPanel.updatePhoneButton.setEnabled(false);
        this.turnOnDeleteButton(stringCount);
        this.turnOnEditButton(stringCount);
    }

    public void clearButtonClick(){
        this.tableViewController.setDefaultParams();
    }
    public void createButtonClick(){
        EditCreateWindow window = new EditCreateWindow();
        window.setEditCreateWindowController(ControllerFactory.createCreateController(window,this.tableViewController.studentList));
        window.setVisible(true);
    }
    public void updateButtonClick(UpdateController controller,EditCreateWindow window){
        int selectedIds =this.dataListModel.getSelectedIds()[0];

        controller.id = (int) this.dataListModel.getData().getElement(selectedIds + 1, 0);
        window.setEditCreateWindowController(controller);
        controller.setStudentInfo();
        window.setVisible(true);
    }

    public void deleteButtonClick(){
        int[]selectedIds =this.dataListModel.getSelectedIds();
        LinkedList<Integer>selectedIndexes = new LinkedList<>();
        for(int i:selectedIds) {
            selectedIndexes.add((Integer) this.dataListModel.getData().getElement(i + 1, 0));
        }
        for(int i:selectedIndexes){
            this.tableViewController.studentList.deleteById(i);
        }
    }
    @Override
    public void updatePage() {
        System.out.println(this.dataListModel.getSelectedIds().length);
        turnOffButtons(this.dataListModel.getSelectedIds().length);
    }
}
