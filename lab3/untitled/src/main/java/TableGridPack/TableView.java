package TableGridPack;

import ButtonCRUDPanel.ButtonPanel;
import DataListPack.DataTable;
import InputFilterPack.FilterPanel;
import MainPack.UpdateDataInterface;
import TableGridPack.Controllers.TableViewController;
import TableGridPack.Navigator.NavigatorPanel;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel implements TableParamsInterfaceSetter {
    public FilterPanel filterPanel;
    public MainTable mainTable;
    public NavigatorPanel navigatorPanel;

    public ButtonPanel buttonPanel;

    public TableViewController tablePanelController;

    public TableView() {
        super(new BorderLayout());
        this.filterPanel = new FilterPanel();
        this.add(filterPanel, BorderLayout.NORTH);

        this.mainTable = new MainTable();
        JScrollPane scrollPane = new JScrollPane(this.mainTable);
        this.add(scrollPane, BorderLayout.CENTER);

        this.navigatorPanel = new NavigatorPanel(5);
        this.add(this.navigatorPanel, BorderLayout.SOUTH);

        this.buttonPanel = new ButtonPanel();
        this.add(this.buttonPanel, BorderLayout.EAST);

        //������� ��� ������� ����������
        this.tablePanelController = new TableViewController(
                this,
                this.mainTable.mainTableController,
                this.navigatorPanel.navigatorController,
                this.buttonPanel.buttonPanelController,
                this.filterPanel.filterPanelController
        );
        this.tablePanelController.refreshData();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        this.tablePanelController.setTableParams(columnNames,wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        this.tablePanelController.setTableData(dataTable);
    }

}
