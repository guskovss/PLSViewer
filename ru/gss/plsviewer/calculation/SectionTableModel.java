/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.calculation;

import java.util.Locale;
import javax.swing.table.AbstractTableModel;
import ru.gss.plsviewer.data.DataList;
import ru.gss.plsviewer.data.Section;

/**
 * Model of sections table.
 * @version 1.1.0 10.12.2020
 * @author Sergey Guskov
 */
public class SectionTableModel extends AbstractTableModel {

    /**
     * Data.
     */
    private DataList data;
    /**
     * Headers of table columns.
     */
    private String[] colNames = {"D, мм", "ε0, %", "γ0, град", "ρ, м"};
 
    /**
     * Constructor.
     * @param aData data
     */
    public SectionTableModel(final DataList aData) {
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
        return 4;
    }

    /**
     * Count of table row.
     * @return count of table row
     */
    public int getRowCount() {
        return data.getSection().size();
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
        Section o = data.getSection().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return convertToString(o.getDiameter() * 1e3, 0);
            case 1:
                return convertToString(o.getTensileDeformation() * 1e2, 3);
            case 2:
                return convertToString(o.getNeutralLineAngle(), 0);
            case 3:
                return convertToString(o.getBendingRadius(), 0);
            default:
                return null;
        }
    }
}
