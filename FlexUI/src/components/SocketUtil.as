package components
{
	import flash.events.Event;
	import flash.events.ProgressEvent;
	import flash.net.Socket;

	public class SocketUtil
	{
		private static var _socket:Socket = new Socket();
		
		public static function connect(host:String,port:int,dataCall:Function=null,connectCall:Function=null,closeCall:Function=null):void{
			if(!_socket.connected){
				_socket.addEventListener(Event.CLOSE,closeCall); 
				_socket.addEventListener(Event.CONNECT,connectCall); 
				_socket.addEventListener(ProgressEvent.SOCKET_DATA, dataCall); 
				_socket.connect(host,port);
			}
		}
		
		public static function sendCommand(body:Object):void{
			sendStr(JSON.stringify(body));
		}
		
		public static function sendStr(msg:String):void{
			if(_socket.connected){
				trace("send data:"+msg);
				_socket.writeUTFBytes(msg);
				_socket.flush();
			}
		}
		
		public static function readCommand():Object{
			var obj:Object = null;
			if(_socket.connected){
				var msg:String = _socket.readUTFBytes(_socket.bytesAvailable); 
				trace("receive data : " + msg);
				// 沙箱处理 
				if(msg!=null){
					if(msg.indexOf("<?xml version=\"1.0\"?>") == -1){
						obj = JSON.parse(msg);
					}
				}
			}
			return obj;
		}
		
		public function SocketUtil(singletonFlag:SingletonFlag){}
	}
}
class SingletonFlag{}
