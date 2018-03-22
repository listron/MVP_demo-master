package com.example.a20141022.myapplication.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.a20141022.myapplication.R;
import com.example.a20141022.myapplication.presenter.MainPresenter;
import com.example.a20141022.myapplication.presenter.SencondPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import xhxny.com.mylibrary.Uitils;

/**
 * Created by zhangqiang on 2018/01/20.
 */

public class MainActivity<V extends MainActivity, T extends MainPresenter<V>,M extends SencondPresenter<V>> extends AppCompatActivity implements MainView{

    @BindView(R.id.main_view)
    public TextView mainView;
    @BindView(R.id.sesond_view)
    public TextView sesondView;

    //多个presenter，实现不同的业务逻辑，分别处理，最终决定页面显示风格
    protected MainPresenter mPresenter; //Presenter对象
    protected SencondPresenter mSencondPresenter;

    TextView hello ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this) ;
        mPresenter = createMainPresenter();
        mSencondPresenter = createSencondPresenter();
        Uitils.getString();
    }


    public void setMainView(String string){
        mainView.setText(string);
    }

    public void setSesondView(String string){
        sesondView.setText(string);
    }

    protected  MainPresenter createMainPresenter(){
        MainPresenter basePresenter = new MainPresenter(this);
        basePresenter.attachView(this);
        return basePresenter;
    }

    protected SencondPresenter createSencondPresenter(){
        SencondPresenter sencondPresenter = new SencondPresenter(this);
        sencondPresenter.attachView(this);
        return sencondPresenter;
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter.isViewAttached()){
            mPresenter.detachView();

        }
        if(mSencondPresenter.isViewAttached()){
            mSencondPresenter.detachView();
        }
    }


    @OnClick(R.id.main_view)
    public void showMainView(){
        if(mPresenter.isViewAttached()){
            //设置到view，由presenter决定怎么绘制界面，由view调用
            mPresenter.setView();
        }
    }

    @OnClick(R.id.sesond_view)
    public void showSecondView(){
        if(mSencondPresenter.isViewAttached()){
            //设置到view，由presenter决定怎么绘制界面，由view调用
            mSencondPresenter.setView();
        }
    }

}
