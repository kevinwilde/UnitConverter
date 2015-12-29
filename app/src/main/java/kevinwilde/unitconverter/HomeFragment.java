package kevinwilde.unitconverter;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;


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
        recentConversionsList.setAdapter(new SimpleCursorAdapter(this.getActivity(), R.layout.conversion_listview_row, recentCursor, from, to) {
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setTag(getCursor().getLong(getCursor().getColumnIndex(DbHelper.COLUMN_ID)));
                return view;
            }
        });
        Cursor savedCursor = mDataSource.GetSavedConversions();
        savedConversionsList.setAdapter(new SimpleCursorAdapter(this.getActivity(), R.layout.conversion_listview_row, savedCursor, from, to) {
            @Override
            public View getView (int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                view.setTag(getCursor().getLong(getCursor().getColumnIndex(DbHelper.COLUMN_ID)));
                return view;
            }
        });
        recentConversionsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long db_id = (long) view.getTag();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.delete_conversion);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDataSource.DeleteRecentConversion(db_id);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });
        savedConversionsList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long db_id = (long) view.getTag();
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.delete_conversion);
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDataSource.DeleteSavedConversion(db_id);
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
                return true;
            }
        });

        return view;
    }
}
