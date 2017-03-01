package cn.thc.cache.ehcache;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by thc on 2017/3/1
 */
public class EhcacheDemo {

    public static void main(String[] args) {

        //Create a cache manager
        final CacheManager cacheManager = new CacheManager();

        //Create the cache called "helloworld"
        final Cache cache = cacheManager.getCache("helloworld");

        //create a key to map the data to
        final String key = "greeting";

        //create a data element
        final Element putGreeting = new Element(key,"hello world");

        //put the element into the data source
        cache.put(putGreeting);

        //retrieve the data element
        final Element getGreeting = cache.get(key);

        //print the value
        System.out.println(getGreeting.getObjectValue());

    }

}
