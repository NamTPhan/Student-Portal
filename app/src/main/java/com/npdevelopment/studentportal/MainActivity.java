package com.npdevelopment.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerView.OnItemTouchListener {

    private List<PortalObject> mPortalObjectList;
    private GestureDetector mGestureDetector;
    private PortalObject mPortalObject;
    private PortalObjectAdapter mAdapter;
    public static final String NEW_PORTAL_KEY = "portalKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPortalObjectList = new ArrayList<>();

        // Default portal
        mPortalObjectList.add(new PortalObject(getString(R.string.default_website), getString(R.string.default_website_title)));

        // Floating button redirects to web view activity
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddPortal.class);
                startActivityForResult(intent, PortalObjectAdapter.REQUEST_CODE_OK);
            }
        });

        final RecyclerView mPortalRecyclerView = findViewById(R.id.portalList);

        RecyclerView.LayoutManager mLayoutManager = new StaggeredGridLayoutManager(1, LinearLayoutManager.VERTICAL);

        mPortalRecyclerView.setLayoutManager(mLayoutManager);
        mPortalRecyclerView.setHasFixedSize(true);
        mPortalRecyclerView.addOnItemTouchListener(this);

        mAdapter = new PortalObjectAdapter(this, mPortalObjectList);
        mPortalRecyclerView.setAdapter(mAdapter);

        mGestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    // Used for dynamically refreshing the list
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check if the result code is the right one
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PortalObjectAdapter.REQUEST_CODE_OK) {
                mPortalObject = data.getParcelableExtra(NEW_PORTAL_KEY);
                mPortalObjectList.add(mPortalObject);
                mAdapter.notifyItemInserted(mPortalObjectList.size() - 1);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
        View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        int mAdapterPosition = recyclerView.getChildAdapterPosition(child);

        if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
            Snackbar.make(recyclerView, mPortalObjectList.get(mAdapterPosition).getmUrl(), Snackbar.LENGTH_SHORT).show();
        }
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {}

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean b) {}
}
