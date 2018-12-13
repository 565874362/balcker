// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import com.baihua.yaya.view.recorder.RecordButton;
import com.youth.banner.Banner;
import java.lang.IllegalStateException;
import java.lang.Override;
import jaygoo.widget.wlv.WaveLineView;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.mBtnRecorder = Utils.findRequiredViewAsType(source, R.id.btn_recorder, "field 'mBtnRecorder'", Button.class);
    target.mBtnStopRecorder = Utils.findRequiredViewAsType(source, R.id.btn_stop_recorder, "field 'mBtnStopRecorder'", Button.class);
    target.mTvState = Utils.findRequiredViewAsType(source, R.id.tv_state, "field 'mTvState'", TextView.class);
    target.mTvSoundSize = Utils.findRequiredViewAsType(source, R.id.tv_sound_size, "field 'mTvSoundSize'", TextView.class);
    target.mWaveView = Utils.findRequiredViewAsType(source, R.id.wave_view, "field 'mWaveView'", WaveLineView.class);
    target.mPlaySound = Utils.findRequiredViewAsType(source, R.id.btn_play_sound, "field 'mPlaySound'", Button.class);
    target.mPressRecord = Utils.findRequiredViewAsType(source, R.id.btn_press_record, "field 'mPressRecord'", RecordButton.class);
    target.mBanner = Utils.findRequiredViewAsType(source, R.id.banner, "field 'mBanner'", Banner.class);
    target.mPlayVoiceView = Utils.findRequiredView(source, R.id.play_voice_layout, "field 'mPlayVoiceView'");
    target.mRecordVoice = Utils.findRequiredViewAsType(source, R.id.record_voice, "field 'mRecordVoice'", RecordButton.class);
    target.mPreResultRecycler = Utils.findRequiredViewAsType(source, R.id.home_photo_recycler, "field 'mPreResultRecycler'", RecyclerView.class);
    target.mTvSubmit = Utils.findRequiredViewAsType(source, R.id.home_tv_submit, "field 'mTvSubmit'", TextView.class);
    target.mIvPlayVoiceAnim = Utils.findRequiredViewAsType(source, R.id.iv_play_voice_anim, "field 'mIvPlayVoiceAnim'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnRecorder = null;
    target.mBtnStopRecorder = null;
    target.mTvState = null;
    target.mTvSoundSize = null;
    target.mWaveView = null;
    target.mPlaySound = null;
    target.mPressRecord = null;
    target.mBanner = null;
    target.mPlayVoiceView = null;
    target.mRecordVoice = null;
    target.mPreResultRecycler = null;
    target.mTvSubmit = null;
    target.mIvPlayVoiceAnim = null;
  }
}
