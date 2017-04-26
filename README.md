# JrdRecyclerDemo
基于SWPullRecyclerLayout添加不同布局的RecyclerItem

使用说明：
        recyclerLayout = (SWPullRecyclerLayout) findViewById(R.id.recycler_layout);
        //添加Head和Foot
        View head = LayoutInflater.from(this).inflate(R.layout.layout_head,null);
        View foot = LayoutInflater.from(this).inflate(R.layout.layout_foot,null);
        int des = (int) getScreenDes();
        recyclerLayout.addHeaderView(head, (int)60*des);
        recyclerLayout.addFooterView(foot, (int)60*des);
        
        /* 添加Adapter*/
        initData(); //初始化数据
        JrdRecyclerAdapter numAdapter = new JrdRecyclerAdapter(this,mData);
        
        recyclerLayout.setMyRecyclerView(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL), numAdapter);
        /*设置回调*/
        recyclerLayout.addOnTouchUpListener(this);
        //设置是否自动回滚，建议为false
        recyclerLayout.setScrollBackAuto(false);
