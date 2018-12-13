// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.home;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class TipsActivity_ViewBinding implements Unbinder {
  private TipsActivity target;

  @UiThread
  public TipsActivity_ViewBinding(TipsActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public TipsActivity_ViewBinding(TipsActivity target, View source) {
    this.target = target;

    target.mRecyclerView = Utils.findRequiredViewAsType(source, R.id.tips_recycler, "field 'mRecyclerView'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    TipsActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRecyclerView = null;
  }
}
