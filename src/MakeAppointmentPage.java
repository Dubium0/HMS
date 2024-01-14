import java.awt.*;
import java.awt.event.*;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class MakeAppointmentPage extends JPanel {
    private static final long serialVersionUID = 1L;
    private int userId;
    private JFrame parentFrame;


    public MakeAppointmentPage(JFrame frame,int userId) {
        this.parentFrame = frame;
        this.userId = userId;
        this.setLayout(new BorderLayout(0, 0));


        JPanel tempPanel4 = new JPanel();
		tempPanel4.setLayout(new BorderLayout(0, 0));
        add(tempPanel4);
        JPanel showRoomPanel = new JPanel();
        //showRoomPanel.setLayout(new BorderLayout(0, 0));
		tempPanel4.add(showRoomPanel, BorderLayout.CENTER);


        DefaultTableModel model2 = new DefaultTableModel( ) {
        /**
         *
         */
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };



    // Column names
        String[] col2 = {"UserId","Name Surname", "Age", "Gender","Expertise","Department"};

            for (String colName: col2){
            model2.addColumn(colName);
        }

        refreshExpertiseTable(model2);


        JTable table2 = new JTable(model2);
            table2.setFocusable(false);

            table2.setPreferredScrollableViewportSize(new Dimension(1000,300));
            table2.setRowSelectionAllowed(true);


            table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



            table2.getTableHeader().setReorderingAllowed(false);



            for (int i = 0; i < table2.getColumnCount(); i++) {
            table2.getColumnModel().getColumn(i).setResizable(false);
        }

        JTableHeader header2 = table2.getTableHeader();
            header2.setBackground(Color.yellow);

        JScrollPane pane2 = new JScrollPane(table2);

            showRoomPanel.add(pane2,BorderLayout.CENTER);

        JPanel panelButton2 = new JPanel();

        showRoomPanel.add(panelButton2,BorderLayout.SOUTH);
        panelButton2.setLayout(new BoxLayout(panelButton2, BoxLayout.Y_AXIS));

        JButton filterButton = new JButton("Filter");

        filterButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        filterButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        filterButton.setPreferredSize(new Dimension(300, 100));
        panelButton2.add(filterButton);


        //filter components

        JPanel gridPanel = new JPanel(new GridLayout(2,0));
        panelButton2.add(gridPanel);

        JLabel leftSliderLabel = new JLabel("Minimum day from now");
        leftSliderLabel.setHorizontalAlignment(SwingConstants.CENTER);


        JSlider leftSliderDay = new JSlider(SwingConstants.HORIZONTAL, 0, 7, 0);
        leftSliderDay.setPaintLabels(true);
        leftSliderDay.setMajorTickSpacing(1);


        JSlider rightSliderDay = new JSlider(JSlider.HORIZONTAL, 0, 7, 7);
        rightSliderDay.setPaintLabels(true);
        rightSliderDay.setMajorTickSpacing(1);


        JLabel rightSliderLabel = new JLabel("Maximum day from now");
        rightSliderLabel.setHorizontalAlignment(SwingConstants.CENTER);


        leftSliderDay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (leftSliderDay.getValue() > rightSliderDay.getValue()) {
                    leftSliderDay.setValue(rightSliderDay.getValue());
                }
            }
        });

        rightSliderDay.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rightSliderDay.getValue() < leftSliderDay.getValue()) {
                    rightSliderDay.setValue(leftSliderDay.getValue());
                }
            }
        });


        JSlider leftSliderHour = new JSlider(SwingConstants.HORIZONTAL, 8, 17, 8);
        leftSliderHour.setPaintLabels(true);
        leftSliderHour.setMajorTickSpacing(1);


        JSlider rightSliderHour = new JSlider(JSlider.HORIZONTAL, 8, 17, 17);
        rightSliderHour.setPaintLabels(true);
        rightSliderHour.setMajorTickSpacing(1);

        leftSliderHour.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (leftSliderHour.getValue() > rightSliderHour.getValue()) {
                    leftSliderHour.setValue(rightSliderHour.getValue());
                }
            }
        });

        rightSliderHour.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (rightSliderHour.getValue() < leftSliderHour.getValue()) {
                    rightSliderHour.setValue(leftSliderHour.getValue());
                }
            }
        });


        JLabel leftSlider2Label = new JLabel("Minimum hour");
        leftSlider2Label.setHorizontalAlignment(SwingConstants.CENTER);


        JLabel rightSlider2Label = new JLabel("Maximum hour");
        rightSlider2Label.setHorizontalAlignment(SwingConstants.CENTER);



        JComboBox<String> expertiseComboBox = new JComboBox<>();
        ArrayList<String> expertiseList = new ArrayList<>();
        for (Expertise expertise : EntityController.getExpertises()){
            expertiseList.add(expertise.name);
        }
        expertiseComboBox.setModel(new DefaultComboBoxModel(expertiseList.toArray()));

        expertiseComboBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList<String> expertiseList = new ArrayList<>();
                for (Expertise expertise : EntityController.getExpertises()){
                    expertiseList.add(expertise.name);
                }
                expertiseComboBox.setModel(new DefaultComboBoxModel(expertiseList.toArray()));
            }
        });

        expertiseComboBox.setVisible(false);

        JCheckBox expertiseCheckBox = new JCheckBox("Expertise Filter");
        expertiseCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
        expertiseCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    expertiseComboBox.setVisible(true);
                } else {
                    expertiseComboBox.setVisible(false);
                }
            }
        });






        gridPanel.add(leftSliderLabel);
        gridPanel.add(rightSliderLabel);
        gridPanel.add(expertiseCheckBox);
        gridPanel.add(leftSlider2Label);
        gridPanel.add(rightSlider2Label);

        gridPanel.add(leftSliderDay);
        gridPanel.add(rightSliderDay);
        gridPanel.add(expertiseComboBox);
        gridPanel.add(leftSliderHour);
        gridPanel.add(rightSliderHour);


        filterButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String expertiseName = null;
                if (expertiseCheckBox.isSelected()){
                    expertiseName = expertiseComboBox.getSelectedItem().toString();
                }
                int minDay = leftSliderDay.getValue();
                int maxDay = rightSliderDay.getValue();
                int minHour = leftSliderHour.getValue();
                int maxHour = rightSliderHour.getValue();
                System.out.println(expertiseName);
                System.out.println(minDay);
                System.out.println(maxDay);
                System.out.println(minHour);
                System.out.println(maxHour);
                Filter(model2,expertiseName,minDay,maxDay,minHour,maxHour);
            }
        });



        // SOUTH BUTTON

        JPanel buttonPanel4 = new JPanel();
            buttonPanel4.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    	tempPanel4.add(buttonPanel4, BorderLayout.SOUTH);



        JButton selectButton = new JButton("Select");
        selectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int doctorId = selectDoctor(table2);
                System.out.println(doctorId);
                if (doctorId != -1){
                    changePanel(parentFrame,new SelectAppointmentDay(parentFrame,userId,doctorId));
                }


            }
        });
        selectButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        selectButton.setPreferredSize(new Dimension(300, 100));
        buttonPanel4.add(selectButton);

        JButton refreshButton2 = new JButton("Refresh");
	    	refreshButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshExpertiseTable(model2);
            }
        });
	    	refreshButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    	refreshButton2.setPreferredSize(new Dimension(300, 100));
	    	buttonPanel4.add(refreshButton2);

        JButton cancelButton4 = new JButton("Cancel");
	    	cancelButton4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(parentFrame,new PatientMainPage(parentFrame,userId));
            }
        });
	    	cancelButton4.setFont(new Font("Tahoma", Font.PLAIN, 30));
	    	cancelButton4.setPreferredSize(new Dimension(300, 100));
	    	buttonPanel4.add(cancelButton4);

}

        public void  changePanel(JFrame frame,JPanel newPanel ) {

            frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
            frame.getContentPane().add(newPanel, BorderLayout.CENTER);
            frame.getContentPane().repaint();
            frame.getContentPane().revalidate();
        }

        private void refreshExpertiseTable(DefaultTableModel model) {
            model.setRowCount(0);
            ArrayList<Doctor> doctorArrayList = UserController.getDoctors();   // must be changed
            for (Doctor doctor: doctorArrayList) {
                Object[] rowData = {doctor.user_id,doctor.user_name,doctor.age,doctor.gender,EntityController.getExpertiseByID(doctor.expertise_id).name,EntityController.getDepartmentByID(EntityController.getExpertiseByID(doctor.expertise_id).department_id).name};
                model.addRow(rowData);

            }
        }
        public static void Filter(DefaultTableModel model ,String expertiseName, int minDay,int maxDay,int minHour, int maxHour ){
            model.setRowCount(0);
            ArrayList<Doctor> doctorArrayList = UserController.getFilteredDoctors(expertiseName,minDay,maxDay,minHour,maxHour);    //must be changed
            for (Doctor doctor: doctorArrayList) {
                Object[] rowData = {doctor.user_id,doctor.user_name,doctor.age,doctor.gender,EntityController.getExpertiseByID(doctor.expertise_id).name,EntityController.getDepartmentByID(EntityController.getExpertiseByID(doctor.expertise_id).department_id).name};
                model.addRow(rowData);

            }
        }

        public static int selectDoctor(JTable table){
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1){
                JOptionPane.showMessageDialog(new JFrame(), "There is no selected Doctor");
                return -1;
            }
            else{
                int doctorId = (int)(table.getValueAt(selectedRow, 0));
                return doctorId;

            }
        }

}
