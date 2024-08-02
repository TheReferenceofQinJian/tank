package tankgame;

import java.util.Vector;

//敌人的坦克
public class EnemyTank extends Tank implements Runnable {
    //敌方坦克生命
    Boolean islive = true;

    Vector<Shot> shots = new Vector<>();


    public EnemyTank(int x, int y) {
        super(x, y);
    }

    @Override
    public void run() {

        while (true) {
            switch (getDirect()) {
                case 0:  //向上
                    //让坦克保持一个方向走30步
                    for (int i = 0; i < 30; i++){
                        if (getY() > 0) { //坦克没有到边界 继续往上走
                            moveUp();
                            //休眠50毫秒
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;
                case 1: //向右
                    for (int i = 0; i < 30; i++){
                        if (getX()+60 < 1000) { //坦克没有到边界 继续往右走
                            moveRiht();
                            //休眠50毫秒
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;

                case 2: //向下
                    for (int i = 0; i < 30; i++) {
                        if (getY()+60 < 750) {//坦克没有到边界 继续往下走
                            moveDown();
                            //休眠50毫秒
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    break;

                case 3: //向左
                    for (int i = 0; i < 30; i++) {
                        if (getX() > 0){  //坦克没有到边界 继续往左走
                            moveLeft();
                            //休眠50毫秒
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }

                    }
                    break;
            }


            //随机的改变坦克方向
            setDirect((int)(Math.random()*4));

            // 如果敌方坦克消亡 结束线程
            if (!islive) {
                break;
            }
        }
    }
}

