package MainPack.Controllers;

import MainPack.TabbedPanel;
import TableGridPack.Controllers.TableViewController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPanelController {
    public TabbedPanel tabbedPanel;
    public TableViewController tableViewController;

    public TabbedPanelController(TabbedPanel tabbedPanel,TableViewController tableViewController){
        this.tabbedPanel = tabbedPanel;
        this.tableViewController = tableViewController;
        this.tabbedPanel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
               if(((JTabbedPane)e.getSource()).getSelectedIndex()==0) TabbedPanelController.this.updateFirstTab();
            }
        });
    }
    public void updateFirstTab(){
        TabbedPanelController.this.tableViewController.setDefaultParams();
    }
}
