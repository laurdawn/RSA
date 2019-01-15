package com.laurdawn.website.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.laurdawn.website.dao.RsaDao;
import com.laurdawn.website.entity.RSA;
import com.laurdawn.website.exception.TipException;
import com.laurdawn.website.service.IKeyService;
import com.laurdawn.website.utils.Base64;
import com.laurdawn.website.utils.RSAEncrypt;

@Service
public class KeyServiceImpl implements IKeyService {
	
    private static final Logger logging = LoggerFactory.getLogger(KeyServiceImpl.class);

    @Resource
    private RsaDao rsaDao;

    /**
     * 私钥加密数据
     */
	@Override
	public String dataEncrypt(String data) {
		Map<String, String> key = RSAEncrypt.genKeyPair();
		byte[] cipherData;
		try {
			cipherData = RSAEncrypt.encrypt(RSAEncrypt.loadPrivateKeyByStr(key.get("privateKeyString")), data.getBytes());
		} catch (Exception e) {
			logging.error(e.getMessage());
			throw new TipException(e.getMessage());
		}  
        String cipher=Base64.encode(cipherData);
        RSA rsa = new RSA();
        rsa.setPublicKey(key.get("publicKeyString"));
        rsa.setData(cipher);
        rsaDao.insertEntity(rsa);
        try {
			return URLEncoder.encode(key.get("publicKeyString"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 公钥解密数据
	 */
	@Override
	public String dataDecrypt(String pubKey) {
		String cipher = rsaDao.selectByPubKey(pubKey);
		byte[] res;
		try {
			res = RSAEncrypt.decrypt(RSAEncrypt.loadPublicKeyByStr(pubKey), Base64.decode(cipher));
		} catch (Exception e) {
			logging.error(e.getMessage());
			throw new TipException(e.getMessage());
		}  
		return new String(res);
	}
	
	/**
	 * 通过id获取公钥
	 * @param id
	 * @return
	 */
	@Override
	public String getPubKey(int id) {
		try {
			return URLEncoder.encode(rsaDao.selectById(id).getPublicKey(), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "编码错误，不支持的编码类型";
		}
	}

}
