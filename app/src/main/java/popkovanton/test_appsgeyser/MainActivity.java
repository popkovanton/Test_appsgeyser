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

import java.util.ArrayList;

import popkovanton.test_appsgeyser.data.ModelHistoryElement;
import popkovanton.test_appsgeyser.database.DBHelper;
import popkovanton.test_appsgeyser.fragment.HistoryFragment;
import popkovanton.test_appsgeyser.fragment.NewTextFragment;

public class MainActivity extends AppCompatActivity {


    public static ArrayList<ModelHistoryElement> arrayHistoryElements = new ArrayList<>();
    private Toolbar toolBar;

    private HistoryFragment historyFragment = new HistoryFragment();
    private NewTextFragment newTextFragment = new NewTextFragment();

    public static DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initNavigationView();
        initDB();

//выбор фрагмента, для загрузки на стартовый экран
        displaySelectedScreen(R.id.newTextNaviItem);
    }

    // инициализация тулбара
    private void initToolbar() {
        toolBar = findViewById(R.id.toolbar);
        if (toolBar != null) {
            toolBar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
            setSupportActionBar(toolBar);
        }
    }

    private void initNavigationView() {

        NavigationView navigationView = findViewById(R.id.navigation);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressWarnings("StatementWithEmptyBody")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                displaySelectedScreen(menuItem.getItemId());
                return true;
            }
        });
    }

// инициализация базы данных
    private void initDB() {
        dbHelper = new DBHelper(this);
        if (arrayHistoryElements.size() == 0) {
            arrayHistoryElements = dbHelper.query().getHistoryItems();
        }
        dbHelper.close();
    }

    private void displaySelectedScreen(int itemId) {

        Fragment fragment = null;

        switch (itemId) {
            case R.id.newTextNaviItem:
                fragment = newTextFragment;
                break;
            case R.id.historyNaviItem:
                fragment = historyFragment;
                break;
        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

}
