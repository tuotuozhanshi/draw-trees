package draw_tree;

import util.AntiColor;
import util.ScreenImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class DrawTree {
    int WIDTH = 1024;
    int HEIGHT = 1024;
    DrawNode root = null;

    void function(DrawNode dn,String path) throws IOException {
        JFrame jf = new JFrame();
        jf.setTitle("树");
        jf.setSize(WIDTH, HEIGHT);
        MyPanel mp = new MyPanel();
        //设置标号颜色
        Color bgColor = new Color(255,255,255);
        mp.setBackground(bgColor);
        setX(dn);
        mp.root = dn;
        mp.setSize(1024,1024);

        //jf.add(mp);
        //jf.setVisible(true);
        BufferedImage bi = ScreenImage.createImage(mp);
        AntiColor.antiColor(bi);
        ScreenImage.writeImage(bi, path);
    }

    //递归函数，设置树中每个节点的selfX
    void setX(DrawNode root){
        //设置子结点的beginX和endX
        int gap = (root.endX-root.beginX)/(root.sonNodeNum+1);
        for(int i=0;i<root.sonNodeNum;i++){
            root.selfX = root.beginX+(root.endX-root.beginX)/2;
            root.sonNode[i].selfX = root.beginX+(i+1)*gap;
            root.sonNode[i].beginX = root.sonNode[i].selfX-gap/2;
            root.sonNode[i].endX = root.sonNode[i].selfX + gap/2;
            root.sonNode[i].parentX = root.selfX;
            if(root.sonNode[i]!= null){
                setX(root.sonNode[i]);
            }
        }
    }
}


class MyPanel extends JPanel{
    DrawNode root = null;

    private static final long serialVersionUID = 1L;

    public void paint(Graphics g){

        //设置标号大小
        Font font = new Font("Verdana", Font.BOLD, 20);
        g.setFont(font);

        Color bgColor = new Color(255,255,255);
        g.clearRect(0,0,1024,1024);
        Color lineColor = new Color(0,0,0);
        //g.setColor(lineColor);
        //调用paint获得组件JPanel的画笔，以组件为画板
        DrawNode tmp = root;
        int x = 0;
        int num = 0;
        if(tmp != null){
            num = 1;
        }
        //类似非递归函数遍历树的节点
        while(x<num){	//
            int depth = tmp.depth;
            if(tmp.draw == false){
                if(tmp.sonNodeNum==0){

                }else{
                    num = num + tmp.sonNodeNum;
                }
                //g.drawOval(tmp.selfX, tmp.selfY, 50, 50);
                g.drawString(tmp.value, tmp.selfX+25, tmp.selfY+25);
                if(tmp != root){
                    g.drawLine(tmp.selfX+25, tmp.selfY+25, tmp.parentX+25, tmp.parentY+25);
                    g.drawString(tmp.lineValue, (tmp.selfX+tmp.parentX+50)/2, (tmp.selfY+tmp.parentY+50)/2);
                }
                tmp.draw = true;
                x++;
            }else{
                int y = -1;
                for(int i=0;i<tmp.sonNodeNum;i++){
                    if(tmp.sonNode[i].draw == false){
                        y=i;
                        break;
                    }
                }
                if(y!=-1){	//还有子结点为绘画。
                    tmp = tmp.sonNode[y];
                    continue;	//进入子结点，重新循环
                }else{		//之下的全部结点都以绘完
                    if(tmp ==root){
                        break;	//为根节点就退出
                    }else{
                        tmp = tmp.parentNode;
                        continue;
                    }
                }
            }
            if(tmp.sonNodeNum == 0){	//叶子节点,回到父结点
                tmp = tmp.parentNode;
            }else{						//不是叶子节点，进入下一层
                tmp = tmp.sonNode[0];
            }
        }

    }
}


