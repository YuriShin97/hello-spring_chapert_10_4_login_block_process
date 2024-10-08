package com.ktdsuniversity.edu.hellospringhomework.common.vo;

public class StoreResultVO {

	private String originFileName;
	private String obfuscatedFileName;
	
	public StoreResultVO(String originFileName, String obfuscatedFileName) {
		this.originFileName = originFileName;
		this.obfuscatedFileName = obfuscatedFileName;
	}
	
	public String getOriginFileName() {
		return originFileName;
	}

	public String getObfuscatedFileName() {
		return obfuscatedFileName;
	}

	
	
}
