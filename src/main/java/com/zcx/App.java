package com.zcx;

import com.zcx.entity.User;
import com.zcx.schedule.ScheduledTest2;
import com.zcx.service.UserService;
import org.junit.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App extends SpringTestCase
{
    @Test
    public void test() {
            String a = "2018-02-22 11:15:00.";
            System.out.println(a.substring(0,a.length()-1));
            Date date = getCurrentDate(a,"yyyy-MM-dd HH:mm:ss");
            System.out.println(getDteToString(getValidEndTime(date,7),"yyyy-MM-dd"));
    }




    public static Date  getValidEndTime(Date date,int validDayCount){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String datestr = sdf.format(date);

        try {

            date = sdf.parse(datestr);

        } catch (ParseException e) {

// TODO Auto-generated catch block

            e.printStackTrace();

        }

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);

        calendar.add(Calendar.DATE, validDayCount);

        date = calendar.getTime();

        return date;

    }

    public static  Date getCurrentDate(String date,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 时间类型转字符串 不固定日期格式
     * @param date
     * @param format
     * @return String
     */
    public static String getDteToString (Date date,String format){
        String s;
        SimpleDateFormat sft=new SimpleDateFormat(format);//格式时间对象
        s=sft.format(date);
        return s;
    }


    @Test
    public void testTimeZoneTransfer(){
        String result = timeZoneTransfer("2018-07-03", "yyyy-MM-dd", "+8", "-4");
        //Assert.assertEquals("转换错误", "2018-07-03 07:43", result);
        System.out.println(result);
//        result = timeZoneTransfer("2018-07-03 15:43", "yyyy-MM-dd HH:mm", "+9", "0");
//        //Assert.assertEquals("转换错误", "2018-07-03 06:43", result);
//        System.out.println(result);
//        result = timeZoneTransfer("2018-07-03 15:43", "yyyy-MM-dd HH:mm", "-1", "0");
//        //Assert.assertEquals("转换错误", "2018-07-03 16:43", result);
//        System.out.println(result);
//        result = timeZoneTransfer("2018-07-03 15:43", "yyyy-MM-dd HH:mm", "+8", "+9");
//        //Assert.assertEquals("转换错误", "2018-07-03 16:43", result);
//        System.out.println(result);
//        result = timeZoneTransfer("2018-07-03 15:43", "yyyy-MM-dd HH:mm", "+0", "+8");
//        //Assert.assertEquals("转换错误", "2018-07-03 23:43", result);
//        System.out.println(result);
//        result =timeZoneTransfer("2018-07-03 15:43", "yyyy-MM-dd HH:mm", "+0", "0");

    }

    /**
     * 时区转换
     * @param time 时间字符串
     * @param pattern 格式 "yyyy-MM-dd HH:mm"
     * @param nowTimeZone eg:+8，0，+9，-1 等等
     * @param targetTimeZone 同nowTimeZone
     * @return
     */
    public static String timeZoneTransfer(String time, String pattern, String nowTimeZone, String targetTimeZone) {
        if(StringUtils.isBlank(time)){
            return "";
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + nowTimeZone));
        Date date;
        Date a;
        String b;
        try {
            date = simpleDateFormat.parse(time);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT" + targetTimeZone));
            b = simpleDateFormat.format(date);
            date = simpleDateFormat.parse(simpleDateFormat.format(date));
            Calendar ca = simpleDateFormat.getCalendar(); //获取当前日期
            ca.set(Calendar.DAY_OF_MONTH,ca.get(Calendar.DAY_OF_MONTH)-9);
            a = ca.getTime();
        } catch (ParseException e) {
            //logger.error("时间转换出错。", e);
            return "";
        }
        return simpleDateFormat.format(a);
    }

    @Test
    public void readTest() {
        File file = new File("E:\\IdeaProjects\\studyspring\\src\\main\\resuorces\\新建文本文档.txt");
        for (int i = 0;i<txt2String(file).size();i++) {
            String[] s = txt2String(file).get(i).split("卍");
            System.out.println("UPDATE game_config SET key_word = '"+s[1]+"' WHERE pack_name='"+s[0]+"' AND type='1';");
            writeData("UPDATE game_config SET key_word = '"+s[1]+"' WHERE pack_name='"+s[0]+"' AND type='1';");
        }
    }

    /**
     * 读取txt文件的内容
     * @param file 想要读取的文件对象
     * @return 返回文件内容
     */
    public static List<String> txt2String(File file){
        List<String> list = new ArrayList<>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                list.add(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void writeData(String data)  {
        File file = new File("E:\\IdeaProjects\\studyspring\\src\\main\\resuorces\\update.sql");
        data = data+"\r\n";
        try {
            FileOutputStream fos = new FileOutputStream(file,true);
            fos.write(data.getBytes());
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void dateTest() {
        System.out.println(System.currentTimeMillis());
        String time = String.valueOf(System.currentTimeMillis());//获取当前时间精确到毫秒级的时间戳，例：1525849325942
        System.out.println(time);
        System.out.println(timeStamp2Date(time));
    }

    public static String timeStamp2Date(String time) {
        Long timeLong = Long.parseLong(time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//要转换的时间格式
        Date date;
        try {
            date = sdf.parse(sdf.format(timeLong));
            System.out.println(date);
            return sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

}
