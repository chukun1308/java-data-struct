package datasource;

/**
 * 数据库的基本配置类
 */
public class DataBaseConfig {

    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String jdbcUrl="jdbc:mysql;//192.168.1.127:3306/test";
    public static final String user="root";
    public static final String password="root";

    //数据库连接池的初始化的大小
    public static final int initCount=10;

    //连接池不足时增长的步进
    public static  final  int step=2;

    //连接池的最大数量
    public static final int maxCount=50;
}
