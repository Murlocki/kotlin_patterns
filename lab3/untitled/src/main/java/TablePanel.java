import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class TablePanel extends JPanel {
    public TablePanel(){
        super(new BorderLayout());
        this.add(new FilterPanel(), BorderLayout.NORTH);
    }
}
