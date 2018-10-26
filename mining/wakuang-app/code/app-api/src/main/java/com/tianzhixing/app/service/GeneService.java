package com.tianzhixing.app.service;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.Gene;

public interface GeneService {
	/**
	 * 增加基因数据认证
	 * @param gene
	 * @return
	 * @throws SQLException 
	 */
	Integer addGene(Gene gene) throws SQLException;
	
	/**
	 * 查询某用户的基因数据
	 * @param userId
	 * @return
	 * @throws SQLException 
	 */
	Gene findGene(Integer userId) throws SQLException;
}
