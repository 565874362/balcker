// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import com.baihua.yaya.view.recorder.RecordButton;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomepageFragment_ViewBinding implements Unbinder {
  private HomepageFragment target;

  private View view2131296500;

  private View view2131296498;

  private View view2131296499;

  @UiThread
  public HomepageFragment_ViewBinding(final HomepageFragment target, View source) {
    this.target = target;

    View view;
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'mBanner'", Banner.class);
    target.mPlayVoiceView = Utils.findRequiredViewAsType(source, R.id.play_voice_layout, "field 'mPlayVoiceView'", RelativeLayout.class);
    target.mRecordVoice = Utils.findRequiredViewAsType(source, R.id.record_voice, "field 'mRecordVoice'", RecordButton.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.home_photo_recycler, "field 'mRecyclerView'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.home_tv_submit, "field 'mTvSubmit' and method 'onViewClicked'");
    target.mTvSubmit = Utils.castView(view, R.id.home_tv_submit, "field 'mTvSubmit'", TextView.class);
    view2131296500 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.mIvPlayVoiceAnim = Utils.findRequiredViewAsType(source, R.id.iv_play_voice_anim, "field 'mIvPlayVoiceAnim'", ImageView.class);
    target.homeEtName = Utils.findRequiredViewAsType(source, R.id.home_et_name, "field 'homeEtName'", EditText.class);
    target.homeEtAge = Utils.findRequiredViewAsType(source, R.id.home_et_age, "field 'homeEtAge'", EditText.class);
    target.homeRbFemale = Utils.findRequiredViewAsType(source, R.id.home_rb_female, "field 'homeRbFemale'", RadioButton.class);
    target.homeRbMale = Utils.findRequiredViewAsType(source, R.id.home_rb_male, "field 'homeRbMale'", RadioButton.class);
    target.homeRadioGroup = Utils.findRequiredViewAsType(source, R.id.home_radio_group, "field 'homeRadioGroup'", RadioGroup.class);
    target.homeEtMobile = Utils.findRequiredViewAsType(source, R.id.home_et_mobile, "field 'homeEtMobile'", EditText.class);
    view = Utils.findRequiredView(source, R.id.home_tv_select_diet, "field 'homeTvSelectDiet' and method 'onViewClicked'");
    target.homeTvSelectDiet = Utils.castView(view, R.id.home_tv_select_diet, "field 'homeTvSelectDiet'", TextView.class);
    view2131296498 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.home_tv_select_sleep, "field 'homeTvSelectSleep' and method 'onViewClicked'");
    target.homeTvSelectSleep = Utils.castView(view, R.id.home_tv_select_sleep, "field 'homeTvSelectSleep'", TextView.class);
    view2131296499 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.homeEtDescription = Utils.findRequiredViewAsType(source, R.id.home_et_description, "field 'homeEtDescription'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomepageFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBanner = null;
    target.mPlayVoiceView = null;
    target.mRecordVoice = null;
    target.mRecyclerView = null;
    target.mTvSubmit = null;
    target.mIvPlayVoiceAnim = null;
    target.homeEtName = null;
    target.homeEtAge = null;
    target.homeRbFemale = null;
    target.homeRbMale = null;
    target.homeRadioGroup = null;
    target.homeEtMobile = null;
    target.homeTvSelectDiet = null;
    target.homeTvSelectSleep = null;
    target.homeEtDescription = null;

    view2131296500.setOnClickListener(null);
    view2131296500 = null;
    view2131296498.setOnClickListener(null);
    view2131296498 = null;
    view2131296499.setOnClickListener(null);
    view2131296499 = null;
  }
}
