package TableGridPack;

import DataListPack.DataTable;
import MainPack.UpdateDataInterface;
import TableGridPack.Controllers.MainTableController;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;

public class MainTable extends JTable implements TableParamsInterfaceSetter {
    public DefaultTableModel tableModel;

    public MainTableController mainTableController;

    public MainTable(){
        this.tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // ������ �������������� �����
            }
        };
        this.setFillsViewportHeight(true);
        this.setModel(this.tableModel);

        Font font = new Font("Arial", Font.PLAIN, 16); // ��������� ������ Arial, �������, ������ 16
        this.setFont(font);
        this.setRowHeight(30);

        this.getTableHeader().setFont(font);

        // ��������� ��������� ��� ������������� ������
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // ������������� ������

        // ��������� �������� �� ���� ��������
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //��������� ��������� �� ���������� ������
        this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRowCount = MainTable.this.getSelectedRowCount(); // �������� ���������� ���������� �����
                    System.out.println("���������� ���������� �����: " + selectedRowCount);
                }
            }
        });
        this.mainTableController = new MainTableController(this);
    }


    @Override
    public void setTableParams(String[] columnNames, int wholeEntitiesCount) {
        this.mainTableController.setTableParams(columnNames,wholeEntitiesCount);
    }

    @Override
    public void setTableData(DataTable dataTable) {
        this.mainTableController.setTableData(dataTable);
    }


}
