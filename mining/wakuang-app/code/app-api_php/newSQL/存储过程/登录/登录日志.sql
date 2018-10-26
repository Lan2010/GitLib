DROP PROCEDURE IF EXISTS sp_Login_log; 
CREATE PROCEDURE `sp_Login_log`(
            m_user_id 			INT(11),         -- 用户ID
            m_phone 		  varchar(150),
            m_terminal 		varchar(150),     -- 终端
            m_equipment  	varchar(255),   -- 设备描述
            m_operate_ip 	varchar(150)
)
   COMMENT '登录日志'
BEGIN
        DECLARE t_error INTEGER DEFAULT 0;  
        DECLARE result INTEGER DEFAULT 0; 
        DECLARE v_pc INTEGER DEFAULT 0; 
        DECLARE v_wx INTEGER DEFAULT 0; -- 微信
        DECLARE v_m INTEGER DEFAULT 0; -- 手机 	
        DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;  
        START TRANSACTION;
        IF (m_terminal=1) THEN
            set v_pc = 1;
        ELSEIF (m_terminal=4) THEN
            set v_wx = 1;
        ELSEIF (m_terminal>1 and m_terminal<4) THEN
            set v_m = 1;
        END IF;
            insert into d_user_login_log(user_id,phone,equipment,login_ip,login_datetime,terminal) VALUES(m_user_id,m_phone,m_equipment,m_operate_ip,UNIX_TIMESTAMP(),m_terminal);
            IF (EXISTS(select 1 from d_user_login_count where user_id = m_user_id)) THEN
             UPDATE d_user_login_count SET  
                login_times=(login_times+1),
                mobile_times=(mobile_times + v_m),
                wechat_times=(wechat_times + v_wx),
                pc_times= (pc_times + v_pc),
                last_login_ip= m_operate_ip,
                last_login_datetime= UNIX_TIMESTAMP()
                WHERE user_id=m_user_id;
                ELSE
                insert into d_user_login_count(user_id,phone,login_times,mobile_times,wechat_times,pc_times,last_login_ip,last_login_datetime) VALUES(m_user_id,m_phone,1,v_m,v_wx,v_pc,m_operate_ip,UNIX_TIMESTAMP());
               END IF;
        SET result= ROW_COUNT();
             IF t_error = 1 or result<1 THEN
                     ROLLBACK;		
          SET result= ROW_COUNT();
             ELSE
                     COMMIT; 
             END IF;
        SELECT result;
END;  
