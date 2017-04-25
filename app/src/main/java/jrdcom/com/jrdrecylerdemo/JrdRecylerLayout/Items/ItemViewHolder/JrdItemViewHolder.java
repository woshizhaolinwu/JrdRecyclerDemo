package jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder;

import android.view.View;

import java.util.List;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter.JrdViewHolder;

/**
 * Created by longcheng on 2017/4/24.
 */

public interface JrdItemViewHolder {
    public void onCreateViewHolder(View view);
    public void onBindViewData(JrdViewHolder viewHolder, List<?> dataList, int position);
    public int getLayoutId();
}
