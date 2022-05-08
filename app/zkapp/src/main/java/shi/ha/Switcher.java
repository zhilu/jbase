package shi.ha;

import java.util.Arrays;
import java.util.List;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * 应用和zookeeper的连接器
 * 维护应用自身注册的id和当前所有注册的最小id
 * 最小的id开始运行
 * 应用自己判断自己是不是应该运行，是则启动运行
 * @author shi
 *
 */
public class Switcher implements Watcher {

	private ZooKeeper zk = null;
	private String regSeq = null;
	private String working = null;

	public Switcher() {
		init();
	}

	public void init() {
		try {
			zk = new ZooKeeper(Conf.zkHost, Conf.timeOut, this);

			while (ZooKeeper.States.CONNECTED != zk.getState()) {
				Thread.sleep(Conf.connWait);
			}

			if (zk.exists(Conf.root, false) == null) {
				zk.create(Conf.root, "".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			}

			String regRes = zk.create(Conf.root + "/", "".getBytes(), Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			regSeq = getLast(regRes);

			String working = getMinSeq(Conf.root);

			if (working.equals(regSeq)) {
				setWorkingkey(regSeq);
				zk.setData(Conf.root, regSeq.getBytes(), -1);
			} else {
				setWorkingkey(working);
				zk.setData(Conf.root, working.getBytes(), -1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean isConnected() {
		return (zk == null) ? false : true;
	}

	public String getWorkingkey() {
		return working;
	}

	public void setWorkingkey(String workingkey) {
		this.working = workingkey;
	}

	public String getRegKey() {
		return regSeq;
	}

	@Override
	public void process(WatchedEvent event) {
		if (event.getType() == Watcher.Event.EventType.NodeChildrenChanged) {
			try {
				String workingKey = getMinSeq(Conf.root);
				setWorkingkey(workingKey);
				zk.setData(Conf.root, getRegKey().getBytes(), -1);
			} catch (Exception e) {
			}
		}
	}

	private String getMinSeq(String root) {
		String minSeq = "";
		try {
			List<String> Keys = zk.getChildren(root, true);
			String[] nodes = Keys.toArray(new String[Keys.size()]);
			Arrays.sort(nodes);
			minSeq = nodes[0];
			System.out.println(minSeq);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
		return minSeq;
	}

	private String getLast(String dir) {
		String res = dir.substring(dir.lastIndexOf("/") + 1);
		return res;
	}
}
