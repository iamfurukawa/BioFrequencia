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
public class Curso implements organizador {

    private String nomeCurso;
    private int quantidadeModulos;
    private int ativo;
    private int codigo;

    public Curso() {
        this.nomeCurso = "";
        this.quantidadeModulos = 0;
        this.ativo = 1;
    }

    public Curso(String nomeCurso, int quantidadeModulos, int ativo, int codigo) {
        this.nomeCurso = nomeCurso;
        this.quantidadeModulos = quantidadeModulos;
        this.ativo = ativo;
        this.codigo = codigo;
    }

    public Curso(String nomeCurso, int quantidadeModulos, int ativo) {
        this.nomeCurso = nomeCurso;
        this.quantidadeModulos = quantidadeModulos;
        this.ativo = ativo;
    }

    public Curso(String nomeCurso, int codigo) {
        this.nomeCurso = nomeCurso;
        this.codigo = codigo;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String NomeCurso) {
        this.nomeCurso = NomeCurso;
    }

    public int getQuantidadeModulos() {
        return quantidadeModulos;
    }

    public void setQuantidadeModulos(int quantidadeModulos) {
        this.quantidadeModulos = quantidadeModulos;
    }

    public int getAtivo() {
        return ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    @Override
    public void testaClasse() {
        JOptionPane.showMessageDialog(null, getNomeCurso() + "\n" + getQuantidadeModulos() + "\n" + getAtivo() + "\n" + getCodigo());
    }

    @Override
    public void limpar() {
        nomeCurso = "";
        quantidadeModulos = 0;
        ativo = 0;
        codigo = 0;
    }
}
