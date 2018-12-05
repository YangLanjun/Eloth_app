package cn.yanglj65.www.ecloth_app.Util;

import android.graphics.drawable.Drawable;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;

public class ToolUtil {
    public static ArrayList<Integer> getRandomIntArray(int n,int max){
        ArrayList<Integer> randomIntArray=new ArrayList<>();
        Random random=new Random();
        for (int i=0;i<n;i++){
            Integer randomInteger=random.nextInt(max);
            if(!randomIntArray.contains(randomInteger)){
                randomIntArray.add(randomInteger);
            }else{
                i--;
            }
        }
        return randomIntArray;
    }

    public static void setButtonImageLeft(Button button){
        Drawable[] drawables=button.getCompoundDrawables();
        drawables[0].setBounds(30,0,130,100);
        if(drawables[2]!=null){
            drawables[2].setBounds(10,0,70,60);
        }
        button.setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]);
    }

    public static String formatStr(final String leftStr,String rightStr, int length)
    {

        if (rightStr == null)
        {
            rightStr="";
        }
        int strLen = rightStr.getBytes().length+leftStr.getBytes().length;
        if (strLen == length)
        {
            return leftStr+rightStr;
        } else if (strLen < length)
        {
            int temp = length - strLen;
            String tem = "";
            for (int i = 0; i < temp; i++)
            {
                tem = tem + " ";
            }
            return leftStr + tem + rightStr;
        } else
        {
            return (leftStr+" "+rightStr).substring(0, length);
        }

    }
}
