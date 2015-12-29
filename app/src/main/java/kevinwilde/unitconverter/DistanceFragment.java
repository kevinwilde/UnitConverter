package kevinwilde.unitconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by Kevin Wilde on 11/12/2015.
 */
public class DistanceFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "DistanceFragment";

    private EditText mInput;
    private Spinner mToSpinner;
    private Spinner mFromSpinner;
    private TextView mAnswer;
    private ConversionsDataSource mDataSource;
    private ImageView mStarConversion;
    private String mConversionString;

    private double[][] mMultipliers = new double[][]{
            //         in         ft          yd          mi            cm          m         km         mm         um           nm  ly
            {           1,     1.0/12,     1.0/36,   1.0/63360,       2.54e0,   2.54e-2,   2.54e-5,    2.54e1,    2.54e4,     2.54e7, 0 }, //in
            {          12,          1,      1.0/3,        5280,      3.048e1,  3.048e-1,  3.048e-4,   3.048e2,   3.048e5,    3.048e8, 0 }, //ft
            {          36,          3,          1,        1760,      9.144e1,  9.144e-1,  9.144e-4,   9.144e2,   9.144e5,    9.144e8, 0 }, //yd
            {       63360,       5280,       1760,           1,    1.60934e5, 1.60934e3, 1.60934e0, 1.60934e6, 1.60934e9, 1.60934e12, 0 }, //mi
            { 39370079e-1, 3.28084e-2, 1.09361e-2,  6.21371e-6,          1e0,      1e-2,      1e-5,       1e1,       1e4,        1e7, 0 }, //cm
            { 3.9370079e1,  3.28084e0,  1.09361e0,  6.21371e-4,          1e2,       1e0,      1e-3,       1e3,       1e6,        1e9, 0 }, //m
            { 3.9370079e4,  3.28083e3,  1.09361e3,  6.21371e-1,          1e5,       1e3,       1e0,       1e6,       1e9,       1e12, 0 }, //km
            { 39370079e-2, 3.28084e-3, 1.09361e-3,  6.21371e-7,         1e-1,      1e-3,      1e-6,       1e0,       1e3,        1e6, 0 }, //mm
            { 39370079e-5, 3.28084e-6, 1.09361e-6, 6.21371e-10,         1e-4,      1e-6,      1e-9,      1e-3,       1e0,        1e3, 0 }, //um
            { 39370079e-8, 3.28084e-9, 1.09361e-9, 6.21371e-13,         1e-7,      1e-9,     1e-12,      1e-6,      1e-3,        1e0, 0 }, //nm
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }  //ly
    };


    public DistanceFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataSource = new ConversionsDataSource(this.getActivity());
        View v = inflater.inflate(R.layout.multipliers_layout, null);
        mStarConversion = (ImageView) v.findViewById(R.id.img_star_conversion);
        mStarConversion.setColorFilter(getResources().getColor(R.color.colorAccent));
        mInput = (EditText) v.findViewById(R.id.inputValue);
        mToSpinner = (Spinner) v.findViewById(R.id.toSpinner);
        mFromSpinner = (Spinner) v.findViewById(R.id.fromSpinner);
        mAnswer = (TextView) v.findViewById(R.id.answer);
        Button btnConvert = (Button) v.findViewById(R.id.btnConvert);
        Button btnClear = (Button) v.findViewById(R.id.btnClear);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.distance_units_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mToSpinner.setAdapter(adapter);
        mFromSpinner.setAdapter(adapter);

        btnConvert.setOnClickListener(this);
        btnClear.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnConvert:
                if (mInput.getText().length() == 0) {
                    Toast.makeText(getActivity(), R.string.invalid_input, Toast.LENGTH_LONG).show();
                }
                else {
                    mStarConversion.setImageResource(R.mipmap.ic_star_border);
                    mStarConversion.setOnClickListener(this);
                    double inpVal = Double.parseDouble(mInput.getText().toString());
                    int fromUnit = mFromSpinner.getSelectedItemPosition();
                    int toUnit = mToSpinner.getSelectedItemPosition();
                    double ans = inpVal * mMultipliers[fromUnit][toUnit];
                    String[] units = getResources().getStringArray(R.array.distance_units_array);
                    mAnswer.setText(Double.toString(ans) + " " + units[toUnit]);
                    mConversionString = mInput.getText().toString() + " " + units[fromUnit] + " = " + mAnswer.getText().toString();
                    mDataSource.InsertRecentConversion(mConversionString);
                }
                break;
            case R.id.btnClear:
                mInput.setText("");
                mAnswer.setText("");
                break;
            case R.id.img_star_conversion:
                mDataSource.InsertSavedConversion(mConversionString);
                mStarConversion.setImageResource(R.mipmap.ic_star);
                Toast.makeText(getActivity(), R.string.conversion_saved, Toast.LENGTH_LONG).show();
                break;
            default:
        }
    }

}

