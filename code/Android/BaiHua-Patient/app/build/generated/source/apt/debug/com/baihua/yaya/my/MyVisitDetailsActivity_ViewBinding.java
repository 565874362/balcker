// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyVisitDetailsActivity_ViewBinding implements Unbinder {
  private MyVisitDetailsActivity target;

  @UiThread
  public MyVisitDetailsActivity_ViewBinding(MyVisitDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyVisitDetailsActivity_ViewBinding(MyVisitDetailsActivity target, View source) {
    this.target = target;

    target.layoutPatientTvName = Utils.findRequiredViewAsType(source, R.id.layout_patient_tv_name, "field 'layoutPatientTvName'", TextView.class);
    target.layoutPatientTvGender = Utils.findRequiredViewAsType(source, R.id.layout_patient_tv_gender, "field 'layoutPatientTvGender'", TextView.class);
    target.layoutPatientTvAge = Utils.findRequiredViewAsType(source, R.id.layout_patient_tv_age, "field 'layoutPatientTvAge'", TextView.class);
    target.layoutPatientTvDescription = Utils.findRequiredViewAsType(source, R.id.layout_patient_tv_description, "field 'layoutPatientTvDescription'", TextView.class);
    target.ivPlayVoiceAnim = Utils.findRequiredViewAsType(source, R.id.iv_play_voice_anim, "field 'ivPlayVoiceAnim'", ImageView.class);
    target.playVoiceLayout = Utils.findRequiredViewAsType(source, R.id.play_voice_layout, "field 'playVoiceLayout'", RelativeLayout.class);
    target.layoutPatientRecycler = Utils.findRequiredViewAsType(source, R.id.layout_patient_recycler, "field 'layoutPatientRecycler'", RecyclerView.class);
    target.layoutPatientTvDate = Utils.findRequiredViewAsType(source, R.id.layout_patient_tv_date, "field 'layoutPatientTvDate'", TextView.class);
    target.itemDoctorIvAvatar = Utils.findRequiredViewAsType(source, R.id.item_doctor_iv_avatar, "field 'itemDoctorIvAvatar'", ImageView.class);
    target.itemDoctorTvName = Utils.findRequiredViewAsType(source, R.id.item_doctor_tv_name, "field 'itemDoctorTvName'", TextView.class);
    target.itemDoctorTvJob = Utils.findRequiredViewAsType(source, R.id.item_doctor_tv_job, "field 'itemDoctorTvJob'", TextView.class);
    target.itemDoctorTvHospital = Utils.findRequiredViewAsType(source, R.id.item_doctor_tv_hospital, "field 'itemDoctorTvHospital'", TextView.class);
    target.itemDoctorTvDepartment = Utils.findRequiredViewAsType(source, R.id.item_doctor_tv_department, "field 'itemDoctorTvDepartment'", TextView.class);
    target.itemDoctorTvGood = Utils.findRequiredViewAsType(source, R.id.item_doctor_tv_good, "field 'itemDoctorTvGood'", TextView.class);
    target.layoutReplyInitialDiagnosis = Utils.findRequiredViewAsType(source, R.id.layout_reply_initial_diagnosis, "field 'layoutReplyInitialDiagnosis'", EditText.class);
    target.layoutReplyNeedToCheck = Utils.findRequiredViewAsType(source, R.id.layout_reply_need_to_check, "field 'layoutReplyNeedToCheck'", LinearLayout.class);
    target.layoutReplyRecycler = Utils.findRequiredViewAsType(source, R.id.layout_reply_recycler, "field 'layoutReplyRecycler'", RecyclerView.class);
    target.layoutReplyWarmDoctor = Utils.findRequiredViewAsType(source, R.id.layout_reply_warm_doctor, "field 'layoutReplyWarmDoctor'", EditText.class);
    target.layoutReplyTime = Utils.findRequiredViewAsType(source, R.id.layout_reply_time, "field 'layoutReplyTime'", TextView.class);
    target.layoutReplyAdvisory = Utils.findRequiredViewAsType(source, R.id.layout_reply_advisory, "field 'layoutReplyAdvisory'", TextView.class);
    target.layoutReplyVisit = Utils.findRequiredViewAsType(source, R.id.layout_reply_visit, "field 'layoutReplyVisit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyVisitDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layoutPatientTvName = null;
    target.layoutPatientTvGender = null;
    target.layoutPatientTvAge = null;
    target.layoutPatientTvDescription = null;
    target.ivPlayVoiceAnim = null;
    target.playVoiceLayout = null;
    target.layoutPatientRecycler = null;
    target.layoutPatientTvDate = null;
    target.itemDoctorIvAvatar = null;
    target.itemDoctorTvName = null;
    target.itemDoctorTvJob = null;
    target.itemDoctorTvHospital = null;
    target.itemDoctorTvDepartment = null;
    target.itemDoctorTvGood = null;
    target.layoutReplyInitialDiagnosis = null;
    target.layoutReplyNeedToCheck = null;
    target.layoutReplyRecycler = null;
    target.layoutReplyWarmDoctor = null;
    target.layoutReplyTime = null;
    target.layoutReplyAdvisory = null;
    target.layoutReplyVisit = null;
  }
}
