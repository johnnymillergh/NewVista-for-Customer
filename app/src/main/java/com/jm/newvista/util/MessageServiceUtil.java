package com.jm.newvista.util;

import android.util.Log;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class MessageServiceUtil {
    public static ServerSocket serverSocket;
    private static boolean hasServerSocketStarted = false;
    private MessageServiceUtilCallback myCallback;
    public static int localPort;
    public static final String remoteIp = "192.168.123.217";

    public void start() {
        startServerSocket();
        listenPort(myCallback);
    }

    public void stop() {
        stopServerSocket();
    }

    private void startServerSocket() {
        if (!hasServerSocketStarted) {
            try {
                serverSocket = new ServerSocket(0); // System will allocate an available port to serverSocket.
                hasServerSocketStarted = true;
                Log.v("startServerSocket", "Start local server socket(Message Service): [localhost:"
                        + serverSocket.getLocalPort() + "]");
                localPort = serverSocket.getLocalPort();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Log.v("startServerSocket", "Local socket has started: [localhost:"
                    + serverSocket.getLocalPort() + "]");
        }
    }

    private void listenPort(final MessageServiceUtilCallback messageServiceUtilCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (hasServerSocketStarted == false) {
                        break;
                    }
                    try {
                        Socket clientSocket = serverSocket.accept();
                        // Handle
                        Task task = new Task(clientSocket);
                        FutureTask<String> futureTask = new FutureTask<>(task);
                        new Thread(futureTask).start();
                        try {
                            String messageFromRemote = futureTask.get();
                            Log.v("messageFromRemote", messageFromRemote);
                            messageServiceUtilCallback.onGetMessageFromServer(messageFromRemote);
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stopServerSocket() {
        if (hasServerSocketStarted == true) {
            try {
                Log.v("stopServerSocket", "Stop ServerSocket: [localhost:"
                        + serverSocket.getLocalPort() + "]");
                serverSocket.close();
                hasServerSocketStarted = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Task implements Callable<String> {
        Socket socketAccepted;

        public Task(Socket socketAccepted) {
            this.socketAccepted = socketAccepted;
        }

        @Override
        public String call() {
            InputStream is = null;
            InputStreamReader isr = null;
            BufferedReader br = null;
            OutputStream os = null;
            PrintWriter out = null;
            String messageFromRemote;
            try {
                // Get input stream
                is = socketAccepted.getInputStream();
                isr = new InputStreamReader(is);// byte stream
                br = new BufferedReader(isr);// character stream

                // Read a line from client
                messageFromRemote = br.readLine();
                Log.v("Got message from remote", messageFromRemote);
                // Close input stream
                socketAccepted.shutdownInput();
//
//                // Prepare message to send to remote
//                Gson gson = new GsonBuilder().disableHtmlEscaping().create();
//                String messageStr = gson.toJson(messageToRemote);
//
//                // Get output stream, send message to remote
//                os = socketAccepted.getOutputStream();
//                out = new PrintWriter(os);
//                out.write(messageStr);
//                out.flush();
                return messageFromRemote;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                // Close resources
                try {
                    if (out != null)
                        out.close();
                    if (os != null)
                        os.close();
                    if (br != null)
                        br.close();
                    if (isr != null)
                        isr.close();
                    if (is != null)
                        is.close();
                    if (socketAccepted != null)
                        socketAccepted.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public interface MessageServiceUtilCallback {
        void onGetMessageFromServer(String message);
    }

    public MessageServiceUtilCallback getMyCallback() {
        return myCallback;
    }

    public void setMyCallback(MessageServiceUtilCallback myCallback) {
        this.myCallback = myCallback;
    }

    public boolean isHasServerSocketStarted() {
        return hasServerSocketStarted;
    }

    public void setHasServerSocketStarted(boolean hasServerSocketStarted) {
        this.hasServerSocketStarted = hasServerSocketStarted;
    }
}
