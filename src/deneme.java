import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class deneme extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public deneme() {
		JPanel panel = new JPanel();
		DefaultTableModel model = new DefaultTableModel( ) {
            /**
             *
             */
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };







        String col[] = {"TicketID","Event Name","Price","Date","Location","Status"};
        for (String colName: col){
            model.addColumn(colName);
        }


        
        JTable table = new JTable(model);

        table.setFocusable(false);


        table.setRowSelectionAllowed(true);

        table.setPreferredScrollableViewportSize(new Dimension(800, 300));

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);



        table.getTableHeader().setReorderingAllowed(false);


        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setResizable(false);
        }

        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.yellow);




        JScrollPane pane = new JScrollPane(table);

        panel.add(pane);

	}

}
