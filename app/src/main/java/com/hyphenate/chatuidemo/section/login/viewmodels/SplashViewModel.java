package com.hyphenate.chatuidemo.section.login.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.hyphenate.chatuidemo.common.ApiResponse;
import com.hyphenate.chatuidemo.common.Result;
import com.hyphenate.chatuidemo.repositories.EMClientRepository;

public class SplashViewModel extends AndroidViewModel {
    private EMClientRepository mRepository;

    public SplashViewModel(@NonNull Application application) {
        super(application);
        mRepository = new EMClientRepository();
    }

    public LiveData<ApiResponse<Result<Boolean>>> getLoginData() {
        return mRepository.loadAllInfoFromHX();
    }
}
