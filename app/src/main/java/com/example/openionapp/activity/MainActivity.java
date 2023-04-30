package com.example.openionapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.openionapp.R;
import com.example.openionapp.fragment.RecentLinkFragment;
import com.example.openionapp.fragment.TopLinkFragment;
import com.example.openionapp.model.RecentLinkModel;
import com.example.openionapp.model.ResponseModel;
import com.example.openionapp.util.Constent;
import com.example.openionapp.util.CurvedBottomNavigationView;
import com.example.openionapp.util.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;



public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int MY_SOCKET_TIMEOUT_MS = 60000;

    ResponseModel responseModel;
    ArrayList<ResponseModel> responseModelArrayList =new ArrayList<>();
    public static ArrayList<RecentLinkModel> recent_linklist =new ArrayList<>();
    public static ArrayList<RecentLinkModel> top_linklist =new ArrayList<>();
    TextView txt_clicks,txt_location,txt_source,txt_besttime,txt_whatsapp,txt_greetings;
    CurvedBottomNavigationView curvedBottomNavigationView;
    GraphView graph;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        validateDashboarddata();
        init();
    }

    @SuppressLint("SetTextI18n")
    public void init(){
        txt_clicks= findViewById(R.id.txt_clicks);
        txt_location=findViewById(R.id.txt_location);
        txt_source=findViewById(R.id.txt_source);
        txt_besttime=findViewById(R.id.txt_besttime);
        txt_whatsapp=findViewById(R.id.txt_whatsapp);
        txt_greetings=findViewById(R.id.txt_greetings);
        //lineChart=findViewById(R.id.lineChart);
        curvedBottomNavigationView = findViewById(R.id.customBottomBar);
        Log.d(TAG,"listdata=======init==="+recent_linklist.size());
        viewPager =(ViewPager) findViewById(R.id.viewPager) ;
        tabLayout=findViewById(R.id.tabLayout);
        graph = (GraphView) findViewById(R.id.graph);

        setupViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#296A96"));
        tabLayout.setTabTextColors(Color.parseColor("#AAAAAA"), Color.parseColor("#296A96"));
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        assert viewPager != null;
        tabLayout.setupWithViewPager(viewPager);

        curvedBottomNavigationView.inflateMenu(R.menu.menu);


        Calendar c = Calendar.getInstance();
        int timeOfDay = c.get(Calendar.HOUR_OF_DAY);

        if(timeOfDay >= 0 && timeOfDay < 12){
            txt_greetings.setText("Good Morning");
        }else if(timeOfDay >= 12 && timeOfDay < 16){
            txt_greetings.setText("Good Afternoon");
        }else if(timeOfDay >= 16 && timeOfDay < 21){
            txt_greetings.setText("Good Evening");
        }else if(timeOfDay >= 21 && timeOfDay < 24){
            txt_greetings.setText("Good Night");

        }
//       Log.d(TAG,"dashorddata=====1="+responseModel.toString());


        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

    }
    private void setupViewPager(ViewPager viewPager)
    {
        System.out.println("setupViewPager called");
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new TopLinkFragment(),     "Top Links");
        adapter.addFrag(new RecentLinkFragment(),     "Recent Links");

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(1,false);

        //Log.e(TAG,"message setupViewPager"+message);

    }


    public void validateDashboarddata() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constent.Apiurl, null,
                response -> {
                    Log.i("onResponse", response.toString());
                    parseJsonResponse(response);
                },
                error -> {
                    VolleyLog.e(TAG, "Error: " + error.networkResponse);
                }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + Constent.token);
                return headers;
            }
        };
        request.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        queue.add(request);
    }
    void parseJsonResponse(JSONObject response) {
        ArrayList<RecentLinkModel> recent_linklist =new ArrayList<>();
        Log.d(TAG,"response===="+recent_linklist.toString());
        try {
            if (response.getString("message").equals("success")) {
                ResponseModel responseModel = new ResponseModel();
             //   responseModel.setSupportWhatsappNumber(response.optString("support_whatsapp_number"));
                responseModel.setExtraIncome(response.optString("extra_income"));
                responseModel.setTotalLinks(response.optInt("total_links"));

                txt_clicks.setText(response.getString("total_clicks"));
                txt_location.setText(response.optString("top_location"));
                txt_source.setText(response.optString("top_source"));
                txt_besttime.setText(response.optString("startTime"));
                txt_whatsapp.setText(response.optString("support_whatsapp_number"));

                responseModel.setTodayClicks(response.optInt("today_clicks"));
                responseModel.setLinksCreatedToday(response.optInt("links_created_today"));
                responseModel.setAppliedCampaign(response.optInt("applied_campaign"));
                responseModelArrayList.add(responseModel);
                try {
                        RecentLinkModel recentLinkModel =new RecentLinkModel();
                        JSONObject jsonObject = new JSONObject(String.valueOf(response)).getJSONObject("data");
                        Log.d(TAG,"responselink====="+jsonObject.toString());
                        JSONArray recentLinksArray = jsonObject.getJSONArray("recent_links");
                        for (int i = 0; i < recentLinksArray.length(); i++) {
                            JSONObject linkObject = recentLinksArray.getJSONObject(i);
                            recentLinkModel.setUrl_id(linkObject.getInt("url_id"));
                            recentLinkModel.setWeb_link(linkObject.getString("web_link"));
                            recentLinkModel.setSmart_link(linkObject.getString("smart_link"));
                            recentLinkModel.setTitle(linkObject.getString("title"));
                            recentLinkModel.setTotal_clicks(linkObject.getString("total_clicks"));
                            recentLinkModel.setOriginal_image(linkObject.getString("original_image"));
                            recentLinkModel.setCreated_at(linkObject.getString("created_at"));
                            recentLinkModel.setTimes_ago(linkObject.getString("times_ago"));
                            recentLinkModel.setDomain_id(linkObject.getString("domain_id"));
                            recentLinkModel.setApp(linkObject.getString("app"));
                            recent_linklist.add(recentLinkModel);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                try {
                    RecentLinkModel recentLinkModel =new RecentLinkModel();
                    JSONObject jsonObject = new JSONObject(String.valueOf(response)).getJSONObject("data");
                    Log.d(TAG,"responselink====="+jsonObject.toString());
                    JSONArray recentLinksArray = jsonObject.getJSONArray("top_links");
                    for (int i = 0; i < recentLinksArray.length(); i++) {
                        JSONObject linkObject = recentLinksArray.getJSONObject(i);
                        recentLinkModel.setUrl_id(linkObject.getInt("url_id"));
                        recentLinkModel.setWeb_link(linkObject.getString("web_link"));
                        recentLinkModel.setSmart_link(linkObject.getString("smart_link"));
                        recentLinkModel.setTitle(linkObject.getString("title"));
                        recentLinkModel.setTotal_clicks(linkObject.getString("total_clicks"));
                        recentLinkModel.setOriginal_image(linkObject.getString("original_image"));
                        recentLinkModel.setCreated_at(linkObject.getString("created_at"));
                        recentLinkModel.setTimes_ago(linkObject.getString("times_ago"));
                        recentLinkModel.setDomain_id(linkObject.getString("domain_id"));
                        recentLinkModel.setApp(linkObject.getString("app"));
                        top_linklist.add(recentLinkModel);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.d(TAG,"listdata====link=="+recent_linklist.toString());
                try {
                    JSONObject jsonObject = new JSONObject(String.valueOf(response)).getJSONObject("data");
                    JSONObject overallUrlChartObject = jsonObject.getJSONObject("overall_url_chart");

                    Iterator<String> keys = overallUrlChartObject.keys();
                    while (keys.hasNext()) {
                        String key = keys.next();
                        int value = overallUrlChartObject.getInt(key);
                        Log.d(TAG,"chartdata====key="+key);

                        Log.d(TAG,"chartdata===="+value);

                        // do something with the key-value pair
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


    }
}