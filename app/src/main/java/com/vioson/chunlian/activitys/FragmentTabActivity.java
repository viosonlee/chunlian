package com.vioson.chunlian.activitys;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.Toast;

import com.vioson.chunlian.R;
import com.vioson.chunlian.fragments.WordFragment;
import com.vioson.chunlian.fragments.LunarFragment;
import com.vioson.chunlian.fragments.AboutFragment;
import com.vioson.chunlian.util.ShareUtil;

import java.util.HashMap;
import java.util.Map;


public class FragmentTabActivity extends AppCompatActivity {
    private long exitTime = 0;
    private TabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        DataUtil.requestNotTitleBar(this);

        setContentView(R.layout.fragment_tabs);

        setFirst();


        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        TabManager mTabManager = new TabManager(this, mTabHost, R.id.containertabcontent);

        RelativeLayout app = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.tab_layout_chunlian, null);
        mTabManager.addTab(mTabHost.newTabSpec("Apps")
                .setIndicator(app), WordFragment.class, null);

        RelativeLayout contacts = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.tab_layout_zhufu, null);

        mTabManager.addTab(mTabHost.newTabSpec("Contact")
                .setIndicator(contacts), LunarFragment.class, null);

        RelativeLayout message = (RelativeLayout) getLayoutInflater().inflate(
                R.layout.tab_layout_about, null);
        mTabManager.addTab(mTabHost.newTabSpec("Message")
                .setIndicator(message), AboutFragment.class, null);

        findViewById(R.id.btn_settings).setOnClickListener(mOnClickListener);

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tag"));
        }
    }

    private void setFirst() {
        SharedPreferences preferences = getSharedPreferences("first_pref", Context.MODE_PRIVATE);
        Editor editor = preferences.edit();
        editor.putBoolean("isFirstIn", false);
        editor.apply();
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_settings:
                    send();
                    break;
            }
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(this, getString(R.string.press_to_exit), Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_share)
            send();
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("tag", mTabHost.getCurrentTabTag());
    }

    public static class TabManager implements TabHost.OnTabChangeListener {
        private final FragmentTabActivity mActivity;

        private final Map<String, TabInfo> mTabs = new HashMap<String, TabInfo>();
        private final TabHost mTabHost;
        private final int mContainerID;
        private TabInfo mLastTab;

        /**
         * @param activity    context
         * @param tabHost     tab
         * @param containerID fragment's parent note
         */
        public TabManager(FragmentTabActivity activity, TabHost tabHost,
                          int containerID) {
            mActivity = activity;
            mTabHost = tabHost;
            mContainerID = containerID;
            mTabHost.setOnTabChangedListener(this);
        }

        static final class TabInfo {
            private final String tag;
            private final Class<?> clss;
            private final Bundle args;
            private Fragment fragment;

            TabInfo(String _tag, Class<?> _clss, Bundle _args) {
                tag = _tag;
                clss = _clss;
                args = _args;
            }
        }

        static class TabFactory implements TabHost.TabContentFactory {
            private Context mContext;

            TabFactory(Context context) {
                mContext = context;
            }

            @Override
            public View createTabContent(String tag) {
                View v = new View(mContext);
                v.setMinimumHeight(0);
                v.setMinimumWidth(0);
                return v;
            }
        }


        public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
            tabSpec.setContent(new TabFactory(mActivity));
            String tag = tabSpec.getTag();

            TabInfo info = new TabInfo(tag, clss, args);
            final FragmentManager fm = mActivity.getSupportFragmentManager();
            info.fragment = fm.findFragmentByTag(tag);
            if (info.fragment != null && !info.fragment.isDetached()) {
                FragmentTransaction ft = fm.beginTransaction();
                ft.detach(info.fragment);
                ft.commit();
            }
            mTabs.put(tag, info);
            mTabHost.addTab(tabSpec);
        }

        @Override
        public void onTabChanged(String tabId) {
            TabInfo newTab = mTabs.get(tabId);
            if (mLastTab != newTab) {
                FragmentManager fragmentManager = mActivity
                        .getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager
                        .beginTransaction();

                if (mLastTab != null && mLastTab.fragment != null) {
                    fragmentTransaction.detach(mLastTab.fragment);
                }
                if (newTab != null) {
                    if (newTab.fragment == null) {
                        newTab.fragment = Fragment.instantiate(mActivity,
                                newTab.clss.getName(), newTab.args);
                        fragmentTransaction.add(mContainerID, newTab.fragment,
                                newTab.tag);
                    } else {

                        fragmentTransaction.attach(newTab.fragment);
                    }
                }
                mLastTab = newTab;
                fragmentTransaction.commit();
                fragmentManager.executePendingTransactions();
            }
        }
    }


    protected void send() {
        String msg = getString(R.string.msg_word);
        ShareUtil.share(this, msg);
    }

}