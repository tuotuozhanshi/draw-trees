package draw_tree;

public class DrawNode {
    String value = null;		//当前节点的属性
    DrawNode parentNode = null;	//父结点
    String lineValue = null;	//与父结点连接的属性
    int sonNodeNum = 0;			//子结点个数
    DrawNode[] sonNode = new DrawNode[10];	//子结点
    int depth = 0;				//深度
    int beginX = 0;				//以该节点为根节点的树前边界
    int endX = 0;				//以该节点为根节点的树后边界
    boolean draw = false;		//是否已经被绘制

    int selfX = 0;				//自身节点横坐标
    int selfY = 0;				//自身节点纵坐标
    int parentX = 0;			//父亲节点横坐标
    int parentY = 0;			//父亲节点纵坐标



    DrawNode(String value, int beginX, int endX){	//根节点构造函数
        this.value = value;
        this.beginX = beginX;
        this.endX = endX;
        this.depth = 0;
        this.selfX = beginX + (beginX+endX)/2;
        this.selfY = 0;
    }

    DrawNode(DrawNode parentNode, String value, String lineValue){	//不是根节点构造函数
        this.parentNode = parentNode;
        this.value = value;
        this.lineValue = lineValue;
        this.parentX = parentNode.selfX;
        this.parentY = parentNode.selfY;
        this.depth = parentNode.depth+1;
        //自身x坐标是要根据父结点的子结点的个数动态改变
        //this.selfX = parentNode.selfX+(-200+parentNode.sonNodeNum*200/(depth+1));	//这条语句可有可无，在树完全生成后，再确定每个节点的selfX
        this.selfY = parentNode.selfY+200;
        parentNode.sonNodeNum++;
        parentNode.sonNode[parentNode.sonNodeNum-1] = this;
        //setAllSonX(parentNode);

    }


}
