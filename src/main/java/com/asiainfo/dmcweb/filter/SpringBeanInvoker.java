package com.asiainfo.dmcweb.filter;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * 获取SprinBean工具类
 * @author lx
 *
 */
public class SpringBeanInvoker implements ApplicationContextAware {

	private static ApplicationContext context = null;
	private static SpringBeanInvoker springBeanInvoker = null;

	public synchronized static SpringBeanInvoker init() {
		if (springBeanInvoker == null) {
			springBeanInvoker = new SpringBeanInvoker();
		}
		return springBeanInvoker;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public synchronized static Object getBean(String beanName) {
		return context.getBean(beanName);
	}
}