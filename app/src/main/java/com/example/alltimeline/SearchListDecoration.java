package com.example.alltimeline;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SearchListDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = 10;
    }
}