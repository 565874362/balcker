// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.doctor;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AppointmentActivity_ViewBinding implements Unbinder {
  private AppointmentActivity target;

  @UiThread
  public AppointmentActivity_ViewBinding(AppointmentActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AppointmentActivity_ViewBinding(AppointmentActivity target, View source) {
    this.target = target;

    target.appointmentEtPatientName = Utils.findRequiredViewAsType(source, R.id.appointment_et_patient_name, "field 'appointmentEtPatientName'", EditText.class);
    target.appointmentEtPatientAge = Utils.findRequiredViewAsType(source, R.id.appointment_et_patient_age, "field 'appointmentEtPatientAge'", EditText.class);
    target.appointmentRbFemale = Utils.findRequiredViewAsType(source, R.id.appointment_rb_female, "field 'appointmentRbFemale'", RadioButton.class);
    target.homeRbMale = Utils.findRequiredViewAsType(source, R.id.home_rb_male, "field 'homeRbMale'", RadioButton.class);
    target.appointmentRadioGroup = Utils.findRequiredViewAsType(source, R.id.appointment_radio_group, "field 'appointmentRadioGroup'", RadioGroup.class);
    target.appointmentEtPatientMobile = Utils.findRequiredViewAsType(source, R.id.appointment_et_patient_mobile, "field 'appointmentEtPatientMobile'", EditText.class);
    target.appointmentTvSelectTime = Utils.findRequiredViewAsType(source, R.id.appointment_tv_select_time, "field 'appointmentTvSelectTime'", TextView.class);
    target.appointmentTvPrice = Utils.findRequiredViewAsType(source, R.id.appointment_tv_price, "field 'appointmentTvPrice'", TextView.class);
    target.appointmentTvSubmit = Utils.findRequiredViewAsType(source, R.id.appointment_tv_submit, "field 'appointmentTvSubmit'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AppointmentActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.appointmentEtPatientName = null;
    target.appointmentEtPatientAge = null;
    target.appointmentRbFemale = null;
    target.homeRbMale = null;
    target.appointmentRadioGroup = null;
    target.appointmentEtPatientMobile = null;
    target.appointmentTvSelectTime = null;
    target.appointmentTvPrice = null;
    target.appointmentTvSubmit = null;
  }
}
