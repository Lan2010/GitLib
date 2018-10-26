package com.tianzhixing.app.service.impl;

import java.sql.SQLException;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianzhixing.app.dao.mapper.GeneMapper;
import com.tianzhixing.app.pojo.Gene;
import com.tianzhixing.app.service.GeneService;

@Service
public class GeneServiceImpl implements GeneService {
	@Resource
	private GeneMapper geneMapper;
	
	@Override
	public Integer addGene(Gene gene) throws SQLException {
		return geneMapper.addGene(gene);
	}

	@Override
	public Gene findGene(Integer userId) throws SQLException {
		return geneMapper.findGene(userId);
	}
	
}
