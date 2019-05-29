package com.example.andorid_big.Base;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<ViewInterface> {
    private WeakReference<ViewInterface> mViewInterface;
    public BasePresenter(ViewInterface viewInterface){
        mViewInterface=new WeakReference<>(viewInterface);
    }

    public void attachView(ViewInterface viewInterface){
        detachView();
        mViewInterface=new WeakReference<>(viewInterface);
    }

    public void detachView(){
        if (isAttached()){
            mViewInterface.clear();
            mViewInterface=null;
        }
    }

    public boolean isAttached(){
        return mViewInterface!=null && mViewInterface.get()!=null;
    }

    public ViewInterface getView(){
        if(isAttached())
            return mViewInterface.get();
        else
            return null;
    }
}
