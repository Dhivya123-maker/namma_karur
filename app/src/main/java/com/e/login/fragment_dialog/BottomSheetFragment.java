package com.e.login.fragment_dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;


import com.e.login.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheetFragment extends BottomSheetDialogFragment {

    private BottomSheetBehavior mBehavior;
    LinearLayout close;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);

        close = view.findViewById(R.id.lnr_close_fragment);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(dialog.isShowing()){
                    dialog.dismiss();
                }else{
                    dialog.show();
                }
//                getActivity().finish();
//                Intent intent = new Intent(getActivity(), Fragment_Home.class);
//                startActivity(intent);
            }
        });


        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;



    }

    @Override
    public void onStart() {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
    }


    public void doclick(View v) {
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}


//public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
//    LinearLayout close;
//    private BottomSheetBehavior mBehavior;


//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
//        View view= inflater.inflate(R.layout.fragment_bottom_sheet, container, false);
//
//
//        close = view.findViewById(R.id.lnr_close_fragment);
//        close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getActivity().finish();
//            }
//        });
//
//    }
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//
//        }
//    }
//
//}
