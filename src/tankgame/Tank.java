package tankgame;

public class Tank {

    private int x;  //坦克的横坐标
    private int y;  //坦克的中坐标
    private int direct;  //0上 1右 2下 3左
    private int speed = 1; //坦克行驶速度

    //获取速度
    public int getSpeed() {
        return speed;
    }

    //设置速度
    public void setSpeed(int speed) {
        this.speed = speed;
    }


    public  Tank(int x, int y) {
        this.x = x;
        this.y = y;

    }
    //向上移动
    public void moveUp(){
        y -= getSpeed();
    };

    //向右移动
    public void moveRiht(){
        x += getSpeed();
    };

    //向下移动
    public void moveDown(){
        y += getSpeed();
    };

    //向左移动
    public void moveLeft(){
        x -= getSpeed();
    };
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}
