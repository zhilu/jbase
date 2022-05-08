package corejava.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.AbstractSpinnerModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;

/**
 * A program to test spinners.
 * 
 * @version 1.02 2007-08-01
 * @author Cay Horstmann
 */
public class SpinnerTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new SpinnerFrame();
				frame.setTitle("SpinnerTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with a panel that contains several spinners and a button that
 * displays the spinner values.
 */
@SuppressWarnings("serial")
class SpinnerFrame extends JFrame {
	private JPanel mainPanel;
	private JButton okButton;

	public SpinnerFrame() {
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("Ok");
		buttonPanel.add(okButton);
		add(buttonPanel, BorderLayout.SOUTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 3));
		add(mainPanel, BorderLayout.CENTER);

		JSpinner defaultSpinner = new JSpinner();
		addRow("Default", defaultSpinner);

		JSpinner boundedSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 0.5));
		addRow("Bounded", boundedSpinner);

		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

		JSpinner listSpinner = new JSpinner(new SpinnerListModel(fonts));
		addRow("List", listSpinner);

		JSpinner reverseListSpinner = new JSpinner(new SpinnerListModel(fonts) {
			public Object getNextValue() {
				return super.getPreviousValue();
			}

			public Object getPreviousValue() {
				return super.getNextValue();
			}
		});
		addRow("Reverse List", reverseListSpinner);

		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
		addRow("Date", dateSpinner);

		JSpinner betterDateSpinner = new JSpinner(new SpinnerDateModel());
		String pattern = ((SimpleDateFormat) DateFormat.getDateInstance()).toPattern();
		betterDateSpinner.setEditor(new JSpinner.DateEditor(betterDateSpinner, pattern));
		addRow("Better Date", betterDateSpinner);

		JSpinner timeSpinner = new JSpinner(new SpinnerDateModel());
		pattern = ((SimpleDateFormat) DateFormat.getTimeInstance(DateFormat.SHORT)).toPattern();
		timeSpinner.setEditor(new JSpinner.DateEditor(timeSpinner, pattern));
		addRow("Time", timeSpinner);

		JSpinner permSpinner = new JSpinner(new PermutationSpinnerModel("meat"));
		addRow("Word permutations", permSpinner);
		pack();
	}

	/**
	 * Adds a row to the main panel.
	 * 
	 * @param labelText
	 *            the label of the spinner
	 * @param spinner
	 *            the sample spinner
	 */
	public void addRow(String labelText, final JSpinner spinner) {
		mainPanel.add(new JLabel(labelText));
		mainPanel.add(spinner);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object value = spinner.getValue();
				valueLabel.setText(value.toString());
			}
		});
	}
}

/**
 * A model that dynamically generates word permutations.
 */
@SuppressWarnings("serial")
class PermutationSpinnerModel extends AbstractSpinnerModel {
	private String word;

	/**
	 * Constructs the model.
	 * 
	 * @param w
	 *            the word to permute
	 */
	public PermutationSpinnerModel(String w) {
		word = w;
	}

	public Object getValue() {
		return word;
	}

	public void setValue(Object value) {
		if (!(value instanceof String))
			throw new IllegalArgumentException();
		word = (String) value;
		fireStateChanged();
	}

	public Object getNextValue() {
		int[] codePoints = toCodePointArray(word);
		for (int i = codePoints.length - 1; i > 0; i--) {
			if (codePoints[i - 1] < codePoints[i]) {
				int j = codePoints.length - 1;
				while (codePoints[i - 1] > codePoints[j])
					j--;
				swap(codePoints, i - 1, j);
				reverse(codePoints, i, codePoints.length - 1);
				return new String(codePoints, 0, codePoints.length);
			}
		}
		reverse(codePoints, 0, codePoints.length - 1);
		return new String(codePoints, 0, codePoints.length);
	}

	public Object getPreviousValue() {
		int[] codePoints = toCodePointArray(word);
		for (int i = codePoints.length - 1; i > 0; i--) {
			if (codePoints[i - 1] > codePoints[i]) {
				int j = codePoints.length - 1;
				while (codePoints[i - 1] < codePoints[j])
					j--;
				swap(codePoints, i - 1, j);
				reverse(codePoints, i, codePoints.length - 1);
				return new String(codePoints, 0, codePoints.length);
			}
		}
		reverse(codePoints, 0, codePoints.length - 1);
		return new String(codePoints, 0, codePoints.length);
	}

	private static int[] toCodePointArray(String str) {
		int[] codePoints = new int[str.codePointCount(0, str.length())];
		for (int i = 0, j = 0; i < str.length(); i++, j++) {
			int cp = str.codePointAt(i);
			if (Character.isSupplementaryCodePoint(cp))
				i++;
			codePoints[j] = cp;
		}
		return codePoints;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	private static void reverse(int[] a, int i, int j) {
		while (i < j) {
			swap(a, i, j);
			i++;
			j--;
		}
	}
}
