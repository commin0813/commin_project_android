package commin.pro.diseasemanagement.page.manage.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import commin.pro.diseasemanagement.R;

/**
 * Created by user on 2017-10-18.
 */

public class Fregment2Gastrits_1 extends Fragment
{
    public Fregment2Gastrits_1()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.item_fragment_gastrits_1, container, false);
        return layout;
    }
}
