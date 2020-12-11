/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.calculation;

import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import ru.gss.plsviewer.data.DataList;
import ru.gss.plsviewer.data.Reper;

/**
 * Model of repers table.
 * @version 1.1.0 10.12.2020
 * @author Sergey Guskov
 */
public class ReperTableModel extends AbstractTableModel {

    /**
     * Data.
     */
    private DataList data;
    /**
     * Headers of table columns.
     */
    private String[] colNames = {"γ, град", "Текст"};

    /**
     * Constructor.
     * @param aData data
     */
    public ReperTableModel(final DataList aData) {
        data = aData;
    }

    /**
     * Header of table column.
     * @param column index of table column
     * @return header of table column
     */
    @Override
    public String getColumnName(final int column) {
        return colNames[column];
    }

    /**
     * Count of table column.
     * @return count of table column
     */
    public int getColumnCount() {
        return 2;
    }

    /**
     * Count of table row.
     * @return count of table row
     */
    public int getRowCount() {
        if (data.getSectionIndex() < 0) {
            return 0;
        } else {
            return data.getSection().get(data.getSectionIndex()).getReper().size();
        }
    }

    /**
     * Class of table column.
     * @param columnIndex index of table column
     * @return class of table column
     */
    @Override
    public Class < ? > getColumnClass(final int columnIndex) {
        switch (columnIndex) {
            default:
                return String.class;
        }
    }

    /**
     * Value of table cell.
     * @param rowIndex index of table row
     * @param columnIndex index of table column
     * @return value of table cell
     */
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        Reper o = data.getSection().get(data.getSectionIndex()).getReper().get(rowIndex);
        switch (columnIndex) {
            case 0:
                String s = String.format(Locale.US, "%.2f", o.getCoord1());
                if (o.getType() > 0) {
                    s = s + " - " + String.format(Locale.US, "%.2f", o.getCoord2());
                }
                return s;
            case 1:
                return o.getName();
            default:
                return null;
        }
    }
}
