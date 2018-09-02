package Hash;

import Hash.HashTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Adem KOCAMAZ
 */
public class WordParser {
// -------------------------------------------------------------

    //private String[] words;
    //private int wordCounter;
    private File file;
    private Scanner reader;
    public HashTable hashTable;
            
    private ArrayList uniqueList;
    private ArrayList wordList;
// -------------------------------------------------------------

    public WordParser(File file) {
        this.file = file;
        //wordCounter = 0;
        parse();
        hash();
    }
// -------------------------------------------------------------

//    public String[] getWords() {
//        return words;
//    }
// -------------------------------------------------------------
    public JTable getTop10(){
        String[] columnNames = {"Word", "Frequency"};

        Object[][] data = hashTable.getTop10();

        final JTable table = new JTable(data, columnNames);
        Font font=new Font("Arial", Font.BOLD, 15);
        table.setFont(font);
        table.setPreferredScrollableViewportSize(new Dimension(200, 400));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.repaint();

        return table;
    }
// -------------------------------------------------------------
    public JTable getTable() {
        String[] columnNames = {"Word", "Frequency"};

        Object[][] data = hashTable.getItems();

        final JTable table = new JTable(data, columnNames);
        Font font=new Font("Arial", Font.BOLD, 15);
        table.setFont(font);
        table.setPreferredScrollableViewportSize(new Dimension(200, 400));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        table.repaint();

        return table;
    }
// -------------------------------------------------------------

    private void createWords() {
        if (file.exists()) {
//            try {
//                reader=new Scanner(file);
//            } catch (FileNotFoundException ex) {
//                JOptionPane.showMessageDialog(null, file.getName() + " not found!");
//            }            
//            while(reader.hasNext()){                
//                wordCounter++;      
//                reader.next();
//            }
//            reader.close();
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
            //System.out.println(everything);
            everything = everything.replaceAll("\\.|\\]|\\[|[0-9]|,|\\?|:|\\(|\\)|;|-|!|=", "");
            //everything = everything.toLowerCase();
            
            //System.out.println(everything);
            
            Pattern pattern = Pattern.compile("[\\w]+");
            Matcher m = pattern.matcher(everything);
            
            uniqueList = new ArrayList();
            wordList = new ArrayList();            
            while (m.find()) {
                wordList.add(m.group());
            }
            
            for (int i = 0; i < wordList.size(); i++) {
                if (!uniqueList.contains(wordList.get(i))) {
                    uniqueList.add(wordList.get(i));
                }
            }
            

            //wordCounter = wordList.size();

//            if (wordCounter == 0) {
//                JOptionPane.showMessageDialog(null, file.getName() + " has 0 words");
//                System.exit(0);
//            }

//            words = new String[wordList.size()];
//            for (int i = 0; i < wordList.size(); i++) {
//                words[i]=wordList.get(i).toString();
//            }
            //words=wordList.toArray();
            //JOptionPane.showMessageDialog(null, file.getName() + " has "+wordCounter+" words");
        }
    }
// -------------------------------------------------------------

    private void parse() {
        createWords();

//        if (file.exists()) {
//            try {
//                reader = new Scanner(file);
//            } catch (FileNotFoundException ex) {
//                JOptionPane.showMessageDialog(null, file.getName() + " not found!");
//            }
//            int index = 0;
//            String word = new String("");
//            while (reader.hasNext()) {
//                word = reader.next();
//                //if(check(word))continue;
//                //word.replaceAll("\"|\\.|\\]|\\[|[0-9]|,|\\?|:|\\(|\\)|;|-|!","");
//                Pattern p = Pattern.compile("[a-zA-Z]+");
//                Matcher m = p.matcher(word);
//                while (m.find()) {
//                    word = word.substring(m.start(), m.end());
//                }
//                words[index++] = word.toLowerCase();
//            }
//            reader.close();
//        }
    }
// -------------------------------------------------------------
    private boolean check(String word){
//        if(word.contains("the")
//                ||word.contains("The")
//                ||word.contains("a")
//                ||word.contains("A")
//                ||word.contains("in")
//                ||word.contains("on")
//                ||word.contains("at")
//                ||word.contains("for")
//                ||word.contains("to")){
//                return true;
//        }
        if(word.length()==1&&!word.equalsIgnoreCase("A"))
            return false;
        return true;
    }
// -------------------------------------------------------------

    private void hash() {
        hashTable = new HashTable(wordList.size());
        for (int i = 0; i < wordList.size(); i++) {
            if(check(wordList.get(i).toString()))
                hashTable.insert(wordList.get(i).toString());
        }
    }
// -------------------------------------------------------------
    private JPanel makeTextPanel(String text) {
        Font font = new Font("Times", Font.BOLD, 15);
        JPanel panel = new JPanel(false);
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(1, 1));
        panel.add(label);
        return panel;
    }
}
