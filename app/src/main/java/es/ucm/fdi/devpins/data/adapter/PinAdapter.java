package es.ucm.fdi.devpins.data.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import es.ucm.fdi.devpins.R;
import es.ucm.fdi.devpins.data.model.Pin;

public class PinAdapter extends ArrayAdapter<Pin> {

    public PinAdapter(Context context, List<Pin> pins){
        super(context, 0, pins);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pin pin = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.basic_pin, parent, false);
        }
        TextView url = convertView.findViewById(R.id.pin_url);
        TextView fav = convertView.findViewById(R.id.pin_fav);
        url.setText(pin.getUrl());
        Boolean isFav = pin.getFav();
        if(String.valueOf(isFav) == "true"){
            fav.setText("Favorito");
        }else{
            fav.setText("");
        }
        return convertView;
    }
}
