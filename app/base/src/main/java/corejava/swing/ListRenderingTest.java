package corejava.swing;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * This program demonstrates the use of cell renderers in a list box.
 * @version 1.24 2012-01-26
 * @author Cay Horstmann
 */
public class ListRenderingTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new ListRenderingFrame();
               frame.setTitle("ListRenderingTest");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * This frame contains a list with a set of fonts and a text area that is set to the selected font.
 */
@SuppressWarnings("serial")
class ListRenderingFrame extends JFrame
{
   private static final int TEXT_ROWS = 8;
   private static final int TEXT_COLUMNS = 20;

   private JTextArea text;
   private JList<Font> fontList;

   public ListRenderingFrame()
   {
      java.util.List<Font> fonts = new ArrayList<>();
      final int SIZE = 24;
      fonts.add(new Font("Serif", Font.PLAIN, SIZE));
      fonts.add(new Font("SansSerif", Font.PLAIN, SIZE));
      fonts.add(new Font("Monospaced", Font.PLAIN, SIZE));
      fonts.add(new Font("Dialog", Font.PLAIN, SIZE));
      fonts.add(new Font("DialogInput", Font.PLAIN, SIZE));
      fontList = new JList<Font>(fonts.toArray(new Font[]{}));
      fontList.setVisibleRowCount(4);
      fontList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
      fontList.setCellRenderer(new FontCellRenderer());
      JScrollPane scrollPane = new JScrollPane(fontList);

      JPanel p = new JPanel();
      p.add(scrollPane);
      fontList.addListSelectionListener(new ListSelectionListener()
         {
            public void valueChanged(ListSelectionEvent evt)
            {
               Font font = fontList.getSelectedValue();
               text.setFont(font);
            }

         });

      Container contentPane = getContentPane();
      contentPane.add(p, BorderLayout.SOUTH);
      text = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      text.setText("The quick brown fox jumps over the lazy dog");
      text.setFont(fonts.get(0));
      text.setLineWrap(true);
      text.setWrapStyleWord(true);
      contentPane.add(text, BorderLayout.CENTER);
      pack();
   }
}


/**
 * A cell renderer for Font objects that renders the font name in its own font.
 */
@SuppressWarnings("serial")
class FontCellRenderer extends JComponent implements ListCellRenderer<Font>
{
   private Font font;
   private Color background;
   private Color foreground;

   public Component getListCellRendererComponent(JList<? extends Font> list, 
         Font value, int index, boolean isSelected, boolean cellHasFocus)
   {
      font = value;
      background = isSelected ? list.getSelectionBackground() : list.getBackground();
      foreground = isSelected ? list.getSelectionForeground() : list.getForeground();
      return this;
   }

   public void paintComponent(Graphics g)
   {
      String text = font.getFamily();
      FontMetrics fm = g.getFontMetrics(font);
      g.setColor(background);
      g.fillRect(0, 0, getWidth(), getHeight());
      g.setColor(foreground);
      g.setFont(font);
      g.drawString(text, 0, fm.getAscent());
   }

   public Dimension getPreferredSize()
   {
      String text = font.getFamily();
      Graphics g = getGraphics();
      FontMetrics fm = g.getFontMetrics(font);
      return new Dimension(fm.stringWidth(text), fm.getHeight());
   }
}
