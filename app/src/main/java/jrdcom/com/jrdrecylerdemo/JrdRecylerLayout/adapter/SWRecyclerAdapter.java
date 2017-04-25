package jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder.JrdItemViewHolder;
import jrdcom.com.jrdrecylerdemo.R;

/**
 * Created by Angel on 2017/3/28.
 */
public abstract class SWRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    public List<?> mData;
    private LayoutInflater inflater;
    private SparseArray<JrdItemViewHolder> viewHolderSparseArray;

    public SWRecyclerAdapter(Context context, List<?> list) {
        this.context = context;
        this.mData = list;
        inflater = LayoutInflater.from(context);
        viewHolderSparseArray = new SparseArray<>();
        initViewHolder();
    }

    /*
    * 将ViewType作为Key， ItemViewHolder作为对象，保存进SparseArray
    * */
    public void registerViewHolder(int viewType, JrdItemViewHolder viewHolder){
        viewHolderSparseArray.put(viewType, viewHolder);
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据Viewtype获取ViewHolder
        JrdItemViewHolder jrdItemViewHolder = viewHolderSparseArray.get(viewType);
        View view = LayoutInflater.from(context).inflate(jrdItemViewHolder.getLayoutId(),parent, false);
        ViewHolder viewHolder = new ViewHolder(view, jrdItemViewHolder);
        return viewHolder;
    }


    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //根据Holder中的item,获取出jrdViewHolder
        JrdViewHolder jrdViewHolder = (JrdViewHolder) holder.itemView.getTag(R.id.item_tag_id);
        jrdViewHolder.getItemViewHolder().onBindViewData(jrdViewHolder, mData, position);
    }

    @Override
    public int getItemViewType(int position) {
        return getViewType(position);
    }

    public int getItemCount() {
        return mData.size();
    }

    public void delete(int positon) {
        mData.remove(positon);
        notifyItemRemoved(positon);
    }

    /*
    * 定义抽象类给子类使用
    * */
    public abstract int getViewType(int position);
    public abstract void initViewHolder();

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View view, JrdItemViewHolder itemViewHolder){
            super(view);
            //将ViewHolder保存起来
            JrdViewHolder viewHolder = JrdViewHolder.getInstance(itemView);
            viewHolder.setItemViewHolder(itemViewHolder);
            //需要执行各个不同viewHolder的CreateViewHolder
            itemViewHolder.onCreateViewHolder(view);
        }
    }
}
