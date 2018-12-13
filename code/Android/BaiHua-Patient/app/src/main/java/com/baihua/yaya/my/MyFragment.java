package com.baihua.yaya.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baihua.common.base.BaseFragment;
import com.baihua.yaya.R;
import com.baihua.yaya.rcloud.ConversationListActivity;
import com.baihua.yaya.rcloud.RCUtils;
import com.baihua.yaya.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Author:byd
 * Time:4/12/2018 14:39
 * Description: 我的
 */
public class MyFragment extends BaseFragment {

    @BindView(R.id.my_iv_avatar)
    ImageView myIvAvatar;
    @BindView(R.id.my_ll_visiting)
    LinearLayout myLlVisting;
    @BindView(R.id.my_ll_my_registered)
    LinearLayout myLlMyTreatment;
    @BindView(R.id.my_ll_ask_and_answer)
    LinearLayout myLlTreatmentTime;
    @BindView(R.id.my_tv_exit_account)
    TextView myTvExitAccount;

    @Override
    public int setRootView() {
        return R.layout.fragment_my;
    }

    @Override
    public void setLayout() {

    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        initContent();
    }

    private void initContent() {
        Utils.showCircleImg(getActivity(), "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=593762100,3871765340&fm=26&gp=0.jpg", myIvAvatar);
    }

    @Override
    public void setListener() {

    }

    @OnClick({R.id.my_iv_avatar, R.id.my_ll_visiting, R.id.my_ll_my_registered, R.id.my_ll_ask_and_answer, R.id.my_tv_exit_account})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_iv_avatar:
                break;
            case R.id.my_ll_visiting:
                ActivityUtils.startActivity(MyVisitActivity.class);
                break;
            case R.id.my_ll_my_registered:
                ActivityUtils.startActivity(MyAppointmentActivity.class);
                break;
            case R.id.my_ll_ask_and_answer:
                RCUtils.startConversationList(getActivity());
                break;
            case R.id.my_tv_exit_account:
                toast("TODO");
                break;
        }
    }
}
