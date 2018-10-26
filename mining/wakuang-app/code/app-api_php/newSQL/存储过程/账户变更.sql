DROP PROCEDURE IF EXISTS sp_update_account;
CREATE  PROCEDURE `sp_update_account`(m_userID 			INT(11),
            m_bustype 		varchar(50),
            m_orderNo 		varchar(50),
            m_available  	decimal(15,6),
            m_unavailable decimal(15,6),
            m_trade       decimal(15,6),
            m_remark 			varchar(200))
    COMMENT '账户变更操作'
BEGIN
    DECLARE t_error INTEGER DEFAULT 0;
    DECLARE result INTEGER DEFAULT 0;
    DECLARE CONTINUE HANDLER FOR SQLEXCEPTION SET t_error=1;
		START TRANSACTION;
      insert into d_account_detail(user_id,`status`,bus_type,order_no,available,unavailable,trade,add_datetime,remark)
                  select A.user_id,1,m_bustype,m_orderNo,(A.available+m_available),(A.unavailable+m_unavailable),
                  m_trade,UNIX_TIMESTAMP(),m_remark
                  from  d_account A where A.user_id =m_userID;
			IF ROW_COUNT()>0 THEN
			 UPDATE d_account SET
					available=(available+m_available),
					unavailable=(unavailable+m_unavailable),
					edit_datetime=UNIX_TIMESTAMP()
					WHERE user_id=m_userID;
			END IF;
   SET result= ROW_COUNT();
	IF t_error = 1 or result<1 THEN
			ROLLBACK;
     SET result= ROW_COUNT();
	ELSE
		COMMIT;
	END IF;
  SELECT result;
END