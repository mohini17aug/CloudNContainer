package com.ag.common;

import java.util.concurrent.ConcurrentHashMap;

public class OrderStorage {
    private static ConcurrentHashMap<Integer, String> orderMap = new ConcurrentHashMap<>();

    public static void saveOrder(int orderId, String name) {
        orderMap.put(orderId, name);
    }

    public static String getOrderName(int orderId) {
        return orderMap.get(orderId);
    }
}
