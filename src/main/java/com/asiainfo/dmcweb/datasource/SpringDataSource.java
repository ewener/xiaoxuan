package com.asiainfo.dmcweb.datasource;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.asiainfo.dmcweb.filter.SpringBeanInvoker;
import com.wabacus.config.database.datasource.AbsDataSource;
import com.wabacus.exception.WabacusRuntimeException;

public class SpringDataSource extends AbsDataSource {

	private DataSource ds;

	public SpringDataSource() {
		this.ds = (DataSource) SpringBeanInvoker.init().getBean("dataSource");
		System.out.println("########dataSource" + this.ds.toString());
	}

	@Override
	public Connection getConnection() {
		try {
			return this.ds.getConnection();
		} catch (SQLException e) {
			throw new WabacusRuntimeException("获取" + this.getName() + "数据源的数据库连接失败", e);
		}
	}

	@Override
	public DataSource getDataSource() {
		return this.ds;
	}

}
