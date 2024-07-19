package tankgame;

//子弹管理
public class Shot implements Runnable {
    private int x;  //子弹横坐标
    private int y;  //子弹纵坐标
    private int speed = 10;  //子弹运行速度
    private int direct; //子弹运行方向
    boolean islive = true;

    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {

        while (true) {
            //休眠50毫秒
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            switch (direct) {
                case 0: //子单向上
                    y -= speed;
                    break;
                case 1: //子弹向右
                    x += speed;
                    break;
                case 2: //子弹向下
                    y += speed;
                    break;
                case 3: //子弹向左
                    x -= speed;
                    break;
            }
            System.out.println("子弹坐标：" + x + "," + y);
            if (!(x > 0 && x <= 1000 && y > 0 && y <= 750)) {
                islive = false;
                break;
            }


        }


    }

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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}
