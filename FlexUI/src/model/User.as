package model
{
	[Bindable]
	public class User
	{
		private var _id:Number;
		private var _loginName:String;
		private var _nickname:String;
		private var _avatarUrl:String;
		private var _remainPoint:Number;
		private var _expPoint:Number;
		private var _level:Number;
		private var _title:String;
		private var _status:Number;
		private var _devCode:String;
		private var _isVisitor:Number;
		private var _sessionId:String;
		
		public function User()
		{
		}

		public function get sessionId():String
		{
			return _sessionId;
		}

		public function set sessionId(value:String):void
		{
			_sessionId = value;
		}

		public function get isVisitor():Number
		{
			return _isVisitor;
		}

		public function set isVisitor(value:Number):void
		{
			_isVisitor = value;
		}

		public function get devCode():String
		{
			return _devCode;
		}

		public function set devCode(value:String):void
		{
			_devCode = value;
		}

		public function get status():Number
		{
			return _status;
		}

		public function set status(value:Number):void
		{
			_status = value;
		}

		public function get title():String
		{
			return _title;
		}

		public function set title(value:String):void
		{
			_title = value;
		}

		public function get level():Number
		{
			return _level;
		}

		public function set level(value:Number):void
		{
			_level = value;
		}

		public function get expPoint():Number
		{
			return _expPoint;
		}

		public function set expPoint(value:Number):void
		{
			_expPoint = value;
		}

		public function get remainPoint():Number
		{
			return _remainPoint;
		}

		public function set remainPoint(value:Number):void
		{
			_remainPoint = value;
		}

		public function get avatarUrl():String
		{
			return _avatarUrl;
		}

		public function set avatarUrl(value:String):void
		{
			_avatarUrl = value;
		}

		public function get nickname():String
		{
			return _nickname;
		}

		public function set nickname(value:String):void
		{
			_nickname = value;
		}

		public function get loginName():String
		{
			return _loginName;
		}

		public function set loginName(value:String):void
		{
			_loginName = value;
		}

		public function get id():Number
		{
			return _id;
		}

		public function set id(value:Number):void
		{
			_id = value;
		}

	}
}