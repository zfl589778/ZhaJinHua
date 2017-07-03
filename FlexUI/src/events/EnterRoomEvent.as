package events
{
	import flash.events.Event;

	public class EnterRoomEvent extends Event
	{
		public static const ENTER_ROOM:String = "enterRoom";
		
		public var roomNumber:String; 
		public var roomPassword:String;
		
		public function EnterRoomEvent(type:String, roomNumber:String,roomPassword:String)
		{
			super(type,true);
			this.roomNumber = roomNumber;
			this.roomPassword = roomPassword;
		}
		override public function clone():Event {
			return new EnterRoomEvent(type, roomNumber,roomPassword);
		}
	}
}