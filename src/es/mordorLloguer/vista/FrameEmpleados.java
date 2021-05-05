package es.mordorLloguer.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.table.WebTable;

import es.mordor.mordorLloguer.model.AlmacenDatosDB;
import es.mordor.mordorLloguer.model.Empleado;
import es.mordor.mordorLloguer.model.MyOracleDataBase;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameEmpleados extends JFrame {

	private JPanel contentPane;
	private WebTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					FrameEmpleados frame = new FrameEmpleados();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrameEmpleados() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE).addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup().addContainerGap()
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 193, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(60, Short.MAX_VALUE)));

		table = new WebTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setVisibleRowCount(5);
		table.optimizeColumnWidths(true);
		table.setOptimizeRowHeight(true);
		table.setEditable(true);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);

		inicializar();
	}

	private void inicializar() {

		AlmacenDatosDB modelo = new MyOracleDataBase();
		ArrayList<Empleado> empleados = modelo.getEmpleados();
		
		MyTableModel mtm = new MyTableModel(empleados);
		table.setModel(mtm);
		
		mtm.addEmployee(empleados.get(empleados.size()-1));
		

	}

	private class MyTableModel extends AbstractTableModel {

		private final String[] HEADER = { "DNI", "Nombre", "Nacimiento" };

		List<Empleado> data;

		public MyTableModel(List<Empleado> data) {
			this.data = data;
		}

		@Override
		public int getColumnCount() {

			return HEADER.length;
		}

		@Override
		public int getRowCount() {
			return data.size();
		}
		
		@Override
		public String getColumnName(int column) {
			return HEADER[column];
		}

		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0:
				return data.get(row).getDNI();
				
			case 1:
				return  data.get(row).getNombre();
				
			case 2:
				return data.get(row).getFechaNac();
				
			}
			return null;
		}
		
		public void addEmployee(Empleado empleado) {
			data.add(empleado);
			fireTableRowsInserted(data.size()-1, data.size()-1);

		}

	}

}
