// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.doctor;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DoctorListFragment_ViewBinding implements Unbinder {
  private DoctorListFragment target;

  @UiThread
  public DoctorListFragment_ViewBinding(DoctorListFragment target, View source) {
    this.target = target;

    target.doctorLlSearch = Utils.findRequiredViewAsType(source, R.id.doctor_ll_search, "field 'doctorLlSearch'", LinearLayout.class);
    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.doctor_recycler, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DoctorListFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.doctorLlSearch = null;
    target.mRecyclerView = null;
  }
}
