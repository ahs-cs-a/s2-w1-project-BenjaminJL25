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
public class DodgeTheNight extends JPanel implements MouseListener,MouseMotionListener{
    public static void main(String[] args) {
        new DodgeTheNight();
    }
    ArrayList<Creeps> allCreeps = new ArrayList<>();
    ArrayList<Coin> Coins = new ArrayList<>();
    ArrayList<water> waves = new ArrayList<>();
    ArrayList<extraLife> extraLives = new ArrayList<>();

    private JFrame myFrame;
    private int lives = 3;
    private String playerName;
    int points = 0;
    int windowWidth = 700;
    int windowHeight = 700;
    private int maxBaddies;
    private int playerX = 343;
    private int playerY = 320;
    private Instant start;
    public int score = 0;
    private boolean started = false;
    private int curDif = 1;
    private int cX;
    private int cY;
    private LeaderBored l = new LeaderBored();
    public void removeCursor(){
        byte[]imageByte=new byte[0];
        Cursor myCursor;
        Point myPoint=new Point(0,0);
        Image cursorImage=Toolkit.getDefaultToolkit().createImage(imageByte);
        myFrame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(cursorImage,myPoint,"cursor"));
    }
    // https://www.sanfoundry.com/java-program-create-transparent-cursor/
    public void center() {
        try {
            Robot robot = new Robot();
            // Set the cursor's position to the center of the frame
            robot.mouseMove(myFrame.getX() + myFrame.getWidth() / 2, myFrame.getY() + myFrame.getHeight() / 2);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
    //https://www.geeksforgeeks.org/robot-class-java-awt/
    private void delay(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }



    // I didn't write the functions above this because it's stuff I don't quite understand or just never uesed or why reinvent the wheel
    public int timeSec(){
        Instant end = Instant.now();
        Duration elapsedTime = Duration.between(start, end);
        return (int)elapsedTime.getSeconds();
    }
    public void updateGameState() {
        if (lives < 1)
            gameQuit();
        List<Integer> todie = new ArrayList<>();
        int i = 0;
        for (Creeps a : allCreeps) {
            if ((a.getStartx() + (int) a.getTotalx() > 700 + a.getDiameter() || a.getStartx() + (int) a.getTotalx() < -a.getDiameter()) || (a.getStarty() + (int) a.getTotaly() > 700 + a.getDiameter() || a.getStarty() + (int) a.getTotaly() < -a.getDiameter())) {
                todie.add(0, i);
            }
            i++;
        }
        for (int l : todie) {
            allCreeps.remove(l);
            allCreeps.add(new Creeps());
        }
        if (started) {
            maxBaddies = 5 + timeSec() / 20;
            curDif = 1 + timeSec() / 20;
        }
    }
    public void moveCreeps(){
        for (Creeps c: allCreeps){
            c.setTotalx(c.getTotalx() + (c.getFinalx() * 1.0 / 500 - c.getStartx() * 1.0 / 500));
            c.setTotaly(c.getTotaly() + (c.getFinaly() * 1.0 / 500 - c.getStarty() * 1.0 / 500));
        }
    }

    public void checkTouch(){
        int i = 0;
        int remove = -1;
        for (Creeps c: allCreeps){
            if (c.getDiameter() / 2 + 15 > Math.sqrt((c.getStartx()+c.getTotalx()-this.playerX)*(c.getStartx()+c.getTotalx()-this.playerX)+(c.getStarty()+c.getTotaly()-this.playerY)*(c.getStarty()+c.getTotaly()-this.playerY))){
                this.lives--;
                remove = i;
            }
            i++;
        }
        if (remove != -1)
            allCreeps.remove(remove);
        int cRemove = -1;
        int cI = 0;
        for (Coin C: Coins){
            if (25 > Math.sqrt((C.getX() - this.playerX) * (C.getX() - this.playerX) + (C.getY() - this.playerY) * (C.getY() - this.playerY)))
                cRemove = cI;
            cI++;
        }
        if (cRemove != -1) {
            Coins.remove(cRemove);
            score++;
        }
        int wRemove = -1;
        int wI = 0;
//        int drowningTime = 0;
        int drownedToDeath = 0;
        for (water d: waves){
            if (23 > Math.sqrt((d.getX() - this.playerX) * (d.getX() - this.playerX) + (d.getY() - this.playerY) * (d.getY() - this.playerY))){
                drownedToDeath++;
            }
            if(drownedToDeath > 5){
                this.lives--;
                wRemove = wI;
            }
            wI++;
            int eRemove = -1;
            int eI = 0;
            for (extraLife E: extraLives){
                if (25 > Math.sqrt((E.getX() - this.playerX) * (E.getX() - this.playerX) + (E.getY() - this.playerY) * (E.getY() - this.playerY)))
                    eRemove = eI;
                eI++;
            }
            if (eRemove != -1) {
                Coins.remove(eRemove);
                lives++;
            }
        }
        if (wRemove != -1)
            waves.remove(wRemove);
    }
    // Logic functions ^^^^
    public DodgeTheNight() {
        myFrame = new JFrame("Dodge!");
        myFrame.add(this);
        myFrame.setSize(windowWidth, windowHeight);
        myFrame.setVisible(true);
        addMouseListener(this);
        initializeGame();
        //removeCursor();
        addMouseMotionListener(this);
    }
    public void initializeGame() {
        while(!this.started){
            delay(1000);
        }
        this.start = Instant.now();
        maxBaddies = 5;
        this.lives = 3;
        this.score = 0;
    }
    public void paintComponent(Graphics g){
        while (Coins.size() < 3)
            Coins.add(new Coin());
        while(waves.size() < curDif / 2) //adds water
            waves.add(new water());
//        while(curDif % 3 == 2)
//            extraLives.add(new extraLife());
        while (allCreeps.size() < maxBaddies)
            allCreeps.add(new Creeps());
        updateGameState();
        updateBackdrop(g);
        updateSprites(g);
        updateDangerZones(g);
        updateHealth(g);
        updateCreeps(g);
        updatePlayer(g);
        updateWeather(g);
        updateEffects(g);
        updateHUD(g);
    }

    public void updateBackdrop(Graphics G){
        Color myColour = new Color(0, 0, 0);
        G.setColor(myColour);
        G.fillRect(0,0, 700,700);
    }
    public void updateSprites(Graphics G){
        for (Coin n: Coins){
            G.setColor(Color.yellow);
            G.fillRect(n.getX(),n.getY(),20,20);
        }
    }
    public void updateDangerZones(Graphics G){
        for (water n: waves){
            G.setColor(Color.blue);
            G.fillRect(n.getX(),n.getY(),25,15);
        }
    }
    public void updateHealth(Graphics G){
        for (extraLife n: extraLives){
            G.setColor(Color.cyan);
            G.fillOval(n.getX(), n.getY(), 25, 20);
        }
    }
    public void updateCreeps(Graphics G){
        moveCreeps();
        for (Creeps a: allCreeps){
//          Color myColour = new Color(125, 181, 255);
            G.setColor(Color.green);
            G.fillOval(a.getStartx() + (int)a.getTotalx() - a.getDiameter()/2 , a.getStarty() + (int)a.getTotaly() - a.getDiameter()/2 , a.getDiameter(), a.getDiameter());
        }
    }
    public void updatePlayer(Graphics G){
        G.setColor(Color.white);
        G.fillOval(this.playerX - 15, this.playerY - 15, 30, 30);
    }
    public void updateWeather(Graphics G){
        Color mist = new Color(0xE61A1B1C, true);
        G.setColor(mist);
        G.fillRect(0,0,700,700);

    }
    public void updateEffects(Graphics G){

    }
    public void updateHUD(Graphics G){
        if (this.started) {
            G.setColor(Color.white);
            G.drawString("Score " + Integer.toString(this.score), 350, 50);
            G.drawString("Current Difficulty " + this.curDif, 320, 70);
            int i = 0;
            while (i < this.lives){
                G.setColor(Color.red);
                int x = 320;
                int y = 80;
                int[] xPoints = {0 + x + i * 35,6 + x + i * 35,12 + x + i * 35,18 + x + i * 35,24 + x + i * 35,12 + x + i * 35};
                int[] yPoints = {6 + y,0 + y,6 + y,0 + y,6 + y,16 + y};
                G.drawPolygon(xPoints, yPoints, 6);
                i++;
            }
            for (water n: waves){
                G.setColor(Color.BLUE);
                G.drawOval(n.getX(),n.getY(),25, 15);
            }
            for (extraLife n: extraLives){
                G.setColor(Color.yellow);
//                G.drawArc(n.getX()-12, n.getY()+13, n.getX()+13, n.getY()+13);
            }
            for (Coin n: Coins){
                G.setColor(Color.yellow);
                G.drawRect(n.getX(),n.getY(),20,20);
            }
        } else {
            G.setColor(Color.black);
            G.fillRect(0,0, 700, 700);
            G.setColor(Color.white);
            G.drawString("collect yellow coins" , 20, 30);
            G.drawString("Everything else is hostile", 20, 100);
            G.drawString("Click to Start", 300, 500);
            G.setColor(Color.yellow);
            G.fillRect(160, 10, 20, 20);
        }
    }
    public void mouseClicked(MouseEvent event){
        this.started = true;
        center();
    }
    public void mouseMoved(MouseEvent event){
        this.playerX += event.getX() - (cX);
        this.playerY += event.getY() - (cY);
        System.out.println(event.getX());
        System.out.println((myFrame.getWidth())/2);
        center();
        System.out.println(event.getX());
        if (playerY > 640)
            playerY = 640;
        if (playerY < 0)
            playerY = 0;
        if (playerX > 670)
            playerX = 670;
        if (playerX < 0)
            playerX = 0;
        checkTouch();
        repaint();
    }
    public void mouseDragged(MouseEvent e){ }
    public void mouseReleased (MouseEvent event) {
        cX = event.getX();
        cY = event.getY();
    }
    public void mousePressed (MouseEvent event) {
        center();
    }
    public void mouseEntered (MouseEvent event) {
        center();
    }
    public void mouseExited (MouseEvent event) {
        center();

    }
    public void gameQuit(){
        myFrame.hide();
        l.leadershow(this.score);
        //add s + this.score to file then parse file to make leaderboard :D////////////////////////////////////////////*8
    }
}
