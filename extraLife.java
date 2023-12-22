public class extraLife {
    private int x;
    private int y;
    public extraLife() {
        this.y = (int)(Math.random()*600) + 40;
        this.x = (int)(Math.random()*600) + 40;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
