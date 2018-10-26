package core.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import core.mapper.SubMapper;
import core.pojo.Sub;
import core.service.SubService;

@Service
public class SubServiceImpl implements SubService {
	@Autowired
	private SubMapper subMapper;

	public int saveSub(Sub sub) throws SQLException {
		if (prizeSub()) {//
			sub.setSubSuccess((short) 1);// 预约成功
		} else {
			sub.setSubSuccess((short) 0);// 预约失败
		}
		if (subMapper.saveSub(sub) > 0) {
			return 1;
		}
		return 0;
	}

	public List<Sub> getSubs(Integer userId, String date) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("date", date);
		return subMapper.getSubs(map);
	}

	
	public boolean prizeSub() {
		Random rand = new Random();
		int i = rand.nextInt(10);
		if (i < 5) {
			return true;
		} else {
			return false;
		}
	}
	
//	public static void main(String[] args) {
//		int j = 0;
//		Random rand = new Random();
//		int i = 0;
//		int count1=0;
//		int count0=0;
//		while(j<100) {
//			 i = rand.nextInt(10);
//			 if(i<5) {
//				 System.out.println(true);
//				 count1++;
//			 } else{
//				System.out.println(false);
//				count0++;
//			}
//			j++;
//		}
//		System.out.println("count1="+count1);
//		System.out.println("count0="+count0);
//	}
}
