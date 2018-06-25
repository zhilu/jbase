package corejava.swing;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * This program demonstrates cell rendering and editing in a table.
 * 
 * @version 1.03 2012-06-08
 * @author Cay Horstmann
 */
public class TableCellRenderTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				JFrame frame = new TableCellRenderFrame();
				frame.setTitle("TableCellRenderTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * This frame contains a table of planet data.
 */
@SuppressWarnings("serial")
class TableCellRenderFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 600;
	private static final int DEFAULT_HEIGHT = 400;

	public TableCellRenderFrame() {
		setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

		TableModel model = new PlanetTableModel();
		JTable table = new JTable(model);
		table.setRowSelectionAllowed(false);

		// set up renderers and editors

		table.setDefaultRenderer(Color.class, new ColorTableCellRenderer());
		table.setDefaultEditor(Color.class, new ColorTableCellEditor());

		JComboBox<Integer> moonCombo = new JComboBox<>();
		for (int i = 0; i <= 20; i++)
			moonCombo.addItem(i);

		TableColumnModel columnModel = table.getColumnModel();
		TableColumn moonColumn = columnModel.getColumn(PlanetTableModel.MOONS_COLUMN);
		moonColumn.setCellEditor(new DefaultCellEditor(moonCombo));
		moonColumn.setHeaderRenderer(table.getDefaultRenderer(ImageIcon.class));
		moonColumn.setHeaderValue(new ImageIcon(getClass().getResource("Moons.gif")));

		// show table

		table.setRowHeight(100);
		add(new JScrollPane(table), BorderLayout.CENTER);
	}
}

/**
 * This renderer renders a color value as a panel with the given color.
 */
@SuppressWarnings("serial")
class ColorTableCellRenderer extends JPanel implements TableCellRenderer {
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		setBackground((Color) value);
		if (hasFocus)
			setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		else
			setBorder(null);
		return this;
	}
}

/**
 * The planet table model specifies the values, rendering and editing properties
 * for the planet data.
 */
@SuppressWarnings("serial")
class PlanetTableModel extends AbstractTableModel {
	public static final int PLANET_COLUMN = 0;
	public static final int MOONS_COLUMN = 2;
	public static final int GASEOUS_COLUMN = 3;
	public static final int COLOR_COLUMN = 4;

	private Object[][] cells = {
			{ "Mercury", 2440.0, 0, false, Color.YELLOW, new ImageIcon(getClass().getResource("Mercury.gif")) },
			{ "Venus", 6052.0, 0, false, Color.YELLOW, new ImageIcon(getClass().getResource("Venus.gif")) },
			{ "Earth", 6378.0, 1, false, Color.BLUE, new ImageIcon(getClass().getResource("Earth.gif")) },
			{ "Mars", 3397.0, 2, false, Color.RED, new ImageIcon(getClass().getResource("Mars.gif")) },
			{ "Jupiter", 71492.0, 16, true, Color.ORANGE, new ImageIcon(getClass().getResource("Jupiter.gif")) },
			{ "Saturn", 60268.0, 18, true, Color.ORANGE, new ImageIcon(getClass().getResource("Saturn.gif")) },
			{ "Uranus", 25559.0, 17, true, Color.BLUE, new ImageIcon(getClass().getResource("Uranus.gif")) },
			{ "Neptune", 24766.0, 8, true, Color.BLUE, new ImageIcon(getClass().getResource("Neptune.gif")) },
			{ "Pluto", 1137.0, 1, false, Color.BLACK, new ImageIcon(getClass().getResource("Pluto.gif")) } };

	private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color", "Image" };

	public String getColumnName(int c) {
		return columnNames[c];
	}

	public Class<?> getColumnClass(int c) {
		return cells[0][c].getClass();
	}

	public int getColumnCount() {
		return cells[0].length;
	}

	public int getRowCount() {
		return cells.length;
	}

	public Object getValueAt(int r, int c) {
		return cells[r][c];
	}

	public void setValueAt(Object obj, int r, int c) {
		cells[r][c] = obj;
	}

	public boolean isCellEditable(int r, int c) {
		return c == PLANET_COLUMN || c == MOONS_COLUMN || c == GASEOUS_COLUMN || c == COLOR_COLUMN;
	}
}

/**
 * This editor pops up a color dialog to edit a cell value.
 */
@SuppressWarnings("serial")
class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor {
	private JColorChooser colorChooser;
	private JDialog colorDialog;
	private JPanel panel;

	public ColorTableCellEditor() {
		panel = new JPanel();
		// prepare color dialog

		colorChooser = new JColorChooser();
		colorDialog = JColorChooser.createDialog(null, "Planet Color", false, colorChooser,
				EventHandler.create(ActionListener.class, this, "stopCellEditing"),
				EventHandler.create(ActionListener.class, this, "cancelCellEditing"));
	}

	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		// this is where we get the current Color value. We store it in the
		// dialog in case the user.
		// starts editing
		colorChooser.setColor((Color) value);
		return panel;
	}

	public boolean shouldSelectCell(EventObject anEvent) {
		// start editing
		colorDialog.setVisible(true);

		// tell caller it is ok to select this cell
		return true;
	}

	public void cancelCellEditing() {
		// editing is canceled--hide dialog
		colorDialog.setVisible(false);
		super.cancelCellEditing();
	}

	public boolean stopCellEditing() {
		// editing is complete--hide dialog
		colorDialog.setVisible(false);
		super.stopCellEditing();

		// tell caller is is ok to use color value
		return true;
	}

	public Object getCellEditorValue() {
		return colorChooser.getColor();
	}
}
