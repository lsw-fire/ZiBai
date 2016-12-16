package lsw.fengshui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout line1 = (LinearLayout) findViewById(R.id.rowOne);
        LinearLayout line2 = (LinearLayout) findViewById(R.id.rowTwo);
        LinearLayout line3 = (LinearLayout) findViewById(R.id.rowThree);

        ArrayList<TextView> al1 = findTextViewByRow(line1);
        ArrayList<TextView> al2 = findTextViewByRow(line2);
        ArrayList<TextView> al3 = findTextViewByRow(line3);

        String[] strArray1 = new String[]{"巽","离","坤"};
        String[] strArray2 = new String[]{"震","","兑"};
        String[] strArray3 = new String[]{"艮","坎","乾"};

        setTextByRow(al1,strArray1);
        setTextByRow(al2,strArray2);
        setTextByRow(al3,strArray3);

        int index = calculate(1982,true);

        Log.d("index hexagra",Integer.toString(index));

        int index2 = calculate(1983,true);

        Log.d("index hexagra",Integer.toString(index2));
    }

    //1984 下元甲子第一年
    //一坎；二坤；三震；四巽；六乾；七兑；八艮；九离。余数是五的男性视为坤，女性则视为艮

    //1864 上元甲子第一年
    //1924 中元甲子第一年

    int[] men = new int[]{1,4,7};
    int[] women = new int[]{5,2,8};

    private int calculate(int year, boolean isMale){

        int yearCount = year - 1864;
        if ( yearCount > 0){

            int num = yearCount/60;
            int mod = yearCount%60;

            //0 上元 1中元 2下元
            int whichiYuan = num%3;

            int beginIndex = men[whichiYuan];
            if (!isMale)
            {
                beginIndex = women[whichiYuan];
            }

            if(isMale)
            {
                int modIndex = mod%9;

                //int guaIndex = 0;
                int i=0;
                while (i<modIndex){

                    while (beginIndex < 1){
                        beginIndex = 9;
                    }
                    beginIndex --;
                    i++;
                }

                return beginIndex==0?9:beginIndex;
            }
        }

        return 0;
    }



    private Dictionary<String,String> getZiBaiDefault(){
        Dictionary<String,String> dicZiBai = new Hashtable<>();

        dicZiBai.put("坎","一白");
        dicZiBai.put("坤","二黑");
        dicZiBai.put("震","三碧");
        dicZiBai.put("巽","四绿");
        dicZiBai.put("乾","六白");
        dicZiBai.put("兑","七赤");
        dicZiBai.put("艮","八白");
        dicZiBai.put("离","九紫");

        return dicZiBai;
    }

    private Dictionary<String,String> getZiBaiStarNameDefault(){
        Dictionary<String,String> dicZiBai = new Hashtable<>();

        dicZiBai.put("一白","贪狼水");
        dicZiBai.put("二黑","巨门土");
        dicZiBai.put("三碧","禄存木");
        dicZiBai.put("四绿","文曲木");
        dicZiBai.put("五黄","廉贞土");
        dicZiBai.put("六白","武曲金");
        dicZiBai.put("七赤","破军金");
        dicZiBai.put("八白","左辅土");
        dicZiBai.put("九紫","右弼火");

        return dicZiBai;
    }

    private Dictionary<String,Integer> getOrderDefault(){
        Dictionary<String,Integer> dicZiBai = new Hashtable<>();

        dicZiBai.put("坎",1);
        dicZiBai.put("坤",2);
        dicZiBai.put("震",3);
        dicZiBai.put("巽",4);
        dicZiBai.put("乾",6);
        dicZiBai.put("兑",7);
        dicZiBai.put("艮",8);
        dicZiBai.put("离",9);

        return dicZiBai;
    }


    private void setTextByRow(ArrayList<TextView> al, String[] strArray){
        for (TextView tv : al) {
            tv.setText(strArray[al.indexOf(tv)]);
        }

    }

    private ArrayList<TextView> findTextViewByRow(LinearLayout linearLayout){
        ArrayList<TextView> alOne = new ArrayList<>();
        TextView textView1 = (TextView)linearLayout.findViewById(R.id.tv1);
        TextView textView2 = (TextView)linearLayout.findViewById(R.id.tv2);
        TextView textView3 = (TextView)linearLayout.findViewById(R.id.tv3);
        alOne.add(textView1);
        alOne.add(textView2);
        alOne.add(textView3);
        return alOne;
    }
}
