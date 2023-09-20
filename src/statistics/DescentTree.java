package statistics;


import static statistics.LabeledDyckPath.getAllLabeledTree;

/**
 * @className: statistics.DescentTree
 * @description: TODO
 * @author: liyang
 * @create: 2023-09-19 16:38
 */
public class DescentTree {
    /**
     * 获取nodeNum长descentTree的个数
     *
     * @param nodeNum
     * @return
     */
    public static int descentTreeNum(int nodeNum) {
        LabeledDyckPath[] allLabeledTree = getAllLabeledTree(nodeNum);
        int discent_tree_num = 0;
        for (LabeledDyckPath tree : allLabeledTree) {
            if (tree.isDescent()) {
                discent_tree_num++;
            }
        }
        return discent_tree_num;
    }

    /**
     *打印不同统计量的descent tree的数目
     */
    public static void printDescentTreeStatisticMessage(int nodeNum) {
        LabeledDyckPath[] allLabeledTree = getAllLabeledTree(nodeNum);
        int[][] result = new int[2][nodeNum];
        for (LabeledDyckPath tree : allLabeledTree) {
            if (tree.isDescent()) {
                result[0][tree.getStatistic1()]++;
                result[1][tree.getStatistic2()]++;
            }
        }
        for (int i = 0; i < nodeNum; i++) {
            System.out.println("统计量1=" + i + "的数目：" + result[0][i] + "  " + "统计量2=" + i + "的数目：" + result[1][i]);
        }

    }
}
