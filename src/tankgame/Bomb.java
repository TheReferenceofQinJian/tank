package tankgame;

public class Bomb {
    int x; //炸弹横坐标
    int y; //炸弹纵坐标
    int life = 9;
    boolean isLive = true; //炸弹是否存活标识

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值方法
    public void lifeDown(){
        if (life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
