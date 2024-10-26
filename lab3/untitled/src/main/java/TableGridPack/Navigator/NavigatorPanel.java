package TableGridPack.Navigator;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NavigatorPanel extends JPanel {
    JButton prevButton;
    JButton nextButton;
    JLabel pageLabel;
    ElemsForPageSelector elemsForPageSelector;

    int currentPage = 1;
    int maxCountOfPages;
    public NavigatorPanel(int maxCountOfPages){
        this.prevButton = new JButton("Previous");
        Font font = this.prevButton.getFont();
        this.prevButton.setFont(new Font(font.getFontName(),font.getStyle(),15));
        this.prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(NavigatorPanel.this.currentPage>1){
                    NavigatorPanel.this.currentPage -=1;
                    updateLabel();
                    // Тут идет постук к контроллеру на обновление данных
                }
            }
        });


        this.nextButton = new JButton("Next");
        this.nextButton.setFont(new Font(font.getFontName(),font.getStyle(),15));
        this.nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(NavigatorPanel.this.currentPage<NavigatorPanel.this.maxCountOfPages){
                    NavigatorPanel.this.currentPage +=1;
                    updateLabel();
                    // Тут идет постук к контроллеру на обновление данных
                }
            }
        });


        this.elemsForPageSelector = new ElemsForPageSelector();
        this.elemsForPageSelector.setFont(new Font(font.getFontName(),font.getStyle(),15));

        this.maxCountOfPages = maxCountOfPages;
        this.pageLabel = new JLabel(currentPage+" of "+maxCountOfPages,JLabel.CENTER);
        this.pageLabel.setFont(new Font(font.getFontName(),font.getStyle(),15));

        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        this.setBorder(new EmptyBorder(20,0,0,0));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200,60));
        FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER);
        panel.setLayout(layout1);
        panel.add(this.prevButton);
        panel.add(this.pageLabel);
        panel.add(this.nextButton);
        panel.add(this.elemsForPageSelector);

        this.add(panel);
    }

    private void updateLabel(){
        this.pageLabel.setText(currentPage+" of "+maxCountOfPages);
    }
}
