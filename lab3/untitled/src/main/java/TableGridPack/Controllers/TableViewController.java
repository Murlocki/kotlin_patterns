package TableGridPack.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import ButtonCRUDPanel.Controllers.ButtonPanelController;
import DataBasePack.StudentListDB;
import DataListPack.DataList;
import DataListPack.DataTable;
import InputFilterPack.Controllers.FilterPanelController;
import MainPack.RefreshDataInterface;
import Student.StudentShort;
import StudentList.StudentList;
import TableGridPack.Navigator.Controllers.NavigatorController;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.Navigator.NavigatorPanel;
import TableGridPack.TableParamsInterfaceSetter;
import TableGridPack.TableView;

import javax.swing.table.DefaultTableModel;
import java.util.Arrays;

public class TableViewController implements RefreshDataInterface, TableParamsInterfaceSetter {
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

        //Создаем модель для навигации по страницам
        this.navigationPageModel = new NavigationPageModel(5);

        this.navigatorController.navigationPageModel = this.navigationPageModel;
        this.mainTableController.navigationPageModel = this.navigationPageModel;

        this.navigationPageModel.subscribe(this.navigatorController.navigatorPanel);
        this.navigationPageModel.subscribe(this.mainTableController.mainTable);

        //Создаем модель списка студентов
        this.studentList = new StudentList(new StudentListDB());
    }

    @Override
    public void refreshData() {
        this.currentDataList = this.studentList.getKNStudentShortList(this.navigationPageModel.elementsPerPage,this.navigationPageModel.currentPage);
        this.currentDataList.setTableView(this.tableView);
        this.currentDataList.notifySubs();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        DefaultTableModel model = (DefaultTableModel) this.tableView.mainTable.getModel();
        model.setColumnIdentifiers(columnNames);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        DefaultTableModel model = (DefaultTableModel) this.tableView.mainTable.getModel();
        String[] currentColumnNames = new String[model.getColumnCount()];
        for (int i = 0; i < currentColumnNames.length; i++) {
            currentColumnNames[i] = model.getColumnName(i); // Получаем имя каждого столбца
        }

        Object[][] arr = new Object[dataTable.getRowCount()-1][dataTable.getColumnCount()];
        for(int i=1;i<dataTable.getRowCount();i++){
            for (int j=0;j<dataTable.getColumnCount();j++){
                arr[i-1][j] = dataTable.getElement(i,j);
            }
        }
        model.setDataVector(arr,currentColumnNames);
    }
}
