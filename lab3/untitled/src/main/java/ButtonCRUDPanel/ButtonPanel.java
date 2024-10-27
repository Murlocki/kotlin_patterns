package ButtonCRUDPanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ButtonPanel extends JPanel {
    private JButton createButton;
    private JButton deleteButton;
    private JButton updateButton;

    private JButton reloadButton;
    private JButton clearButton;
    public ButtonPanel(){

        this.createButton = new JButton("Create");
        this.createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Font font = this.createButton.getFont();
        this.createButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        this.deleteButton = new JButton("Delete");
        this.deleteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.deleteButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        this.updateButton = new JButton("Update");
        this.updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.updateButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        this.reloadButton = new JButton("Reload");
        this.reloadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.reloadButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        this.clearButton = new JButton("Clear");
        this.clearButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.clearButton.setFont(new Font(font.getFontName(),font.getStyle(),15));

        this.setBorder(new EmptyBorder(10,20,10,20));

        GridLayout layout = new GridLayout(5,1);
        layout.setVgap(40);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(100,400));
        panel.setLayout(layout);
        panel.add(createButton);
        panel.add(deleteButton);
        panel.add(updateButton);
        panel.add(reloadButton);
        panel.add(clearButton);

        this.add(panel);
    }
    public void turnOffButtons(int stringCount){
        this.updateButton.setEnabled(false);
        this.deleteButton.setEnabled(false);

        if(stringCount!=1){
            this.updateButton.setEnabled(true);
        }
        if(stringCount>=1){
            this.deleteButton.setEnabled(true);
        }
    }
}
