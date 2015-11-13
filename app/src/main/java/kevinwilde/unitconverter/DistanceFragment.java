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
public class DistanceFragment extends Fragment implements View.OnClickListener {

    private EditText input;
    private Button btnConvert;
    private Button btnClear;
    private RadioButton rbKtoM;
    private RadioButton rbMtoK;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.distance_layout,null);
        input = (EditText) v.findViewById(R.id.inputValue);
        btnConvert = (Button) v.findViewById(R.id.btnConvert);
        btnClear = (Button) v.findViewById(R.id.btnClear);
        rbKtoM = (RadioButton) v.findViewById(R.id.radioKtoM);
        rbMtoK = (RadioButton) v.findViewById(R.id.radioMtoK);
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
                    if (rbKtoM.isChecked()) {
                        input.setText(String.valueOf(convertKtoM(temp)));
                        rbKtoM.setChecked(false);
                        rbMtoK.setChecked(true);
                    }
                    else if (rbMtoK.isChecked()) {
                        input.setText(String.valueOf(convertMtoK(temp)));
                        rbKtoM.setChecked(true);
                        rbMtoK.setChecked(false);
                    }
                }
                break;
            case R.id.btnClear:
                input.setText("");
                break;
        }
    }

    private float convertKtoM(float k) {
        return (float) (k * 0.621371);
    }

    private float convertMtoK(float m) {
        return (float) (m * 1.60934);
    }
}
