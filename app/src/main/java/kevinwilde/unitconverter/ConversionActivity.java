package kevinwilde.unitconverter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ConversionActivity extends AppCompatActivity {

    private static final String TAG = "ConversionActivity";
    public static final String EXTRA = "ConversionType";

    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView)findViewById(R.id.navView);

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.mainContainerView, new HomeFragment()).commit();


        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                mDrawerLayout.closeDrawers();

                FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
                switch (menuItem.getItemId()) {
                    case R.id.nav_item_home:
                        fragmentTransaction.replace(R.id.mainContainerView, new HomeFragment())
                                .commit();
                        break;
                    case R.id.nav_item_distance:
                        fragmentTransaction.replace(R.id.mainContainerView, new DistanceFragment())
                                .commit();
                        break;
                    case R.id.nav_item_temp:
                        fragmentTransaction.replace(R.id.mainContainerView, new TemperatureFragment())
                                .commit();
                        break;
                    case R.id.nav_item_mass:
                        fragmentTransaction.replace(R.id.mainContainerView, new MassFragment())
                                .commit();
                        break;
//                    case R.id.nav_item_volume:
//                        fragmentTransaction.replace(R.id.mainContainerView, new VolumeFragment()).commit();
//                        break;
                    case R.id.nav_item_settings:
                        fragmentTransaction.replace(R.id.mainContainerView, new SettingsFragment()).commit();
                        break;
                    default:
                        fragmentTransaction.replace(R.id.mainContainerView, new HomeFragment()).commit();
                        break;
                }
                return false;
            }
        });


        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this,mDrawerLayout, toolbar,R.string.app_name,
                R.string.app_name);

        mDrawerLayout.setDrawerListener(mDrawerToggle);

        mDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_home:
                mFragmentManager.beginTransaction()
                        .replace(R.id.mainContainerView, new HomeFragment())
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
