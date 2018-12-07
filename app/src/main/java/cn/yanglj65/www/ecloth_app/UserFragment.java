package cn.yanglj65.www.ecloth_app;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.yanglj65.www.ecloth_app.Util.ToolUtil;


public class UserFragment extends Fragment {
    public UserFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }
    @Override
    public void onStart(){
        super.onStart();
        Button personInfo=getView().findViewById(R.id.PERSONINFO_BTN);
        Button record=getView().findViewById(R.id.RECORD_BTN);
        Button scoreInfo=getView().findViewById(R.id.SCORE_BTN);
        Button setting=getView().findViewById(R.id.SETTINGS_BTN);
        Button bugReporter=getView().findViewById(R.id.BUGREPORT_BTN);
        ToolUtil.setButtonImageLeft(personInfo);
        ToolUtil.setButtonImageLeft(record);
        ToolUtil.setButtonImageLeft(scoreInfo);
        ToolUtil.setButtonImageLeft(setting);
        ToolUtil.setButtonImageLeft(bugReporter);
        personInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), PersonInfoActivity.class);
                startActivity(intent);
            }
        });
        record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        scoreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
            }
        });
        bugReporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getActivity(), BugReportActivity.class);
                startActivity(intent);
            }
        });
    }


}
