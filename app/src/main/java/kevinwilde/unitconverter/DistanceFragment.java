package kevinwilde.unitconverter;

/**
 * Created by Kevin Wilde on 11/12/2015.
 */
public class DistanceFragment extends MultiplierFragment {

    private static final String TAG = "DistanceFragment";

    public DistanceFragment() {
        // Required empty public constructor
    }

    @Override
    public double[][] getMultipliersArray() {
        return new double[][]{
                //         in         ft          yd          mi            cm          m         km         mm         um           nm   ly
                {            1,     1.0/12,     1.0/36,   1.0/63360,       2.54e0,   2.54e-2,   2.54e-5,    2.54e1,    2.54e4,     2.54e7, 0 }, //in
                {           12,          1,      1.0/3,        5280,      3.048e1,  3.048e-1,  3.048e-4,   3.048e2,   3.048e5,    3.048e8, 0 }, //ft
                {           36,          3,          1,        1760,      9.144e1,  9.144e-1,  9.144e-4,   9.144e2,   9.144e5,    9.144e8, 0 }, //yd
                {        63360,       5280,       1760,           1,    1.60934e5, 1.60934e3, 1.60934e0, 1.60934e6, 1.60934e9, 1.60934e12, 0 }, //mi
                { 3.9370079e-1, 3.28084e-2, 1.09361e-2,  6.21371e-6,          1e0,      1e-2,      1e-5,       1e1,       1e4,        1e7, 0 }, //cm
                {  3.9370079e1,  3.28084e0,  1.09361e0,  6.21371e-4,          1e2,       1e0,      1e-3,       1e3,       1e6,        1e9, 0 }, //m
                {  3.9370079e4,  3.28083e3,  1.09361e3,  6.21371e-1,          1e5,       1e3,       1e0,       1e6,       1e9,       1e12, 0 }, //km
                { 3.9370079e-2, 3.28084e-3, 1.09361e-3,  6.21371e-7,         1e-1,      1e-3,      1e-6,       1e0,       1e3,        1e6, 0 }, //mm
                { 3.9370079e-5, 3.28084e-6, 1.09361e-6, 6.21371e-10,         1e-4,      1e-6,      1e-9,      1e-3,       1e0,        1e3, 0 }, //um
                { 3.9370079e-8, 3.28084e-9, 1.09361e-9, 6.21371e-13,         1e-7,      1e-9,     1e-12,      1e-6,      1e-3,        1e0, 0 }, //nm
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }  //ly
        };
    }

    @Override
    public String[] getUnitsArray() {
        return getResources().getStringArray(R.array.distance_units_array);
    }
}