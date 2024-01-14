import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.*;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShowPersonnel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parentFrame;
	private int userId;
	/**
	 * Create the panel.
	 */
	public ShowPersonnel(JFrame frame, int userId) {
		this.parentFrame = frame;
		this.userId = userId;
		this.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		
		
		
		
		//DOCTORP PANE
		JPanel tempPanel1 = new JPanel();
		tempPanel1.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Doctor",  tempPanel1);
		
	
		JPanel doctorPanel = new JPanel();
		tempPanel1.add(doctorPanel, BorderLayout.CENTER);
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
		


        // Column names
        String[] col1 = {"UserId","Username","Password","Name Surname","Age","Gender","Department","Expertise"};
        
        for (String colName: col1){
            model1.addColumn(colName);
        }

        refreshDoctorTable(model1);
        JTable table1 = new JTable(model1);
        table1.setFocusable(false);
        table1.setPreferredScrollableViewportSize(new Dimension(1000, 300));
        table1.setRowSelectionAllowed(true);


        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        table1.getTableHeader().setReorderingAllowed(false);



        for (int i = 0; i < table1.getColumnCount(); i++) {
            table1.getColumnModel().getColumn(i).setResizable(false);
        }
        
        JTableHeader header1 = table1.getTableHeader();
        header1.setBackground(Color.yellow);

        JScrollPane pane1 = new JScrollPane(table1);

        doctorPanel.add(pane1);
        
        
        JPanel buttonPanel1 = new JPanel();
        buttonPanel1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel1.add(buttonPanel1, BorderLayout.SOUTH);
		
		JButton editRoomButton = new JButton("Edit");
		editRoomButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		editRoomButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel1.add(editRoomButton);
		
		JButton deleteDoctorButton = new JButton("Delete");
		deleteDoctorButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		deleteDoctorButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel1.add(deleteDoctorButton);

		JButton refreshButton1 = new JButton("Refresh");
		refreshButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshDoctorTable(model1);
			}
		});
		refreshButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		refreshButton1.setPreferredSize(new Dimension(300, 100));
		buttonPanel1.add(refreshButton1);
		
		JButton cancelButton1 = new JButton("Cancel");
		cancelButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame,userId));
			}
		});
		cancelButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancelButton1.setPreferredSize(new Dimension(300, 100));
		buttonPanel1.add(cancelButton1);
        
        
        
        
        
		//NURSE PANE
        JPanel tempPanel2 = new JPanel();
		tempPanel2.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Nurse",  tempPanel2);
		
	
		JPanel nursePanel = new JPanel();
		tempPanel2.add(nursePanel, BorderLayout.CENTER);
        
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
        String[] col2 = {"UserId","Username","Password","Name Surname","Age","Gender"};
        
        for (String colName: col2){
            model2.addColumn(colName);
        }
		refreshNurseTable(model2);

        
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

        nursePanel.add(pane2);
        
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel2.add(buttonPanel2, BorderLayout.SOUTH);
		
		JButton editNurseButton = new JButton("Edit");
		editNurseButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		editNurseButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(editNurseButton);
		
		JButton deleteNurseButton = new JButton("Delete");
		deleteNurseButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		deleteNurseButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(deleteNurseButton);

		JButton refreshButton2 = new JButton("Refresh");
		refreshButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshNurseTable(model2);
			}
		});
		refreshButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		refreshButton2.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(refreshButton2);
		
		JButton cancelButton2 = new JButton("Cancel");
		cancelButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame,userId));
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

	private void refreshDoctorTable(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Doctor> doctorArrayList = UserController.getDoctors();
		for (Doctor doctor : doctorArrayList) {
			Expertise expertise = EntityController.getExpertiseByID(doctor.expertise_id);
			Department department = EntityController.getDepartmentByID(expertise.department_id);
			Object[] rowData = {doctor.user_id, doctor.user_name, doctor.password, doctor.name_surname,doctor.age,doctor.gender,expertise.name,department.name};
			model.addRow(rowData);

		}
	}

	private void refreshNurseTable(DefaultTableModel model) {
		model.setRowCount(0);
		ArrayList<Nurse> nurseArrayList = UserController.getNurses();
		for (Nurse nurse : nurseArrayList) {
			Object[] rowData = {nurse.user_id, nurse.user_name, nurse.password, nurse.name_surname,nurse.age,nurse.gender};
			model.addRow(rowData);

		}
	}

}
