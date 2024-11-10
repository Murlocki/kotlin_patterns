package TableGridPack.Controllers;
import DataListPack.DataList;
import DataListPack.DataTable;
import Student.StudentShort;
import TableGridPack.MainTable;
import TableGridPack.Models.MainTableModel;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.TableParamsInterfaceSetter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;


public class MainTableController implements TableParamsInterfaceSetter {
    public MainTable mainTable;
    public NavigationPageModel navigationPageModel;

    public MainTableModel mainTableModel = new MainTableModel();
    public DataList<StudentShort> dataStudentListModel;
    public MainTableController(MainTable mainTable){
        this.mainTable = mainTable;
        this.mainTable.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainTableController.this.mainTableModel.sortOrder(MainTableController.this.mainTable.columnAtPoint(e.getPoint()));
            }
        });
        this.mainTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int[] selectedRows = MainTableController.this.mainTable.getSelectedRows();
                    System.out.println("Выбранные строки"+Arrays.toString(selectedRows));
                    MainTableController.this.dataStudentListModel.unSelectAll();
                    for (int rowIndex : selectedRows) {
                        MainTableController.this.dataStudentListModel.select(rowIndex);
                    }
                    MainTableController.this.dataStudentListModel.notifySubs();
                }
            }
        });
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
}
