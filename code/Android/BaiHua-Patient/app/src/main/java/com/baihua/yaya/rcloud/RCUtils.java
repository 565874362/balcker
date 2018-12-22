package com.baihua.yaya.rcloud;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.baihua.common.rx.Observers.ProgressObserver;
import com.baihua.common.rx.RxHttp;
import com.baihua.common.rx.RxSchedulers;
import com.baihua.yaya.MainActivity;
import com.baihua.yaya.entity.AccountEntity;
import com.baihua.yaya.util.CommonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.Utils;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

import static io.rong.imkit.utils.SystemUtils.getCurProcessName;

/**
 * Author:byd
 * Time:10/12/2018 9:35
 * Description: RCUtils
 */
public class RCUtils {

    /**
     * <p>连接服务器，在整个应用程序全局，只需要调用一次，需在 @init(Context) 之后调用。</p>
     * <p>如果调用此接口遇到连接失败，SDK 会自动启动重连机制进行最多10次重连，分别是1, 2, 4, 8, 16, 32, 64, 128, 256, 512秒后。
     * 在这之后如果仍没有连接成功，还会在当检测到设备网络状态变化时再次进行重连。</p>
     *
     * @param token 从服务端获取的用户身份令牌（Token）。
     * @return RongIM  客户端核心类的实例。
     */
    public static void connect(Context context, String token) {

        if (Utils.getApp().getApplicationInfo().packageName.equals(getCurProcessName(Utils.getApp()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtils.e("----====onTokenIncorrect====----");
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 token 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    Log.d("LoginActivity", "--onSuccess" + userid);
                    /**
                     * 设置当前用户信息，
                     *
                     * @param userInfo 当前用户信息
                     *                 new UserInfo("luckerbai",
                     *                 "令狐小影",
                     *                 Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541485393251&di=0bee903f56c4375f4174fcc2509669b7&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F908fa0ec08fa513dde0e76f8376d55fbb2fbd974.jpg"))
                     */
                    String photo = SPUtils.getInstance("account").getString("photo", "");
                    LogUtils.e(photo);
                    RongIM.getInstance().setCurrentUserInfo(new UserInfo(userid, "患者", Uri.parse(photo)));
                    /**
                     * 设置消息体内是否携带用户信息。
                     *
                     * @param state 是否携带用户信息，true 携带，false 不携带。
                     */
                    RongIM.getInstance().setMessageAttachedUserInfo(true);

                    setUserInfo(context);

                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.e("onError----==== " + errorCode + " ====----onError");
                }
            });
        }
    }

    static UserInfo userInfo;

    private static void setUserInfo(Context context) {
        /**
         * 设置用户信息的提供者，供 RongIM 调用获取用户名称和头像信息。
         *
         * @param userInfoProvider 用户信息提供者。
         * @param isCacheUserInfo  设置是否由 IMKit 来缓存用户信息。<br>
         *                         如果 App 提供的 UserInfoProvider
         *                         每次都需要通过网络请求用户数据，而不是将用户数据缓存到本地内存，会影响用户信息的加载速度；<br>
         *                         此时最好将本参数设置为 true，由 IMKit 将用户信息缓存到本地内存中。
         * @see UserInfoProvider
         */
        RongIM.setUserInfoProvider(new RongIM.UserInfoProvider() {

            @Override
            public UserInfo getUserInfo(String userId) {

                RxHttp.getInstance().getSyncServer()
                        .getAccount(CommonUtils.getToken(), userId)
                        .compose(RxSchedulers.observableIO2Main(context))
                        .subscribe(new ProgressObserver<AccountEntity>(context) {
                            @Override
                            public void onSuccess(AccountEntity result) {
                                userInfo = new UserInfo(result.getInfo().getAccountId(), result.getInfo().getName(), Uri.parse(result.getInfo().getPhoto()));
                            }

                            @Override
                            public void onFailure(Throwable e, String errorMsg) {
                                LogUtils.e(errorMsg);
                            }
                        });
                return userInfo;//根据 userId 去你的用户系统里查询对应的用户信息返回给融云 SDK。
            }

        }, true);
    }

    /**
     * 启动会话列表界面。
     *
     * @param context               应用上下文。
     * @param supportedConversation 定义会话列表支持显示的会话类型，及对应的会话类型是否聚合显示。
     *                              例如：supportedConversation.put(Conversation.ConversationType.PRIVATE.getName(), false) 非聚合式显示 private 类型的会话。
     */
    public static void startConversationList(Context context) {
        Map<String, Boolean> supportConversation = new HashMap<>();
        supportConversation.put(Conversation.ConversationType.PRIVATE.getName(), false);
        RongIM.getInstance().startConversationList(context, supportConversation);
    }

    /**
     * <p>启动会话界面。</p>
     * <p>使用时，可以传入多种会话类型 {@link io.rong.imlib.model.Conversation.ConversationType} 对应不同的会话类型，开启不同的会话界面。
     * 如果传入的是 {@link io.rong.imlib.model.Conversation.ConversationType#CHATROOM}，sdk 会默认调用
     * {@link RongIMClient#joinChatRoom(String, int, RongIMClient.OperationCallback)} 加入聊天室。
     * 如果你的逻辑是，只允许加入已存在的聊天室，请使用接口 {@link #startChatRoomChat(Context, String, boolean)} 并且第三个参数为 true</p>
     *
     * @param context          应用上下文。
     * @param conversationType 会话类型。
     * @param targetId         根据不同的 conversationType，可能是用户 Id、群组 Id 或聊天室 Id。
     * @param title            聊天的标题，开发者可以在聊天界面通过 intent.getData().getQueryParameter("title") 获取该值, 再手动设置为标题。
     */
    public static void startConversation(Context context, String targetId, String title) {
        RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, targetId, title);
    }

}
