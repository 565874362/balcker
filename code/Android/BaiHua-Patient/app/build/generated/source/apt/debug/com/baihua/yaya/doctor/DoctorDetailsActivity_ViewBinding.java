// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.doctor;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DoctorDetailsActivity_ViewBinding implements Unbinder {
  private DoctorDetailsActivity target;

  private View view2131296437;

  private View view2131296445;

  @UiThread
  public DoctorDetailsActivity_ViewBinding(DoctorDetailsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DoctorDetailsActivity_ViewBinding(final DoctorDetailsActivity target, View source) {
    this.target = target;

    View view;
    target.doctorDetailsImage = Utils.findRequiredViewAsType(source, R.id.doctor_details_image, "field 'doctorDetailsImage'", ImageView.class);
    target.doctorDetailsTvName = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_name, "field 'doctorDetailsTvName'", TextView.class);
    target.doctorDetailsTvJob = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_job, "field 'doctorDetailsTvJob'", TextView.class);
    target.doctorDetailsTvDepartment = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_department, "field 'doctorDetailsTvDepartment'", TextView.class);
    target.doctorDetailsTvHospital = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_hospital, "field 'doctorDetailsTvHospital'", TextView.class);
    target.doctorDetailsTvGoodAtOne = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_good_at_one, "field 'doctorDetailsTvGoodAtOne'", TextView.class);
    target.doctorDetailsTvGoodAtTwo = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_good_at_two, "field 'doctorDetailsTvGoodAtTwo'", TextView.class);
    target.doctorDetailsTvGoodAtThree = Utils.findRequiredViewAsType(source, R.id.doctor_details_tv_good_at_three, "field 'doctorDetailsTvGoodAtThree'", TextView.class);
    view = Utils.findRequiredView(source, R.id.doctor_details_tv_advisory, "field 'doctorDetailsTvAdvisory' and method 'onViewClicked'");
    target.doctorDetailsTvAdvisory = Utils.castView(view, R.id.doctor_details_tv_advisory, "field 'doctorDetailsTvAdvisory'", TextView.class);
    view2131296437 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.doctor_details_tv_visiting, "field 'doctorDetailsTvVisiting' and method 'onViewClicked'");
    target.doctorDetailsTvVisiting = Utils.castView(view, R.id.doctor_details_tv_visiting, "field 'doctorDetailsTvVisiting'", TextView.class);
    view2131296445 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.doctorDetailsTvComment = Utils.findRequiredViewAsType(source, R.id.doctor_details_comment, "field 'doctorDetailsTvComment'", TextView.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.doctor_details_recycler, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DoctorDetailsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.doctorDetailsImage = null;
    target.doctorDetailsTvName = null;
    target.doctorDetailsTvJob = null;
    target.doctorDetailsTvDepartment = null;
    target.doctorDetailsTvHospital = null;
    target.doctorDetailsTvGoodAtOne = null;
    target.doctorDetailsTvGoodAtTwo = null;
    target.doctorDetailsTvGoodAtThree = null;
    target.doctorDetailsTvAdvisory = null;
    target.doctorDetailsTvVisiting = null;
    target.doctorDetailsTvComment = null;
    target.mRecyclerView = null;

    view2131296437.setOnClickListener(null);
    view2131296437 = null;
    view2131296445.setOnClickListener(null);
    view2131296445 = null;
  }
}
