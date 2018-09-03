package corejava.swing;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This program demonstrates a list that dynamically computes list entries.
 * 
 * @version 1.23 2007-08-01
 * @author Cay Horstmann
 */
public class LongListTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new LongListFrame();
				frame.setTitle("LongListTest");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}

/**
 * This frame contains a long word list and a label that shows a sentence made
 * up from the chosen word.
 */
@SuppressWarnings("serial")
class LongListFrame extends JFrame {
	private JList<String> wordList;
	private JLabel label;
	private String prefix = "The quick brown ";
	private String suffix = " jumps over the lazy dog.";

	public LongListFrame() {
		wordList = new JList<String>(new WordListModel(3));
		wordList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		wordList.setPrototypeCellValue("www");
		JScrollPane scrollPane = new JScrollPane(wordList);

		JPanel p = new JPanel();
		p.add(scrollPane);
		wordList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent evt) {
				setSubject(wordList.getSelectedValue());
			}
		});

		Container contentPane = getContentPane();
		contentPane.add(p, BorderLayout.NORTH);
		label = new JLabel(prefix + suffix);
		contentPane.add(label, BorderLayout.CENTER);
		setSubject("fox");
		pack();
	}

	/**
	 * Sets the subject in the label.
	 * 
	 * @param word
	 *            the new subject that jumps over the lazy dog
	 */
	public void setSubject(String word) {
		StringBuilder text = new StringBuilder(prefix);
		text.append(word);
		text.append(suffix);
		label.setText(text.toString());
	}
}

/**
 * A model that dynamically generates n-letter words.
 */
@SuppressWarnings("serial")
class WordListModel extends AbstractListModel<String> {
	private int length;
	public static final char FIRST = 'a';
	public static final char LAST = 'z';

	/**
	 * Constructs the model.
	 * 
	 * @param n
	 *            the word length
	 */
	public WordListModel(int n) {
		length = n;
	}

	public int getSize() {
		return (int) Math.pow(LAST - FIRST + 1, length);
	}

	public String getElementAt(int n) {
		StringBuilder r = new StringBuilder();

		for (int i = 0; i < length; i++) {
			char c = (char) (FIRST + n % (LAST - FIRST + 1));
			r.insert(0, c);
			n = n / (LAST - FIRST + 1);
		}
		return r.toString();
	}
}
