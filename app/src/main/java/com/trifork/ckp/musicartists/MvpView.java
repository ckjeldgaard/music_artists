package com.trifork.ckp.musicartists;

public interface MvpView {
    void showLoading();
    void showContent();
    void showError(Throwable e);
}
