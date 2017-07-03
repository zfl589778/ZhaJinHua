package components
{
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;

	public class HTTPServiceUtil
	{
		private static var _http:HTTPService = new HTTPService();
		
		public static function send(url:String,params:Object=null,successCall:Function=null,errorCall:Function=null):void{
			_http.url = url;
			_http.send(params);
			_http.addEventListener(ResultEvent.RESULT,successCall);
			_http.addEventListener(FaultEvent.FAULT,errorCall);
		}
		
		public function HTTPServiceUtil(singletonFlag:SingletonFlag){}
	}
}
class SingletonFlag{}