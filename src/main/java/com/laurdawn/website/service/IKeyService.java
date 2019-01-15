package com.laurdawn.website.service;

public interface IKeyService {

	String dataEncrypt(String data);
	
	String dataDecrypt(String pubKey);

	String getPubKey(int id);
	
}
