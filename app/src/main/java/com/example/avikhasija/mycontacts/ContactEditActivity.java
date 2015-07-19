package com.example.avikhasija.mycontacts;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ContactEditActivity extends ActionBarActivity {

    public static final String EXTRA = "CEA_EXTRA";

    private Contact mContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        int position = getIntent().getIntExtra(EXTRA, 0);
        mContact = ContactList.getInstance().get(position);

        Toolbar toolbar = (Toolbar)findViewById(R.id.contact_edit_toolbar);
        toolbar.setTitle(getResources().getString(R.string.edit_contact));
        //NavigationIcon - on left
        toolbar.setNavigationIcon(R.drawable.ic_done);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editName = (EditText)findViewById(R.id.contact_edit_name);
                mContact.setName(editName.getText().toString());

                //return arraylist with new values inserted; becomes arraylist of contact object
                mContact.phoneNumbers = getSectionValues(R.id.phonenumber_section);
                mContact.emails = getSectionValues(R.id.email_section);

                Toast.makeText(ContactEditActivity.this, "Saved contact", Toast.LENGTH_LONG)
                        .show();

                finish();
            }
        });

        EditText editName = (EditText) findViewById(R.id.contact_edit_name);
        //Puts contact name as text (not hint) in edittext
        editName.setText(mContact.getName());

        addToSection(R.id.phonenumber_section, mContact.phoneNumbers);
        addToSection(R.id.email_section, mContact.emails);

        TextView addNewPhoneNumber = (TextView) findViewById(R.id.add_new_phonenumber);
        addNewPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.phonenumber_section, "");
            }
        });

        TextView addNewEmail = (TextView) findViewById(R.id.add_new_email);
        addNewEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToSection(R.id.email_section, "");
            }
        });
    }

    //GETS NEW VALUES IN EDIT SCREEN
    //gets all elements from section linear layouts, including existing and new
    //puts these into a new ArrayList, which replaces email and phonenumber arraylists for object contact
    private ArrayList<String> getSectionValues(int sectionID){
        ArrayList<String> values = new ArrayList<String>();
        LinearLayout section = (LinearLayout) findViewById(sectionID);
        for (int i = 0; i < section.getChildCount(); i++) {
            EditText editNumber = (EditText) section.getChildAt(i);
            values.add(editNumber.getText().toString());
        }
        return values;
    }

    //Method overloading, can have more than one method with same name, but different perimeters
    //Will let user add new email and phone numbers for contact
    private void addToSection(int sectionID, String value) {
        LinearLayout section = (LinearLayout) findViewById(sectionID);
        EditText et = new EditText(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        et.setLayoutParams(lp);
        et.setText(value);
        section.addView(et);
    }

    //Method to add existing emails and phone numbers as UI objects
    private void addToSection(int sectionID, ArrayList<String> values) {

        LinearLayout section = (LinearLayout) findViewById(sectionID);
        for (int i = 0; i < values.size(); i++) {
            //Create UI objects from code
            EditText et = new EditText(this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            et.setLayoutParams(lp);
            et.setText(values.get(i));
            section.addView(et);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_edit, menu);
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
}
