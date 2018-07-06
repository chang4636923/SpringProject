package com.chang.client;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class ReadServer implements Runnable{
    private Socket client;

    public ReadServer(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            Scanner scan = new Scanner(client.getInputStream());
            while(true){
                String str = scan.nextLine();
                //if(scan.hasNext()){
                    System.out.println(str);
                //}
                if(client.isClosed()){
                    break;
                }
            }
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Write implements Runnable{
    private Socket client;

    public Write(Socket client) {
        this.client = client;
    }

    public void run() {
        try {
            PrintStream ps = new PrintStream(client.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            while(true){
                Thread.sleep(100);
                System.out.println("请输入：");
                String str = scanner.next().trim();
                ps.println(str);
                if(str.equals("bey")){
                    System.out.println("客户端关闭。");
                    ps.close();
                    scanner.close();
                    client.close();
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("127.0.0.1",6666);
        new Thread(new ReadServer(client)).start();
        new Thread(new Write(client)).start();
    }
}
