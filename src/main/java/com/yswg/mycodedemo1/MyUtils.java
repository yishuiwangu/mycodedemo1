package com.yswg.mycodedemo1;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auther: zch
 * @Date: 2019/8/29 10:08
 * @Description:
 */
@Slf4j
public class MyUtils {

    public static void main(String[] args) throws Exception {
//        ClassPathResource classPathResource = new ClassPathResource("data/SubMemidCof.json");
//        String path = classPathResource.getPath();
//        InputStream inputStream = classPathResource.getInputStream();
        System.out.println(checkSpecialWord("zz k"));
        System.out.println(checkSpecialWord("zzk"));
    }



    /**
     * 0#0#
     * @param seatNo
     * @return
     */
    public static String checkSeats(String seatNo){
        List<String> list = Arrays.asList(seatNo.split("#"));
        String s1 = list.get(0);
        String s2 = "";
        StringBuilder s3 = new StringBuilder();
        int i = 1;
        for (String s : list) {
            s2 = s;
            s3.append(i).append("#");
            i++;
        }
        if(1==list.size() && s1.equals("0")){
            return "1#";
        }
        if(1!=list.size() && s2.equals(s1)){
            return s3.toString();
        }
        return seatNo;
    }






    /**
     * 去重
     *
     * @param keyExtractor
     * @param <T>
     * @return
     */
    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * 脱密处理证件
     *
     * @param no
     * @return
     */
    public static String pwdIdentityNo(String no) {
        if (StringUtils.isBlank(no)) {
            return null;
        }
        if (no.length() < 2) {
            return no;
        }
        if (no.length() == 2) {
            return no.substring(0, 1) + "*";
        }
        int length = no.length();
        int a = length / 3;
        int b = length % 3;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a + b; i++) {
            sb.append("*");
        }
        return no.substring(0, a) + sb.toString() + no.substring(length - a);
    }

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy/MM/dd", "yyyy.MM.dd", "HH:mm", "yyyy-MM-dd HH:mm:ss"
    };





    public static String getRandNum(Integer length, Supplier<String> str) {
        return str.get().substring(0, length);
    }


    /**
     * 字符串转日期
     *
     * @param dateString
     * @return
     */
    public static Date parseStringDate(String dateString) {
        try {
            return DateUtils.parseDate(
                    dateString, parsePatterns
            );
        } catch (Exception ex) {
            log.error(MyUtils.class + "parseStringDate方法异常", ex);
            return null;
        }
    }

    /**
     * 日期转字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String formatDate(Date date, String format) {
        try {
            return DateFormatUtils.format(date, format);
        } catch (Exception ex) {
            log.error(MyUtils.class + "formatDate方法异常", ex);
            return null;
        }
    }


    /**
     * 计算某个date加几分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date dayPlusMinute(Date date, Long minute) {
        long tv = minute * 60L * 1000L;
        Date result = new Date(date.getTime() + tv);
        return result;
    }

    /**
     * 计算某个date 减几分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static Date dayMinusMinute(Date date, Long minute) {
        long tv = minute * 60L * 1000L;
        Date result = new Date(date.getTime() - tv);
        return result;
    }



    /**
     * 处理发车日期(yyyy-MM-dd)和发车时间(HH:mm 或者 HH:mm前)
     *
     * @param runDate
     * @param runTime
     * @return
     */
    public static Date dealRunDateAndRunTime(Date runDate, String runTime) {
        if (null == runDate || StringUtils.isBlank(runTime)) {
            return null;
        }
        runTime = runTime.substring(0, 5) + ":00";
        String strRunDate = MyUtils.formatDate(runDate, "yyyy-MM-dd");
        String strDate = strRunDate + " " + runTime;
        return parseStringDate(strDate);
    }

    /**
     * re 校验字符串是否含有特殊字符
     *
     * @param str
     * @return
     */
    public static boolean checkSpecialWord(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        String regEx = "[\n`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        String value = m.replaceAll("").trim();
        return str.length() == value.length();
    }

    /**
     * 编码路径
     *
     * @return
     */
    public static String encodeUrl(String urlStr) {
        if (StringUtils.isBlank(urlStr)) {
            return urlStr;
        }
        try {
            return URLEncoder.encode(urlStr, "UTF-8");
        } catch (Exception e) {
            return urlStr;
        }
    }
}
