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
import android.widget.Toast;

/**
 * Created by Kevin Wilde on 11/12/2015.
 */
public class TemperatureFragment extends Fragment implements View.OnClickListener {

    private EditText input;
    private Button btnConvert;
    private Button btnClear;
    private RadioButton rbCtoF;
    private RadioButton rbFtoC;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.temp_layout,null);
        input = (EditText) v.findViewById(R.id.inputValue);
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
                    float temp = Float.parseFloat(input.getText().toString());
                    if (rbCtoF.isChecked()) {
                        input.setText(String.valueOf(convertCtoF(temp)));
                        rbCtoF.setChecked(false);
                        rbFtoC.setChecked(true);
                    }
                    else if (rbFtoC.isChecked()) {
                        input.setText(String.valueOf(convertFtoC(temp)));
                        rbCtoF.setChecked(true);
                        rbFtoC.setChecked(false);
                    }
                }
                break;
            case R.id.btnClear:
                input.setText("");
                break;
        }
    }

    private float convertCtoF(float c) {
        return ((c * 9 / 5) + 32);
    }

    private float convertFtoC(float f) {
        return ((f - 32) * 5 / 9);
    }
}
