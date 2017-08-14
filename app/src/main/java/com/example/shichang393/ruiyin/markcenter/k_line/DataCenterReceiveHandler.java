package com.example.shichang393.ruiyin.markcenter.k_line;

import android.util.Log;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

public class DataCenterReceiveHandler extends IoHandlerAdapter {

    public static final String TAG = "DataCenterReceive";

    private OnDataCenterReceiveListener mCallback;


    public DataCenterReceiveHandler(OnDataCenterReceiveListener callback) {

        mCallback = callback;
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {


        super.sessionCreated(session);
        System.out.println("sessionCreated~");
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionOpened(session);
//        session.write("socket begin!");
        System.out.println("sessionOpened~");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        // TODO Auto-generated method stub
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {

        super.exceptionCaught(session, cause);
        System.out.println("exceptionCaught~");
    }

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {

        super.messageReceived(session, message);

        String rcvMsg = message.toString();

        rcvMsg = rcvMsg.substring(0, rcvMsg.lastIndexOf("}") + 1);
        Log.d("bcq", rcvMsg);
        if (null != mCallback) {
            mCallback.clientReceiveMsg(rcvMsg);
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }


}
