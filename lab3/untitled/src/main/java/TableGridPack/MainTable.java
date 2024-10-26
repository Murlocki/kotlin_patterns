package TableGridPack;

import DataListPack.DataList;
import Student.StudentShort;
import StudentList.StudentListExtend;
import StudentList.StudentListJson;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.HashMap;

public class MainTable extends JTable {
    public DefaultTableModel tableModel;
    public Object[][] dataHard = {
            {0, "Aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa B. C.", null, null},
            {1, "Aabbb-Abbb B. C." ,null, "{telegram=@elelelelele}"},
            {2, "Aaabbb B. C." , "eeefefefef",null},
            {3, "Aaabbb B. C.", null, "{email=kk@gmial.com}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
            {4, "B V. C.", null, "{phoneNumber=+79889889898}"},
    };
    public Object[] columnsNames = {"ID","initials", "gitHub", "contact"};

    private int clickCount = 0;
    private int lastSortedColumn = -1;
    public MainTable(){
        this.tableModel = new DefaultTableModel(dataHard,columnsNames){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Запрет редактирования ячеек
            }
        };
        this.setFillsViewportHeight(true);
        this.setModel(this.tableModel);

        Font font = new Font("Arial", Font.PLAIN, 16); // Установка шрифта Arial, обычный, размер 16
        this.setFont(font);
        this.setRowHeight(30);

        this.getTableHeader().setFont(font);

        // Установка рендерера для центрирования текста
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // Центрирование текста

        // Применяем рендерер ко всем столбцам
        for (int i = 0; i < this.getColumnCount(); i++) {
            this.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }


        // Добавление TableRowSorter для управления сортировкой
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(this.tableModel);
        this.setRowSorter(sorter);
        this.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = MainTable.this.columnAtPoint(e.getPoint());

                // Проверяем, был ли нажат тот же столбец
                if (MainTable.this.lastSortedColumn == column) {
                    MainTable.this.clickCount +=1;
                } else {
                    MainTable.this.clickCount = 1; // Сбрасываем счетчик
                    MainTable.this.lastSortedColumn = column; // Запоминаем новый столбец
                }

                // Сортируем или сбрасываем сортировку
                if (MainTable.this.clickCount == 3) {
                    MainTable.this.setRowSorter(null); // Сброс сортировки
                    MainTable.this.setRowSorter(new TableRowSorter<>(MainTable.this.tableModel)); // Установить сортировщик снова
                    MainTable.this.clickCount = 0; // Сбрасываем счетчик
                    MainTable.this.lastSortedColumn = -1; // Сбрасываем последний отслеживаемый столбец
                }
            }
        });

    }

}
