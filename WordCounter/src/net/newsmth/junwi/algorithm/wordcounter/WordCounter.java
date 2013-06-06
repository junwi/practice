package net.newsmth.junwi.algorithm.wordcounter;

import com.google.common.collect.Multiset;

/**
 * Created by IntelliJ IDEA.
 * User: Administrator
 * Date: 12-1-7
 * Time: 下午9:50
 * To change this template use File | Settings | File Templates.
 */
public interface WordCounter {
    void count(String str);

    Multiset<String> getResult();
}
