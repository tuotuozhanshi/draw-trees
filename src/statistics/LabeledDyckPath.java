package statistics;

import util.FullPermutation;
import util.MathUtil;

import static draw_tree.Main.drawTree;

public class LabeledDyckPath {
    private int[] shape;
    private int[] label;
    //这个属性描述是否为descent tree映射过来的
    private boolean isDescent = true;
    //统计量1为i+1最左边孩子为i的边数
    private int statistic1 = 0;
    //统计量2为n的孩子数
    private int statistic2 = 0;

    /**
     * 输入形状与标号，生成标号树并判断是否为Descent tree，生成统计量1和2的值
     * @param shape
     * @param label
     */
    public LabeledDyckPath(int[] shape, int[] label) {
        this.shape = shape;
        this.label = label;
        a: for (int i = 0; i < this.shape.length; i++) {
            if(i==0 || this.shape[i-1]==this.shape[i]){
                if (this.label[i+1] == this.label[i]-1){
                    this.statistic1++;
                }
                if(this.label[i+1]>this.label[i]){
                    this.isDescent = false;
                    break a;
                }
            }

            if(i!=this.shape.length){
                if (this.label[i]==this.label.length-1){
                    if(i==0||this.shape[i-1]==this.shape[i]){
                        statistic2++;
                    }
                }
                b:for (int j = 1; j < this.shape.length-i; j++) {
                    if(this.shape[i+j]==this.shape[i]+j){
                        if (this.label[i]==this.label.length-1&&(i==0||this.shape[i-1]==this.shape[i])){
                            this.statistic2++;
                        }
                        if(this.label[i+j+1]<this.label[i+1]){
                            this.isDescent = false;
                            break a;
                        }
                    }else if(this.shape[i+j]<this.shape[i]+j){
                        continue ;
                    }else if (this.shape[i+j]>this.shape[i]+j){
                        break b;
                    }
                }
            }
        }
    }

    /**
     * 生成nodeNum个点的所有标号树
     * @param nodeNum
     * @return
     */
    public static LabeledDyckPath[] getAllLabeledTree(int nodeNum){
        int[][] typeList = MathUtil.DyckPaths(nodeNum-1);
        int[][] labelList = FullPermutation.getFullPermutation(nodeNum);
        LabeledDyckPath[] allLabeledDyckPaths = new LabeledDyckPath[labelList.length*(typeList.length-1)];

        for (int i = 1; i < typeList.length; i++) {
            for (int j = 0; j < labelList.length; j++) {
                int current_index = (i-1)*labelList.length+j;
                allLabeledDyckPaths[current_index] = new LabeledDyckPath(typeList[i],labelList[j]);
            }
        }
        return allLabeledDyckPaths;
    }

    /**
     * 获取
     * @return shape
     */
    public int[] getShape() {
        return shape;
    }

    /**
     * 获取
     * @return label
     */
    public int[] getLabel() {
        return label;
    }

    /**
     * 获取
     * @return isDescent
     */
    public boolean isDescent() {
        return isDescent;
    }

    /**
     * 获取
     * @return statistic1
     */
    public int getStatistic1() {
        return statistic1;
    }

    /**
     * 获取
     * @return statistic2
     */
    public int getStatistic2() {
        return statistic2;
    }

}
