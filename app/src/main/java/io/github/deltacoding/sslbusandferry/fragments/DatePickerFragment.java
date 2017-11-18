package io.github.deltacoding.sslbusandferry.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

import io.github.deltacoding.sslbusandferry.R;
import io.github.deltacoding.sslbusandferry.util.DateUtil;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private EditText date;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        this.date = (EditText) getActivity().findViewById(R.id.editTextDate);
        String text = date.getText().toString();

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        if(text.equals("")) {
            return new DatePickerDialog(getActivity(), this, year, month, day);
        } else {
            if(DateUtil.isValidDate(text)) {
                String[] values = text.split("\\.");
                return new DatePickerDialog(getActivity(), this,
                        Integer.parseInt(values[2]), Integer.parseInt(values[1]) - 1, Integer.parseInt(values[0]));
            } else if(text.equals("Today")) {
                return new DatePickerDialog(getActivity(), this, year, month, day);
            } else {
                date.setText("");
                return new DatePickerDialog(getActivity(), this, year, month, day);
            }
        }
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        if(DateUtil.isDateToday(year, month, day)) {
            date.setText("Today");
        } else {
            date.setText(DateUtil.convertToString(year, month, day));
        }
    }
}
