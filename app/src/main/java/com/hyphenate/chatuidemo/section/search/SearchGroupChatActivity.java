package com.hyphenate.chatuidemo.section.search;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.chatuidemo.section.chat.ChatHistoryActivity;
import com.hyphenate.chatuidemo.section.search.adapter.SearchMessageAdapter;
import com.hyphenate.easeui.adapter.EaseBaseRecyclerViewAdapter;
import com.hyphenate.easeui.constants.EaseConstant;

import java.util.List;

public class SearchGroupChatActivity extends SearchActivity {
    private String toUsername;
    private EMConversation conversation;

    public static void actionStart(Context context, String toUsername) {
        Intent intent = new Intent(context, SearchGroupChatActivity.class);
        intent.putExtra("toUsername", toUsername);
        context.startActivity(intent);
    }

    @Override
    protected void initIntent(Intent intent) {
        super.initIntent(intent);
        toUsername = getIntent().getStringExtra("toUsername");
    }

    @Override
    protected EaseBaseRecyclerViewAdapter getAdapter() {
        return new SearchMessageAdapter();
    }

    @Override
    protected void initData() {
        super.initData();
        conversation = EMClient.getInstance().chatManager().getConversation(toUsername, EMConversation.EMConversationType.GroupChat, true);
    }

    @Override
    public void searchMessages(String search) {
        List<EMMessage> mData = conversation.searchMsgFromDB(search, System.currentTimeMillis(), 100, null, EMConversation.EMSearchDirection.UP);
        adapter.setData(mData);
    }

    @Override
    protected void onChildItemClick(View view, int position) {
        EMMessage item = ((SearchMessageAdapter) adapter).getItem(position);
        ChatHistoryActivity.actionStart(mContext, toUsername, EaseConstant.CHATTYPE_GROUP, item.getMsgId());
    }
}