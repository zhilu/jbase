package corejava.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultFormatter;
import javax.swing.text.DocumentFilter;
import javax.swing.text.InternationalFormatter;
import javax.swing.text.MaskFormatter;

/**
 * A program to test formatted text fields
 * 
 * @version 1.03 2012-06-08
 * @author Cay Horstmann
 */
public class FormatTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new FormatTestFrame();
				frame.setTitle("FormatTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * A frame with a collection of formatted text fields and a button that displays
 * the field values.
 */
@SuppressWarnings("serial")
class FormatTestFrame extends JFrame {
	private DocumentFilter filter = new IntFilter();
	private JButton okButton;
	private JPanel mainPanel;

	public FormatTestFrame() {
		JPanel buttonPanel = new JPanel();
		okButton = new JButton("Ok");
		buttonPanel.add(okButton);
		add(buttonPanel, BorderLayout.SOUTH);

		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 3));
		add(mainPanel, BorderLayout.CENTER);

		JFormattedTextField intField = new JFormattedTextField(NumberFormat.getIntegerInstance());
		intField.setValue(new Integer(100));
		addRow("Number:", intField);

		JFormattedTextField intField2 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		intField2.setValue(new Integer(100));
		intField2.setFocusLostBehavior(JFormattedTextField.COMMIT);
		addRow("Number (Commit behavior):", intField2);

		JFormattedTextField intField3 = new JFormattedTextField(
				new InternationalFormatter(NumberFormat.getIntegerInstance()) {
					protected DocumentFilter getDocumentFilter() {
						return filter;
					}

				});
		intField3.setValue(new Integer(100));
		addRow("Filtered Number", intField3);

		JFormattedTextField intField4 = new JFormattedTextField(NumberFormat.getIntegerInstance());
		intField4.setValue(new Integer(100));
		intField4.setInputVerifier(new InputVerifier() {
			public boolean verify(JComponent component) {
				JFormattedTextField field = (JFormattedTextField) component;
				return field.isEditValid();
			}
		});
		addRow("Verified Number:", intField4);

		JFormattedTextField currencyField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
		currencyField.setValue(new Double(10));
		addRow("Currency:", currencyField);

		JFormattedTextField dateField = new JFormattedTextField(DateFormat.getDateInstance());
		dateField.setValue(new Date());
		addRow("Date (default):", dateField);

		DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT);
		format.setLenient(false);
		JFormattedTextField dateField2 = new JFormattedTextField(format);
		dateField2.setValue(new Date());
		addRow("Date (short, not lenient):", dateField2);

		try {
			DefaultFormatter formatter = new DefaultFormatter();
			formatter.setOverwriteMode(false);
			JFormattedTextField urlField = new JFormattedTextField(formatter);
			urlField.setValue(new URL("http://java.sun.com"));
			addRow("URL:", urlField);
		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		}

		try {
			MaskFormatter formatter = new MaskFormatter("###-##-####");
			formatter.setPlaceholderCharacter('0');
			JFormattedTextField ssnField = new JFormattedTextField(formatter);
			ssnField.setValue("078-05-1120");
			addRow("SSN Mask:", ssnField);
		} catch (ParseException ex) {
			ex.printStackTrace();
		}

		JFormattedTextField ipField = new JFormattedTextField(new IPAddressFormatter());
		ipField.setValue(new byte[] { (byte) 130, 65, 86, 66 });
		addRow("IP Address:", ipField);
		pack();
	}

	/**
	 * Adds a row to the main panel.
	 * 
	 * @param labelText
	 *            the label of the field
	 * @param field
	 *            the sample field
	 */
	public void addRow(String labelText, final JFormattedTextField field) {
		mainPanel.add(new JLabel(labelText));
		mainPanel.add(field);
		final JLabel valueLabel = new JLabel();
		mainPanel.add(valueLabel);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				Object value = field.getValue();
				Class<?> cl = value.getClass();
				String text = null;
				if (cl.isArray()) {
					if (cl.getComponentType().isPrimitive()) {
						try {
							text = Arrays.class.getMethod("toString", cl).invoke(null, value).toString();
						} catch (ReflectiveOperationException ex) {
							// ignore reflection exceptions
						}
					} else
						text = Arrays.toString((Object[]) value);
				} else
					text = value.toString();
				valueLabel.setText(text);
			}
		});
	}
}

/**
 * A filter that restricts input to digits and a '-' sign.
 */
class IntFilter extends DocumentFilter {
	public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
			throws BadLocationException {
		StringBuilder builder = new StringBuilder(string);
		for (int i = builder.length() - 1; i >= 0; i--) {
			int cp = builder.codePointAt(i);
			if (!Character.isDigit(cp) && cp != '-') {
				builder.deleteCharAt(i);
				if (Character.isSupplementaryCodePoint(cp)) {
					i--;
					builder.deleteCharAt(i);
				}
			}
		}
		super.insertString(fb, offset, builder.toString(), attr);
	}

	public void replace(FilterBypass fb, int offset, int length, String string, AttributeSet attr)
			throws BadLocationException {
		if (string != null) {
			StringBuilder builder = new StringBuilder(string);
			for (int i = builder.length() - 1; i >= 0; i--) {
				int cp = builder.codePointAt(i);
				if (!Character.isDigit(cp) && cp != '-') {
					builder.deleteCharAt(i);
					if (Character.isSupplementaryCodePoint(cp)) {
						i--;
						builder.deleteCharAt(i);
					}
				}
			}
			string = builder.toString();
		}
		super.replace(fb, offset, length, string, attr);
	}
}

/**
 * A formatter for 4-byte IP addresses of the form a.b.c.d
 */
@SuppressWarnings("serial")
class IPAddressFormatter extends DefaultFormatter {
	public String valueToString(Object value) throws ParseException {
		if (!(value instanceof byte[]))
			throw new ParseException("Not a byte[]", 0);
		byte[] a = (byte[]) value;
		if (a.length != 4)
			throw new ParseException("Length != 4", 0);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			int b = a[i];
			if (b < 0)
				b += 256;
			builder.append(String.valueOf(b));
			if (i < 3)
				builder.append('.');
		}
		return builder.toString();
	}

	public Object stringToValue(String text) throws ParseException {
		StringTokenizer tokenizer = new StringTokenizer(text, ".");
		byte[] a = new byte[4];
		for (int i = 0; i < 4; i++) {
			int b = 0;
			if (!tokenizer.hasMoreTokens())
				throw new ParseException("Too few bytes", 0);
			try {
				b = Integer.parseInt(tokenizer.nextToken());
			} catch (NumberFormatException e) {
				throw new ParseException("Not an integer", 0);
			}
			if (b < 0 || b >= 256)
				throw new ParseException("Byte out of range", 0);
			a[i] = (byte) b;
		}
		if (tokenizer.hasMoreTokens())
			throw new ParseException("Too many bytes", 0);
		return a;
	}
}
