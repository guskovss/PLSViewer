/*
 * Pipeline Lengthwise Stress Viewer
 */
package ru.gss.plsviewer.calculation;

import javax.swing.text.DefaultFormatterFactory;
import org.jdesktop.application.Action;
import ru.gss.plsviewer.commons.DlgDirEdit;
import ru.gss.plsviewer.commons.NoLocaleNumberFormatter;
import ru.gss.plsviewer.data.Section;

/**
 * Dialog for edit of section.
 * @version 1.1.0 10.12.2020
 * @author Sergey Guskov
 */
public class DlgSectionEdit extends DlgDirEdit < Section > {

    /**
     * Constructor.
     */
    public DlgSectionEdit() {
        super();
        initComponents();
        jftfAngle0.setFormatterFactory(new DefaultFormatterFactory(new NoLocaleNumberFormatter(2)));
        jftfE0.setFormatterFactory(new DefaultFormatterFactory(new NoLocaleNumberFormatter(4)));
        jftfDiameter.setFormatterFactory(new DefaultFormatterFactory(new NoLocaleNumberFormatter(2)));
        jftfRo.setFormatterFactory(new DefaultFormatterFactory(new NoLocaleNumberFormatter(2)));
    }

    /**
     * Setter editing object.
     * @param aTempObj editing object
     */
    @Override
    public void setTempObj(final Section aTempObj) {
        putTempObj(aTempObj);
        jtfName.setText(getTempObj().getName());
        jtfFileName.setText(getTempObj().getFileName());
        jftfAngle0.setValue(getTempObj().getNeutralLineAngle());
        jftfE0.setValue(getTempObj().getTensileDeformation() * 1e2);
        jftfDiameter.setValue(getTempObj().getDiameter() * 1e3);
        jftfRo.setValue(getTempObj().getBendingRadius());
        getRootPane().setDefaultButton(jbtnOk);
    }

    /**
     * Init new object.
     * @return new object
     */
    @Override
    public Section createTempObj() {
        return new Section();
    }

    /**
     * Action for Cancel button.
     */
    @Action
    public void acCancel() {
        setChangeObj(false);
    }

    /**
     * Action for OK button.
     */
    @Action
    public void acOk() {
        if (checkFormattedTextFieldNullNoSupposed(jftfAngle0, -180.0, 180.0)) {
            return;
        }
        if (checkFormattedTextFieldNullNoSupposed(jftfE0, -10.0, 10.0)) {
            return;
        }
        if (checkFormattedTextFieldNullNoSupposed(jftfDiameter, 10.0, 1500.0)) {
            return;
        }
        if (checkFormattedTextFieldNullNoSupposed(jftfRo, 0.01, 1000000.0)) {
            return;
        }
        getTempObj().setName(jtfName.getText().trim());
        getTempObj().setNeutralLineAngle(getDoubleFromFormattedTextField(jftfAngle0));
        getTempObj().setTensileDeformation(getDoubleFromFormattedTextField(jftfE0) * 1e-2);
        getTempObj().setDiameter(getDoubleFromFormattedTextField(jftfDiameter) * 1e-3);
        getTempObj().setBendingRadius(getDoubleFromFormattedTextField(jftfRo));
        setChangeObj(true);
    }

    //CHECKSTYLE:OFF
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jbtnOk = new javax.swing.JButton();
        jbtnCancel = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jtfName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jtfFileName = new javax.swing.JTextField();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jlbDiameter = new javax.swing.JLabel();
        jftfDiameter = new javax.swing.JFormattedTextField();
        jPanel5 = new javax.swing.JPanel();
        jlbAngle0 = new javax.swing.JLabel();
        jftfAngle0 = new javax.swing.JFormattedTextField();
        jlbE0 = new javax.swing.JLabel();
        jftfE0 = new javax.swing.JFormattedTextField();
        jlbRo = new javax.swing.JLabel();
        jftfRo = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(ru.gss.plsviewer.PLSViewerApp.class).getContext().getResourceMap(DlgSectionEdit.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setModal(true);
        setName("Form"); // NOI18N
        setResizable(false);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(ru.gss.plsviewer.PLSViewerApp.class).getContext().getActionMap(DlgSectionEdit.class, this);
        jbtnOk.setAction(actionMap.get("acOk")); // NOI18N
        jbtnOk.setName("jbtnOk"); // NOI18N

        jbtnCancel.setAction(actionMap.get("acCancel")); // NOI18N
        jbtnCancel.setName("jbtnCancel"); // NOI18N

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel1.border.title"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jtfName.setName("jtfName"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfName, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jtfName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel2.border.title"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jtfFileName.setBackground(resourceMap.getColor("jtfFileName.background")); // NOI18N
        jtfFileName.setEditable(false);
        jtfFileName.setName("jtfFileName"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtfFileName, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jtfFileName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jlbDiameter.setText(resourceMap.getString("jlbDiameter.text")); // NOI18N
        jlbDiameter.setName("jlbDiameter"); // NOI18N

        jftfDiameter.setName("jftfDiameter"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlbDiameter)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(jftfDiameter, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbDiameter)
                    .addComponent(jftfDiameter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jlbAngle0.setText(resourceMap.getString("jlbAngle0.text")); // NOI18N
        jlbAngle0.setName("jlbAngle0"); // NOI18N

        jftfAngle0.setName("jftfAngle0"); // NOI18N

        jlbE0.setText(resourceMap.getString("jlbE0.text")); // NOI18N
        jlbE0.setName("jlbE0"); // NOI18N

        jftfE0.setName("jftfE0"); // NOI18N

        jlbRo.setText(resourceMap.getString("jlbRo.text")); // NOI18N
        jlbRo.setName("jlbRo"); // NOI18N

        jftfRo.setName("jftfRo"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlbAngle0)
                    .addComponent(jlbE0)
                    .addComponent(jlbRo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jftfRo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jftfE0, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jftfAngle0, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbAngle0)
                    .addComponent(jftfAngle0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbE0)
                    .addComponent(jftfE0, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlbRo)
                    .addComponent(jftfRo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jbtnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnOk)
                    .addComponent(jbtnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton jbtnCancel;
    private javax.swing.JButton jbtnOk;
    private javax.swing.JFormattedTextField jftfAngle0;
    private javax.swing.JFormattedTextField jftfDiameter;
    private javax.swing.JFormattedTextField jftfE0;
    private javax.swing.JFormattedTextField jftfRo;
    private javax.swing.JLabel jlbAngle0;
    private javax.swing.JLabel jlbDiameter;
    private javax.swing.JLabel jlbE0;
    private javax.swing.JLabel jlbRo;
    private javax.swing.JTextField jtfFileName;
    private javax.swing.JTextField jtfName;
    // End of variables declaration//GEN-END:variables
    //CHECKSTYLE:ON
}