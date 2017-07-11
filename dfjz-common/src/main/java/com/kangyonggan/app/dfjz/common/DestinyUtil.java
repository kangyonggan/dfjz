package com.kangyonggan.app.dfjz.common;

import java.time.LocalDate;

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
     * 阴历阳历对照表
     */
    private static final long[] LUNAR_INFO = new long[]{0x04bd8, 0x04ae0,
            0x0a570, 0x054d5, 0x0d260, 0x0d950, 0x16554, 0x056a0, 0x09ad0,
            0x055d2, 0x04ae0, 0x0a5b6, 0x0a4d0, 0x0d250, 0x1d255, 0x0b540,
            0x0d6a0, 0x0ada2, 0x095b0, 0x14977, 0x04970, 0x0a4b0, 0x0b4b5,
            0x06a50, 0x06d40, 0x1ab54, 0x02b60, 0x09570, 0x052f2, 0x04970,
            0x06566, 0x0d4a0, 0x0ea50, 0x06e95, 0x05ad0, 0x02b60, 0x186e3,
            0x092e0, 0x1c8d7, 0x0c950, 0x0d4a0, 0x1d8a6, 0x0b550, 0x056a0,
            0x1a5b4, 0x025d0, 0x092d0, 0x0d2b2, 0x0a950, 0x0b557, 0x06ca0,
            0x0b550, 0x15355, 0x04da0, 0x0a5d0, 0x14573, 0x052d0, 0x0a9a8,
            0x0e950, 0x06aa0, 0x0aea6, 0x0ab50, 0x04b60, 0x0aae4, 0x0a570,
            0x05260, 0x0f263, 0x0d950, 0x05b57, 0x056a0, 0x096d0, 0x04dd5,
            0x04ad0, 0x0a4d0, 0x0d4d4, 0x0d250, 0x0d558, 0x0b540, 0x0b5a0,
            0x195a6, 0x095b0, 0x049b0, 0x0a974, 0x0a4b0, 0x0b27a, 0x06a50,
            0x06d40, 0x0af46, 0x0ab60, 0x09570, 0x04af5, 0x04970, 0x064b0,
            0x074a3, 0x0ea50, 0x06b58, 0x055c0, 0x0ab60, 0x096d5, 0x092e0,
            0x0c960, 0x0d954, 0x0d4a0, 0x0da50, 0x07552, 0x056a0, 0x0abb7,
            0x025d0, 0x092d0, 0x0cab5, 0x0a950, 0x0b4a0, 0x0baa4, 0x0ad50,
            0x055d9, 0x04ba0, 0x0a5b0, 0x15176, 0x052b0, 0x0a930, 0x07954,
            0x06aa0, 0x0ad50, 0x05b52, 0x04b60, 0x0a6e6, 0x0a4e0, 0x0d260,
            0x0ea65, 0x0d530, 0x05aa0, 0x076a3, 0x096d0, 0x04bd7, 0x04ad0,
            0x0a4d0, 0x1d0b6, 0x0d250, 0x0d520, 0x0dd45, 0x0b5a0, 0x056d0,
            0x055b2, 0x049b0, 0x0a577, 0x0a4b0, 0x0aa50, 0x1b255, 0x06d20,
            0x0ada0};

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
