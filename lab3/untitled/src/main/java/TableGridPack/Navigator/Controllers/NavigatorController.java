package TableGridPack.Navigator.Controllers;

import MainPack.UpdateDataInterface;
import TableGridPack.Navigator.Models.NavigationPageModel;
import TableGridPack.Navigator.NavigatorPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class NavigatorController implements UpdateDataInterface {
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

        this.navigatorPanel.elemsForPageSelector.addItemListener(NavigatorController.this::elementPerPageSelected);

    }
    public void pageNext(){
        this.navigationPageModel.nextPage();
    }
    public void pagePrev(){
        this.navigationPageModel.prevPage();
    }
    public void elementPerPageSelected(ItemEvent e){
        this.navigationPageModel.elementPerPageSelected(e);
    }
    public void updateLabel(){
        this.navigatorPanel.pageLabel.setText(this.navigationPageModel.currentPage+" of "+this.navigationPageModel.maxCountOfPages);
    }
    public void updatePageSelector(){
        this.navigatorPanel.elemsForPageSelector.setSelectedItem(this.navigationPageModel.elementsPerPage);
    }
    @Override
    public void updatePage() {
        this.updateLabel();
        this.updatePageSelector();
    }
}
