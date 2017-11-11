package br.com.rr.sociotorcedor.enumeration;

public enum Time {
	
	CORINTHIANS(1l),
	PALMEIRAS(2l),
	SAO_PAULO(3l),
	SANTOS(4l);

	final private Long id;
	
	private Time(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}
	
	public static Time findById(Long id) {
		Time[] times = Time.values();
		
		for(Time t : times) {
			if(t.getId().equals(id)) {
				return t;
			}
		}
		
		return null;
	}
}
