import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        //窗口位置
        jFrame.setBounds(10,10,900,720);
        //窗口是否可以改变大小
        jFrame.setResizable(false);
        //点击关闭窗口做的事情:窗口关闭
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

        //加入画布
        SnakePanel snakePanel = new SnakePanel();
        jFrame.add(snakePanel);

        //让窗口展现出来，因为默认是隐藏的
        jFrame.setVisible(true);
    }
}
