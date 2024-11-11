package TableGridPack;

import ButtonCRUDPanel.ButtonPanel;
import DataListPack.DataTable;
import InputFilterPack.FilterPanel;
import MainPack.UpdateDataInterface;
import TableGridPack.Controllers.TableViewController;
import TableGridPack.Navigator.NavigatorPanel;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel implements TableParamsInterfaceSetter,UpdateDataInterface {
    public FilterPanel filterPanel;
    public MainTable mainTable;
    public NavigatorPanel navigatorPanel;

    public ButtonPanel buttonPanel;

    public TableViewController tableViewController;

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

        //Создаем наш главный контроллер
        this.tableViewController = new TableViewController(
                this,
                this.mainTable.mainTableController,
                this.navigatorPanel.navigatorController,
                this.buttonPanel.buttonPanelController,
                this.filterPanel.filterPanelController
        );
        this.tableViewController.refreshData();
    }

    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        this.tableViewController.setTableParams(columnNames,wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        this.tableViewController.setTableData(dataTable);
    }

    @Override
    public void updatePage() {
        this.tableViewController.updatePage();
    }
}
