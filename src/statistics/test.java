package statistics;

import util.FullPermutation;
import util.MathUtil;

import java.io.IOException;

import static draw_tree.Main.drawTree;

public class test {

    public static void main(String[] args) throws IOException {
        //点的个数
        int node_num = 5;
        int[][] typeList = MathUtil.DyckPaths(node_num-1);
        int[][] labelList = FullPermutation.getFullPermutation(node_num);
        LabeledDyckPath[] data = new LabeledDyckPath[labelList.length*(typeList.length-1)];
        int count=0;
        int discent_tree_num=0;
        int[][] result = new int[2][node_num];
        System.out.println("点的个数："+node_num);
        for (int i = 1; i < typeList.length; i++) {
            for (int j = 0; j < labelList.length; j++) {
                int current_index = (i-1)*labelList.length+j;
                data[current_index] = new LabeledDyckPath(typeList[i],labelList[j]);
                if (data[current_index].isDescent()){
                    discent_tree_num++;

                    //输出统计量1为0的数据
                    if (data[current_index].getStatistic1() == 0){
                        System.out.print(++count+"---");
                        System.out.println("一");
                        int shape_length = data[current_index].getShape().length;
                        for (int k = 0; k < shape_length; k++) {
                            System.out.print(data[current_index].getShape()[k]);
                        }
                        System.out.print(" ");
                        int label_length = data[current_index].getLabel().length;
                        for (int k = 0; k < label_length; k++) {
                            System.out.print(data[current_index].getLabel()[k]);
                        }
                        System.out.println(" ");
                        drawTree(data[current_index].getShape(),data[current_index].getLabel(),count+".png");
                    }
                    //输出统计量2为1的数据
                    if (data[current_index].getStatistic2() == 0){
                        System.out.print(++count+"---");
                        System.out.println("二");
                        int shape_length = data[current_index].getShape().length;
                        for (int k = 0; k < shape_length; k++) {
                            System.out.print(data[current_index].getShape()[k]);
                        }
                        System.out.print(" ");
                        int label_length = data[current_index].getLabel().length;
                        for (int k = 0; k < label_length; k++) {
                            System.out.print(data[current_index].getLabel()[k]);
                        }
                        System.out.println(" ");
                        drawTree(data[current_index].getShape(),data[current_index].getLabel(),count+".png");
                    }

                    result[0][data[(i-1)*labelList.length+j].getStatistic1()]++;
                    result[1][data[(i-1)*labelList.length+j].getStatistic2()]++;
                }
            }
        }
        for (int i = 0; i < node_num; i++) {
            System.out.println("统计量1="+i+"的数目："+result[0][i]+"  "+"统计量2="+i+"的数目："+result[1][i]);
        }
        System.out.println("discent_tree的个数:"+discent_tree_num);

    }


}
