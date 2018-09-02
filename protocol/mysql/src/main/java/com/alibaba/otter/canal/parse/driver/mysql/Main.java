package com.alibaba.otter.canal.parse.driver.mysql;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.otter.canal.parse.driver.mysql.packets.server.FieldPacket;
import com.alibaba.otter.canal.parse.driver.mysql.packets.server.ResultSetPacket;

public class Main {
	public static void main(String[] args) throws Exception {
		InetAddress host=InetAddress.getByName("172.172.0.107");
		InetSocketAddress ip = new InetSocketAddress(host,3306);
		MysqlConnector conn = new MysqlConnector(ip,"xluser","xluser");
		conn.connect();
		
		MysqlQueryExecutor mqe = new MysqlQueryExecutor(conn);
		
		ResultSetPacket res =mqe.query("select * from xlcloud.device limit 10");
//		System.out.println(res.toString());
		List<String>  data = res.getFieldValues();
		List<FieldPacket> field =res.getFieldDescriptors();
		for (FieldPacket d: field) {
			System.out.print(d.getName()+"\t");
		}
		System.out.println();
		int i =0;
		for (String d:data) {
			System.out.print(d+"\t");
			if (i%13==0) {
				System.out.println();
			}
			i++;
		}
	}
}
