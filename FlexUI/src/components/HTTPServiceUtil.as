package components
{
	import mx.controls.Alert;
	import mx.rpc.events.FaultEvent;
	import mx.rpc.events.ResultEvent;
	import mx.rpc.http.HTTPService;

	public class HTTPServiceUtil
	{
		public static const URL:String = "http://127.0.0.1:8800/service";
		
		private static var _http:HTTPService = new HTTPService();
		
		public static function send(url:String,params:Object,successCall:Function):void{
			if(_http==null){
				_http = new HTTPService();
			}
			_http.method = "POST";
			_http.url = url;
			_http.send(params);
			_http.addEventListener(ResultEvent.RESULT,successCall);
			_http.addEventListener(FaultEvent.FAULT,faultHandler);
		}
		
		protected static function faultHandler(event:FaultEvent):void
		{
			trace("服务调用异常："+event.message);
			Alert.show("服务调用异常："+event.message);
		}
		
		public function HTTPServiceUtil(singletonFlag:SingletonFlag){}
	}
}
class SingletonFlag{}