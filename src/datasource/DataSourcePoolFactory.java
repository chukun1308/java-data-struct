package datasource;

/**
 * 数据库工厂
 * 初始化默认的数据库连接池
 */
public class DataSourcePoolFactory {

    private static class CreatePool{
        public static IMiniDataSourcePool dataSourcePool = new DefaultDataSourcePool();
    }

    public static IMiniDataSourcePool getInstance(){
        return CreatePool.dataSourcePool;
    }
}
