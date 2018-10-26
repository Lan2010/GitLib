package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.Gene;

public interface GeneMapper {
	/**
	 * 增加基因数据认证
	 * @param gene
	 * @return
	 */
	Integer addGene(Gene gene)throws SQLException;

	/**
	 * 查找某用户的基因数据
	 * @param userId
	 * @return
	 */
	Gene findGene(Integer userId)throws SQLException;
}
