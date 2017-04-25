package jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder;

import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemsDefine.JrdTitleItem;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter.JrdViewHolder;
import jrdcom.com.jrdrecylerdemo.R;

/**
 * Created by dhcui on 2017/4/24.
 */

public class JrdTitleViewHolder implements JrdItemViewHolder {
    @Override
    public void onCreateViewHolder(View view) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }

    }

    @Override
    public void onBindViewData(JrdViewHolder viewHolder, List<?> dataList, int position) {
        JrdTitleItem jrdTitleItem = (JrdTitleItem) dataList.get(position);
        View view  = viewHolder.getmView();
        TextView textView= (TextView)viewHolder.get(R.id.text_view);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_type1;
    }
}
