
package merccontrol;

public class Cliente {
    private String id;
    private String nome;
    private String CPF;
    private String endereco;

    public Cliente(String id, String nome, String CPF, String endereco) {
        this.id = id;
        this.nome = nome;
        this.CPF = CPF;
        this.endereco = endereco;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
    
    
}
