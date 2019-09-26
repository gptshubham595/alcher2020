package com.codefundo.alcheringa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.google.android.material.navigation.NavigationView;

import at.blogc.android.views.ExpandableTextView;

public class Events extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    ExpandableTextView expandableTextView,expandableTextView2,expandableTextView3,expandableTextView4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        drawerLayout =findViewById(R.id.drawer);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView=findViewById(R.id.nav);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        expandableTextView =findViewById(R.id.expandableTextView1);
        expandableTextView2 =findViewById(R.id.expandableTextView2);
        expandableTextView3 =findViewById(R.id.expandableTextView3);
        expandableTextView4 =findViewById(R.id.expandableTextView4);


        expandableTextView.setAnimationDuration(750L);
        expandableTextView.setInterpolator(new OvershootInterpolator());
        expandableTextView.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView.setCollapseInterpolator(new OvershootInterpolator());

        expandableTextView2.setAnimationDuration(750L);
        expandableTextView2.setInterpolator(new OvershootInterpolator());
        expandableTextView2.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView2.setCollapseInterpolator(new OvershootInterpolator());

        expandableTextView3.setAnimationDuration(750L);
        expandableTextView3.setInterpolator(new OvershootInterpolator());
        expandableTextView3.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView3.setCollapseInterpolator(new OvershootInterpolator());

        expandableTextView4.setAnimationDuration(750L);
        expandableTextView4.setInterpolator(new OvershootInterpolator());
        expandableTextView4.setExpandInterpolator(new OvershootInterpolator());
        expandableTextView4.setCollapseInterpolator(new OvershootInterpolator());


        expandableTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                expandableTextView.toggle();


            }
        });
        expandableTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView.isExpanded())
                {expandableTextView.collapse();
                    expandableTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                }

                else
                {expandableTextView.expand();
                    expandableTextView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                }
            }
        });

        expandableTextView2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                expandableTextView2.toggle();
            }
        });
        expandableTextView2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView2.isExpanded())
                {expandableTextView2.collapse();
                    expandableTextView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                }

                else
                {expandableTextView2.expand();
                    expandableTextView2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                }
            }
        });




        expandableTextView3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                expandableTextView3.toggle();
            }
        });
        expandableTextView3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView3.isExpanded())
                {expandableTextView3.collapse();
                    expandableTextView3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                }

                else
                {expandableTextView3.expand();
                    expandableTextView3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                }
            }
        });


        expandableTextView4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                expandableTextView4.toggle();
            }
        });
        expandableTextView4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(final View v)
            {
                if (expandableTextView4.isExpanded())
                {expandableTextView4.collapse();
                    expandableTextView4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_up_black_24dp, 0);
                }

                else
                {expandableTextView4.expand();
                    expandableTextView4.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_keyboard_arrow_down_black_24dp, 0);
                }
            }
        });




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menulog, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){

            case R.id.action_logout:startActivity(new Intent(this, Login.class)); return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_home:startActivity(new Intent(this, MainActivity.class)); return true;

            case R.id.action_events: return true;

            case R.id.action_teams:startActivity(new Intent(this, Teams.class)); return true;

            case R.id.action_sponsers:startActivity(new Intent(this, Sponsers.class)); return true;

            case R.id.action_faq:startActivity(new Intent(this, FAQ.class)); return true;

            case R.id.action_about:startActivity(new Intent(this, About.class)); return true;

            case R.id.action_logout:startActivity(new Intent(this, Login.class)); return true;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
