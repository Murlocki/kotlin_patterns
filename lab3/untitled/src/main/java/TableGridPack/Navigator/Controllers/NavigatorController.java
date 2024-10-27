package TableGridPack.Navigator.Controllers;

import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.Navigator.NavigatorPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigatorController {
    public NavigatorPanel navigatorPanel;
    public NavigationPageModel navigationPageModel;
    public NavigatorController(NavigatorPanel navigatorPanel){
        this.navigatorPanel = navigatorPanel;

        this.navigatorPanel.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavigatorController.this.pageNext();
            }
        });
        this.navigatorPanel.prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NavigatorController.this.pagePrev();
            }
        });

    }
    public void pageNext(){
        this.navigationPageModel.nextPage();
    }
    public void pagePrev(){
        this.navigationPageModel.prevPage();
    }
    public void updateLabel(){
        this.navigatorPanel.pageLabel.setText(this.navigationPageModel.currentPage+" of "+this.navigationPageModel.maxCountOfPages);
    }
}
