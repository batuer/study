package com.gusi.study.slide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.gusi.study.R;
import com.gusi.study.utils.ListViewHeightUtil;
import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {

  private static final String ARG_PARAM1 = "param1";

  private String mParam1;
  private ListView mLv;

  // TODO: Rename and change types and number of parameters
  public static BlankFragment newInstance(String param1) {
    BlankFragment fragment = new BlankFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_blank, container, false);
    mLv = (ListView) view.findViewById(R.id.lv);
    //RadioGroup rg = (RadioGroup) view.findViewById(R.id.rg);
    //RadioButton rb;
    //for (int i = 0; i < 20; i++) {
    //  rb = new RadioButton(getActivity());
    //  rb.setText(mParam1 + "--" + i);
    //  rb.setPadding(10, 10, 10, 10);
    //  rg.addView(rb);
    //}
    return view;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    List<String> list = new ArrayList<>(20);
    for (int i = 0; i < 20; i++) {
      list.add(mParam1 + "--" + i);
    }

    mLv.setAdapter(
        new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, list));
    ListViewHeightUtil.setListViewHeightBasedOnChildren(mLv);
  }
}
