package InputFilterPack.Controllers;

import InputFilterPack.FilterPanel;

public class FilterPanelController {
    public FilterPanel filterPanel;
    public SurnameInitialsFieldController surnameInitialsFieldController;
    public ContactFieldController emailController;
    public ContactFieldController telegramController;
    public ContactFieldController gitHubController;
    public ContactFieldController phoneController;

    public FilterPanelController(
            FilterPanel filterPanel,
            SurnameInitialsFieldController surnameInitialsFieldController,
            ContactFieldController emailController,
            ContactFieldController telegramController,
            ContactFieldController gitHubController,
            ContactFieldController phoneController
    )
    {
        this.filterPanel = filterPanel;
        this.surnameInitialsFieldController = surnameInitialsFieldController;
        this.emailController = emailController;
        this.telegramController = telegramController;
        this.gitHubController = gitHubController;
        this.phoneController = phoneController;
    }
    public void clearFilters(){
        this.surnameInitialsFieldController.clearInput();
        this.emailController.clearInput();
        this.telegramController.clearInput();
        this.gitHubController.clearInput();
        this.phoneController.clearInput();
    }
}
