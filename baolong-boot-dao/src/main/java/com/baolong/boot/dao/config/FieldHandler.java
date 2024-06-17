package com.baolong.boot.dao.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * 字段处理器
 *
 * @author Baolong 2024年06月13 22:59
 * @version 1.0
 * @since 1.8
 */
public class FieldHandler implements MetaObjectHandler {

	/**
	 * 插入元对象字段填充（用于插入时对公共字段的填充）
	 *
	 * @param metaObject 元对象
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		this.setFieldValByName("createTime", new Date(), metaObject);
		this.setFieldValByName("updateTime", new Date(), metaObject);
		// TODO 获取当前操作的用户，存储当前操作用户信息
	}

	/**
	 * 更新元对象字段填充（用于更新时对公共字段的填充）
	 *
	 * @param metaObject 元对象
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("updateTime", new Date(), metaObject);
		// TODO 获取当前操作的用户，存储当前操作用户信息
	}
}
