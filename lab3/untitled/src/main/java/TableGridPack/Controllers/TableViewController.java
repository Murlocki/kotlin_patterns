package TableGridPack.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import ButtonCRUDPanel.Controllers.ButtonPanelController;
import TableGridPack.Navigator.Controllers.NavigatorController;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.Navigator.NavigatorPanel;
import TableGridPack.TableView;

public class TableViewController {
    public TableView tableView;
    private MainTableController mainTableController;
    private NavigatorController navigatorController;
    private ButtonPanelController buttonPanelController;

    private NavigationPageModel navigationPageModel;

    public TableViewController(TableView tableView,MainTableController mainTableController, NavigatorController navigatorController, ButtonPanelController buttonPanelController){
        this.tableView = tableView;
        this.mainTableController = mainTableController;
        this.navigatorController = navigatorController;
        this.buttonPanelController = buttonPanelController;

        this.navigationPageModel = new NavigationPageModel(5);

        this.navigatorController.navigationPageModel = this.navigationPageModel;
        this.mainTableController.navigationPageModel = this.navigationPageModel;

        this.navigationPageModel.subscribe(this.navigatorController.navigatorPanel);
        this.navigationPageModel.subscribe(this.mainTableController.mainTable);
    }
}
