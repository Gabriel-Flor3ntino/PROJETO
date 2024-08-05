package model;

@DatabaseTable(tableName = "voo")
public class Voo {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private int numeroVoo;

    @DatabaseField(canBeNull = false)
    private String data;

    @DatabaseField(canBeNull = false)
    private String hora;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private Aviao aeronave;

    // Construtores
    public Voo() {}

    public Voo(Aviao aeronave, int numeroVoo, String data, String hora) {
        this.aeronave = aeronave;
        this.numeroVoo = numeroVoo;
        this.data = data;
        this.hora = hora;
    }

    public Voo(int id, Aviao aeronave, int numeroVoo, String data, String hora) {
        this.id = id;
        this.aeronave = aeronave;
        this.numeroVoo = numeroVoo;
        this.data = data;
        this.hora = hora;
    }

    // Getters
    public int getId() { return id; }
    public int getNumeroVoo() { return numeroVoo; }
    public String getData() { return data; }
    public String getHora() { return hora; }
    public Aviao getAeronave() { return aeronave; }

    // Setters
    public void setNumeroVoo(int numeroVoo) { this.numeroVoo = numeroVoo; }
    public void setData(String data) { this.data = data; }
    public void setHora(String hora) { this.hora = hora; }
    public void setAeronave(Aviao aeronave) { this.aeronave = aeronave; }

    @Override
    public String toString() {
        return "Voo{id=" + id + ", numeroVoo=" + numeroVoo + ", data='" + data + "', hora='" + hora + "', aeronave=" + aeronave + "}";
    }
}