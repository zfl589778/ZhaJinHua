package model
{
	[Bindable]
	public class Room
	{
		private var _type:int;
		private var _number:String;
		private var _name:String;
		private var _password:String;
		private var _basePoint:int;
		private var _maxOrderLimit:int;
		private var _maxRoundLimit:int;
		private var _count:int;
		private var _totalCount:int;
		
		public function Room()
		{
		}

		public function get totalCount():int
		{
			return _totalCount;
		}

		public function set totalCount(value:int):void
		{
			_totalCount = value;
		}

		public function get count():int
		{
			return _count;
		}

		public function set count(value:int):void
		{
			_count = value;
		}

		public function get maxRoundLimit():int
		{
			return _maxRoundLimit;
		}

		public function set maxRoundLimit(value:int):void
		{
			_maxRoundLimit = value;
		}

		public function get maxOrderLimit():int
		{
			return _maxOrderLimit;
		}

		public function set maxOrderLimit(value:int):void
		{
			_maxOrderLimit = value;
		}

		public function get basePoint():int
		{
			return _basePoint;
		}

		public function set basePoint(value:int):void
		{
			_basePoint = value;
		}

		public function get password():String
		{
			return _password;
		}

		public function set password(value:String):void
		{
			_password = value;
		}

		public function get name():String
		{
			return _name;
		}

		public function set name(value:String):void
		{
			_name = value;
		}

		public function get number():String
		{
			return _number;
		}

		public function set number(value:String):void
		{
			_number = value;
		}

		public function get type():int
		{
			return _type;
		}

		public function set type(value:int):void
		{
			_type = value;
		}

	}
}