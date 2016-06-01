package com.vioson.chunlian.fragments;


import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.vioson.chunlian.R;
import com.vioson.chunlian.util.ActivitySwitchBase;


public class AboutFragment extends Fragment implements OnClickListener {

    private static final String MY_WEIBO_URL = "http://weibo.com/viosonlee";
    private static final String CHUNLIAN_WANG = "http://www.duiduilian.com/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        view.findViewById(R.id.btn_set_logoff).setOnClickListener(this);
        view.findViewById(R.id.btn_more).setOnClickListener(this);
        view.findViewById(R.id.btn_set_help).setOnClickListener(this);
        view.findViewById(R.id.btn_my_weibo).setOnClickListener(this);
        view.findViewById(R.id.btn_set_about).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_my_weibo:
                ActivitySwitchBase.toH5Activity(getActivity(), getString(R.string.my_weibo), MY_WEIBO_URL);
                break;
            case R.id.btn_set_help:
                Toast.makeText(getActivity(), getString(R.string.no_update), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_set_about:
                showUs();
                break;
            case R.id.btn_set_logoff:
                getActivity().finish();
                break;
            case R.id.btn_more:
                ActivitySwitchBase.toH5Activity(getActivity(), "中国春联网",
                        CHUNLIAN_WANG);
                break;
        }
    }

    private void showUs() {
        Builder builder = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            builder = new Builder(getActivity(), android.R.style.Theme_Material_Dialog_Alert);
        }else {
            builder=new Builder(getActivity());
        }
        builder.setTitle(getString(R.string.about_title));
        builder.setMessage(R.string.about);
        builder.setPositiveButton(getString(R.string.i_konw), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
    }


}
