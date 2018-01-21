package com.example.a20141022.myapplication.presenter;

import android.content.Context;

import com.example.a20141022.myapplication.activity.MainView;
import com.example.a20141022.myapplication.model.MainModel;

/**
 * Created by zhangqiang on 2018/01/20.
 */

public class MainPresenter<T extends MainView> extends BasePresenter<T> {


    public MainPresenter(Context context) {
        super(context);
    }

    public String getDataFromModel(){
        MainModel mainModel = new MainModel();
        return mainModel.getMainData();
    }
    //具体业务实现方式
    public String makeBusinessLogic(){
        return getDataFromModel();
    }
    //设置到view，由presenter决定怎么绘制界面，由view调用
    //setHello 属于view，而里面的数据是由逻辑presenter来决定的，所以需要放在MainPresenter中
    public void setView(){
        getView().setMainView(makeBusinessLogic());
    }
}
