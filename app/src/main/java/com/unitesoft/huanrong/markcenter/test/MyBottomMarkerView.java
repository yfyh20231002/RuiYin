package com.unitesoft.huanrong.markcenter.test;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.unitesoft.huanrong.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *x轴显示
 */
public class MyBottomMarkerView extends MarkerView {
    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param layoutResource the layout resource to use for the MarkerView
     */
    private TextView markerTv;
    private String time;
    public MyBottomMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);
        markerTv = (TextView) findViewById(R.id.marker_tv);
        markerTv.setTextSize(10);
    }

    public void setData(String time){

        this.time=time;
    }
    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        String timeString = null;
        int time1 = Integer.parseInt(time);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        timeString = sdf.format(new Date(time1 * 1000l));//单位秒

        markerTv.setText(timeString);
    }

    @Override
    public int getXOffset(float xpos) {
        return 0;
    }

    @Override
    public int getYOffset(float ypos) {
        return 0;
    }
}
