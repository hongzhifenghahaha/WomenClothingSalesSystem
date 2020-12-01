package WomenClothingSalesSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShoppingGUI {
    private JFrame frame;
    private JPanel panel1;//,showCasePage,searchPage,detailPage,shoppingPage;
    private JTabbedPane tabbedPane1;
    private JTextArea textArea1;
    private JButton searchButton;
    private JTextArea textArea2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    private JTextArea textArea3;
    private JButton button6;
    private JButton button7;
    private JButton button8;
    private JButton button9;
    private JTextArea textArea4;

    ShoppingGUI(){
        textArea4.setEditable(false);
        Font font = new Font("Verdana", Font.BOLD, 12);
        textArea4.setFont(font);
        textArea4.setForeground(Color.BLUE);
        textArea4.setText(String.format("%30s%40s%40s","Name","Price","Status") );



        frame = new JFrame("ShoppingGUI");
        frame.setUndecorated(true);
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setBounds(240,50,800,600);
        frame.setSize(800,600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new ShoppingGUI();
    }
}
