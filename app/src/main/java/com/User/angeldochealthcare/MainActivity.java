package com.User.angeldochealthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.User.angeldochealthcare.fragment.BookingHistory;
import com.User.angeldochealthcare.fragment.Home;
import com.User.angeldochealthcare.fragment.Notification;
import com.User.angeldochealthcare.fragment.Profile;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;


public class MainActivity extends AppCompatActivity {
    FrameLayout main_container;
    private MeowBottomNavigation nav_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(getWindow().getContext(), R.color.black));

        nav_view = findViewById(R.id.nav_view);
        nav_view.add(new MeowBottomNavigation.Model(1, R.drawable.baseline_home_24));
        nav_view.add(new MeowBottomNavigation.Model(2, R.drawable.baseline_notifications_active_24));
        nav_view.add(new MeowBottomNavigation.Model(3, R.drawable.baseline_insert_drive_file_24));
        nav_view.add(new MeowBottomNavigation.Model(4, R.drawable.user));

        nav_view.show(1,true);
        replace(new Home());
        nav_view.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new Home());
                        break;
                    case 2:
                        replace(new Notification());
                        break;
                    case 3:
                        replace(new BookingHistory());
                        break;
                    case 4:
                        replace(new Profile());
                        break;
                }
                return null;
            }
        });

    }
    private void replace(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, fragment)
                .commit();
    }
}