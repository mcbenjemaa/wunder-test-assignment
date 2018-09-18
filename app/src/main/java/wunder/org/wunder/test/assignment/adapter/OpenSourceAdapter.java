package wunder.org.wunder.test.assignment.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;
import wunder.org.wunder.test.assignment.R;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import wunder.org.wunder.test.assignment.model.OpenSourceLicense;


public class OpenSourceAdapter extends RecyclerView.Adapter<OpenSourceAdapter.OpenSourceViewHolder> {

    List<OpenSourceLicense> data;
    Context context;

    public OpenSourceAdapter(Context context,List<OpenSourceLicense> data){
        this.context=context;
        this.data=data;
    }


    @Override
    public OpenSourceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.opensource_layout,parent,false);
        return new OpenSourceViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OpenSourceViewHolder holder, int position) {
       final OpenSourceLicense open=data.get(position);
     holder.tv_opensource.setText(open.getLicenseName());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showDialog(open.getLicenseName(),open.getLicenseUrl());
            }
        });

    }

    private void showDialog(String name,String url){
        LayoutInflater li = LayoutInflater.from(context);
        View promptsView = li.inflate(R.layout.dialog_licenses, null);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                context);
        alertDialogBuilder.setTitle(name);

        alertDialogBuilder.setView(promptsView);
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        final WebView webview = (WebView) promptsView
                .findViewById(R.id.webView_dialog);
        webview.loadUrl(url);
        final AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class OpenSourceViewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.open_source_license_vh_name)
        TextView tv_opensource;
        @BindView(R.id.layout_opensource_cont)
        LinearLayout layout;

        public OpenSourceViewHolder(View v){
            super(v);
            ButterKnife.bind(this,v);
        }
    }
}
