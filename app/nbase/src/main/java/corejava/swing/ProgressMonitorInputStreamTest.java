package corejava.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Scanner;

import javax.swing.*;

/**
 * A program to test a progress monitor input stream.
 * @version 1.05 2012-01-26
 * @author Cay Horstmann
 */
public class ProgressMonitorInputStreamTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(new Runnable()
         {
            public void run()
            {
               JFrame frame = new TextFrame();
               frame.setTitle("ProgressMonitorInputStreamTest");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}


/**
 * A frame with a menu to load a text file and a text area to display its contents. The text area is
 * constructed when the file is loaded and set as the content pane of the frame when the loading is
 * complete. That avoids flicker during loading.
 */
@SuppressWarnings("serial")
class TextFrame extends JFrame
{
   public static final int TEXT_ROWS = 10;
   public static final int TEXT_COLUMNS = 40;

   private JMenuItem openItem;
   private JMenuItem exitItem;
   private JTextArea textArea;
   private JFileChooser chooser;

   public TextFrame()
   {
      textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      add(new JScrollPane(textArea));

      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("File");
      menuBar.add(fileMenu);
      openItem = new JMenuItem("Open");
      openItem.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               try
               {
                  openFile();
               }
               catch (IOException exception)
               {
                  exception.printStackTrace();
               }
            }
         });

      fileMenu.add(openItem);
      exitItem = new JMenuItem("Exit");
      exitItem.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0);
            }
         });
      fileMenu.add(exitItem);
      pack();
   }

   /**
    * Prompts the user to select a file, loads the file into a text area, and sets it as the content
    * pane of the frame.
    */
   public void openFile() throws IOException
   {
      int r = chooser.showOpenDialog(this);
      if (r != JFileChooser.APPROVE_OPTION) return;
      final File f = chooser.getSelectedFile();

      // set up stream and reader filter sequence
      
      InputStream fileIn = Files.newInputStream(f.toPath());
      final ProgressMonitorInputStream progressIn = new ProgressMonitorInputStream(
         this, "Reading " + f.getName(), fileIn);      

      textArea.setText("");

      SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
         {
            protected Void doInBackground() throws Exception
            {
               try (Scanner in = new Scanner(progressIn))
               {
                  while (in.hasNextLine())
                  {
                     String line = in.nextLine();
                     textArea.append(line);
                     textArea.append("\n");
                  }
               }
               return null;
            }
         };
      worker.execute();
   }
}


