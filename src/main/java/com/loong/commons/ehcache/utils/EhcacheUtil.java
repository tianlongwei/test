package com.loong.commons.ehcache.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

import java.net.URL;

public class EhcacheUtil {
    private static final String path="/ehcache.xml";
    private URL url;
    private CacheManager cacheManager;

    private static EhcacheUtil ehcacheUtils;

    private EhcacheUtil(String path){
        url=getClass().getResource(path);
        System.out.println(url);
        cacheManager=CacheManager.create(url);
    }

    public static EhcacheUtil getInstance(){
        if (ehcacheUtils==null){
            ehcacheUtils=new EhcacheUtil(path);
        }
        return ehcacheUtils;
    }

    public void put(String cacheName,String key,String value){
        Cache cache=cacheManager.getCache(cacheName);
        Element element=new Element(key,value);
        cache.put(element);
    }

    public Object get(String cacheName,String key){
        Cache cache=cacheManager.getCache(cacheName);
        Element element=cache.get(key);
        return element==null?null:element.getObjectValue();
    }

    public Cache get(String cacheName){
        return cacheManager.getCache(cacheName);
    }

    public void remove(String cacheName,String key){
        Cache cache=cacheManager.getCache(cacheName);
        cache.remove(key);
    }
}
