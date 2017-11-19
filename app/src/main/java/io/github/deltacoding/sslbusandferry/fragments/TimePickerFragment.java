package io.github.deltacoding.sslbusandferry.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import io.github.deltacoding.sslbusandferry.R;
import io.github.deltacoding.sslbusandferry.util.DateUtil;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private EditText time;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.time = (EditText) getActivity().findViewById(R.id.editTextTime);
        String text = time.getText().toString();
        int hour, minute;

        if(text.equals("")) {
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        } else if(DateUtil.isValidTime(text)) {
            String[] values = text.split("\\:");
            hour = Integer.parseInt(values[0]);
            minute = Integer.parseInt(values[1]);
        } else {
            time.setText("");
            final Calendar c = Calendar.getInstance();
            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);
        }

        return new TimePickerDialog(getActivity(), this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        EditText time = (EditText) getActivity().findViewById(R.id.editTextTime);
        time.setText(DateUtil.convertToString(hourOfDay, minute));
    }
}
