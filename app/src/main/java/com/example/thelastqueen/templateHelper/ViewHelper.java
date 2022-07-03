package com.example.thelastqueen.templateHelper;

import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ViewHelper {

    public static ImageButton makeImageButton(Context context, int dpWidth, int y){

        ImageButton imageButton = new ImageButton(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams((int)Math.ceil(dpWidth/y),
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        1.0f);
        imageButton.setLayoutParams(lp);
        imageButton.setAdjustViewBounds(true);
        imageButton.setScaleType(ImageView.ScaleType.FIT_CENTER);


        return imageButton;
    }

    public static LinearLayout makeLinearLayout(Context context, int dpHeight, int x){
        LinearLayout linearLayout = new LinearLayout(context);
        LinearLayout.LayoutParams lp =
                new LinearLayout.LayoutParams
                        (LinearLayout.LayoutParams.MATCH_PARENT,
                                (int)Math.ceil(dpHeight/x),
                                1.0f);
        linearLayout.setLayoutParams(lp);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        return  linearLayout;
    }
}
