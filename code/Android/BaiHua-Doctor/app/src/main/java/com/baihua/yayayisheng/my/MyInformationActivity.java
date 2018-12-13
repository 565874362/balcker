package com.baihua.yayayisheng.my;

import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.baihua.common.base.BaseActivity;
import com.baihua.yayayisheng.R;
import com.baihua.yayayisheng.util.Utils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.SpanUtils;

import butterknife.BindView;

/**
 * Author:byd
 * Time:6/12/2018 15:43
 * Description: 我的信息
 */
public class MyInformationActivity extends BaseActivity {
    @BindView(R.id.my_info_image)
    ImageView myInfoImage;
    @BindView(R.id.my_info_tv_name)
    TextView myInfoTvName;
    @BindView(R.id.my_info_tv_job)
    TextView myInfoTvJob;
    @BindView(R.id.my_info_tv_department)
    TextView myInfoTvDepartment;
    @BindView(R.id.my_info_tv_hospital)
    TextView myInfoTvHospital;
    @BindView(R.id.my_info_tv_good_at_one)
    TextView myInfoTvGoodAtOne;
    @BindView(R.id.my_info_tv_good_at_two)
    TextView myInfoTvGoodAtTwo;
    @BindView(R.id.my_info_tv_good_at_three)
    TextView myInfoTvGoodAtThree;
    @BindView(R.id.my_info_iv_doctor_certificate)
    ImageView myInfoIvDoctorCertificate;
    @BindView(R.id.my_info_iv_front_photo)
    ImageView myInfoIvFrontPhoto;
    @BindView(R.id.my_info_iv_reverse_photo)
    ImageView myInfoIvReversePhoto;

    @Override
    public void setLayout() {
        setTitle("我的信息");
        mTvRight.setText("编辑");
        mTvRight.setVisibility(View.VISIBLE);
        setContentView(R.layout.activity_my_information);
    }

    @Override
    public void setHandler() {

    }

    @Override
    public void initMember() {
        setContentText();
    }

    private void setContentText() {
        Utils.showImg(this, "http://img.hb.aicdn.com/7ba7b99a9cbdbb3b965e6b24036e89fee5dfc88530ffd-hlULA7_fw658", myInfoImage);
        myInfoTvName.setText("令狐小影");
        myInfoTvJob.setText("江湖百晓生");
        myInfoTvDepartment.setText("剑客");
        myInfoTvHospital.setText("青衣楼");
        SpannableStringBuilder one = new SpanUtils().append("独孤九剑：").setBold().append("归妹趋无妄，无妄趋同人，同人趋大有。甲转丙，丙转庚，庚转癸。子丑之交，辰巳之交，午未之 交。风雷是一变，山泽是一变，水火是一变。乾坤相激，震兑相激，离巽相激。三增而成五，五增而成九···").create();
        SpannableStringBuilder two = new SpanUtils().append("总决式：").setBold().append("有种种变化，用以体演总诀，共有三百六十种变化。").create();
        SpannableStringBuilder three = new SpanUtils().append("破剑式：").setBold().append("破解普天下各门各派的剑法。").create();
        myInfoTvGoodAtOne.setText(one);
        myInfoTvGoodAtTwo.setText(two);
        myInfoTvGoodAtThree.setText(three);
        Utils.showImg(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544094862406&di=020b3a797cd8288c0901c7020ceeb812&imgtype=0&src=http%3A%2F%2Fpic.baike.soso.com%2Fugc%2Fbaikepic2%2F24851%2F20140822144735-61823944.jpg%2F0", myInfoIvDoctorCertificate);
        Utils.showImg(this, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1544094804168&di=675fb6ff6db6a09468b5d562bb6253d6&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171019%2F3142bd2d25504798b41b2fa66fb66eeb.jpeg", myInfoIvFrontPhoto);
        Utils.showImg(this, "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=848842050,3998338789&fm=26&gp=0.jpg", myInfoIvReversePhoto);
    }

    @Override
    public void setListener() {
        mIbRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(MyInfoEditActivity.class);
            }
        });
    }

}
