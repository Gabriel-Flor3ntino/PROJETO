package model;


@DatabaseTable(tableName = "passageiro")
public class Passageiro {
    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String nome;

    @DatabaseField(canBeNull = false, unique = true)
    private String cpf;

    // Construtores
    public Passageiro() {}

    public Passageiro(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public Passageiro(int id, String nome, String cpf) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
    }

    // Getters
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }

    // Setters
    public void setNome(String nome) { this.nome = nome; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    @Override
    public String toString() {
        return "Passageiro{id=" + id + ", nome='" + nome + "', cpf='" + cpf + "'}";
    }
}