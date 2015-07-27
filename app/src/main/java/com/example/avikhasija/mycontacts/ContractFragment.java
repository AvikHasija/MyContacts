package com.example.avikhasija.mycontacts;

import android.app.Activity;
import android.app.Fragment;

/**
 * Created by Avik Hasija on 7/27/2015.
 */
public class ContractFragment<T> extends Fragment {

    private T mContract;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try{
            mContract = (T)getActivity();
        }catch (ClassCastException e){
            throw new IllegalStateException("Activit does not implement contract");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        //fragment no longer has activity, therefore no contract object
        mContract = null;
    }

    public T getContract(){
        return mContract;
    }

}
