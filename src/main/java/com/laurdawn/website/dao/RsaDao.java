package com.laurdawn.website.dao;

import org.springframework.stereotype.Component;

import com.laurdawn.website.entity.RSA;

@Component
public interface RsaDao{
	
	/**
	 * 通过id查询秘钥
	 * @param entity
	 * @return
	 */
	RSA selectById(int id);
	
	/**
	 * 通过公钥查询数据
	 * @param entity
	 * @return
	 */
	String selectByPubKey(String publicKeyString);
	
	/**
	 * 插入秘钥
	 * @param entity
	 * @return
	 */
	void insertEntity(RSA entity);
	
}