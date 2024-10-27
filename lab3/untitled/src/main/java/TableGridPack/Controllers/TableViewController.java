package TableGridPack.Controllers;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
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

        this.navigationPageModel.subscribe(this);
        this.mainTableController.mainTableModel.subscribe(this);
    }

    @Override
    public void updatePage(){
        this.studentList.sortByInitials(this.mainTableController.mainTableModel.order);
        this.currentDataList = this.studentList.getKNStudentShortList(this.navigationPageModel.currentPage,this.navigationPageModel.elementsPerPage);
        this.currentDataList.setTableView(this.tableView);
        this.currentDataList.notifySubs();


        this.navigationPageModel.setMaxCountOfPages(this.studentList.getStudentShortCount());
        this.navigatorController.updatePage();
    }

    public void refreshData() {
        this.updatePage();
        this.navigationPageModel.notifySubs();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
       this.mainTableController.setTableParams(columnNames,wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        this.mainTableController.setTableData(dataTable);
    }
}
