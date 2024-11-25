package ButtonCRUDPanel.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import DataListPack.DataList;
import EditCreateForm.Controllers.UpdateController;
import EditCreateForm.EditCreateWindow;
import EditCreateForm.Factory.ControllerFactory;
import MainPack.UpdateDataInterface;
import Student.StudentShort;
import TableGridPack.Controllers.TableViewController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ButtonPanelController implements UpdateDataInterface {
    public ButtonPanel buttonPanel;
    public TableViewController tableViewController;
    public DataList<StudentShort> dataListModel;


    public ButtonPanelController(ButtonPanel buttonPanel){
        this.buttonPanel = buttonPanel;

        //Обработка действия кнопки добавления
        this.buttonPanel.createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.createButtonClick();
            }
        });

        //Обработка действия кнопки обновления фио
        this.buttonPanel.updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateFIOController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });

        //Обработка действия кнопки обновления гита
        this.buttonPanel.updateGitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateGitController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });

        //Обработка действия кнопки обновления телефона
        this.buttonPanel.updatePhoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdatePhoneController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });

        //Обработка действия кнопки обновления телеграмма
        this.buttonPanel.updateTelegramButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateTelegramController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });

        //Обработка действия кнопки обновления почты
        this.buttonPanel.updateEmailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCreateWindow window = new EditCreateWindow();
                ButtonPanelController.this.updateButtonClick(ControllerFactory.createUpdateEmailController(window,ButtonPanelController.this.tableViewController.studentList),window);
            }
        });

        //Обработка действия кнопки очистки
        this.buttonPanel.clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.clearButtonClick();
            }
        });

        //Обработка действия кнопки удаления записей
        this.buttonPanel.deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.deleteButtonClick();
            }
        });

        //Обработка действия кнопки перезагрузки
        this.buttonPanel.reloadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonPanelController.this.tableViewController.setDefaultParams();
            }
        });

        turnOffButtons(0);
    }

    //Включение кнопок редактирования
    public void turnOnEditButton(int count){
        if(count==1){
            this.buttonPanel.updateButton.setEnabled(true);
            this.buttonPanel.updateGitButton.setEnabled(true);
            this.buttonPanel.updateEmailButton.setEnabled(true);
            this.buttonPanel.updateTelegramButton.setEnabled(true);
            this.buttonPanel.updatePhoneButton.setEnabled(true);
        }
    }

    //Включение кнопки удаления
    public void turnOnDeleteButton(int count){
        if(count>=1){
            this.buttonPanel.deleteButton.setEnabled(true);
        }
    }


    //Выключение кнопок удаления и обновления
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

    //Обработка действия кнопки очистки
    public void clearButtonClick(){
        this.tableViewController.setDefaultParams();
    }

    //Обработка действия кнопки добавления
    public void createButtonClick(){
        EditCreateWindow window = new EditCreateWindow();
        window.setEditCreateWindowController(ControllerFactory.createCreateController(window,this.tableViewController.studentList));
        window.setVisible(true);
    }

    //Обработка действия кнопки обновления
    public void updateButtonClick(UpdateController controller,EditCreateWindow window){
        int selectedIds =this.dataListModel.getSelectedIds()[0];

        controller.id = (int) this.dataListModel.getData().getElement(selectedIds + 1, 0);
        window.setEditCreateWindowController(controller);
        controller.setStudentInfo();
        window.setVisible(true);
    }

    //Обработка действия кнопки удаления
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

    //Метод обновления вьюхи при изменении модели
    @Override
    public void updatePage() {
        turnOffButtons(this.dataListModel.getSelectedIds().length);
    }
}
