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

    //���������� ��� ������ ��� �������
    public StudentList studentList;
    public DataList<StudentShort> currentDataList;

    public TableViewController(TableView tableView,MainTableController mainTableController, NavigatorController navigatorController, ButtonPanelController buttonPanelController,FilterPanelController filterPanelController){
        //���������� ������������ ����������� ��� �������� �� ������ � �������
        this.tableView = tableView;
        this.mainTableController = mainTableController;
        this.navigatorController = navigatorController;
        this.buttonPanelController = buttonPanelController;
        this.filterPanelController = filterPanelController;

        //������� ������� � ���������
        this.filterPanelController.clearFilters();

        //������� ������ ������ ���������
        this.studentList = new StudentList(new StudentListDB());
        this.studentList.subscribe(this);

        //������� ������ ��� ��������� �� ���������
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

        this.studentList.sortByInitials(this.mainTableController.mainTableModel.order);
        this.currentDataList = this.studentList.getKNStudentShortList(this.navigationPageModel.currentPage,this.navigationPageModel.elementsPerPage);

        this.currentDataList.setTableView(this.tableView);

        this.mainTableController.dataStudentListModel = this.currentDataList;
        this.mainTableController.dataStudentListModel.subscribe(this.buttonPanelController.buttonPanel);
        this.buttonPanelController.dataListModel = this.currentDataList;

        this.navigationPageModel.setMaxCountOfPages(this.studentList.getStudentShortCount());

        //��������� ��������� ������ ���������� ������
        if(!studentList.checkStExists()){
            SwingUtilities.invokeLater(()->{
                    JOptionPane.showMessageDialog(this.tableView, "�� ������� ������������ � ���� ������", "����������", JOptionPane.INFORMATION_MESSAGE);
            });
        }
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
