package cn.cxd.custom_config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.ibatis.datasource.unpooled.UnpooledDataSourceFactory;

/**
 * @Author: Cxd
 * @Description:
 * @Date: Created in 12:04 2020/5/29
 * @Modified By:
 */
public class C3P0DatasourceFactory extends UnpooledDataSourceFactory {
    public C3P0DatasourceFactory() {
        dataSource = new ComboPooledDataSource();
    }
}
