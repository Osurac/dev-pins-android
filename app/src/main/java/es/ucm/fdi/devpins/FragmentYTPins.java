package es.ucm.fdi.devpins;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jetbrains.annotations.NotNull;

import es.ucm.fdi.devpins.data.adapter.PinAdapter;
import es.ucm.fdi.devpins.data.model.Pin;

public class FragmentYTPins extends Fragment {

    private ListView pinListView;
    private PinAdapter pinAdapter;
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ytpins,container,false);
        pinListView = (ListView) view.findViewById(R.id.ytPinsListView);
        pinAdapter = new PinAdapter(getActivity(), Pin.pinArrayListYT);
        pinListView.setAdapter(pinAdapter);
        loadFromDBToMemory();
        setOnClickListener();
        return view;
    }

    private void setOnClickListener() {
        pinListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){
                Pin selectedPin = (Pin) pinListView.getItemAtPosition(i);
                Intent updatePin = new Intent(getActivity().getApplicationContext(), UpdatePinActivity.class);
                updatePin.putExtra(Pin.PIN_EDIT_EXTRA, selectedPin.getId());
                startActivity(updatePin);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(getActivity());
        sqLiteManager.populatePinListTypeArray("yt");
        pinAdapter.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        pinAdapter.notifyDataSetChanged();
    }
}
