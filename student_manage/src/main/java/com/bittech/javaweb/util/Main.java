package com.bittech.javaweb.util;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String src = in.next();
        String str = in.next();
        if(src==null||str==null||src.length()==0||str.length()==0){
            System.out.println(src);
            return;
        }
        String[] del=str.split("");
        for(int i=0;i<del.length;i++){
            src=src.replaceAll(del[i],"");
        }
        System.out.println(src);
    }
}
