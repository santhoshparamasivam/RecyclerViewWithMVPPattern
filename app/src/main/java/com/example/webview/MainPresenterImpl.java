package com.example.webview;
import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter,MainContract.GetNoticeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }


    @Override
    public void requestDataFromServer() {
        getNoticeIntractor.getNoticeArrayList(this);
    }

    @Override
    public void onFinished(ArrayList<ResponseModel.Datum> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
        }
    }

    @Override
    public void onFailure(String t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
        }
    }
}