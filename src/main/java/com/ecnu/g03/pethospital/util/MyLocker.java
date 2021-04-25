package com.ecnu.g03.pethospital.util;

import java.util.concurrent.ConcurrentHashMap;

/**
 * this is a singleton providing row-level locker
 * @author Shen Lei
 * @date 2021/4/25 21:11
 */
public class MyLocker {

    private static MyLocker myLocker;
    private ConcurrentHashMap<String, Boolean> idMap = new ConcurrentHashMap<>();

    private MyLocker() {

    }

    public static MyLocker getMyLocker() {
        if (myLocker == null) {
            myLocker = new MyLocker();
        }
        return myLocker;
    }

    public synchronized boolean lockWithoutWait(String table, String id) {
        if (idMap.containsKey(table + id)) {
            return false;
        }
        idMap.put(table + id, true);
        return true;
    }

    public synchronized void lock(String table, String id) throws InterruptedException {
        while (idMap.containsKey(table + id)) {
            wait();
        }
        idMap.put(table + id, true);
    }

    public synchronized void unlock(String table, String id) {
        if (idMap.containsKey(table + id)) {
            idMap.remove(table + id);
            notifyAll();
        }
    }

}
