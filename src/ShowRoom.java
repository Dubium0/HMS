import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class ShowRoom extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame parentFrame;
	/**
	 * Create the panel.
	 */
	public ShowRoom(JFrame frame) {
		this.parentFrame = frame;
		this.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		add(tabbedPane);
		
		JPanel tempPanel = new JPanel();
		tempPanel.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Add Room",  tempPanel);
		JPanel centerPanel = new JPanel();
		tempPanel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new GridLayout(0, 2, 20, 25));	

		
		JLabel usernameText = new JLabel("Room Type:");
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(usernameText);
		
		JTextField usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(usernameField);
		usernameField.setColumns(5);

		
		JLabel nameText = new JLabel("Capacity:");
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(nameText);
		
		JTextField nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 30));
		centerPanel.add(nameField);
		nameField.setColumns(5);
		
		
		
		// to create space
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		centerPanel.add(new JLabel(""));
		

	
		
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		JButton addPersonnelButton = new JButton("Add Room");
		addPersonnelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String roomType = usernameField.getText();
				int capacity =  Integer.parseInt(nameField.getText());
				if (EntityController.addRoom(new Room(roomType,capacity))!= -1){
					JOptionPane.showMessageDialog(new JFrame(), "New Room is created");
					changePanel(parentFrame,new ShowRoom(parentFrame));
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "Requirements is not met!!!");

				}

			}
		});
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
		

		
        
        
        
        
        
        
		//edit-delete PANE
		JPanel tempPanel2 = new JPanel();
		tempPanel2.setLayout(new BorderLayout(0, 0));
		tabbedPane.addTab("Show Rooms",  tempPanel2);
		
		

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
        String[] col2 = {"RoomId", "Room Type","Capacity"};
        
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
        
        JPanel buttonPanel2 = new JPanel();
        buttonPanel2.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tempPanel2.add(buttonPanel2, BorderLayout.SOUTH);
		
		JButton editRoomButton = new JButton("Edit");
		editRoomButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		editRoomButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(editRoomButton);
		
		JButton deleteRoomButton = new JButton("Delete");
		deleteRoomButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		deleteRoomButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(deleteRoomButton);

		JButton refreshButton = new JButton("Refresh");
		refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
		refreshButton.setPreferredSize(new Dimension(300, 100));
		buttonPanel2.add(refreshButton);
		
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
        
        

	}
	
	public void  changePanel(JFrame frame,JPanel newPanel ) {

		frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
		frame.getContentPane().add(newPanel, BorderLayout.CENTER);
		frame.getContentPane().repaint();
		frame.getContentPane().revalidate();
	}

}
