package com.tianjin.huanrong.model.mine;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.tianjin.huanrong.ApiService.MineService;
import com.tianjin.huanrong.Bean.LoginBean;
import com.tianjin.huanrong.Bean.mine.ForgotPwdPostBean;
import com.tianjin.huanrong.Bean.mine.UseRegisterPostBean;
import com.tianjin.huanrong.listener.mine.OnSMSListener;
import com.tianjin.huanrong.manager.SharedPreferencesMgr;
import com.tianjin.huanrong.utils.CommonUtil;
import com.tianjin.huanrong.utils.ConstanceValue;

import java.io.DataInputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Mr.zhang on 2017/8/11.
 */

public class SMSModel {
    private OnSMSListener onSMSListener;
    private int sendMessageSeconds;

    private Thread buttonChangeThread;

    private boolean runThread;

    public SMSModel(OnSMSListener onSMSListener) {
        this.onSMSListener = onSMSListener;
    }

    /**
     * 发送短信验证码
     */
    public void sendValidateCode(final String userPhone) {
        if (TextUtils.isEmpty(userPhone)) {
            onSMSListener.failed("手机号码不能为空");
            return;
        }
        if (!CommonUtil.isMobileNO(userPhone)) {
            onSMSListener.failed("亲，手机号码格式不正确哦");
            return;
        }
        sendMessageSeconds = 0;
        runThread = true;
        // 必须每次new 不然会报错 thread already started
        try {
            buttonChangeThread = new Thread(new Runnable() {
                public void run() {
                    for (int i = 60; i > 0; i--) {
                        if (runThread) {
                            sendMessageSeconds = i;
                            handler.sendEmptyMessage(-9);
                            if (i <= 0) {
                                break;
                            }
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                            break;
                        }
                    }
                    handler.sendEmptyMessage(-8);
                }
            });
            // 倒计时开始
            buttonChangeThread.start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 发送验证码
                    sendValidateMsg(userPhone);
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
            stopThreadAndShowMsg();
        }
    }

    private void sendValidateMsg(String userPhone) {
        try {
            // 生成验证码
            final String validateMsg = CommonUtil.getValidateNumberMsg();
            // 发送开始
            String user = "doujin";
            String password = "DouJin134";
            String content = URLEncoder.encode("尊敬的客户，您好，欢迎使用【斗金直播】APP，您的验证码为:"
                    + validateMsg + "，祝您投资愉快！", "GBK");
            String urlString = "http://123.150.41.87:36988/cgi-bin/sendsms?";
            StringBuffer param = new StringBuffer();
            param.append("username=");
            param.append(user);
            param.append("&password=");
            param.append(password);
            param.append("&to=");
            param.append(userPhone);
            param.append("&text=");
            param.append(content);
            param.append("&msgtype=1");

            String httpContent = urlString + param.toString();

            try {
                String reqParam = param.toString();
                URL url = new URL(httpContent);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setConnectTimeout(30 * 1000);
                connection.setRequestMethod("GET");
                connection.setUseCaches(false);
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", String.valueOf(param.length()));
                connection.setDoInput(true);
                connection.connect();
                OutputStreamWriter out = new OutputStreamWriter(connection
                        .getOutputStream(), "UTF-8");
                out.write(reqParam);
                out.flush();
                out.close();
                DataInputStream inputStream = new DataInputStream(connection.getInputStream());
                byte[] inBuf = new byte[inputStream.available()];
                int len = inBuf.length;
                int off = 0;
                int ret = 0;
                while ((ret = inputStream.read(inBuf, off, len)) > 0) {
                    off += ret;
                    len -= ret;
                }
                final String result = new String(inBuf, 0, off, "UTF-8");
                if (TextUtils.equals("0", result)) {
                    // 成功
                    onSMSListener.checkValidateSuccess(validateMsg);

                } else {
                    onSMSListener.failed("短信发送失败,错误代码" + result);

                }
                inputStream.close();
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
                stopThreadAndShowMsg();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            stopThreadAndShowMsg();
        }
    }

    private void stopThreadAndShowMsg() {
        onSMSListener.failed( "短信发送失败,请重试");
        // 停止线程
        if (buttonChangeThread != null) {
            runThread = false;
        }
    }

    // 根据回调参数判断验证码是否正确
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == -9) {
                onSMSListener.buttonChange(false,"重新发送(" + sendMessageSeconds-- + ")");
            } else if (msg.what == -8) {
                onSMSListener.buttonChange(true,"获取验证码");
            } else {
                onSMSListener.failed("亲，短信验证码不正确哦");
            }
        }
    };


    /**
     * 提交验证码
     *
     * @param userPhone
     * @param validateCode
     */
    public void checkValidate(String userPhone, String validateCode, String sendValidateCode,String password) {
        if (TextUtils.isEmpty(userPhone)) {
            onSMSListener.failed("手机号码不能为空");
            return;
        }
        if (!CommonUtil.isMobileNO(userPhone)) {
            onSMSListener.failed("亲，手机号码格式不正确哦");
            return;
        }
        if (TextUtils.isEmpty(validateCode)) {
            onSMSListener.failed("亲，验证码不能为空哦");
            return;
        }
        if (!validateCode.toUpperCase().equals(sendValidateCode)) {
            onSMSListener.failed("亲，验证码不正确哦");
            return;
        }
        resetpasswords(userPhone,password);
    }

    private void resetpasswords(String userPhone, final String password) {
        if (TextUtils.isEmpty(password)) {
            onSMSListener.failed("密码不能为空");
            return;
        }
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        MineService mineService = retrofit.create(MineService.class);
        Call<LoginBean> responseBodyCall = mineService.forgotPwd(new ForgotPwdPostBean(userPhone, password));
        responseBodyCall.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean loginBean = response.body();
                if (loginBean!=null) {
                    int syscode = loginBean.getSyscode();
                    if (0==syscode){
                        SharedPreferencesMgr.saveMima(password);
                        onSMSListener.resetSuccess();
                    }else {
                        onSMSListener.failed("密码设置失败");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }




    /**
     * 提交验证码
     *
     * @param userPhone
     * @param validateCode
     */
    public void useRegister(String userPhone, String validateCode, String sendValidateCode,String password) {
        if (TextUtils.isEmpty(userPhone)) {
            onSMSListener.failed("手机号码不能为空");
            return;
        }
        if (!CommonUtil.isMobileNO(userPhone)) {
            onSMSListener.failed("亲，手机号码格式不正确哦");
            return;
        }
        if (TextUtils.isEmpty(validateCode)) {
            onSMSListener.failed("亲，验证码不能为空哦");
            return;
        }
        if (!validateCode.toUpperCase().equals(sendValidateCode)) {
            onSMSListener.failed("亲，验证码不正确哦");
            return;
        }
        loginVisitor(userPhone,password);
    }

    private void loginVisitor(final String userPhone, final String password) {
        if (TextUtils.isEmpty(password)) {
            onSMSListener.failed("密码不能为空");
            return;
        }
        Retrofit retrofit = CommonUtil.retrofit(ConstanceValue.testurl);
        final MineService mineService = retrofit.create(MineService.class);
        Call<LoginBean> loginBeanCall = mineService.guestLogin(new UseRegisterPostBean(userPhone, "1"));
        loginBeanCall.enqueue(new Callback<LoginBean>() {
            @Override
            public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                LoginBean loginBean = response.body();
                if (loginBean!=null) {
                    int syscode = loginBean.getSyscode();
                    if (0==syscode){
                        final int businesscode = loginBean.getData().getBusinesscode();
                        if (100 == businesscode || 106 == businesscode) {
                            Call<LoginBean> forgotPwd = mineService.forgotPwd(new ForgotPwdPostBean(userPhone, password));
                            forgotPwd.enqueue(new Callback<LoginBean>() {
                                @Override
                                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {
                                    LoginBean loginBean = response.body();
                                    if (loginBean!=null) {
                                        int syscode = loginBean.getSyscode();
                                        if (0==syscode){
                                            int code = loginBean.getData().getBusinesscode();
                                            if (100==code||106==code) {
                                                LoginBean.DataBean.UsersBean usersBean = loginBean.getData().getUsers();
                                                if (usersBean != null) {
                                                    SharedPreferencesMgr.setuserid(usersBean.getYonghuid());
                                                    SharedPreferencesMgr.saveUserIcon(usersBean.getYonghutouxiang());
                                                    SharedPreferencesMgr.saveUserMark(usersBean.getYonghubiaozhu());
                                                    int zhanghaoleixing = usersBean.getZhanghaoleixing();
                                                    SharedPreferencesMgr.setZhanghaoleixing(zhanghaoleixing);
                                                    String username = isGeneral(zhanghaoleixing) ? usersBean.getYonghunicheng() : usersBean.getYonghuxingming();
                                                    SharedPreferencesMgr.saveUsername(username);
                                                    SharedPreferencesMgr.saveUserActivation(usersBean.getShifoushijihuoyonghu());
                                                    SharedPreferencesMgr.saveZhanghao(userPhone);
                                                    SharedPreferencesMgr.saveMima(password);
                                                    onSMSListener.resetSuccess();
                                                }
                                            }
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<LoginBean> call, Throwable t) {

                                }
                            });

                        }else if (101 == businesscode) {
                            onSMSListener.failed("当前账号已认证实盘账户，请从实盘账户登录！");
                        } else {
                            onSMSListener.failed("验证失败,请稍后再试");
                        }
                    }else if (-1 == syscode) {
                        onSMSListener.failed("服务器发生异常");
                    } else if (-2 == syscode) {
                        onSMSListener.failed("参数不正确");
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginBean> call, Throwable t) {

            }
        });
    }

    /**
     * 是否是普通用户
     *
     * @param permission 用户权限
     * @return
     */
    private boolean isGeneral(int permission) {
        if (2 == permission || 3 == permission || 4 == permission || 5 == permission || 6 == permission) {
            return false;
        }
        return true;
    }
}
