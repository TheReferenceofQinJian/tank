package tankgame;

import javax.swing.*;

public class TankGame extends JFrame  {

    //定义面板
    MyPanel mp = null;

    public static void main(String[] args) {

        TankGame tk = new TankGame();

    }

    public TankGame() {
        mp = new MyPanel();
        this.add(mp);  //把面板添加到画框上（游戏绘制区域）
        this.setSize(1000, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
