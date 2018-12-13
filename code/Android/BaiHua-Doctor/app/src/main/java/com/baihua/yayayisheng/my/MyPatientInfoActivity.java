package com.baihua.yayayisheng.my;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.base.BaseActivity;
import com.baihua.common.base.BaseFragment;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.baihua.yayayisheng.home.PatientInfoAdapter;
import com.baihua.yayayisheng.home.PatientInfoAnsweredDetailsActivity;
import com.baihua.yayayisheng.home.PatientInfoPendingReplyDetailsActivity;
import com.baihua.yayayisheng.home.PatientInfoVisitingDetailsActivity;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MyPatientInfoActivity extends BaseActivity {


    @BindView(R.id.my_patient_info_recycler)
    RecyclerView mRecyclerView;

    private List<String> mList;
    private PatientInfoAdapter mAdapter;

    @Override
    public void setLayout() {
        setTitle("患者信息");
        setContentView(R.layout.activity_my_patient_info);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {


//        RxHttp.getInstance().getSyncServer().getLatestVersion("1", "1.0.0")
//                .compose(RxSchedulers.observableIO2Main(getActivity()))
//                .subscribe(new ProgressObserver<String>(getActivity()) {
//
//                    @Override
//                    public void onSuccess(String result) {
//                        toast(result);
//                    }
//
//                    @Override
//                    public void onFailure(Throwable e, String errorMsg) {
//
//                    }
//                });

        initRecycler();
        initData();
    }

    private void initData() {
        mList = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            mList.add("i = " + i);
        }
        mAdapter.setNewData(mList);
    }

    private void initRecycler() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        SpaceDecoration spaceDecoration = new SpaceDecoration(ConvertUtils.dp2px(8));
        spaceDecoration.setPaddingStart(false);
        spaceDecoration.setPaddingEdgeSide(false);
        mRecyclerView.addItemDecoration(spaceDecoration);
        mAdapter = new PatientInfoAdapter(new ArrayList<>());
        mRecyclerView.setAdapter(mAdapter);
    }


    @Override
    public void setListener() {

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (position % 2 == 1) {
                    ActivityUtils.startActivity(PatientInfoVisitingDetailsActivity.class);
                } else if (position % 2 == 0 && 10 != position) {
                    ActivityUtils.startActivity(PatientInfoPendingReplyDetailsActivity.class);
                } else {
                    ActivityUtils.startActivity(PatientInfoAnsweredDetailsActivity.class);
                }
            }
        });

    }

}
