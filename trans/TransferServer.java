package trans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 */
public class TransferServer {

    private File sendDir;
    private File saveDir;
    private FileInputStream in;
    private FileOutputStream out;
    private FileInputStream sendStream;
    private FileOutputStream saveStream;
    private Socket socket;
    private ServerSocket server;
    private int port;

    public TransferServer() {
        sendDir = new File("D:\\dsxservice\\tempServerSend.txt");   //默认发送的文件
        saveDir = new File("D:\\dsxservice\\tempServerSave.txt");   //默认存文件的文件
        port = 2017;        //默认端口
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

    private void init() {
        try {
            if (!sendDir.exists()) {        //防止报错
                sendDir.createNewFile();
            }
            if (!saveDir.exists()) {
                saveDir.createNewFile();
            }
            sendStream = new FileInputStream(sendDir);
            saveStream = new FileOutputStream(saveDir);
            server = new ServerSocket(port);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void waitConnect() {
        try {
            init();     //初始化
            socket = server.accept();
            in = (FileInputStream)socket.getInputStream();
            out = (FileOutputStream)socket.getOutputStream();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void send() {
        try {
            init();     //这是是防止使用setter函数之后，及时更新数据
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

    public void receive() {     //以字节流的方式接收文件
        try {
            init();     //这是是防止使用setter函数之后，及时更新数据
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

    public void closeAll() {        //关闭全部流和套接字
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
            if (null != server) {
                server.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //测试程序
    public static void main(String[] args) {
//        TransferServer server = new TransferServer();
//        server.setSendDir(new File("E:\\mima.png"));
//        server.waitConnect();
//        server.send();
//        server.closeAll();
        System.out.println("12312312");
    }
}
