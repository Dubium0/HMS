import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;

public class SelectAppointmentDay extends JPanel {
    private int userId;
    private JFrame parentFrame;
    private int doctorId;

    SelectAppointmentDay(JFrame frame,int userId, int doctorId){
        this.parentFrame = frame;
        this.userId = userId;
        this.doctorId = doctorId;
        JPanel tempPanel2 = new JPanel();
        tempPanel2.setLayout(new BorderLayout(0, 0));
        add(tempPanel2);



        JPanel showRoomPanel = new JPanel();
        tempPanel2.add(showRoomPanel, BorderLayout.CENTER);
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
        String[] col2 = {"Date"};

        for (String colName: col2){
            model2.addColumn(colName);
        }

        refreshTable(model2);


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

        showRoomPanel.add(pane2);

        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setFont(new Font("Tahoma", Font.PLAIN, 30));
        tempPanel2.add(buttonPanel2, BorderLayout.SOUTH);

        JButton addAppointmentButton = new JButton("Add Appointment");
        addAppointmentButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIndex = table2.getSelectedRow();
                Timestamp date = (Timestamp) table2.getValueAt(rowIndex,0);
                Appointment appointment = new Appointment(date,userId,doctorId);
                boolean result = PatientController.addAppointment(appointment);
                if (result){

                    JOptionPane.showMessageDialog(new JFrame(), "Appointment is created successfuly");
                    changePanel(parentFrame,new MakeAppointmentPage(parentFrame,userId));
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "this slot is not available");
                }


            }
        });
        addAppointmentButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        addAppointmentButton.setPreferredSize(new Dimension(300, 100));
        buttonPanel2.add(addAppointmentButton);


        JButton refreshButton = new JButton("Refresh");
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshTable(model2);
            }
        });
        refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        refreshButton.setPreferredSize(new Dimension(300, 100));
        buttonPanel2.add(refreshButton);

        JButton cancelButton2 = new JButton("Cancel");
        cancelButton2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(parentFrame,new MakeAppointmentPage(parentFrame,userId));
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
    private void refreshTable(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<DoctorAvailability> doctorAvailabilities = DoctorController.getDoctorAvailabilitiesForNext_7_days(doctorId);
        for (DoctorAvailability dAva : doctorAvailabilities) {
            if (dAva.availability ){
                Object[] rowData = {dAva.date};
                model.addRow(rowData);

            }

        }
    }
}
