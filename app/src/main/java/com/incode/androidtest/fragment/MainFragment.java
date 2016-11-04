package com.incode.androidtest.fragment;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.incode.androidtest.R;
import com.incode.androidtest.adapter.PictureListAdapter;
import com.incode.androidtest.interfaces.FragmentInterface;

/**
 * Created by argi on 11/2/16.
 */

public class MainFragment extends BaseFragment <FragmentInterface> {

    RecyclerView recyclerView;

    FloatingActionButton fabReloadData, fabTakePicture;

    public static MainFragment getInstance(){
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_pictures_list;
    }

    @Override
    protected void initWidgets(View fragmentView) {
        recyclerView = (RecyclerView) fragmentView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(PictureListAdapter.getInstance((FragmentInterface) getActivity(), getContext()));


        fabReloadData = (FloatingActionButton) fragmentView.findViewById(R.id.fabReloadData);
        fabTakePicture = (FloatingActionButton) fragmentView.findViewById(R.id.fabTakePicture);

        fabReloadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onDataReload();
            }
        });

        fabTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onTakePicture();
            }
        });
    }

    public void reloadData(){
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}
