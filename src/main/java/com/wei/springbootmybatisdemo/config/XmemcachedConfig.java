package com.wei.springbootmybatisdemo.config;

import lombok.extern.slf4j.Slf4j;
import net.rubyeye.xmemcached.KeyProvider;
import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.MemcachedClientBuilder;
import net.rubyeye.xmemcached.XMemcachedClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author LUCCI
 * @date 2018/8/3 22:07
 * @Description:
 * @Modify:
 */
@Slf4j
@Configuration
public class XmemcachedConfig {

    //构建builder
    @Bean
    public MemcachedClientBuilder getXMBuilder(){
        MemcachedClientBuilder memcachedClientBuilder = null;
        try {
            String server = "192.168.41.128:2222";
            memcachedClientBuilder = new XMemcachedClientBuilder(server);
            memcachedClientBuilder.setFailureMode(false);
            //就是在key的基础上加一个luqi
//            memcachedClientBuilder.setKeyProvider(new KeyProvider(){
//                @Override
//                public String process(String s) {
//                    return s+"luqi";
//                }
//            });
            return memcachedClientBuilder;
        }catch (Exception e){
            log.info("构建builder异常！！",e);
        }
        return null;
    }

    //构建client
    @Bean
    public MemcachedClient getClient(MemcachedClientBuilder memcachedClientBuilder){
        MemcachedClient memcachedClient = null;
        try {
            memcachedClient = memcachedClientBuilder.build();
            return memcachedClient;
        }catch (Exception e){
            log.info("构建client异常！！",e);
        }
        return null;
    }

}
