package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;

@SpringBootTest
public class SortTest {

    @Test
    public  void BubbleSortTest() {
        int[] sourceArray = new int[]{3,9,2,4,1,67,42,34,89,21,32};
        int[] res = BubbleSort(sourceArray);
        System.out.println(Arrays.toString(res));
    }

    /*冒泡排序*/
    public int[] BubbleSort(int[] sourceArray){
        for(int i = 1 ; i < sourceArray.length ; i++){
            for(int j = 0 ; j < sourceArray.length-i ; j++){
                if(sourceArray[j]>sourceArray[j+1]){
                    int temp = sourceArray[j];
                    sourceArray[j] = sourceArray[j+1];
                    sourceArray[j+1] = temp;
                }
            }
            System.out.println("-----"+Arrays.toString(sourceArray));
        }
        return sourceArray;
    }

    @Test
    public void InsertSortTest(){
        int[] sourceArray = new int[]{3,92,64,23,4,1,67,42,34,89,21,32,12,65};
        int[] res = insertSort(sourceArray);
        System.out.println(Arrays.toString(res));

    }

    /*插入排序*/
    public int[] insertSort(int[] arr){
        for(int i = 1 ; i < arr.length ; i++){
            for(int j = i ; j > 0 ;j--){
                if(arr[j]<arr[j-1]){
                    int temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                }
            }
        }
        return arr;
    }


    @Test
    public void quickSortTest(){
        int[] sourceArray = new int[]{3,92,64,23,4,1,67,42,34,89,21,32,12,65};
        int[] res = insertSort(sourceArray);
        System.out.println(Arrays.toString(res));

    }

    /*快速排序*/
    public int[] quickSort(int[] arr){

        return arr;
    }




}
