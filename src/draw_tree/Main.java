package draw_tree;

import statistics.LabeledDyckPath;

import java.io.IOException;

import static statistics.LabeledDyckPath.getAllLabeledTree;

/**
 * @className: draw_tree.Main
 * @description:
 * @author: liyang
 * @create: 2023-09-18 19:12
 */


public class Main {
    public static void main(String[] args) throws IOException {
        int nodeNum = 3;
        LabeledDyckPath[] allLabeledTree = getAllLabeledTree(nodeNum);
        int count1 = 0;
        int count2 = 0;
        for (LabeledDyckPath tree : allLabeledTree) {
            if (tree.isDescent()) {
                if (tree.getStatistic1() == 0) {
                    count1++;
                    drawTree(tree.getShape(), tree.getLabel(), "first_statistic=0\\" + count1 + ".png");
                }
                if (tree.getStatistic2() == 0) {
                    count2++;
                    drawTree(tree.getShape(), tree.getLabel(), "second_statistic=0\\" + count2 + ".png");
                }
            }
        }
    }

    /*
    输入树映射到labeled dyck path 的形状和参数，生成树的图片到指定位置
     */
    public static void drawTree(int[] type, int[] label, String path) throws IOException {
        DrawTree dt = new DrawTree();
        DrawNode[] nodes = new DrawNode[label.length];
        //生成根点
        nodes[0] = new DrawNode("" + label[0], 0, 1024);
        //生成深度优先搜索下第i+个点
        for (int i = 0; i < type.length; i++) {
            int fatherIndex = i;
            if (i == type[i] - 1) {
                fatherIndex = 0;
            } else {
                for (int j = 1; j < i; j--) {
                    if (type[i] > type[i - j] + j) {
                        continue;
                    } else if (type[j] == type[i - j] + j) {
                        fatherIndex = i - j;
                    } else {
                        break;
                    }
                }
            }
            nodes[i + 1] = new DrawNode(nodes[fatherIndex], "" + label[i + 1], "");
        }
        dt.function(nodes[0], path);
    }
}
