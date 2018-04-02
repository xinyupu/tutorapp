package pxy.com.tutor.socketservice;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by pxy on 2018/4/1.
 */

public class SocketService {

    private Socket socket;
    private DataOutputStream out;
    private ExecutorService service = Executors.newFixedThreadPool(5);

    private static SocketService _instance;

    public static SocketService createInstance() {
        if (_instance == null) {
            synchronized (SocketService.class) {
                if (_instance == null) {
                    _instance = new SocketService();

                }
            }
        }
        return _instance;
    }

    public static SocketService getInstance() {
        return _instance;
    }

    private SocketService() {
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(1503);
                while (true) {
                    Socket accept = serverSocket.accept();
                    service.execute(new SubPolThread(accept));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }


    public void send(String content, boolean isTutor, String NO, String targetNO) {
        service.execute(() -> {
            try {
                DataStream stream = new DataStream();
                stream.writeByte((byte) 0xAA);
                byte[] child = child(content, isTutor, NO, targetNO);
                stream.writeUint(child.length);
                stream.writeByteArray(child);
                stream.writeByte((byte) 0x55);
                byte[] bytes = stream.toBytes();
                stream.close();
                socket = new Socket("192.168.1.102", 1503);
                OutputStream os = socket.getOutputStream();
                os.write(bytes);
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    private byte[] child(String content, boolean isTutor, String NO, String targetNO) {
        DataStream stream = new DataStream();
        try {
            stream.writeBoolean(isTutor);
            stream.WriteShortString(NO);
            stream.WriteShortString(targetNO);
            stream.WriteShortString(content);
            byte[] bytes = stream.toBytes();
            stream.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
