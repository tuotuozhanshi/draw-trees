package util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: util.FullPermutation
 * @description: TODO
 * @author: liyang
 * @create: 2023-09-19 15:29
 */
public class FullPermutation {
    //设定点的个数
    static int node_num;
    //标号集
    static int[][] labelList ;
    //集合{1,2,...,node_num}
    static int[] nList = MathUtil.nList(node_num);
    static Set<Integer> all = new HashSet<Integer>(); // 全体集合（待选集合=全体集合-已选集合）
    static Set<Integer> used = new HashSet<Integer>(); // 已选集合
    static List<List<Integer>> resultList = new ArrayList<>(); // 全局结果列表

    /**
     * 生成全排列
     * @param num 长度
     * @return 二维数组
     */
    public static int[][] getFullPermutation(int num){
        FullPermutation.node_num = num;
        FullPermutation.nList = MathUtil.nList(node_num);
        FullPermutation.labelList = new int[MathUtil.factorial(node_num)][node_num];
        for (int i = 0; i < nList.length; i++) {
            all.add(nList[i]);
        }
        backtrack(0, new ArrayList<Integer>());
        return labelList;
    }

    /**
     * 全排列递归工具
     * @param p
     * @param tempresult
     */
    public static void backtrack(int p, List<Integer> tempresult) {
        if (p >= nList.length) {
            labelList[resultList.size()][0] = tempresult.get(0);
            for (int i = 1; i < tempresult.size(); i++) {
                labelList[resultList.size()][i] = tempresult.get(i);
            }
            resultList.add(tempresult);
            return;
        }
        for (Integer n : all) {
            if (!used.contains(n)) {
                used.add(n);
                tempresult.add(n);
                backtrack(p + 1, tempresult);
                tempresult.remove(tempresult.size() - 1);
                used.remove(n);
            }
        }
    }
}
