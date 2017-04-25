package jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemsDefine.JrdListItem;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter.JrdViewHolder;
import jrdcom.com.jrdrecylerdemo.R;

/**
 * Created by dhcui on 2017/4/24.
 */

public class JrdListViewHolder implements JrdItemViewHolder, View.OnClickListener {
    @Override
    public void onCreateViewHolder(View view) {
        /*ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof StaggeredGridLayoutManager.LayoutParams) {
            ((StaggeredGridLayoutManager.LayoutParams) layoutParams).setFullSpan(true);
        }*/
    }

    @Override
    public void onBindViewData(JrdViewHolder viewHolder, List<?> dataList, int position) {
        JrdListItem listItem = (JrdListItem)dataList.get(position);
        TextView view =viewHolder.get(R.id.type2_text1);
        view.setText(listItem.listText);
        ImageView imageView = viewHolder.get(R.id.type2_image);
        imageView.setImageDrawable(viewHolder.getContext().getResources().getDrawable(listItem.listImageId));
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_type2;
    }

    @Override
    public void onClick(View v) {

    }
}
