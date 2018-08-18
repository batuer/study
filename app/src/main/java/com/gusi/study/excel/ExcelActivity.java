package com.gusi.study.excel;

import android.util.Log;
import android.view.View;
import com.blankj.utilcode.util.ToastUtils;
import com.gusi.study.R;
import com.gusi.study.base.BaseActivity;
import com.gusi.study.objectbox.bean.Student;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelActivity extends BaseActivity {

  private ExecutorService mService;
  private ArrayList<ArrayList<String>> recordList;
  private List<Student> students;
  private static String[] title = { "编号", "姓名", "性别", "年龄", "班级", "数学", "英语", "语文" };
  //private File file;
  //private String fileName;

  @Override protected int getLayout() {
    return R.layout.activity_excel;
  }

  @Override protected void initView() {
    initToolBar(mToolbar, true, "Excel");
    mService = Executors.newSingleThreadExecutor();
    initData();

    findViewById(R.id.btn_generate_excel).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        mService.execute(new Runnable() {
          @Override public void run() {
            generateExcel();
          }
        });
      }
    });
  }

  private void initData() {
    //模拟数据集合
    students = new ArrayList<>();
    for (int i = 1; i <= 10; i++) {
      students.add(new Student("小红" + i, "女", "12", "1" + i, "一班", "85", "77", "98"));
      students.add(new Student("小明" + i, "男", "14", "2" + i, "二班", "65", "57", "100"));
    }
  }

  private void generateExcel() {

    try {
      File parentFile = new File(getExternalCacheDir() + "/Record");
      if (!parentFile.exists()) {
        parentFile.mkdirs();
      }
      File file = new File(parentFile, "成绩表.xlsx");
      if (!file.exists()) {
        file.createNewFile();
      }
      //ExcelUtils.initExcel(file, title);
      //Log.w("Fire", "ExcelActivity:72行:");
      //ExcelUtils.writeObjListToExcel(getRecordData(), file, this);
      test(file);
      ToastUtils.showShort("生成成功!");
    } catch (Exception e) {
      Log.e("Fire", "ExcelActivity:65行:" + e.toString());
    }
  }

  /**
   * 将数据集合 转化成ArrayList<ArrayList<String>>
   */
  private ArrayList<ArrayList<String>> getRecordData() {
    recordList = new ArrayList<>();
    for (int i = 0; i < students.size(); i++) {
      Student student = students.get(i);
      ArrayList<String> beanList = new ArrayList<String>();
      beanList.add(student.id);
      beanList.add(student.name);
      beanList.add(student.sex);
      beanList.add(student.age);
      beanList.add(student.classNo);
      beanList.add(student.math);
      beanList.add(student.english);
      beanList.add(student.chinese);
      recordList.add(beanList);
    }
    return recordList;
  }

  private void test(File file) throws Exception {
    //210×297

    //创建Excel表
    OutputStream os = new FileOutputStream(file);
    WritableWorkbook wwb = Workbook.createWorkbook(os);
    //创建第一个表并设置第一格名字
    WritableCellFormat format = getFormat();
    //生成名为“第一页”的工作表，参数0表示这是第一页
    WritableSheet sheet = wwb.createSheet("单号", 0);//表名
    //设定单元格高度与宽度 设定第一行高度500 设定第一列宽度30



    //第一行头部
    Label label;//(头部4列)
    for (int i = 0; i < title.length; i++) {
      //每一格是以二维数组的形式存在（列,行）（0,0； 1,0 ；2,0 ；3,0）
      label = new Label(i, 0, title[i], format);//一个单元格;
      sheet.addCell(label);//将单元格加入表中
      sheet.setRowView(0, 400);
      sheet.setColumnView(i, 50);
    }
    //具体数据(口口口口)
    for (int i = 0; i < students.size(); i++) {
      Student order = students.get(i);
      //column row  content
      Label id = new Label(0, i + 1, "id:" + i, format);
      Label phone = new Label(1, i + 1, "phone:" + i, format);
      Label name = new Label(2, i + 1, "name:" + i, format);
      Label addr = new Label(3, i + 1, "add:" + i, format);

      sheet.addCell(id);
      sheet.addCell(phone);
      sheet.addCell(name);
      sheet.addCell(addr);
    }
    int line = students.size() + 2;
    Label id = new Label(0, line, "id:大发送到" + line, format);
    Label phone = new Label(1, line, "phone:" + line, format);
    Label name = new Label(2, line, "name:" + line, format);
    Label addr = new Label(3, line, "add阿斯顿发送到发送到发送到发送到:" + line, format);

    sheet.addCell(id);
    sheet.addCell(phone);
    sheet.addCell(name);
    sheet.addCell(addr);

    File parentFile = getExternalCacheDir().getParentFile().getParentFile().getParentFile();
    File imgFile = new File(parentFile, "img.jpg");
    //Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imgFile));
    //int width = bitmap.getWidth();
    //int height = bitmap.getHeight();
    //Log.w("Fire", "ExcelActivity:162行:" + width + ':' + height);
    WritableImage image =
        new WritableImage(1, 1, 3, 2, imgFile);//从A1开始 跨2行3个单元格

    sheet.addImage(image);

    wwb.write();//写入数据
    wwb.close();
  }

  /**
   * 表格样式
   */
  public static WritableCellFormat getFormat() throws Exception {
    //参数1：字体大小， 2：18，3：粗体，4：斜体
    //
    //WritableFont font = new WritableFont(WritableFont.ARIAL, 12, WritableFont.NO_BOLD);// 定义字体
    WritableFont font =
        new WritableFont(WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD);// 定义字体
    font.setColour(Colour.BLACK);// 蓝色字体
    //单元格样式
    WritableCellFormat format = new WritableCellFormat(font);

    format.setAlignment(jxl.format.Alignment.CENTRE);// 左右居中
    format.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);// 上下居中
    format.setBorder(Border.ALL, BorderLineStyle.THIN, Colour.BLACK);// 黑色边框

    format.setWrap(true);
    //font.setStruckout();
    return format;
  }
}
