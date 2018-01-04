package com.moment;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class BlooperFragment extends Fragment {
    public BlooperFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_blooper, container, false);
        String imagePath = "";
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            imagePath = bundle.getString("imagePath");
        }

        ImageView imageView = view.findViewById(R.id.img_bloop);
        imageView.setImageDrawable(Drawable.createFromPath(imagePath));

        return view;
    }
}
