package emuns;

public enum TipoEnum {
	
	CLIENTE("002"),
	VENDEDOR("001"),
	VENDA("003");
	
	private TipoEnum(String cod) {
		this.cod = cod;
	}
	
	private String cod;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}	
	
	
}
