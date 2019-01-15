package com.laurdawn.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laurdawn.website.service.IKeyService;

/** 
* @author  laurdawn 
* @version 2019年1月11日 下午4:31:24 
*/
@RestController
public class RsaController {
	
	@Autowired
	private IKeyService keyService;

	/**
	 * 加密
	 * @return
	 */
    @RequestMapping("/encrypt")
    private String encrypt(String data) {
    	return keyService.dataEncrypt(data);
    }
    
    /**
     * 解密
     * @return
     */
    @RequestMapping("/decrypt")
    private String decrypt(String pubKey) {
    	return keyService.dataDecrypt(pubKey);
    }
    
    /**
     * 通过id获取公钥
     * @param id
     * @return
     */
    @RequestMapping("/getPubkey")
    private String getPubkey(int id) {
    	return keyService.getPubKey(id);
    }
	
}
