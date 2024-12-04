package TableGridPack.Controllers;

import ButtonCRUDPanel.Controllers.ButtonPanelController;
import DataBasePack.DbCon;
import DataBasePack.StudentListDB;
import Student.Student;
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
import StudentList.StudentListDecorator;
import StudentList.StudentListComponent;
import TableGridPack.Navigator.Controllers.NavigatorController;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.TableParamsInterfaceSetter;
import TableGridPack.TableView;

import javax.swing.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class TableViewController implements UpdateDataInterface, TableParamsInterfaceSetter {
    public TableView tableView;
    private MainTableController mainTableController;
    private NavigatorController navigatorController;
    private ButtonPanelController buttonPanelController;

    public FilterPanelController filterPanelController;

    private NavigationPageModel navigationPageModel;

    //Запихиваем две модели для таблицы
    public StudentListComponent studentList;
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
        this.studentList = new StudentListDecorator(new StudentList(new StudentListDB()),this);
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

        this.studentList.sortBy(this.mainTableController.mainTableModel.order,this.mainTableController.mainTableModel.columnName);

        this.navigationPageModel.setMaxCountOfPages(this.studentList.getStudentShortCount());
        this.currentDataList = this.studentList.getKNStudentShortList(this.navigationPageModel.currentPage,this.navigationPageModel.elementsPerPage);
        this.currentDataList.setTableView(this.tableView);


        this.mainTableController.dataStudentListModel = this.currentDataList;
        this.mainTableController.dataStudentListModel.subscribe(this.buttonPanelController.buttonPanel);
        this.buttonPanelController.dataListModel = this.currentDataList;


    }

    private void checkStudentList(){
        //Описываем обработку случая отсутствия списка
        if(studentList.checkStExists()) return;
        this.studentList = this.createSourceStudentList();
        this.studentList.subscribe(this);
        if(!studentList.checkStExists()){
            SwingUtilities.invokeLater(()->{
                JOptionPane.showMessageDialog(this.tableView, "Не удалось подключиться к базе данных", "Информация", JOptionPane.INFORMATION_MESSAGE);
            });
        }
    }

    private StudentListDecorator createSourceStudentList(){
        //Создаем модель списка студентов
        StudentListDB st = new StudentListDB();
        if(st.checkAdapterExisting()){
            return new StudentListDecorator(new StudentList(st),this);
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
                return new StudentListDecorator(new StudentList(resultList),this);
            }
        }
        return new StudentListDecorator(new StudentList(new StudentListDB()),this);
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
        this.filterPanelController.clearFilters();
        this.filterStudentList();
        this.navigationPageModel.setDefaultParams();
    }

    public void filterStudentList(){
        this.navigationPageModel.setDefaultWithoutNotify();
        this.studentList.restoreOrderList();
        Function<List<Student>, List<Student>>[] filters = this.filterPanelController.getFilters();
        Arrays.stream(filters).toList().forEach(it->this.studentList.filterList(it));
    }
}
