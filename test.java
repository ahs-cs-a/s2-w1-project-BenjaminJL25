import java.awt.*;
import javax.swing.*;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import java.awt.event.*;
import java.awt.Robot;
import java.awt.AWTException;
import java.time.Duration;
import java.time.Instant;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class test {
    public static void main(String[] args) {
        test t = new test();
        t.writeToTextFile("wjei123", "localLeaderboard.txt");
    }
    public static void writeToTextFile(String content, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(content);
            writer.newLine();
            System.out.println("Content has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

}
