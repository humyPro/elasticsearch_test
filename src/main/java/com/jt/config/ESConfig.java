package com.jt.config;

import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;

@Configuration
public class ESConfig implements FactoryBean<TransportClient>, InitializingBean {
    @Value("${cluster-name}")
    private String name;
    @Value("${cluster-nodes}")
    private String node;

    private TransportClient client;


    @Override
    public TransportClient getObject() throws Exception {
        return client;

    }

    @Override
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Settings setting = Settings.builder().put("cluster.name", name).build();
        //用上面的设置对象创建一个客户端对象
        TransportClient preClient = new PreBuiltTransportClient(setting);
        //获取节点信息
        String[] HostAndPost = node.split(":");

        preClient.addTransportAddress(
                new InetSocketTransportAddress(
                        InetAddress.getByName(HostAndPost[0]),
                        Integer.parseInt(HostAndPost[1])
                )
        );
        client = preClient;
    }
}
