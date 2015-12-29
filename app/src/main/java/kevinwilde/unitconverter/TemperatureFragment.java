package kevinwilde.unitconverter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Kevin Wilde on 11/12/2015.
 */
public class TemperatureFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = "TemperatureFragment";

    private EditText mInput;
    private TextView mAnswer;
    private RadioButton mRbCtoF;
    private RadioButton mRbFtoC;
    private ConversionsDataSource mDataSource;
    private Boolean mConversionSaved;
    private ImageView mStarConversion;
    private String mConversionString;
    private long mConversionId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataSource = new ConversionsDataSource(this.getActivity());
        View v = inflater.inflate(R.layout.fragment_temperature,null);
        mStarConversion = (ImageView) v.findViewById(R.id.img_star_conversion);
        mStarConversion.setColorFilter(getResources().getColor(R.color.colorAccent));
        mInput = (EditText) v.findViewById(R.id.inputValue);
        mAnswer = (TextView) v.findViewById(R.id.answer);
        Button btnConvert = (Button) v.findViewById(R.id.btnConvert);
        Button btnClear = (Button) v.findViewById(R.id.btnClear);
        mRbCtoF = (RadioButton) v.findViewById(R.id.radioCtoF);
        mRbFtoC = (RadioButton) v.findViewById(R.id.radioFtoC);
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
                    double temp = Double.parseDouble(mInput.getText().toString());
                    if (mRbCtoF.isChecked()) {
                        mAnswer.setText(String.valueOf(convertCtoF(temp)) + " Fahrenheit");
                        mConversionString = mInput.getText().toString() + " Celsius = " + mAnswer.getText().toString();
                        mDataSource.InsertRecentConversion(mConversionString);
                    }
                    else if (mRbFtoC.isChecked()) {
                        mAnswer.setText(String.valueOf(convertFtoC(temp)) + " Celsius");
                        mConversionString = mInput.getText().toString() + " Fahrenheit = " + mAnswer.getText().toString();
                        mDataSource.InsertRecentConversion(mConversionString);
                    }
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

    private double convertCtoF(double c) {
        return ((c * 9 / 5) + 32);
    }

    private double convertFtoC(double f) {
        return ((f - 32) * 5 / 9);
    }
}
