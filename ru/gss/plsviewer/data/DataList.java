/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JTextArea;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 * List of sections.
 * @version 1.1.0 08.12.2020
 * @author Sergey Guskov
 */
public class DataList {

    /**
     * Chart parameters.
     */
    private ParameterChart parameter;
    /**
     * List of sections.
     */
    private ArrayList<Section> section;
    /**
     * Index of current section.
     */
    private int sectionIndex;

    /**
     * Constructor.
     */
    public DataList() {
        parameter = new ParameterChart();
        section = new ArrayList<Section>();
        sectionIndex = -1;
    }

    /**
     * Save text area to file.
     * @param file file
     * @param jta text area
     * @throws java.io.IOException exception
     */
    public void saveTextAreaToFile(final File file, final JTextArea jta) throws IOException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileOutputStream(file), true);
            out.print(jta.getText());
        } finally {
            out.close();
        }
    }

    /**
     * Count of sections.
     * @return count of sections
     */
    public int getSectionCount() {
        return section.size();
    }

    /**
     * Maximum count of sections.
     * @return maximum count of sections
     */
    public int getSectionCountMax() {
        return 9;
    }

    /**
     * Check section existion.
     * @return true, if list of section is empty
     */
    public boolean isEmpty() {
        return section.isEmpty();
    }

    /**
     * Create dataset for chart of deformation.
     * @return dataset
     */
    public XYSeriesCollection createDatasetDeformation() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        int k = 1;
        for (int i = 0; i < section.size(); i++) {
            XYSeries series = new XYSeries(k);
            for (int j = 0; j < section.get(i).getData().size(); j++) {
                Number a = section.get(i).getData().get(j).getDeformation() * 1e2;
                series.add(section.get(i).getData().get(j).getAngle(), a);
            }
            dataset.addSeries(series);
            k++;
        }
        return dataset;
    }

    /**
     * Create dataset for chart of stress.
     * @return dataset
     */
    public XYSeriesCollection createDatasetStress() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        int k = 1;
        for (int i = 0; i < section.size(); i++) {
            XYSeries series = new XYSeries(k);
            for (int j = 0; j < section.get(i).getData().size(); j++) {
                series.add(section.get(i).getData().get(j).getAngle(), section.get(i).getData().get(j).getStress());
            }
            dataset.addSeries(series);
            k++;
        }
        return dataset;
    }

    /**
     * Chart parameters.
     * @return chart parameters
     */
    public ParameterChart getParameter() {
        return parameter;
    }

    /**
     * List of sections.
     * @return list of sections
     */
    public ArrayList<Section> getSection() {
        return section;
    }

    /**
     * Current section.
     * @return current section
     */
    public Section getCurrentSection() {
        return section.get(sectionIndex);
    }

    /**
     * Index of current section.
     * @return index of current section
     */
    public int getSectionIndex() {
        return sectionIndex;
    }

    /**
     * Index of current section.
     * @param aSectionIndex index of current section
     */
    public void setSectionIndex(final int aSectionIndex) {
        sectionIndex = aSectionIndex;
    }
}
