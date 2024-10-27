package TableGridPack.Controllers;

import ButtonCRUDPanel.ButtonPanel;
import ButtonCRUDPanel.Controllers.ButtonPanelController;
import DataBasePack.StudentListDB;
import InputFilterPack.Controllers.FilterPanelController;
import MainPack.RefreshDataInterface;
import StudentList.StudentList;
import TableGridPack.Navigator.Controllers.NavigatorController;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.Navigator.NavigatorPanel;
import TableGridPack.TableView;

public class TableViewController implements RefreshDataInterface {
    public TableView tableView;
    private MainTableController mainTableController;
    private NavigatorController navigatorController;
    private ButtonPanelController buttonPanelController;

    private FilterPanelController filterPanelController;

    private NavigationPageModel navigationPageModel;
    private StudentList studentList;

    public TableViewController(TableView tableView,MainTableController mainTableController, NavigatorController navigatorController, ButtonPanelController buttonPanelController,FilterPanelController filterPanelController){
        //Встраиваем существующие контроллеры для удобства их вызова в будущем
        this.tableView = tableView;
        this.mainTableController = mainTableController;
        this.navigatorController = navigatorController;
        this.buttonPanelController = buttonPanelController;
        this.filterPanelController = filterPanelController;

        //Фильтры очищаем в умолчанию
        this.filterPanelController.clearFilters();

        //Создаем модель для навигации по страницам
        this.navigationPageModel = new NavigationPageModel(5);

        this.navigatorController.navigationPageModel = this.navigationPageModel;
        this.mainTableController.navigationPageModel = this.navigationPageModel;

        this.navigationPageModel.subscribe(this.navigatorController.navigatorPanel);
        this.navigationPageModel.subscribe(this.mainTableController.mainTable);

        //Создаем модель списка студентов
        this.studentList = new StudentList(new StudentListDB());
    }

    @Override
    public void refreshData() {

    }
}
