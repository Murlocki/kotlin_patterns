package TableGridPack;

import ButtonCRUDPanel.ButtonPanel;
import InputFilterPack.FilterPanel;
import TableGridPack.Navigator.NavigatorPanel;
import javax.swing.*;
import java.awt.*;

public class TablePanel extends JPanel {
    private FilterPanel filterPanel;
    private MainTable mainTable;
    private NavigatorPanel navigatorPanel;

    private ButtonPanel buttonPanel;
    public TablePanel(){
        super(new BorderLayout());
        this.filterPanel = new FilterPanel();
        this.add(filterPanel, BorderLayout.NORTH);



        this.mainTable = new MainTable();
        JScrollPane scrollPane = new JScrollPane(this.mainTable);
        this.add(scrollPane,BorderLayout.CENTER);

        this.navigatorPanel = new NavigatorPanel(5);
        this.add(this.navigatorPanel,BorderLayout.SOUTH);

        this.buttonPanel = new ButtonPanel();
        this.add(this.buttonPanel,BorderLayout.EAST);
    }
}
