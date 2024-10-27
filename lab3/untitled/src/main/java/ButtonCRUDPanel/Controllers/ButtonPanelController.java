package ButtonCRUDPanel.Controllers;

import ButtonCRUDPanel.ButtonPanel;

public class ButtonPanelController {
    private ButtonPanel buttonPanel;
    public ButtonPanelController(ButtonPanel buttonPanel){
        this.buttonPanel = buttonPanel;
    }
    public void turnOnEditButton(int count){
        if(count==1){
            this.buttonPanel.updateButton.setEnabled(true);
        }
    }
    public void turnOnDeleteButton(int count){
        if(count>=1){
            this.buttonPanel.deleteButton.setEnabled(true);
        }
    }
    public void turnOffButtons(int stringCount){
        this.buttonPanel.updateButton.setEnabled(false);
        this.buttonPanel.deleteButton.setEnabled(false);
        this.turnOnDeleteButton(stringCount);
        this.turnOnEditButton(stringCount);
    }

}
