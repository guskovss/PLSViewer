/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.IntervalMarker;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PolarPlot;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.DefaultPolarItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYStepRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.LengthAdjustmentType;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.TextAnchor;
import ru.gss.plsviewer.data.DataList;
import ru.gss.plsviewer.data.Reper;

/**
 * Chart.
 * @version 1.1.0 10.12.2020
 * @author Sergey Guskov
 */
public class ChartMaker {

    /**
     * Parent frame.
     */
    private Component parent;
    /**
     * Data.
     */
    private DataList data;
    /**
     * Dialog of chart of deformation.
     */
    private DlgChart dlgChartDeformation;
    /**
     * Dialog of chart of stress.
     */
    private DlgChart dlgChartStress;

    /**
     * Constructor.
     * @param aParent parent frame
     * @param aData data
     */
    public ChartMaker(final Component aParent, final DataList aData) {
        parent = aParent;
        data = aData;
    }

    /**
     * Create chart.
     * @param dataset data
     * @param labelX name of axis x
     * @param labelY name of axis y
     * @param isStepPlot step chart
     * @return chart
     */
    public static JFreeChart createChart(final XYDataset dataset, final String labelX, final String labelY, final boolean isStepPlot) {
        XYPlot plot = createPlot(dataset, labelX, labelY, isStepPlot);
        JFreeChart chart = new JFreeChart("", plot);
        //Settings
        chart.setBackgroundPaint(Color.white);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setBorder(0, 0, 0, 0);
        return chart;  
    }

    /**
     * Create plot.
     * @param dataset data
     * @param labelX name of axis x
     * @param labelY name of axis y
     * @param isStepPlot step chart
     * @return plot
     */
    private static XYPlot createPlot(final XYDataset dataset, 
            final String labelX, final String labelY, final boolean isStepPlot) {
        NumberAxis xAxis = new NumberAxis(labelX);
        xAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));
        xAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 12));
        xAxis.setAutoRangeIncludesZero(false);
        xAxis.setLowerMargin(0);
        xAxis.setUpperMargin(0);
        NumberAxis yAxis = new NumberAxis(labelY);
        yAxis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));
        yAxis.setLabelFont(new Font("Tahoma", Font.BOLD, 12));
        //Parameters of series
        XYItemRenderer renderer;
        if (isStepPlot) {
            renderer = new XYStepRenderer();
        } else {
            renderer = new XYLineAndShapeRenderer();
            for (int i = 0; i < 10; i++) {
                ((XYLineAndShapeRenderer) renderer).setSeriesShapesVisible(i, false);
            }
        }
        //Colors
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesPaint(1, Color.RED);
        renderer.setSeriesPaint(2, new Color(0, 170, 0));
        renderer.setSeriesPaint(3, Color.BLACK);
        renderer.setSeriesPaint(4, Color.DARK_GRAY);
        renderer.setSeriesPaint(5, Color.GRAY);
        renderer.setSeriesPaint(6, Color.LIGHT_GRAY);
        renderer.setSeriesPaint(7, Color.MAGENTA);
        renderer.setSeriesPaint(8, Color.ORANGE);
        //Tooltips
        for (int i = 0; i < 10; i++) {
            renderer.setSeriesToolTipGenerator(i, new StandardXYToolTipGenerator("{1}; {2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance()));
        }
        XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.darkGray);
        plot.setRangeGridlinePaint(Color.darkGray);
        plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));
        return plot;
    }

    /**
     * Create chart.
     * @param data data
     * @return chart
     */
    public static JFreeChart createChart(final DataList data) {
        JFreeChart chart = null;
        if (data.getParameter().isShowChart1() || data.getParameter().isShowChart2()) {
            NumberAxis axis = new NumberAxis();
            axis.setTickLabelFont(new Font("Tahoma", Font.PLAIN, 12));
            DefaultPolarItemRenderer renderer = new DefaultPolarItemRenderer();
            //Colors
            renderer.setSeriesPaint(0, Color.BLUE);
            renderer.setSeriesPaint(1, Color.RED);
            renderer.setSeriesPaint(2, new Color(0, 170, 0));
            renderer.setSeriesPaint(3, Color.BLACK);
            renderer.setSeriesPaint(4, Color.DARK_GRAY);
            renderer.setSeriesPaint(5, Color.GRAY);
            renderer.setSeriesPaint(6, Color.LIGHT_GRAY);
            renderer.setSeriesPaint(7, Color.MAGENTA);
            renderer.setSeriesPaint(8, Color.ORANGE);
            renderer.setShapesVisible(false);
            //Tooltips
            for (int i = 0; i < 10; i++) {
                renderer.setSeriesToolTipGenerator(i, new StandardXYToolTipGenerator("{1}; {2}", NumberFormat.getNumberInstance(), NumberFormat.getNumberInstance()));
            }
            XYDataset dataset = null;
            if (data.getParameter().isShowChart1()) {
                dataset = data.createDatasetStress();
                //axis.setNumberFormatOverride(new DecimalFormat("0.0"));
            } else {
                dataset = data.createDatasetDeformation();
                axis.setNumberFormatOverride(new DecimalFormat("0.000"));
            }
            PolarPlot pplot = new PolarPlot(dataset, axis, renderer);
            pplot.setAngleLabelFont(new Font("Tahoma", Font.PLAIN, 12));
            pplot.setRadiusMinorGridlinesVisible(false);
            pplot.setAngleTickUnit(new NumberTickUnit(30));
            axis.setAxisLineVisible(false);
            pplot.setAngleGridlinePaint(Color.darkGray);
            pplot.setRadiusGridlinePaint(Color.darkGray);
            chart = new JFreeChart("", pplot);
            chart.getPlot().setOutlinePaint(Color.white);
        }
        if (data.getParameter().isShowChart3() || data.getParameter().isShowChart4()) {
            XYPlot xyplot = null;
            if (data.getParameter().isShowChart3()) {
                xyplot = createPlot(data.createDatasetStress(), "γ, град", "σ, МПа", false);
                //((NumberAxis) xyplot.getRangeAxis()).setNumberFormatOverride(new DecimalFormat("0.0"));
            } else {
                xyplot = createPlot(data.createDatasetDeformation(), "γ, град", "ε, %", false);
                ((NumberAxis) xyplot.getRangeAxis()).setNumberFormatOverride(new DecimalFormat("0.000"));
            }
            createMarker(data, xyplot);
            chart = new JFreeChart("", xyplot);
        }
        chart.setBackgroundPaint(Color.white);
        chart.getLegend().setPosition(RectangleEdge.RIGHT);
        chart.getLegend().setBorder(0, 0, 0, 0);
        JFreeChart chartTemp = createChart(data.createDatasetStress(), null, null, true);
        LegendItemSource[] lis = chartTemp.getLegend().getSources();
        chart.getLegend().setSources(lis);
        if (data.getParameter().isShowLegend()) {
            chart.getLegend().setVisible(true);
            chart.setPadding(new RectangleInsets(0, 0, 0, 5));
        } else {
            chart.getLegend().setVisible(false);
            chart.setPadding(new RectangleInsets(0, 0, 0, 38));
        }
        return chart;
    }

    /**
     * Create markers.
     * @param data data
     * @param plot plot
     */
    public static void createMarker(final DataList data, final XYPlot plot) {
        for (int k = 0; k < data.getSection().size(); k++) {
            //Repers
            if (data.getSection().get(k).isShowReper()) {
                for (int i = 0; i < data.getSection().get(k).getReper().size(); i++) {
                    Reper rl = data.getSection().get(k).getReper().get(i);
                    //Markers
                    if (rl.getType() == 0) {
                        Marker m = new ValueMarker(rl.getCoord1());
                        m.setStroke(new BasicStroke(1.5F));
                        m.setPaint(Color.ORANGE);
                        //Labels
                        String label = rl.getName();
                        if (label.startsWith("<L>")) {
                            m.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                            m.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                            label = label.substring(3, label.length());
                        } else {
                            m.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                            m.setLabelTextAnchor(TextAnchor.TOP_LEFT);
                        }
                        m.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                        m.setLabel(label);
                        m.setLabelFont(new Font("Tahoma", Font.PLAIN, 11));
                        m.setLabelPaint(Color.BLACK);
                        plot.addDomainMarker(m);
                    } else {
                        //Interval
                        Marker m = new IntervalMarker(rl.getCoord1(), rl.getCoord2());
                        m.setAlpha(0.3F);
                        m.setPaint(Color.ORANGE);
                        plot.addDomainMarker(m);
                        //Labels
                        String label = rl.getName();
                        boolean leftLabel = false;
                        if (label.startsWith("<L>")) {
                            label = label.substring(3, label.length());
                            leftLabel = true;
                        }
                        //Left border
                        m = new ValueMarker(rl.getCoord1());
                        m.setStroke(new BasicStroke(1.0F));
                        m.setPaint(Color.ORANGE);
                        if (leftLabel) {
                            m.setLabelAnchor(RectangleAnchor.TOP_LEFT);
                            m.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
                            m.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                            m.setLabel(label);
                            m.setLabelFont(new Font("Tahoma", Font.PLAIN, 11));
                            m.setLabelPaint(Color.BLACK);
                        }
                        plot.addDomainMarker(m);
                        //Right border
                        m = new ValueMarker(rl.getCoord2());
                        m.setStroke(new BasicStroke(1.0F));
                        m.setPaint(Color.ORANGE);
                        if (!leftLabel) {
                            m.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                            m.setLabelTextAnchor(TextAnchor.TOP_LEFT);
                            m.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                            m.setLabel(label);
                            m.setLabelFont(new Font("Tahoma", Font.PLAIN, 11));
                            m.setLabelPaint(Color.BLACK);
                        }
                        plot.addDomainMarker(m);
                    }
                }
                Marker m = new ValueMarker(data.getSection().get(k).getNeutralLineAngle());
                m.setStroke(new BasicStroke(1.5F, 1, 1, 1.0F, new float[]{8F, 8F}, 0.0F));
                m.setPaint(new Color(0, 150, 0));
                m.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
                m.setLabelTextAnchor(TextAnchor.BOTTOM_LEFT);
                m.setLabelOffsetType(LengthAdjustmentType.EXPAND);
                m.setLabel("γ0");
                m.setLabelFont(new Font("Tahoma", Font.PLAIN, 11));
                m.setLabelPaint(new Color(0, 150, 0));
                plot.addDomainMarker(m);
            }
        }
    }

    /**
     * Create or refresh chart dialog.
     * @param currentDialog current chart dialog
     * @param dataset data
     * @param dTitle title of chart dialog
     * @param xLabel name of axis x
     * @param yLabel name of axis y
     * @param isStepPlot step chart
     * @param isShowLegend show legend
     * @return new chart dialog
     */
    private DlgChart createOrRefresh(final DlgChart currentDialog,
            final XYSeriesCollection dataset, final String dTitle,
            final String xLabel, final String yLabel, final boolean isStepPlot, final boolean isShowLegend) {
        DlgChart newDialog;
        if (currentDialog == null) {
            newDialog = new DlgChart(dataset, dTitle, xLabel, yLabel, isStepPlot);
            newDialog.setLocationRelativeTo(parent);
        } else {
            newDialog = currentDialog;
            newDialog.refresh(dataset, isStepPlot);
        }
        if (isShowLegend) {
            newDialog.getChart().getLegend().setVisible(true);
            newDialog.getChart().setPadding(new RectangleInsets(0, 0, 0, 5));
        } else {
            newDialog.getChart().getLegend().setVisible(false);
            newDialog.getChart().setPadding(new RectangleInsets(0, 0, 0, 38));
        }
        return newDialog;
    }

    /**
     * Create or refresh dialog of chart of deformation.
     */
    public void showChartDeformation() {
        dlgChartDeformation = createOrRefresh(dlgChartDeformation, data.createDatasetDeformation(),
                "Зависимость деформации от угловой координаты", "γ, град", "ε, %", false, data.getParameter().isShowLegend());
        createMarker(data, dlgChartDeformation.getChart().getXYPlot());
        dlgChartDeformation.showChart();
    }

    /**
     * Create or refresh dialog of chart of stress.
     */
    public void showChartStress() {
        dlgChartStress = createOrRefresh(dlgChartStress, data.createDatasetStress(),
                "Зависимость напряжения от угловой координаты", "γ, град", "σ, МПа", false, data.getParameter().isShowLegend());
        createMarker(data, dlgChartStress.getChart().getXYPlot());
        dlgChartStress.showChart();
    }
}
