package pxy.com.tutor.socketservice;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SubPolThread implements Runnable {
    private Socket connection;
    private List<Byte> receiveDatas;
    private Thread pares;


    public SubPolThread(Socket conSocket) {
        this.connection = conSocket;
        receiveDatas = new ArrayList<>();

    }

    /**
     * 读取客户端信息
     *
     * @param inputStream
     */
    private void readMessageFromClient(InputStream inputStream) throws IOException {

        byte[] data = new byte[1024];
        inputStream.read(data);
        List<Byte> bytes = ListUtil.bytesToList(data);
        receiveDatas.addAll(bytes);
        parseCommand();

    }


    private void parseCommand() {
        while (receiveDatas.size() > 0) {
            if (receiveDatas.get(0) != -86) {
                receiveDatas.remove(0);
                return;
            }
            byte[] bytes = ListUtil.listTobyte(receiveDatas);
            DataStream stream = new DataStream(bytes);
            try {
                byte head = stream.readByte();
                int length = stream.readUint();
                boolean isTutor = stream.readBoolean();
                String NO = stream.readShortString();
                String targetNo = stream.readShortString();
                String content = stream.readShortString();
                String hostIp = stream.readShortString();
                byte foot = stream.readByte();
                int totalLength = 1 + 4 + length + 1;
                ListUtil.getRange(ListUtil.listTobyte(receiveDatas), 0, totalLength);
                System.out.println(content);
                receiveDatas.clear();
                SocketService.getInstance().callReceived(content,isTutor,NO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 响应客户端信息
     *
     * @param outputStream
     * @param string
     */
    private void writeMsgToClient(OutputStream outputStream, String string) throws IOException {
        Writer writer = new OutputStreamWriter(outputStream);
        writer.append("I am server message!!!");
        writer.flush();
        writer.close();
    }


    @Override
    public void run() {
        try {
            readMessageFromClient(connection.getInputStream());
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
