package com.npdevelopment.studentportal;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PortalObjectViewHolder extends RecyclerView.ViewHolder {

    public View view;
    public TextView portalCardTitle;
    public Button portalBtn, portalCardBtn;
    public CardView portalCard;

    public PortalObjectViewHolder(View itemView) {
        super(itemView);
        portalCard = itemView.findViewById(R.id.portalCard);
        portalCardBtn = itemView.findViewById(R.id.portalCardBtn);
        portalCardTitle = itemView.findViewById(R.id.portalCardTitle);
        view = itemView;
    }
}
