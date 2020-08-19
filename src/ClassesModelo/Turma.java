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
public class Turma implements organizador {

    private int curso;
    private String nomeCurso;
    private int modulo;
    private String periodo;
    private String sigla;
    private String sala;
    private String ano;
    private String semestre;
    private int codigo;
    private int ativo;

    public Turma() {
        this.curso = 0;
        this.modulo = 0;
        this.periodo = "";
        this.sigla = "";
        this.sala = "";
        this.ano = "";
        this.semestre = "";
    }

    public Turma(String sigla, int codigo) {
        this.sigla = sigla;
        this.codigo = codigo;
    }

    public Turma(int curso, int modulo, String periodo, String sigla, String sala, String ano, String semestre, int codigo, int ativo, String nomeCurso) {
        this.curso = curso;
        this.modulo = modulo;
        this.periodo = periodo;
        this.sigla = sigla;
        this.sala = sala;
        this.ano = ano;
        this.semestre = semestre;
        this.codigo = codigo;
        this.ativo = ativo;
        this.nomeCurso = nomeCurso;
    }


    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    @Override
    public void testaClasse() {
        JOptionPane.showMessageDialog(null, getCurso() + "\n"
                + getModulo() + "\n"
                + getPeriodo() + "\n"
                + getSigla() + "\n"
                + getSala() + "\n"
                + getAno() + "\n"
                + getSemestre());
    }

    @Override
    public void limpar() {
        this.curso = 0;
        this.modulo = 0;
        this.periodo = "";
        this.sigla = "";
        this.sala = "";
        this.ano = "";
        this.semestre = "";
        this.codigo = 0;
        this.ativo = 0;
        this.nomeCurso = "";
    }
}
