package InputFilterPack;

import InputFilterPack.Controllers.SurnameInitialsFieldController;
import MainPack.UpdateDataInterface;

import javax.swing.*;
import java.awt.*;

public class SurnameInitialsField extends JPanel implements UpdateDataInterface {
    public InputField nameField;
    public SurnameInitialsFieldController surnameInitialsFieldController;
    public SurnameInitialsField(){
        //????????? ??????
        super();
        this.setLayout(new GridLayout(2,1));
        this.setBackground(Color.WHITE);

        //????????? ???? ?????
        this.nameField = new InputField();

        //??????? ?????????
        JLabel label = new JLabel("Surname and Initials:");
        label.setFont(new Font(label.getFont().getFontName(), Font.PLAIN, 15));

        this.add(label);
        this.add(this.nameField);

        //??????? ??????????
        this.surnameInitialsFieldController = new SurnameInitialsFieldController(this);
    }

    @Override
    public void updatePage() {
        this.surnameInitialsFieldController.setParams();
    }
}
