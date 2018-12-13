// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyFragment_ViewBinding implements Unbinder {
  private MyFragment target;

  private View view2131296652;

  private View view2131296656;

  private View view2131296654;

  private View view2131296653;

  private View view2131296657;

  @UiThread
  public MyFragment_ViewBinding(final MyFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.my_iv_avatar, "field 'myIvAvatar' and method 'onViewClicked'");
    target.myIvAvatar = Utils.castView(view, R.id.my_iv_avatar, "field 'myIvAvatar'", ImageView.class);
    view2131296652 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.my_ll_visiting, "field 'myLlVisting' and method 'onViewClicked'");
    target.myLlVisting = Utils.castView(view, R.id.my_ll_visiting, "field 'myLlVisting'", LinearLayout.class);
    view2131296656 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.my_ll_my_registered, "field 'myLlMyTreatment' and method 'onViewClicked'");
    target.myLlMyTreatment = Utils.castView(view, R.id.my_ll_my_registered, "field 'myLlMyTreatment'", LinearLayout.class);
    view2131296654 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.my_ll_ask_and_answer, "field 'myLlTreatmentTime' and method 'onViewClicked'");
    target.myLlTreatmentTime = Utils.castView(view, R.id.my_ll_ask_and_answer, "field 'myLlTreatmentTime'", LinearLayout.class);
    view2131296653 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.my_tv_exit_account, "field 'myTvExitAccount' and method 'onViewClicked'");
    target.myTvExitAccount = Utils.castView(view, R.id.my_tv_exit_account, "field 'myTvExitAccount'", TextView.class);
    view2131296657 = view;
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
    MyFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.myIvAvatar = null;
    target.myLlVisting = null;
    target.myLlMyTreatment = null;
    target.myLlTreatmentTime = null;
    target.myTvExitAccount = null;

    view2131296652.setOnClickListener(null);
    view2131296652 = null;
    view2131296656.setOnClickListener(null);
    view2131296656 = null;
    view2131296654.setOnClickListener(null);
    view2131296654 = null;
    view2131296653.setOnClickListener(null);
    view2131296653 = null;
    view2131296657.setOnClickListener(null);
    view2131296657 = null;
  }
}
