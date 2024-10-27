package TableGridPack.Controllers;

import DataListPack.DataTable;
import MainPack.UpdateDataInterface;
import StudentList.StudentList;
import TableGridPack.MainTable;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.TableParamsInterfaceSetter;

import javax.swing.table.DefaultTableModel;

public class MainTableController implements UpdateDataInterface, TableParamsInterfaceSetter {
    public MainTable mainTable;
    public NavigationPageModel navigationPageModel;
    public StudentList studentListModel;
    public MainTableController(MainTable mainTable){
        this.mainTable = mainTable;
    }
    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        DefaultTableModel model = (DefaultTableModel) this.mainTable.getModel();
        model.setColumnIdentifiers(columnNames);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        DefaultTableModel model = (DefaultTableModel) this.mainTable.getModel();
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

    @Override
    public void updatePage() {

    }
}
