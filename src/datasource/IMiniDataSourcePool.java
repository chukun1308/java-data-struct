package datasource;

/**
 * 数据库连接池的接口类
 * 对连接池的一个基本管理的API接口
 * 可以得到数据库操作的管道、可以创建数据库的管理
 */
public interface IMiniDataSourcePool {

    public PooledConnection getPooledConnection();

    public void createPooledConnection(int count);
}
