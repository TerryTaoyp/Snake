import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
/**
 * Created by Taoyongpan on 2017/3/23.
 */
public class SnakePanel extends JPanel implements KeyListener,ActionListener{

    ImageIcon body = new ImageIcon("img/body.png");
    ImageIcon food = new ImageIcon("img/food.png");
    ImageIcon title = new ImageIcon("img/title.jpg");
    ImageIcon up = new ImageIcon("img/up.png");
    ImageIcon down = new ImageIcon("img/down.png");
    ImageIcon left = new ImageIcon("img/left.png");
    ImageIcon right = new ImageIcon("img/right.png");

    int[] snakex = new int[750];
    int[] snakey = new int[750];
    //随机产生食物
    Random random = new Random();
    int foodx = random.nextInt(34)*25+25;
    int foody = random.nextInt(24)*25+75;
    //贪吃蛇长度
    int len = 3;
    //得分
    int score = 0;
    //贪吃蛇速度
    int speed = 0;
    //贪吃蛇方向
    String fangxiang = "R";//R向右，L向左，U向上，D向下

    //默认游戏为不开始
    boolean isStarted = false;

    //判断游戏是否结束
    boolean isFailed = false;

    Timer timer = new Timer(100,this);
    //重新构造
    public SnakePanel(){
        //获得焦点
        this.setFocusable(true);
        //添加键盘监听
        this.addKeyListener(this);
        setup();
        timer.start();
    }

    public void paint(Graphics g){
        //调用paint方法
        super.paint(g);
        this.setBackground(Color.WHITE);
        title.paintIcon(this,g,25,11);
        //画一个方框
        g.fillRect(25,75,850,600);
        //画蛇头
        if(fangxiang.equals("R")){
            right.paintIcon(this,g,snakex[0],snakey[0]);
        }else if(fangxiang.equals("L")){
            left.paintIcon(this,g,snakex[0],snakey[0]);
        }else if(fangxiang.equals("U")){
            up.paintIcon(this,g,snakex[0],snakey[0]);
        }else if(fangxiang.equals("D")){
            down.paintIcon(this,g,snakex[0],snakey[0]);
        }
        //画蛇身体
        for(int i = 1; i < len; i++){
            body.paintIcon(this,g,snakex[i],snakey[i]);
        }
        //设置开始的提示语句
        if(!isStarted){
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",Font.BOLD,30));
            g.drawString("Press Space to Start/Stop",300,300);
        }
        //设置失败时候的提示语句
        if (isFailed){
            g.setColor(Color.WHITE);
            g.setFont(new Font("arial",Font.BOLD,30));
            g.drawString("Game over,Press Space to ReStart",300,300);
        }
        //添加食物
        food.paintIcon(this,g,foodx,foody);

        g.setColor(Color.WHITE);
        g.setFont(new Font("arial",Font.PLAIN,20));
        g.drawString("Score:"+score,750,30);
        g.drawString("Length:"+len,750,50);

    }
    //初始化蛇
    public void setup(){

        isFailed = false;
        isStarted = false;
        len = 3;
        //默认方向为R
        fangxiang = "R";
        snakex[0] = 100;
        snakey[0] = 100;
        snakex[1] = 75;
        snakey[1] = 100;
        snakex[2] = 50;
        snakey[2] = 100;
    }

    //在源组件上按下一个键然后释放该键后被调用
    @Override
    public void keyTyped(KeyEvent e) {

    }
    //在源组件上按下一个键后被调用
    @Override
    public void keyReleased(KeyEvent e) {

    }

    // 在源组件上释放一个键后被调用
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //敲空格键
        /**
         * VK_HOME         Home键                          VK_CONTROL                  控制键
           VK_END            End键                         VK_SHIFT                       shift键
           VK_PGUP          page up键                      VK_BACK_SPACE             退格键
           VK_PGDN          page down键                    VK_CAPS_LOCK              大小写锁定键
           VK_UP              上箭头                        VK_NUM_LOCK                小键盘锁定键
           VK_DOWN         下箭头                           VK_ENTER                      回车键
           VK_LEFT           左箭头                         VK_UNDEFINED                未知键
           VK_RIGHT          右箭头                         VK_F1--VK_F12                F1 -- F12
           VK_ESCAPE       Esc键                            VK_0 --VK_9                    0 --- 9
           VK_TAB             Tab键                         VK_A --VK_Z                    A----Z
         */
        if(keyCode == KeyEvent.VK_SPACE){
            if (isFailed)
            {
                setup();
            }else {
                isStarted = !isStarted;
            }
            repaint();
        }else if (keyCode == KeyEvent.VK_UP && fangxiang != "D"){
            fangxiang = "U";
        }else if (keyCode == KeyEvent.VK_DOWN&& fangxiang != "U"){
            fangxiang = "D";
        }else if (keyCode == KeyEvent.VK_LEFT&& fangxiang != "R"){
            fangxiang = "L";
        }else if (keyCode == KeyEvent.VK_RIGHT&& fangxiang != "L"){
            fangxiang = "R";
        }
    }

    //就像一个闹钟一样

    /**
     * 这是一个事件监听器，可以处理类似单击鼠标时触发的事件
     * ActionEvent就是一个事件类，传入的e就是该事件的对象
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //再定义一个闹钟
        timer.start();
        //移动数据
        if(isStarted&&!isFailed){
            //移动身体
            for (int i = len ; i > 0 ; i--){
                snakex[i] = snakex[i-1];
                snakey[i] = snakey[i-1];

            }
            //移动头
            if(fangxiang.equals("R")){
                snakex[0] = snakex[0] + 25;
                if (snakex[0] >850){
                    isFailed = true;
                    //snakex[0] = 25;
                }
            }else if(fangxiang.equals("L")){
                snakex[0] = snakex[0] - 25;
                if (snakex[0] < 25){
                    //snakex[0] = 850;
                    isFailed = true;
                }
            }else if(fangxiang.equals("U")){
                snakey[0] = snakey[0] - 25;
                if (snakey[0] <75 ){
                    //snakey[0] = 650;
                    isFailed = true;
                }
            }else if(fangxiang.equals("D")){
                snakey[0] = snakey[0] + 25;
                if(snakey[0] > 650){
                    //snakey[0] = 75;
                    isFailed = true;
                }
            }
            if(snakex[0] == foodx&&snakey[0] == foody){
                len++;
                score++;
                foodx = random.nextInt(34)*25+25;
                foody = random.nextInt(24)*25+75;
            }
            for (int i= 1;i<len;i++){
                if (snakex[0] == snakex[i] && snakey[0] == snakey[i]) {
                    isFailed = true;
                }
            }
        }
        //repaint()
        repaint();
    }
}
