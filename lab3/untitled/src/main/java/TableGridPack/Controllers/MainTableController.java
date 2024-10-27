package TableGridPack.Controllers;

import TableGridPack.MainTable;
import TableGridPack.Navigator.Models.NavigationPageModel;

public class MainTableController {
    public MainTable mainTable;
    public NavigationPageModel navigationPageModel;
    public MainTableController(MainTable mainTable){
        this.mainTable = mainTable;
    }
}
