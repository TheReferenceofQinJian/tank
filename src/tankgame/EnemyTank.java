package tankgame;

import java.util.Vector;

//敌人的坦克
public class EnemyTank extends Tank{
    //敌方坦克生命
    Boolean islive = true;

    Vector<Shot> shots = new Vector<>();


    public EnemyTank(int x, int y) {
        super(x, y);
    }
}
