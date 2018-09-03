package shi.ha;

/**
 *  zookeeper 连接配置
 * @author shi
 *
 */
public interface Conf {

	public static final String root="/master";
	public static final String zkHost="127.0.0.1:2181";
	public static final int timeOut=5000;
	public static final int connWait=1000;
}
