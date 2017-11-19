package io.github.deltacoding.sslbusandferry;

import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import io.github.deltacoding.sslbusandferry.fragments.DatePickerFragment;
import io.github.deltacoding.sslbusandferry.fragments.TimePickerFragment;
import io.github.deltacoding.sslbusandferry.util.DateUtil;

public class MainActivity extends AppCompatActivity {

    private EditText time;
    private EditText date;
    private Spinner from;
    private Spinner to;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = (EditText) findViewById(R.id.editTextTime);
        date = (EditText) findViewById(R.id.editTextDate);
        from = (Spinner) findViewById(R.id.spinnerFrom);
        to = (Spinner) findViewById(R.id.spinnerTo);

        loadData();
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFrag = new TimePickerFragment();
        newFrag.show(getFragmentManager(), "timePicker");
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFrag = new DatePickerFragment();
        newFrag.show(getFragmentManager(), "datePicker");
    }

    public void onSearchButtonClicked(View v) {
        save();
        Intent intent = new Intent(this, RequestedRouteActivity.class);
        startActivity(intent);
    }

    public void onSwapButtonClicked(View v) {
        int fromPos = from.getSelectedItemPosition();
        int toPos = to.getSelectedItemPosition();
        from.setSelection(toPos);
        to.setSelection(fromPos);
        save();
    }

    private void save() {
        SharedPreferences settings = getPreferences(0);
        SharedPreferences.Editor editor = settings.edit();

        editor.putString("time", time.getText().toString());
        editor.putString("date", date.getText().toString());
        editor.putInt("from", from.getSelectedItemPosition());
        editor.putInt("to", to.getSelectedItemPosition());

        editor.commit();
    }

    private String loadString(String dataName) {
        SharedPreferences settings = getPreferences(0);
        return settings.getString(dataName, "");
    }

    private int loadInt(String dataName) {
        SharedPreferences settings = getPreferences(0);
        return settings.getInt(dataName, 0);
    }

    private void loadData() {
        String timeData = loadString("time");
        String dateData = loadString("date");
        int fromData = loadInt("from");
        int toData = loadInt("to");

        if(!(timeData == "") && DateUtil.isValidTime(timeData)) {
            time.setText(timeData);
        } else {
            time.setText(DateUtil.getCurrentTime());
        }
        if(!(dateData == "") && DateUtil.isValidDate(dateData)) {
            date.setText(dateData);
        } else {
            date.setText("Today");
        }
        from.setSelection(fromData);
        to.setSelection(toData);
    }
}
