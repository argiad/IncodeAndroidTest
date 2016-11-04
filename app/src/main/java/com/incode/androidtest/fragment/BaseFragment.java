package com.incode.androidtest.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.incode.androidtest.BuildConfig;
import com.incode.androidtest.DataManager;

/**
 * Created by argi on 11/2/16.
 */

public abstract class BaseFragment<T> extends Fragment {

    Activity mActivity;

    /**
     * Hosted Fragment Interface Listener
     */
    T mListener;

    /**
     * Root View
     */
    View mFragmentRootView;

    /**
     *  Business Logic DataManager shortcut
     */
    DataManager dm = DataManager.INSTANCE;

    /**
     *  Log Tag
     */
    public static String TAG;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mFragmentRootView = inflater.inflate(getLayoutResourceId(), container, false);

        // Init Root View
        initWidgets(mFragmentRootView);

        return mFragmentRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Creates a copy of the current context so it can be accessed from within the listeners
        if (context instanceof Activity){
            mActivity=(Activity) context;
        }

        // Check that our hosting activity implements our interface
        try {
            mListener = (T) mActivity;
        } catch (ClassCastException e) {
            throw new ClassCastException(mActivity.toString() + " must implement its hosted fragment listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Remove the listener
        mListener = null;
    }

    void log(String textToLog){
        if (BuildConfig.DEBUG) {
            Log.d(TAG, textToLog);
        }
    }

    protected abstract int getLayoutResourceId();

    protected abstract void initWidgets(View fragmentView);
}
