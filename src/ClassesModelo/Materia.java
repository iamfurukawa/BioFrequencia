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
public class Materia implements organizador {

    private int curso;
    private String nomeCurso;
    private int turma;
    private String nomeMateria;
    private String sigla;
    private int professorPrincipal;
    private String professorPrincipalS;
    private int divisao;
    private String divisaoS;
    private int modulo;
    private int codigo;

    public Materia() {
        this.curso = 0;
        this.turma = 0;
        this.nomeMateria = "";
        this.sigla = "";
        this.professorPrincipal = 0;
        this.divisao = 0;
    }

    public Materia(int curso,String nomeCurso, int turma, String nomeMateria, String sigla, int professorPrincipal, int divisao,int modulo, int codigo) {
        this.curso = curso;
        this.turma = turma;
        this.nomeCurso = nomeCurso;
        this.nomeMateria = nomeMateria;
        this.sigla = sigla;
        this.professorPrincipal = professorPrincipal;
        this.divisao = divisao;
        this.modulo = modulo;
        this.codigo = codigo;
    }

    public Materia(int curso, int turma, String nomeMateria, String sigla, int professorPrincipal, int divisao) {
        this.curso = curso;
        this.turma = turma;
        this.nomeMateria = nomeMateria;
        this.sigla = sigla;
        this.professorPrincipal = professorPrincipal;
        this.divisao = divisao;
    }

    public Materia(int codigo) {
        this.codigo = codigo;
    }

    public int getModulo() {
        return modulo;
    }

    public void setModulo(int modulo) {
        this.modulo = modulo;
    }

    public int getTurma() {
        return turma;
    }

    public void setTurma(int turma) {
        this.turma = turma;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public String getProfessorPrincipalS() {
        return professorPrincipalS;
    }

    public void setProfessorPrincipalS(String professorPrincipalS) {
        this.professorPrincipalS = professorPrincipalS;
    }

    public String getDivisaoS() {
        return divisaoS;
    }

    public void setDivisaoS(String divisaoS) {
        this.divisaoS = divisaoS;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public int getCurso() {
        return curso;
    }

    public void setCurso(int curso) {
        this.curso = curso;
    }

    public int getProfessorPrincipal() {
        return professorPrincipal;
    }

    public void setProfessorPrincipal(int professorPrincipal) {
        this.professorPrincipal = professorPrincipal;
    }

    public int getDivisao() {
        return divisao;
    }

    public void setDivisao(int divisao) {
        this.divisao = divisao;
    }

    @Override
    public void testaClasse() {
        JOptionPane.showMessageDialog(null, getCurso() + "\n"
                + getTurma()+ "\n"
                + getNomeMateria() + "\n"
                + getSigla() + "\n"
                + getProfessorPrincipal() + "\n"
                + getDivisao() + "\n");
    }

    @Override
    public void limpar() {
        curso = 0;
        nomeCurso = "";
        turma = 0;
        nomeMateria = "";
        sigla = "";
        professorPrincipal = 0;
        professorPrincipalS = "";
        divisao = 0;
        divisaoS = "";
        codigo = 0;
    }
}
