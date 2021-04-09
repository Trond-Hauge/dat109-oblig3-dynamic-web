package utils;

public enum AdminOperation {
	
	START {
		@Override
		public String toString() {
			return "Start avstemming";
		}
	},
	
	STOP {
		@Override
		public String toString() {
			return "Stopp avstemming";
		}
	},
	
	ADMINISTRATE {
		@Override
		public String toString() {
			return "Administrer utstilling";
		}
	};
	
	public static AdminOperation getOperation(String operationStr) {
		
		for(AdminOperation o : AdminOperation.values()) {
			if(o.toString().equalsIgnoreCase(operationStr))
				return o;
		}
		
		return null;
		
	}

}
