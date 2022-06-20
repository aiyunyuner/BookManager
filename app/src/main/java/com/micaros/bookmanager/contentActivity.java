package com.micaros.bookmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import android.os.Bundle;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.google.android.material.navigation.NavigationView;
import com.micaros.bookmanager.pojo.Book;
import com.micaros.bookmanager.utils.HttpGetRequest;
import com.micaros.bookmanager.utils.HttpUtils;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class contentActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private ListView listView;
    private long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        init();
    }


    private void init() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.ic_menu);
        }
        //侧滑菜单栏的选项
        navigationView.setCheckedItem(R.id.shoucang);//设置菜单项的默认选项
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.shoucang:
//                        Intent intent2 = new Intent(contentActivity.this, collectActivity.class);
//                        startActivity(intent2);
                        break;
                    case R.id.exit:
                        finish();
                        break;
                    case R.id.jieyue:
                        //跳转到个人借书的页面
//                        Intent intent = new Intent(contentActivity.this, person_borrow.class);
//                        startActivity(intent);
                        break;
                    case R.id.updateInfo:
                        //跳转到个人借书的页面
//                        Intent intent3 = new Intent(contentActivity.this, ReaderUpdateInfo.class);
//                        startActivity(intent3);
                        break;
                    default:

                }
                drawerLayout.closeDrawers();//将滑动菜单关闭
                return true;
            }
        });
        listView = (ListView) findViewById(R.id.list_view);

        HttpGetRequest.sendOkHttpGetRequest(HttpUtils.address + "/book/all", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Looper.prepare();
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Looper.prepare();
                String string = response.body().string();
                List<Book> bookList = JSON.parseArray(string, Book.class);
                Looper.loop();
            }
        });
    }
}