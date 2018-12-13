// Generated code from Butter Knife. Do not modify!
package com.baihua.yaya.login;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.baihua.yaya.R;
import com.baihua.yaya.widget.ValidateCodeView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view2131296610;

  private View view2131296611;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    target.registerEtMobileNo = Utils.findRequiredViewAsType(source, R.id.register_et_mobile_no, "field 'registerEtMobileNo'", EditText.class);
    view = Utils.findRequiredView(source, R.id.login_tv_get_code, "field 'loginTvGetCode' and method 'onViewClicked'");
    target.loginTvGetCode = Utils.castView(view, R.id.login_tv_get_code, "field 'loginTvGetCode'", ValidateCodeView.class);
    view2131296610 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.loginEtCode = Utils.findRequiredViewAsType(source, R.id.login_et_code, "field 'loginEtCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.login_tv_login, "field 'loginTvLogin' and method 'onViewClicked'");
    target.loginTvLogin = Utils.castView(view, R.id.login_tv_login, "field 'loginTvLogin'", TextView.class);
    view2131296611 = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.registerEtMobileNo = null;
    target.loginTvGetCode = null;
    target.loginEtCode = null;
    target.loginTvLogin = null;

    view2131296610.setOnClickListener(null);
    view2131296610 = null;
    view2131296611.setOnClickListener(null);
    view2131296611 = null;
  }
}
