package org.bran.sso.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.IdleConnectionEvictor;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: BranSummer
 * @Date: 2018-9-20 20:42
 * @Description: HttpClient Configration
 */

@Configuration
@PropertySource("classpath:httpclient.properties")
public class HttpClientConfig {

    /**
     * 连接池的最大连接数
     */
    @Value("${http.maxTotal}")
    private Integer httpMaxTotal;


    /**
     * 每个路由的默认连接数
     */
    @Value("${defaultMaxPerRoute}")
    private Integer httpDefaultMaxPerRoute;

    /**
     * 连接超时时间
     */
    @Value("${http.connectTimeout=1000}")
    private Integer httpConnectTimeout;

    /**
     * 获取连接的最长时间
     */
    @Value("${connectionRequestTimeout}")
    private Integer httpConnectionRequestTimeout;

    /**
     * 数据传输的最长时间
     */
    @Value("${socketTimeout}")
    private Integer httpSocketTimeout;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
        // 最大连接数
        poolingHttpClientConnectionManager.setMaxTotal(httpMaxTotal);
        // 每个主机的最大并发数
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(httpDefaultMaxPerRoute);
        return poolingHttpClientConnectionManager;
    }

    @Bean //定期清理无效连接
    public IdleConnectionEvictor idleConnectionEvictor(PoolingHttpClientConnectionManager manager){
        return new IdleConnectionEvictor(manager,1L,TimeUnit.HOURS);
    }

    @Bean   // 定义HttpClient对象 注意该对象需要设置scope="prototype":多例对象
    @Scope("prototype")
    public CloseableHttpClient closeableHttpClient(PoolingHttpClientConnectionManager manager) {
        return HttpClients.custom().setConnectionManager(manager).build();
    }


    @Bean   // 请求配置
    public RequestConfig requestConfig() {
        return RequestConfig.custom().setConnectTimeout(httpConnectTimeout) // 创建连接的最长时间
                .setConnectionRequestTimeout(httpConnectionRequestTimeout) // 从连接池中获取到连接的最长时间
                .setSocketTimeout(httpSocketTimeout) // 数据传输的最长时间
                .build();
    }
}
