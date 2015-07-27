package com.example.avikhasija.mycontacts;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactListActivity extends ActionBarActivity
        implements ContactListFragment.Contract, ContactViewFragment.Contract{

    private ContactListFragment mContactListFragment;
    private ContactViewFragment mContactViewFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        mContactListFragment = (ContactListFragment) getFragmentManager().findFragmentById(R.id.List_fragment_container);

        if(mContactListFragment == null) {
            mContactListFragment = new ContactListFragment();
            getFragmentManager().beginTransaction()
                    .add(R.id.List_fragment_container, mContactListFragment)
                    .commit();
        }

        mContactViewFragment = (ContactViewFragment) getFragmentManager().findFragmentById(R.id.view_fragment_container);

        if(mContactViewFragment == null && (findViewById(R.id.view_fragment_container)) != null) {
            mContactViewFragment = new ContactViewFragment();
            mContactViewFragment.setPosition(0);
            getFragmentManager().beginTransaction()
                    .add(R.id.view_fragment_container, mContactViewFragment)
                    .commit();
        }
    }

    @Override
    public void selectedPosition(int position) {
        if (mContactViewFragment == null) {
            Intent i = new Intent(this, ContactViewActivity.class);
            i.putExtra(ContactViewActivity.EXTRA, position);
            startActivity(i);
        }
        else{
            mContactViewFragment.setPosition(position);
        }

    }

    @Override
    public void selectEditPosition(int position) {
        Intent i = new Intent(this, ContactEditActivity.class);
        i.putExtra(ContactEditActivity.EXTRA, position);
        startActivity(i);

    }
}
