package com.chant;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//userName:username
//G:msg
//P:pName-msg
public class Server {

    //存放客户的Socket
    private static Map<String,Socket> map = new HashMap<String,Socket>();
    private static Object lock = new Object();
    //静态内部类线程
    static class ExecuteClientSever implements Runnable{
        private Socket client;

        public ExecuteClientSever(Socket client) {
            this.client = client;
        }

        public void run() {
            try {
                PrintStream ps = new PrintStream(client.getOutputStream());
                Scanner scanner = new Scanner(client.getInputStream());
                while(true){
                    String str = null;
                    if(scanner.hasNext()){
                        str = scanner.next();
                        Pattern pattern = Pattern.compile("\r");
                        Matcher matcher = pattern.matcher(str);
                        str = matcher.replaceAll("");
                    }

                    if(str.startsWith("userName")){
                        synchronized (lock){
                            String userName = str.split("\\:")[1];
                            userRegist(userName);
                        }
                    }
                    else if (str.startsWith("G")){
                        String msg = str.split("\\:")[1];
                        groupChat(msg);
                    }
                    else if(str.startsWith("P")){
                        String tmp = str.split("\\:")[1];
                        String pName = tmp.split("-")[0];
                        String msg = tmp.split("-")[1];
                        privateChat(pName,msg);
                    }
                    else if(str.startsWith("bey")){
                        scanner.close();
                        ps.close();
                        break;
                    }
                    else{
                        System.out.println("输入格式不对");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        private void userRegist(String userName){
            map.put(userName,this.client);
            System.out.println("用户"+userName+"上线了。 当前人数:"+map.size()+"人");
        }
        private void groupChat(String msg) throws IOException {
            Set<Map.Entry<String,Socket>> set = map.entrySet();
            Iterator<Map.Entry<String,Socket>> it = set.iterator();
            while(it.hasNext()){
                Map.Entry<String,Socket> entry = it.next();
                Socket client = entry.getValue();
                PrintStream ps = new PrintStream(client.getOutputStream(),true,"UTF8");
                String str = ("用户说： "+msg).trim();
                ps.println(str);
            }
        }
        private void privateChat(String pName, String msg) throws IOException{
            Socket pClient = map.get(pName);
            if(pClient!=null){
                PrintStream ps = new PrintStream(pClient.getOutputStream(),true,"UTF8");
                ps.println("Private: "+msg);
            }else{
                PrintStream ps = new PrintStream(this.client.getOutputStream());
                ps.println("你要找的人不存在。");
            }
        }

    }
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        ServerSocket server = new ServerSocket(6666);
        for(int i=0;i<20;i++){
            System.out.println("等待客户端连接。");
            Socket client = server.accept();
            executorService.execute(new ExecuteClientSever(client));
        }
        executorService.shutdown();
        server.close();
    }
}
