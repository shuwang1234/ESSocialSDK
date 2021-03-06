package com.encore.actionnow;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.elbbbird.android.socialsdk.SocialSDK;
import com.elbbbird.android.socialsdk.model.SocialShareScene;
import com.elbbbird.android.socialsdk.model.SocialToken;
import com.elbbbird.android.socialsdk.model.SocialUser;
import com.elbbbird.android.socialsdk.otto.SSOBusEvent;
import com.elbbbird.android.socialsdk.otto.BusProvider;
import com.elbbbird.android.socialsdk.otto.ShareBusEvent;
import com.elbbbird.android.socialsdk.share.SocialShareActivity;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.constant.WBConstants;
import com.squareup.otto.Subscribe;
import com.tencent.connect.common.Constants;

public class SocialActivity extends AppCompatActivity implements View.OnClickListener, IWeiboHandler.Response {

    private static final String TAG = "SocialActivity";

    private Button btnLoginWeibo;
    private Button btnLogoutWeibo;
    private Button btnLoginWeChat;
    private Button btnLogoutWeChat;
    private Button btnLoginQQ;
    private Button btnLogoutQQ;
    private Button btnLoginAll;
    private Button btnLogoutAll;
    private Button btnShareAll;
    private Button btnShareWeibo;
    private Button btnShareWeChat;
    private Button btnShareWeChatTimeline;
    private Button btnShareQQ;
    private Button btnShareQZone;

    private SocialShareScene scene = new SocialShareScene(0, "演技派", SocialShareScene.SHARE_TYPE_WECHAT, "Android 开源社会化登录 SDK，支持微信，微博， QQ",
            "像友盟， ShareSDK 等平台也提供类似的 SDK ，之所以造轮子是因为这些平台的 SDK 内部肯定会带有数据统计功能，不想给他们共享数据。",
            "http://cdn.v2ex.co/gravatar/becb0d5c59469a34a54156caef738e90?s=73&d=retro", "http://www.v2ex.com/t/238165");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnLoginWeibo = (Button) findViewById(R.id.btn_login_weibo);
        btnLoginWeibo.setOnClickListener(this);
        btnLogoutWeibo = (Button) findViewById(R.id.btn_logout_weibo);
        btnLogoutWeibo.setOnClickListener(this);
        btnLoginWeChat = (Button) findViewById(R.id.btn_login_wechat);
        btnLoginWeChat.setOnClickListener(this);
        btnLogoutWeChat = (Button) findViewById(R.id.btn_logout_wechat);
        btnLogoutWeChat.setOnClickListener(this);
        btnLoginQQ = (Button) findViewById(R.id.btn_login_qq);
        btnLoginQQ.setOnClickListener(this);
        btnLogoutQQ = (Button) findViewById(R.id.btn_logout_qq);
        btnLogoutQQ.setOnClickListener(this);
        btnLoginAll = (Button) findViewById(R.id.btn_login_all);
        btnLoginAll.setOnClickListener(this);
        btnLogoutAll = (Button) findViewById(R.id.btn_logout_all);
        btnLogoutAll.setOnClickListener(this);
        btnShareAll = (Button) findViewById(R.id.btn_share_all);
        btnShareAll.setOnClickListener(this);
        btnShareWeibo = (Button) findViewById(R.id.btn_share_weibo);
        btnShareWeibo.setOnClickListener(this);
        btnShareWeChat = (Button) findViewById(R.id.btn_share_wechat);
        btnShareWeChat.setOnClickListener(this);
        btnShareWeChatTimeline = (Button) findViewById(R.id.btn_share_wechat_timeline);
        btnShareWeChatTimeline.setOnClickListener(this);
        btnShareQQ = (Button) findViewById(R.id.btn_share_qq);
        btnShareQQ.setOnClickListener(this);
        btnShareQZone = (Button) findViewById(R.id.btn_share_qzone);
        btnShareQZone.setOnClickListener(this);

        BusProvider.getInstance().register(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                throw new RuntimeException("this is a test of BugHDDDDDD.");
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_weibo:
                SocialSDK.setDebugMode(true);
                SocialSDK.initWeibo("1633462674");
                SocialSDK.oauthWeibo(SocialActivity.this);
                break;
            case R.id.btn_logout_weibo:
                SocialSDK.revokeWeibo(SocialActivity.this);
                break;
            case R.id.btn_login_wechat:
                SocialSDK.setDebugMode(true);
                SocialSDK.initWeChat("wx3ecc7ffe590fd845", "1b3f07fa99d82232d360c359f6504980");
                SocialSDK.oauthWeChat(SocialActivity.this);
                break;
            case R.id.btn_logout_wechat:
                SocialSDK.revokeWeChat(SocialActivity.this);
                break;
            case R.id.btn_login_qq:
                SocialSDK.setDebugMode(true);
                SocialSDK.initQQ("1104664609");
                SocialSDK.oauthQQ(SocialActivity.this);
                break;
            case R.id.btn_logout_qq:
                SocialSDK.revokeQQ(SocialActivity.this);
                break;
            case R.id.btn_login_all:
                SocialSDK.setDebugMode(true);
                SocialSDK.init("wx3ecc7ffe590fd845", "1b3f07fa99d82232d360c359f6504980", "1633462674", "1104664609");
                SocialSDK.oauth(SocialActivity.this);
                break;
            case R.id.btn_logout_all:
                SocialSDK.revoke(SocialActivity.this);
                break;
            case R.id.btn_share_all:
                Intent intent = new Intent();
                intent.setClass(SocialActivity.this, SocialShareActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_share_qq:
                SocialSDK.setDebugMode(true);
                SocialSDK.shareToQQ(SocialActivity.this, "1104664609", scene);
                break;
            case R.id.btn_share_qzone:
                SocialSDK.setDebugMode(true);
                SocialSDK.shareToQZone(SocialActivity.this, "1104664609", scene);
                break;
            case R.id.btn_share_wechat:
                SocialSDK.setDebugMode(true);
                SocialSDK.shareToWeChat(SocialActivity.this, "wx3ecc7ffe590fd845", scene);
                break;
            case R.id.btn_share_wechat_timeline:
                SocialSDK.setDebugMode(true);
                SocialSDK.shareToWeChatTimeline(SocialActivity.this, "wx3ecc7ffe590fd845", scene);
                break;
            case R.id.btn_share_weibo:
                SocialSDK.setDebugMode(true);
                SocialSDK.shareToWeibo(SocialActivity.this, "1633462674", scene);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SocialSDK.oauthWeiboCallback(SocialActivity.this, requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_LOGIN || requestCode == Constants.REQUEST_APPBAR) {
            SocialSDK.oauthQQCallback(requestCode, resultCode, data);
        }

        if (requestCode == Constants.REQUEST_QZONE_SHARE || requestCode == Constants.REQUEST_QQ_SHARE) {
            SocialSDK.shareToQCallback(requestCode, resultCode, data);
        }

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        SocialSDK.shareToWeiboCallback(intent, this);
    }

    @Subscribe
    public void onOauthResult(SSOBusEvent event) {
        switch (event.getType()) {
            case SSOBusEvent.TYPE_GET_TOKEN:
                SocialToken token = event.getToken();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_GET_TOKEN " + token.toString());
                break;
            case SSOBusEvent.TYPE_GET_USER:
                SocialUser user = event.getUser();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_GET_USER " + user.toString());
                break;
            case SSOBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_FAILURE " + e.toString());
                break;
            case SSOBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onOauthResult#BusEvent.TYPE_CANCEL");
                break;
        }
    }

    @Subscribe
    public void onShareResult(ShareBusEvent event) {
        switch (event.getType()) {
            case ShareBusEvent.TYPE_SUCCESS:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_SUCCESS " + event.getId());
                break;
            case ShareBusEvent.TYPE_FAILURE:
                Exception e = event.getException();
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_FAILURE " + e.toString());
                break;
            case ShareBusEvent.TYPE_CANCEL:
                Log.i(TAG, "onShareResult#ShareBusEvent.TYPE_CANCEL");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        BusProvider.getInstance().unregister(this);
        super.onDestroy();
    }

    @Override
    public void onResponse(BaseResponse baseResponse) {
        switch (baseResponse.errCode) {
            case WBConstants.ErrorCode.ERR_OK:
                BusProvider.getInstance().post(new ShareBusEvent(ShareBusEvent.TYPE_SUCCESS, scene.getType(), scene.getId()));
                break;
            case WBConstants.ErrorCode.ERR_CANCEL:
                BusProvider.getInstance().post(new ShareBusEvent(ShareBusEvent.TYPE_CANCEL, scene.getType()));
                break;
            case WBConstants.ErrorCode.ERR_FAIL:
                BusProvider.getInstance().post(new ShareBusEvent(ShareBusEvent.TYPE_FAILURE, scene.getType(), new Exception("WBConstants.ErrorCode.ERR_FAIL")));
                break;
        }
    }
}
