package util;

/**
 * @className: util.MathUtil
 * @description: 数学工具类
 * @author: liyang
 * @create: 2023-09-19 14:40
 */
public final class MathUtil {


    //生成n长卡特兰数
    public static int cat(int cn){
        long cat=1;
        for (int i = 1; i < cn+1; i++) {
            cat = cat*i;
        }
        cat = cat*cat;
        long currentCat=1;
        for (int i = 1; i < 2*cn+1; i++){
            currentCat = currentCat*i;
        }
        int val = (int)(currentCat/cat/(cn+1));
        return val;
    }

    //生成n长全部Dyck path
    public static int[][] DyckPaths(int n){
        int[] list = {1};
        int cat= cat(n);
        int[][] dyckPath = new int[cat+1][n];
        return DyckPath(list, dyckPath, n);
    }

    //生成全部Dyck path的递归工具，其中第一个数组中只存储一个数据，就是当前二维数组中的数据个数
    public static int[][] DyckPath(int[] list,int[][] dyckPath,int n){
        if(list.length==n){
            dyckPath[dyckPath[0][0]+1] = new int[list.length];
            for (int i = 0; i < list.length; i++) {
                dyckPath[dyckPath[0][0]+1][i] = list[i];
            }
            dyckPath[0][0]+=1;
            return dyckPath;
        }
        int[] currentList = new int[list.length + 1];
        for (int i = list[list.length-1]; i <list.length+2 ; i++) {

            for (int j = 0; j < list.length; j++) {
                currentList[j] = list[j];
            }
            currentList[list.length] = i;
            //j代表有效list个数
            int currentLength = dyckPath.length-1;
            for (int j = 1; j < dyckPath.length; j++) {
                if (dyckPath[j][0] == 0) {
                    currentLength = j-1;
                    break;
                }
            }
            int[][] currentDyckPath = DyckPath(currentList,dyckPath,n);
            if(currentDyckPath[0][0]>currentLength){
                dyckPath[currentLength+1] = currentDyckPath[currentLength+1];
            }
        }
        return dyckPath;
    }

    //阶乘
    public static int factorial(int node_num){
        int pro = 1;
        for (int i = 1; i < node_num+1; i++) {
            pro = pro*i;
        }
        return pro;
    }

    //生成n元集123...n
    public static int[] nList(int pro){
        int[] nlist = new int[pro];
        for (int i = 0; i < pro; i++) {
            nlist[i] = i;
        }
        return nlist;
    }
}
