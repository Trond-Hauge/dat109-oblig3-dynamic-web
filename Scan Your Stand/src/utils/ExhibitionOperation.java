package utils;

public enum ExhibitionOperation {
	
	ADD {
		@Override
		public String toString() {
			return "Legg til prosjekt";
		}
	},
		
	REMOVE {
		@Override
		public String toString() {
			return "Slett til prosjekt";
		}
	},
			
	UPDATE_START {
		@Override
		public String toString() {
			return "Oppdater starttidspunkt";
		}
	},
	
	UPDATE_STOP {
		@Override
		public String toString() {
			return "Oppdater sluttidspunkt";
		}
	},
	
	IMPORT {
		@Override
		public String toString() {
			return "Importer prosjekter";
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

