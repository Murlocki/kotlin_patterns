package TableGridPack.Controllers;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import DataBasePack.DbCon;
import DataBasePack.StudentListDB;
import StudentList.StudentListJson;
import StudentList.StudentListTxt;
import StudentList.StudentListYaml;
import StudentList.StudentListAdapterExtend;


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
import java.util.LinkedList;
import java.util.List;

public class TableViewController implements UpdateDataInterface, TableParamsInterfaceSetter {
    public TableView tableView;
    private MainTableController mainTableController;
    private NavigatorController navigatorController;
    private ButtonPanelController buttonPanelController;

    private FilterPanelController filterPanelController;

    private NavigationPageModel navigationPageModel;

    //Запихиваем две модели для таблицы
    public StudentList studentList;
    public DataList<StudentShort> currentDataList;

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
        this.studentList.subscribe(this);

        //Создаем модель для навигации по страницам
        this.navigationPageModel = new NavigationPageModel(this.studentList.getStudentShortCount());

        this.navigatorController.navigationPageModel = this.navigationPageModel;
        this.mainTableController.navigationPageModel = this.navigationPageModel;
        this.buttonPanelController.tableViewController = this;

        this.navigationPageModel.subscribePageUpdate(this.tableView);
        this.navigationPageModel.subscribeNavigatorUpdate(this.navigatorController);

        this.mainTableController.mainTableModel.subscribe(this.tableView);

    }

    @Override
    public void updatePage(){
        this.checkStudentList();


        this.studentList.sortByInitials(this.mainTableController.mainTableModel.order);
        this.currentDataList = this.studentList.getKNStudentShortList(this.navigationPageModel.currentPage,this.navigationPageModel.elementsPerPage);

        this.currentDataList.setTableView(this.tableView);

        this.mainTableController.dataStudentListModel = this.currentDataList;
        this.mainTableController.dataStudentListModel.subscribe(this.buttonPanelController.buttonPanel);
        this.buttonPanelController.dataListModel = this.currentDataList;

        this.navigationPageModel.setMaxCountOfPages(this.studentList.getStudentShortCount());

    }

    private void checkStudentList(){
        //Описываем обработку случая отсутствия списка
        this.studentList = this.createSourceStudentList();
        this.studentList.subscribe(this);
        if(!studentList.checkStExists()){
            SwingUtilities.invokeLater(()->{
                JOptionPane.showMessageDialog(this.tableView, "Не удалось подключиться к базе данных", "Информация", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    private StudentList createSourceStudentList(){
        //Создаем модель списка студентов
        StudentListDB st = new StudentListDB();
        if(st.checkAdapterExisting()){
            return new StudentList(st);
        }

        //Проверка для других списков
        LinkedList<StudentListAdapterExtend> list = new LinkedList<>();
        list.add(new StudentListTxt("src/main/resources/t.txt","src/main/resources/t.txt"));
        list.add(new StudentListYaml("src/main/resources/test.yaml","src/main/resources/test.yaml"));
        list.add(new StudentListJson("src/main/resources/file.json","src/main/resources/file.json"));

        StudentListAdapterExtend resultList = null;
        for (StudentListAdapterExtend studentListAdapter : list) {
            if (studentListAdapter.checkAdapterExisting()) {
                resultList = studentListAdapter;
                resultList.processRead();
                return new StudentList(resultList);
            }
        }
        return new StudentList(new StudentListDB());
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
