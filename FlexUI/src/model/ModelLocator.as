package model
{
	import mx.collections.ArrayCollection;
	
	import views.gameView.GameBg;
	import views.lobbyView.LobbyBg;
	import views.loginView.LoginBg;
	import views.typeView.TypeBg;

	[Bindable]
	public class ModelLocator
	{
		private static var _instance:ModelLocator;
		public function ModelLocator(singletonFlag:SingletonFlag){}
		public static function getInstance():ModelLocator
		{
			if(_instance == null){
				_instance = new ModelLocator(new SingletonFlag());
			}
			return _instance;
		}
		
		public var loginBg:LoginBg;
		public var typeBg:TypeBg;
		public var lobbyBg:LobbyBg;
		public var gameBg:GameBg;
		
		public var user:User = new User();
		public var room:Room = new Room();
		
		public var rooms:ArrayCollection = new ArrayCollection();

	}
}
class SingletonFlag{}