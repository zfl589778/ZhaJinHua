package events
{
	import flash.events.Event;

	public class RegEvent extends Event
	{
		public static const REG:String = "reg";
		
		public var loginName:String; 
		public var password:String;
		public var nickname:String;
		public var gender:int;
		public var devCode:String;
		
		public function RegEvent(type:String, loginName:String,password:String,nickname:String,gender:int,devCode:String)
		{
			super(type,true);
			this.loginName = loginName;
			this.password = password;
			this.nickname = nickname;
			this.gender = gender;
			this.devCode = devCode;
		}
		override public function clone():Event {
			return new RegEvent(type, loginName,password,nickname,gender,devCode);
		}
		
	}
}