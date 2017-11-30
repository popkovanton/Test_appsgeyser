package popkovanton.test_appsgeyser;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import popkovanton.test_appsgeyser.fragment.HistoryFragment;
import popkovanton.test_appsgeyser.fragment.NewTextFragment;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initNavigationView();

//выбор фрагмента, для загрузки на стартовый экран
        displaySelectedScreen(R.id.newTextNaviItem);
    }

    private void initToolbar() {
        toolBar = (Toolbar) findViewById(R.id.toolbar);
        if (toolBar != null) {
            toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            setSupportActionBar(toolBar);
            toolBar.setTitle(R.string.app_name);
        }
    }

    private void initNavigationView() {

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                displaySelectedScreen(menuItem.getItemId());
                return true;
            }
        });
    }

    private void displaySelectedScreen(int itemId) {

        Fragment fragment = null;

        switch (itemId) {
            case R.id.newTextNaviItem:
                fragment = new NewTextFragment();
                break;
            case R.id.historyNaviItem:
                fragment = new HistoryFragment();
                break;
        }

        //замена фрагмента по выбору списка меню
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }
}
