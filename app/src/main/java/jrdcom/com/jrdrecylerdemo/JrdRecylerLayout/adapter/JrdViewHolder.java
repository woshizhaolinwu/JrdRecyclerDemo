package jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder.JrdItemViewHolder;
import jrdcom.com.jrdrecylerdemo.R;

/**
 * Created by longcheng on 2017/4/24.
 * JrdViewHolder用来保存View 和JrdItemViewHolder信息
 *
 */

public class JrdViewHolder {
    private View mView;
    private JrdItemViewHolder itemViewHolder;
    private SparseArray<View> ViewList;
    public JrdViewHolder(View view){
        mView = view;
        ViewList = new SparseArray<>();
    }

    public static JrdViewHolder getInstance(View view){
        JrdViewHolder viewHolder = (JrdViewHolder) view.getTag(R.id.item_tag_id);
        if(viewHolder == null)
        {
            viewHolder = new JrdViewHolder(view);
            view.setTag(R.id.item_tag_id, viewHolder);
        }
        return viewHolder;
    }

    public void setItemViewHolder(JrdItemViewHolder itemViewHolder) {
        this.itemViewHolder = itemViewHolder;
    }

    public View getmView() {
        return mView;
    }

    public JrdItemViewHolder getItemViewHolder() {
        return itemViewHolder;
    }

    public <T extends  View> T get(int id){
        View view = ViewList.get(id);
        if(view == null){
            view = mView.findViewById(id);
            ViewList.put(id, view);

        }
        return (T)view;
    }

    public Context getContext(){
        return mView.getContext();

    }
}
