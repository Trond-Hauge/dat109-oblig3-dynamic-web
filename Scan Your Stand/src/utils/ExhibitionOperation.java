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
			return "Slett prosjekt";
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
	
	public static ExhibitionOperation getOperation(String operationStr) {
		
		for(ExhibitionOperation o : ExhibitionOperation.values()) {
			if(o.toString().equalsIgnoreCase(operationStr))
				return o;
		}
		
		return null;
		
	}
		
}

