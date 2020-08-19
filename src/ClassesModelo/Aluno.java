/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassesModelo;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import Interfaces.*;

/**
 *
 * @author Lucas
 */
public class Aluno implements organizador{

    private String ra;
    private int numeroChamada;
    private String dataNascimento;
    private String telResidencial;
    private String cidade;
    private String nomeResponsavel1;
    private String nomeResponsavel2;
    private int curso;
    private int turma;
    private String nomeCompleto;
    private int sexo;
    private String telCelular;
    private String cep;
    private String endereco;
    private int codigo;
    private ArrayList<String> cursosCadastrados = new ArrayList<>();

    public Aluno() {
        this.ra = "";
        this.numeroChamada = 0;
        this.dataNascimento = "";
        this.telResidencial = "";
        this.cidade = "";
        this.nomeResponsavel1 = "";
        this.nomeResponsavel2 = "";
        this.curso = 0;
        this.turma = 0;
        this.nomeCompleto = "";
        this.sexo = 0;
        this.telCelular = "";
        this.cep = "";
        this.endereco = "";
    }

    public Aluno(String ra, int numeroChamada, String dataNascimento, String telResidencial, String cidade, String nomeResponsavel1, String nomeResponsavel2, String nomeCompleto, int sexo, String telCelular, String cep, String endereco,int codigo) {
        this.ra = ra;
        this.numeroChamada = numeroChamada;
        this.dataNascimento = dataNascimento;
        this.telResidencial = telResidencial;
        this.cidade = cidade;
        this.nomeResponsavel1 = nomeResponsavel1;
        this.nomeResponsavel2 = nomeResponsavel2;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.telCelular = telCelular;
        this.cep = cep;
        this.endereco = endereco;
        this.codigo = codigo;
    }

    public Aluno(String ra, int numeroChamada, String dataNascimento, String telResidencial, String cidade, String nomeResponsavel1, String nomeResponsavel2, int curso, int turma, String nomeCompleto, int sexo, String telCelular, String cep, String endereco) {
        this.ra = ra;
        this.numeroChamada = numeroChamada;
        this.dataNascimento = dataNascimento;
        this.telResidencial = telResidencial;
        this.cidade = cidade;
        this.nomeResponsavel1 = nomeResponsavel1;
        this.nomeResponsavel2 = nomeResponsavel2;
        this.curso = curso;
        this.turma = turma;
        this.nomeCompleto = nomeCompleto;
        this.sexo = sexo;
        this.telCelular = telCelular;
        this.cep = cep;
        this.endereco = endereco;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public String getRa() {
        return ra;
    }

    public void setRa(String ra) {
        this.ra = ra;
    }

    public int getNumeroChamada() {
        return numeroChamada;
    }

    public void setNumeroChamada(int numeroChamada) {
        this.numeroChamada = numeroChamada;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelResidencial() {
        return telResidencial;
    }

    public void setTelResidencial(String telResidencial) {
        this.telResidencial = telResidencial;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getNomeResponsavel1() {
        return nomeResponsavel1;
    }

    public void setNomeResponsavel1(String nomeResponsavel1) {
        this.nomeResponsavel1 = nomeResponsavel1;
    }

    public String getNomeResponsavel2() {
        return nomeResponsavel2;
    }

    public void setNomeResponsavel2(String nomeResponsavel2) {
        this.nomeResponsavel2 = nomeResponsavel2;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getTelCelular() {
        return telCelular;
    }

    public void setTelCelular(String telCelular) {
        this.telCelular = telCelular;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ArrayList<String> getCursosCadastrados() {
        return cursosCadastrados;
    }

    public void setCursosCadastrados(ArrayList<String> cursosCadastrados) {
        this.cursosCadastrados = cursosCadastrados;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    @Override
    public void testaClasse() {
        JOptionPane.showMessageDialog(null, ""
                + getRa() + "\n"
                + getNumeroChamada() + "\n"
                + getDataNascimento() + "\n"
                + getTelResidencial() + "\n"
                + getCidade() + "\n"
                + getNomeResponsavel1() + "\n"
                + getNomeResponsavel2() + "\n"
                + getCurso() + "\n"
                + getTurma() + "\n"
                + getSexo() + "\n"
                + getTelCelular() + "\n"
                + getCep() + "\n"
                + getEndereco());
    }

   @Override
    public void limpar() {
        this.ra = "";
        this.numeroChamada = 0;
        this.dataNascimento = "";
        this.telResidencial = "";
        this.cidade = "";
        this.nomeResponsavel1 = "";
        this.nomeResponsavel2 = "";
        this.curso = 0;
        this.turma = 0;
        this.nomeCompleto = "";
        this.sexo = 0;
        this.telCelular = "";
        this.cep = "";
        this.endereco = "";
    }
}
