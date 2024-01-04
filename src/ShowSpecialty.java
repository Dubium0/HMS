import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ShowSpecialty extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parentFrame;
	/**
	 * Create the panel.
	 */
	public ShowSpecialty(JFrame frame) {
		this.parentFrame = frame;
		this.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		
		
		
		
		// add department
		
		JPanel tempPanel1 = new JPanel();
		tempPanel1.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Add Department",  tempPanel1);
		JPanel centerPanel = new JPanel();
		tempPanel1.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 2, 20, 25));	

		
		JLabel departmentText = new JLabel("Departmant:");
		departmentText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(departmentText);
		
		JTextField departmentField = new JTextField();
		departmentField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(departmentField);
		departmentField.setColumns(5);

		
		
		
		
		// to create space
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		

	
		
		
		JPanel buttonPanel1 = new JPanel();
		buttonPanel1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel1.add(buttonPanel1, BorderLayout.SOUTH);
		
		JButton addDepartmentButton = new JButton("Add Department");
		addDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		addDepartmentButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel1.add(addDepartmentButton);
		
		JButton cancelButton1 = new JButton("Cancel");
		cancelButton1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame));
			}
		});
		cancelButton1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancelButton1.setPreferredSize(new Dimension(300, 100));
		buttonPanel1.add(cancelButton1);
		

		
        
        
        
        
        
        
		//edit-delete department PANE
		JPanel tempPanel2 = new JPanel();
		tempPanel2.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Show Departments",  tempPanel2);
		
		
		JPanel showDepartmentPanel = new JPanel();
		tempPanel2.add(showDepartmentPanel, BorderLayout.CENTER);
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
        String[] col1 = {"DepartmentId", "Department"};
        
        for (String colName: col1){
            model1.addColumn(colName);
        }

        
        JTable departmentTable = new JTable(model1);
        departmentTable.setFocusable(false);

        departmentTable.setPreferredScrollableViewportSize(new Dimension(1000,300));
        departmentTable.setRowSelectionAllowed(true);


        departmentTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        departmentTable.getTableHeader().setReorderingAllowed(false);



        for (int i = 0; i < departmentTable.getColumnCount(); i++) {
            departmentTable.getColumnModel().getColumn(i).setResizable(false);
        }
        
        JTableHeader header1 = departmentTable.getTableHeader();
        header1.setBackground(Color.yellow);

        JScrollPane pane1 = new JScrollPane(departmentTable);

        showDepartmentPanel.add(pane1);
        
        
        JPanel buttonPanel2 = new JPanel();
		buttonPanel2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel2.add(buttonPanel2, BorderLayout.SOUTH);
		
		JButton editDepartmentButton = new JButton("Edit");
		editDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		editDepartmentButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(editDepartmentButton);
		
		JButton deleteDepartmentButton = new JButton("Delete");
		deleteDepartmentButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		deleteDepartmentButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(deleteDepartmentButton);
		
		JButton cancelButton2 = new JButton("Cancel");
		cancelButton2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame));
			}
		});
		cancelButton2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancelButton2.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(cancelButton2);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
       //expertise
        
        
        //add expertise
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Add Expertise",  tempPanel);
		JPanel centerPanel2 = new JPanel();
		tempPanel.add(centerPanel2, BorderLayout.CENTER);
		centerPanel2.setLayout(new GridLayout(0, 2, 20, 25));	

		
		JLabel text1 = new JLabel("Expertise:");
		text1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel2.add(text1);
		
		JTextField field1 = new JTextField();
		field1.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel2.add(field1);
		field1.setColumns(5);

		
		JLabel text2 = new JLabel("Department:");
		text2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel2.add(text2);
		
		JComboBox<String> departmentComboBox = new JComboBox();
		departmentComboBox.setFont(new Font("Tahoma", Font.PLAIN, 30));
		departmentComboBox.setModel(new DefaultComboBoxModel(new String[] {"A","B"}));
		centerPanel2.add(departmentComboBox);
		
		
		
		// to create space
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		centerPanel2.add(new JLabel(""));
		

	
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton addPersonnelButton = new JButton("Add Room");
		addPersonnelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		addPersonnelButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel.add(addPersonnelButton);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame));
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cancelButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel.add(cancelButton);
		

		
        
        
        
        
        
        
		//edit-delete expertise
		JPanel tempPanel4 = new JPanel();
		tempPanel4.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Show Expertises",  tempPanel4);
		
		JPanel showRoomPanel = new JPanel();
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
        String[] col2 = {"ExpertiseId","Expertise", "Department"};
        
        for (String colName: col2){
            model2.addColumn(colName);
        }

        
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
        
        JPanel panelButton2 = new JPanel();

        showRoomPanel.add(panelButton2);
        panelButton2.setLayout(new BoxLayout(panelButton2, BoxLayout.Y_AXIS));
        
        
        
        JPanel buttonPanel4 = new JPanel();
        buttonPanel4.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel4.add(buttonPanel4, BorderLayout.SOUTH);
		
		JButton editExpertiseButton = new JButton("Edit");
		editExpertiseButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		editExpertiseButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel4.add(editExpertiseButton);
		
		JButton deleteExpertiseButton = new JButton("Delete");
		deleteExpertiseButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		deleteExpertiseButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel4.add(deleteExpertiseButton);
		
		JButton cancelButton4 = new JButton("Cancel");
		cancelButton4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(parentFrame,new AdminMainPage(parentFrame));
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

}
