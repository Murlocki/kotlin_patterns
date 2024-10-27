package TableGridPack;

import ButtonCRUDPanel.ButtonPanel;
import InputFilterPack.FilterPanel;
import TableGridPack.Controllers.TableViewController;
import TableGridPack.Navigator.NavigatorPanel;

import javax.swing.*;
import java.awt.*;

public class TableView extends JPanel {
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

        //Создаем наш главный контроллер
        this.tablePanelController = new TableViewController(
                this,
                this.mainTable.mainTableController,
                this.navigatorPanel.navigatorController,
                this.buttonPanel.buttonPanelController,
                this.filterPanel.filterPanelController
        );
    }
}
