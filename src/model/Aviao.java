package model;

@DatabaseTable(tableName = "aviao")
public class Aviao {

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(canBeNull = false)
    private String modelo;Da

	@DatabaseField(canBeNull=false)
	private int fileira;

	@DatabaseField(canBeNull = false)
	private int assentos;

	// Construtores
	public Aviao() {
	}

	public Aviao(String modelo, int fileira, int assentos) {
		this.modelo = modelo;
		this.fileira = fileira;
		this.assentos = assentos;
	}

	public Aviao(int id, String modelo, int fileira, int assentos) {
		this.id = id;
		this.modelo = modelo;
		this.fileira = fileira;
		this.assentos = assentos;
	}

	// Getters
	public int getId() {
		return id;
	}

	public String getModelo() {
		return modelo;
	}

	public int getFileira() {
		return fileira;
	}

	public int getAssentos() {
		return assentos;
	}

	// Setters
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setFileira(int fileira) {
		this.fileira = fileira;
	}

	public void setAssentos(int assentos) {
		this.assentos = assentos;
	}

	@Override
	public String toString() {
		return "Aviao{id=" + id + ", modelo='" + modelo + "', fileira=" + fileira + ", assentos=" + assentos + "}";
	}
}