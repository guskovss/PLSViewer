/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.calculation;

import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import ru.gss.plsviewer.data.DataLine;
import ru.gss.plsviewer.data.DataList;

/**
 * Model of data table.
 * @version 1.1.0 10.12.2020
 * @author Sergey Guskov
 */
public class DataTableModel extends AbstractTableModel {

    /**
     * Data.
     */
    private DataList data;
    /**
     * Headers of table columns.
     */
    private String[] colNames = {"γ, град", "ε, %", "σ, МПа"};
 
    /**
     * Constructor.
     * @param aData data
     */
    public DataTableModel(final DataList aData) {
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
        return 3;
    }

    /**
     * Count of table row.
     * @return count of table row
     */
    public int getRowCount() {
        if (data.getSectionIndex() < 0) {
            return 0;
        } else {
            return data.getSection().get(data.getSectionIndex()).getData().size();
        }
    }

    /**
     * Class of table column.
     * @param columnIndex index of table column
     * @return class of table column
     */
    @Override
    public Class < ? > getColumnClass(final int columnIndex) {
        return String.class;
    }

    /**
     * Convertation number to string.
     * @param value number
     * @param format count of symbols after separator
     * @return string representation of number
     */
    private String convertToString(final Double value, final int format) {
        if (value == null) {
            return "";
        }
        return String.format(Locale.US, "%." + format + "f", value);
    }

    /**
     * Value of table cell.
     * @param rowIndex index of table row
     * @param columnIndex index of table column
     * @return value of table cell
     */
    public Object getValueAt(final int rowIndex, final int columnIndex) {
        DataLine o = data.getSection().get(data.getSectionIndex()).getData().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return convertToString(o.getAngle(), 2);
            case 1:
                return convertToString(o.getDeformation() * 1e2, 4);
            case 2:
                return convertToString(o.getStress(), 2);
            default:
                return null;
        }
    }
}
