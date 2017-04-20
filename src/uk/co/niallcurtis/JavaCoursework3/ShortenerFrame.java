package uk.co.niallcurtis.JavaCoursework3;

/*
 * Name: Niall Curtis
 * Student number: C1623580
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.tools.Tool;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class ShortenerFrame extends JFrame implements ActionListener{
    // This class is only a starting point. You may wish to add members and 
    // fields to complete the implementation of this class as per the
    // question on the assignment sheet.
    
    // Constants
    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 260;
    
    // Instance variables -- GUI components
    private JPanel panel;
    private JLabel instructionLabel;
    private JTextArea inputArea;
    private JTextArea outputArea;
    private JButton shortenButton;
    private JButton copyClipboard;
    private JButton pasteClipboard;
    
    // Constructor
    public ShortenerFrame() {
        super();

        // Additional Functions from https://docs.oracle.com/javase/tutorial/uiswing/components/textarea.html
        //
        // Set up the frame
        // Set window title
        setTitle("English to TXT Abbreviations");
        setSize( FRAME_WIDTH, FRAME_HEIGHT );
        
        //
        // Set up the panel and layout manager
        panel = new JPanel();
        GridLayout grid = new GridLayout( 0, 1 );  // a one-column layout
        panel.setLayout( grid );
        
        add( panel );  // add panel to the JFrame

        //
        // Set up and add components
        instructionLabel = new JLabel( "Enter text in the field below and click 'Shorten'" );
        panel.add( instructionLabel );
        
        inputArea = new JTextArea();
        // Simple two line to wrap text, then wrap by word
        inputArea.setLineWrap(true);
        inputArea.setWrapStyleWord(true);
        panel.add( inputArea );
        
        shortenButton = new JButton( "Shorten" );
        panel.add( shortenButton );
        
        outputArea = new JTextArea();
        // Make uneditable
        outputArea.setEditable(false);
        panel.add( outputArea );

        copyClipboard = new JButton("Copy Output to Clipboard");
        panel.add(copyClipboard);

        pasteClipboard = new JButton("Paste Clipboard to Input");
        panel.add(pasteClipboard);

        // We are going to implement two extra buttons to paste from the clipboard, and copy to the clipboard
        // http://docs.oracle.com/javase/7/docs/api/java/awt/datatransfer/Clipboard.html
        // These awt datatransfer API docs teach us how


        shortenButton.addActionListener(this);
        copyClipboard.addActionListener(this);
        pasteClipboard.addActionListener(this);
    }

    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == shortenButton) {
            String userInput = inputArea.getText();
            // In Shortener.java, we catch an error when trying to read and return filename + "could not be read", then display it to the user here.
            Shortener msgshort = new Shortener("abbreviations.txt");
            outputArea.setText(msgshort.shortenMessage(userInput));
        }
        else if(evt.getSource() == copyClipboard) {
            // More clipboard stuff
            // Make a new StringSelection from the output area
            StringSelection output = new StringSelection(outputArea.getText());
            // Get the system clipboard
            Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
            // Set clipboard contents
            board.setContents(output, null);
        }
        else if(evt.getSource() == pasteClipboard) {
            // This official Oracle Docs blog post helps a lot
            // https://blogs.oracle.com/JavaFundamentals/entry/transferring_text_through_the_clipboard
            Clipboard board = Toolkit.getDefaultToolkit().getSystemClipboard();
            // We need to handle exceptions to ensure our clipboard data is text
            try {
                // Convoluted stuff. We get the clipboard contents, then get transferable data in the flavor we need (String)
                String pasteString = (String) (board.getContents(null).getTransferData(DataFlavor.stringFlavor));
                inputArea.setText(pasteString);
            }
            catch (Exception e ) {
                outputArea.setText("Data is wrong flavor.");
            }
        }
    }
}

