// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.doctor;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AppointmentConfirmActivity_ViewBinding implements Unbinder {
  private AppointmentConfirmActivity target;

  private View view2131297137;

  private View view2131297136;

  @UiThread
  public AppointmentConfirmActivity_ViewBinding(AppointmentConfirmActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public AppointmentConfirmActivity_ViewBinding(final AppointmentConfirmActivity target,
      View source) {
    this.target = target;

    View view;
    target.confirmTvHospital = Utils.findRequiredViewAsType(source, R.id.confirm_tv_hospital, "field 'confirmTvHospital'", TextView.class);
    target.visitingTvDepartment = Utils.findRequiredViewAsType(source, R.id.visiting_tv_department, "field 'visitingTvDepartment'", TextView.class);
    target.visitingTvDoctor = Utils.findRequiredViewAsType(source, R.id.visiting_tv_doctor, "field 'visitingTvDoctor'", TextView.class);
    target.visitingTvPrice = Utils.findRequiredViewAsType(source, R.id.visiting_tv_price, "field 'visitingTvPrice'", TextView.class);
    target.visitingTvName = Utils.findRequiredViewAsType(source, R.id.visiting_tv_name, "field 'visitingTvName'", TextView.class);
    target.visitingTvAge = Utils.findRequiredViewAsType(source, R.id.visiting_tv_age, "field 'visitingTvAge'", TextView.class);
    target.visitingTvGender = Utils.findRequiredViewAsType(source, R.id.visiting_tv_gender, "field 'visitingTvGender'", TextView.class);
    target.visitingTvMobile = Utils.findRequiredViewAsType(source, R.id.visiting_tv_mobile, "field 'visitingTvMobile'", TextView.class);
    target.visitingTvTime = Utils.findRequiredViewAsType(source, R.id.visiting_tv_time, "field 'visitingTvTime'", TextView.class);
    view = Utils.findRequiredView(source, R.id.visiting_ll_wechat, "field 'visitingLlWechat' and method 'onViewClicked'");
    target.visitingLlWechat = Utils.castView(view, R.id.visiting_ll_wechat, "field 'visitingLlWechat'", LinearLayout.class);
    view2131297137 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.visiting_ll_alipay, "field 'visitingLlAlipay' and method 'onViewClicked'");
    target.visitingLlAlipay = Utils.castView(view, R.id.visiting_ll_alipay, "field 'visitingLlAlipay'", LinearLayout.class);
    view2131297136 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    AppointmentConfirmActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.confirmTvHospital = null;
    target.visitingTvDepartment = null;
    target.visitingTvDoctor = null;
    target.visitingTvPrice = null;
    target.visitingTvName = null;
    target.visitingTvAge = null;
    target.visitingTvGender = null;
    target.visitingTvMobile = null;
    target.visitingTvTime = null;
    target.visitingLlWechat = null;
    target.visitingLlAlipay = null;

    view2131297137.setOnClickListener(null);
    view2131297137 = null;
    view2131297136.setOnClickListener(null);
    view2131297136 = null;
  }
}
