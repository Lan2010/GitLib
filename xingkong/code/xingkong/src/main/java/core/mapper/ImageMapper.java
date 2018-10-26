package core.mapper;


/**
 * 图片管理层
 * @author dev-lan
 * @date 2018年6月13日
 *
 */
public interface ImageMapper {

	/**
	 * 删除用户图片
	 * @param fileName
	 * @param userId
	 * @return
	 */
	Integer delImage(String fileName, String userId);

}
