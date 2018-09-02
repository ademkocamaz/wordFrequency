package App;

import Hash.WordParser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author Adem KOCAMAZ
 */
public class App extends JFrame {

    File file;
    WordParser wp;
// -------------------------------------------------------------

    public App() {
        super("Word Frequency");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(getMenu());
        setPreferredSize(new Dimension(1024, 768));
        setContentPane(makeTextPanel("Select File->Open"));
        pack();
        setVisible(true);
        //JScrollPane scrollPane = new JScrollPane(WordParser.getTable());
        //add(scrollPane);
    }
// -------------------------------------------------------------

    public File getFile() {
        JFileChooser fc;
        fc = new JFileChooser();
        fc.setMultiSelectionEnabled(false);
        fc.setCurrentDirectory(new File(System.getProperty("user.dir")));
        fc.showDialog(null, "Open");
        return fc.getSelectedFile();
    }
// -------------------------------------------------------------
    public JMenuBar getMenu() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JMenuItem exit;

        menuBar = new JMenuBar();

        menu = new JMenu("File");
        menuBar.add(menu);

        menuItem = new JMenuItem("Open");
        menuItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                getContent();
            }
        });
        
        exit=new JMenuItem("Exit");
        exit.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
            
        });
        menu.add(menuItem);
        menu.addSeparator();
        menu.add(exit);

        

        return menuBar;
    }
// -------------------------------------------------------------

    private JPanel makeTextPanel(String text) {
        Font font = new Font("Arial", Font.BOLD, 15);
        JPanel panel = new JPanel(false);
        JLabel label = new JLabel(text);
        label.setFont(font);        
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(1, 1));
        panel.setBackground(Color.DARK_GRAY);
        panel.add(label);
        return panel;
    }
// -------------------------------------------------------------

    private String getText() {
        String everything = new String();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            everything = sb.toString();
        } catch (Exception ex) {

        }
        return everything;
    }
// -------------------------------------------------------------

    public void getContent() {
        //        HashTable h=new HashTable(11);
//        h.insert("jeoyt", 5);
//        h.displayTable();

        JPanel hashPanel = new JPanel(new GridLayout(2, 0));
        file = getFile();

        if (file != null) {
            wp = new WordParser(file);
            //wp.parse();
//            String[] words = wp.getWords();
//            for (int i = 0; i < words.length; i++) {
//                System.out.println(words[i]);
//            }
            //wp.hashTable.displayTable();
            JPanel allPanel = new JPanel(new BorderLayout());
            allPanel.add(makeTextPanel("All Words"), BorderLayout.NORTH);

            JScrollPane allScrollPane = new JScrollPane(wp.getTable());
            allPanel.add(allScrollPane);

            JPanel top10Panel = new JPanel(new BorderLayout());
            top10Panel.add(makeTextPanel("Top 10"), BorderLayout.NORTH);

            JScrollPane top10ScrollPane = new JScrollPane(wp.getTop10());
            top10Panel.add(top10ScrollPane);

            hashPanel.add(allPanel);
            hashPanel.add(top10Panel);
            hashPanel.setOpaque(true);

        } else {
            //JOptionPane.showMessageDialog(null, "file is null!");
            return;
        }
        //add(p);
        
        Font font=new Font("Arial", Font.BOLD, 12);
        JTextArea text = new JTextArea(20, 30);
        text.setMargin(new Insets(5, 5, 5, 5));
        text.setEditable(false);
        JScrollPane textScrollPane = new JScrollPane(text);
        text.setFont(font);
        text.setBackground(Color.DARK_GRAY);
        text.setForeground(Color.WHITE);
        text.setLineWrap(true);
        text.setText(getText());
        
        JPanel p=new JPanel(new GridLayout(0,2));        
        p.add(textScrollPane);
        p.add(hashPanel);
        setContentPane(p);
        //repaint();
        pack();
    }
// -------------------------------------------------------------

    public static void main(String[] args) {
        new App();
    }
// -------------------------------------------------------------
}
