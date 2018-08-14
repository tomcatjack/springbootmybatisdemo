package com.wei.springbootmybatisdemo;

import com.wei.springbootmybatisdemo.controller.ShowApi;
import net.rubyeye.xmemcached.KeyIterator;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;
import net.rubyeye.xmemcached.utils.AddrUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeoutException;
import java.util.function.DoubleToIntFunction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootmybatisdemoApplicationTests {

	@Autowired
	ShowApi showApi;
	@Autowired
	MemcachedClient memcachedClient;

	@Test
	public void contextLoads() {
		//showApi.add("m3","hahm33");
		String v = showApi.quary("l_1");
		System.out.println(v);
	}
//192.168.41.128:2222 192.168.41.128:3333
	@Test
	public void getkey() throws InterruptedException, MemcachedException, TimeoutException {
        KeyIterator keyIterator = memcachedClient.getKeyIterator(AddrUtil.getOneAddress("192.168.41.128:3333"));

        while(keyIterator.hasNext())
		{
			String key=keyIterator.next();
            System.out.println(key);
		}
	}

}
