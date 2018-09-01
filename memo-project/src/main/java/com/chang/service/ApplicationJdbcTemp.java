package com.chang.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

public class ApplicationJdbcTemp {

    public static void menu(){
        System.out.println("1.添加便签组");
        System.out.println("2.修改便签组");
        System.out.println("3.查询便签组");
        System.out.println("4.删除便签组");
        System.out.println("5.查询所有便签组");
        System.out.println("6.退出");
    }

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");
        Scanner scan = new Scanner(System.in);
        MemoGroupService memoGroupService = context.getBean(MemoGroupService.class);

        int choice = 0;
        while(true){
            menu();
            System.out.println("请选择：");
            choice=scan.nextInt();
            switch (choice){
                case 1:
                    memoGroupService.addMemoGroup();
                    break;
                case 2:
                    memoGroupService.updateMemoGroupName();
                    break;
                case 3:
                    memoGroupService.queryMemoGroupById();
                    break;
                case 4:
                    memoGroupService.deleteMemoGroupById();
                    break;
                case 5:
                    memoGroupService.queryAll();
                    break;
                case 6:
                    System.out.println("BeyBey");
                    return;
                default:
                    throw new IllegalArgumentException("Input is Illegal");
            }
        }

    }
}
