package com.baihua.yayayisheng.home;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.baihua.common.base.BaseFragment;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.decoration.SpaceDecoration;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class PatientInfoFragment extends BaseFragment {


    @BindView(R.id.patient_info_recycler)
    RecyclerView mRecyclerView;

    private List<String> mList;
    private PatientInfoAdapter mAdapter;

    private String mVoiceFilePath;
    private List<String> mImagePathList;
    private List<File> mImageFileList;

    @Override
    public int setRootView() {
        return R.layout.fragment_patient_info;
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {

//        Map<String, RequestBody> partMap = new HashMap<>();
//
//        /* 处理语音文件*/
//        if (null != mVoiceFilePath) { // 有上传语音文件
//            File audioFile = new File(mVoiceFilePath);
//            RequestBody audioBody = RequestBody.create(MediaType.parse("audio/*"), audioFile);
//            partMap.put("voice\";fileName=\"" + audioFile.getName(), audioBody);
//        }
//
//        if (!Utils.isListEmpty(mImagePathList)) { // 有上传图片
//            mImageFileList = new ArrayList<>();
//            for (int i = 0; i < mImagePathList.size(); i++) {
//                mImageFileList.add(new File(mImagePathList.get(i)));
//            }
//        }
//
//        /* 多图片处理*/
//        for (File file :
//                mImageFileList) {
//            MediaType contentType;
//            String content;
//            RequestBody imageBody = RequestBody.create(MediaType.parse("image/*"), file);
//            partMap.put("pic\";fileName=\"" + file.getName(), imageBody);
//        }
//
//        partMap.put("accessToken", RequestBody.create(null, "token")); // 普通参数类型处理
//
//        RxHttp.getInstance().getSyncServer().upLoadImages(partMap)
//                .compose(RxSchedulers.observableIO2Main(getActivity()))
//                .subscribe(new ProgressObserver<String>(getActivity()) {
//                    @Override
//                    public void onSuccess(String result) {
//
//                    }
//
//                    @Override
//                    public void onFailure(Throwable e, String errorMsg) {
//
//                    }
//                });

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
                // 到时候直接传递信息或者ID
                if (position % 2 == 1) {
                    Utils.gotoActivity(getActivity(), PatientInfoDetailsActivity.class, false, "status", "1");
                } else if (position % 2 == 0 && 10 != position) {
                    Utils.gotoActivity(getActivity(), PatientInfoDetailsActivity.class, false, "status", "2");
                } else {
                    Utils.gotoActivity(getActivity(), PatientInfoDetailsActivity.class, false, "status", "3");
                }
            }
        });

    }

}
