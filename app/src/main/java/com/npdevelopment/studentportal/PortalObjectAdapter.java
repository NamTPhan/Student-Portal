package com.npdevelopment.studentportal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class PortalObjectAdapter extends RecyclerView.Adapter<PortalObjectViewHolder> {

    private Context mContext;
    public List<PortalObject> portalObjectList;
    public static final int REQUEST_CODE_OK = 200;
    public static final String URL_KEY = "portalUrl";

    public PortalObjectAdapter(Context context, List<PortalObject> portalObjectList) {
        this.mContext = context;
        this.portalObjectList = portalObjectList;
    }

    @NonNull
    @Override
    public PortalObjectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_card_portal, viewGroup, false);
        return new PortalObjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PortalObjectViewHolder holder, final int position) {
        // Gets a single item in the list from its position
        final PortalObject portalObject = portalObjectList.get(position);

//        holder.portalBtn.setText(portalObject.getTitle());
        holder.portalCardBtn.setText(R.string.open_portal_btn);
        holder.portalCardTitle.setText(portalObject.getmTitle());

        holder.portalCardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PortalView.class);
                intent.putExtra(URL_KEY, portalObject);
                ((Activity) mContext).startActivityForResult(intent, REQUEST_CODE_OK);
            }
        });
    }

    @Override
    public int getItemCount() {
        return portalObjectList.size();
    }
}
