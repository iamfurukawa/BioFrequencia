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
public class Horario implements organizador {

    private int ordem;
    private int diaSemana;
    private int cdMat;
    private String nomeMateria;
    private int codigo;
    private String nomeCurso;
    private String nomeTurma;
    private String nomePeriodo;

    public Horario() {
    }

    public Horario(int ordem, int diaSemana, int cdMat, String nomeMateria) {
        this.ordem = ordem;
        this.diaSemana = diaSemana;
        this.cdMat = cdMat;
        this.nomeMateria = nomeMateria;
    }

    public Horario(int ordem, int diaSemana, int cdMat) {
        this.ordem = ordem;
        this.diaSemana = diaSemana;
        this.cdMat = cdMat;
    }

    public Horario(int ordem, int diaSemana, int cdMat, String nomeMateria, int codigo, String nomeCurso, String nomeTurma, String nomePeriodo) {
        this.ordem = ordem;
        this.diaSemana = diaSemana;
        this.cdMat = cdMat;
        this.nomeMateria = nomeMateria;
        this.codigo = codigo;
        this.nomeCurso = nomeCurso;
        this.nomeTurma = nomeTurma;
        this.nomePeriodo = nomePeriodo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getNomeTurma() {
        return nomeTurma;
    }

    public void setNomeTurma(String nomeTurma) {
        this.nomeTurma = nomeTurma;
    }

    public String getNomePeriodo() {
        return nomePeriodo;
    }

    public void setNomePeriodo(String nomePeriodo) {
        this.nomePeriodo = nomePeriodo;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }

    public int getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getCdMat() {
        return cdMat;
    }

    public void setCdMat(int cdMat) {
        this.cdMat = cdMat;
    }

    @Override
    public void testaClasse() {
    }

    @Override
    public void limpar() {
        this.ordem = 0;
        this.diaSemana = 0;
        this.cdMat = 0;
    }
}
