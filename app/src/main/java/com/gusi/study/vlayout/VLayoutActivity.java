package com.gusi.study.vlayout;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.ColumnLayoutHelper;
import com.alibaba.android.vlayout.layout.FixLayoutHelper;
import com.alibaba.android.vlayout.layout.FloatLayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.alibaba.android.vlayout.layout.OnePlusNLayoutHelper;
import com.alibaba.android.vlayout.layout.ScrollFixLayoutHelper;
import com.alibaba.android.vlayout.layout.SingleLayoutHelper;
import com.alibaba.android.vlayout.layout.StaggeredGridLayoutHelper;
import com.alibaba.android.vlayout.layout.StickyLayoutHelper;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.widget.OnRcvItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class VLayoutActivity extends BaseActivity {
  private static final String TAG = "FireVLayout";
  @BindView(R.id.rcv) RecyclerView recyclerView;
  private LayoutInflater mInflater;

  @Override protected int getLayout() {
    return R.layout.activity_vlayout;
  }

  @Override protected void initView() {
    super.initView();
    initToolBar(mToolbar, true, "VLayout");
    mInflater = LayoutInflater.from(this);
    //init Rcv

    // 创建VirtualLayoutManager对象 同时内部会创建一个LayoutHelperFinder对象，用来后续的LayoutHelper查找
    VirtualLayoutManager layoutManager = new VirtualLayoutManager(this);
    // 将VirtualLayoutManager绑定到recyclerView
    recyclerView.setLayoutManager(layoutManager);

    // 设置组件复用回收池
    RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    recyclerView.setRecycledViewPool(viewPool);
    viewPool.setMaxRecycledViews(0, 10);

    ArrayList<LayoutHelper> helperArrayList = new ArrayList<>();

    helperArrayList.add(getLinearLayoutHelper());
    helperArrayList.add(getGridLayoutHelper());
    helperArrayList.add(getFixLayoutHelper());
    helperArrayList.add(getLinearLayoutHelper());
    helperArrayList.add(getScrollFixLayoutHelper());
    helperArrayList.add(getFloatLayoutHelper());
    helperArrayList.add(getColumnLayoutHelper());
    helperArrayList.add(getSingleLayoutHelper());
    helperArrayList.add(getOnePlusNLayoutHelper(5));
    helperArrayList.add(getStickyLayoutHelper());
    helperArrayList.add(getStaggeredGridLayoutHelper());

    layoutManager.setLayoutHelpers(helperArrayList);
    recyclerView.setAdapter(getAdapter(layoutManager));

    OnRcvItemClickListener listener =
        new OnRcvItemClickListener(recyclerView, new OnRcvItemClickListener.RcvItemClickListener() {
          @Override public void onItemClick(RecyclerView.ViewHolder vh) {
            int adapterPosition = vh.getAdapterPosition();
            Log.w(TAG,
                vh.getLayoutPosition() + "onItemClick: " + adapterPosition + ":" + vh.getPosition()
                    + ":" + vh.getOldPosition());
          }
        });
    recyclerView.addOnItemTouchListener(listener);
  }

  private VirtualLayoutAdapter getAdapter(final VirtualLayoutManager layoutManager) {
    VirtualLayoutAdapter adapter = new VirtualLayoutAdapter<MainViewHolder>(layoutManager) {
      @Override public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        return new MainViewHolder(view);
      }

      @Override public void onBindViewHolder(MainViewHolder holder, int position) {
        TextView tv = (TextView) holder.itemView;
        tv.setText(String.valueOf(position));
        if (position == 27) {
          tv.setBackgroundColor(Color.GREEN);
        } else if (position == 17) {
          tv.setBackgroundColor(Color.BLUE);
        } else if (position == 16) {
          tv.setBackgroundColor(Color.RED);
          tv.setText("返回顶部");
          tv.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
              recyclerView.scrollToPosition(0);
            }
          });
        } else if (position == 10) {
          tv.setBackgroundColor(Color.parseColor("#0fffff"));
        } else if (position % 2 == 0) {
          tv.setBackgroundColor(Color.parseColor("#22000000"));
        } else {
          tv.setBackgroundColor(Color.WHITE);
        }
      }

      @Override public int getItemCount() {
        List<LayoutHelper> helpers = getLayoutHelpers();
        int count = 0;
        for (LayoutHelper helper : helpers) {
          count += helper.getItemCount();
        }
        return count;
      }
    };
    return adapter;
  }

  /**
   * 设置瀑布流布局
   */
  private StaggeredGridLayoutHelper getStaggeredGridLayoutHelper() {
    StaggeredGridLayoutHelper staggeredGridLayoutHelper = new StaggeredGridLayoutHelper();
    // 创建对象
    // 公有属性
    staggeredGridLayoutHelper.setItemCount(20);// 设置布局里Item个数
    staggeredGridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    staggeredGridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    staggeredGridLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    staggeredGridLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
    // 特有属性
    staggeredGridLayoutHelper.setLane(3);// 设置控制瀑布流每行的Item数
    staggeredGridLayoutHelper.setHGap(20);// 设置子元素之间的水平间距
    staggeredGridLayoutHelper.setVGap(15);// 设置子元素之间的垂直间距
    return staggeredGridLayoutHelper;
  }

  /**
   * 设置吸边布局
   */
  private StickyLayoutHelper getStickyLayoutHelper() {
    StickyLayoutHelper stickyLayoutHelper = new StickyLayoutHelper();
    // 公共属性
    stickyLayoutHelper.setItemCount(3);// 设置布局里Item个数
    stickyLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    stickyLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    stickyLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    stickyLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
    // 特有属性
    stickyLayoutHelper.setStickyStart(true);
    // true = 组件吸在顶部
    // false = 组件吸在底部
    stickyLayoutHelper.setOffset(100);// 设置吸边位置的偏移量

    //Adapter_StickyLayout = new MyAdapter(this, stickyLayoutHelper,1, listItem) {
    //  // 设置需要展示的数据总数,此处设置是1
    //  // 为了展示效果,通过重写onBindViewHolder()将布局的第一个数据设置为Stick
    //  @Override
    //  public void onBindViewHolder(MainViewHolder holder, int position) {
    //    super.onBindViewHolder(holder, position);
    //    if (position == 0) {
    //      holder.Text.setText("Stick");
    //    }
    //  }
    //};
    //
    //adapters.add(Adapter_StickyLayout) ;
    // 将当前的Adapter加入到Adapter列表里

    return stickyLayoutHelper;
  }

  /**
   * 设置1拖N布局
   */
  private OnePlusNLayoutHelper getOnePlusNLayoutHelper(int itemcount) {
    OnePlusNLayoutHelper onePlusNLayoutHelper = new OnePlusNLayoutHelper(itemcount);
    // 在构造函数里传入显示的Item数
    // 最多是1拖4,即5个
    // 公共属性
    //onePlusNLayoutHelper.setItemCount(3);// 设置布局里Item个数
    onePlusNLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    onePlusNLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    onePlusNLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    onePlusNLayoutHelper.setAspectRatio(3);// 设置设置布局内每行布局的宽与高的比
    //onePlusNLayoutHelper.setColWeights(new float[] { 20f, 80f, 0f, 60f, 20f });
    return onePlusNLayoutHelper;
  }

  /**
   * 设置通栏布局
   */
  private SingleLayoutHelper getSingleLayoutHelper() {
    SingleLayoutHelper singleLayoutHelper = new SingleLayoutHelper();
    // 公共属性
    singleLayoutHelper.setItemCount(3);// 设置布局里Item个数
    singleLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    singleLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    singleLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    singleLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
    return singleLayoutHelper;
  }

  /**
   * 设置栏格布局
   * 可理解为只有一行的线性布局
   */
  private ColumnLayoutHelper getColumnLayoutHelper() {

    ColumnLayoutHelper columnLayoutHelper = new ColumnLayoutHelper();
    // 创建对象

    // 公共属性
    columnLayoutHelper.setItemCount(3);// 设置布局里Item个数
    columnLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    columnLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    columnLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    columnLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

    // columnLayoutHelper特有属性
    columnLayoutHelper.setWeights(new float[] { 30, 40, 30 });// 设置该行每个Item占该行总宽度的比例
    // 同上面Weigths属性讲解

    return columnLayoutHelper;
  }

  /**
   * 设置浮动布局
   */
  private FloatLayoutHelper getFloatLayoutHelper() {
    FloatLayoutHelper floatLayoutHelper = new FloatLayoutHelper();
    // 创建FloatLayoutHelper对象

    // 公共属性
    floatLayoutHelper.setItemCount(1);// 设置布局里Item个数
    // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
    //        @Override
    //        public void setItemCount(int itemCount) {
    //            if (itemCount > 0) {
    //                super.setItemCount(1);
    //            } else {
    //                super.setItemCount(0);
    //            }
    //        }
    floatLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    floatLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    floatLayoutHelper.setBgColor(Color.BLUE);// 设置背景颜色
    floatLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

    // floatLayoutHelper特有属性
    floatLayoutHelper.setDefaultLocation(300, 300);// 设置布局里Item的初始位置
    return floatLayoutHelper;
  }

  /**
   * 设置可选固定布局
   * 到页面底部显示”一键到顶部“的按钮功能
   */
  private ScrollFixLayoutHelper getScrollFixLayoutHelper() {

    ScrollFixLayoutHelper scrollFixLayoutHelper =
        new ScrollFixLayoutHelper(ScrollFixLayoutHelper.TOP_RIGHT, 50, 50);
    // 参数说明:
    // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    // 参数2:基准位置的偏移量x
    // 参数3:基准位置的偏移量y

    // 公共属性
    scrollFixLayoutHelper.setItemCount(1);// 设置布局里Item个数
    // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
    //        @Override
    //        public void setItemCount(int itemCount) {
    //            if (itemCount > 0) {
    //                super.setItemCount(1);
    //            } else {
    //                super.setItemCount(0);
    //            }
    //        }
    scrollFixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    scrollFixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    scrollFixLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    scrollFixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

    // fixLayoutHelper特有属性
    scrollFixLayoutHelper.setAlignType(FixLayoutHelper.TOP_RIGHT);// 设置吸边时的基准位置(alignType)
    scrollFixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
    scrollFixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
    scrollFixLayoutHelper.setShowType(ScrollFixLayoutHelper.SHOW_ON_ENTER);// 设置Item的显示模式
    return scrollFixLayoutHelper;
  }

  private FixLayoutHelper getFixLayoutHelper() {
    /**
     设置固定布局
     */
    FixLayoutHelper fixLayoutHelper = new FixLayoutHelper(FixLayoutHelper.TOP_LEFT, 40, 100);
    // 参数说明:
    // 参数1:设置吸边时的基准位置(alignType) - 有四个取值:TOP_LEFT(默认), TOP_RIGHT, BOTTOM_LEFT, BOTTOM_RIGHT
    // 参数2:基准位置的偏移量x
    // 参数3:基准位置的偏移量y

    // 公共属性
    fixLayoutHelper.setItemCount(1);// 设置布局里Item个数
    // 从设置Item数目的源码可以看出，一个FixLayoutHelper只能设置一个
    //        @Override
    //        public void setItemCount(int itemCount) {
    //            if (itemCount > 0) {
    //                super.setItemCount(1);
    //            } else {
    //                super.setItemCount(0);
    //            }
    //        }
    fixLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    fixLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    fixLayoutHelper.setBgColor(Color.YELLOW);// 设置背景颜色
    fixLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比
    // fixLayoutHelper特有属性
    fixLayoutHelper.setAlignType(FixLayoutHelper.TOP_LEFT);// 设置吸边时的基准位置(alignType)
    fixLayoutHelper.setX(30);// 设置基准位置的横向偏移量X
    fixLayoutHelper.setY(50);// 设置基准位置的纵向偏移量Y
    return fixLayoutHelper;
  }

  private GridLayoutHelper getGridLayoutHelper() {
    /**
     设置Grid布局
     */
    GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(3);
    // 在构造函数设置每行的网格个数
    // 公共属性
    gridLayoutHelper.setItemCount(5);// 设置布局里Item个数
    gridLayoutHelper.setPadding(20, 20, 20, 20);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    gridLayoutHelper.setMargin(20, 20, 20, 20);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    gridLayoutHelper.setBgColor(Color.parseColor("#FFF7F7D7"));// 设置背景颜色
    gridLayoutHelper.setAspectRatio(6);// 设置设置布局内每行布局的宽与高的比

    // gridLayoutHelper特有属性（下面会详细说明）
    gridLayoutHelper.setWeights(new float[] { 40, 30, 30 });//设置每行中 每个网格宽度 占 每行总宽度 的比例
    gridLayoutHelper.setVGap(1);// 控制子元素之间的垂直间距
    gridLayoutHelper.setHGap(1);// 控制子元素之间的水平间距
    gridLayoutHelper.setAutoExpand(false);//是否自动填充空白区域
    gridLayoutHelper.setSpanCount(3);// 设置每行多少个网格
    // 通过自定义SpanSizeLookup来控制某个Item的占网格个数
    //gridLayoutHelper.setSpanSizeLookup(new GridLayoutHelper.SpanSizeLookup() {
    //  @Override public int getSpanSize(int position) {
    //    if (position > 5) {
    //      return 3;
    //      // 第7个位置后,每个Item占3个网格
    //    } else {
    //      return 2;
    //      // 第7个位置前,每个Item占2个网格
    //    }
    //  }
    //});
    return gridLayoutHelper;
  }

  @NonNull private LinearLayoutHelper getLinearLayoutHelper() {
    /**
     设置线性布局
     */
    LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
    // 创建对应的LayoutHelper对象
    // 所有布局的公共属性（属性会在下面详细说明）
    linearLayoutHelper.setItemCount(5);// 设置布局里Item个数
    linearLayoutHelper.setPadding(10, 10, 10, 10);// 设置LayoutHelper的子元素相对LayoutHelper边缘的距离
    linearLayoutHelper.setMargin(10, 10, 10, 10);// 设置LayoutHelper边缘相对父控件（即RecyclerView）的距离
    linearLayoutHelper.setBgColor(Color.GRAY);// 设置背景颜色
    linearLayoutHelper.setAspectRatio(4);// 设置设置布局内每行布局的宽与高的比

    // linearLayoutHelper特有属性
    linearLayoutHelper.setDividerHeight(10); // 设置每行Item的距离
    return linearLayoutHelper;
  }

  class MainViewHolder extends RecyclerView.ViewHolder {

    public MainViewHolder(View itemView) {
      super(itemView);
    }
  }
}
