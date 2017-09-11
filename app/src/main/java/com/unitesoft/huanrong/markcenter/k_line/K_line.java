package com.unitesoft.huanrong.markcenter.k_line;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bigkoo.svprogresshud.SVProgressHUD;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.markcenter.suanfa.KDJ;
import com.unitesoft.huanrong.markcenter.test.MyBottomMarkerView;
import com.unitesoft.huanrong.markcenter.test.MyLeftMarkerView;
import com.unitesoft.huanrong.markcenter.test.MyLineChart;
import com.unitesoft.huanrong.markcenter.test.MyRightMarkerView;
import com.unitesoft.huanrong.markcenter.test.MyXAxis;
import com.unitesoft.huanrong.markcenter.test.MyYAxis;
import com.unitesoft.huanrong.widget.activity.BaseActivity;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class K_line extends BaseActivity implements OnDataCenterReceiveListener, View.OnClickListener {
    private CombinedChart mChart;
    private CombinedChart bar;
    private MyLineChart lineChart;
    private Button btn_fenshi, btn_rixian, btn_5fenshi, btn_15fenshi, btn_30fenshi, btn_60fenshi;
    private TextView bar_text, dif_text, dea_text, name;
    private int itemcount;
    private LineData lineData;
    private LineData barLineData;
    private LineData lineData_munite;
    private CandleData candleData;
    private CombinedData combinedData;
    private ArrayList<String> xVals = new ArrayList<>();
    private ArrayList<String> xVals_munite = new ArrayList<>();
    private List<CandleEntry> candleEntries = new ArrayList<>();
    private List<BarEntry> barEntries = new ArrayList<>();
    private String pingtai, pinzhong, marktitle;
    private RequestQueue mRequestQueue;
    private ImageView jiantou;


    private int colorHomeBg;
    private int colorLine;
    private int colorText;
    private int colorMa5;
    private int colorMa10;
    private int colorMa20;
    private int number_socket = 0;//设置显示条数


    private static final int PORT = 9123;
    private static final String CHAR_SET = ChangeCharUtil.UTF_8;
    private static final int THEAD_POOL_NUM = 5;
    /**
     * The client session
     */
    private IoSession clientSession;
    // 焦点是否在行情中心
    private ExecutorService service = null;
    // 请求socket的数据
    private String currentRequestString = null;
    // 类型集合
    private String[] types;

    private List<NewBean> stockBeans = new ArrayList<>();

    private List<NewBean> datas;
    private List<NewBean> datas_munite;
    //    分时图
    ArrayList<Entry> line_minute = new ArrayList<Entry>();
    //均线
    ArrayList<Entry> ma5Entries = new ArrayList<Entry>();
    ArrayList<Entry> ma10Entries = new ArrayList<Entry>();
    ArrayList<Entry> ma20Entries = new ArrayList<Entry>();
    private float o, c, h, low;
    private String t, zhouqi;
    MyXAxis xAxisLine;
    MyYAxis axisRightLine;
    MyYAxis axisLeftLine;
    SparseArray<String> stringSparseArray;
    /**
     * 偏移量的判定（a为0的时候设置偏移）
     */
    private int a;

    private int m;
    /**
     * MACD和KDJ的判定
     */
    private int b;
    /**
     * 分时图(k为1的时候画分时图的线)
     */
    private int k;
    private LinearLayout  macd_lay2;
    private TextView  systemtime;
    private Button macd_text, kdj_text;
    private TextView text_open, text_hight, text_low, text_close, text_newprice, tv_change, tv_changeExtent;
    List<Double> floatList = new ArrayList<>();
    List<Double> difList = new ArrayList<>();
    List<Double> deaList = new ArrayList<>();
    List<Double> macdList = new ArrayList<>();
    ArrayList<Entry> difentries = new ArrayList<>();
    ArrayList<Entry> deaentries = new ArrayList<>();

    ArrayList<Float> ks = new ArrayList<Float>();
    ArrayList<Float> ds = new ArrayList<Float>();
    ArrayList<Float> js = new ArrayList<Float>();

    ArrayList<Entry> kentries = new ArrayList<>();
    ArrayList<Entry> dentries = new ArrayList<>();
    ArrayList<Entry> jentries = new ArrayList<>();

    private SVProgressHUD mSVProgressHUD;


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.macd:
                b = 0;
                m = 0;
                name.setText("MACD(12,26,9)");
                bar.notifyDataSetChanged();
                bar.invalidate();
                break;
            case R.id.kdj:
                b = 1;
                m = 0;
                name.setText("KDJ(9,3,3)");
                bar.notifyDataSetChanged();
                bar.invalidate();
                break;

            case R.id.btn_tominutes:
                mSVProgressHUD.showWithStatus("加载中...");
                zhouqi = "5";
                k = 1;
                inintdata_munite();
                initdata();
                lineChart.setVisibility(View.VISIBLE);
                mChart.setVisibility(View.GONE);
                bar.setVisibility(View.GONE);
                macd_lay2.setVisibility(View.GONE);
                mSVProgressHUD.dismiss();
                break;
            case R.id.btn_rixian:
                mSVProgressHUD.showWithStatus("加载中...");
                zhouqi = "1d";
                number_socket = 0;
                k = 0;
                initdata();
                lineChart.setVisibility(View.GONE);
                mChart.setVisibility(View.VISIBLE);
                bar.setVisibility(View.VISIBLE);
                macd_lay2.setVisibility(View.VISIBLE);
                mSVProgressHUD.dismiss();
                break;
            case R.id.btn_5fenxian:
                mSVProgressHUD.showWithStatus("加载中...");
                zhouqi = "5";
                number_socket = 0;
                k = 0;
                initdata();
                lineChart.setVisibility(View.GONE);
                mChart.setVisibility(View.VISIBLE);
                bar.setVisibility(View.VISIBLE);
                macd_lay2.setVisibility(View.VISIBLE);
                mSVProgressHUD.dismiss();
                break;
            case R.id.btn_15fenxian:
                mSVProgressHUD.showWithStatus("加载中...");
                zhouqi = "15";
                number_socket = 0;
                k = 0;
                initdata();
                lineChart.setVisibility(View.GONE);
                mChart.setVisibility(View.VISIBLE);
                bar.setVisibility(View.VISIBLE);
                macd_lay2.setVisibility(View.VISIBLE);
                mSVProgressHUD.dismiss();
                break;
            case R.id.btn_30fenxian:
                mSVProgressHUD.showWithStatus("加载中...");
                zhouqi = "30";
                number_socket = 0;
                k = 0;
                initdata();
                lineChart.setVisibility(View.GONE);
                mChart.setVisibility(View.VISIBLE);
                bar.setVisibility(View.VISIBLE);
                macd_lay2.setVisibility(View.VISIBLE);
                mSVProgressHUD.dismiss();
                break;
            case R.id.btn_60fenxian:
                mSVProgressHUD.showWithStatus("加载中...");
                zhouqi = "60";
                number_socket = 0;
                k = 0;
                initdata();
                lineChart.setVisibility(View.GONE);
                mChart.setVisibility(View.VISIBLE);
                bar.setVisibility(View.VISIBLE);
                macd_lay2.setVisibility(View.VISIBLE);
                mSVProgressHUD.dismiss();
                break;
            default:
                break;
        }

    }

    private void initdata() {
        /**
         *       接收历史数据
         */
        String utfpinzhong = null;
        try {
            utfpinzhong = URLEncoder.encode(pinzhong, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://hangqing.yun066.com/home/Gethangqingls?type=" + pingtai + "&code=" + utfpinzhong + "&timespace=" + zhouqi + "&num=100";
        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(pinzhong);
                Gson gson = new Gson();
                List<NewBean> historylist = new ArrayList<>();
                for (JsonElement user : jsonArray) {
                    //通过反射 得到UserBean.class
                    NewBean bean_history = gson.fromJson(user, new TypeToken<NewBean>() {
                    }.getType());
                    historylist.add(bean_history);

                }
                datas = historylist;
                mChart.setVisibleXRangeMaximum(42);
                bar.setVisibleXRangeMaximum(42);
                floatList.clear();
                difList.clear();
                deaList.clear();
                macdList.clear();

                for (int i = 0; i < datas.size(); i++) {
                    floatList.add(Double.valueOf(datas.get(i).getC()));
                    difList.add(HModel.getDouble(HModel.getMACD(floatList, 12, 26, 9).get("DIF")));
                    deaList.add(HModel.getDouble(HModel.getMACD(floatList, 12, 26, 9).get("DEA")));
                    macdList.add(HModel.getDouble(HModel.getMACD(floatList, 12, 26, 9).get("MACD")));
                }

                connectServer();
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "get请求失败" + volleyError.toString());
            }
        });
        //设置取消取消http请求标签 Activity的生命周期中的onStiop()中调用
        request.setTag("volleyget");
        mRequestQueue.add(request);
    }

    private void inintdata_munite() {
        /**
         *       接收历史数据
         */
        String utfpinzhong = null;
        try {
            utfpinzhong = URLEncoder.encode(pinzhong, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://hangqing.yun066.com/home/Gethangqingfenshils?type=" + pingtai + "&code=" + utfpinzhong;
        StringRequest request = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(pinzhong);
                Gson gson = new Gson();
                List<NewBean> list = new ArrayList<>();
                for (JsonElement user : jsonArray) {
                    //通过反射 得到UserBean.class
                    NewBean bean_history = gson.fromJson(user, new TypeToken<NewBean>() {
                    }.getType());
                    list.add(bean_history);
                }
                datas_munite = list;
            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "get请求失败" + volleyError.toString());
            }
        });
        //设置取消取消http请求标签 Activity的生命周期中的onStiop()中调用
        request.setTag("volleyget");
        mRequestQueue.add(request);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSVProgressHUD = new SVProgressHUD(this);
        mSVProgressHUD.showWithStatus("加载中...");
        stringSparseArray = setXLabels();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        pingtai = bundle.getString("pingtai");
        pinzhong = bundle.getString("pinzhong");
        marktitle = bundle.getString("mark");
        setToolBarTitle(marktitle);
        mRequestQueue = Volley.newRequestQueue(this);

        zhouqi = "5";
        mChart = (CombinedChart) findViewById(R.id.chart);
        bar = (CombinedChart) findViewById(R.id.barChart);
        lineChart = (MyLineChart) findViewById(R.id.lineChart);
        macd_lay2 = (LinearLayout) findViewById(R.id.macd_lay2);

        bar_text = (TextView) findViewById(R.id.bar);
        dif_text = (TextView) findViewById(R.id.dif);
        dea_text = (TextView) findViewById(R.id.dea);
        name = (TextView) findViewById(R.id.name);

        macd_text = (Button) findViewById(R.id.macd);
        kdj_text = (Button) findViewById(R.id.kdj);
        macd_text.setOnClickListener(this);
        kdj_text.setOnClickListener(this);
        /**
         * 添加系统时间
         */
        systemtime = (TextView) findViewById(R.id.systemtime);

        text_open = (TextView) findViewById(R.id.text_open);
        text_hight = (TextView) findViewById(R.id.text_hight);
        text_low = (TextView) findViewById(R.id.text_low);
        text_close = (TextView) findViewById(R.id.text_zuoshou);
        tv_change = (TextView) findViewById(R.id.change);
        tv_changeExtent = (TextView) findViewById(R.id.changeExtent);
        text_newprice = (TextView) findViewById(R.id.text_new_price);
        jiantou= (ImageView) findViewById(R.id.jiantou);
        btn_5fenshi = (Button) findViewById(R.id.btn_5fenxian);
        btn_5fenshi.setOnClickListener(this);
        btn_15fenshi = (Button) findViewById(R.id.btn_15fenxian);
        btn_15fenshi.setOnClickListener(this);
        btn_30fenshi = (Button) findViewById(R.id.btn_30fenxian);
        btn_30fenshi.setOnClickListener(this);
        btn_60fenshi = (Button) findViewById(R.id.btn_60fenxian);
        btn_60fenshi.setOnClickListener(this);
        btn_rixian = (Button) findViewById(R.id.btn_rixian);
        btn_rixian.setOnClickListener(this);
        btn_fenshi = (Button) findViewById(R.id.btn_tominutes);
        btn_fenshi.setOnClickListener(this);

        initChart();
        initdata();
        inintdata_munite();
        mSVProgressHUD.dismiss();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_combine;
    }

    @Override
    protected boolean isShowBacking() {
        return true;
    }

    @Override
    protected void onStop() {
        closeConnect();
        super.onStop();
    }

    public void connectServer() {

        if (null == service || (clientSession != null && clientSession.isConnected())) {
            service = Executors.newFixedThreadPool(THEAD_POOL_NUM);
            service.submit(new Runnable() {

                @Override
                public void run() {
                    connectToServer();
                    startReceive();
                }
            });
        }
    }

    /**
     * socket连接
     */
    private void connectToServer() {

        NioSocketConnector connector = new NioSocketConnector();

        DefaultIoFilterChainBuilder chain = connector.getFilterChain();

        TextLineCodecFactory factory =
                new TextLineCodecFactory(Charset.forName(CHAR_SET));

        factory.setDecoderMaxLineLength(Integer.MAX_VALUE);
        factory.setEncoderMaxLineLength(Integer.MAX_VALUE);

        chain.addLast("codec", new ProtocolCodecFilter(factory));

        connector.setHandler(new DataCenterReceiveHandler(this));
        connector.setConnectTimeoutCheckInterval(30);

        ConnectFuture cf = connector.connect(
                new InetSocketAddress(getResources().getString(R.string.socket_address), PORT));

        cf.awaitUninterruptibly();

        clientSession = cf.getSession();
    }

    @Override
    public void clientReceiveMsg(String msg) {
        JSONObject jsonObject = null;
        try {
            jsonObject = JSON.parseObject(msg);

        } catch (Exception e) {
            e.printStackTrace();
        }
        postData(jsonObject);
    }

    private void postData(JSONObject jsonObject) {
        Message message = new Message();
        message.obj = jsonObject;
        handler.sendMessage(message);
    }

    Handler handler =
            new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    JSONObject jsonObject = (JSONObject) msg.obj;
                    Log.e("TAG", "handleMessage: " + jsonObject.toString());
                    final String time = jsonObject.getString("time");
                    //获取当前最新值
                    final float last = Float.valueOf(jsonObject.getString("last"));
                    tv_changeExtent.setText(jsonObject.getString("changeExtent"));
                    tv_change.setText(jsonObject.getString("change"));
                    text_newprice.setText(jsonObject.getString("last"));
                    if (TextUtils.equals("1", jsonObject.getString("isUp"))) {
                        text_newprice.setTextColor(Color.parseColor("#ff0000"));
                        tv_change.setTextColor(Color.parseColor("#ff0000"));
                        tv_changeExtent.setTextColor(Color.parseColor("#ff0000"));
                        jiantou.setImageResource(R.mipmap.mc_redjt);
                    } else {
                        text_newprice.setTextColor(Color.parseColor("#008000"));
                        tv_change.setTextColor(Color.parseColor("#008000"));
                        tv_changeExtent.setTextColor(Color.parseColor("#008000"));
                        jiantou.setImageResource(R.mipmap.mc_greenjt);
                    }
                    text_open.setText(jsonObject.getString("open"));
                    text_hight.setText(jsonObject.getString("high"));
                    text_low.setText(jsonObject.getString("low"));
                    text_close.setText(jsonObject.getString("lastClose"));
                    systemtime.setText(HModel.getSystemTime(System.currentTimeMillis()));
/**
 * change     changeExtent     isUp
 */
                    loadChartData(last, time, datas);

                    //  传递参数，并且利用socket实现页面的不断绘制

                }
            };

    private void startReceive() {
        if (null != clientSession && clientSession.isConnected() && currentRequestString == null) {
            getSocketData();
        }
    }

    private void getSocketData() {
        if (null == clientSession || !clientSession.isConnected()) {
            return;
        }

        List<String> list = new ArrayList<>();
        list.add(pinzhong);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(pingtai, list);
        currentRequestString = JSON.toJSONString(jsonObject);
        if (!TextUtils.isEmpty(currentRequestString)) {
            clientSession.write(currentRequestString);
            Log.e("请求的数据", currentRequestString);
        }
    }

    /**
     * 初始化
     */
    private void initChart() {

        colorHomeBg = getResources().getColor(R.color.home_page_bg);
        colorLine = getResources().getColor(R.color.common_divider);
        colorText = getResources().getColor(R.color.text_grey_light);
        colorMa5 = getResources().getColor(R.color.ma5);
        colorMa10 = getResources().getColor(R.color.ma10);
        colorMa20 = getResources().getColor(R.color.ma20);


        bar.setDescription("");
        bar.setDrawGridBackground(true);
        bar.setBackgroundColor(colorHomeBg);
        bar.setGridBackgroundColor(colorHomeBg);

        bar.setScaleYEnabled(false);//y轴不支持缩放
        bar.setScaleXEnabled(true); //是否可以缩放 仅x轴
        bar.setPinchZoom(true);//缩放
        bar.setDrawValueAboveBar(false);
        bar.fitScreen();
        bar.setNoDataText("");//无数据默认显示的内容
        bar.setAutoScaleMinMaxEnabled(true);//自动计算最大最小值
        bar.setDoubleTapToZoomEnabled(false);//可进行手势缩放
        bar.setDragEnabled(true);//是否可以进行拖拽
        bar.moveViewToX(itemcount);           //与x轴进行对应,默认进入后视图定义在最右边
        bar.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.LINE});
        Legend barChartLegend = bar.getLegend();
        barChartLegend.setEnabled(false);

        //BarYAxisFormatter  barYAxisFormatter=new BarYAxisFormatter();
        //bar x y轴
        XAxis xAxisBar = bar.getXAxis();
        xAxisBar.setDrawLabels(true);
        xAxisBar.setDrawGridLines(false);
        xAxisBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        xAxisBar.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisBar.setEnabled(false);
        xAxisBar.setDrawAxisLine(false);
        xAxisBar.setGridColor(getResources().getColor(R.color.minute_grayLine));

        YAxis axisLeftBar = bar.getAxisLeft();
        axisLeftBar.setEnabled(false);
        YAxis axisRightBar = bar.getAxisRight();
        axisRightBar.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                DecimalFormat df = new DecimalFormat("0.00");
                return String.valueOf(df.format(value));//这句是重点!
            }
        });
        axisRightBar.setLabelCount(5, true);
        axisRightBar.setDrawGridLines(false);
        axisRightBar.setDrawAxisLine(true);
        axisRightBar.setTextColor(getResources().getColor(R.color.minute_zhoutv));
        axisRightBar.setDrawLabels(true);
        axisRightBar.setSpaceTop(10);
        axisRightBar.setSpaceBottom(10);
        axisRightBar.setDrawZeroLine(false);
        axisRightBar.setZeroLineColor(Color.DKGRAY);
        axisRightBar.setZeroLineWidth(1f);

        /********************lineChart   分时图*******************************************/
        lineChart.setDescription("");
        lineChart.setDrawGridBackground(true);
        lineChart.setBackgroundColor(colorHomeBg);
        lineChart.setGridBackgroundColor(colorHomeBg);

        lineChart.setScaleYEnabled(false);//y轴不支持缩放
        lineChart.setScaleXEnabled(false); //是否可以缩放 仅x轴
        lineChart.setPinchZoom(false);//缩放
//        lineChart.setDrawValueAboveBar(false);
        lineChart.fitScreen();
        lineChart.setNoDataText("");//无数据默认显示的内容
        lineChart.setAutoScaleMinMaxEnabled(true);//自动计算最大最小值
        lineChart.setDoubleTapToZoomEnabled(false);//可进行手势缩放
        lineChart.setDragEnabled(true);//是否可以进行拖拽

        Legend lineChartLegend = lineChart.getLegend();
        lineChartLegend.setEnabled(false);

        //BarYAxisFormatter  barYAxisFormatter=new BarYAxisFormatter();
        //bar x y轴
        xAxisLine = lineChart.getXAxis();
        xAxisLine.setAvoidFirstLastClipping(true);  //x轴上起点和终点坐标数显示不完全


        xAxisLine.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxisLine.setDrawGridLines(true);
        xAxisLine.setGridColor(colorLine);
        xAxisLine.setTextColor(colorText);
        xAxisLine.setEnabled(true);
        xAxisLine.setSpaceBetweenLabels(15);
        xAxisLine.enableGridDashedLine(15, 5, 0);//网格线设置为虚线，第一个参数表示长度，第二个参数表示虚线间隔，第三个参数表示起始坐标

        //y轴属性设置和定义
        axisLeftLine = lineChart.getAxisLeft();
        axisLeftLine.setEnabled(false);//取消y轴按刻度均匀分配
        axisLeftLine.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        axisLeftLine.setSpaceBottom(0);    //Y轴坐标距底有多少距离，即留白
        //设置y轴坐标的小数点位数
        axisRightLine = lineChart.getAxisRight();
        axisRightLine.setValueFormatter(
                new YAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, YAxis yAxis) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        return String.valueOf(df.format(value));//这句是重点!
                    }
                });
        axisRightLine.setLabelCount(5, true);//y轴显示的刻度数目
        axisRightLine.setDrawGridLines(true);
        axisRightLine.setDrawAxisLine(true);
        axisRightLine.setGridColor(colorLine);
        axisRightLine.setTextColor(colorText);
        axisRightLine.enableGridDashedLine(15, 5, 0);
        axisRightLine.setSpaceTop(10);    //Y轴坐标距顶有多少距离，即留白
        axisRightLine.setSpaceBottom(10);    //Y轴坐标距底有多少距离，即留白

        lineChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
            }

            @Override
            public void onNothingSelected() {
                lineChart.highlightValue(null);
            }
        });
        /****************************************************************/
        mChart.setDescription("");
        mChart.setDrawGridBackground(true);
        mChart.setBackgroundColor(colorHomeBg);
        mChart.setGridBackgroundColor(colorHomeBg);

        mChart.setScaleYEnabled(false);//y轴不支持缩放
        mChart.setScaleXEnabled(true); //是否可以缩放 仅x轴
        mChart.setPinchZoom(true);//缩放
        mChart.setDrawValueAboveBar(false);
        mChart.fitScreen();
        mChart.setNoDataText("");//无数据默认显示的内容
        mChart.setAutoScaleMinMaxEnabled(true);//自动计算最大最小值
        mChart.setDoubleTapToZoomEnabled(false);//可进行手势缩放
        mChart.setDragEnabled(true);//是否可以进行拖拽

// 初始化视图默认放大1.4倍
        mChart.setDrawOrder(new CombinedChart.DrawOrder[]{CombinedChart.DrawOrder.CANDLE, CombinedChart.DrawOrder.LINE});
        //x轴的属性设置和定义
        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(true);
        xAxis.setGridColor(colorLine);
        xAxis.setTextColor(colorText);
        xAxis.setEnabled(true);
        xAxis.setSpaceBetweenLabels(4);
        xAxis.enableGridDashedLine(15, 5, 0);//网格线设置为虚线，第一个参数表示长度，第二个参数表示虚线间隔，第三个参数表示起始坐标
        //y轴属性设置和定义
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setEnabled(false);//取消y轴按刻度均匀分配
        leftAxis.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        leftAxis.setSpaceBottom(0);    //Y轴坐标距底有多少距离，即留白
        //设置y轴坐标的小数点位数
        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setValueFormatter(
                new YAxisValueFormatter() {
                    @Override
                    public String getFormattedValue(float value, YAxis yAxis) {
                        DecimalFormat df = new DecimalFormat("0.00");
                        return String.valueOf(df.format(value));//这句是重点!
                    }
                });
        rightAxis.setLabelCount(5, true);//y轴显示的刻度数目
        rightAxis.setDrawGridLines(true);
        rightAxis.setDrawAxisLine(true);
        rightAxis.setGridColor(colorLine);
        rightAxis.setTextColor(colorText);
        rightAxis.setSpaceTop(0);    //Y轴坐标距顶有多少距离，即留白
        rightAxis.setSpaceBottom(0);    //Y轴坐标距底有多少距离，即留白
        rightAxis.enableGridDashedLine(15, 5, 0);

        // 将K线控的滑动事件传递给交易量控件
        mChart.setOnChartGestureListener(new CoupleChartGestureListener(mChart, new Chart[]{bar}));
        // 将交易量控件的滑动事件传递给K线控件
        bar.setOnChartGestureListener(new CoupleChartGestureListener(bar, new Chart[]{mChart}));


        //十字标的监控
        mChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry entry, int i, Highlight highlight) {

                bar.highlightValues(new Highlight[]{highlight});
                int index = entry.getXIndex();

                if (0 == b) {
                    int dex = index > deaList.size() - 1 ? deaList.size() - 1 : index;
                    float bar = HModel.getFloat((float) (2 * (difList.get(dex) - deaList.get(dex))));
                    bar_text.setText("BAR: " + bar);
                    dif_text.setText("DIF: " + HModel.getDouble(difList.get(dex)));
                    dea_text.setText("DEA: " + HModel.getDouble(deaList.get(dex)));
                    bar_text.setTextColor(colorMa5);
                    dif_text.setTextColor(colorMa10);
                    dea_text.setTextColor(colorMa20);
                } else {
                    bar_text.setText("K:" + HModel.getDouble(ks.get(index)));
                    dif_text.setText("D:" + HModel.getDouble(ds.get(index)));
                    dea_text.setText("J:" + HModel.getDouble(js.get(index)));
                    bar_text.setTextColor(colorMa5);
                    dif_text.setTextColor(colorMa10);
                    dea_text.setTextColor(colorMa20);
                }
//                CandleEntry candleEntry = (CandleEntry) entry;
//
//                float change = (candleEntry.getClose() - candleEntry.getOpen()) / candleEntry.getOpen();
//                NumberFormat nf = NumberFormat.getPercentInstance();
//                nf.setMaximumFractionDigits(2);
//                String changePercentage = nf.format(Double.valueOf(String.valueOf(change)));
//                Log.d("qqq", "最高" + candleEntry.getHigh() + " 最低" + candleEntry.getLow() +
//                        " 开盘" + candleEntry.getOpen() + " 收盘" + candleEntry.getClose() +
//                        " 涨跌幅" + changePercentage);
//                text_open.setText(String.valueOf(candleEntry.getOpen()));
//                text_hight.setText(String.valueOf(candleEntry.getHigh()));
//                text_low.setText(String.valueOf(candleEntry.getLow()));
//                text_close.setText(String.valueOf(candleEntry.getClose()));
//                text_time.setText(String.valueOf(xVals.get(entry.getXIndex())));
//                text_zd.setText(String.valueOf(changePercentage));

                NewBean d = stockBeans.get(index);
                float change = (d.getC() - d.getO()) / d.getO();
                NumberFormat nf = NumberFormat.getPercentInstance();
                nf.setMaximumFractionDigits(2);
                String changePercentage = nf.format(Double.valueOf(String.valueOf(change)));
                Log.d("qqq", "最高" + d.getH() + " 最低" + d.getL() +
                        " 开盘" + d.getO() + " 收盘" + d.getC() +
                        " 涨跌幅" + changePercentage);
                text_open.setText(String.valueOf(d.getO()));
                text_hight.setText(String.valueOf(d.getH()));
                text_low.setText(String.valueOf(d.getL()));
                text_close.setText(String.valueOf(d.getC()));

/**
 * 获取十字光标所对应的均线数值
 */
                String ma5_p, ma10_p, ma20_p;

                if (entry.getXIndex() - 4 < 0) {
                    ma5_p = "0.000   ";
                } else if (entry.getXIndex() - 4 > stockBeans.size() - 5) {
                    ma5_p = "暂无数据";
                } else {
                    DecimalFormat df = new DecimalFormat("##.00");
                    ma5_p = df.format(ma5Entries.get(entry.getXIndex() - 4).getVal());
                }
                if (entry.getXIndex() - 9 < 0) {
                    ma10_p = "0.000   ";
                } else if (entry.getXIndex() - 9 > stockBeans.size() - 10) {
                    ma10_p = "暂无数据";
                } else {
                    DecimalFormat df = new DecimalFormat("##.00");
                    ma10_p = df.format(ma10Entries.get(entry.getXIndex() - 9).getVal());
                }
                if (entry.getXIndex() - 19 < 0) {
                    ma20_p = "0.000   ";
                } else if (entry.getXIndex() - 19 > stockBeans.size() - 20) {
                    ma20_p = "暂无数据";
                } else {
                    DecimalFormat df = new DecimalFormat("##.00");
                    ma20_p = df.format(ma20Entries.get(entry.getXIndex() - 19).getVal());
                }
                /**
                 * 均线数据的实时显示
                 */
                Legend legend = mChart.getLegend();
                int[] colors = {colorMa5, colorMa10, colorMa20};
                final String[] labels = {"MA5:" + ma5_p + "   ", "MA10:" + ma10_p + "   ", "MA20:" + ma20_p + "   "};
                legend.setCustom(colors, labels);
                legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
                legend.setTextColor(Color.WHITE);


            }

            @Override
            public void onNothingSelected() {
                mChart.highlightValue(null);
            }
        });

    }

    /**
     * 图表界面的绘制
     *
     * @param l
     * @param nt
     * @param list
     */

    private void loadChartData(float l, String nt, List<NewBean> list) {
        if (k != 1) {
            number_socket = number_socket + 1;
            mChart.resetTracking();


            stockBeans = list;
            /**
             *进行对比，确认实时k线的跳动
             * */
            String JDtime = stockBeans.get(stockBeans.size() - 1).getT();
            int jdtime = Integer.parseInt(JDtime);
            int nowtime = Integer.parseInt(nt);
            if (jdtime < nowtime) {
                o = l;
                c = l;
                h = l;
                low = l;
                t = String.valueOf(((jdtime / 300) + 1) * 300);
                stockBeans.add(stockBeans.size(), new NewBean(o, h, low, c, t));
                floatList.add(floatList.size(), (double) c);

                HashMap<String, Double> h = HModel.getMACD(floatList, 12, 26, 9);
                difList.add(difList.size(), h.get("DIF"));

                deaList.add(deaList.size(), h.get("DEA"));

                macdList.add(macdList.size(), h.get("MACD"));

                kentries.clear();
                dentries.clear();
                jentries.clear();
                KDJ kdj = new KDJ(stockBeans);
                ks = kdj.getK();
                ds = kdj.getD();
                js = kdj.getJ();
            } else {
                t = String.valueOf(jdtime);
                o = stockBeans.get(stockBeans.size() - 1).getO();
                c = l;
                if (l > h) {
                    h = l;
                } else {
                    h = stockBeans.get(stockBeans.size() - 1).getH();
                }
                if (l < low) {
                    low = l;
                } else {
                    low = stockBeans.get(stockBeans.size() - 1).getL();
                }
//            移除，添加，让实体在原有横坐标跳动，而不是增加实体
                stockBeans.remove(stockBeans.size() - 1);
                Log.e("stockBeans", stockBeans.size() + "");
                stockBeans.add(stockBeans.size(), new NewBean(o, h, low, c, t));
                Log.e("stockBeans", stockBeans.size() + "");

                floatList.remove(floatList.size() - 1);
                floatList.add(floatList.size(), (double) c);

                HashMap<String, Double> h = HModel.getMACD(floatList, 12, 26, 9);
                difList.remove(difList.size() - 1);
                difList.add(difList.size(), h.get("DIF"));

                deaList.remove(deaList.size() - 1);
                deaList.add(deaList.size(), h.get("DEA"));

                macdList.remove(macdList.size() - 1);
                macdList.add(macdList.size(), h.get("MACD"));


                kentries.clear();
                dentries.clear();
                jentries.clear();
                KDJ kdj = new KDJ(stockBeans);
                ks = kdj.getK();
                ds = kdj.getD();
                js = kdj.getJ();
            }


            candleEntries = HModel.getCandleEntries(stockBeans, 0);

            itemcount = candleEntries.size();

            xVals = new ArrayList<>();

            for (int i = 0; i < stockBeans.size(); i++) {
                Log.e("time", i + "=====" + stockBeans.get(i).getT());
                xVals.add(HModel.getStrTime(stockBeans.get(i).getT()));
            }

            combinedData = new CombinedData(xVals);
        /*k line*/
            candleData = generateCandleData();
            combinedData.setData(candleData);

            ma5Entries.clear();
            ma10Entries.clear();
            ma20Entries.clear();
            /**
             *  均线算法
             */

            for (int i = 0; i < stockBeans.size(); i++) {
                Log.e("stockBeans1", stockBeans.size() + "");
                float ma5;
                if (i >= 4) {
                    ma5 = 0;
                    for (int a = i - 4; a <= i; a++) {
                        ma5 += stockBeans.get(a).getC();
                    }
                    ma5 /= 5;
                    ma5Entries.add(new Entry(ma5, i));
                }
                float ma10;
                if (i >= 9) {
                    ma10 = 0;
                    for (int a = i - 9; a <= i; a++) {
                        ma10 += stockBeans.get(a).getC();
                    }
                    ma10 /= 10;
                    ma10Entries.add(new Entry(ma10, i));
                }
                float ma20;
                if (i >= 19) {
                    ma20 = 0;
                    for (int a = i - 19; a <= i; a++) {
                        ma20 += stockBeans.get(a).getC();
                    }
                    ma20 /= 20;
                    ma20Entries.add(new Entry(ma20, i));
                }

            }
            Legend legend = mChart.getLegend();
            int[] colors = {colorMa5, colorMa10, colorMa20};
            DecimalFormat df = new DecimalFormat("##.00");
            final String[] labels = {"MA5:" + df.format(ma5Entries.get(stockBeans.size() - 5).getVal()) + "   ", "MA10:" + df.format(ma10Entries.get(stockBeans.size() - 10).getVal()) + "   ", "MA20:" + df.format(ma20Entries.get(stockBeans.size() - 20).getVal()) + "   "};
            legend.setCustom(colors, labels);
            legend.setForm(Legend.LegendForm.CIRCLE);
            legend.setFormSize(5f);
            legend.setPosition(Legend.LegendPosition.ABOVE_CHART_RIGHT);
            legend.setTextColor(Color.WHITE);
            lineData = generateMultiLineData(
                    generateLineDataSet(ma5Entries, colorMa5, "ma5", true),
                    generateLineDataSet(ma10Entries, colorMa10, "ma10", true),
                    generateLineDataSet(ma20Entries, colorMa20, "ma20", true)
            );


            final CombinedData combinedData2 = new CombinedData(xVals);

            final ViewPortHandler viewPortHandlerBar = bar.getViewPortHandler();
            viewPortHandlerBar.setMaximumScaleX(culcMaxscale(xVals.size()));
            Matrix touchmatrix = viewPortHandlerBar.getMatrixTouch();
            final float xscale = 1;
            touchmatrix.postScale(xscale, 1f);

            if (0 == b) {
                difentries.clear();
                deaentries.clear();
                for (int i = 0; i < difList.size(); i++) {
                    double k = difList.get(i);
                    difentries.add(new Entry((float) k, i));
                }

                for (int i = 0; i < deaList.size(); i++) {
                    double k = deaList.get(i);
                    deaentries.add(new Entry((float) k, i));
                }
                barLineData = generateMultiLineData(
                        generateLineDataSet(deaentries, colorMa20, "dea", false),
                        generateLineDataSet(difentries, colorMa10, "dif", false)
                );

                List<Integer> integers = new ArrayList<Integer>();
                int green = Color.GREEN;
                int red = Color.RED;
                barEntries.clear();
                for (int i = 0; i < macdList.size(); i++) {
                    double stock = macdList.get(i);
                    float k = (float) stock;
                    BarEntry entry = new BarEntry(k, i);
                    barEntries.add(entry);
                    if (k > 0) {
                        integers.add(red);
                    } else {
                        integers.add(green);
                    }
                }

                BarDataSet set;

                set = new BarDataSet(barEntries, "");
                set.setColors(integers);
                set.setDrawValues(false);
                set.setBarSpacePercent(25f);
                set.setHighlightEnabled(true);
                set.setAxisDependency(YAxis.AxisDependency.RIGHT);
                set.setValueTextColors(integers);

                BarData data = new BarData(xVals, set);
                data.setValueTextSize(13f);

                combinedData2.setData(barLineData);
                combinedData2.setData(data);

            } else {

                for (int i = 0; i < ks.size(); i++) {
                    float k = ks.get(i);
                    kentries.add(new Entry(k, i));
                }
                for (int i = 0; i < ds.size(); i++) {
                    float k = ds.get(i);
                    dentries.add(new Entry(k, i));
                }
                for (int i = 0; i < js.size(); i++) {
                    float k = js.get(i);
                    jentries.add(new Entry(k, i));
                }
                barLineData = generateMultiLineData(
                        generateLineDataSet(kentries, colorMa5, "k", false),
                        generateLineDataSet(dentries, colorMa10, "d", false),
                        generateLineDataSet(jentries, colorMa20, "j", false)
                );
                combinedData2.setData(barLineData);
            }

            if (0 == m) {
                m = 1;
                if (0 == b) {
                    float bar = HModel.getFloat((float) (2 * (difList.get(difList.size() - 1) - deaList.get(deaList.size() - 1))));
                    bar_text.setText("BAR: " + bar);
                    dif_text.setText("DIF: " + HModel.getDouble(difList.get(difList.size() - 1)));
                    dea_text.setText("DEA: " + HModel.getDouble(deaList.get(deaList.size() - 1)));
                    bar_text.setTextColor(colorMa5);
                    dif_text.setTextColor(colorMa10);
                    dea_text.setTextColor(colorMa20);
                } else {
                    bar_text.setText("K:" + HModel.getDouble(ks.get(ks.size() - 1)));
                    dif_text.setText("D:" + HModel.getDouble(ds.get(ds.size() - 1)));
                    dea_text.setText("J:" + HModel.getDouble(js.get(js.size() - 1)));
                    bar_text.setTextColor(colorMa5);
                    dif_text.setTextColor(colorMa10);
                    dea_text.setTextColor(colorMa20);
                }
            }

            if (number_socket == 1) {
                bar.setVisibleXRangeMaximum(42);
                bar.moveViewToX(itemcount);
            } else {
                bar.setVisibleXRangeMaximum(100);
            }
            bar.setData(combinedData2);
            bar.notifyDataSetChanged();
            bar.invalidate();

            Log.e("ma5Entries", ma5Entries.size() + "");
            combinedData.setData(lineData);
            mChart.setData(combinedData);//当前屏幕会显示所有的数据
            if (number_socket == 1) {
                mChart.setVisibleXRangeMaximum(42);
                mChart.moveViewToX(itemcount);           //与x轴进行对应,默认进入后视图定义在最右边
            } else {
                mChart.setVisibleXRangeMaximum(100);
            }
            final ViewPortHandler viewPortHandlerCombin = mChart.getViewPortHandler();//控制最大的缩放比例
            viewPortHandlerCombin.setMaximumScaleX(culcMaxscale(xVals.size()));
            Matrix matrixCombin = viewPortHandlerCombin.getMatrixTouch();
            final float xscaleCombin = 1;
            matrixCombin.postScale(xscaleCombin, 1f);
            mChart.notifyDataSetChanged();
            mChart.invalidate();
            if (0 == a) {
                a = 1;
                setOffset();
            }
        }
//        linedata     分时图

        if (k == 1) {
            setShowLabels(stringSparseArray);
            setMarkerView(datas_munite);
            xVals_munite = new ArrayList<>();
            long starttime = 1496181300;
            for (int i = 0; i < 289; i++) {

                starttime = starttime + 300;
                String timeString = null;
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                timeString = sdf.format(new Date(starttime * 1000l));//单位秒

                xVals_munite.add(timeString);

            }

            lineData_munite = new LineData(xVals_munite);

            line_minute.clear();

            for (int i = 0; i < datas_munite.size(); i++) {
                Log.e("datas_munite", datas_munite.size() + "");
                float fenline;
                fenline = datas_munite.get(i).getC();
                line_minute.add(new Entry(fenline, i));
            }
            NewBean data = new NewBean(0, 0, l, 0, "");
            datas_munite.add(datas_munite.size(), data);
            datas_munite.remove(datas_munite.size() - 1);
            lineData_munite = generateMultiLineData2(
                    generateLineDataSet2(line_minute, Color.WHITE, "minutes_line"));
            lineChart.setData(lineData_munite);

            lineChart.notifyDataSetChanged();
            lineChart.invalidate();
        }
    }

    /**
     * linechart属性设置
     */

    private LineDataSet generateLineDataSet(List<Entry> entries, int color, String label, boolean HorizontalHighlight) {
        LineDataSet set = new LineDataSet(entries, label);
        set.setColor(color);
        set.setLineWidth(1f);
        set.setDrawCubic(true);//圆滑曲线
        set.setDrawCircles(false);
        set.setDrawCircleHole(false);
        set.setDrawValues(false);
        set.setHighlightEnabled(true);
        set.setHighLightColor(Color.WHITE);
        set.setDrawVerticalHighlightIndicator(true);
        set.setDrawHorizontalHighlightIndicator(HorizontalHighlight);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);

        return set;

    }

    private LineDataSet generateLineDataSet2(List<Entry> entries, int color, String label) {
        LineDataSet set = new LineDataSet(entries, label);
        set.setColor(color);
        set.setLineWidth(1f);
        set.setDrawCubic(true);//圆滑曲线
        set.setDrawCircles(false);
        set.setDrawCircleHole(false);
        set.setDrawValues(false);
        set.setHighlightEnabled(true);
        set.setHighlightLineWidth(0.5f);
        set.setHighLightColor(Color.WHITE);
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);

        return set;

    }

    private LineData generateMultiLineData(LineDataSet... lineDataSets) {
        List<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < lineDataSets.length; i++) {
            dataSets.add(lineDataSets[i]);
        }

        LineData data = new LineData(xVals, dataSets);

        return data;
    }


    private LineData generateMultiLineData2(LineDataSet... lineDataSets) {
        List<ILineDataSet> dataSets = new ArrayList<>();
        for (int i = 0; i < lineDataSets.length; i++) {
            dataSets.add(lineDataSets[i]);
        }

        LineData data = new LineData(xVals_munite, dataSets);

        return data;
    }

    private CandleData generateCandleData() {

        CandleDataSet set = new CandleDataSet(candleEntries, "");
        set.setAxisDependency(YAxis.AxisDependency.RIGHT);
        set.setShadowWidth(0.7f);
        set.setHighlightEnabled(false);
        set.setDecreasingColor(Color.GREEN);
        set.setDecreasingPaintStyle(Paint.Style.FILL);
        set.setIncreasingColor(Color.RED);
        set.setIncreasingPaintStyle(Paint.Style.FILL);
        set.setNeutralColor(Color.RED);
        set.setShadowColorSameAsCandle(true);
        set.setHighlightLineWidth(0.5f);
        set.setHighLightColor(Color.WHITE);
        set.setDrawValues(false);

        CandleData candleData = new CandleData(xVals);
        candleData.addDataSet(set);

        return candleData;
    }

    private float culcMaxscale(float count) {
        float max = 1;
        max = count / 127 * 5;
        return max;
    }


    /*设置量表对齐*/
    private void setOffset() {
        float lineLeft = mChart.getViewPortHandler().offsetLeft();
        float barLeft = bar.getViewPortHandler().offsetLeft();
        float lineRight = mChart.getViewPortHandler().offsetRight();
        float barRight = bar.getViewPortHandler().offsetRight();
        float barBottom = bar.getViewPortHandler().offsetBottom();
        float offsetLeft, offsetRight;
        float transLeft = 0, transRight = 0;
 /*注：setExtraLeft...函数是针对图表相对位置计算，比如A表offLeftA=20dp,B表offLeftB=30dp,则A.setExtraLeftOffset(10),并不是30，还有注意单位转换*/
        if (barLeft < lineLeft) {
           /* offsetLeft = Utils.convertPixelsToDp(lineLeft - barLeft);
            barChart.setExtraLeftOffset(offsetLeft);*/
            transLeft = lineLeft;
        } else {
            offsetLeft = Utils.convertPixelsToDp(barLeft - lineLeft);
            mChart.setExtraLeftOffset(offsetLeft);
            transLeft = barLeft;
        }
  /*注：setExtraRight...函数是针对图表绝对位置计算，比如A表offRightA=20dp,B表offRightB=30dp,则A.setExtraLeftOffset(30),并不是10，还有注意单位转换*/
        if (barRight < lineRight) {
          /*  offsetRight = Utils.convertPixelsToDp(lineRight);
            barChart.setExtraRightOffset(offsetRight);*/
            transRight = lineRight;
        } else {
            offsetRight = Utils.convertPixelsToDp(barRight);
            mChart.setExtraRightOffset(offsetRight);
            transRight = barRight;
        }
        bar.setViewPortOffsets(transLeft, 15, transRight, barBottom);
    }


    private void setMarkerView(List<NewBean> datas) {
        MyLeftMarkerView leftMarkerView = new MyLeftMarkerView(K_line.this, R.layout.mymarkerview);
        MyRightMarkerView rightMarkerView = new MyRightMarkerView(K_line.this, R.layout.mymarkerview);
        MyBottomMarkerView bottomMarkerView = new MyBottomMarkerView(K_line.this, R.layout.mymarkerview);
        lineChart.setMarker(leftMarkerView, rightMarkerView, bottomMarkerView, datas);

    }

    private SparseArray<String> setXLabels() {
        SparseArray<String> xLabels = new SparseArray<>();
        xLabels.put(0, "06:00");
        xLabels.put(143, "18:00");
        xLabels.put(287, "06:00");
        return xLabels;
    }

    public void setShowLabels(SparseArray<String> labels) {
        xAxisLine.setXLabels(labels);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeConnect();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        closeConnect();
    }

    private void closeConnect() {

        if (null != clientSession && clientSession.isConnected()) {
            clientSession.close(false);
            currentRequestString = null;
            service = null;
        }
    }

}
