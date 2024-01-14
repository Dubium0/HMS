import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class StatisticPage extends JPanel {
    private JFrame parentFrame;
    private int userId;
    public StatisticPage(JFrame frame,int userId) {
        this.parentFrame = frame;
        this.userId = userId;
        this.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        add(panel);
        panel.setLayout(new BorderLayout());

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout((new GridLayout(2, 3, 10, 10)));
        panel.add(tablePanel,BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setFont(new Font("Tahoma", Font.PLAIN, 30));
        panel.add(buttonPanel, BorderLayout.SOUTH);

        //button
        JButton refreshButton = new JButton("Refresh");

        refreshButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        refreshButton.setPreferredSize(new Dimension(300, 100));
        buttonPanel.add(refreshButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 30));
        cancelButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changePanel(parentFrame,new AdminMainPage(parentFrame,userId));
            }
        });
        cancelButton.setPreferredSize(new Dimension(300, 100));
        buttonPanel.add(cancelButton);








        //tables

        DefaultTableModel model1 =addTable(tablePanel,"Number of Patient According to Department",new String[]{"Number of Patient","Department Name"});
        DefaultTableModel model2 =addTable(tablePanel,"Number of Patient According to Date",new String[]{"Date","Number of Patient"});
        DefaultTableModel model3 =addTable(tablePanel,"Table 3: Title 3",new String[]{"asdsa","ddsd"});
        DefaultTableModel model4 =addTable(tablePanel,"Table 4: Title 4",new String[]{"asdsa","ddsd"});
        DefaultTableModel model5 =addTable(tablePanel,"Table 5: Title 5",new String[]{"asdsa","ddsd"});
        DefaultTableModel model6 =addTable(tablePanel,"Table 6: Title 6",new String[]{"asdsa","ddsd"});
        refreshTables1(model1);
        refreshTables2(model2);


        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                refreshTables1(model1);
                refreshTables2(model2);
                //add other
            }
        });

    }

    private DefaultTableModel addTable(JPanel panel,String title, String[] columns) {
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

        for (String colName: columns){
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
        pane2.setBorder(BorderFactory.createTitledBorder(title));

        panel.add(pane2);
        return model2;
    }


    private void refreshTables1(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<ArrayList<String>> mainList = AdminController.getPatientNumberbyDepartment();
        for (ArrayList<String> tempList : mainList) {
            Object[] rowData = {tempList.get(0),tempList.get(1)};
            model.addRow(rowData);



        }
    }

    private void refreshTables2(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<ArrayList<String>> mainList = AdminController.getPatientByTime();
        for (ArrayList<String> tempList : mainList) {
            Object[] rowData = {tempList.get(0),tempList.get(1)};
            model.addRow(rowData);



        }
    }

    public void  changePanel(JFrame frame,JPanel newPanel ) {

        frame.getContentPane().remove((JPanel) frame.getContentPane().getComponent(1));
        frame.getContentPane().add(newPanel, BorderLayout.CENTER);
        frame.getContentPane().repaint();
        frame.getContentPane().revalidate();
    }

}
