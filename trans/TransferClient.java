package trans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class TransferClient {
    private File sendDir;
    private File saveDir;
    private FileInputStream in;
    private FileOutputStream out;
    private FileInputStream sendStream;
    private FileOutputStream saveStream;
    private Socket socket;
    private int port;
    private String host;

    public TransferClient() {
        sendDir = new File("E:\\tempClientSend.txt");
        saveDir = new File("E:\\tempClientSave.txt");
        host = "127.0.0.1";     //默认连接的地址主机
        port = 2017;            //默认连接的端口
    }

    private void init() {   //初始化
        try {
            if (null != sendDir) {
                sendDir.createNewFile();
            }
            if (null != saveDir) {
                saveDir.createNewFile();
            }
            sendStream = new FileInputStream(sendDir);
            saveStream = new FileOutputStream(saveDir);
            socket = new Socket(host, port);
            in = (FileInputStream)socket.getInputStream();
            out = (FileOutputStream)socket.getOutputStream();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public File getSendDir() {
        return sendDir;
    }

    public void setSendDir(File sendDir) {
        this.sendDir = sendDir;
    }

    public File getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(File saveDir) {
        this.saveDir = saveDir;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void send() {
        try {
            init();     //初始化
            byte[] b = new byte[64];
            int n = sendStream.read(b);
            while (-1 != n) {
                out.write(b, 0, n);
                n = sendStream.read(b);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void receive() {
        try {
            init();     //初始化
            byte[] b = new byte[64];
            int n = in.read(b);
            while (-1 != n) {
                saveStream.write(b, 0, n);
                n = in.read(b);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void closeAll() {
        try {
            if (null != sendStream) {
                sendStream.close();
            }
            if (null != out) {
                out.close();
            }
            if (null != saveStream) {
                saveStream.close();
            }
            if (null != in) {
                in.close();
            }
            if (null != socket) {
                socket.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //测试函数，请先运行服务器端
    public static void main(String[] args) {
        TransferClient client = new TransferClient();
        client.setSaveDir(new File("D:\\dsxservice\\mimatemp.png"));
        client.receive();
        client.closeAll();
    }
}
