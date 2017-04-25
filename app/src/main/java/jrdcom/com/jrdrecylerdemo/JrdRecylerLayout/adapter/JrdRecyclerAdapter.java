package jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.adapter;

import android.content.Context;

import java.util.List;

import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder.JrdListViewHolder;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemViewHolder.JrdTitleViewHolder;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemsDefine.JrdListItem;
import jrdcom.com.jrdrecylerdemo.JrdRecylerLayout.Items.ItemsDefine.JrdTitleItem;


/**
 * Created by zlwu on 2017/4/24.
 * 这个文件是用户自定义的文件， 继承自  SWRecyclerAdapter,
 * 实现getViewType,和注册ViewHolder
 */

public class JrdRecyclerAdapter extends SWRecyclerAdapter {
    public static int ITEM_TITLE =  1;
    public static int ITEM_LIST = 2;

    public JrdRecyclerAdapter(Context context, List<?> data){
        super(context, data);
    }

    /*
    * 接口，获取ViewType，因为子类个性化，所以做抽象类来实现
    * */
    @Override
    public int getViewType(int position) {
        //return 0;
        int viewtype = 0;
        if(mData.get(position) instanceof JrdTitleItem){
            viewtype = ITEM_TITLE;
        }else if(mData.get(position) instanceof JrdListItem){
            viewtype = ITEM_LIST;
        }
        return viewtype;
    }

    @Override
    public void initViewHolder() {
        registerViewHolder(ITEM_TITLE, new JrdTitleViewHolder());
        registerViewHolder(ITEM_LIST, new JrdListViewHolder());
    }


}
