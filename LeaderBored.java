import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.awt.*;
import java.io.File;
public class LeaderBored {
    private String name;
    private int score;
    ArrayList<LeaderBored> leadb = new ArrayList<>();
    ArrayList<LeaderBored> reordered = new ArrayList<>();
    public LeaderBored(){}

    public LeaderBored(String l, int p) {
        this.name = l;
        this.score = p;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }
    //https://www.programiz.com/java-programming/bufferedwriter
    public static void writeToTextFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
    //
    public void leadershow(int scre){
//        I just copied the format from like virtual pet
        String s = (String) JOptionPane.showInputDialog(
                new JFrame(),
                "Enter your name",
                "Your Name",
                JOptionPane.PLAIN_MESSAGE
        );
        //
        writeToTextFile(s + "F2♣☻36☺♦58•9421s¡♦7♣" + scre, "localLeaderboard");
        readfile();
        sortArrayList();
        // https://www.javatpoint.com/java-jlist
        JFrame frame = new JFrame("Scrollable List Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        DefaultListModel<String> listModel = new DefaultListModel<>();
        int i = 1;
        for (LeaderBored item : reordered) {
            listModel.addElement(i + " | " +item.getName() + " | " + item.getScore() + " points");
            i++;
        }

        JList<String> jList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(jList);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        //
        frame.setVisible(true);
    }
    public void sortArrayList() {
        while (!leadb.isEmpty()) {
            LeaderBored f = new LeaderBored();
            int i = 0;
            int remove = 0;
            for (LeaderBored l : leadb) {
                if (l.getScore() > f.getScore()){
                    remove = i;
                    f = l;
                }
                i++;
            }
            leadb.remove(remove);
            reordered.add(f);
        }
    }
    public void readfile(){
        try {
            File f = new File("localLeaderboard");
            Scanner scan = new Scanner(f);
            while (scan.hasNextLine()){
                String Data = scan.nextLine();
                int i = Data.indexOf("F2♣☻36☺♦58•9421s¡♦7♣");
                int p = Integer.parseInt(Data.substring(i+20));
                leadb.add(new LeaderBored(Data.substring(0,i), p));

//                int i = Data.indexOf(",");
//                int n = 0;
//                if (Data.contains(", "))
//                    n = Data.substring(Data.indexOf(", ") + 1).indexOf(",") + Data.indexOf(", ") + 1;
//                else
//                    n = Data.substring(i+1).indexOf(",") + i+1;
//
//                String name = Data.substring(0, i);
//                String country = Data.substring(i+1, n);
//                double Temp = Double.parseDouble(Data.substring(n+1));
//                cities.add(new City(country, name, Temp));
            }
            scan.close();
        }catch (Exception e){
            System.out.println("error");
            e.printStackTrace();
        }
    }

}
