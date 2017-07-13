package com.kangyonggan.app.dfjz.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 生辰八字
 *
 * @author kangyonggan
 * @since 7/11/17
 */
public class DestinyUtil {

    /**
     * 天干，10个
     */
    private static final String[] TIAN_GAN = {"甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"};

    /**
     * 地支，12个
     */
    private static final String[] DI_ZHI = {"子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"};

    /**
     * 甲子，60个
     */
    private static final String[] JIA_ZI = {
            "丙寅", "戊寅", "庚寅", "壬寅", "甲寅",
            "丁卯", "己卯", "辛卯", "癸卯", "乙卯",
            "戊辰", "庚辰", "壬辰", "甲辰", "丙辰",
            "己巳", "辛巳", "癸巳", "乙巳", "丁巳",
            "庚午", "壬午", "甲午", "丙午", "戊午",
            "辛未", "癸未", "乙未", "丁未", "己未",
            "壬申", "甲申", "丙申", "戊申", "庚申",
            "癸酉", "乙酉", "丁酉", "己酉", "辛酉",
            "甲戌", "丙戌", "戊戌", "庚戌", "壬戌",
            "乙亥", "丁亥", "己亥", "辛亥", "癸亥",
            "丙子", "戊子", "庚子", "壬子", "甲子",
            "丁丑", "己丑", "辛丑", "癸丑", "乙丑"
    };

    /**
     * 获取生辰八字(阴历)
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     * @throws Exception
     */
    public static String getEightWord4Lunar(int year, int month, int day, int hour) throws Exception {
        try {
            String res = CalendarUtil.lunarToSolar(LocalDate.of(year, month, day).format(DateTimeFormatter.BASIC_ISO_DATE));
            return getEightWord(Integer.parseInt(res.substring(0, 4)), Integer.parseInt(res.substring(4, 6)), Integer.parseInt(res.substring(6, 8)), hour);
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 获取生辰八字
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    public static String getEightWord(int year, int month, int day, int hour) {
        return getYearColumn(year) + getMonthColumn(year, month) + getDayColumn(year, month, day) + getHourColumn(year, month, day, hour);
    }

    /**
     * 计算年柱
     * <p/>
     * 年柱 = 天干 + 地支
     *
     * @param year
     * @return
     */
    private static String getYearColumn(int year) {
        return getTianGan(year - 3) + getDiZhi(year - 3);
    }

    /**
     * 计算天干
     *
     * @param num
     * @return
     */
    private static String getTianGan(int num) {
        return TIAN_GAN[getTianGanIndex(num)];
    }

    /**
     * 计算天干下标
     *
     * @param num
     * @return
     */
    private static int getTianGanIndex(int num) {
        return (num % TIAN_GAN.length + 9) % 10;
    }

    /**
     * 计算地支
     *
     * @param num
     * @return
     */
    private static String getDiZhi(int num) {
        return DI_ZHI[getDiZhiIndex(num)];
    }

    /**
     * 计算地支下标
     *
     * @param num
     * @return
     */
    private static int getDiZhiIndex(int num) {
        return (num % DI_ZHI.length + 9) % 10;
    }

    /**
     * 计算月柱
     *
     * @param year
     * @param month
     * @return
     */
    private static String getMonthColumn(int year, int month) {
        return JIA_ZI[getTianGanIndex(year - 3) % 5 + (month - 1) * 5];
    }

    /**
     * 计算日柱
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static String getDayColumn(int year, int month, int day) {
        int remainder = ((year - 1) * 5 + (year - 1) / 4 + LocalDate.of(year, month, day).getDayOfYear()) % 60;
        return getTianGan(remainder) + getDiZhi(remainder);
    }

    /**
     * 计算时柱
     *
     * @param year
     * @param month
     * @param day
     * @param hour
     * @return
     */
    private static String getHourColumn(int year, int month, int day, int hour) {
        int remainder = ((year - 1) * 5 + (year - 1) / 4 + LocalDate.of(year, month, day).getDayOfYear()) % 60;
        int col = getTianGanIndex(remainder) + 1;
        int row = (hour + 3) / 2 % 13;

        return TIAN_GAN[(col + row * 5) % 10] + DI_ZHI[row - 1];
    }

}
