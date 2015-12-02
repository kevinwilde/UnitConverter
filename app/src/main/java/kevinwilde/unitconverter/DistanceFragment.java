package kevinwilde.unitconverter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


/**
 * Created by Kevin Wilde on 11/12/2015.
 */
public class DistanceFragment extends Fragment implements View.OnClickListener {

    private EditText input;
    private Spinner toSpinner;
    private Spinner fromSpinner;
    private TextView answer;
    private Button btnConvert;
    private Button btnClear;
    private HashMap<String, HashMap<String, Integer>> distanceMultipliers = new HashMap<String, HashMap<String, Integer>>();

    private void populateDistanceMultipliers() {
        String[] distanceUnits = getResources().getStringArray(R.array.distance_units_array);
        HashMap<String, Integer> temp = new HashMap<String, Integer>();

        for (String item1 : distanceUnits) {
            for (String item2 : distanceUnits) {
                temp.put(item2, 2);
                distanceMultipliers.put(item1, temp);
            }
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        populateDistanceMultipliers();
        View v = inflater.inflate(R.layout.distance_layout,null);
        input = (EditText) v.findViewById(R.id.inputValue);
        toSpinner = (Spinner) v.findViewById(R.id.toSpinner);
        fromSpinner = (Spinner) v.findViewById(R.id.fromSpinner);
        answer = (TextView) v.findViewById(R.id.answer);
        btnConvert = (Button) v.findViewById(R.id.btnConvert);
        btnClear = (Button) v.findViewById(R.id.btnClear);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.distance_units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        toSpinner.setAdapter(adapter);
        fromSpinner.setAdapter(adapter);

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
                    float inpVal = Float.parseFloat(input.getText().toString());
                    String fromUnit = fromSpinner.getSelectedItem().toString();
                    String toUnit = toSpinner.getSelectedItem().toString();
                    float ans = inpVal * distanceMultipliers.get(fromUnit).get(toUnit);
                    answer.setText(Float.toString(ans) + " " + toUnit);
                }
                break;
            case R.id.btnClear:
                input.setText("");
                answer.setText("");
                break;
        }
    }




}

