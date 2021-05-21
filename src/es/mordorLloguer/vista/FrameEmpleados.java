package es.mordorLloguer.vista;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;

import com.alee.laf.WebLookAndFeel;
import com.alee.laf.table.WebTable;
import com.alee.laf.table.editors.WebDateEditor;

import es.mordor.mordorLloguer.model.AlmacenDatosDB;
import es.mordor.mordorLloguer.model.Empleado;
import es.mordor.mordorLloguer.model.MyOracleDataBase;
import es.mordor.mordorLloguer.model.MyTableModel;

import javax.swing.DefaultCellEditor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;

public class FrameEmpleados extends JFrame implements TableModelListener {

	private JPanel contentPane;
	private WebTable table;
	static FrameEmpleados frame;
	private JPopupMenu popupMenu;
	private JMenuItem mntmAddRow_1;
	private JMenuItem mntmDeleteRow_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WebLookAndFeel.install();
					frame = new FrameEmpleados();
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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		table.setVisibleRowCount(5);
		table.optimizeColumnWidths(true);
		table.setOptimizeRowHeight(true);
		table.setEditable(true);
		scrollPane.setViewportView(table);
		
		popupMenu = new JPopupMenu();
		
		mntmAddRow_1 = new JMenuItem("Add row");
		popupMenu.add(mntmAddRow_1);
		
		mntmDeleteRow_1 = new JMenuItem("Delete row");
		popupMenu.add(mntmDeleteRow_1);
		

		contentPane.setLayout(gl_contentPane);
		

		inicializar();
	}

	private void inicializar() {

		AlmacenDatosDB modelo = new MyOracleDataBase();
		ArrayList<Empleado> empleados = modelo.getEmpleados();

		MyEmpleadoTableModel mtm = new MyEmpleadoTableModel(empleados);
		table.setModel(mtm);
		
		table.setDefaultEditor(Date.class, new WebDateEditor());
		
		// Adding comboBox just to edit the company position in the WebTable
		JComboBox<String> comboBox = new JComboBox<String>();
	    comboBox.addItem("mecanico");
	    comboBox.addItem("administrativo");
	    comboBox.addItem("comercial");
	    comboBox.addItem("gerente");
		
		TableColumn column = table.getColumn("Cargo");
		column.setCellEditor(new DefaultCellEditor(comboBox));
		
				
		mtm.addTableModelListener(this);

		mtm.addEmployee(empleados.get(empleados.size() - 1));
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON3) {
					
					int row = table.rowAtPoint(e.getPoint());
					
					if (row < 0 || row >= table.getRowCount())
						table.clearSelection();
					else if (table.getSelectedRowCount() <= 1) {
						table.setSelectedRow(row);
						popupMenu.show(table, e.getX(), e.getY());
					} else if( table.getSelectedRowCount()>1) {
						popupMenu.show(table, e.getX(), e.getY());
					}
					
					
				}
			}
		});
		

	}

	private class MyEmpleadoTableModel extends MyTableModel<Empleado>{

		public MyEmpleadoTableModel(List<Empleado> data) {
			super(Arrays.asList("DNI","Nombre","Fecha","Cargo"), data);
			
		}
		
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			switch (columnIndex) {
		
			case 1:
				data.get(rowIndex).setNombre(aValue.toString());
				break;
		
			case 2:
				java.util.Date fecha = (java.util.Date) aValue;
				data.get(rowIndex).setFechaNac(new java.sql.Date(fecha.getTime()));
				break;
			case 3:
				data.get(rowIndex).setCargo(aValue.toString());
				break;
			}
		
			fireTableCellUpdated(rowIndex, columnIndex);
		}
		@Override
		public Object getValueAt(int row, int col) {
			switch (col) {
			case 0:
				return data.get(row).getDNI();
		
			case 1:
				return data.get(row).getNombre();
		
			case 2:
				return data.get(row).getFechaNac();
		
			case 3:
				return data.get(row).getCargo();
		
			}
			return null;
		}
		public void removeEmployee(String dni) {
			boolean encontrado = false;

			Iterator<Empleado> it = data.iterator();

			Empleado e;
			int pos = 0;

			while (!encontrado && it.hasNext()) {
				e = it.next();
				if (e.getDNI().compareTo(dni) == 0) {
					encontrado = true;
					it.remove();
				} else {
					pos++;
				}

			}
			if (encontrado)
				fireTableRowsDeleted(pos, pos);
		}
		
	}

	@Override
	public void tableChanged(TableModelEvent arg0) {

		if (arg0.getType() == TableModelEvent.UPDATE) {		
			
			MyTableModel mtm = (MyTableModel)table.getModel();
			System.out.println("Se ha actualizado el empleado: " +
							mtm.getEmployee(arg0.getFirstRow()));
		}
			

	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	
}
