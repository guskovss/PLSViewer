/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.data;

/**
 * Chart show parameters.
 * @version 1.1.0 08.12.2020
 * @author Sergey Guskov
 */
public class ParameterChart {
    
    /**
     * Show chart 1.
     */
    private boolean showChart1;
    /**
     * Show chart 2.
     */
    private boolean showChart2;
    /**
     * Show chart 3.
     */
    private boolean showChart3;
    /**
     * Show chart 4.
     */
    private boolean showChart4;
    /**
     * Show legend.
     */
    private boolean showLegend;
    
    /**
     * Constructor.
     */
    public ParameterChart() {
        showChart1 = true;
        showChart2 = false;
        showChart3 = false;
        showChart4 = false;
        showLegend = false;
    }

    /**
     * Show chart 1.
     * @return show chart 1
     */
    public boolean isShowChart1() {
        return showChart1;
    }

    /**
     * Show chart 1.
     * @param aShowChart1 show chart 1
     */
    public void setShowChart1(final boolean aShowChart1) {
        showChart1 = aShowChart1;
    }

    /**
     * Show chart 2.
     * @return show chart 2
     */
    public boolean isShowChart2() {
        return showChart2;
    }

    /**
     * Show chart 2.
     * @param aShowChart2 show chart 2
     */
    public void setShowChart2(final boolean aShowChart2) {
        showChart2 = aShowChart2;
    }

    /**
     * Show chart 3.
     * @return show chart 3
     */
    public boolean isShowChart3() {
        return showChart3;
    }

    /**
     * Show chart 3.
     * @param aShowChart3 show chart 3
     */
    public void setShowChart3(final boolean aShowChart3) {
        showChart3 = aShowChart3;
    }

    /**
     * Show chart 4.
     * @return show chart 4
     */
    public boolean isShowChart4() {
        return showChart4;
    }

    /**
     * Show chart 4.
     * @param aShowChart4 show chart 4
     */
    public void setShowChart4(final boolean aShowChart4) {
        showChart4 = aShowChart4;
    }

    /**
     * Show legend.
     * @return show legend
     */
    public boolean isShowLegend() {
        return showLegend;
    }

    /**
     * Show legend.
     * @param aShowLegend show legend
     */
    public void setShowLegend(final boolean aShowLegend) {
        showLegend = aShowLegend;
    }
}
