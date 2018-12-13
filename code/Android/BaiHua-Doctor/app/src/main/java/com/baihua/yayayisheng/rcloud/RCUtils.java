package com.baihua.yayayisheng.rcloud;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

/**
 * Author:byd
 * Time:10/12/2018 9:35
 * Description: RCUtils
 */
public class RCUtils {

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
