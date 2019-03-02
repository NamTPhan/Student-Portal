package com.npdevelopment.studentportal;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

public class AddPortal extends AppCompatActivity {

    private TextInputEditText mUrlPortalInput, mTitlePortalInput;
    private Button mAddPortalBtn;
    private Snackbar mSnackBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_portal);

        mUrlPortalInput = findViewById(R.id.urlEditInput);
        mTitlePortalInput = findViewById(R.id.titleEditInput);
        mAddPortalBtn = findViewById(R.id.addPortalBtn);

        mAddPortalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(mUrlPortalInput.getText()) || TextUtils.isEmpty(mTitlePortalInput.getText())) {
                    mSnackBar = Snackbar.make(v, getString(R.string.fields_required), Snackbar.LENGTH_SHORT);

                    // Change snackbar view with custom colors
                    View sbView = mSnackBar.getView();
                    sbView.setBackgroundColor(getResources().getColor(R.color.red));
                    mSnackBar.show();
                } else {
                    PortalObject portalObject = new PortalObject(mUrlPortalInput.getText().toString(), mTitlePortalInput.getText().toString());
                    Intent data = new Intent();
                    data.putExtra(MainActivity.NEW_PORTAL_KEY, portalObject);

                    //Send the result back to the activity
                    setResult(Activity.RESULT_OK, data);

                    //Go back to the previous activity
                    finish();
                }
            }
        });
    }
}
