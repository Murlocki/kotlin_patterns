package TableGridPack.Controllers;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import DataBasePack.DbCon;
import DataBasePack.StudentListDB;
import DataListPack.DataList;
import DataListPack.DataTable;
import InputFilterPack.Controllers.FilterPanelController;
import MainPack.UpdateDataInterface;
import Student.StudentShort;
import StudentList.StudentList;
import TableGridPack.Navigator.Controllers.NavigatorController;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.TableParamsInterfaceSetter;
import TableGridPack.TableView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TableViewController implements UpdateDataInterface, TableParamsInterfaceSetter {
    public TableView tableView;
    private MainTableController mainTableController;
    private NavigatorController navigatorController;
    private ButtonPanelController buttonPanelController;

    private FilterPanelController filterPanelController;

    private NavigationPageModel navigationPageModel;

    //Запихиваем две модели для таблицы
    private StudentList studentList;
    private DataList<StudentShort> currentDataList;

    public TableViewController(TableView tableView,MainTableController mainTableController, NavigatorController navigatorController, ButtonPanelController buttonPanelController,FilterPanelController filterPanelController){
        //Встраиваем существующие контроллеры для удобства их вызова в будущем
        this.tableView = tableView;
        this.mainTableController = mainTableController;
        this.navigatorController = navigatorController;
        this.buttonPanelController = buttonPanelController;
        this.filterPanelController = filterPanelController;

        //Фильтры очищаем в умолчанию
        this.filterPanelController.clearFilters();

        //Создаем модель списка студентов
        this.studentList = new StudentList(new StudentListDB());


        //Создаем модель для навигации по страницам
        this.navigationPageModel = new NavigationPageModel(this.studentList.getStudentShortCount());

        this.navigatorController.navigationPageModel = this.navigationPageModel;
        this.mainTableController.navigationPageModel = this.navigationPageModel;
        this.buttonPanelController.tableViewController = this;

        this.navigationPageModel.subscribe(this);
        this.mainTableController.mainTableModel.subscribe(this);

    }

    @Override
    public void updatePage(){

        try{
            this.studentList.sortByInitials(this.mainTableController.mainTableModel.order);
            this.currentDataList = this.studentList.getKNStudentShortList(this.navigationPageModel.currentPage,this.navigationPageModel.elementsPerPage);

        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        if(!studentList.checkStExists()){
            JOptionPane.showMessageDialog(null, "Не удалось подключиться к базе данных", "Информация", JOptionPane.INFORMATION_MESSAGE);
        }

        this.currentDataList.setTableView(this.tableView);
        this.currentDataList.notifyView();

        this.mainTableController.dataStudentListModel = this.currentDataList;
        this.mainTableController.dataStudentListModel.subscribe(this.buttonPanelController.buttonPanel);
        this.buttonPanelController.dataListModel = this.currentDataList;

        this.navigationPageModel.setMaxCountOfPages(this.studentList.getStudentShortCount());
        this.navigatorController.updatePage();
    }

    public void refreshData() {
        this.updatePage();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
       this.mainTableController.setTableParams(columnNames,wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        this.mainTableController.setTableData(dataTable);
    }

    public void setDefaultParams(){
        this.navigationPageModel.setDefaultParams();
        this.filterPanelController.clearFilters();
    }
}
