package com.example.openionapp.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.openionapp.R;
import com.example.openionapp.activity.Adapter_link;
import com.example.openionapp.model.RecentLinkModel;
import com.example.openionapp.util.Constent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class TopLinkFragment extends Fragment {

    private static final String TAG = RecentLinkFragment.class.getSimpleName();
    private static final int MY_SOCKET_TIMEOUT_MS = 60000;
    RecyclerView recy_recent;
    Adapter_link adapter_link;

    RecentLinkModel recentLinkModel;
    public static ArrayList<RecentLinkModel> getRecentlist=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_top_link, container, false);
     //   validateDashboarddata();
        initialise(v);

        return v;
    }
    private void initialise(View v) {

        recy_recent = v.findViewById(R.id.recy_recent);
        getRecentlist = new ArrayList<>();
        Log.d(TAG,"listdata======="+getRecentlist.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter_link = new Adapter_link(getActivity(), getRecentlist);
        recy_recent.setHasFixedSize(true);
        recy_recent.setLayoutManager(layoutManager);
        recy_recent.setAdapter(adapter_link);

    }
    public void validateDashboarddata() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());
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
        Log.d(TAG,"response===="+response.toString());
        try {
            if (response.getString("message").equals("success")) {
                recentLinkModel =new RecentLinkModel();

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
                    getRecentlist.add(recentLinkModel);
                    Log.d(TAG,"listdata=======111==="+getRecentlist.size());

                }
                Log.d(TAG,"listdata=======222==="+getRecentlist.size());
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}