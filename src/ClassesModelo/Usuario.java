/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesModelo;

import javax.swing.JOptionPane;
import Interfaces.*;

/**
 *
 * @author Lucas
 */
public class Usuario implements organizador {

    private String nomeCompleto;
    private String dataNascimento;
    private String cpf;
    private String rg;
    private String telResidencial;
    private String telCelular;
    private String email;
    private boolean professor;
    private int nivel;
    private String senha;
    private int ativo;
    private int codigo;
    private String senhaNova;

    public Usuario() {
        this.nomeCompleto = "";
        this.dataNascimento = "";
        this.cpf = "";
        this.rg = "";
        this.telResidencial = "";
        this.telCelular = "";
        this.email = "";
        this.professor = false;
        this.nivel = 2;
        this.senha = "";
        this.ativo = 0;
    }

    public Usuario(String nomeCompleto, int codigo) {
        this.nomeCompleto = nomeCompleto;
        this.codigo = codigo;
    }

    public Usuario(String nomeCompleto, String dataNascimento, String cpf, String rg, String telResidencial, String telCelular, String email, boolean professor, int nivel, String senha, int ativo, int codigo) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.telResidencial = telResidencial;
        this.telCelular = telCelular;
        this.email = email;
        this.professor = professor;
        this.nivel = nivel;
        this.senha = senha;
        this.ativo = ativo;
        this.codigo = codigo;
    }

    public Usuario(String nomeCompleto, String dataNascimento, String cpf, String rg, String telResidencial, String telCelular, String email) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.rg = rg;
        this.telResidencial = telResidencial;
        this.telCelular = telCelular;
        this.email = email;
    }

    public String getSenhaNova() {
        return senhaNova;
    }

    public void setSenhaNova(String senhaNova) {
        this.senhaNova = senhaNova;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getTelResidencial() {
        return telResidencial;
    }

    public void setTelResidencial(String telResidencial) {
        this.telResidencial = telResidencial;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isProfessor() {
        return professor;
    }

    public void setProfessor(boolean professor) {
        this.professor = professor;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    @Override
    public void testaClasse() {
        JOptionPane.showMessageDialog(null, ""
                + getCpf() + "\n" + getDataNascimento() + "\n" + getEmail() + "\n" + getNomeCompleto() + "\n"
                + getRg() + "\n" + getTelCelular() + "\n" + getTelResidencial() + "\n" + isProfessor() + "\n"
                + getAtivo() + "\n" + getNivel() + "\n" + getSenha());
    }

    @Override
    public void limpar() {
        this.nomeCompleto = "";
        this.dataNascimento = "";
        this.cpf = "";
        this.rg = "";
        this.telResidencial = "";
        this.telCelular = "";
        this.email = "";
        this.professor = false;
        this.nivel = 0;
        this.senha = "";
        this.ativo = 0;
        this.codigo = 0;
    }
}
