package WomenClothingSalesSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

/**
 * This class implements a WomenClothingSalesSystem
 *
 * @author Hong Zhifeng
 * @version 1.2.1
 * @see ClothingDataBase
 * @see Page
 */

public class WomenClothingSalesSystem {
    /**
     * change the color of console
     */
    public static final String NONE = "\033[0m";
    public static final String RED = "\033[31m";
    public static final String GREEN = "\033[32m";
    public static final String YELLOW = "\033[33m";
    public static final String BLUE = "\033[34m";
    public static final String MAGENTA = "\033[35m";
    public static final String CYAN = "\033[36m";
    public static final String WHITE = "\033[37m";

    private static BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    private static PrintWriter stdOut = new PrintWriter(System.out, true);

    private Page[] pages;
    private ClothingDataBase clothingDataBase;
    private ShoppingGUI shoppingGUI;

    /**
     * Loads clothings from the database and initial pages
     *
     * @param args String arguments. Not used.
     */
    public static void main(String[] args) throws IOException {
        Page[] pages = new Page[4];

        ClothingDataBase clothingDataBase = loadClothingDataBase("input.dat");

        WomenClothingSalesSystem app = new WomenClothingSalesSystem(pages, clothingDataBase);

        app.initialPages();   //用户界面版本
        app.showCasePageGUI();
        app.SearchPageGUI();
        app.detailPageGUI();
        app.shoppingCartPageGUI();


//        app.run(); //要运行控制台版本  请注释掉上面五个app方法  并取消app.run的注释
    }

    /**
     * Creates a <code>WomenClothingSalesSystem</code> object.
     */
    public WomenClothingSalesSystem(Page[] pages, ClothingDataBase clothingDataBase) {
        this.pages = pages;
        this.clothingDataBase = clothingDataBase;
        this.shoppingGUI = new ShoppingGUI();
    }

    /**
     * Show the menu to switch pages
     */
    public void run() throws IOException {
        int choice = getChoice();

        while (choice != 0) {
            switch (choice) {
                case 1: {
                    showShowCasePage();
                    break;
                }
                case 2: {
                    showSearchPage();
                    break;
                }
                case 3: {
                    showDetailPage();
                    break;
                }
                case 4: {
                    showShoppingCartPage();
                    break;
                }
                default: {
                    System.out.println(RED + "please input correct choice!");
                    break;
                }
            }
            choice = getChoice();
        }
    }

    /**
     * Validates the user's choice
     *
     * @return valided result
     */
    private int getChoice() throws IOException {

        int input;

        do {
            try {
                stdOut.println();
                stdOut.print(RED + "[0]  Quit\n"
                        + "[1]  show the ShowcasePage\n"
                        + "[2]  show the SearchPage\n"
                        + "[3]  show the DetailPage\n"
                        + "[4]  show the ShoppingCartPage\n"
                        + "choice> ");
                stdOut.flush();

                input = Integer.parseInt(stdIn.readLine());

                if (0 <= input && 4 >= input) {
                    break;
                } else {
                    stdOut.println(RED + "Invalid choice:  " + input);
                }
            } catch (NumberFormatException nfe) {
                stdOut.println(RED + nfe);
            }
        } while (true);

        return input;
    }

    public void shoppingCartPageGUI() {
        JTextArea textArea1 = shoppingGUI.getTextArea1();
        textArea1.setEditable(false);
        ShoppingCartPage shoppingCartPage = ((ShoppingCartPage) pages[3]);
        JButton addButton = shoppingGUI.getButton6();
        JButton reduceButton = shoppingGUI.getButton8();
        JButton buyButton = shoppingGUI.getButton7();
        JButton removeButton = shoppingGUI.getButton9();

        Iterator<Column> columnIterator = shoppingCartPage.iterator();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Column> columnIterator = shoppingCartPage.iterator();
                while (columnIterator.hasNext()) {
                    ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                    if (next.getCheckBox().isSelected()) {
                        next.setChoosedStatus(true);
                        next.setChoosedQuantity(next.getChoosedQuantity() + 1);
                    } else {
                        next.setChoosedStatus(false);
                    }
                }
                reDrawShoppingCartPage();
            }
        });

        reduceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Column> columnIterator = shoppingCartPage.iterator();
                boolean isReduce = false;
                while (columnIterator.hasNext()) {
                    ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                    if (next.getCheckBox().isSelected()) {
                        next.setChoosedStatus(true);
                        if (next.getChoosedQuantity() > 0) {
                            next.setChoosedQuantity(next.getChoosedQuantity() - 1);
                            isReduce = true;
                        }
                    } else {
                        next.setChoosedStatus(false);
                    }
                }
                reDrawShoppingCartPage();
                if (!isReduce){
                    textArea1.append("\n can not reduce! the choosed quantity is 0!\n");
                }
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Column> columnIterator = shoppingCartPage.iterator();
                boolean isBuy = false;
                while (columnIterator.hasNext()) {
                    ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                    if (next.getCheckBox().isSelected()) {
                        next.setChoosedStatus(true);
                        if (next.getChoosedQuantity() > 0) {
                            next.setChoosedQuantity(0);
                            isBuy = true;
                        }
                    } else {
                        next.setChoosedStatus(false);
                    }
                }
                reDrawShoppingCartPage();
                if (!isBuy){
                    textArea1.append("\n can not buy! the choosed quantity is 0!\n");
                } else{
                    textArea1.append("\n buy the clothes successfully\n");
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Iterator<Column> columnIterator = shoppingCartPage.iterator();
                boolean isRemove = false;
                while (columnIterator.hasNext()) {
                    ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                    if (next.getCheckBox().isSelected()) {
                        next.setChoosedStatus(true);
                        textArea1.remove(next.getCheckBox());
                        columnIterator.remove();
                        isRemove = true;
                    } else {
                        next.setChoosedStatus(false);
                    }
                }
                reDrawShoppingCartPage();
                if (!isRemove){
                    textArea1.append("\n can not remove! the choosed quantity is 0!\n");
                } else{
                    textArea1.append("\n remove the clothes successfully\n");
                }
            }
        });
    }

    public void reDrawShoppingCartPage() {
        JTextArea textArea1 = shoppingGUI.getTextArea1();
        ShoppingCartPage shoppingCartPage = ((ShoppingCartPage) pages[3]);

        if (shoppingCartPage.getNumberOfColumns() == 0) {
            textArea1.setText("The Shopping cart is empty!\nplease add something from the detailPage");
        } else {
            textArea1.setText("    Choosed Status            Price            Quantity            Name");
            Iterator<Column> columnIterator = shoppingCartPage.iterator();
            int cnt = 0;
            while (columnIterator.hasNext()) {
                ShoppingColumn shoppingColumn = ((ShoppingColumn) columnIterator.next());
                shoppingColumn.setCheckBoxBounds(40, 20 + cnt * 32, 20, 20);
                textArea1.add(shoppingColumn.getCheckBox());
                textArea1.append(String.format("\n%46s%18s%27s", "" + shoppingColumn.getPrice()
                        , "" + shoppingColumn.getChoosedQuantity(), "" + shoppingColumn.getName() + "\n"));
                cnt++;
            }
        }
    }

    /**
     * Show the Shopping cart page
     */
    public void showShoppingCartPage() throws IOException {
        ShoppingCartPage shoppingCartPage = ((ShoppingCartPage) pages[3]);
        if (shoppingCartPage.getNumberOfColumns() == 0) {
            stdOut.println(CYAN + "\nthe shoppingcart is empty! please add something from the Detailpage");
            return;
        } else {
            stdOut.println(YELLOW + "please choose clothings which you want to operate");
        }
        while (true) {
            try {
                if (shoppingCartPage.getNumberOfColumns() > 0) {
                    stdOut.println(String.format("%21s%12s%13s%16s", BLUE + "Choosed Status", "Name", "Price", "Quantity"));
                } else {
                    stdOut.println(NONE + "\nshoppingcart is empty");
                }
                Iterator<Column> columnIterator = shoppingCartPage.iterator();
                while (columnIterator.hasNext()) {
                    stdOut.println(((ShoppingColumn) columnIterator.next()).toString());
                }
                stdOut.println();

                stdOut.println(YELLOW + "0. return\n1. choose clothings\n2. add all choosed\n3. reduce add choosed\n4. buy all choosed\n5. remove all choosed");
                stdOut.print("choice> ");
                stdOut.flush();
                int choice = Integer.parseInt(stdIn.readLine());

                if (choice >= 0 && choice <= 5) {

                    switch (choice) {
                        case 0: {
                            return;
                        }
                        case 1: {
                            stdOut.println(YELLOW + "\nplease choose by number:");
                            columnIterator = shoppingCartPage.iterator();
                            int cnt = 0;
                            while (columnIterator.hasNext()) {
                                cnt++;
                                stdOut.println(NONE + "  " + cnt + "." + ((ShoppingColumn) columnIterator.next()).getName());
                            }
                            if (cnt == 0) {
                                stdOut.println(RED + "nothing to choosed!");
                            } else {
                                while (true) {
                                    try {
                                        stdOut.print(YELLOW + "number> ");
                                        stdOut.flush();
                                        int number = Integer.parseInt(stdIn.readLine());
                                        if (number >= 1 && number <= cnt) {
                                            if (shoppingCartPage.getShoppingColumn(number - 1).isChoosed()) {
                                                shoppingCartPage.getShoppingColumn(number - 1).setChoosedStatus(false);
                                            } else {
                                                shoppingCartPage.getShoppingColumn(number - 1).setChoosedStatus(true);
                                            }
                                            break;
                                        } else {
                                            stdOut.println(RED + "Invalid choice:  " + number);
                                        }
                                    } catch (NumberFormatException nfe) {
                                        stdOut.println(RED + nfe);
                                    }
                                }
                            }
                            break;
                        }
                        case 2: {
                            boolean isFound = false;
                            columnIterator = shoppingCartPage.iterator();
                            while (columnIterator.hasNext()) {
                                ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                                if (next.isChoosed()) {
                                    next.setChoosedQuantity(next.getChoosedQuantity() + 1);
                                    isFound = true;
                                }
                            }
                            if (!isFound) {
                                stdOut.println(RED + "can not add! nothing been choosed!\nYou can input '1' to choose something");
                            } else {
                                stdOut.println(NONE + "add all choosed clothing by 1 successfully");
                            }
                            break;
                        }
                        case 3: {
                            boolean isFound = false;
                            columnIterator = shoppingCartPage.iterator();
                            while (columnIterator.hasNext()) {
                                ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                                if (next.isChoosed()) {
                                    if (next.getChoosedQuantity() != 0) {
                                        next.setChoosedQuantity(next.getChoosedQuantity() - 1);
                                    } else {
                                        stdOut.println(RED + "can not reduce " + MAGENTA + next.getName()
                                                + RED + " by 1! its choosed quantity is 0!\nYou can input '2' to add the quantity of any clothing choosed");
                                    }
                                    isFound = true;
                                }
                            }
                            if (!isFound) {
                                stdOut.println(RED + "can not reduce! nothing been choosed!\nYou can input '1' to choose something");
                            } else {
                                stdOut.println(NONE + "reduce all choosed clothing by 1 successfully");
                            }
                            break;
                        }
                        case 4: {
                            boolean isFound = false;
                            columnIterator = shoppingCartPage.iterator();
                            while (columnIterator.hasNext()) {
                                ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                                if (next.isChoosed()) {
                                    if (next.getChoosedQuantity() != 0) {
                                        stdOut.println(NONE + "You have buy " + MAGENTA + next.getChoosedQuantity() + " " + YELLOW + next.getName()
                                                + NONE + " and cost " + MAGENTA + next.getPrice() * next.getChoosedQuantity()
                                                + NONE + " RMB");
                                        next.setChoosedQuantity(0);
                                        next.setChoosedStatus(false);
                                    } else {
                                        stdOut.println(RED + "can not buy " + MAGENTA + next.getName()
                                                + RED + "! its choosed quantity is 0\nYou can input '2' to add the quantity of any clothing choosed");
                                    }
                                    isFound = true;
                                }
                            }
                            if (!isFound) {
                                stdOut.println(RED + "can not buy! nothing been choosed!\nYou can input '1' to choose something");
                            }
                            break;
                        }
                        case 5: {
                            boolean isFound = false;
                            columnIterator = shoppingCartPage.iterator();
                            while (columnIterator.hasNext()) {
                                ShoppingColumn next = ((ShoppingColumn) columnIterator.next());
                                if (next.isChoosed()) {
                                    stdOut.println(NONE + "remove " + MAGENTA + next.getName() + NONE + " from the shopping cart");
                                    isFound = true;
                                    columnIterator.remove();
                                }
                            }
                            if (!isFound) {
                                stdOut.println(RED + "can not remove! nothing been choosed!\nYou can input '1' to choose something");
                            } else
                                break;
                        }
                        default: {
                            break;
                        }
                    }
                } else {
                    stdOut.println(RED + "Invalid choice:  " + choice);
                }
            } catch (NumberFormatException nfe) {
                stdOut.println(RED + nfe);
            }
        }
    }

    public void detailPageGUI() {
        DetailsPage detailsPage = ((DetailsPage) pages[2]);
        ShoppingCartPage shoppingCartPage = ((ShoppingCartPage) pages[3]);
        JTextArea textArea1 = shoppingGUI.getTextArea1();
        textArea1.setEditable(false);
//        textArea1.setText("    Choosed Status            Price            Quantity            Name");

        JTextField textField2 = shoppingGUI.getTextField2();
        JTextArea textArea3 = shoppingGUI.getTextArea3();
        JButton beginButton = shoppingGUI.getButton5();
//        JButton addButton = shoppingGUI.getButton1();
//        JButton reduceButton = shoppingGUI.getButton3();
        JButton buyButton = shoppingGUI.getButton4();
        JButton cartButton = shoppingGUI.getButton2();
        JSpinner spinner = shoppingGUI.getSpinner1();
        SpinnerModel value = new SpinnerNumberModel(0, 0, 99, 1);
        spinner.setModel(value);

        textArea3.setEditable(false);
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);

        final boolean[] isFound = {false};

        beginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFound[0] = false;
                String content = textField2.getText();

                Iterator<WomenClothing> iter = clothingDataBase.iterator();
                while (iter.hasNext()) {
                    WomenClothing womenClothing = iter.next();
                    if (womenClothing.getName().equals(content)) {
                        detailsPage.setDetailsBlock(new DetailsBlock(womenClothing));
                        isFound[0] = true;
                        break;
                    }
                }

                if (isFound[0]) {
                    textArea3.setText(detailsPage.getDetailsBlock().getWomenClothings().toStringGUI() + "\n");
                } else {
                    textArea3.setText("Nothing found!\ntry to search like 'skirt_1' 'dress_3' 'coat_2' 'pants_4'");
                    return;
                }
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isFound[0]) {
                    textArea3.setText("There is nothing to buy\n" +
                            "try to input like 'skirt_1' 'dress_3' 'coat_2' 'pants_4' and press begin button");
                } else {
                    if (((int) spinner.getValue()) == 0) {
                        textArea3.append("\ncan not buy! the quantity is 0");
                    } else {

                        detailsPage.getDetailsBlock().setChoosedQuantity(((int) spinner.getValue()));
                        textArea3.append("\nYou have buy " + detailsPage.getDetailsBlock().getChoosedQuantity()
                                + " " + detailsPage.getDetailsBlock().getWomenClothings().getName()
                                + " succesfully!");
                        spinner.setValue((int) 0);
                        detailsPage.getDetailsBlock().setChoosedQuantity(0);
                    }
                }
            }
        });

        ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
        final int[] cnt = {0};

        cartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isFound[0]) {
                    textArea3.setText("There is nothing to add to shopping cart\n" +
                            "try to input like 'skirt_1' 'dress_3' 'coat_2' 'pants_4' and press begin button");
                } else {
                    if (((int) spinner.getValue()) == 0) {
                        textArea3.append("\ncan not add to shopping cart! the quantity is 0");
                    } else {
                        detailsPage.getDetailsBlock().setChoosedQuantity(((int) spinner.getValue()));
                        textArea3.append("\nYou have add " + detailsPage.getDetailsBlock().getChoosedQuantity()
                                + " " + detailsPage.getDetailsBlock().getWomenClothings().getName()
                                + " to shopping cart succesfully!");


//                        JCheckBox checkBox = new JCheckBox();
//                        checkBox.setBackground(Color.WHITE);
//                        checkBox.setBounds(40, 20+cnt[0] *33,20,20);
//                        textArea1.add(checkBox);
//                        textArea1.append(String.format("\n%46s%18s%27s" , ""+detailsPage.getDetailsBlock().getWomenClothings().getPrice()
//                                , ""+detailsPage.getDetailsBlock().getChoosedQuantity(),""+detailsPage.getDetailsBlock().getWomenClothings().getName()+"\n"));
//                        cnt[0]++;

                        boolean inShoppingCart = false;
                        Iterator<Column> columnIterator = shoppingCartPage.iterator();
                        while (columnIterator.hasNext()) {
                            ShoppingColumn shoppingColumn = ((ShoppingColumn) columnIterator.next());
                            if (shoppingColumn.getName().equals(detailsPage.getDetailsBlock().getWomenClothings().getName())) {
                                inShoppingCart = true;
                                shoppingColumn.setChoosedQuantity(shoppingColumn.getChoosedQuantity() + detailsPage.getDetailsBlock().getChoosedQuantity());
                                textArea3.append("\nnow the quantity of " + shoppingColumn.getName() + " in shopping cart is " + shoppingColumn.getChoosedQuantity());
                                reDrawShoppingCartPage();
                            }
                        }

                        if (inShoppingCart) {
                            detailsPage.getDetailsBlock().setChoosedQuantity(0);
                            spinner.setValue((int) 0);
                        } else {

//                            detailsPage.getDetailsBlock().setCheckBoxBounds(40, 20+cnt[0] *32,20,20);
//                            textArea1.add(detailsPage.getDetailsBlock().getCheckBox());
//                            textArea1.append(String.format("\n%46s%18s%27s" , ""+detailsPage.getDetailsBlock().getWomenClothings().getPrice()
//                                    , ""+detailsPage.getDetailsBlock().getChoosedQuantity(),""+detailsPage.getDetailsBlock().getWomenClothings().getName()+"\n"));
//                            cnt[0]++;

                            shoppingCartPage.addShoppingColumn(new ShoppingColumn(detailsPage.getDetailsBlock().getWomenClothings().getName(),
                                    detailsPage.getDetailsBlock().getWomenClothings().getPrice(),
                                    detailsPage.getDetailsBlock().getChoosedQuantity()));
                            reDrawShoppingCartPage();

//                            shoppingCartPage.getShoppingColumn().setCheckBoxBounds(40, 20+cnt[0]*32,20,20);

                            spinner.setValue((int) 0);
                            detailsPage.getDetailsBlock().setChoosedQuantity(0);
                        }
                    }
                }
            }
        });
    }

    /**
     * Show the detail page
     */
    public void showDetailPage() throws IOException {
        DetailsPage detailsPage = ((DetailsPage) pages[2]);
        ShoppingCartPage shoppingCartPage = ((ShoppingCartPage) pages[3]);
        String content;
        int choiceNumber;

        stdOut.print(YELLOW + "there are some" + RED + " hot " + YELLOW + "choices or you can just search other choice\n");
        while (true) {
            try {
                stdOut.println(MAGENTA + "0. other choice");
                for (int i = 2; i < 6; i++) {
                    stdOut.println(String.format(MAGENTA + "%d. %-14s%s", i - 1, ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName(), RED + "HOT"));
                }
                stdOut.print(YELLOW + "choice> ");
                stdOut.flush();
                choiceNumber = Integer.parseInt(stdIn.readLine());

                if (choiceNumber >= 0 && choiceNumber <= 4) {
                    break;
                } else {
                    stdOut.println(RED + "Invalid choice:  " + choiceNumber);
                }
            } catch (NumberFormatException nfe) {
                stdOut.println(RED + nfe);
            }
        }
        boolean isFound;
        while (true) {
            if (choiceNumber == 0) {
                stdOut.println(MAGENTA + "all name:" + NONE + "skirt_1 skirt_2 skirt_3 ... skirt_6  coat_1~6 dress_1~6 pants_1~6 ");
                stdOut.print(YELLOW + "please input the" + MAGENTA + " name " + YELLOW + "of what you want to know detaily\nname> ");
                stdOut.flush();
                content = stdIn.readLine();
            } else {
                content = ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(choiceNumber + 1)).getName();
            }

            isFound = false;
            Iterator<WomenClothing> iter = this.clothingDataBase.iterator();
            while (iter.hasNext()) {
                WomenClothing womenClothing = iter.next();
                if (womenClothing.getName().equals(content)) {
                    detailsPage.setDetailsBlock(new DetailsBlock(womenClothing));
                    isFound = true;
                    break;
                }
            }

            if (isFound) {
                break;
            } else {
                stdOut.println(RED + "nothing found!  try to search like 'skirt_1' 'dress_3' 'coat_2' 'pants_4' \n");
            }
        }

        stdOut.println(NONE + detailsPage.getDetailsBlock().getWomenClothings().toString());
        stdOut.println(String.format("%-30s%s", BLUE + "choosed quantity:", RED + detailsPage.getDetailsBlock().getChoosedQuantity()));
        while (true) {
            try {
                stdOut.print(YELLOW + "1. add quantity      2. reduce quantity\n3. buy it     4. add it to shopping cart\n0. return" + YELLOW + "\nyour choice> ");
                stdOut.flush();
                int functionChoice = Integer.parseInt(stdIn.readLine());
                stdOut.println();
                if (functionChoice >= 0 && functionChoice <= 4) {

                    switch (functionChoice) {
                        case 0: {
                            return;
                        }
                        case 1: {
                            detailsPage.getDetailsBlock().setChoosedQuantity(detailsPage.getDetailsBlock().getChoosedQuantity() + 1);
                            break;
                        }
                        case 2: {
                            if (detailsPage.getDetailsBlock().getChoosedQuantity() == 0) {
                                stdOut.println(RED + "Can not reduce! the choosed quantity is 0!\nYou can input '1' to add the quantity");
                            } else {
                                detailsPage.getDetailsBlock().setChoosedQuantity(detailsPage.getDetailsBlock().getChoosedQuantity() - 1);
                            }
                            break;
                        }
                        case 3: {
                            if (detailsPage.getDetailsBlock().getChoosedQuantity() > 0) {
                                stdOut.println(NONE + "You have buy " + YELLOW + detailsPage.getDetailsBlock().getChoosedQuantity()
                                        + " " + MAGENTA + detailsPage.getDetailsBlock().getWomenClothings().getName()
                                        + NONE + " succesfully!\n");
                                detailsPage.getDetailsBlock().setChoosedQuantity(0);
                            } else {
                                stdOut.println(RED + "Can not buy! the choosed quantity is 0!\nYou can input '1' to add the quantity");
                            }
                            break;
                        }
                        case 4: {
                            if (detailsPage.getDetailsBlock().getChoosedQuantity() > 0) {
                                stdOut.println(NONE + "add " + YELLOW + detailsPage.getDetailsBlock().getChoosedQuantity()
                                        + " " + MAGENTA + detailsPage.getDetailsBlock().getWomenClothings().getName()
                                        + NONE + " to shopping cart succesfully!\n");
                                boolean inShoppingCart = false;
                                Iterator<Column> columnIterator = shoppingCartPage.iterator();
                                while (columnIterator.hasNext()) {
                                    ShoppingColumn shoppingColumn = ((ShoppingColumn) columnIterator.next());
                                    if (shoppingColumn.getName().equals(detailsPage.getDetailsBlock().getWomenClothings().getName())) {
                                        inShoppingCart = true;
                                        shoppingColumn.setChoosedQuantity(shoppingColumn.getChoosedQuantity() + detailsPage.getDetailsBlock().getChoosedQuantity());
                                        stdOut.println(NONE + "add the quantity of " + MAGENTA + shoppingColumn.getName() + NONE + " to " + YELLOW + shoppingColumn.getChoosedQuantity());
                                    }
                                }
                                if (inShoppingCart) {
                                    detailsPage.getDetailsBlock().setChoosedQuantity(0);
                                    break;
                                }

                                shoppingCartPage.addShoppingColumn(new ShoppingColumn(detailsPage.getDetailsBlock().getWomenClothings().getName(),
                                        detailsPage.getDetailsBlock().getWomenClothings().getPrice(),
                                        detailsPage.getDetailsBlock().getChoosedQuantity()));
                                detailsPage.getDetailsBlock().setChoosedQuantity(0);
                            } else {
                                stdOut.println(RED + "Can not add to shoppingcart! the choosed quantity is 0!\nYou can input '1' to add the quantity");
                            }
                            break;
                        }
                    }
                } else {
                    stdOut.println(RED + "Invalid choice:  " + functionChoice);
                }
                stdOut.println(BLUE + "choosed quantity: " + NONE + detailsPage.getDetailsBlock().getChoosedQuantity());

            } catch (NumberFormatException nfe) {
                stdOut.println(RED + nfe);
                stdOut.println(BLUE + "choosed quantity: " + NONE + detailsPage.getDetailsBlock().getChoosedQuantity());
            }
        }
    }

    /**
     * Load all clothings from the database
     *
     * @param fileName save the data of all clothings
     */
    public static ClothingDataBase loadClothingDataBase(String fileName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader fileIn = new BufferedReader(
                new InputStreamReader(fis));

        ClothingDataBase clothingDataBase = new ClothingDataBase();

        String inputLine;
        while ((inputLine = fileIn.readLine()) != null) {
            String[] s = inputLine.split(" ");
            switch (s[0]) {
                case "Skirt": {
                    clothingDataBase.addWomenClothing(new Skirt(s[0], s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]),
                            s[4], s[5], Double.parseDouble(s[6]), Double.parseDouble(s[7]), s[8]));
                    break;
                }
                case "Pants": {
                    clothingDataBase.addWomenClothing(new Pants(s[0], s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]),
                            s[4], s[5], Double.parseDouble(s[6]), Double.parseDouble(s[7]), Double.parseDouble(s[8])));
                    break;
                }
                case "Coat": {
                    clothingDataBase.addWomenClothing(new Coat(s[0], s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]),
                            s[4], s[5], Double.parseDouble(s[6]), Double.parseDouble(s[7]), Double.parseDouble(s[8])));
                    break;
                }
                case "Dress": {
                    clothingDataBase.addWomenClothing(new Dress(s[0], s[1], Double.parseDouble(s[2]), Double.parseDouble(s[3]),
                            s[4], s[5], Double.parseDouble(s[6]), Double.parseDouble(s[7]), s[8],
                            Double.parseDouble(s[9]), Double.parseDouble(s[10]), Double.parseDouble(s[11])));
                    break;
                }
                default:
            }
        }
//        clothingDataBase.addWomenClothing(new Skirt("Skirt", "skirt_1", 121.0, 5.0, "L", "White", 30.0, 30.0, "Short"));
//        clothingDataBase.addWomenClothing(new Skirt("Skirt", "skirt_2", 122.0, 5.0, "L", "White", 30.0, 30.0, "Short"));
//        clothingDataBase.addWomenClothing(new Skirt("Skirt", "skirt_3", 123.0, 5.0, "L", "White", 30.0, 30.0, "Short"));
//        clothingDataBase.addWomenClothing(new Skirt("Skirt", "skirt_4", 124.0, 5.0, "L", "White", 30.0, 30.0, "Short"));
//        clothingDataBase.addWomenClothing(new Skirt("Skirt", "skirt_5", 125.0, 5.0, "L", "White", 30.0, 30.0, "Short"));
//        clothingDataBase.addWomenClothing(new Skirt("Skirt", "skirt_6", 126.0, 5.0, "L", "White", 30.0, 30.0, "Short"));

//        clothingDataBase.addWomenClothing(new Pants("Pants", "pants_1", 141.0, 8.0, "XL", "Black", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Pants("Pants", "pants_2", 142.0, 8.0, "XL", "Black", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Pants("Pants", "pants_3", 143.0, 8.0, "XL", "Black", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Pants("Pants", "pants_4", 144.0, 8.0, "XL", "Black", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Pants("Pants", "pants_5", 145.0, 8.0, "XL", "Black", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Pants("Pants", "pants_6", 146.0, 8.0, "XL", "Black", 30.0, 30.0, 30.0));

//        clothingDataBase.addWomenClothing(new Coat("Coat", "coat_1", 161.0, 6.0, "M", "Blue", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Coat("Coat", "coat_2", 162.0, 6.0, "M", "Blue", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Coat("Coat", "coat_3", 163.0, 6.0, "M", "Blue", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Coat("Coat", "coat_4", 164.0, 6.0, "M", "Blue", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Coat("Coat", "coat_5", 165.0, 6.0, "M", "Blue", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Coat("Coat", "coat_6", 166.0, 6.0, "M", "Blue", 30.0, 30.0, 30.0));

//        clothingDataBase.addWomenClothing(new Dress("Dress", "dress_1", 181.0, 9.0, "XXL", "Red", 30.0, 30.0, "long", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Dress("Dress", "dress_2", 182.0, 9.0, "XXL", "Red", 30.0, 30.0, "long", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Dress("Dress", "dress_3", 183.0, 9.0, "XXL", "Red", 30.0, 30.0, "long", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Dress("Dress", "dress_4", 184.0, 9.0, "XXL", "Red", 30.0, 30.0, "long", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Dress("Dress", "dress_5", 185.0, 9.0, "XXL", "Red", 30.0, 30.0, "long", 30.0, 30.0, 30.0));
//        clothingDataBase.addWomenClothing(new Dress("Dress", "dress_6", 186.0, 9.0, "XXL", "Red", 30.0, 30.0, "long", 30.0, 30.0, 30.0));

        return clothingDataBase;
    }

    /**
     * initial all pages
     */
    public void initialPages() {
        pages[0] = new ShowcasePage("ShowcasePage", "White");
        pages[1] = new SearchPage("SearchPage", "Yellow");
        pages[2] = new DetailsPage("Detailspage", "Green");
        pages[3] = new ShoppingCartPage("ShoppingCartPage", "Purple");

        initialShowingPage();
        ((SearchPage) pages[1]).setSearchBar(new SearchBar());
    }

    /**
     * Using random number to initial the showing page
     */
    public void initialShowingPage() {
        int size = clothingDataBase.getNumberOfWomenClothings();
        boolean[] isChoosed = new boolean[size];
        Arrays.fill(isChoosed, false);
        int[] choosedNumber = new int[6];
        Random r = new Random(System.nanoTime());
        int cnt = 0;

        while (cnt < 6) {
            int randInt = r.nextInt(size);
            if (!isChoosed[randInt]) {
                choosedNumber[cnt] = randInt;
                isChoosed[randInt] = true;
                cnt++;
            }
        }
        ((ShowcasePage) pages[0]).setShowingColumn(0, new ShowingColumn(
                clothingDataBase.getWomenClothing(choosedNumber[0]).getName(),
                clothingDataBase.getWomenClothing(choosedNumber[0]).getPrice(),
                "New"));
        ((ShowcasePage) pages[0]).setShowingColumn(1, new ShowingColumn(
                clothingDataBase.getWomenClothing(choosedNumber[1]).getName(),
                clothingDataBase.getWomenClothing(choosedNumber[1]).getPrice(),
                "New"));
        ((ShowcasePage) pages[0]).setShowingColumn(2, new ShowingColumn(
                clothingDataBase.getWomenClothing(choosedNumber[2]).getName(),
                clothingDataBase.getWomenClothing(choosedNumber[2]).getPrice(),
                "Hot"));
        ((ShowcasePage) pages[0]).setShowingColumn(3, new ShowingColumn(
                clothingDataBase.getWomenClothing(choosedNumber[3]).getName(),
                clothingDataBase.getWomenClothing(choosedNumber[3]).getPrice(),
                "Hot"));
        ((ShowcasePage) pages[0]).setShowingColumn(4, new ShowingColumn(
                clothingDataBase.getWomenClothing(choosedNumber[4]).getName(),
                clothingDataBase.getWomenClothing(choosedNumber[4]).getPrice(),
                "Hot"));
        ((ShowcasePage) pages[0]).setShowingColumn(5, new ShowingColumn(
                clothingDataBase.getWomenClothing(choosedNumber[5]).getName(),
                clothingDataBase.getWomenClothing(choosedNumber[5]).getPrice(),
                "Hot"));

    }

    public void SearchPageGUI() {
        SearchPage searchPage = ((SearchPage) pages[1]);
        JButton button = shoppingGUI.getSearchButton();
        JTextField textField1 = shoppingGUI.getTextField1();
        JTextArea textArea5 = shoppingGUI.getTextArea5();
        JTextArea textArea2 = shoppingGUI.getTextArea2();
        textArea2.setEditable(false);
        textArea5.setEditable(false);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String content = textField1.getText();
                boolean isSearched = false;

//                searchPage.getSearchBar().setSearchContent(content);

                Iterator<WomenClothing> iter = clothingDataBase.iterator();
                while (iter.hasNext()) {
                    WomenClothing womenClothing = iter.next();
                    if (womenClothing.getName().equals(content) || womenClothing.getType().equals(content)) {
                        searchPage.addSearchedColumn(new SearchedColumn(womenClothing.getName(),
                                womenClothing.getPrice(),
                                womenClothing.getType()));
                        isSearched = true;
                    }
                }
                textArea2.append(content + "\n");
                if (!isSearched) {
                    textArea5.setText("Nothing found!\ntry to search like 'Skirt' 'Coat' 'Dress' 'Pants' or 'skirt_1' 'dress_3'");
                    return;
                } else {
                    textArea5.setText(String.format("%9s%15s%14s\n", "Name", "Price", "Type"));
                    Iterator<Column> columnIter = searchPage.iterator();
                    while (columnIter.hasNext()) {
                        SearchedColumn searchedColumn = ((SearchedColumn) columnIter.next());
                        textArea5.append(searchedColumn.toString() + "\n");
                    }
                    searchPage.clearColumns();
                }
            }

        });
    }

    /**
     * Show the search page
     */
    public void showSearchPage() throws IOException {
        SearchPage searchPage = ((SearchPage) pages[1]);
        stdOut.println(MAGENTA + "all type:" + NONE + "Skirt  Coat  Dress  Pants");
        stdOut.println(MAGENTA + "all name:" + NONE + "skirt_1 skirt_2 skirt_3 ... skirt_6  coat_1~6 dress_1~6 pants_1~6 ");
        while (true) {
            stdOut.print(YELLOW + "please input the" + MAGENTA + " name " + YELLOW + "or" + MAGENTA + " type " + YELLOW + "of what you want to search\nname or type> ");
            stdOut.flush();
            String content = stdIn.readLine();

            boolean isSearched = false;
            searchPage.getSearchBar().setSearchContent(content);
            Iterator<WomenClothing> iter = this.clothingDataBase.iterator();
            while (iter.hasNext()) {
                WomenClothing womenClothing = iter.next();
                if (womenClothing.getName().equals(content) || womenClothing.getType().equals(content)) {
                    searchPage.addSearchedColumn(new SearchedColumn(womenClothing.getName(),
                            womenClothing.getPrice(),
                            womenClothing.getType()));
                    isSearched = true;
                }
            }
            if (!isSearched) {
                stdOut.println(RED + "nothing found!  try to search like 'Skirt' 'Coat' 'Dress' 'Pants' or 'skirt_1' 'dress_3'\n");
                searchPage.getSearchBar().addToSearchHistory(RED + content + " (error name or type)");
            } else {
                searchPage.getSearchBar().addToSearchHistory(NONE + content);
                break;
            }
        }

        stdOut.println(BLUE + "\n      Name      " + "     Price     " + "     Type");
        Iterator<Column> columnIter = searchPage.iterator();
        while (columnIter.hasNext()) {
            SearchedColumn searchedColumn = ((SearchedColumn) columnIter.next());
            stdOut.println(NONE + searchedColumn.toString());
        }
        searchPage.clearColumns();

        stdOut.println(YELLOW + "\nsearched history");
        Iterator<String> searchIter = searchPage.getSearchBar().iterator();
        while (searchIter.hasNext()) {
            stdOut.println(NONE + "    " + searchIter.next());
        }
    }

    /**
     * @param textArea4
     */
    public void showCasePageGUI() {
        JTextArea textArea4 = shoppingGUI.getTextArea4();

        textArea4.setEditable(false);
        Font font = new Font("Microsoft Yahei Mono", Font.PLAIN, 12);
        textArea4.setFont(font);
        textArea4.setForeground(Color.BLUE);
        textArea4.setText(String.format("%30s%40s%40s", "Status", "Price", "Name\n"));

        for (int i = 0; i < 2; i++) {
//            if (((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName().length() == 7){
//                stdOut.println(((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName());
            textArea4.append(String.format("\n%30s%40s%41s", ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()
                    , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
                    , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()));
//
//            }else {
//                textArea4.append(String.format("\n%30s%40s%38s", ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()
//                        , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
//                        , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()));
//            }
        }
        for (int i = 2; i < 6; i++) {
//            if (((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName().length() == 7) {
//                stdOut.println(((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName());
//                textArea4.append(String.format("\n%30s%40s%38s", ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()
//                        , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
//                        , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()));
//            } else {
//                textArea4.append(String.format("\n%30s%40s%38s", ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()
//                        , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
//                        , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()));
//            }
            textArea4.append(String.format("\n%30s%41s%41s", ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()
                    , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
                    , ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()));
        }
    }


    /**
     * show the showcase page
     */
    public void showShowCasePage() {
        stdOut.println(BLUE + "      Name      " + "     Price     " + "     Status");
        for (int i = 0; i < 2; i++) {
            stdOut.println(String.format("%15s%20s%19s", NONE + ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()
                    , NONE + ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
                    , GREEN + ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()));
        }
        for (int i = 2; i < 6; i++) {
            stdOut.println(String.format("%15s%20s%19s", NONE + ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getName()
                    , NONE + ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getPrice()
                    , RED + ((ShowingColumn) ((ShowcasePage) pages[0]).getColumn(i)).getStatus()));
        }
    }

}
