package com.gusi.study.today;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import com.gusi.study.R;
import java.util.Random;

/**
 * 作者：${ylw} on 2017-10-30 18:27
 */
public class PageFragment extends Fragment {

  private ClipTextView mClipTv;
  private SeekBar mSeekBar;

  public static PageFragment newInstance(String content) {
    Bundle args = new Bundle();
    PageFragment fragment = new PageFragment();
    args.putString("Content", content);
    fragment.setArguments(args);
    return fragment;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_pager, container, false);
    mClipTv = (ClipTextView) view.findViewById(R.id.tv_content);
    mSeekBar = (SeekBar) view.findViewById(R.id.seek_bar);
    return view;
  }

  private String getRandColor() {
    String r, g, b;
    Random random = new Random();
    r = Integer.toHexString(random.nextInt(256)).toUpperCase();
    g = Integer.toHexString(random.nextInt(256)).toUpperCase();
    b = Integer.toHexString(random.nextInt(256)).toUpperCase();
    r = r.length() == 1 ? "0" + r : r;
    g = g.length() == 1 ? "0" + g : g;
    b = b.length() == 1 ? "0" + b : b;
    return "#" + r + g + b;
    //return r + g + b;
  }

  @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    String content = getArguments().getString("Content", "Null Content");
    mClipTv.setText(content);

    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float f = progress * 1.0f / 100;
        mClipTv.clipPercent(f, 1);
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }
}
