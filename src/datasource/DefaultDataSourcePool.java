package datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 默认实现的数据库连接池
 */
public class DefaultDataSourcePool implements IMiniDataSourcePool{

    private List<PooledConnection> pooledConnections = new ArrayList<>();
    private static String jdbcUrl;
    private static String user;
    private static String password;
    private static int initCount;
    private static int step;
    private static int maxCount;

    public DefaultDataSourcePool(){
        //初始化数据库连接池配置
        init();
        //加载驱动
        try{
            Class.forName(DataBaseConfig.driver);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        //初始化数据库连接池的管道
        createPooledConnection(initCount);
    }

    @Override
    public PooledConnection getPooledConnection() {
        if(pooledConnections.size()<1){
            throw new RuntimeException("数据库连接池初始化错误.....");
        }
        PooledConnection pooledConnection = null;
        try{
            pooledConnection = getRealConnectionFromPool();
            while (pooledConnection==null){
                createPooledConnection(step);
                pooledConnection = getRealConnectionFromPool();
                return pooledConnection;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return pooledConnection;
    }

    /**
     * 获取一个连接，加锁操作
     * @return
     * @throws SQLException
     */
    private PooledConnection getRealConnectionFromPool() throws SQLException {
        synchronized (DefaultDataSourcePool.class){
            for(PooledConnection pooledConnection:pooledConnections){
                if(!pooledConnection.isBusy()){
                    if(pooledConnection.getConnection().isValid(3000)) {
                        //取出数据库连接，设置为busy
                        pooledConnection.setBusy(true);
                        return pooledConnection;
                    }else {
                        Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
                        pooledConnection.setBusy(true);
                        pooledConnection.setConnection(connection);
                        return pooledConnection;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 创建连接，加入到数据库连接池中
     * @param count
     */
    @Override
    public void createPooledConnection(int count) {
       if(pooledConnections.size()>maxCount || pooledConnections.size()+count>maxCount){
           throw  new RuntimeException("数据库连接池已满.....");
       }
       for(int i=0;i<count;i++){
           try{
               Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
               PooledConnection pooledConnection = new PooledConnection(connection,false);
               pooledConnections.add(pooledConnection);
           }catch (SQLException e){
               e.printStackTrace();
           }
       }
    }

    /**
     * 初始化数据库连接参数
     */
    private void init(){
        jdbcUrl = DataBaseConfig.jdbcUrl;
        user = DataBaseConfig.user;
        password = DataBaseConfig.password;
        initCount = DataBaseConfig.initCount;
        step = DataBaseConfig.step;
        maxCount = DataBaseConfig.maxCount;
    }
}
