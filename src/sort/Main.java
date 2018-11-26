package sort;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        int[] arr = new int[10000000];
        Random random = new Random();

        for(int i=0; i<arr.length; i++) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }

        Arrays.sort(arr);
//        print("选择排序 ",selectSort(arr));
//        print("冒泡排序 ",bubbleSort(arr));
//        print("插入排序 ",insertSort(arr));
//        print("希尔排序 ",shellSort(arr));
//        print("归并排序 ",mergeSort(arr));
//        print("堆排序 ",heapSort(arr));
//        print("快速排序 ",quickSort(arr));

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;
        System.out.println("time: " + time + " s");

    }

    /**
     * 冒泡排序算法：每次将最小的一个数”浮“上去
     * 最好情况O(n),即数组本身有序
     * 最坏情况O(n^2)
     */
    public static int[] bubbleSort(int[] a) {
        a = Arrays.copyOf(a, a.length);

        int count = 0;
        boolean terminated = false;

        for(int i=0;i<a.length-1&&!terminated;i++) {
            terminated = true;
            for(int j=a.length-2;j>=i;j--) {
                count++;
                if(a[j]>a[j+1]) {
                    swap(a,j+1,j);
                    terminated = false;
                }
            }
        }
        System.out.println("冒泡排序比较次数: "+count);
        return a;
    }


    /**
     * 选择排序：每次选出待排序中最小的一个
     */
    public static int[] selectSort(int[] a) {
        a = Arrays.copyOf(a, a.length);

        int count = 0;
        for(int i=0;i<a.length-1;i++) {
            int min = a[i];
            int minIndex = i;
            for(int j=i+1;j<a.length;j++) {
                count++;
                if(a[j]<min) {
                    minIndex = j;
                    min = a[j];
                }
            }
            swap(a,i,minIndex);
        }
        System.out.println("选择排序比较次数: "+count);
        return a;
    }

    public static int[] insertSort(int[] a) {
        a = Arrays.copyOf(a, a.length);

        int count = 0;
        for(int i=1;i<a.length;i++) {//  i之前有序,i指向待排序的元素
            if(a[i]<a[i-1]) {
                int temp = a[i];
                int j = i-1;//j指向当前元素的前一个
                for(;j>=0&&a[j]>temp;j--) {
                    count++;
                    a[j+1] = a[j];
                }
                a[j+1] = temp;
            }
        }

        System.out.println("插入排序比较次数: "+count);
        return a;
    }
    private static void swap(int[] a,int i,int j) {
        if (i==j)   return;
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    /**
     * 希尔排序
     * @param a
     * @return
     */
    public static int[] shellSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        int count = 0;

        int increment = a.length;
        do {
            increment = increment/3 + 1;
            for(int i=increment;i<a.length;i=i+increment) {
                if(a[i]<a[i-increment]) {
                    int temp = a[i];
                    int j=i-increment;
                    for(;j>=0&&a[j]>temp;j -= increment) {
                        count++;
                        a[j+increment] = a[j];
                    }
                    a[j+increment] = temp;
                }
            }
        }while(increment>1);

        System.out.println("希尔排序比较次数： "+count);
        return a;

    }
    /**
     * 堆排序
     * @param a
     * @return
     */
    public static int[] heapSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        int length = a.length;
        for(int i=length/2-1;i>=0;i--)//2*i+1<=length-1,最后一个有左孩子的节点
            heapAdjust(a,i,length);

        for(int i=length-1;i>=0;i--) {
            swap(a,0,i);
            heapAdjust(a,0,i);//
        }

        return a;
    }

    //每次将以i为root的子树满足最大堆特性;i指向待调整的节点
    private static void heapAdjust(int[] a,int i,int length) {
        int temp = a[i];

        for(int j=2*i+1;j<length;j=2*i+1){//刚开始时指向左孩子
            if(j+1<length && a[j+1]>a[j])//如果有做右孩子，且右孩子比左孩子大
                j++;//j指向左右孩子中值较大的一个

            if(temp>=a[j])
                break;
            a[i] = a[j];

            i=j;
        }
        a[i] = temp;
    }

    public static int[] mergeSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        int[] aux = new int[a.length];
        mergeSort(a,aux,0,a.length-1);
        return a;
    }
    private static void mergeSort(int[] a,int[] aux,int lo,int high) {
        if(lo>=high)    return;

        int mid = (lo+high)/2;
        mergeSort(a,aux,lo,mid);
        mergeSort(a,aux,mid+1,high);
        merge(a,aux,lo,mid,high);
    }
    private static void merge(int[] a,int aux[],int lo,int mid,int high) {
        for(int i=lo;i<=high;i++) {
            aux[i] = a[i];
        }

        int lo_h = mid+1;
        for(int i=lo;i<=high;i++) {
            if(lo>mid)
                a[i] = aux[lo_h++];
            else if(lo_h>high)
                a[i] = aux[lo++];
            else {
                if(aux[lo]<=aux[lo_h])
                    a[i] = aux[lo++];
                else
                    a[i] = aux[lo_h++];
            }
        }
    }
    /**
     * 快速排序
     * @param a
     * @return
     */

    public static int[] quickSort(int[] a) {
        a = Arrays.copyOf(a, a.length);
        quickSort(a,0,a.length-1);
        return a;
    }
    private static void quickSort(int[] a,int low,int high) {
        if(low>=high)   return;

        int partition = partition(a,low,high);
        quickSort(a,low,partition-1);
        quickSort(a,partition+1,high);
    }
    private static int partition(int[] a,int low,int high) {
        int tempt = a[low];

        while(low<high) {
            while(low<high&&a[high]>=tempt)
                high--;
//            swap(a,low,high);
            a[low] = a[high];

            while(low<high&&a[low]<=tempt)
                low++;
//            swap(a,low,high);
            a[high] = a[low];
        }
        a[low] = tempt;
        return low;
    }
    private static void print(String str,int[] a) {
        System.out.print(str);
//        for(int i=0;i<a.length;i++)
//            System.out.print(a[i]+" ");
//        System.out.println("\n");
    }

}