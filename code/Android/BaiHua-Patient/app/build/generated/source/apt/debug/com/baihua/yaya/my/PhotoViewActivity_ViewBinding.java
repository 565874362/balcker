// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.my;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import com.baihua.yaya.widget.HackyViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class PhotoViewActivity_ViewBinding implements Unbinder {
  private PhotoViewActivity target;

  @UiThread
  public PhotoViewActivity_ViewBinding(PhotoViewActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public PhotoViewActivity_ViewBinding(PhotoViewActivity target, View source) {
    this.target = target;

    target.viewPager = Utils.findRequiredViewAsType(source, R.id.view_pager, "field 'viewPager'", HackyViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    PhotoViewActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.viewPager = null;
  }
}
