package com.bittech.javaweb.util;

public class Test {


    public static void main(String[] args) {
        Test test = new Test();
        String str = test.PrintMinNumber(null);
        System.out.println(str);
    }

    public String PrintMinNumber(int [] numbers) {

        StringBuffer sb = new StringBuffer();
        if(numbers==null||numbers.length==0){
            return "";
        }
        for(int i=0;i<numbers.length-1;i++){
            boolean flag = true;
            for(int j=0;j<numbers.length-i-1;j++){
                if(isBig(numbers[j],numbers[j+1])){
                    swap(numbers,j,j+1);
                    flag=false;
                }
            }
            if(flag){
                break;
            }
        }
        for(int i=0;i<numbers.length;i++){
            sb.append(numbers[i]);
        }
        return sb.toString();

    }

    public boolean isBig(int a,int b){
        String num1=""+a+b;
        String num2=""+b+a;
        return Integer.parseInt(num1)>Integer.parseInt(num2);
    }

    public void swap(int[] numbers,int x,int y){
        int t=numbers[x];
        numbers[x]=numbers[y];
        numbers[y]=t;
    }


    //**********************
//    public String PrintMinNumber(int [] numbers) {
//        if(numbers==null||numbers.length==0){
//            return "";
//        }
//        for(int i=0;i<numbers.length;i++){
//            boolean flag = true;
//            for(int j=0;j<numbers.length-i-1;j++){
//                if(comp(numbers[j],numbers[j+1])){
//                    flag = false;
//                    int t = numbers[j];
//                    numbers[j] = numbers[j+1];
//                    numbers[j+1] = t;
//                }
//            }
//            if(flag)
//                break;
//        }
//        String str="";
//        for(int i: numbers){
//            str+=i;
//        }
//        return str;
//    }
//    public static boolean comp(int x,int y){
//        long a = Long.parseLong(""+x+y);
//        long b = Long.parseLong(""+y+x);
//        if(a>b)
//            return true;
//        return false;
//    }

}
