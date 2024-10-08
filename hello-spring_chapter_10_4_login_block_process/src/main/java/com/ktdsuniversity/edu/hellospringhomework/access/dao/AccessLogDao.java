package com.ktdsuniversity.edu.hellospringhomework.access.dao;

import com.ktdsuniversity.edu.hellospringhomework.access.vo.AccessLogVO;

public interface AccessLogDao {
	
	public String NAMESPACE = "com.ktdsuniversity.edu.hellospringhomework.access.dao.AccessLogDao";

	public int insertNewAccessLog(AccessLogVO accessLogVO);
	
	public int selectLoginFailCount(String ip);
	
}
