package com.example.mcc.gamebox;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private static final String TAG = "MainActivity";
    // ↓ 为RecycleView 的 Item 定义一个数组,也就是各个游戏
    private Game[] games = {
            new Game("Apple", R.drawable.m_game), new Game("推箱子", R.drawable.m_girl),
            new Game("2048", R.drawable.m_game), new Game("俄罗斯方块", R.drawable.m_game),
            new Game("炸弹人", R.drawable.m_game), new Game("贪吃蛇", R.drawable.m_game),
            new Game("飞机大战", R.drawable.m_game), new Game("Strawberry", R.drawable.m_game),
            new Game("Cherry", R.drawable.m_game), new Game("Mango", R.drawable.m_game)
    };
    private List<Game> gameList = new ArrayList<>();
    private GameAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // ↓
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar !=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // ↓ 菜单项的响应事件
        navView.setCheckedItem(R.id.nav_call);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                return true;
            }
        });

        // ↓ 为悬浮按钮添加事件处理
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(MainActivity.this,"点击了FAB",Toast.LENGTH_SHORT).show();
            }
        });

        // ↓ 使用 RecyclerView
        initGames();
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GameAdapter(gameList);
        recyclerView.setAdapter(adapter);

    }

    // ↓ 将数组中的各项逐一添加到gameList中，并通过适配器的构造方法添加到RecycleView中。
    private void initGames() {
        int i;
        gameList.clear();
        for (i=0; i<5; i++){
            gameList.add(games[i]);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.user:
                Toast.makeText(this,"你点击了Backup",Toast.LENGTH_SHORT).show();
                break;
            case  android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
                default:
        }
        return true;
    }

}
