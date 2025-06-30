public class Usuario {
    private int id;
    private String nome;
    private String cpf;
    private int emprestimosAtivos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getEmprestimosAtivos() {
        return emprestimosAtivos;
    }

    public void setEmprestimosAtivos(int emprestimos_ativos) {
        this.emprestimosAtivos = emprestimos_ativos;
    }

    @Override
    public String toString() {
        return getId() + " " + getNome() + " " + getCpf();
    }
}
