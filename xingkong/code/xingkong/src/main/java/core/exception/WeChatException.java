package core.exception;

/**
 * 微信异常处理
 * @author dev-teng
 * @date 2018年6月11日
 */
public class WeChatException extends Exception{
	private static final long serialVersionUID = 1L;
	public WeChatException(String msg){
		super(msg);
	}
}
