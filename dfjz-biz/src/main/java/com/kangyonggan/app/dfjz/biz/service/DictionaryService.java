package com.kangyonggan.app.dfjz.biz.service;

import com.kangyonggan.app.dfjz.model.vo.Dictionary;

import java.util.List;

/**
 * @author kangyonggan
 * @since 2017/4/22 0022
 */
public interface DictionaryService {

    /**
     * 保存字典
     *
     * @param dictionary
     */
    void saveDictionary(Dictionary dictionary);

    /**
     * 获取某类型的字典
     *
     * @param type
     * @return
     */
    List<Dictionary> findDictionariesByType(String type);

}
