package kevinwilde.unitconverter;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ConversionsDataSource mDataSource;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mDataSource = new ConversionsDataSource(this.getActivity());
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ListView savedConversionsList = (ListView) view.findViewById(R.id.saved_conversions_list);
        ListView recentConversionsList = (ListView) view.findViewById(R.id.recent_conversions_list);

        String[] from = { DbHelper.COLUMN_CONVERSION_STRING };
        int[] to = { R.id.conversion_string };
        Cursor recentCursor = mDataSource.GetRecentConversions();
        SimpleCursorAdapter recentCursorAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.conversion_listview_row, recentCursor, from, to);
        recentConversionsList.setAdapter(recentCursorAdapter);
        Cursor savedCursor = mDataSource.GetSavedConversions();
        SimpleCursorAdapter savedCursorAdapter = new SimpleCursorAdapter(this.getActivity(), R.layout.conversion_listview_row, savedCursor, from, to);
        savedConversionsList.setAdapter(savedCursorAdapter);

        return view;
    }


}
