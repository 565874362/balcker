package com.joymates.common.http;

public interface ConstantsHttpCode {
	
	
	//200Code
	public final static int RESPONSECODE_200 = 200;		//请求成功
	public final static int RESPONSECODE_202 = 202;		////由于和被请求的资源的当前状态之间存在冲突，请求无法完成（已存在）
	public final static int RESPONSECODE_204 = 204;		//请求成功，不返回任何东西
	public final static int RESPONSECODE_207 = 207;		//如果请求的资源已永久删除，服务器就会返回此响应。（不存在）
	public final static int RESPONSECODE_400 = 400;     //请求中有语法问题，或不能满足请求
	public final static int RESPONSECODE_401 = 401; 	//没有证书
	public final static int RESPONSECODE_402 = 402; 	//没有登陆权限
	public final static int RESPONSECODE_403 = 403;		//证书过期
	public final static int RESPONSECODE_404 = 404;		//（未找到） 服务器找不到请求的网页。
	public final static int RESPONSECODE_407 = 407;		//错误证书
	public final static int RESPONSECODE_415 = 415;		//请求中提交的实体并不是服务器中所支持的格式
	public final static int RESPONSECODE_422 = 422;		//请求格式正确，但是由于含有语义错误（参数值错误）
	public final static int RESPONSECODE_423 = 423;		//当前资源被锁定
	
	
	public final static int RESPONSECODE_500 = 500;		//（500   （服务器内部错误）  服务器遇到错误，无法完成请求。
	public final static int RESPONSECODE_503 = 503;		//503   （服务不可用） 服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。
	public final static int RESPONSECODE_504 = 504;		//504   （网关超时）  服务器作为网关或代理，但是没有及时从上游服务器收到请求。 
	
	//请求成功解析Map中的code
	public final static String STATUSCODE_200 = "200";
	public final static String STATUSCODE_204 = "204";
	public final static String STATUSCODE_202 = "202";
	public final static String STATUSCODE_207 = "207";
	public final static String STATUSCODE_400 = "400";
	public final static String STATUSCODE_401 = "401";
	public final static String STATUSCODE_402 = "402";
	public final static String STATUSCODE_403 = "403";
	public final static String STATUSCODE_404 = "404";
	public final static String STATUSCODE_407 = "407";
	public final static String STATUSCODE_415 = "415";
	public final static String STATUSCODE_422 = "422";
	public final static String STATUSCODE_423 = "423";
	public final static String STATUSCODE_500 = "500";
	public final static String STATUSCODE_503 = "503";
	public final static String STATUSCODE_504 = "504";
	public final static String STATUSCODE_610 = "610";
	public final static String STATUSCODE_612 = "612";

	
	public final static String SERVERESPONSEMESSAGE_400 = "400";
	public final static String SERVERESPONSEMESSAGE_401 = "401";
	public final static String SERVERESPONSEMESSAGE_402 = "402";
	public final static String SERVERESPONSEMESSAGE_403 = "403";
	public final static String SERVERESPONSEMESSAGE_404 = "404";
	public final static String SERVERESPONSEMESSAGE_407 = "407";
	public final static String SERVERESPONSEMESSAGE_415 = "415";
	public final static String SERVERESPONSEMESSAGE_422 = "422";
	public final static String SERVERESPONSEMESSAGE_423 = "423";
	
	public final static String SERVERESPONSEMESSAGE_500 = "500";
	public final static String SERVERESPONSEMESSAGE_503 = "503";
	public final static String SERVERESPONSEMESSAGE_504 = "504";
	
}
