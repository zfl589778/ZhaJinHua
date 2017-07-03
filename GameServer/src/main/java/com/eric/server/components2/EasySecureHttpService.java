package com.eric.server.components2;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

//import okhttp3.FormBody;
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.RequestBody;
//import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class EasySecureHttpService {
	
//	private static final Logger logger = LoggerFactory.getLogger(EasySecureHttpService.class);
//	public Map<String, EasySecureHttp> easySecureHttpMap = new HashMap<String, EasySecureHttp>();
//	
//	private static final EasySecureHttpService instance = new EasySecureHttpService();
//	public static EasySecureHttpService getInstance(){return instance;}
//	
//	public void init(){
//		List<TApp> appList = BeanFactoryUtil.getBean(AppService.class).loadAll();
//		for(TApp app : appList){
//			if(app.getAppStatus() == null || app.getAppStatus().intValue() != 1)continue;
//			easySecureHttpMap.put(app.getAppId(), new EasySecureHttp(app.getAppId(), app.getPrivateKey(), app.getCpPublicKey(), app.getEncryType(), app.getAppKey()));
//		}
//		
//		
//		//对于app为第三方平台的APPID的处理-会员中心
//		easySecureHttpMap.put(InnerAppIdConstants.TRANSIENT_YB_CENTER, new EasySecureHttp(MainConfig.getYcAppId(), "", "", EasySecureHttp.ENCRY_TYPE_MD5, MainConfig.getYcAppKey()));
//		
//		//对于app为第三方平台的APPID的处理-内部服务器集群（群组服务器|家族服务器|会员中心独立处理服务器）
//		easySecureHttpMap.put(InnerAppIdConstants.CLUSTER_INNER_SERVER, new EasySecureHttp(InnerAppIdConstants.CLUSTER_INNER_SERVER, "", "", EasySecureHttp.ENCRY_TYPE_NONE, MainConfig.getXf100ImageKey()));
//		
//		easySecureHttpMap.put(InnerAppIdConstants.XF100_BG_SERVER, new EasySecureHttp(InnerAppIdConstants.XF100_BG_SERVER, "", "", EasySecureHttp.ENCRY_TYPE_NONE, MainConfig.getXf100BgAppKey()));
//	}
//	
//	public ResultObject secureHttpRequest(String url, String customMerchantKey, TreeMap<String, String> treeMap, boolean isPost){
//		try {
//			return easySecureHttpMap.get(customMerchantKey).sendRequest(url, treeMap, isPost);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("secure request fail[url:{}, customMerchantKey:{}, treeMap:{}, isPost:{}]", url, customMerchantKey, treeMap, isPost);
//			LogServer.error(EasySecureHttpService.class, e);
//			return ResultObjectUtil.fire(-1, "系统故障，请稍候重试");
//		}
//	}
//	
//	public ResultObject httpRequest(String url, TreeMap<String, String> treeMap, boolean isPost){
//		try {
//			return EasySecureHttp._sendFreeRequest(url, treeMap, isPost);
//		} catch (Exception e) {
//			e.printStackTrace();
//			logger.error("http request fail[url:{}, customMerchantKey:{}, treeMap:{}, isPost:{}]", url, treeMap, isPost);
//			LogServer.error(EasySecureHttpService.class, e);
//			return ResultObjectUtil.fire(-1, "系统故障，请稍候重试");
//		}
//	}
//	
//	public ResultObject secureHttpResponse(String customMerchantKey, String data, String rsakey){
//		return easySecureHttpMap.get(customMerchantKey).receiveResponse(data, rsakey);
//	}
//	
//	public String genAesKey(){
//		return EasySecureHttp._random(16);
//	}
//	
//	
//	/**
//	 * 签名方式: RSA/MD5
//	 * 加密方式: ASE[RSA:随机生成秘钥后通过公钥加密 | MD5:秘钥直接通过加密串]
//	 * 不同的签名方式均进行ASE加密后传输
//	 */
//	public static class EasySecureHttp {
//		
//		private static final String DEFAULT_CHAR_SET = "UTF-8";
//		private static final String ENCRY_TYPE_RSA = "RSA";
//		private static final String ENCRY_TYPE_MD5 = "MD5";
//		private static final String ENCRY_TYPE_NONE = "NONE";
//		
//		private static OkHttpClient client = new OkHttpClient().newBuilder()
//		        .connectTimeout(10, TimeUnit.SECONDS)
//		        .writeTimeout(10, TimeUnit.SECONDS)
//		        .readTimeout(20, TimeUnit.SECONDS)
//		        .build();
//		
//		private String merchantId;
//		private String clientPrivateKey;
//		private String serverPublicKey;
//		/**MD5/RSA**/
//		private String encryType;
//		
//		private String md5key;
//		
//		private String SIGN_KEY = "sign";
//		
//		private String RSA_DATA_KEY = "data";
//		private String RSA_APP_ID_KEY = "appId";
//		private String RSA_ENCRY_KEY = "rsakey";
//		
//		private static final Logger logger = LoggerFactory.getLogger(EasySecureHttp.class);
//		
//		public EasySecureHttp(String merchantId, String clientPrivateKey, String serverPublicKey, String encryType, String md5key){
//			this.merchantId = merchantId;
//			this.clientPrivateKey = clientPrivateKey;
//			this.serverPublicKey = serverPublicKey;
//			this.encryType = encryType;
//			this.md5key = md5key;
//		}
//		
//		/********************************************static*************************************************/
//		private static ResultObject _sendFreeRequest(String url, TreeMap<String, String> treeMap, boolean isPost) throws Exception{
//			return _request(url, treeMap, DEFAULT_CHAR_SET, isPost);
//		}
//
//		private static ResultObject _request(String url, TreeMap<String, String> treeMap, String charset, boolean isPost) throws Exception{
//			
//			FormBody.Builder builder = null;
//			Request request = null;
//			if(isPost){
//				builder = new FormBody.Builder();
//				if(treeMap != null && !treeMap.isEmpty()){
//					for(Map.Entry<String, String> entry : treeMap.entrySet()){
//						builder.add(entry.getKey(), entry.getValue());
//					}
//				}
//				RequestBody body = builder.build();
//			    request = new Request.Builder()
//			    		.header("User-Agent", "Mozilla/4.0")
//			            .url(url)
//			            .post(body)
//			            .build();
//			    
//			}else{
//				String linkArgs = "";
//				if(treeMap != null && !treeMap.isEmpty()){
//					StringBuilder links = new StringBuilder();
//					for(Map.Entry<String, String> entry : treeMap.entrySet()){
//						if(!isPost){
//							links.append("&");
//							links.append(entry.getKey());
//							links.append("=");
//							links.append(URLEncoder.encode(entry.getValue(), DEFAULT_CHAR_SET).toString());
//						}
//					}
//					linkArgs = links.delete(0, 1).toString();
//					request = new Request.Builder()
//		    		.header("User-Agent", "Mozilla/4.0")
//		            .url(url.endsWith("?") ? url + linkArgs : url + "?" + linkArgs)
//		            .get()
//		            .build();
//				}
//			}
//			return _requestResponse(client, request, url, isPost);
//		}
//		
//		private static ResultObject _requestResponse(OkHttpClient client, Request request, String url, boolean isPost){
//			Response response = null;
//			try {
//				response = client.newCall(request).execute();
//			} catch (IOException e) {
//				logger.error("[easy-secure-http] request fail[url:{}, isPost:{}, args:{}]", url, isPost);
//				return ResultObjectUtil.fire(-1, "网络请求异常["+e.getMessage()+"]");
//			}
//		    if (!response.isSuccessful()) {
//		        return ResultObjectUtil.fire(-1, "网络请求失败["+response.code()+"]");
//		    }
//			try {
//				return ResultObjectUtil.success(response.body().string());
//			} catch (IOException e) {
//				logger.error("[easy-secure-http] response.body() exception:{}", e);
//				return ResultObjectUtil.fire(-1, "获取响应结果异常");
//			}
//		}
//		
//		/********************************************static end*************************************************/
//		public ResultObject sendRequest(String url, TreeMap<String, String> treeMap, boolean isPost) throws Exception{
//			
//			if(encryType.equals(ENCRY_TYPE_RSA)){
//				return _sendRsaRequest(url, treeMap, isPost);
//			}else if(encryType.equals(ENCRY_TYPE_MD5)){
//				return _sendMd5Request(url, treeMap, isPost);
//			}else if(encryType.equals(ENCRY_TYPE_NONE)){
//				logger.debug("encrypt type only support MD5 or RSA, here is not encrypt");
//				return _sendNoneRequest(url, treeMap, isPost);
//			}else{
//				logger.debug("encrypt type only support MD5 or RSA");
//				return ResultObjectUtil.fire(-1, "encrypt type only support MD5 or RSA or NONE");
//			}
//		}
//		
//		public ResultObject sendRequest(String url, String args, boolean isPost) throws Exception{
//			return sendRequest(url, _reqArgs2Map(args), isPost);
//		}
//		
//		public ResultObject receiveResponse(String data, String rsakey){
//			if(encryType.equals(ENCRY_TYPE_RSA)){
//				return response4Rsa(this.merchantId, data, rsakey);
//			}else if(encryType.equals(ENCRY_TYPE_MD5)){
//				return response4Md5(this.merchantId, data);
//			}else if(encryType.equals(ENCRY_TYPE_NONE)){
//				logger.debug("encrypt type only support MD5 or RSA, here is not encrypt");
//				return response4None(this.merchantId, data);
//			}else{
//				logger.debug("encrypt type only support MD5 or RSA");
//				return ResultObjectUtil.fire(-1, "encrypt type only support MD5 or RSA or NONE");
//			}
//		}
//		
//		private ResultObject _sendRsaRequest(String url, TreeMap<String, String> treeMap, boolean isPost) throws Exception{
//			String AESKey = _random(16);
//			String encryptkey = _buildEncryptkey(AESKey, serverPublicKey);
//			String data = _buildData4Rsa(treeMap, AESKey, merchantId, clientPrivateKey);
//			
//			TreeMap<String, String> paramMap = new TreeMap<String, String>();
//			paramMap.put(RSA_DATA_KEY, data);
//			paramMap.put(RSA_ENCRY_KEY, encryptkey);
//			paramMap.put(RSA_APP_ID_KEY, merchantId);
//			
//			return _request(url, paramMap, DEFAULT_CHAR_SET, isPost);
//		}
//		
//		private ResultObject _sendMd5Request(String url, TreeMap<String, String> treeMap, boolean isPost) throws Exception{
//			String data = _buildData4Md5(treeMap, this.md5key, merchantId);
//			TreeMap<String, String> paramMap = new TreeMap<String, String>();
//			paramMap.put(RSA_DATA_KEY, data);
//			paramMap.put(RSA_APP_ID_KEY, merchantId);
//			
//			return _request(url, paramMap, DEFAULT_CHAR_SET, isPost);
//		}
//		
//		private ResultObject _sendNoneRequest(String url, TreeMap<String, String> treeMap, boolean isPost) throws Exception{
//			String data = _buildData4None(treeMap, this.md5key, merchantId);
//			TreeMap<String, String> paramMap = new TreeMap<String, String>();
//			paramMap.put(RSA_DATA_KEY, data);
//			paramMap.put(RSA_APP_ID_KEY, merchantId);
//			
//			return _request(url, paramMap, DEFAULT_CHAR_SET, isPost);
//		}
//		
//		private String _buildData4Rsa(TreeMap<String, String> treeMap, String AESKey, String merchantaccount, String merchantPrivateKey) {
//			String data = "";
//			treeMap.put(RSA_APP_ID_KEY, merchantaccount);
//			String sign = _buildSign4Rsa(treeMap, merchantPrivateKey);
//			treeMap.put(SIGN_KEY, sign);
//			String jsonStr = JSON.toJSONString(treeMap);
//			data = AES.encryptToBase64(jsonStr, AESKey);
//			return data;
//		}
//		
//		private String _buildData4Md5(TreeMap<String, String> treeMap, String entryptKey, String merchantaccount) {
//			String data = "";
//			treeMap.put(RSA_APP_ID_KEY, merchantaccount);
//			String sign = _buildSign4Md5(treeMap, entryptKey);
//			treeMap.put(SIGN_KEY, sign);
//			String jsonStr = JSON.toJSONString(treeMap);
//			data = AES.encryptToBase64(jsonStr, entryptKey);
//			return data;
//		}
//		
//		private String _buildData4None(TreeMap<String, String> treeMap, String signKey, String merchantaccount) {
//			treeMap.put(RSA_APP_ID_KEY, merchantaccount);
//			String sign = _buildSign4Md5(treeMap, signKey);
//			treeMap.put(SIGN_KEY, sign);
//			return JSON.toJSONString(treeMap);
//		}
//		
//		private String _buildSign4Rsa(TreeMap<String, String> treeMap,String privateKey) {
//			
//			String sign = "";
//			StringBuffer buffer = new StringBuffer();
//			for (Map.Entry<String, String> entry : treeMap.entrySet()) {
//				buffer.append(entry.getValue());
//			}
//			String signTemp = buffer.toString();
//
//			if (StringUtils.isNotEmpty(privateKey)) {
//				sign = RSA.sign(signTemp, privateKey);
//			}
//			return sign;
//		}
//		
//		private String _buildSign4Md5(TreeMap<String, String> treeMap,String entryptKey) {
//			
//			String sign = "";
//			StringBuffer buffer = new StringBuffer();
//			for (Map.Entry<String, String> entry : treeMap.entrySet()) {
//				buffer.append(entry.getValue());
//			}
//			buffer.append(entryptKey);
//			String signTemp = buffer.toString();
//			
//			if (StringUtils.isNotEmpty(entryptKey)) {
//				sign = MD5.getMD5(signTemp);
//			}
//			return sign;
//		}
//		
//		public static Random random = new Random();
//		public static String _random(int length) {
//			StringBuilder ret = new StringBuilder();
//			for (int i = 0; i < length; i++) {
//				boolean isChar = (random.nextInt(2) % 2 == 0);
//				if (isChar) {
//					int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
//					ret.append((char) (choice + random.nextInt(26)));
//				} else {
//					ret.append(Integer.toString(random.nextInt(10)));
//				}
//			}
//			return ret.toString();
//		}
//		
//		private static String _buildEncryptkey(String AESKey, String publicKey) {
//			String encryptkey	= "";
//			try {
//				encryptkey = RSA.encrypt(AESKey, publicKey);
//			} catch(Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//			return encryptkey;
//		}
//		
//		private static TreeMap<String, String> _reqArgs2Map(String reqArgs){
//			if(StringUtils.isBlank(reqArgs))return null;
//			String[] v1 = reqArgs.split("&");
//			TreeMap<String, String> map = new TreeMap<String, String>();
//			for(String kv : v1){
//				String[] v3 = kv.split("=");
//				map.put(v3[0], v3[1]);
//			}
//			return map;
//		}
//		
//		public ResultObject response4Rsa(String merchantId, String data, String encryptkey){
//			try{
//				TreeMap<String, String> treeMap = _decrypt4Rsa(data, encryptkey, clientPrivateKey);
//				logger.debug("rsa response args is {}", treeMap);
//				
//				if(!_checkSign4Rsa(treeMap, serverPublicKey)) {
//					logger.error("sign check is fail, args is {}", treeMap);
//					return ResultObjectUtil.fire(-1, "sign check fail");
//				}
//				return ResultObjectUtil.success(treeMap);
//			}catch(Exception e){
//				logger.error("rsa response decrypt exception, appid is {}", merchantId);
//				return ResultObjectUtil.fire(-1, "response exception, decrypt or check sign exception");
//			}
//		}
//		
//		public ResultObject response4Md5(String merchantId, String data){
//			try{
//				TreeMap<String, String> treeMap = _decrypt4Md5(data);
//				logger.debug("md5 response args is {}", treeMap);
//				
//				if(!_checkSign4Md5(treeMap, this.md5key)) {
//					logger.error("sign check is fail, args is {}", treeMap);
//					return ResultObjectUtil.fire(-1, "sign check fail");
//				}
//				return ResultObjectUtil.success(treeMap);
//			}catch(Exception e){
//				logger.error("md5 response decrypt exception, appid is {}", merchantId);
//				return ResultObjectUtil.fire(-1, "response exception, decrypt or check sign exception");
//			}
//		}
//		
//		public ResultObject response4None(String merchantId, String data){
//			try{
//				TreeMap<String, String> treeMap = _decrypt4None(data);
//				logger.debug("none response args is {}", treeMap);
//				
//				if(!_checkSign4Md5(treeMap, this.md5key)) {
//					logger.error("sign check is fail, args is {}", treeMap);
//					return ResultObjectUtil.fire(-1, "sign check fail");
//				}
//				return ResultObjectUtil.success(treeMap);
//			}catch(Exception e){
//				logger.error("md5 response decrypt exception, appid is {}", merchantId);
//				return ResultObjectUtil.fire(-1, "response exception, decrypt or check sign exception");
//			}
//		}
//		
//		public TreeMap<String, String> _decrypt4Rsa(String data, String rsakey, String privateKey) {
//			TreeMap<String, String> result	= null;
//			String AESKey 	= "";
//			try {
//				AESKey = RSA.decrypt(rsakey, privateKey);
//			} catch (Exception e) {
//				e.printStackTrace();
//				return null;
//			}
//			String jsonStr = AES.decryptFromBase64(data, AESKey);
//			result = JSON.parseObject(jsonStr,new TypeReference<TreeMap<String, String>>() {});
//			return result;
//		}
//		
//		public TreeMap<String, String> _decrypt4Md5(String data) {
//			TreeMap<String, String> result	= null;
//			String jsonStr = AES.decryptFromBase64(data, this.md5key);
//			result = JSON.parseObject(jsonStr,new TypeReference<TreeMap<String, String>>() {});
//			return result;
//		}
//		
//		public TreeMap<String, String> _decrypt4None(String data) {
//			return JSON.parseObject(data,new TypeReference<TreeMap<String, String>>() {});
//		}
//		
//		public boolean _checkSign4Rsa(TreeMap<String, String> dataMap, String serverPublicKey) {
//			String rsign	= StringUtils.trimToEmpty(dataMap.get(SIGN_KEY));
//			StringBuffer buffer	= new StringBuffer();
//			for(Map.Entry<String, String> entry : dataMap.entrySet()) {
//				String key = _formatStr(entry.getKey());
//				String value = _formatStr(entry.getValue());
//				if(SIGN_KEY.equals(key)) {
//					continue;
//				}
//				buffer.append(value);
//			}
//			return RSA.checkSign(buffer.toString(), rsign, serverPublicKey);
//		}
//		
//		public boolean _checkSign4Md5(TreeMap<String, String> dataMap, String encryptKey) {
//			String rsign	= StringUtils.trimToEmpty(dataMap.get(SIGN_KEY));
//			StringBuffer buffer	= new StringBuffer();
//			for(Map.Entry<String, String> entry : dataMap.entrySet()) {
//				String key = _formatStr(entry.getKey());
//				String value = _formatStr(entry.getValue());
//				if(SIGN_KEY.equals(key)) {
//					continue;
//				}
//				buffer.append(value);
//			}
//			buffer.append(encryptKey);
//			return rsign.equals(MD5.getMD5(buffer.toString()));
//		}
//		
//		public String _formatStr(String text) {
//			return (text == null) ? "" : text.trim();
//		}
//	}
	
	
}
