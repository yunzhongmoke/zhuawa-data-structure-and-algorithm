package com.w.greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    
    public static void main(String[] args) {
        
        //创建map集合，用于存放广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        //存放各个电台
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入电台
        broadcasts.put("k1", hashSet1);
        broadcasts.put("k2", hashSet2);
        broadcasts.put("k3", hashSet3);
        broadcasts.put("k4", hashSet4);
        broadcasts.put("k5", hashSet5);
        //存放所有地区
        HashSet<Object> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");
        //存放贪心算法选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();
        //存放电台覆盖地区和当前还未覆盖地区的交集
        HashSet<String> tempSet = new HashSet<String>();
        //保存在一次遍历过程中，存放覆盖最大未覆盖地区的key
        String maxKey = null;
        while (allAreas.size() != 0) {//电台还未覆盖所有地区
            //遍历broadcasts
            for (String key : broadcasts.keySet()) {
                //重置tempSet
                tempSet.clear();
                //保存该电台覆盖的地区
                tempSet.addAll(broadcasts.get(key));
                //取与未覆盖地区的交集
                tempSet.retainAll(allAreas);
                //比较maxKey与key覆盖的地区大小
                if (tempSet.size() > 0 && (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size())) {
                    maxKey = key;
                }
            }
            //判断maxKey是否为null
            if (maxKey == null) {
                continue;
            }
            //将其加入选择的电台集合
            selects.add(maxKey);
            //移除已覆盖的地区
            allAreas.removeAll(broadcasts.get(maxKey));
            //重置maxKey
            maxKey = null;
        }
        System.out.println(selects);
    }
}
