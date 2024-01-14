import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class PatientAppointmentPage extends JPanel {
    private static final long serialVersionUID = 1L;
    private JFrame parentFrame;
    private int userId;


    public PatientAppointmentPage(JFrame frame,int userId) {
        this.parentFrame = frame;
        this.userId = userId;
        this.setLayout(new BorderLayout(0, 0));

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        add(tabbedPane);



        //Past Appointments
        JPanel pastAppointmentsTab = new JPanel();
        pastAppointmentsTab.setLayout(new BorderLayout(0, 0));
        tabbedPane.addTab("Past Appointments",  pastAppointmentsTab);


        JPanel showPastAppointment = new JPanel();
        pastAppointmentsTab.add(showPastAppointment, BorderLayout.CENTER);
        DefaultTableModel model1 = new DefaultTableModel( ) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };



        JComboBox<String> filterComboBox = new JComboBox<>();
        ArrayList<Expertise> expertiseArrayList = EntityController.getExpertises();
        filterComboBox.addItem("Not Filtered");
        for (Expertise expertise : expertiseArrayList){
            filterComboBox.addItem(expertise.name);
        }

        filterComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshPastAppointment(filterComboBox,model1);
            }
        });



        // Column names
        String[] col1 = {"Date","DoctorId","RoomId","NurseId"};

        for (String colName: col1){
            model1.addColumn(colName);
        }

        refreshPastAppointment(filterComboBox,model1);

        JTable pastAppointmentTable = new JTable(model1);
        pastAppointmentTable.setFocusable(false);

        pastAppointmentTable.setPreferredScrollableViewportSize(new Dimension(1000,300));
        pastAppointmentTable.setRowSelectionAllowed(true);


        pastAppointmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        pastAppointmentTable.getTableHeader().setReorderingAllowed(false);



        for (int i = 0; i < pastAppointmentTable.getColumnCount(); i++) {
            pastAppointmentTable.getColumnModel().getColumn(i).setResizable(false);
        }

        JTableHeader header1 = pastAppointmentTable.getTableHeader();
        header1.setBackground(Color.yellow);

        JScrollPane pane1 = new JScrollPane(pastAppointmentTable);

        showPastAppointment.add(pane1);




        showPastAppointment.add(filterComboBox);


        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        pastAppointmentsTab.add(buttonPanel1, BorderLayout.SOUTH);

        JButton getInformationButton1 = new JButton("Get Information");
        getInformationButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getInformation(pastAppointmentTable);
            }
        });
        getInformationButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        getInformationButton1.setPreferredSize(new Dimension(300, 100));
        buttonPanel1.add(getInformationButton1);


        JButton refreshButton1 = new JButton("Refresh");
        refreshButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshPastAppointment(filterComboBox,model1);
            }
        });
        refreshButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        refreshButton1.setPreferredSize(new Dimension(300, 100));
        buttonPanel1.add(refreshButton1);

        JButton cancelButton1 = new JButton("Cancel");
        cancelButton1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(parentFrame,new PatientMainPage(parentFrame,userId));
            }
        });
        cancelButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
        cancelButton1.setPreferredSize(new Dimension(300, 100));
        buttonPanel1.add(cancelButton1);


        //Upcoming Appointments
        JPanel tempPanel2 = new JPanel();
        tempPanel2.setLayout(new BorderLayout(0, 0));
        tabbedPane.addTab("Upcoming Appointments",  tempPanel2);


        JPanel upcomingAppointmentPanel = new JPanel();
        tempPanel2.add(upcomingAppointmentPanel, BorderLayout.CENTER);
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


        JComboBox<String> filterComboBox2 = new JComboBox<>();
        ArrayList<Expertise> expertiseArrayList2 = EntityController.getExpertises();
        filterComboBox2.addItem("Not Filtered");
        for (Expertise expertise : expertiseArrayList2){
            filterComboBox2.addItem(expertise.name);
        }

        filterComboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshUpcomingAppointment(filterComboBox2,model2);
            }
        });



        // Column names
        String[] col2 = {"Date","DoctorId","RoomId","NurseId"};

        for (String colName: col2){
            model2.addColumn(colName);
        }
        refreshUpcomingAppointment(filterComboBox2,model2);

        JTable upcomingAppointmentTable = new JTable(model2);
        upcomingAppointmentTable.setFocusable(false);

        upcomingAppointmentTable.setPreferredScrollableViewportSize(new Dimension(1000,300));
        upcomingAppointmentTable.setRowSelectionAllowed(true);


        upcomingAppointmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        upcomingAppointmentTable.getTableHeader().setReorderingAllowed(false);



        for (int i = 0; i < upcomingAppointmentTable.getColumnCount(); i++) {
            upcomingAppointmentTable.getColumnModel().getColumn(i).setResizable(false);
        }

        JTableHeader header2 = upcomingAppointmentTable.getTableHeader();
        header2.setBackground(Color.yellow);

        JScrollPane pane2 = new JScrollPane(upcomingAppointmentTable);

        upcomingAppointmentPanel.add(pane2);

        upcomingAppointmentPanel.add(filterComboBox2);


        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        tempPanel2.add(buttonPanel2, BorderLayout.SOUTH);

        JButton getInformationButton2 = new JButton("Get Information");
        getInformationButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                getInformation(upcomingAppointmentTable);
            }
        });
        getInformationButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        getInformationButton2.setPreferredSize(new Dimension(300, 100));
        buttonPanel2.add(getInformationButton2);



        JButton deleteAppointmentButton = new JButton("Delete Appointment");
        deleteAppointmentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                deleteAppointment(upcomingAppointmentTable);
            }
        });
        deleteAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        deleteAppointmentButton.setPreferredSize(new Dimension(300, 100));
        buttonPanel2.add(deleteAppointmentButton);



        JButton refreshButton2 = new JButton("Refresh");
        refreshButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshUpcomingAppointment(filterComboBox2,model2);
            }
        });
        refreshButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        refreshButton2.setPreferredSize(new Dimension(300, 100));
        buttonPanel2.add(refreshButton2);

        JButton cancelButton2 = new JButton("Cancel");
        cancelButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(parentFrame,new PatientMainPage(parentFrame,userId));
            }
        });
        cancelButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        cancelButton2.setPreferredSize(new Dimension(300, 100));
        buttonPanel2.add(cancelButton2);




    }

    public void  changePanel(JFrame frame,JPanel newPanel ) {

        frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
        frame.getContentPane().add(newPanel, BorderLayout.CENTER);
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();
    }

    private void refreshPastAppointment(JComboBox comboBox,DefaultTableModel model) {
        model.setRowCount(0);
        if (comboBox.getSelectedItem().equals("Not Filtered")){
            ArrayList<Appointment> appointmentArrayList = PatientController.getPastAppointment(userId);
            for (Appointment appointment : appointmentArrayList) {
                Booking booking = DoctorController.getBookingByBookingId(appointment.booking_id);
                if (booking == null){
                    Object[] rowData = {appointment.date,appointment.doctor_id,null,null};
                    model.addRow(rowData);
                }else{
                    Object[] rowData = {appointment.date,appointment.doctor_id,booking.room_id,booking.nurse_id};
                    model.addRow(rowData);
                }

            }
        }
        else {
            ArrayList<Appointment> appointmentArrayList = PatientController.getPastAppointment(userId);
            for (Appointment appointment : appointmentArrayList) {
                Doctor doctor = UserController.getDoctor(appointment.doctor_id);
                String expertiseName = EntityController.getExpertiseByID(doctor.expertise_id).name;
                if (expertiseName.equals(comboBox.getSelectedItem())){
                    Booking booking = DoctorController.getBookingByBookingId(appointment.booking_id);
                    if (booking == null){
                        Object[] rowData = {appointment.date,appointment.doctor_id,null,null};
                        model.addRow(rowData);
                    }else{
                        Object[] rowData = {appointment.date,appointment.doctor_id,booking.room_id,booking.nurse_id};
                        model.addRow(rowData);
                    }
                }


            }
        }

    }

    private void refreshUpcomingAppointment(JComboBox comboBox,DefaultTableModel model) {
        model.setRowCount(0);
        if (comboBox.getSelectedItem().equals("Not Filtered")){
            ArrayList<Appointment> appointmentArrayList = PatientController.getUpcomingAppointment(userId);
            for (Appointment appointment : appointmentArrayList) {
                Booking booking = DoctorController.getBookingByBookingId(appointment.booking_id);
                if (booking == null){
                    Object[] rowData = {appointment.date,appointment.doctor_id,null,null};
                    model.addRow(rowData);
                }else{
                    Object[] rowData = {appointment.date,appointment.doctor_id,booking.room_id,booking.nurse_id};
                    model.addRow(rowData);
                }

            }
        }
        else {
            ArrayList<Appointment> appointmentArrayList = PatientController.getUpcomingAppointment(userId);
            for (Appointment appointment : appointmentArrayList) {
                Doctor doctor = UserController.getDoctor(appointment.doctor_id);
                String expertiseName = EntityController.getExpertiseByID(doctor.expertise_id).name;
                if (expertiseName.equals(comboBox.getSelectedItem())){
                    Booking booking = DoctorController.getBookingByBookingId(appointment.booking_id);
                    if (booking == null){
                        Object[] rowData = {appointment.date,appointment.doctor_id,null,null};
                        model.addRow(rowData);
                    }else{
                        Object[] rowData = {appointment.date,appointment.doctor_id,booking.room_id,booking.nurse_id};
                        model.addRow(rowData);
                    }
                }


            }
        }
    }
    private void getInformation(JTable table){
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(new JFrame(), "There is no selected Appointment");
        }
        else{
            Date date = (Date)(table.getValueAt(selectedRow, 0));
            int doctorId = (int)(table.getValueAt(selectedRow, 1));
            Doctor doctor = UserController.getDoctor(doctorId);
            Room room;
            Nurse nurse;
            if ((table.getValueAt(selectedRow, 2) == null)){
                room = null;
            }
            else {
                int roomId = (int)(table.getValueAt(selectedRow, 2));
                room = EntityController.getRoomByID(roomId);
            }
            if ((table.getValueAt(selectedRow, 3) == null)){
                nurse = null;
            }
            else {
                int nurseId = (int)(table.getValueAt(selectedRow, 3));
                nurse = UserController.getNurse(nurseId);
            }

            String text = "Date-> " + date + "\n\nDoctor-> " + doctor + "\nRoom-> " + room + "\nNurse-> " + nurse;
            JOptionPane.showMessageDialog(new JFrame(), text);




        }
    }

    private void deleteAppointment(JTable table){
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1){
            JOptionPane.showMessageDialog(new JFrame(), "There is no selected Appointment");
        }
        else{
            Timestamp date = (Timestamp) (table.getValueAt(selectedRow, 0));
            int doctorId = (int)(table.getValueAt(selectedRow, 1));
            Appointment appointment = PatientController.getAppointment(userId,doctorId, date);
            if (appointment != null){
                if (PatientController.deleteAppointment(appointment)){
                    JOptionPane.showMessageDialog(new JFrame(), "Appointment is successfully deleted");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Cannot be deleted!!");
                }
            }else{
                JOptionPane.showMessageDialog(new JFrame(), "This appointment not found");
            }






        }
    }

}
