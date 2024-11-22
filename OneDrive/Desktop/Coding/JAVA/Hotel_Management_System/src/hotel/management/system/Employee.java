package hotel.management.system;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Employee extends JFrame {
	private JPanel contentPane;
	private JTable table;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Employee frame = new Employee();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Employee() throws SQLException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(430, 200, 1000, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		table = new JTable();
		table.setBounds(0, 34, 1000, 450);
		contentPane.add(table);

		JButton btnLoadData = new JButton("Load Data");
		btnLoadData.addActionListener(e -> loadData());
		btnLoadData.setBounds(350, 500, 120, 30);
		btnLoadData.setBackground(Color.BLACK);
		btnLoadData.setForeground(Color.WHITE);
		contentPane.add(btnLoadData);

		JButton btnExit = new JButton("Back");
		btnExit.addActionListener(e -> {
			new Reception().setVisible(true);
			setVisible(false);
		});
		btnExit.setBounds(510, 500, 120, 30);
		btnExit.setBackground(Color.BLACK);
		btnExit.setForeground(Color.WHITE);
		contentPane.add(btnExit);

		// Column Labels
		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setBounds(41, 11, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblJob = new JLabel("Age");
		lblJob.setBounds(159, 11, 46, 14);
		contentPane.add(lblJob);

		JLabel lblName = new JLabel("Gender");
		lblName.setBounds(273, 11, 46, 14);
		contentPane.add(lblName);

		JLabel lblDepartment = new JLabel("Job");
		lblDepartment.setBounds(416, 11, 86, 14);
		contentPane.add(lblDepartment);

		JLabel l1 = new JLabel("Salary");
		l1.setBounds(536, 11, 86, 14);
		contentPane.add(l1);

		JLabel l2 = new JLabel("Phone");
		l2.setBounds(656, 11, 86, 14);
		contentPane.add(l2);

		JLabel l3 = new JLabel("Aadhar");
		l3.setBounds(786, 11, 86, 14);
		contentPane.add(l3);

		JLabel l4 = new JLabel("Gmail");
		l4.setBounds(896, 11, 86, 14);
		contentPane.add(l4);

		getContentPane().setBackground(Color.WHITE);
	}

	private void loadData() {
		try {
			conn c = new conn(); // Assuming `conn` is your database connection class
			String query = "SELECT * FROM employee";
			ResultSet rs = c.s.executeQuery(query);

			// Extract column names and data
			ResultSetMetaData metaData = rs.getMetaData();
			int columnCount = metaData.getColumnCount();

			// Create a table model
			DefaultTableModel model = new DefaultTableModel();

			// Add column names to the model
			for (int i = 1; i <= columnCount; i++) {
				model.addColumn(metaData.getColumnName(i));
			}

			// Add rows to the model
			while (rs.next()) {
				Object[] row = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					row[i - 1] = rs.getObject(i);
				}
				model.addRow(row);
			}

			// Set the model to the table
			table.setModel(model);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
