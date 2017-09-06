package com.unitesoft.huanrong.widget.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.unitesoft.huanrong.R;
import com.unitesoft.huanrong.markcenter.ListDataSave;
import com.unitesoft.huanrong.markcenter.MyOnSlipStatusListener;
import com.unitesoft.huanrong.markcenter.k_line.ChangeCharUtil;
import com.unitesoft.huanrong.markcenter.k_line.K_line;
import com.unitesoft.huanrong.markcenter.ziye.Bean;
import com.unitesoft.huanrong.markcenter.ziye.Bean_History;
import com.unitesoft.huanrong.markcenter.ziye.Bean_pinzhong;
import com.unitesoft.huanrong.markcenter.ziye.DataCenterReceiveHandler;
import com.unitesoft.huanrong.markcenter.ziye.OnDataCenterReceiveListener;
import com.unitesoft.huanrong.markcenter.ziye.SwipeListLayout;
import com.unitesoft.huanrong.markcenter.ziye.ZiXuanBean;
import com.unitesoft.huanrong.utils.CommonUtil;
import com.unitesoft.huanrong.utils.ToastUtils;

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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static android.content.ContentValues.TAG;


/**
 * Created by naive on 2017-6-28.
 */

public class MarkCenterFragment extends Fragment implements OnDataCenterReceiveListener {

    private static final int PORT = 9123;
    private static final String CHAR_SET = ChangeCharUtil.UTF_8;
    private static final int THEAD_POOL_NUM = 5;
    private IoSession clientSession;

    // 焦点是否在行情中心
    private ExecutorService service = null;
    // 请求socket的数据
    private String currentRequestString = null;
    // 类型集合
    private RequestQueue mRequestQueue;
    private LinearLayout linear_button;
    private ListView listView;
    private Spinner spinner;
    private Hq_ListAdapter mAdapter;
    private String pt_click;
    private ArrayList<String> pinzhonglist = new ArrayList<>();

    private ArrayList<String> short_name = new ArrayList<>();
    private ArrayList<String> bean_pinpingtais = new ArrayList<>();
    private List<ArrayList> list_shuju = new ArrayList<>();
    private String spinner_num = "0";
    private Set<SwipeListLayout> sets = new HashSet<>();


    private List<String> zixuan_pingtai = new ArrayList<String>();

    private Button zixuan_btn;
    private View kongbai;

    ListDataSave dataSave;

    List<ZiXuanBean> listBean = new ArrayList<>();
    private Context mContext;

    private boolean isZixuan;
    private int count = 0;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden){
            closeConnect();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_hq, container, false);
        linear_button = (LinearLayout) view.findViewById(R.id.linear_button);
        mRequestQueue = Volley.newRequestQueue(mContext);
        listView = (ListView) view.findViewById(R.id.hq_listview);
        kongbai=view.findViewById(R.id.view);
        dataSave = new ListDataSave(mContext, "zixuan");
        zixuan_btn = (Button) view.findViewById(R.id.zixuan_btn);

        zixuan_btn.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              isZixuan = true;
                                              count=0;
                                              List<String> list = new ArrayList<>();
                                              listBean.clear();
                                              listBean = dataSave.getDataList("javaBean");
                                              pinzhonglist.clear();
                                              short_name.clear();
                                              bean_pinpingtais.clear();
                                              list_shuju.clear();
                                              pt_click = "";
                                              closeConnect();
                                              for (int i = 0; i < listBean.size(); i++) {
                                                  String pingtai = listBean.get(i).getZixuanpingtai();
                                                  list.add(pingtai);
                                                  String pinzhong = listBean.get(i).getZixuanpinzhong();
                                                  pinzhonglist.add(pinzhong);
                                                  ArrayList<String> historylist = new ArrayList<>();
                                                  String o = listBean.get(i).getZixuan_o();
                                                  String h = listBean.get(i).getZixuan_h();
                                                  String l = listBean.get(i).getZixuan_l();
                                                  String c = listBean.get(i).getZixuan_c();
                                                  String t = "";
                                                  String zd = listBean.get(i).getZixuan_zd();
                                                  String zdf = listBean.get(i).getZixuan_zdf();
                                                  String low = listBean.get(i).getZixuan_low();
                                                  String isup = listBean.get(i).getIsUp();
                                                  String shortname = listBean.get(i).getZixuan_shortname();

                                                  historylist.clear();
                                                  historylist.add(o);
                                                  historylist.add(h);
                                                  historylist.add(l);
                                                  historylist.add(c);
                                                  historylist.add(t);
                                                  historylist.add(zd);
                                                  historylist.add(zdf);
                                                  historylist.add(low);
                                                  historylist.add(isup);
                                                  historylist.add(shortname);
                                                  Log.e("history", historylist.toString());
                                                  list_shuju.add(historylist);
                                                  short_name.add(shortname);

                                              }
                                              zixuan_pingtai = removeDuplicate(list);
                                              bean_pinpingtais = (ArrayList<String>) removeDuplicate(list);
                                              if (listBean.size()<=0){
                                                  listView.setVisibility(View.GONE);
                                                  kongbai.setVisibility(View.VISIBLE);
                                              }else {
                                                  listView.setVisibility(View.VISIBLE);
                                                  kongbai.setVisibility(View.GONE);
                                                  connectServer();
                                              }
                                          }
                                      }
        );

        spinner = (Spinner) view.findViewById(R.id.spinner);
        String[] mItems = getResources().getStringArray(R.array.leixingArray);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.spinner_item, mItems);
        adapter.setDropDownViewResource(R.layout.spinner_item);
        //绑定 Adapter到控件
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Log.e("spinner", String.valueOf(pos) + "========" + id);
                spinner_num = String.valueOf(pos);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        inintdata_pt();
        return view;
    }

    private void inintdata_pt() {
        /**
         *       接收平台信息
         */

        String url = "http://hangqing.yun066.com/home/GetTradingPlatform";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                if (TextUtils.isEmpty(s)) {
                    ToastUtils.showToast(mContext, "暂无平台数据");
                    return;
                }
                JsonParser parser = new JsonParser();
                //将JSON的String 转成一个JsonArray对象
                JsonArray jsonArray = parser.parse(s).getAsJsonArray();


                Gson gson = new Gson();
                final ArrayList<Bean> userBeanList = new ArrayList<>();

                //加强for循环遍历JsonArray
                for (JsonElement user : jsonArray) {
                    //使用GSON，直接转成Bean对象
                    Bean bean = gson.fromJson(user, Bean.class);
                    userBeanList.add(bean);
                }
                int i;
                for (i = 0; i < userBeanList.size(); i++) {
                    final int id = userBeanList.get(i).getId();
                    Button button = new Button(mContext);
                    LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(CommonUtil.dip2px(10),0,CommonUtil.dip2px(10),0);
                    button.setLayoutParams(layoutParams);
                    button.setId(id);
                    button.setText(userBeanList.get(i).getShortname());
                    button.setTextColor(getResources().getColor(R.color.button_text));
                    button.setBackgroundColor(getResources().getColor(R.color.linear_btn));
                    button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 17);
                    linear_button.addView(button);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listView.setVisibility(View.VISIBLE);
                            kongbai.setVisibility(View.GONE);
                            isZixuan = false;
                            count=0;
                            pinzhonglist.clear();
                            pt_click = "";
                            bean_pinpingtais.clear();
                            short_name.clear();
                            list_shuju.clear();
                            closeConnect();
                            pt_click = userBeanList.get(v.getId() - 1).getCode();
                            Log.e("tag", pt_click);
                            inintdata_pinzhong(pt_click);

                        }
                    });

                }

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "get请求失败" + volleyError.toString());
            }
        });
        //设置取消取消http请求标签 Activity的生命周期中的onStiop()中调用
        request.setTag("volleyget");
        mRequestQueue.add(request);
    }

    private void inintdata_pinzhong(final String str) {
        /**
         *       接收品种信息
         */
        String url = "http://hangqing.yun066.com/home/GetTradedInstrument?type=" + str;
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(str);
                Gson gson = new Gson();

                for (JsonElement user : jsonArray) {
                    //通过反射 得到UserBean.class
                    Bean_pinzhong bean_pinzhong = gson.fromJson(user, new TypeToken<Bean_pinzhong>() {
                    }.getType());
                    String pinzhong = bean_pinzhong.getCode();
                    String shortname = bean_pinzhong.getFullname();
                    String pingtai = bean_pinzhong.getSj_code();
                    pinzhonglist.add(pinzhong);
                    bean_pinpingtais.add(pingtai);
                    short_name.add(shortname);
                }

                for (int i = 0; i < pinzhonglist.size(); i++) {

                    inintdata_history(str, pinzhonglist.get(i));

                    Log.i("bb", "get请求成功" + pinzhonglist.get(i));
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "get请求失败" + volleyError.toString());
            }
        });
        //设置取消取消http请求标签 Activity的生命周期中的onStiop()中调用
        request.setTag("volleyget");
        mRequestQueue.add(request);
    }

    private void inintdata_history(String pingtai, final String pinzhong) {
        /**
         *       接收历史数据
         */
        String utfpinzhong = null;
        try {
            utfpinzhong = URLEncoder.encode(pinzhong, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = "http://hangqing.yun066.com/home/Gethangqingls?type=" + pingtai + "&code=" + utfpinzhong + "&timespace=5" + "&num=1";
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                count++;
                JsonObject jsonObject = new JsonParser().parse(s).getAsJsonObject();
                JsonArray jsonArray = jsonObject.getAsJsonArray(pinzhong);
                Gson gson = new Gson();

                for (JsonElement user : jsonArray) {
                    //通过反射 得到UserBean.class
                    Bean_History bean_history = gson.fromJson(user, new TypeToken<Bean_History>() {
                    }.getType());

                    ArrayList<String> historylist = new ArrayList<>();
                    String o = bean_history.getO();
                    String h = bean_history.getH();
                    String l = bean_history.getL();
                    String c = bean_history.getC();
                    String t = bean_history.getT();
                    String zd = "0";
                    String zdf = "0";
                    String low = bean_history.getL();
                    String isup = "0";

                    historylist.clear();
                    historylist.add(o);
                    historylist.add(h);
                    historylist.add(l);
                    historylist.add(c);
                    historylist.add(t);
                    historylist.add(zd);
                    historylist.add(zdf);
                    historylist.add(low);
                    historylist.add(isup);
                    Log.e("history", historylist.toString());
                    list_shuju.add(historylist);
                    Log.i("bb", "get请求成功" + list_shuju.toString());

                }
                listView.setOnScrollListener(new AbsListView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(AbsListView view, int scrollState) {
                        switch (scrollState) {
                            //当listview开始滑动时，若有item的状态为Open，则Close，然后移除
                            case SCROLL_STATE_TOUCH_SCROLL:
                                if (sets.size() > 0) {
                                    for (SwipeListLayout s : sets) {
                                        s.setStatus(SwipeListLayout.Status.Close, true);
                                        sets.remove(s);
                                    }
                                }
                                break;
                        }
                    }

                    @Override
                    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                    }
                });
                if (count == pinzhonglist.size() - 1) {
                    connectServer();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("aa", "get请求失败" + volleyError.toString());
            }
        });
        //设置取消取消http请求标签 Activity的生命周期中的onStiop()中调用
        request.setTag("volleyget");
        mRequestQueue.add(request);
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
                new InetSocketAddress("123.56.190.3", PORT));

        cf.awaitUninterruptibly();

        clientSession = cf.getSession();

    }

    private void closeConnect() {

        if (null != clientSession && clientSession.isConnected()) {
            Log.d(TAG, "sever closed");
            clientSession.close(false);
            currentRequestString = null;
            service = null;
        }
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

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            JSONObject jsonObject = (JSONObject) msg.obj;
            List<String> list = new ArrayList();
            for (int j = 0; j < pinzhonglist.size(); j++) {
                if (pinzhonglist.get(j).equals(jsonObject.getString("code"))) {
                    list = list_shuju.get(j);
                    list.set(0, jsonObject.getString("open"));
                    list.set(1, jsonObject.getString("high"));
                    list.set(2, jsonObject.getString("last"));
                    list.set(3, jsonObject.getString("lastClose"));
                    list.set(5, jsonObject.getString("change"));
                    list.set(6, jsonObject.getString("changeExtent"));
                    list.set(7, jsonObject.getString("low"));
                    list.set(8, jsonObject.getString("isUp"));

                    list_shuju.set(j, (ArrayList) list);
                    Log.e("TAG", "handleMessage: " + list_shuju.size());
                    mAdapter = new Hq_ListAdapter(pinzhonglist, bean_pinpingtais, short_name, list_shuju, spinner_num, mContext);
                    listView.setAdapter(mAdapter);
                }
            }

            Log.e("TAG", "handleMessage: " + jsonObject.toString());
            final float last = Float.valueOf(jsonObject.getString("last"));
            Log.e("last", "++++++" + last);
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
        JSONObject jsonObject = new JSONObject();
        Log.d("dd", pt_click + pinzhonglist);
        if (pt_click.equals("")) {
            for (int i = 0; i < zixuan_pingtai.size(); i++) {
                Log.d("dd", zixuan_pingtai.get(i) + pinzhonglist);
                jsonObject.put(zixuan_pingtai.get(i), pinzhonglist);


            }
        } else {
            jsonObject.put(pt_click, pinzhonglist);
        }
        currentRequestString = JSON.toJSONString(jsonObject);
        Log.e("请求的数据1", currentRequestString);
        if (!TextUtils.isEmpty(currentRequestString)) {
            clientSession.write(currentRequestString);
            Log.e("请求的数据2", currentRequestString);

        }
    }

    public class Hq_ListAdapter extends BaseAdapter {
        private List<String> pinzhongList;
        private LayoutInflater inflater;
        private List<ArrayList> shujulist;
        private String spinner_num;
        private List<String> shortname;
        private List<String> zixuanpinzhong;


        public Hq_ListAdapter(List<String> zixuanpinzhong, List<String> pinzhongList, List<String> shortname, List<ArrayList> shujulist, String spinner_num, Context context) {
            this.pinzhongList = pinzhongList;
            this.inflater = LayoutInflater.from(context);
            this.shujulist = shujulist;
            this.spinner_num = spinner_num;
            this.shortname = shortname;
            this.zixuanpinzhong = zixuanpinzhong;

        }

        @Override
        public int getCount() {
            return zixuanpinzhong == null ? 0 : zixuanpinzhong.size();
        }

        @Override
        public Object getItem(int position) {
            return zixuanpinzhong
                    .get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final ViewHolder viewHolder;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.hq_list_item, null);
                viewHolder = new ViewHolder();
                viewHolder.text_newprice = (TextView) convertView.findViewById(R.id.list_item_newprice);
                viewHolder.text_pinzhong = (TextView) convertView.findViewById(R.id.list_item_pinzhong);
                viewHolder.text_zhagdie = (TextView) convertView.findViewById(R.id.list_item_zhangdie);
                viewHolder.lay = (LinearLayout) convertView.findViewById(R.id.hq_layout);

                viewHolder.lay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Bundle bundle = new Bundle();
                        bundle.putString("pingtai", pinzhongList.get(position));
                        bundle.putString("pinzhong", zixuanpinzhong.get(position));
                        bundle.putString("mark", short_name.get(position));
                        Intent intent = new Intent(mContext, K_line.class);
                        intent.putExtras(bundle);
                        closeConnect();
                        startActivity(intent);
                    }
                });
                viewHolder.sll_main = (SwipeListLayout) convertView
                        .findViewById(R.id.sll_main);
                viewHolder.tv_top = (TextView) convertView.findViewById(R.id.tv_top);
                viewHolder.tv_delete = (TextView) convertView.findViewById(R.id.tv_delete);
                if (isZixuan) {
                    viewHolder.tv_delete.setVisibility(View.VISIBLE);
                    viewHolder.tv_top.setVisibility(View.GONE);
                } else {
                    viewHolder.tv_delete.setVisibility(View.GONE);
                    viewHolder.tv_top.setVisibility(View.VISIBLE);
                }
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            if (shujulist.get(position).get(8).equals("-1")) {
                viewHolder.text_newprice.setTextColor(Color.RED);
                viewHolder.text_zhagdie.setTextColor(Color.RED);
            } else if (shujulist.get(position).get(8).equals("1")) {

                viewHolder.text_newprice.setTextColor(Color.GREEN);
                viewHolder.text_zhagdie.setTextColor(Color.GREEN);
            }
            viewHolder.text_pinzhong.setText(shortname.get(position));
            viewHolder.text_newprice.setText(shujulist.get(position).get(2).toString());
            int num = Integer.parseInt(spinner_num);
            if (num == 0) {
                viewHolder.text_zhagdie.setText(shujulist.get(position).get(5).toString());
            } else if (num == 1) {
                viewHolder.text_zhagdie.setText(shujulist.get(position).get(6).toString());
            } else if (num == 2) {
                viewHolder.text_zhagdie.setText(shujulist.get(position).get(0).toString());
            } else if (num == 3) {
                viewHolder.text_zhagdie.setText(shujulist.get(position).get(3).toString());
            } else if (num == 5) {
                viewHolder.text_zhagdie.setText(shujulist.get(position).get(1).toString());
            } else if (num == 6) {
                viewHolder.text_zhagdie.setText(shujulist.get(position).get(7).toString());
            }

            viewHolder.sll_main.setOnSwipeStatusListener(new MyOnSlipStatusListener(
                    viewHolder.sll_main));
            viewHolder.tv_top.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    viewHolder.sll_main.setStatus(SwipeListLayout.Status.Close, true);
                    String pingtai = bean_pinpingtais.get(position);
                    String pinzhong = zixuanpinzhong.get(position);

                    ZiXuanBean ziXuanBean = new ZiXuanBean();
                    ziXuanBean.setZixuan_o(shujulist.get(position).get(0).toString());
                    ziXuanBean.setZixuan_h(shujulist.get(position).get(1).toString());
                    ziXuanBean.setZixuan_l(shujulist.get(position).get(2).toString());
                    ziXuanBean.setZixuan_c(shujulist.get(position).get(3).toString());
                    ziXuanBean.setZixuan_zd(shujulist.get(position).get(5).toString());
                    ziXuanBean.setZixuan_zdf(shujulist.get(position).get(6).toString());
                    ziXuanBean.setZixuan_low(shujulist.get(position).get(7).toString());
                    ziXuanBean.setZixuanpingtai(pingtai);
                    ziXuanBean.setZixuanpinzhong(pinzhong);
                    ziXuanBean.setZixuan_shortname(shortname.get(position));
                    ziXuanBean.setMb("1");
                    ziXuanBean.setIsUp(shujulist.get(position).get(8).toString());
                    listBean.add(ziXuanBean);
                    dataSave = new ListDataSave(mContext, "zixuan");
                    dataSave.setDataList("javaBean", listBean);
                    notifyDataSetChanged();
                }
            });
            viewHolder.tv_delete.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    viewHolder.sll_main.setStatus(SwipeListLayout.Status.Close, true);
                    listBean.clear();
                    listBean = dataSave.getDataList("javaBean");
                    if (listBean.size() == 1) {
                        listBean.clear();
                    } else {
                        listBean.remove(position);
                    }
                    dataSave = new ListDataSave(mContext, "zixuan");
                    dataSave.setDataList("javaBean", listBean);
                    notifyDataSetChanged();
                }
            });

            listBean = dataSave.getDataList("javaBean");
            for (int i = 0; i < listBean.size(); i++) {
                String code = listBean.get(i).getZixuanpinzhong();
                if (pinzhonglist.get(position).equals(code)) {
                    viewHolder.tv_top.setVisibility(View.GONE);
                    viewHolder.tv_delete.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.tv_top.setVisibility(View.VISIBLE);
                    viewHolder.tv_delete.setVisibility(View.GONE);
                }
            }

            return convertView;
        }

        class ViewHolder {
            private TextView text_pinzhong, text_newprice, text_zhagdie, tv_top, tv_delete;
            SwipeListLayout sll_main;
            private LinearLayout lay;
        }

    }

    public static String listToString(List<String> list) {
        if (list == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean first = true;
        //第一个前面不拼接","
        for (String string : list) {
            if (first) {
                first = false;
            } else {
                result.append(",");
            }
            result.append(string);
        }
        return result.toString();
    }

    public static List removeDuplicate(List list) {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (list.get(j).equals(list.get(i))) {
                    list.remove(j);
                }
            }
        }
        return list;
    }
}

