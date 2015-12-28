package kevinwilde.unitconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin Wilde on 11/12/2015.
 */
public class TemperatureFragment extends Fragment implements View.OnClickListener {

    private ConversionsDataSource mDataSource;
    private EditText input;
    private TextView answer;
    private Button btnConvert;
    private Button btnClear;
    private RadioButton rbCtoF;
    private RadioButton rbFtoC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataSource = new ConversionsDataSource(this.getActivity());
        View v = inflater.inflate(R.layout.fragment_temperature,null);
        input = (EditText) v.findViewById(R.id.inputValue);
        answer = (TextView) v.findViewById(R.id.answer);
        btnConvert = (Button) v.findViewById(R.id.btnConvert);
        btnClear = (Button) v.findViewById(R.id.btnClear);
        rbCtoF = (RadioButton) v.findViewById(R.id.radioCtoF);
        rbFtoC = (RadioButton) v.findViewById(R.id.radioFtoC);
        btnConvert.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnConvert:
                if (input.getText().length() == 0) {
                    Toast.makeText(getActivity(), R.string.invalid_input, Toast.LENGTH_LONG).show();
                }
                else {
                    double temp = Double.parseDouble(input.getText().toString());
                    if (rbCtoF.isChecked()) {
                        answer.setText(String.valueOf(convertCtoF(temp)) + " Fahrenheit");
                        String conversionString = input.getText().toString() + " Celsius = " + answer.getText().toString();
                        mDataSource.InsertRecentConversion(conversionString);
                        mDataSource.InsertSavedConversion(conversionString);
                    }
                    else if (rbFtoC.isChecked()) {
                        answer.setText(String.valueOf(convertFtoC(temp)) + " Celsius");
                        String conversionString = input.getText().toString() + " Fahrenheit = " + answer.getText().toString();
                        mDataSource.InsertRecentConversion(conversionString);
                        mDataSource.InsertSavedConversion(conversionString);
                    }
                }
                break;
            case R.id.btnClear:
                input.setText("");
                answer.setText("");
                break;
        }
    }

    private double convertCtoF(double c) {
        return ((c * 9 / 5) + 32);
    }

    private double convertFtoC(double f) {
        return ((f - 32) * 5 / 9);
    }
}
