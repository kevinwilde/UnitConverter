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
 * A simple {@link Fragment} subclass.
 */
public abstract class MultiplierFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "MultiplierFragment";

    private EditText mInput;
    private Spinner mToSpinner;
    private Spinner mFromSpinner;
    private TextView mAnswer;
    private ConversionsDataSource mDataSource;
    private ImageView mStarConversion;
    private Boolean mConversionSaved;
    private String mConversionString;
    private long mConversionId;
    private double[][] mMultipliers;
    private String[] mUnits;

    public MultiplierFragment() {
        // Required empty public constructor
    }


    public abstract double[][] getMultipliersArray();
    public abstract String[] getUnitsArray();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataSource = new ConversionsDataSource(this.getActivity());
        mMultipliers = getMultipliersArray();
        mUnits = getUnitsArray();
        View v = inflater.inflate(R.layout.fragment_multiplier, null);
        mStarConversion = (ImageView) v.findViewById(R.id.img_star_conversion);
        mStarConversion.setColorFilter(getResources().getColor(R.color.colorAccent));
        mInput = (EditText) v.findViewById(R.id.inputValue);
        mToSpinner = (Spinner) v.findViewById(R.id.toSpinner);
        mFromSpinner = (Spinner) v.findViewById(R.id.fromSpinner);
        mAnswer = (TextView) v.findViewById(R.id.answer);
        Button btnConvert = (Button) v.findViewById(R.id.btnConvert);
        Button btnClear = (Button) v.findViewById(R.id.btnClear);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, mUnits);
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
                    mConversionSaved = false;
                    mStarConversion.setImageResource(R.mipmap.ic_star_border);
                    mStarConversion.setOnClickListener(this);
                    double inpVal = Double.parseDouble(mInput.getText().toString());
                    int fromUnit = mFromSpinner.getSelectedItemPosition();
                    int toUnit = mToSpinner.getSelectedItemPosition();
                    double ans = inpVal * mMultipliers[fromUnit][toUnit];
                    mAnswer.setText(Double.toString(ans) + " " + mUnits[toUnit]);
                    mConversionString = mInput.getText().toString() + " " + mUnits[fromUnit] + " = " + mAnswer.getText().toString();
                    mDataSource.InsertRecentConversion(mConversionString);
                }
                break;
            case R.id.btnClear:
                mInput.setText("");
                mAnswer.setText("");
                mConversionSaved = false;
                mStarConversion.setImageDrawable(null);
                break;
            case R.id.img_star_conversion:
                if (mConversionSaved) {
                    mConversionSaved = false;
                    mDataSource.DeleteSavedConversion(mConversionId);
                    mStarConversion.setImageResource(R.mipmap.ic_star_border);
                    Toast.makeText(getActivity(), R.string.conversion_unsaved, Toast.LENGTH_SHORT).show();
                }
                else {
                    mConversionSaved = true;
                    mConversionId = mDataSource.InsertSavedConversion(mConversionString);
                    mStarConversion.setImageResource(R.mipmap.ic_star);
                    Toast.makeText(getActivity(), R.string.conversion_saved, Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

}
