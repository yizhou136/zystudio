package com.zy.common.java;

import java.util.*;

/**
 * Created by Administrator on 2017/6/22.
 */
public class DAF {

    private HashMap<String,Object> sensitiveWordMap;

    public  void addSenstiveWordToHashMap(Set<String> keyWordSet){
        sensitiveWordMap = new HashMap<>(keyWordSet.size());

        String key = null;
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        //迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while(iterator.hasNext()){
            key = iterator.next();    //关键字
            nowMap = sensitiveWordMap;
            for(int i = 0 ; i < key.length() ; i++){
                char keyChar = key.charAt(i);       //转换成char型
                Object wordMap = nowMap.get(keyChar);       //获取

                if(wordMap != null){        //如果存在该key，直接赋值
                    nowMap = (Map) wordMap;
                }
                else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<String,String>();
                    newWorMap.put("isEnd", "0");     //不是最后一个
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if(i == key.length() - 1){
                    nowMap.put("isEnd", "1");    //最后一个
                }
            }
        }
    }

    public static void main(String argv[]){
        DAF daf = new DAF();
        Set<String> set = new HashSet<>();
        set.add("江泽民");
        set.add("江泽民SB");
        daf.addSenstiveWordToHashMap(set);

        System.out.println(daf.sensitiveWordMap);
    }
}
