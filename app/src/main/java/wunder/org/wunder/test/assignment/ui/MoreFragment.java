package wunder.org.wunder.test.assignment.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wunder.org.wunder.test.assignment.R;
import wunder.org.wunder.test.assignment.adapter.OpenSourceAdapter;
import wunder.org.wunder.test.assignment.model.OpenSourceLicense;


public class MoreFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    List<OpenSourceLicense> data=new ArrayList<>();
    OpenSourceAdapter adapter;


    public MoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_more, container, false);

        ButterKnife.bind(this,v);

        LinearLayoutManager lm=new LinearLayoutManager(getContext());
        lm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lm);

        adapter=new OpenSourceAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);
        setupListOpenSources();

        return v;
    }



    public void setupListOpenSources(){

        data.add(new OpenSourceLicense("Android Sdk","file:///android_asset/third_party/open_source_licenses_android_annotations_support_library.html") );
        data.add(new OpenSourceLicense("Butterknife","file:///android_asset/third_party/butter_knife.html") );
        data.add(new OpenSourceLicense("Gson","file:///android_asset/third_party/gson.html") );
        data.add(new OpenSourceLicense("BottomBar","file:///android_asset/third_party/bottom-bar.html") );
        data.add(new OpenSourceLicense("SafeFragmentTransaction","file:///android_asset/third_party/safetransaction.html") );

        adapter.notifyDataSetChanged();
    }
}
