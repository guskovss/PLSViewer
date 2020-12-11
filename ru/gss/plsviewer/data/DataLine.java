/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.data;

/**
 * Result of calculation.
 * @version 1.1.0 08.12.2020
 * @author Sergey Guskov
 */
public class DataLine {

    /**
     * Angle, deg.
     */
    private double angle;
    /**
     * Deformation.
     */
    private double deformation;
    /**
     * Stress, MPa.
     */
    private double stress;
    
    /**
     * Constructor.
     */
    public DataLine() {
    }

    /**
     * Angle.
     * @return angle
     */
    public double getAngle() {
        return angle;
    }

    /**
     * Angle.
     * @param aAngle angle
     */
    public void setAngle(final double aAngle) {
        angle = aAngle;
    }

    /**
     * Deformation.
     * @return deformation
     */
    public double getDeformation() {
        return deformation;
    }

    /**
     * Deformation.
     * @param aDeformation deformation
     */
    public void setDeformation(final double aDeformation) {
        deformation = aDeformation;
    }

    /**
     * Stress.
     * @return stress
     */
    public double getStress() {
        return stress;
    }

    /**
     * Stress.
     * @param aStress stress
     */
    public void setStress(final double aStress) {
        stress = aStress;
    }
}
