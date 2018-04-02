package pxy.com.tutor.socketservice;

import android.util.Log;

import com.pxy.pangjiao.PangJiao;
import com.pxy.pangjiao.common.Mapper;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import pxy.com.tutor.adapter.protocol.getstudent.GetStudentResponse;
import pxy.com.tutor.adapter.protocol.gettutor.GetTutorResponse;
import pxy.com.tutor.globle.Env;
import pxy.com.tutor.service.IAppService;
import pxy.com.tutor.service.imp.AppService;

/**
 * Created by pxy on 2018/4/1.
 */

public class SocketService {

    private Socket socket;
    private DataOutputStream out;
    private ExecutorService service = Executors.newFixedThreadPool(5);
    private String hostIp;
    private boolean isRunning = false;
    private IReceivedMsg iReceivedMsg;
    private Map<String, ChatContainer> container = new HashMap<>();
    private Map<String,IReceivedMsg> msgMap=new HashMap<>();

    private static SocketService _instance;

    public static SocketService createInstance(String hostIp) {
        if (_instance == null) {
            synchronized (SocketService.class) {
                if (_instance == null) {
                    _instance = new SocketService(hostIp);

                }
            }
        }
        return _instance;
    }

    private SocketService() {
    }

    public static SocketService getInstance() {
        return _instance;
    }

    private SocketService(String hostIp) {
        this.hostIp = hostIp;
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(1504);
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
                socket = new Socket(this.hostIp, 1503);
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
            stream.WriteShortString(Env.getAppContext().getHostIp());
            byte[] bytes = stream.toBytes();
            stream.close();
            return bytes;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void callReceived(String content, boolean isTuer, String NO) {
        Log.i("Socket", content);
        ChatContainer chatContainer = container.get(NO);
        if (chatContainer == null) {
            AppService resolve = PangJiao.resolve(AppService.class);
            ChatContainer mChatContainer = new ChatContainer();
            if (isTuer) {
                GetTutorResponse tutor = resolve.getTutor(NO);
                if (tutor.isSuccess()) {
                    mChatContainer.setHeadUrl(tutor.getData().getHeadUrl());
                    mChatContainer.setName(tutor.getData().getName());
                    mChatContainer.setTutor(true);
                    ChatContainer.Msg msg=new ChatContainer.Msg();
                    msg.msg=content;
                    msg.headUrl=tutor.getData().getHeadUrl();
                    msg.no=NO;
                    mChatContainer.getReceivedMsgs().add(msg);
                    mChatContainer.setNo(NO);
                    container.put(tutor.getData().getTutorNO(), mChatContainer);
                    for (String key:msgMap.keySet()){
                        msgMap.get(key).notifyMsg();
                    }
                }
            } else {
                GetStudentResponse student = resolve.getStudent(NO);
                if (student.isSuccess()) {
                    mChatContainer.setHeadUrl(student.getData().getHeadUrl());
                    mChatContainer.setName(student.getData().getName());
                    mChatContainer.setTutor(false);
                    mChatContainer.setNo(NO);
                    ChatContainer.Msg msg=new ChatContainer.Msg();
                    msg.msg=content;
                    msg.headUrl=student.getData().getHeadUrl();
                    msg.no=NO;
                    mChatContainer.getReceivedMsgs().add(msg);
                    container.put(student.getData().getStudentNO(), mChatContainer);
                    for (String key:msgMap.keySet()){
                        msgMap.get(key).notifyMsg();
                    }
                }
            }
        } else {
            ChatContainer.Msg msg=new ChatContainer.Msg();
            msg.msg=content;
            msg.no=NO;
            msg.headUrl=chatContainer.getHeadUrl();
            chatContainer.getReceivedMsgs().add(msg);
            for (String key:msgMap.keySet()){
                msgMap.get(key).notifyMsg();
            }
        }
    }

    public void login(boolean isTutor, String NO) {
        this.send("Login", isTutor, NO, "0");
        startHear(isTutor, NO);
    }

    private void startHear(boolean isTutor, String NO) {
        if (!isRunning) {
            new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.send("Hear:" + NO, isTutor, NO, "0");
                }
            }).start();
        }
    }

    public void stop() {
        isRunning = false;
    }

    public Map<String, ChatContainer> getContainer() {
        return container;
    }

    public interface IReceivedMsg {
        void notifyMsg();
    }



    public void setReceivedListener(IReceivedMsg iReceivedMsg) {
        msgMap.put(iReceivedMsg.getClass().getName(),iReceivedMsg);
    }
    public void clearListener(IReceivedMsg iReceivedMsg){
        msgMap.remove(iReceivedMsg.getClass().getName());
    }
}
