package com.example.shichang393.ruiyin.markcenter.suanfa;




import com.example.shichang393.ruiyin.markcenter.k_line.NewBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangliang on 16/11/5.
 * QQ:1179980507
 */
public class KDJ {
	private ArrayList<Float> Ks;
	private ArrayList<Float> Ds;
	private ArrayList<Float> Js;
	private ArrayList<Float> rsv;

	public KDJ(List<NewBean> OHLCData) {
		Ks = new ArrayList<Float>();
		Ds = new ArrayList<Float>();
		Js = new ArrayList<Float>();

//        DecimalFormat format=new DecimalFormat("");

		float k = 50;
		float d = 50;

		if (OHLCData != null && OHLCData.size() > 0) {
            for (int i = 0; i < OHLCData.size(); i++) {
                NewBean entry = OHLCData.get(i);

                int startIndex = i - 8;
                if (startIndex < 0) {
                    startIndex = 0;
                }

                float max9 = Float.MIN_VALUE;
                float min9 = Float.MAX_VALUE;
                for (int index = startIndex; index <= i; index++) {
                    max9 = Math.max(max9, OHLCData.get(index).getH());
                    min9 = Math.min(min9, OHLCData.get(index).getL());
                }

                float rsv = 100f * (entry.getC() - min9) / (max9 - min9);
                if (i == 0) {
                    k = rsv;
                    d = rsv;
                } else {
                    k = (rsv + 2f * k) / 3f;
                    d = (k + 2f * d) / 3f;
                }


                Ks.add(k);
                Ds.add(d);
                Js.add(3f * k - 2 * d);
            }
		}
	}

	public ArrayList<Float> getK() {
		return Ks;
	}

	public ArrayList<Float> getD() {
		return Ds;
	}

	public ArrayList<Float> getJ() {
		return Js;
	}
}

