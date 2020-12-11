/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Section.
 * @version 1.1.0 08.12.2020
 * @author Sergey Guskov
 */
public class Section {

    /**
     * Name.
     */
    private String name;
    /**
     * Name of file.
     */
    private String fileName;
    /**
     * List of repers.
     */
    private ArrayList<Reper> reper;
    /**
     * Result of calculation.
     */
    private ArrayList<DataLine> data;
    /**
     * Count of parse exeptions.
     */
    private int parseExceptionCount;
    /**
     * Show repers on charts.
     */
    private boolean showReper;
    /**
     * Diameter, m.
     */
    private double diameter;
    /**
     * Bending radius, m.
     */
    private double bendingRadius;
    /**
     * Angular coordinate of the neutral line, deg.
     */
    private double neutralLineAngle;
    /**
     * Tensile (compression) deformation.
     */
    private double tensileDeformation;
    /**
     * Elastic modulus, MPa.
     */
    private double elasticModulus;

    /**
     * Constructor.
     */
    public Section() {
        name = "";
        fileName = "";
        reper = new ArrayList<Reper>();
        parseExceptionCount = 0;
        showReper = true;
        data = new ArrayList<DataLine>();
        diameter = 1.42;
        bendingRadius = 1000;
        neutralLineAngle = 90;
        tensileDeformation = 0.0002;
        elasticModulus = 200e3;
    }

    /**
     * Deformation calculation.
     * @param a угловая координата
     * @return деформация
     */
    public double calculateDeformation(final double a) {
        double aRad = a * Math.PI / 180;
        double a0Rad = neutralLineAngle * Math.PI / 180;
        return tensileDeformation + diameter * Math.sin(a0Rad - aRad) / bendingRadius / 2;
    }

    /**
     * Parse double value.
     * @param s string representation of double value
     * @return double value or null
     */
    private Double parseDouble(final String s) {
        if (s.trim().isEmpty()) {
            return null;
        }
        if (s.equals("-")) {
            return null;
        }
        try {
            String ss = s.replaceAll(",", ".");
            return Double.valueOf(ss);
        } catch (NumberFormatException ex) {
            parseExceptionCount++;
            return null;
        }
    }

    /**
     * Convert double value to string.
     * @param value double value
     * @param precision count of symbols after separator
     * @return string representation of value
     */
    private String convertToString(final Double value, final int precision) {
        if (value == null) {
            return " ";
        }
        return String.format(Locale.US, "%." + precision + "f", value);
    }

    /**
     * Load data from file.
     * @param file file
     * @throws java.io.IOException exception
     */
    public void loadDataFromFile(final File file) throws IOException {
        BufferedReader reader = null;
        try {
            //Read all lines from file
            reader = new BufferedReader(new FileReader(file));
            ArrayList<String> strings = new ArrayList<String>();
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
            data.clear();
            reper.clear();
            parseExceptionCount = 0;
            fileName = file.getAbsolutePath();
            //Parse data
            if (strings.size() > 10) {
                setName(strings.get(0).trim());
                setDiameter(parseDouble(strings.get(1).trim()) * 1e-3);
                setNeutralLineAngle(parseDouble(strings.get(2).trim()));
                setTensileDeformation(parseDouble(strings.get(3).trim()) * 1e-2);
                setBendingRadius(parseDouble(strings.get(4).trim()));
            }
        } finally {
            reader.close();
        }
    }

    /**
     * Save data to file.
     * @param file file
     * @throws java.io.IOException exception
     */
    public void saveDataToFile(final File file) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            writer.write(name);
            writer.newLine();
            writer.write(convertToString(diameter * 1e3, 0));
            writer.newLine();
            writer.write(convertToString(neutralLineAngle, 2));
            writer.newLine();
            writer.write(convertToString(tensileDeformation * 1e2, 4));
            writer.newLine();
            writer.write(convertToString(bendingRadius, 2));
            writer.newLine();
            fileName = file.getAbsolutePath();
        } finally {
            writer.close();
        }
    }

    /**
     * Deformation and stress calculation.
     */
    public void calculate() {
        double dk = 1;
        data.clear();
        for (int i = -180; i < 181; i++) {
            double k = i * dk;
            DataLine dl = new DataLine();
            dl.setAngle(k);
            double d = calculateDeformation(k);
            dl.setDeformation(d);
            dl.setStress(d * elasticModulus);
            data.add(dl);
        }
    }

    /**
     * Name.
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Name.
     * @param aName name
     */
    public void setName(final String aName) {
        name = aName;
    }

    /**
     * Name of file.
     * @return name of file
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Name of file.
     * @param aFileName name of file
     */
    public void setFileName(final String aFileName) {
        fileName = aFileName;
    }

    /**
     * Show repers on charts.
     * @return show repers on charts
     */
    public boolean isShowReper() {
        return showReper;
    }

    /**
     * Show repers on charts.
     * @param aShowReper show repers on charts
     */
    public void setShowReper(final boolean aShowReper) {
        showReper = aShowReper;
    }

    /**
     * Count of parse exeptions.
     * @return count of parse exeptions
     */
    public int getParseExceptionCount() {
        return parseExceptionCount;
    }

    /**
     * List of repers.
     * @return list of repers
     */
    public ArrayList<Reper> getReper() {
        return reper;
    }

    /**
     * Result of calculation.
     * @return result of calculation
     */
    public ArrayList<DataLine> getData() {
        return data;
    }

   /**
    * Diameter.
    * @return diameter
    */
    public double getDiameter() {
        return diameter;
    }

    /**
     * Diameter.
     * @param aDiameter diameter
     */
    public void setDiameter(final double aDiameter) {
        diameter = aDiameter;
    }

    /**
     * Bending radius.
     * @return bending radius
     */
    public double getBendingRadius() {
        return bendingRadius;
    }

    /**
     * Bending radius.
     * @param aBendingRadius bending radius
     */
    public void setBendingRadius(final double aBendingRadius) {
        bendingRadius = aBendingRadius;
    }

    /**
     * Angular coordinate of the neutral line.
     * @return angular coordinate of the neutral line
     */
    public double getNeutralLineAngle() {
        return neutralLineAngle;
    }

    /**
     * Angular coordinate of the neutral line.
     * @param aNeutralLineAngle angular coordinate of the neutral line
     */
    public void setNeutralLineAngle(final double aNeutralLineAngle) {
        neutralLineAngle = aNeutralLineAngle;
    }

    /**
     * Tensile (compression) deformation.
     * @return tensile (compression) deformation
     */
    public double getTensileDeformation() {
        return tensileDeformation;
    }

    /**
     * Tensile (compression) deformation.
     * @param aTensileDeformation tensile (compression) deformation
     */
    public void setTensileDeformation(final double aTensileDeformation) {
        tensileDeformation = aTensileDeformation;
    }
}
