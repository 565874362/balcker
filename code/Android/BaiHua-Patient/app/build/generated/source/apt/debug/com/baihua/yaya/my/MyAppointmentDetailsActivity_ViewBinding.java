// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyAppointmentDetailsActivity_ViewBinding implements Unbinder {
  private MyAppointmentDetailsActivity target;

  @UiThread
  public MyAppointmentDetailsActivity_ViewBinding(MyAppointmentDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyAppointmentDetailsActivity_ViewBinding(MyAppointmentDetailsActivity target,
      View source) {
    this.target = target;

    target.layoutVisitIvAvatar = Utils.findRequiredViewAsType(source, R.id.layout_visit_iv_avatar, "field 'layoutVisitIvAvatar'", ImageView.class);
    target.layoutVisitTvDepartment = Utils.findRequiredViewAsType(source, R.id.layout_visit_tv_department, "field 'layoutVisitTvDepartment'", TextView.class);
    target.layoutVisitTvDoctor = Utils.findRequiredViewAsType(source, R.id.layout_visit_tv_doctor, "field 'layoutVisitTvDoctor'", TextView.class);
    target.layoutVisitTvPrice = Utils.findRequiredViewAsType(source, R.id.layout_visit_tv_price, "field 'layoutVisitTvPrice'", TextView.class);
    target.visitingTvName = Utils.findRequiredViewAsType(source, R.id.visiting_tv_name, "field 'visitingTvName'", TextView.class);
    target.visitingTvAge = Utils.findRequiredViewAsType(source, R.id.visiting_tv_age, "field 'visitingTvAge'", TextView.class);
    target.visitingTvGender = Utils.findRequiredViewAsType(source, R.id.visiting_tv_gender, "field 'visitingTvGender'", TextView.class);
    target.visitingTvMobile = Utils.findRequiredViewAsType(source, R.id.visiting_tv_mobile, "field 'visitingTvMobile'", TextView.class);
    target.visitingTvTime = Utils.findRequiredViewAsType(source, R.id.visiting_tv_time, "field 'visitingTvTime'", TextView.class);
    target.visitingTvPayMethod = Utils.findRequiredViewAsType(source, R.id.visiting_tv_pay_method, "field 'visitingTvPayMethod'", TextView.class);
    target.visitingTvPayTime = Utils.findRequiredViewAsType(source, R.id.visiting_tv_pay_time, "field 'visitingTvPayTime'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MyAppointmentDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.layoutVisitIvAvatar = null;
    target.layoutVisitTvDepartment = null;
    target.layoutVisitTvDoctor = null;
    target.layoutVisitTvPrice = null;
    target.visitingTvName = null;
    target.visitingTvAge = null;
    target.visitingTvGender = null;
    target.visitingTvMobile = null;
    target.visitingTvTime = null;
    target.visitingTvPayMethod = null;
    target.visitingTvPayTime = null;
  }
}
