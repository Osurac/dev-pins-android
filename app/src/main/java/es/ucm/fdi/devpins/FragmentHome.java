package es.ucm.fdi.devpins;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import es.ucm.fdi.devpins.data.adapter.PinAdapter;
import es.ucm.fdi.devpins.data.model.Pin;

public class FragmentHome extends Fragment {

    private ListView pinListView;
    private  PinAdapter pinAdapter;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,container,false);
       // String[] menuItems = {"1", "2", "3"};
        pinListView = (ListView) view.findViewById(R.id.pinsListView);
        pinAdapter = new PinAdapter(getActivity(), Pin.pinArrayList);
        pinListView.setAdapter(pinAdapter);
        loadFromDBToMemory();
        return view;
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getActivity());
        sqLiteManager.populatePinListArray();
        pinAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        pinAdapter.notifyDataSetChanged();
    }
}
