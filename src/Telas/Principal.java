/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Telas;

import ClassesCPF.*;
import ClassesModelo.*;
import Leitor.*;
import ClassesBanco.*;
import com.mysql.jdbc.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.*;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author User
 */
public class Principal extends javax.swing.JFrame implements WindowListener {

    //variaveis de botão
    private int cadAl = 0;
    private int pesquisaBtn = 0;
    private int row = -1;
    private int cadFunc = 0;
    //variaveis normais
    private int guardaCod = 0;
    //Classes Modelos
    private Curso cc = new Curso();
    private Materia cm = new Materia();
    private Turma ct = new Turma();
    private Aluno al = new Aluno();
    private Usuario user = new Usuario();
    private Usuario usuarioLogado = new Usuario();
    private Aluno alunoTemporario = new Aluno();
    //Conexao com banco
    private Connection con;
    //CPF
    private ValidarCPF vcpf = new ValidarCPF();
    //array pra adicionar as combobox's
    private String[] cursoArray;
    private String[] professoresArray;
    private String[] turmaArray;
    private String[] aulasChamada;
    //arrayList de resultados
    private ArrayList<Curso> cursosLista = new ArrayList<>();
    private ArrayList<Curso> cursosLista2 = new ArrayList<>();
    private ArrayList<Curso> cursosListaCadastrado = new ArrayList<>();
    private ArrayList<Turma> turma = new ArrayList<>();
    private ArrayList<Turma> turma2 = new ArrayList<>();
    private ArrayList<Usuario> professores = new ArrayList<>();
    private ArrayList<Usuario> professores2 = new ArrayList<>();
    private ArrayList<Usuario> funcionario = new ArrayList<>();
    private ArrayList<Usuario> funcionario2 = new ArrayList<>();
    private ArrayList<Materia> materia = new ArrayList<>();
    private ArrayList<Aluno> aluno = new ArrayList<>();
    private ArrayList<Aluno> aluno2 = new ArrayList<>();
    private ArrayList<Horario> horario = new ArrayList<>();
    //Instanciação das classes com banco
    private ConexaoBanco conexaoBanco = new ConexaoBanco();
    private UsuarioBanco usuarioBanco = new UsuarioBanco();
    private CursoBanco cursoBanco = new CursoBanco();
    private AlunoBanco alunoBanco = new AlunoBanco();
    private TurmaBanco turmaBanco = new TurmaBanco();
    private MateriaBanco materiaBanco = new MateriaBanco();
    private HorarioBanco horarioBanco = new HorarioBanco();
    // DADOS UTILIZADOS PARA FAZER SELECTS NO BANCO
    //sql
    private String txtSql = "";
    //professor
    private String profAtivoSql = "select * from funcionario where ativo = 1 && nivel_acesso_func = 1";
    private String profInativoSql = "select * from funcionario where ativo = 0 && nivel_acesso_func = 1";
    private String profNomeAtivoSql = "select * from funcionario where ativo = 1 && nivel_acesso_func = 1 && nm_func like '%txtSql%'";
    private String profNomeInativoSql = "select * from funcionario where ativo = 0 && nivel_acesso_func = 1 && nm_func like '%txtSql%'";
    private String profCPFAtivoSql = "select * from funcionario where ativo = 1 && nivel_acesso_func = 1 && cpf_func like '%txtSql%'";
    private String profCPFInativoSql = "select * from funcionario where ativo = 0 && nivel_acesso_func = 1 && cpf_func like '%txtSql%'";
    //secretaria
    private String funcAtivoSql = "select * from funcionario where ativo = 1 && nivel_acesso_func = 2";
    private String funcInativoSql = "select * from funcionario where ativo = 0 && nivel_acesso_func = 2";
    private String funcNomeAtivoSql = "select * from funcionario where ativo = 1 && nivel_acesso_func = 2 && nm_func like '%txtSql%'";
    private String funcNomeInativoSql = "select * from funcionario where ativo = 0 && nivel_acesso_func = 2 && nm_func like '%txtSql%'";
    private String funcCPFAtivoSql = "select * from funcionario where ativo = 1 && nivel_acesso_func = 2 && cpf_func like '%txtSql%'";
    private String funcCPFInativoSql = "select * from funcionario where ativo = 0 && nivel_acesso_func = 2 && cpf_func like '%txtSql%'";
    //turma
    private String turmaAtiva = "select * from turma natural join curso where at_turma = 1";
    private String turmaInativa = "select * from turma natural join curso where at_turma = 0";
    private String turmaSiglaAtivo = "select * from turma natural join curso where at_turma = 1 && sigla_turma like '%txtSql%'";
    private String turmaSiglaInativo = "select * from turma natural join curso where at_turma = 0 && sigla_turma like '%txtSql%'";
    private String turmaSalaAtivo = "select * from turma natural join curso where at_turma = 1 && sala_turma like '%txtSql%'";
    private String turmaSalaInativo = "select * from turma natural join curso where at_turma = 0 && sala_turma like '%txtSql%'";
    private String turmaCursoAtivo = "select * from turma natural join curso where at_turma = 1 && nm_curso like '%txtSql%'";
    private String turmaCursoAtivoCodigo = "select * from turma natural join curso where at_turma = 1 && cd_curso = '%txtSql%'";
    private String turmaCursoInativo = "select * from turma natural join curso where at_turma = 0 && nm_curso like '%txtSql%'";
    private String turmaModuloAtivo = "select * from turma natural join curso where at_turma = 1 && mod_turma like '%txtSql%'";
    private String turmaModuloInativo = "select * from turma natural join curso where at_turma = 0 && mod_turma like '%txtSql%'";
    private String turmaPeriodoAtivo = "select * from turma natural join curso where at_turma = 1 && per_turma like '%txtSql%'";
    private String turmaPeriodoInativo = "select * from turma natural join curso where at_turma = 0 && per_turma like '%txtSql%'";
    private String turmaAnoAtivo = "select * from turma natural join curso where at_turma = 1 && ano_turma like '%txtSql%'";
    private String turmaAnoInativo = "select * from turma natural join curso where at_turma = 0 && ano_turma like '%txtSql%'";
    //materia
    private String materiaTodas = "select * from materia natural join turma natural join curso";
    private String materiaNome = "select * from materia natural join turma natural join curso where nm_mat like '%txtSql%'";
    private String materiaSigla = "select * from materia natural join turma natural join curso where sigla_mat like '%txtSql%'";
    private String materiaCurso = "select * from materia natural join turma natural join curso where nm_curso like '%txtSql%'";
    private String materiaTurma = "select * from materia natural join turma natural join curso where sigla_turma like '%txtSql%'";
    private String materiaModulo = "select * from materia natural join turma natural join curso where mod_curso like '%txtSql%'";
    //curso
    private String cursoAtivo = "select * from curso where at_curso = 1";
    private String cursoInativo = "select * from curso where at_curso = 0";
    private String cursoNomeAtivo = "select * from curso where at_curso = 1 && nm_curso like '%txtSql%'";
    private String cursoNomeInativo = "select * from curso where at_curso = 0 && nm_curso like '%txtSql%'";
    private String cursoModuloAtivo = "select * from curso where at_curso = 1 && mod_curso like '%txtSql%'";
    private String cursoModuloInativo = "select * from curso where at_curso = 0 && mod_curso like '%txtSql%'";
    private String cursoAluno = "select * from alunoxmateria natural join materia natural join turma natural join curso where alunoxmateria.cd_al = ID group by nm_curso";
    //Aluno
    private String alunoTodos = "select * from aluno where ra_al = 'sql'";
    private String alunoAtivo = "select * from aluno where ativo_al = 1";
    private String alunoInativo = "select * from aluno where ativo_al = 0";
    private String alunoNomeAtivo = "select * from aluno where ativo_al = 1 && nm_al like '%txtSql%'";
    private String alunoNomeInativo = "select * from aluno where ativo_al = 0 && nm_al like '%txtSql%'";
    private String alunoRAAtivo = "select * from aluno where ativo_al = 1 && ra_al like '%txtSql%'";
    private String alunoRAInativo = "select * from aluno where ativo_al = 0 && ra_al like '%txtSql%'";
    //horario
    private String horarioTodos = "select horario_aula.cd_hr, horario_aula.ordem_hr, horario_aula.dia_sem_hr, horario_aula.cd_mat,materia.nm_mat, curso.nm_curso, turma.sigla_turma, turma.per_turma from horario_aula natural join turma natural join curso natural join materia group by  turma.sigla_turma";
    private String horarioMateria = "select horario_aula.cd_hr, horario_aula.ordem_hr, horario_aula.dia_sem_hr, horario_aula.cd_mat,materia.nm_mat, curso.nm_curso, turma.sigla_turma, turma.per_turma from horario_aula natural join turma natural join curso natural join materia where sigla_turma = '%txtSql%'";

    /**
     * Creates new form Principal
     */
    public Principal(Usuario usuario) {
        initComponents();
        usuarioLogado = usuario;
        distribuicaoInformacao();
        setExtendedState(MAXIMIZED_BOTH);
        this.addWindowListener(this);
        new Thread(new Contador()).start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgTipoCadastroUser = new javax.swing.ButtonGroup();
        bgPesquisa = new javax.swing.ButtonGroup();
        jFileChooser = new javax.swing.JFileChooser();
        pHorarios = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tHorarios = new javax.swing.JTable();
        btnAlterarHorarios = new javax.swing.JButton();
        jSeparator11 = new javax.swing.JSeparator();
        pBanner = new javax.swing.JPanel();
        lImageBanner = new javax.swing.JLabel();
        lUserImagem = new javax.swing.JLabel();
        lUser = new javax.swing.JLabel();
        lNivelAcesso = new javax.swing.JLabel();
        lTempo = new javax.swing.JLabel();
        pMenu = new javax.swing.JPanel();
        btn_Logoff = new javax.swing.JButton();
        btn_sair = new javax.swing.JButton();
        btn_pesquisa = new javax.swing.JButton();
        btn_chamadaManual = new javax.swing.JButton();
        btn_add = new javax.swing.JButton();
        btn_chamadaBiometrica = new javax.swing.JButton();
        btnConf = new javax.swing.JButton();
        jdeskTelas = new javax.swing.JDesktopPane();
        jInternalFramePesquisa = new javax.swing.JInternalFrame();
        pCampoPesquisa = new javax.swing.JPanel();
        rbProfessor = new javax.swing.JRadioButton();
        rbAluno = new javax.swing.JRadioButton();
        rbSecretaria = new javax.swing.JRadioButton();
        rbMaterias = new javax.swing.JRadioButton();
        rbTurmas = new javax.swing.JRadioButton();
        rbCursos = new javax.swing.JRadioButton();
        rbHorarios = new javax.swing.JRadioButton();
        tPesquisa = new javax.swing.JTextField();
        btnPesquisa = new javax.swing.JButton();
        cbPesquisa = new javax.swing.JComboBox();
        pResultado = new javax.swing.JPanel();
        jSeparator12 = new javax.swing.JSeparator();
        btnVerDetalhesPesquisa = new javax.swing.JButton();
        jtpResultadoPesquisa = new javax.swing.JTabbedPane();
        jpAtivos = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtResultadoAtivo = new javax.swing.JTable();
        jpInativos = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jtResultadoInativos = new javax.swing.JTable();
        btnResetSenha = new javax.swing.JButton();
        btnVariosPesquisa = new javax.swing.JButton();
        jInternalFrameConfiguracoesGerais = new javax.swing.JInternalFrame();
        jConf = new javax.swing.JPanel();
        pEditUser = new javax.swing.JPanel();
        lImagemEditUser = new javax.swing.JLabel();
        lNomeCompletoEditUser = new javax.swing.JLabel();
        ldtNasEditUser = new javax.swing.JLabel();
        lCPFEditUser = new javax.swing.JLabel();
        lRGEditUser = new javax.swing.JLabel();
        lTelFixoEditUser = new javax.swing.JLabel();
        lTelCelEditUser = new javax.swing.JLabel();
        lEmailEditUser = new javax.swing.JLabel();
        lAvisosEditUser = new javax.swing.JLabel();
        tNomeEditUser = new javax.swing.JTextField();
        tEmailEditUser = new javax.swing.JTextField();
        tfDataNasEditUser = new javax.swing.JFormattedTextField();
        tfTelCelEditUser = new javax.swing.JFormattedTextField();
        tfTelFixoEditUser = new javax.swing.JFormattedTextField();
        tfCPFEditUser = new javax.swing.JFormattedTextField();
        tfRGEditUser = new javax.swing.JFormattedTextField();
        btnEditUser = new javax.swing.JButton();
        btnCancelarEditUser = new javax.swing.JButton();
        btnAddImgEditUser = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        pEditarSenha = new javax.swing.JPanel();
        lSenhaAtual = new javax.swing.JLabel();
        lSenhaNova = new javax.swing.JLabel();
        lSenhaNovaRepetir = new javax.swing.JLabel();
        pfSenhaNovaRepetir = new javax.swing.JPasswordField();
        pfSenhaNova = new javax.swing.JPasswordField();
        pfSenhaAtual = new javax.swing.JPasswordField();
        btnCancelar = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JSeparator();
        jInternalFrameChamadaBiometrica = new javax.swing.JInternalFrame();
        pChamadaBio = new javax.swing.JPanel();
        pAlunoChamadaBio = new javax.swing.JPanel();
        lFotoAlunoChamadaBio = new javax.swing.JLabel();
        lDigitalChamadaBio = new javax.swing.JLabel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        lNomeChamadaBio = new javax.swing.JLabel();
        lNumeroChamadaBio = new javax.swing.JLabel();
        tNomeChamadaBio = new javax.swing.JTextField();
        tNumeroChamadaBio = new javax.swing.JTextField();
        pAlunosChamadaBio = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTableChamadaBio = new javax.swing.JTable();
        pChamadaBioInicio = new javax.swing.JPanel();
        lTurmaChamadaBio = new javax.swing.JLabel();
        lAulaChamadaBio = new javax.swing.JLabel();
        cbTurmaChamadaBio = new javax.swing.JComboBox();
        cbAulaChamadaBio = new javax.swing.JComboBox();
        btnIniciarChamadaBio = new javax.swing.JButton();
        btnFinalizarChamadaBio = new javax.swing.JButton();
        lProfChamadaBio = new javax.swing.JLabel();
        tProfChamadaBio = new javax.swing.JTextField();
        jSeparator16 = new javax.swing.JSeparator();
        jInternalFrameChamadaManual = new javax.swing.JInternalFrame();
        pChamadaManual = new javax.swing.JPanel();
        pAlunosChamadaManual = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTableChamadaManual = new javax.swing.JTable();
        pChamadaManualInicio = new javax.swing.JPanel();
        lTurmaChamadaManual = new javax.swing.JLabel();
        lAulaChamadaManual = new javax.swing.JLabel();
        cbTurmaChamadaManual = new javax.swing.JComboBox();
        cbAulaChamadaManual = new javax.swing.JComboBox();
        btnIniciarChamadaManual = new javax.swing.JButton();
        btnFinalizarChamadaManual = new javax.swing.JButton();
        lProfChamadaManual = new javax.swing.JLabel();
        tProfChamadaManual = new javax.swing.JTextField();
        jSeparator19 = new javax.swing.JSeparator();
        jInternalFrameCadastros = new javax.swing.JInternalFrame();
        pMenuInternal = new javax.swing.JPanel();
        lCadFuncionario = new javax.swing.JLabel();
        lCadMaterias = new javax.swing.JLabel();
        lCadTurmas = new javax.swing.JLabel();
        lCadCurso = new javax.swing.JLabel();
        lCadAluno = new javax.swing.JLabel();
        lHorario = new javax.swing.JLabel();
        jdeskTelasCadastro = new javax.swing.JDesktopPane();
        jInternalFrameCadAluno = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        lCadUser = new javax.swing.JLabel();
        jTabbedPane = new javax.swing.JTabbedPane();
        pDadosAlunoPrincipal = new javax.swing.JPanel();
        pDadosAluno = new javax.swing.JPanel();
        lNomeAluno = new javax.swing.JLabel();
        lDtNasAluno = new javax.swing.JLabel();
        lNumAluno = new javax.swing.JLabel();
        lTelAluno = new javax.swing.JLabel();
        lCelAluno = new javax.swing.JLabel();
        lCidadeAluno = new javax.swing.JLabel();
        lEndAluno = new javax.swing.JLabel();
        lCEPAluno = new javax.swing.JLabel();
        lSexoAluno = new javax.swing.JLabel();
        lNmResAluno1 = new javax.swing.JLabel();
        lNmResAluno2 = new javax.swing.JLabel();
        lImagemAluno = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        spinnerNumChamadaAluno = new javax.swing.JSpinner();
        cbSexoAluno = new javax.swing.JComboBox();
        tfDtNasAluno = new javax.swing.JFormattedTextField();
        tfTelAluno = new javax.swing.JFormattedTextField();
        tfCelularAluno = new javax.swing.JFormattedTextField();
        tfCEPAluno = new javax.swing.JFormattedTextField();
        tNomeAluno = new javax.swing.JTextField();
        tEndAluno = new javax.swing.JTextField();
        tCidadeAluno = new javax.swing.JTextField();
        tRespAluno1 = new javax.swing.JTextField();
        tRespAluno2 = new javax.swing.JTextField();
        btnImagemAluno = new javax.swing.JButton();
        lDigitalAluno = new javax.swing.JLabel();
        btnAddDigitalAluno = new javax.swing.JButton();
        btnCadastrarAluno = new javax.swing.JButton();
        btnLimparAluno = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        lRAAluno = new javax.swing.JLabel();
        tfRAAluno = new javax.swing.JFormattedTextField();
        lCursoAluno = new javax.swing.JLabel();
        cbCursoAluno = new javax.swing.JComboBox();
        lTurmaAluno = new javax.swing.JLabel();
        cbTurmaAluno = new javax.swing.JComboBox();
        pCursosCadastradosAluno = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tbCursCadAluno = new javax.swing.JTable();
        jInternalFrameCadUser = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        lCadAlun = new javax.swing.JLabel();
        pCadastroUser = new javax.swing.JPanel();
        lImagem = new javax.swing.JLabel();
        lNomeCompletoUser = new javax.swing.JLabel();
        ldtNasUser = new javax.swing.JLabel();
        lCPFUser = new javax.swing.JLabel();
        lRGUser = new javax.swing.JLabel();
        lTelFixoUser = new javax.swing.JLabel();
        lTelCelUser = new javax.swing.JLabel();
        lEmailUser = new javax.swing.JLabel();
        lTipoCad = new javax.swing.JLabel();
        lAvisosUser = new javax.swing.JLabel();
        tNomeUser = new javax.swing.JTextField();
        tEmailUser = new javax.swing.JTextField();
        jRadioButtonProfessor = new javax.swing.JRadioButton();
        jRadioButtonSecretaria = new javax.swing.JRadioButton();
        tfDataNasUser = new javax.swing.JFormattedTextField();
        tfTelCelUser = new javax.swing.JFormattedTextField();
        tfTelFixoUser = new javax.swing.JFormattedTextField();
        tfCPFUser = new javax.swing.JFormattedTextField();
        tfRGUser = new javax.swing.JFormattedTextField();
        btnCadastrarUser = new javax.swing.JButton();
        btnCancelarUser = new javax.swing.JButton();
        btnAddImgUser = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jInternalFrameCadCurso = new javax.swing.JInternalFrame();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        pCadCurso = new javax.swing.JPanel();
        lNomeCurso = new javax.swing.JLabel();
        lQtdModCurso = new javax.swing.JLabel();
        tNomeCurso = new javax.swing.JTextField();
        btnCadCurso = new javax.swing.JButton();
        btnLimparCurso = new javax.swing.JButton();
        spQtdModCurso = new javax.swing.JSpinner();
        jSeparator8 = new javax.swing.JSeparator();
        jInternalFrameCadMateria = new javax.swing.JInternalFrame();
        jPanel8 = new javax.swing.JPanel();
        lCadMat = new javax.swing.JLabel();
        pCadMateria = new javax.swing.JPanel();
        lNomeMateria = new javax.swing.JLabel();
        lSiglaMateria = new javax.swing.JLabel();
        lProfessorMateria = new javax.swing.JLabel();
        lDivisaoTurma = new javax.swing.JLabel();
        tNomeMateria = new javax.swing.JTextField();
        tSiglaMateria = new javax.swing.JTextField();
        cbProfessorMateria = new javax.swing.JComboBox();
        cbDivisaoMateria = new javax.swing.JComboBox();
        btnCadastrarMateria = new javax.swing.JButton();
        btnLimparMateria = new javax.swing.JButton();
        lCursoMateria = new javax.swing.JLabel();
        lTurmaMateria = new javax.swing.JLabel();
        cbNomeCursoMateria = new javax.swing.JComboBox();
        cbTurmaMateria = new javax.swing.JComboBox();
        jSeparator9 = new javax.swing.JSeparator();
        jInternalFrameCadHorario = new javax.swing.JInternalFrame();
        jPanel9 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        pHorario = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jtHorario = new javax.swing.JTable();
        lhorariocurso = new javax.swing.JLabel();
        lhorarioturma = new javax.swing.JLabel();
        jcbCursoHorario = new javax.swing.JComboBox();
        jcbTurmaHorario = new javax.swing.JComboBox();
        jSeparator13 = new javax.swing.JSeparator();
        btnSalvarHorario = new javax.swing.JButton();
        btnCancelarHorario = new javax.swing.JButton();
        jInternalFrameCadTurmas = new javax.swing.JInternalFrame();
        jPanel10 = new javax.swing.JPanel();
        lCadTurm = new javax.swing.JLabel();
        pCadTurma = new javax.swing.JPanel();
        lNomeCursoTurma = new javax.swing.JLabel();
        lSiglaTurma = new javax.swing.JLabel();
        lSalaTurma = new javax.swing.JLabel();
        lPeriodoTurma = new javax.swing.JLabel();
        lModuloTurma = new javax.swing.JLabel();
        tSiglaTurma = new javax.swing.JTextField();
        tSalaTurma = new javax.swing.JTextField();
        cbCursoTurma = new javax.swing.JComboBox();
        cbPeriodoTurma = new javax.swing.JComboBox();
        cbModuloTurma = new javax.swing.JComboBox();
        btnCadTurma = new javax.swing.JButton();
        btnLimparTurma = new javax.swing.JButton();
        jSeparator10 = new javax.swing.JSeparator();
        lsemTurma = new javax.swing.JLabel();
        lAnoTurma = new javax.swing.JLabel();
        tfAnoTurma = new javax.swing.JFormattedTextField();
        tfSemTurma = new javax.swing.JFormattedTextField();
        jInternalFrameAlterarHorario = new javax.swing.JInternalFrame();
        jPanel2 = new javax.swing.JPanel();
        pHorarioEdit = new javax.swing.JPanel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jtHorarioEdit = new javax.swing.JTable();
        jSeparator25 = new javax.swing.JSeparator();
        btnVoltarHorarioEdit = new javax.swing.JButton();
        jInternalFrameAlterarFuncionario = new javax.swing.JInternalFrame();
        pDadosFuncionarioEdit = new javax.swing.JPanel();
        pEditFuncionario = new javax.swing.JPanel();
        lImagemEdit = new javax.swing.JLabel();
        lNomeCompletoUserEdit = new javax.swing.JLabel();
        ldtNasUserEdit = new javax.swing.JLabel();
        lCPFUserEdit = new javax.swing.JLabel();
        lRGUser1Edit = new javax.swing.JLabel();
        lTelFixoUserEdit = new javax.swing.JLabel();
        lTelCelUserEdit = new javax.swing.JLabel();
        lEmailUserEdit = new javax.swing.JLabel();
        lAvisosUser1 = new javax.swing.JLabel();
        tNomeUserEdit = new javax.swing.JTextField();
        tEmailUserEdit = new javax.swing.JTextField();
        tfDataNasUserEdit = new javax.swing.JFormattedTextField();
        tfTelCelUserEdit = new javax.swing.JFormattedTextField();
        tfTelFixoUserEdit = new javax.swing.JFormattedTextField();
        tfCPFUserEdit = new javax.swing.JFormattedTextField();
        tfRGUserEdit = new javax.swing.JFormattedTextField();
        btnEditUser1 = new javax.swing.JButton();
        btnCancelarUserEdit = new javax.swing.JButton();
        btnAddImgUserEdit = new javax.swing.JButton();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator20 = new javax.swing.JSeparator();
        jInternalFrameAlterarTurma = new javax.swing.JInternalFrame();
        jPanel4 = new javax.swing.JPanel();
        pEditTurma = new javax.swing.JPanel();
        lNomeCursoTurmaEdit = new javax.swing.JLabel();
        lSiglaTurmaEdit = new javax.swing.JLabel();
        lSalaTurmaEdit = new javax.swing.JLabel();
        lPeriodoTurmaEdit = new javax.swing.JLabel();
        lModuloTurmaEdit = new javax.swing.JLabel();
        tSiglaTurmaEdit = new javax.swing.JTextField();
        tSalaTurmaEdit = new javax.swing.JTextField();
        cbCursoTurmaEdit = new javax.swing.JComboBox();
        cbPeriodoTurmaEdit = new javax.swing.JComboBox();
        cbModuloTurmaEdit = new javax.swing.JComboBox();
        btnAlterarTurma = new javax.swing.JButton();
        btnLimparTurmaEdit = new javax.swing.JButton();
        jSeparator22 = new javax.swing.JSeparator();
        lsemTurmaEdit = new javax.swing.JLabel();
        lAnoTurmaEdit = new javax.swing.JLabel();
        tfAnoTurmaEdit = new javax.swing.JFormattedTextField();
        tfSemTurmaEdit = new javax.swing.JFormattedTextField();
        pPresencaAtualEdit = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        tbPresencaEdit = new javax.swing.JTable();
        jInternalFrameAlterarMaterias = new javax.swing.JInternalFrame();
        pEditMateria1 = new javax.swing.JPanel();
        lNomeMateriaEdit = new javax.swing.JLabel();
        lSiglaMateriaEdit = new javax.swing.JLabel();
        lProfessorMateriaEdit = new javax.swing.JLabel();
        lDivisaoTurmaEdit = new javax.swing.JLabel();
        tNomeMateriaEdit = new javax.swing.JTextField();
        tSiglaMateriaEdit = new javax.swing.JTextField();
        cbProfessorMateriaEdit = new javax.swing.JComboBox();
        cbDivisaoMateriaEdit = new javax.swing.JComboBox();
        btnEditMateria1 = new javax.swing.JButton();
        btnLimparMateriaEdit = new javax.swing.JButton();
        lCursoMateriaEdit = new javax.swing.JLabel();
        lTurmaMateriaEdit = new javax.swing.JLabel();
        cbNomeCursoMateriaEdit = new javax.swing.JComboBox();
        cbTurmaMateriaEdit = new javax.swing.JComboBox();
        jSeparator21 = new javax.swing.JSeparator();
        jInternalFrameAlterarAluno = new javax.swing.JInternalFrame();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        pDadosAlunoPrincipal1 = new javax.swing.JPanel();
        pDadosAlunoEdit = new javax.swing.JPanel();
        lNomeAlunoEdit = new javax.swing.JLabel();
        lDtNasAlunoEdit = new javax.swing.JLabel();
        lNumAlunoEdit = new javax.swing.JLabel();
        lTelAlunoEdit = new javax.swing.JLabel();
        lCelAlunoEdit = new javax.swing.JLabel();
        lCidadeAlunoEdit = new javax.swing.JLabel();
        lEndAlunoEdit = new javax.swing.JLabel();
        lCEPAlunoEdit = new javax.swing.JLabel();
        lSexoAlunoEdit = new javax.swing.JLabel();
        lNmResAlunoEdit = new javax.swing.JLabel();
        lNmResAluno2Edit = new javax.swing.JLabel();
        lImagemAlunoEdit = new javax.swing.JLabel();
        jSeparator23 = new javax.swing.JSeparator();
        spinnerNumChamadaAlunoEdit = new javax.swing.JSpinner();
        cbSexoAlunoEdit = new javax.swing.JComboBox();
        tfDtNasAlunoEdit = new javax.swing.JFormattedTextField();
        tfTelAlunoEdit = new javax.swing.JFormattedTextField();
        tfCelularAlunoEdit = new javax.swing.JFormattedTextField();
        tfCEPAlunoEdit = new javax.swing.JFormattedTextField();
        tNomeAlunoEdit = new javax.swing.JTextField();
        tEndAlunoEdit = new javax.swing.JTextField();
        tCidadeAlunoEdit = new javax.swing.JTextField();
        tRespAlunoEdit = new javax.swing.JTextField();
        tRespAluno2Edit = new javax.swing.JTextField();
        btnImagemAlunoEdit = new javax.swing.JButton();
        lDigitalAlunoEdit = new javax.swing.JLabel();
        btnAddDigitalAlunoEdit = new javax.swing.JButton();
        btnAlterarAlunoEdit = new javax.swing.JButton();
        btnCancelarAlunoEdit = new javax.swing.JButton();
        jSeparator24 = new javax.swing.JSeparator();
        lRAAlunoEdit = new javax.swing.JLabel();
        tfRAAlunoEdit = new javax.swing.JFormattedTextField();
        lCursoAlunoEdit = new javax.swing.JLabel();
        cbCursoAlunoEdit = new javax.swing.JComboBox();
        lTurmaAlunoEdit = new javax.swing.JLabel();
        cbTurmaAlunoEdit = new javax.swing.JComboBox();
        pCursosCadastradosAluno1 = new javax.swing.JPanel();
        pCursosCadastradosAlunoEdit = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        tbCursCadAlunoEdit = new javax.swing.JTable();
        pMateriasCadAlunEdit = new javax.swing.JPanel();
        pMateriasCadastradasAlunoEdit = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tbAlunoMateriaEdit = new javax.swing.JTable();
        jInternalFrameAlterarCurso = new javax.swing.JInternalFrame();
        jPanel3 = new javax.swing.JPanel();
        pDadosCursoEdit = new javax.swing.JPanel();
        lNomeCursoEdit = new javax.swing.JLabel();
        lModuloCursoEdit = new javax.swing.JLabel();
        btnCursoAlterarEdit = new javax.swing.JButton();
        tNomeCursoEdit = new javax.swing.JTextField();
        tModCursoEdit = new javax.swing.JTextField();
        jSeparator17 = new javax.swing.JSeparator();
        btnCursoCancelarEdit = new javax.swing.JButton();
        pTurmaCursoEdit = new javax.swing.JPanel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jtTurmasAtivas = new javax.swing.JTable();

        pHorarios.setBorder(javax.swing.BorderFactory.createTitledBorder("Horários"));

        tHorarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Aula 1:", null, null, null},
                {"Aula 2:", null, null, null},
                {"Aula 3:", null, null, null},
                {"Aula 4:", null, null, null},
                {"Aula 5:", null, null, null},
                {"Aula 6:", null, null, null},
                {"Intervalo 1:", null, null, null},
                {"Intervalo 2:", null, null, null}
            },
            new String [] {
                "Aulas", "Manha", "Tarde", "Noite"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tHorarios.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jScrollPane1.setViewportView(tHorarios);

        btnAlterarHorarios.setText("Alterar");
        btnAlterarHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarHorariosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pHorariosLayout = new javax.swing.GroupLayout(pHorarios);
        pHorarios.setLayout(pHorariosLayout);
        pHorariosLayout.setHorizontalGroup(
            pHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHorariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHorariosLayout.createSequentialGroup()
                        .addGap(0, 285, Short.MAX_VALUE)
                        .addComponent(btnAlterarHorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pHorariosLayout.createSequentialGroup()
                        .addGroup(pHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE)
                            .addComponent(jSeparator11, javax.swing.GroupLayout.DEFAULT_SIZE, 345, Short.MAX_VALUE))
                        .addGap(28, 28, 28)))
                .addContainerGap())
        );
        pHorariosLayout.setVerticalGroup(
            pHorariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHorariosLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAlterarHorarios)
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1024, 768));

        pBanner.setBackground(new java.awt.Color(0, 153, 255));
        pBanner.setForeground(new java.awt.Color(92, 155, 235));

        lImageBanner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/BioFreq.png"))); // NOI18N
        lImageBanner.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lImageBannerMouseClicked(evt);
            }
        });

        lUserImagem.setBackground(new java.awt.Color(204, 204, 204));
        lUserImagem.setForeground(new java.awt.Color(204, 204, 204));
        lUserImagem.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lUserImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N
        lUserImagem.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lUserImagem.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lUserImagem.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        lUser.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lUser.setForeground(new java.awt.Color(255, 255, 255));
        lUser.setText("Unknown");

        lNivelAcesso.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lNivelAcesso.setForeground(new java.awt.Color(255, 255, 255));
        lNivelAcesso.setText("Funcionario/Professor");

        lTempo.setForeground(new java.awt.Color(255, 255, 255));
        lTempo.setText("Tempo");

        javax.swing.GroupLayout pBannerLayout = new javax.swing.GroupLayout(pBanner);
        pBanner.setLayout(pBannerLayout);
        pBannerLayout.setHorizontalGroup(
            pBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pBannerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lUserImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lUser, javax.swing.GroupLayout.DEFAULT_SIZE, 341, Short.MAX_VALUE)
                    .addComponent(lTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lNivelAcesso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(lImageBanner)
                .addContainerGap())
        );
        pBannerLayout.setVerticalGroup(
            pBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pBannerLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lImageBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pBannerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(pBannerLayout.createSequentialGroup()
                            .addComponent(lUser, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lNivelAcesso, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lTempo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(lUserImagem, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pMenu.setPreferredSize(new java.awt.Dimension(89, 600));

        btn_Logoff.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/trocar_usuario.png"))); // NOI18N
        btn_Logoff.setToolTipText("Logoff");
        btn_Logoff.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Logoff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_LogoffActionPerformed(evt);
            }
        });

        btn_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/sair.png"))); // NOI18N
        btn_sair.setToolTipText("Sair");
        btn_sair.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_sairActionPerformed(evt);
            }
        });

        btn_pesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/search.png"))); // NOI18N
        btn_pesquisa.setToolTipText("Pesquisa");
        btn_pesquisa.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_pesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pesquisaActionPerformed(evt);
            }
        });

        btn_chamadaManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/check.png"))); // NOI18N
        btn_chamadaManual.setToolTipText("Chamada Manual");
        btn_chamadaManual.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_chamadaManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chamadaManualActionPerformed(evt);
            }
        });

        btn_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/userAdd.png"))); // NOI18N
        btn_add.setToolTipText("Cadastrar");
        btn_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_addActionPerformed(evt);
            }
        });

        btn_chamadaBiometrica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/finger.png"))); // NOI18N
        btn_chamadaBiometrica.setToolTipText("Chamada Biométrica");
        btn_chamadaBiometrica.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_chamadaBiometrica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chamadaBiometricaActionPerformed(evt);
            }
        });

        btnConf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/conf.png"))); // NOI18N
        btnConf.setToolTipText("Configurações");
        btnConf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pMenuLayout = new javax.swing.GroupLayout(pMenu);
        pMenu.setLayout(pMenuLayout);
        pMenuLayout.setHorizontalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btn_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_Logoff, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_chamadaBiometrica, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConf, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_chamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pMenuLayout.setVerticalGroup(
            pMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_Logoff, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_pesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_chamadaBiometrica, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_chamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConf)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_add, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pMenuLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnConf, btn_chamadaManual});

        jdeskTelas.setBackground(new java.awt.Color(19, 134, 255));

        jInternalFramePesquisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pCampoPesquisa.setBorder(javax.swing.BorderFactory.createTitledBorder("Campo De Pesquisa"));

        bgPesquisa.add(rbProfessor);
        rbProfessor.setText("Professor");
        rbProfessor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbProfessorActionPerformed(evt);
            }
        });

        bgPesquisa.add(rbAluno);
        rbAluno.setText("Aluno");
        rbAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbAlunoActionPerformed(evt);
            }
        });

        bgPesquisa.add(rbSecretaria);
        rbSecretaria.setText("Secretaria");
        rbSecretaria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbSecretariaActionPerformed(evt);
            }
        });

        bgPesquisa.add(rbMaterias);
        rbMaterias.setText("Matérias");
        rbMaterias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMateriasActionPerformed(evt);
            }
        });

        bgPesquisa.add(rbTurmas);
        rbTurmas.setText("Turmas");
        rbTurmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbTurmasActionPerformed(evt);
            }
        });

        bgPesquisa.add(rbCursos);
        rbCursos.setText("Cursos");
        rbCursos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbCursosActionPerformed(evt);
            }
        });

        bgPesquisa.add(rbHorarios);
        rbHorarios.setText("Horário");
        rbHorarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbHorariosActionPerformed(evt);
            }
        });

        tPesquisa.setEnabled(false);
        tPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPesquisaActionPerformed(evt);
            }
        });

        btnPesquisa.setText("Pesquisar");
        btnPesquisa.setEnabled(false);
        btnPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisaActionPerformed(evt);
            }
        });

        cbPesquisa.setEnabled(false);

        javax.swing.GroupLayout pCampoPesquisaLayout = new javax.swing.GroupLayout(pCampoPesquisa);
        pCampoPesquisa.setLayout(pCampoPesquisaLayout);
        pCampoPesquisaLayout.setHorizontalGroup(
            pCampoPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCampoPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCampoPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCampoPesquisaLayout.createSequentialGroup()
                        .addComponent(rbProfessor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbSecretaria, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbMaterias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbTurmas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbCursos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(rbHorarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(tPesquisa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPesquisa))
        );
        pCampoPesquisaLayout.setVerticalGroup(
            pCampoPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCampoPesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCampoPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCampoPesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbProfessor)
                    .addComponent(rbSecretaria)
                    .addComponent(rbAluno)
                    .addComponent(rbMaterias)
                    .addComponent(rbTurmas)
                    .addComponent(rbCursos)
                    .addComponent(rbHorarios))
                .addContainerGap())
        );

        pResultado.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultado"));

        btnVerDetalhesPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/list.png"))); // NOI18N
        btnVerDetalhesPesquisa.setEnabled(false);
        btnVerDetalhesPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerDetalhesPesquisaActionPerformed(evt);
            }
        });

        jtpResultadoPesquisa.setEnabled(false);
        jtpResultadoPesquisa.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jtpResultadoPesquisaStateChanged(evt);
            }
        });

        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtResultadoAtivo.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jtResultadoAtivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtResultadoAtivoKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(jtResultadoAtivo);

        javax.swing.GroupLayout jpAtivosLayout = new javax.swing.GroupLayout(jpAtivos);
        jpAtivos.setLayout(jpAtivosLayout);
        jpAtivosLayout.setHorizontalGroup(
            jpAtivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 823, Short.MAX_VALUE)
            .addGroup(jpAtivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpAtivosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpAtivosLayout.setVerticalGroup(
            jpAtivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
            .addGroup(jpAtivosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpAtivosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jtpResultadoPesquisa.addTab("Ativos", jpAtivos);

        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jtResultadoInativos.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jtResultadoInativos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jtResultadoInativosKeyPressed(evt);
            }
        });
        jScrollPane3.setViewportView(jtResultadoInativos);

        javax.swing.GroupLayout jpInativosLayout = new javax.swing.GroupLayout(jpInativos);
        jpInativos.setLayout(jpInativosLayout);
        jpInativosLayout.setHorizontalGroup(
            jpInativosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 823, Short.MAX_VALUE)
            .addGroup(jpInativosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jpInativosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 803, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jpInativosLayout.setVerticalGroup(
            jpInativosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
            .addGroup(jpInativosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jpInativosLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        jtpResultadoPesquisa.addTab("Inativos", jpInativos);

        btnResetSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/resetSenha.png"))); // NOI18N
        btnResetSenha.setToolTipText("Padronizar Senha");
        btnResetSenha.setEnabled(false);
        btnResetSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetSenhaActionPerformed(evt);
            }
        });

        btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png"))); // NOI18N
        btnVariosPesquisa.setToolTipText("");
        btnVariosPesquisa.setEnabled(false);
        btnVariosPesquisa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVariosPesquisaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pResultadoLayout = new javax.swing.GroupLayout(pResultado);
        pResultado.setLayout(pResultadoLayout);
        pResultadoLayout.setHorizontalGroup(
            pResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator12)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pResultadoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVariosPesquisa)
                        .addGap(18, 18, 18)
                        .addComponent(btnResetSenha)
                        .addGap(18, 18, 18)
                        .addComponent(btnVerDetalhesPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jtpResultadoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pResultadoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnResetSenha, btnVariosPesquisa, btnVerDetalhesPesquisa});

        pResultadoLayout.setVerticalGroup(
            pResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pResultadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jtpResultadoPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 318, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator12, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pResultadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVerDetalhesPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addComponent(btnResetSenha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnVariosPesquisa))
                .addContainerGap())
        );

        pResultadoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnResetSenha, btnVariosPesquisa, btnVerDetalhesPesquisa});

        javax.swing.GroupLayout jInternalFramePesquisaLayout = new javax.swing.GroupLayout(jInternalFramePesquisa.getContentPane());
        jInternalFramePesquisa.getContentPane().setLayout(jInternalFramePesquisaLayout);
        jInternalFramePesquisaLayout.setHorizontalGroup(
            jInternalFramePesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFramePesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFramePesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pCampoPesquisa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jInternalFramePesquisaLayout.setVerticalGroup(
            jInternalFramePesquisaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFramePesquisaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pCampoPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pResultado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jInternalFramePesquisa.setBounds(0, 0, 840, 610);
        jdeskTelas.add(jInternalFramePesquisa, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameConfiguracoesGerais.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameConfiguracoesGerais.setPreferredSize(new java.awt.Dimension(924, 611));

        pEditUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Edição De Usuário"));

        lImagemEditUser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N

        lNomeCompletoEditUser.setText("*Nome Completo:");

        ldtNasEditUser.setText("Data de Nascimento:");

        lCPFEditUser.setText("*CPF:");

        lRGEditUser.setText("RG:");

        lTelFixoEditUser.setText("Telefone Fixo:");

        lTelCelEditUser.setText("Telefone Celular:");

        lEmailEditUser.setText("Email:");

        lAvisosEditUser.setForeground(new java.awt.Color(255, 0, 0));
        lAvisosEditUser.setText("Campos marcados com (*) são obrigatórios.");

        try {
            tfDataNasEditUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelCelEditUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)9####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelFixoEditUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCPFEditUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfRGEditUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#-UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnEditUser.setText("Alterar");
        btnEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserActionPerformed(evt);
            }
        });

        btnCancelarEditUser.setText("Cancelar");

        btnAddImgEditUser.setText("Adicionar");
        btnAddImgEditUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddImgEditUserActionPerformed(evt);
            }
        });

        jSeparator1.setBackground(new java.awt.Color(240, 240, 240));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pEditUserLayout = new javax.swing.GroupLayout(pEditUser);
        pEditUser.setLayout(pEditUserLayout);
        pEditUserLayout.setHorizontalGroup(
            pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditUserLayout.createSequentialGroup()
                        .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lImagemEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddImgEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEditUserLayout.createSequentialGroup()
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(ldtNasEditUser)
                                    .addComponent(lNomeCompletoEditUser))
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEditUserLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfDataNasEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lCPFEditUser)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCPFEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE))
                                    .addGroup(pEditUserLayout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(tNomeEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE))))
                            .addGroup(pEditUserLayout.createSequentialGroup()
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pEditUserLayout.createSequentialGroup()
                                        .addComponent(lRGEditUser)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfRGEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lEmailEditUser))
                                    .addGroup(pEditUserLayout.createSequentialGroup()
                                        .addComponent(lTelFixoEditUser)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTelFixoEditUser)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEditUserLayout.createSequentialGroup()
                                        .addComponent(lTelCelEditUser)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTelCelEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE))
                                    .addComponent(tEmailEditUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)))
                            .addGroup(pEditUserLayout.createSequentialGroup()
                                .addComponent(lAvisosEditUser)
                                .addGap(0, 250, Short.MAX_VALUE))))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEditUserLayout.createSequentialGroup()
                        .addGap(0, 413, Short.MAX_VALUE)
                        .addComponent(btnEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelarEditUser)))
                .addContainerGap())
        );

        pEditUserLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelarEditUser, btnEditUser});

        pEditUserLayout.setVerticalGroup(
            pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditUserLayout.createSequentialGroup()
                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pEditUserLayout.createSequentialGroup()
                        .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEditUserLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNomeCompletoEditUser)
                                    .addComponent(tNomeEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ldtNasEditUser)
                                    .addComponent(tfDataNasEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCPFEditUser)
                                    .addComponent(tfCPFEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lRGEditUser)
                                    .addComponent(tfRGEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lEmailEditUser)
                                    .addComponent(tEmailEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEditUserLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lImagemEditUser)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEditUserLayout.createSequentialGroup()
                                .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lTelFixoEditUser)
                                    .addComponent(tfTelFixoEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lTelCelEditUser)
                                    .addComponent(tfTelCelEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(lAvisosEditUser))
                            .addGroup(pEditUserLayout.createSequentialGroup()
                                .addComponent(btnAddImgEditUser)
                                .addGap(47, 47, 47)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pEditUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnCancelarEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEditUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        pEditUserLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelarEditUser, btnEditUser});

        pEditarSenha.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Senha"));

        lSenhaAtual.setText("Senha Atual:");

        lSenhaNova.setText("Nova Senha:");

        lSenhaNovaRepetir.setText("Repita a Nova Senha:");

        pfSenhaAtual.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                pfSenhaAtualFocusLost(evt);
            }
        });

        btnCancelar.setText("Cancelar");

        btnAlterar.setText("Alterar");
        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pEditarSenhaLayout = new javax.swing.GroupLayout(pEditarSenha);
        pEditarSenha.setLayout(pEditarSenhaLayout);
        pEditarSenhaLayout.setHorizontalGroup(
            pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditarSenhaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditarSenhaLayout.createSequentialGroup()
                        .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEditarSenhaLayout.createSequentialGroup()
                                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pEditarSenhaLayout.createSequentialGroup()
                                            .addComponent(lSenhaAtual)
                                            .addGap(43, 43, 43))
                                        .addComponent(lSenhaNovaRepetir, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(lSenhaNova))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(pfSenhaNova, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                                    .addComponent(pfSenhaAtual, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pfSenhaNovaRepetir)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEditarSenhaLayout.createSequentialGroup()
                                .addComponent(btnAlterar)
                                .addGap(18, 18, 18)
                                .addComponent(btnCancelar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jSeparator7))
                .addContainerGap())
        );

        pEditarSenhaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {pfSenhaAtual, pfSenhaNova, pfSenhaNovaRepetir});

        pEditarSenhaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnAlterar, btnCancelar});

        pEditarSenhaLayout.setVerticalGroup(
            pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditarSenhaLayout.createSequentialGroup()
                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfSenhaAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lSenhaAtual))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfSenhaNova, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lSenhaNova))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pfSenhaNovaRepetir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lSenhaNovaRepetir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEditarSenhaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnAlterar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pEditarSenhaLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnAlterar, btnCancelar});

        javax.swing.GroupLayout jConfLayout = new javax.swing.GroupLayout(jConf);
        jConf.setLayout(jConfLayout);
        jConfLayout.setHorizontalGroup(
            jConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jConfLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jConfLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pEditarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jConfLayout.setVerticalGroup(
            jConfLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jConfLayout.createSequentialGroup()
                .addComponent(pEditUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pEditarSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jInternalFrameConfiguracoesGeraisLayout = new javax.swing.GroupLayout(jInternalFrameConfiguracoesGerais.getContentPane());
        jInternalFrameConfiguracoesGerais.getContentPane().setLayout(jInternalFrameConfiguracoesGeraisLayout);
        jInternalFrameConfiguracoesGeraisLayout.setHorizontalGroup(
            jInternalFrameConfiguracoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameConfiguracoesGeraisLayout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addComponent(jConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jInternalFrameConfiguracoesGeraisLayout.setVerticalGroup(
            jInternalFrameConfiguracoesGeraisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameConfiguracoesGeraisLayout.createSequentialGroup()
                .addContainerGap(78, Short.MAX_VALUE)
                .addComponent(jConf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(78, Short.MAX_VALUE))
        );

        jInternalFrameConfiguracoesGerais.setBounds(0, 0, 850, 620);
        jdeskTelas.add(jInternalFrameConfiguracoesGerais, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameChamadaBiometrica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pChamadaBio.setBorder(javax.swing.BorderFactory.createTitledBorder("Chamada Biométrica"));

        pAlunoChamadaBio.setBorder(javax.swing.BorderFactory.createTitledBorder("Aluno"));

        lFotoAlunoChamadaBio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N

        lDigitalChamadaBio.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/fingerprint.png"))); // NOI18N

        jSeparator14.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jSeparator15.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lNomeChamadaBio.setText("Nome:");

        lNumeroChamadaBio.setText("Numero:");

        tNomeChamadaBio.setText("User1");
        tNomeChamadaBio.setEnabled(false);

        tNumeroChamadaBio.setText("1");
        tNumeroChamadaBio.setEnabled(false);

        javax.swing.GroupLayout pAlunoChamadaBioLayout = new javax.swing.GroupLayout(pAlunoChamadaBio);
        pAlunoChamadaBio.setLayout(pAlunoChamadaBioLayout);
        pAlunoChamadaBioLayout.setHorizontalGroup(
            pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAlunoChamadaBioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lFotoAlunoChamadaBio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lNomeChamadaBio)
                    .addComponent(lNumeroChamadaBio))
                .addGap(18, 18, 18)
                .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tNumeroChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tNomeChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lDigitalChamadaBio)
                .addContainerGap())
        );

        pAlunoChamadaBioLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tNomeChamadaBio, tNumeroChamadaBio});

        pAlunoChamadaBioLayout.setVerticalGroup(
            pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAlunoChamadaBioLayout.createSequentialGroup()
                .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pAlunoChamadaBioLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(lDigitalChamadaBio))
                    .addGroup(pAlunoChamadaBioLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pAlunoChamadaBioLayout.createSequentialGroup()
                                .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNomeChamadaBio)
                                    .addComponent(tNomeChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNumeroChamadaBio)
                                    .addComponent(tNumeroChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pAlunoChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(lFotoAlunoChamadaBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator14)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pAlunoChamadaBioLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {tNomeChamadaBio, tNumeroChamadaBio});

        pAlunosChamadaBio.setBorder(javax.swing.BorderFactory.createTitledBorder("Alunos Da Sala"));

        jTableChamadaBio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTableChamadaBio.setEnabled(false);
        jTableChamadaBio.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jScrollPane4.setViewportView(jTableChamadaBio);

        javax.swing.GroupLayout pAlunosChamadaBioLayout = new javax.swing.GroupLayout(pAlunosChamadaBio);
        pAlunosChamadaBio.setLayout(pAlunosChamadaBioLayout);
        pAlunosChamadaBioLayout.setHorizontalGroup(
            pAlunosChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAlunosChamadaBioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                .addContainerGap())
        );
        pAlunosChamadaBioLayout.setVerticalGroup(
            pAlunosChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAlunosChamadaBioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addContainerGap())
        );

        pChamadaBioInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Chamada"));

        lTurmaChamadaBio.setText("Turma:");

        lAulaChamadaBio.setText("Aula:");

        cbTurmaChamadaBio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAulaChamadaBio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnIniciarChamadaBio.setText("Iniciar Chamada");

        btnFinalizarChamadaBio.setText("Finalizar Chamada");

        lProfChamadaBio.setText("Professores:");

        tProfChamadaBio.setText("Professor1 e Professor 2");
        tProfChamadaBio.setEnabled(false);

        jSeparator16.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pChamadaBioInicioLayout = new javax.swing.GroupLayout(pChamadaBioInicio);
        pChamadaBioInicio.setLayout(pChamadaBioInicioLayout);
        pChamadaBioInicioLayout.setHorizontalGroup(
            pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChamadaBioInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pChamadaBioInicioLayout.createSequentialGroup()
                        .addComponent(lTurmaChamadaBio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTurmaChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lAulaChamadaBio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAulaChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pChamadaBioInicioLayout.createSequentialGroup()
                        .addComponent(lProfChamadaBio)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tProfChamadaBio)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarChamadaBio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizarChamadaBio, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );

        pChamadaBioInicioLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFinalizarChamadaBio, btnIniciarChamadaBio});

        pChamadaBioInicioLayout.setVerticalGroup(
            pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pChamadaBioInicioLayout.createSequentialGroup()
                .addGroup(pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTurmaChamadaBio)
                    .addComponent(cbTurmaChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarChamadaBio)
                    .addComponent(lAulaChamadaBio)
                    .addComponent(cbAulaChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnFinalizarChamadaBio)
                    .addGroup(pChamadaBioInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lProfChamadaBio)
                        .addComponent(tProfChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        javax.swing.GroupLayout pChamadaBioLayout = new javax.swing.GroupLayout(pChamadaBio);
        pChamadaBio.setLayout(pChamadaBioLayout);
        pChamadaBioLayout.setHorizontalGroup(
            pChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChamadaBioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pAlunosChamadaBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pAlunoChamadaBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pChamadaBioInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        pChamadaBioLayout.setVerticalGroup(
            pChamadaBioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChamadaBioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChamadaBioInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pAlunoChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pAlunosChamadaBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameChamadaBiometricaLayout = new javax.swing.GroupLayout(jInternalFrameChamadaBiometrica.getContentPane());
        jInternalFrameChamadaBiometrica.getContentPane().setLayout(jInternalFrameChamadaBiometricaLayout);
        jInternalFrameChamadaBiometricaLayout.setHorizontalGroup(
            jInternalFrameChamadaBiometricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameChamadaBiometricaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChamadaBio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(245, 245, 245))
        );
        jInternalFrameChamadaBiometricaLayout.setVerticalGroup(
            jInternalFrameChamadaBiometricaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameChamadaBiometricaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChamadaBio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jInternalFrameChamadaBiometrica.setBounds(0, 0, 880, 480);
        jdeskTelas.add(jInternalFrameChamadaBiometrica, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameChamadaManual.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pChamadaManual.setBorder(javax.swing.BorderFactory.createTitledBorder("Chamada Manual"));

        pAlunosChamadaManual.setBorder(javax.swing.BorderFactory.createTitledBorder("Alunos Da Sala"));

        jTableChamadaManual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableChamadaManual.setEnabled(false);
        jTableChamadaManual.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jScrollPane5.setViewportView(jTableChamadaManual);

        javax.swing.GroupLayout pAlunosChamadaManualLayout = new javax.swing.GroupLayout(pAlunosChamadaManual);
        pAlunosChamadaManual.setLayout(pAlunosChamadaManualLayout);
        pAlunosChamadaManualLayout.setHorizontalGroup(
            pAlunosChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAlunosChamadaManualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pAlunosChamadaManualLayout.setVerticalGroup(
            pAlunosChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pAlunosChamadaManualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addContainerGap())
        );

        pChamadaManualInicio.setBorder(javax.swing.BorderFactory.createTitledBorder("Chamada"));

        lTurmaChamadaManual.setText("Turma:");

        lAulaChamadaManual.setText("Aula:");

        cbTurmaChamadaManual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAulaChamadaManual.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnIniciarChamadaManual.setText("Iniciar Chamada");
        btnIniciarChamadaManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarChamadaManualActionPerformed(evt);
            }
        });

        btnFinalizarChamadaManual.setText("Finalizar Chamada");
        btnFinalizarChamadaManual.setEnabled(false);

        lProfChamadaManual.setText("Professores:");

        tProfChamadaManual.setText("Professor1 e Professor 2");
        tProfChamadaManual.setEnabled(false);

        jSeparator19.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pChamadaManualInicioLayout = new javax.swing.GroupLayout(pChamadaManualInicio);
        pChamadaManualInicio.setLayout(pChamadaManualInicioLayout);
        pChamadaManualInicioLayout.setHorizontalGroup(
            pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChamadaManualInicioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pChamadaManualInicioLayout.createSequentialGroup()
                        .addComponent(lTurmaChamadaManual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbTurmaChamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lAulaChamadaManual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAulaChamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pChamadaManualInicioLayout.createSequentialGroup()
                        .addComponent(lProfChamadaManual)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tProfChamadaManual)))
                .addGap(10, 10, 10)
                .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 8, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnIniciarChamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizarChamadaManual))
                .addContainerGap())
        );

        pChamadaManualInicioLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnFinalizarChamadaManual, btnIniciarChamadaManual});

        pChamadaManualInicioLayout.setVerticalGroup(
            pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(pChamadaManualInicioLayout.createSequentialGroup()
                .addGroup(pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTurmaChamadaManual)
                    .addComponent(cbTurmaChamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnIniciarChamadaManual)
                    .addComponent(lAulaChamadaManual)
                    .addComponent(cbAulaChamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pChamadaManualInicioLayout.createSequentialGroup()
                        .addGroup(pChamadaManualInicioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lProfChamadaManual)
                            .addComponent(tProfChamadaManual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3))
                    .addComponent(btnFinalizarChamadaManual, javax.swing.GroupLayout.Alignment.TRAILING)))
        );

        javax.swing.GroupLayout pChamadaManualLayout = new javax.swing.GroupLayout(pChamadaManual);
        pChamadaManual.setLayout(pChamadaManualLayout);
        pChamadaManualLayout.setHorizontalGroup(
            pChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChamadaManualLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(pChamadaManualInicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pAlunosChamadaManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pChamadaManualLayout.setVerticalGroup(
            pChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pChamadaManualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChamadaManualInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pAlunosChamadaManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameChamadaManualLayout = new javax.swing.GroupLayout(jInternalFrameChamadaManual.getContentPane());
        jInternalFrameChamadaManual.getContentPane().setLayout(jInternalFrameChamadaManualLayout);
        jInternalFrameChamadaManualLayout.setHorizontalGroup(
            jInternalFrameChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameChamadaManualLayout.createSequentialGroup()
                .addContainerGap(181, Short.MAX_VALUE)
                .addComponent(pChamadaManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        jInternalFrameChamadaManualLayout.setVerticalGroup(
            jInternalFrameChamadaManualLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameChamadaManualLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pChamadaManual, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jInternalFrameChamadaManual.setBounds(0, 0, 880, 480);
        jdeskTelas.add(jInternalFrameChamadaManual, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameCadastros.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        pMenuInternal.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lCadFuncionario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/worker-48.png"))); // NOI18N
        lCadFuncionario.setToolTipText("Cadastrar Usuário");
        lCadFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lCadFuncionario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lCadFuncionarioMouseClicked(evt);
            }
        });

        lCadMaterias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/books-48.png"))); // NOI18N
        lCadMaterias.setToolTipText("Cadastrar Matérias");
        lCadMaterias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lCadMaterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lCadMateriasMouseClicked(evt);
            }
        });

        lCadTurmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/conference-48.png"))); // NOI18N
        lCadTurmas.setToolTipText("Cadastrar Turmas");
        lCadTurmas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lCadTurmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lCadTurmasMouseClicked(evt);
            }
        });

        lCadCurso.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/school-48.png"))); // NOI18N
        lCadCurso.setToolTipText("Cadastrar Cursos");
        lCadCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lCadCurso.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lCadCursoMouseClicked(evt);
            }
        });

        lCadAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/student-48.png"))); // NOI18N
        lCadAluno.setToolTipText("Cadastrar Alunos");
        lCadAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lCadAluno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lCadAlunoMouseClicked(evt);
            }
        });

        lHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/time.png"))); // NOI18N
        lHorario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lHorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lHorarioMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pMenuInternalLayout = new javax.swing.GroupLayout(pMenuInternal);
        pMenuInternal.setLayout(pMenuInternalLayout);
        pMenuInternalLayout.setHorizontalGroup(
            pMenuInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuInternalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pMenuInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lCadFuncionario)
                    .addComponent(lCadMaterias)
                    .addComponent(lCadTurmas)
                    .addComponent(lCadCurso)
                    .addComponent(lCadAluno)
                    .addComponent(lHorario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pMenuInternalLayout.setVerticalGroup(
            pMenuInternalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMenuInternalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCadFuncionario)
                .addGap(18, 18, 18)
                .addComponent(lCadMaterias)
                .addGap(18, 18, 18)
                .addComponent(lCadTurmas)
                .addGap(18, 18, 18)
                .addComponent(lCadAluno)
                .addGap(18, 18, 18)
                .addComponent(lCadCurso)
                .addGap(18, 18, 18)
                .addComponent(lHorario)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jdeskTelasCadastro.setBackground(new java.awt.Color(19, 134, 255));

        jInternalFrameCadAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lCadUser.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lCadUser.setText("Cadastro de Aluno");

        pDadosAluno.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Aluno"));

        lNomeAluno.setText("Nome Completo:");

        lDtNasAluno.setText("Data de Nascimento:");

        lNumAluno.setText("Numero Da Chamada:");

        lTelAluno.setText("Telefone Fixo:");

        lCelAluno.setText("Telefone Celular:");

        lCidadeAluno.setText("Cidade:");

        lEndAluno.setText("Endereço:");

        lCEPAluno.setText("CEP:");

        lSexoAluno.setText("Sexo:");

        lNmResAluno1.setText("Nome do Responsável 1:");

        lNmResAluno2.setText("Nome do Responsável 2:");

        lImagemAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cbSexoAluno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));

        try {
            tfDtNasAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCelularAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)9####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCEPAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnImagemAluno.setText("Adicionar");

        lDigitalAluno.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/fingerprint.png"))); // NOI18N

        btnAddDigitalAluno.setText("Adicionar");
        btnAddDigitalAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDigitalAlunoActionPerformed(evt);
            }
        });

        btnCadastrarAluno.setText("Cadastrar");
        btnCadastrarAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarAlunoActionPerformed(evt);
            }
        });

        btnLimparAluno.setText("Limpar");
        btnLimparAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparAlunoActionPerformed(evt);
            }
        });

        lRAAluno.setText("RA:");

        try {
            tfRAAluno.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#-UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfRAAluno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfRAAlunoFocusLost(evt);
            }
        });

        lCursoAluno.setText("Curso:");

        cbCursoAluno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoAlunoActionPerformed(evt);
            }
        });

        lTurmaAluno.setText("Turma:");

        javax.swing.GroupLayout pDadosAlunoLayout = new javax.swing.GroupLayout(pDadosAluno);
        pDadosAluno.setLayout(pDadosAlunoLayout);
        pDadosAlunoLayout.setHorizontalGroup(
            pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCadastrarAluno)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnLimparAluno))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator4)
                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                        .addComponent(lNmResAluno2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tRespAluno2))
                                    .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                        .addComponent(lNmResAluno1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tRespAluno1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosAlunoLayout.createSequentialGroup()
                                        .addComponent(lCidadeAluno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tCidadeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lEndAluno)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tEndAluno))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosAlunoLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lDtNasAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfDtNasAluno))
                                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lTelAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfTelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lSexoAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbSexoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lCelAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfCelularAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lCEPAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfCEPAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                        .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lRAAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfRAAluno))
                                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lNumAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinnerNumChamadaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lNomeAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                                .addComponent(lCursoAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbCursoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(lTurmaAluno)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbTurmaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)))))
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImagemAluno, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lImagemAluno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddDigitalAluno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoLayout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(lDigitalAluno))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)))))
                .addContainerGap(1, Short.MAX_VALUE))
        );

        pDadosAlunoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCadastrarAluno, btnLimparAluno});

        pDadosAlunoLayout.setVerticalGroup(
            pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3)
                    .addGroup(pDadosAlunoLayout.createSequentialGroup()
                        .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                .addComponent(lImagemAluno)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImagemAluno)
                                .addGap(18, 18, 18)
                                .addComponent(lDigitalAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pDadosAlunoLayout.createSequentialGroup()
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lRAAluno)
                                    .addComponent(lCursoAluno)
                                    .addComponent(lTurmaAluno)
                                    .addComponent(tfRAAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCursoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTurmaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNomeAluno)
                                    .addComponent(lNumAluno)
                                    .addComponent(spinnerNumChamadaAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tNomeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lDtNasAluno)
                                    .addComponent(tfDtNasAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lSexoAluno)
                                    .addComponent(cbSexoAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lTelAluno)
                                    .addComponent(tfTelAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCelAluno)
                                    .addComponent(tfCelularAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCEPAluno)
                                    .addComponent(tfCEPAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lCidadeAluno)
                                    .addComponent(lEndAluno)
                                    .addComponent(tEndAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tCidadeAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNmResAluno1)
                                    .addComponent(tRespAluno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNmResAluno2)
                                    .addComponent(tRespAluno2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddDigitalAluno)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrarAluno)
                    .addComponent(btnLimparAluno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pDadosAlunoPrincipalLayout = new javax.swing.GroupLayout(pDadosAlunoPrincipal);
        pDadosAlunoPrincipal.setLayout(pDadosAlunoPrincipalLayout);
        pDadosAlunoPrincipalLayout.setHorizontalGroup(
            pDadosAlunoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pDadosAlunoPrincipalLayout.setVerticalGroup(
            pDadosAlunoPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosAluno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane.addTab("Dados Gerais", pDadosAlunoPrincipal);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Cursos"));

        tbCursCadAluno.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Curso"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbCursCadAluno.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jScrollPane6.setViewportView(tbCursCadAluno);
        tbCursCadAluno.getColumnModel().getColumn(0).setResizable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pCursosCadastradosAlunoLayout = new javax.swing.GroupLayout(pCursosCadastradosAluno);
        pCursosCadastradosAluno.setLayout(pCursosCadastradosAlunoLayout);
        pCursosCadastradosAlunoLayout.setHorizontalGroup(
            pCursosCadastradosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCursosCadastradosAlunoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pCursosCadastradosAlunoLayout.setVerticalGroup(
            pCursosCadastradosAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCursosCadastradosAlunoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane.addTab("Cursos Cadastrados", pCursosCadastradosAluno);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lCadUser)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCadUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(jTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameCadAlunoLayout = new javax.swing.GroupLayout(jInternalFrameCadAluno.getContentPane());
        jInternalFrameCadAluno.getContentPane().setLayout(jInternalFrameCadAlunoLayout);
        jInternalFrameCadAlunoLayout.setHorizontalGroup(
            jInternalFrameCadAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadAlunoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameCadAlunoLayout.setVerticalGroup(
            jInternalFrameCadAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadAlunoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jInternalFrameCadAluno.setBounds(0, 0, 880, 460);
        jdeskTelasCadastro.add(jInternalFrameCadAluno, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameCadUser.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lCadAlun.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lCadAlun.setText("Cadastro de Usuário");

        pCadastroUser.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Gerais"));
        pCadastroUser.setPreferredSize(new java.awt.Dimension(800, 347));

        lImagem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N

        lNomeCompletoUser.setText("*Nome Completo:");

        ldtNasUser.setText("Data de Nascimento:");

        lCPFUser.setText("*CPF:");

        lRGUser.setText("RG:");

        lTelFixoUser.setText("Telefone Fixo:");

        lTelCelUser.setText("Telefone Celular:");

        lEmailUser.setText("Email:");

        lTipoCad.setText("*Tipo de Cadastro:");

        lAvisosUser.setForeground(new java.awt.Color(255, 0, 0));
        lAvisosUser.setText("Campos marcados com (*) são obrigatórios.");

        bgTipoCadastroUser.add(jRadioButtonProfessor);
        jRadioButtonProfessor.setText("Professor");

        bgTipoCadastroUser.add(jRadioButtonSecretaria);
        jRadioButtonSecretaria.setText("Secretaria");

        try {
            tfDataNasUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelCelUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("9-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelFixoUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCPFUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfRGUser.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#-UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnCadastrarUser.setText("Cadastrar");
        btnCadastrarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarUserActionPerformed(evt);
            }
        });

        btnCancelarUser.setText("Limpar");
        btnCancelarUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUserActionPerformed(evt);
            }
        });

        btnAddImgUser.setText("Adicionar");

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pCadastroUserLayout = new javax.swing.GroupLayout(pCadastroUser);
        pCadastroUser.setLayout(pCadastroUserLayout);
        pCadastroUserLayout.setHorizontalGroup(
            pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadastroUserLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCadastroUserLayout.createSequentialGroup()
                        .addComponent(btnCadastrarUser)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarUser)
                        .addGap(8, 8, 8))
                    .addGroup(pCadastroUserLayout.createSequentialGroup()
                        .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lImagem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddImgUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCadastroUserLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(pCadastroUserLayout.createSequentialGroup()
                                            .addComponent(lEmailUser)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tEmailUser))
                                        .addGroup(pCadastroUserLayout.createSequentialGroup()
                                            .addComponent(lNomeCompletoUser)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tNomeUser))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCadastroUserLayout.createSequentialGroup()
                                            .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCadastroUserLayout.createSequentialGroup()
                                                    .addComponent(lRGUser)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(tfRGUser, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCadastroUserLayout.createSequentialGroup()
                                                    .addComponent(ldtNasUser)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                    .addComponent(tfDataNasUser, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGap(18, 18, 18)
                                            .addComponent(lCPFUser)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfCPFUser))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCadastroUserLayout.createSequentialGroup()
                                            .addComponent(lTelFixoUser)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfTelFixoUser, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(lTelCelUser)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(tfTelCelUser, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(pCadastroUserLayout.createSequentialGroup()
                                        .addComponent(lTipoCad)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jRadioButtonProfessor)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButtonSecretaria))))
                            .addGroup(pCadastroUserLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lAvisosUser)))))
                .addContainerGap())
        );

        pCadastroUserLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCadastrarUser, btnCancelarUser});

        pCadastroUserLayout.setVerticalGroup(
            pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadastroUserLayout.createSequentialGroup()
                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pCadastroUserLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCadastroUserLayout.createSequentialGroup()
                                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNomeCompletoUser)
                                    .addComponent(tNomeUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ldtNasUser)
                                    .addComponent(tfDataNasUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCPFUser)
                                    .addComponent(tfCPFUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lRGUser)
                                    .addComponent(tfRGUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lTelFixoUser)
                                    .addComponent(tfTelFixoUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lTelCelUser)
                                    .addComponent(tfTelCelUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pCadastroUserLayout.createSequentialGroup()
                                .addComponent(lImagem)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddImgUser)))
                        .addGap(18, 18, 18)
                        .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEmailUser)
                            .addComponent(tEmailUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lTipoCad)
                            .addComponent(jRadioButtonProfessor)
                            .addComponent(jRadioButtonSecretaria))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addComponent(lAvisosUser))
                    .addComponent(jSeparator5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pCadastroUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCadastrarUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pCadastroUserLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCadastrarUser, btnCancelarUser});

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pCadastroUser, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lCadAlun)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCadAlun)
                .addGap(50, 50, 50)
                .addComponent(pCadastroUser, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameCadUserLayout = new javax.swing.GroupLayout(jInternalFrameCadUser.getContentPane());
        jInternalFrameCadUser.getContentPane().setLayout(jInternalFrameCadUserLayout);
        jInternalFrameCadUserLayout.setHorizontalGroup(
            jInternalFrameCadUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadUserLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameCadUserLayout.setVerticalGroup(
            jInternalFrameCadUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadUserLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jInternalFrameCadUser.setBounds(0, 0, 1110, 610);
        jdeskTelasCadastro.add(jInternalFrameCadUser, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameCadCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameCadCurso.setPreferredSize(new java.awt.Dimension(500, 611));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Cadasto de Cursos");

        pCadCurso.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Curso"));

        lNomeCurso.setText("Nome do Curso:");

        lQtdModCurso.setText("Quantidade de Modulos:");

        btnCadCurso.setText("Cadastrar");
        btnCadCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadCursoActionPerformed(evt);
            }
        });

        btnLimparCurso.setText("Limpar");
        btnLimparCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparCursoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCadCursoLayout = new javax.swing.GroupLayout(pCadCurso);
        pCadCurso.setLayout(pCadCursoLayout);
        pCadCursoLayout.setHorizontalGroup(
            pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCadCursoLayout.createSequentialGroup()
                .addGroup(pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pCadCursoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator8))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCadCursoLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCadCursoLayout.createSequentialGroup()
                                .addComponent(lQtdModCurso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(spQtdModCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 106, Short.MAX_VALUE))
                            .addGroup(pCadCursoLayout.createSequentialGroup()
                                .addComponent(lNomeCurso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tNomeCurso))))
                    .addGroup(pCadCursoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnCadCurso)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparCurso)))
                .addContainerGap())
        );

        pCadCursoLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCadCurso, btnLimparCurso});

        pCadCursoLayout.setVerticalGroup(
            pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadCursoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomeCurso)
                    .addComponent(tNomeCurso))
                .addGap(18, 18, 18)
                .addGroup(pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lQtdModCurso)
                    .addComponent(spQtdModCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparCurso)
                    .addComponent(btnCadCurso))
                .addContainerGap())
        );

        pCadCursoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCadCurso, btnLimparCurso});

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pCadCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(50, 50, 50)
                .addComponent(pCadCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameCadCursoLayout = new javax.swing.GroupLayout(jInternalFrameCadCurso.getContentPane());
        jInternalFrameCadCurso.getContentPane().setLayout(jInternalFrameCadCursoLayout);
        jInternalFrameCadCursoLayout.setHorizontalGroup(
            jInternalFrameCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadCursoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameCadCursoLayout.setVerticalGroup(
            jInternalFrameCadCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadCursoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrameCadCurso.setBounds(0, 0, 880, 490);
        jdeskTelasCadastro.add(jInternalFrameCadCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameCadMateria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lCadMat.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lCadMat.setText("Cadastro de Matérias");

        pCadMateria.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações da Matéria"));

        lNomeMateria.setText("Nome da Matéria:");

        lSiglaMateria.setText("Sigla:");

        lProfessorMateria.setText("Professor Principal:");

        lDivisaoTurma.setText("Divisão:");

        btnCadastrarMateria.setText("Cadastrar");
        btnCadastrarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarMateriaActionPerformed(evt);
            }
        });

        btnLimparMateria.setText("Limpar");
        btnLimparMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparMateriaActionPerformed(evt);
            }
        });

        lCursoMateria.setText("Curso:");

        lTurmaMateria.setText("Turma:");

        cbNomeCursoMateria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNomeCursoMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNomeCursoMateriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCadMateriaLayout = new javax.swing.GroupLayout(pCadMateria);
        pCadMateria.setLayout(pCadMateriaLayout);
        pCadMateriaLayout.setHorizontalGroup(
            pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadMateriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator9)
                    .addGroup(pCadMateriaLayout.createSequentialGroup()
                        .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNomeMateria)
                            .addComponent(lSiglaMateria)
                            .addComponent(lProfessorMateria)
                            .addComponent(lDivisaoTurma)
                            .addComponent(lCursoMateria)
                            .addComponent(lTurmaMateria))
                        .addGap(18, 18, 18)
                        .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNomeMateria)
                            .addComponent(cbProfessorMateria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbDivisaoMateria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pCadMateriaLayout.createSequentialGroup()
                                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbNomeCursoMateria, 0, 171, Short.MAX_VALUE)
                                    .addComponent(tSiglaMateria)
                                    .addComponent(cbTurmaMateria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCadMateriaLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCadastrarMateria)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparMateria)))
                .addContainerGap())
        );

        pCadMateriaLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCadastrarMateria, btnLimparMateria});

        pCadMateriaLayout.setVerticalGroup(
            pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadMateriaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCursoMateria)
                    .addComponent(cbNomeCursoMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTurmaMateria)
                    .addComponent(cbTurmaMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomeMateria)
                    .addComponent(tNomeMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSiglaMateria)
                    .addComponent(tSiglaMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProfessorMateria)
                    .addComponent(cbProfessorMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDivisaoTurma)
                    .addComponent(cbDivisaoMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator9, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparMateria)
                    .addComponent(btnCadastrarMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pCadMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lCadMat)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCadMat)
                .addGap(50, 50, 50)
                .addComponent(pCadMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameCadMateriaLayout = new javax.swing.GroupLayout(jInternalFrameCadMateria.getContentPane());
        jInternalFrameCadMateria.getContentPane().setLayout(jInternalFrameCadMateriaLayout);
        jInternalFrameCadMateriaLayout.setHorizontalGroup(
            jInternalFrameCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadMateriaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameCadMateriaLayout.setVerticalGroup(
            jInternalFrameCadMateriaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadMateriaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrameCadMateria.setBounds(0, 0, 355, 447);
        jdeskTelasCadastro.add(jInternalFrameCadMateria, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameCadHorario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameCadHorario.setPreferredSize(new java.awt.Dimension(500, 611));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Cadastro de Horário");

        pHorario.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Horário"));

        jtHorario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Segunda", "Terça", "Quarta", "Quinta", "Sexta"
            }
        ));
        jScrollPane7.setViewportView(jtHorario);

        lhorariocurso.setText("Selecione o curso:");

        lhorarioturma.setText("Selecione a turma:");

        jcbCursoHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbCursoHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbCursoHorarioActionPerformed(evt);
            }
        });

        jcbTurmaHorario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jcbTurmaHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbTurmaHorarioActionPerformed(evt);
            }
        });

        btnSalvarHorario.setText("Salvar");
        btnSalvarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarHorarioActionPerformed(evt);
            }
        });

        btnCancelarHorario.setText("Limpar");
        btnCancelarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarHorarioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pHorarioLayout = new javax.swing.GroupLayout(pHorario);
        pHorario.setLayout(pHorarioLayout);
        pHorarioLayout.setHorizontalGroup(
            pHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator13)
                    .addGroup(pHorarioLayout.createSequentialGroup()
                        .addComponent(lhorariocurso)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbCursoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lhorarioturma)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbTurmaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHorarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvarHorario, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(btnCancelarHorario))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHorarioLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 578, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pHorarioLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelarHorario, btnSalvarHorario});

        pHorarioLayout.setVerticalGroup(
            pHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHorarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lhorariocurso)
                    .addComponent(lhorarioturma)
                    .addComponent(jcbCursoHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jcbTurmaHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator13, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarHorario)
                    .addComponent(btnCancelarHorario))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pHorarioLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelarHorario, btnSalvarHorario});

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(50, 50, 50)
                .addComponent(pHorario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameCadHorarioLayout = new javax.swing.GroupLayout(jInternalFrameCadHorario.getContentPane());
        jInternalFrameCadHorario.getContentPane().setLayout(jInternalFrameCadHorarioLayout);
        jInternalFrameCadHorarioLayout.setHorizontalGroup(
            jInternalFrameCadHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadHorarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameCadHorarioLayout.setVerticalGroup(
            jInternalFrameCadHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameCadHorarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrameCadHorario.setBounds(0, 0, 880, 490);
        jdeskTelasCadastro.add(jInternalFrameCadHorario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameCadTurmas.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        lCadTurm.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lCadTurm.setText("Cadastro de Turmas");

        pCadTurma.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Da Turma"));

        lNomeCursoTurma.setText("Curso:");

        lSiglaTurma.setText("Sigla:");

        lSalaTurma.setText("Sala:");

        lPeriodoTurma.setText("Período:");

        lModuloTurma.setText("Modulo:");

        cbCursoTurma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCursoTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoTurmaActionPerformed(evt);
            }
        });

        cbPeriodoTurma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manha", "Tarde", "Noite" }));

        btnCadTurma.setText("Cadastrar");
        btnCadTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadTurmaActionPerformed(evt);
            }
        });

        btnLimparTurma.setText("Limpar");
        btnLimparTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparTurmaActionPerformed(evt);
            }
        });

        lsemTurma.setText("Semestre:");

        lAnoTurma.setText("Ano:");

        try {
            tfAnoTurma.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfSemTurma.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pCadTurmaLayout = new javax.swing.GroupLayout(pCadTurma);
        pCadTurma.setLayout(pCadTurmaLayout);
        pCadTurmaLayout.setHorizontalGroup(
            pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadTurmaLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pCadTurmaLayout.createSequentialGroup()
                        .addComponent(lNomeCursoTurma)
                        .addGap(4, 4, 4)
                        .addComponent(cbCursoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pCadTurmaLayout.createSequentialGroup()
                        .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(pCadTurmaLayout.createSequentialGroup()
                                .addComponent(lModuloTurma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbModuloTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lPeriodoTurma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbPeriodoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pCadTurmaLayout.createSequentialGroup()
                                .addComponent(lSiglaTurma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tSiglaTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lSalaTurma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tSalaTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pCadTurmaLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lAnoTurma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfAnoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pCadTurmaLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(lsemTurma)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfSemTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCadTurmaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCadTurma)
                .addGap(18, 18, 18)
                .addComponent(btnLimparTurma, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pCadTurmaLayout.setVerticalGroup(
            pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCadTurmaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCadTurmaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lNomeCursoTurma))
                    .addComponent(cbCursoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbModuloTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lPeriodoTurma)
                        .addComponent(cbPeriodoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lAnoTurma)
                        .addComponent(tfAnoTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCadTurmaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lModuloTurma)))
                .addGap(18, 18, 18)
                .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tSiglaTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lSalaTurma)
                        .addComponent(tSalaTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lsemTurma)
                        .addComponent(tfSemTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCadTurmaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lSiglaTurma)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCadTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparTurma)
                    .addComponent(btnCadTurma))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(pCadTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lCadTurm)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lCadTurm)
                .addGap(50, 50, 50)
                .addComponent(pCadTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameCadTurmasLayout = new javax.swing.GroupLayout(jInternalFrameCadTurmas.getContentPane());
        jInternalFrameCadTurmas.getContentPane().setLayout(jInternalFrameCadTurmasLayout);
        jInternalFrameCadTurmasLayout.setHorizontalGroup(
            jInternalFrameCadTurmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadTurmasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameCadTurmasLayout.setVerticalGroup(
            jInternalFrameCadTurmasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadTurmasLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jInternalFrameCadTurmas.setBounds(0, 0, 391, 401);
        jdeskTelasCadastro.add(jInternalFrameCadTurmas, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jInternalFrameCadastrosLayout = new javax.swing.GroupLayout(jInternalFrameCadastros.getContentPane());
        jInternalFrameCadastros.getContentPane().setLayout(jInternalFrameCadastrosLayout);
        jInternalFrameCadastrosLayout.setHorizontalGroup(
            jInternalFrameCadastrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameCadastrosLayout.createSequentialGroup()
                .addComponent(jdeskTelasCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pMenuInternal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jInternalFrameCadastrosLayout.setVerticalGroup(
            jInternalFrameCadastrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameCadastrosLayout.createSequentialGroup()
                .addContainerGap(94, Short.MAX_VALUE)
                .addComponent(pMenuInternal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(93, Short.MAX_VALUE))
            .addComponent(jdeskTelasCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
        );

        jInternalFrameCadastros.setBounds(0, 0, 670, 630);
        jdeskTelas.add(jInternalFrameCadastros, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameAlterarHorario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameAlterarHorario.setVisible(false);

        pHorarioEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações do Horário"));

        jtHorarioEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Segunda", "Terça", "Quarta", "Quinta", "Sexta"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jtHorarioEdit.setColumnSelectionAllowed(true);
        jtHorarioEdit.getTableHeader().setReorderingAllowed(false);
        jScrollPane14.setViewportView(jtHorarioEdit);
        jtHorarioEdit.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btnVoltarHorarioEdit.setText("Voltar");
        btnVoltarHorarioEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarHorarioEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pHorarioEditLayout = new javax.swing.GroupLayout(pHorarioEdit);
        pHorarioEdit.setLayout(pHorarioEditLayout);
        pHorarioEditLayout.setHorizontalGroup(
            pHorarioEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHorarioEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pHorarioEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator25)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pHorarioEditLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnVoltarHorarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane14, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addContainerGap())
        );
        pHorarioEditLayout.setVerticalGroup(
            pHorarioEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pHorarioEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator25, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnVoltarHorarioEdit)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pHorarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pHorarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameAlterarHorarioLayout = new javax.swing.GroupLayout(jInternalFrameAlterarHorario.getContentPane());
        jInternalFrameAlterarHorario.getContentPane().setLayout(jInternalFrameAlterarHorarioLayout);
        jInternalFrameAlterarHorarioLayout.setHorizontalGroup(
            jInternalFrameAlterarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameAlterarHorarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameAlterarHorarioLayout.setVerticalGroup(
            jInternalFrameAlterarHorarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarHorarioLayout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jInternalFrameAlterarHorario.setBounds(20, 570, 666, 296);
        jdeskTelas.add(jInternalFrameAlterarHorario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameAlterarFuncionario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameAlterarFuncionario.setVisible(false);

        pEditFuncionario.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Gerais"));
        pEditFuncionario.setPreferredSize(new java.awt.Dimension(800, 347));

        lImagemEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N

        lNomeCompletoUserEdit.setText("*Nome Completo:");

        ldtNasUserEdit.setText("Data de Nascimento:");

        lCPFUserEdit.setText("*CPF:");

        lRGUser1Edit.setText("RG:");

        lTelFixoUserEdit.setText("Telefone Fixo:");

        lTelCelUserEdit.setText("Telefone Celular:");

        lEmailUserEdit.setText("Email:");

        lAvisosUser1.setForeground(new java.awt.Color(255, 0, 0));
        lAvisosUser1.setText("Campos marcados com (*) são obrigatórios.");

        try {
            tfDataNasUserEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelCelUserEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("9-####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelFixoUserEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCPFUserEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfRGUserEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#-UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnEditUser1.setText("Alterar");
        btnEditUser1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUser1ActionPerformed(evt);
            }
        });

        btnCancelarUserEdit.setText("Cancelar");
        btnCancelarUserEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarUserEditActionPerformed(evt);
            }
        });

        btnAddImgUserEdit.setText("Adicionar");

        jSeparator18.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout pEditFuncionarioLayout = new javax.swing.GroupLayout(pEditFuncionario);
        pEditFuncionario.setLayout(pEditFuncionarioLayout);
        pEditFuncionarioLayout.setHorizontalGroup(
            pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jSeparator20, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                        .addComponent(btnEditUser1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCancelarUserEdit)
                        .addGap(8, 8, 8))
                    .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                        .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lImagemEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAddImgUserEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                        .addComponent(lEmailUserEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tEmailUserEdit))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEditFuncionarioLayout.createSequentialGroup()
                                        .addComponent(lNomeCompletoUserEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tNomeUserEdit))
                                    .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                        .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEditFuncionarioLayout.createSequentialGroup()
                                                .addComponent(lRGUser1Edit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfRGUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEditFuncionarioLayout.createSequentialGroup()
                                                .addComponent(ldtNasUserEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfDataNasUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addComponent(lCPFUserEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCPFUserEdit))
                                    .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                        .addComponent(lTelFixoUserEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTelFixoUserEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lTelCelUserEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfTelCelUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(lAvisosUser1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pEditFuncionarioLayout.setVerticalGroup(
            pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNomeCompletoUserEdit)
                                    .addComponent(tNomeUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ldtNasUserEdit)
                                    .addComponent(tfDataNasUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCPFUserEdit)
                                    .addComponent(tfCPFUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lRGUser1Edit)
                                    .addComponent(tfRGUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lTelFixoUserEdit)
                                    .addComponent(tfTelFixoUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lTelCelUserEdit)
                                    .addComponent(tfTelCelUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pEditFuncionarioLayout.createSequentialGroup()
                                .addComponent(lImagemEdit)
                                .addGap(18, 18, 18)
                                .addComponent(btnAddImgUserEdit)))
                        .addGap(18, 18, 18)
                        .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lEmailUserEdit)
                            .addComponent(tEmailUserEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lAvisosUser1))
                    .addComponent(jSeparator18))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator20, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pEditFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEditUser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelarUserEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pDadosFuncionarioEditLayout = new javax.swing.GroupLayout(pDadosFuncionarioEdit);
        pDadosFuncionarioEdit.setLayout(pDadosFuncionarioEditLayout);
        pDadosFuncionarioEditLayout.setHorizontalGroup(
            pDadosFuncionarioEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosFuncionarioEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pEditFuncionario, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE)
                .addContainerGap())
        );
        pDadosFuncionarioEditLayout.setVerticalGroup(
            pDadosFuncionarioEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosFuncionarioEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pEditFuncionario, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jInternalFrameAlterarFuncionarioLayout = new javax.swing.GroupLayout(jInternalFrameAlterarFuncionario.getContentPane());
        jInternalFrameAlterarFuncionario.getContentPane().setLayout(jInternalFrameAlterarFuncionarioLayout);
        jInternalFrameAlterarFuncionarioLayout.setHorizontalGroup(
            jInternalFrameAlterarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarFuncionarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pDadosFuncionarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameAlterarFuncionarioLayout.setVerticalGroup(
            jInternalFrameAlterarFuncionarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarFuncionarioLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(pDadosFuncionarioEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jInternalFrameAlterarFuncionario.setBounds(650, 30, 667, 424);
        jdeskTelas.add(jInternalFrameAlterarFuncionario, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameAlterarTurma.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameAlterarTurma.setVisible(false);

        pEditTurma.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações Da Turma"));

        lNomeCursoTurmaEdit.setText("Curso:");

        lSiglaTurmaEdit.setText("Sigla:");

        lSalaTurmaEdit.setText("Sala:");

        lPeriodoTurmaEdit.setText("Período:");

        lModuloTurmaEdit.setText("Modulo:");

        cbCursoTurmaEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCursoTurmaEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoTurmaEditActionPerformed(evt);
            }
        });

        cbPeriodoTurmaEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manha", "Tarde", "Noite" }));

        btnAlterarTurma.setText("Alterar");
        btnAlterarTurma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarTurmaActionPerformed(evt);
            }
        });

        btnLimparTurmaEdit.setText("Cancelar");
        btnLimparTurmaEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparTurmaEditActionPerformed(evt);
            }
        });

        lsemTurmaEdit.setText("Semestre:");

        lAnoTurmaEdit.setText("Ano:");

        try {
            tfAnoTurmaEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfSemTurmaEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        javax.swing.GroupLayout pEditTurmaLayout = new javax.swing.GroupLayout(pEditTurma);
        pEditTurma.setLayout(pEditTurmaLayout);
        pEditTurmaLayout.setHorizontalGroup(
            pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTurmaLayout.createSequentialGroup()
                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pEditTurmaLayout.createSequentialGroup()
                                .addComponent(lNomeCursoTurmaEdit)
                                .addGap(4, 4, 4)
                                .addComponent(cbCursoTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pEditTurmaLayout.createSequentialGroup()
                                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                                        .addComponent(lModuloTurmaEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbModuloTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lPeriodoTurmaEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbPeriodoTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pEditTurmaLayout.createSequentialGroup()
                                        .addComponent(lSiglaTurmaEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tSiglaTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lSalaTurmaEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tSalaTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(lAnoTurmaEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tfAnoTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(lsemTurmaEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfSemTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 317, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEditTurmaLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnAlterarTurma)
                                .addGap(18, 18, 18)
                                .addComponent(btnLimparTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pEditTurmaLayout.setVerticalGroup(
            pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditTurmaLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lNomeCursoTurmaEdit))
                    .addComponent(cbCursoTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbModuloTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lPeriodoTurmaEdit)
                        .addComponent(cbPeriodoTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lAnoTurmaEdit)
                        .addComponent(tfAnoTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lModuloTurmaEdit)))
                .addGap(18, 18, 18)
                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(tSiglaTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lSalaTurmaEdit)
                        .addComponent(tSalaTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lsemTurmaEdit)
                        .addComponent(tfSemTurmaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pEditTurmaLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(lSiglaTurmaEdit)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator22, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEditTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparTurmaEdit)
                    .addComponent(btnAlterarTurma))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pPresencaAtualEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Presença Atual"));

        tbPresencaEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"EDITAR PRESENÇA", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane11.setViewportView(tbPresencaEdit);

        javax.swing.GroupLayout pPresencaAtualEditLayout = new javax.swing.GroupLayout(pPresencaAtualEdit);
        pPresencaAtualEdit.setLayout(pPresencaAtualEditLayout);
        pPresencaAtualEditLayout.setHorizontalGroup(
            pPresencaAtualEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPresencaAtualEditLayout.createSequentialGroup()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                .addContainerGap())
        );
        pPresencaAtualEditLayout.setVerticalGroup(
            pPresencaAtualEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPresencaAtualEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pEditTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pPresencaAtualEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pEditTurma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pPresencaAtualEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameAlterarTurmaLayout = new javax.swing.GroupLayout(jInternalFrameAlterarTurma.getContentPane());
        jInternalFrameAlterarTurma.getContentPane().setLayout(jInternalFrameAlterarTurmaLayout);
        jInternalFrameAlterarTurmaLayout.setHorizontalGroup(
            jInternalFrameAlterarTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarTurmaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameAlterarTurmaLayout.setVerticalGroup(
            jInternalFrameAlterarTurmaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarTurmaLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jInternalFrameAlterarTurma.setBounds(60, 90, 601, 604);
        jdeskTelas.add(jInternalFrameAlterarTurma, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameAlterarMaterias.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameAlterarMaterias.setVisible(false);

        pEditMateria1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações da Matéria"));

        lNomeMateriaEdit.setText("Nome da Matéria:");

        lSiglaMateriaEdit.setText("Sigla:");

        lProfessorMateriaEdit.setText("Professor Principal:");

        lDivisaoTurmaEdit.setText("Divisão:");

        btnEditMateria1.setText("Alterar");
        btnEditMateria1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditMateria1ActionPerformed(evt);
            }
        });

        btnLimparMateriaEdit.setText("Cancelar");
        btnLimparMateriaEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparMateriaEditActionPerformed(evt);
            }
        });

        lCursoMateriaEdit.setText("Curso:");

        lTurmaMateriaEdit.setText("Turma:");

        cbNomeCursoMateriaEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbNomeCursoMateriaEdit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbNomeCursoMateriaEditMouseClicked(evt);
            }
        });
        cbNomeCursoMateriaEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNomeCursoMateriaEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pEditMateria1Layout = new javax.swing.GroupLayout(pEditMateria1);
        pEditMateria1.setLayout(pEditMateria1Layout);
        pEditMateria1Layout.setHorizontalGroup(
            pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditMateria1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator21, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addGroup(pEditMateria1Layout.createSequentialGroup()
                        .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lNomeMateriaEdit)
                            .addComponent(lSiglaMateriaEdit)
                            .addComponent(lProfessorMateriaEdit)
                            .addComponent(lDivisaoTurmaEdit)
                            .addComponent(lCursoMateriaEdit)
                            .addComponent(lTurmaMateriaEdit))
                        .addGap(18, 18, 18)
                        .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNomeMateriaEdit, javax.swing.GroupLayout.DEFAULT_SIZE, 193, Short.MAX_VALUE)
                            .addComponent(cbProfessorMateriaEdit, 0, 193, Short.MAX_VALUE)
                            .addComponent(cbDivisaoMateriaEdit, 0, 193, Short.MAX_VALUE)
                            .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(cbNomeCursoMateriaEdit, 0, 193, Short.MAX_VALUE)
                                .addComponent(cbTurmaMateriaEdit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tSiglaMateriaEdit))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pEditMateria1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditMateria1)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimparMateriaEdit)))
                .addContainerGap())
        );
        pEditMateria1Layout.setVerticalGroup(
            pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pEditMateria1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lCursoMateriaEdit)
                    .addComponent(cbNomeCursoMateriaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lTurmaMateriaEdit)
                    .addComponent(cbTurmaMateriaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomeMateriaEdit)
                    .addComponent(tNomeMateriaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lSiglaMateriaEdit)
                    .addComponent(tSiglaMateriaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lProfessorMateriaEdit)
                    .addComponent(cbProfessorMateriaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lDivisaoTurmaEdit)
                    .addComponent(cbDivisaoMateriaEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addComponent(jSeparator21, javax.swing.GroupLayout.PREFERRED_SIZE, 5, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pEditMateria1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimparMateriaEdit)
                    .addComponent(btnEditMateria1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameAlterarMateriasLayout = new javax.swing.GroupLayout(jInternalFrameAlterarMaterias.getContentPane());
        jInternalFrameAlterarMaterias.getContentPane().setLayout(jInternalFrameAlterarMateriasLayout);
        jInternalFrameAlterarMateriasLayout.setHorizontalGroup(
            jInternalFrameAlterarMateriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarMateriasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pEditMateria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameAlterarMateriasLayout.setVerticalGroup(
            jInternalFrameAlterarMateriasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarMateriasLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(pEditMateria1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jInternalFrameAlterarMaterias.setBounds(40, 180, 357, 366);
        jdeskTelas.add(jInternalFrameAlterarMaterias, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameAlterarAluno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameAlterarAluno.setVisible(false);

        pDadosAlunoEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados Do Aluno"));

        lNomeAlunoEdit.setText("Nome Completo:");

        lDtNasAlunoEdit.setText("Data de Nascimento:");

        lNumAlunoEdit.setText("Numero Da Chamada:");

        lTelAlunoEdit.setText("Telefone Fixo:");

        lCelAlunoEdit.setText("Telefone Celular:");

        lCidadeAlunoEdit.setText("Cidade:");

        lEndAlunoEdit.setText("Endereço:");

        lCEPAlunoEdit.setText("CEP:");

        lSexoAlunoEdit.setText("Sexo:");

        lNmResAlunoEdit.setText("Nome do Responsável 1:");

        lNmResAluno2Edit.setText("Nome do Responsável 2:");

        lImagemAlunoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/usuario_sem_imagem_adicionada.jpg"))); // NOI18N

        jSeparator23.setOrientation(javax.swing.SwingConstants.VERTICAL);

        cbSexoAlunoEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Feminino" }));

        try {
            tfDtNasAlunoEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfTelAlunoEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCelularAlunoEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)9####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            tfCEPAlunoEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("#####-###")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        btnImagemAlunoEdit.setText("Adicionar");

        lDigitalAlunoEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/fingerprint.png"))); // NOI18N

        btnAddDigitalAlunoEdit.setText("Adicionar");
        btnAddDigitalAlunoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddDigitalAlunoEditActionPerformed(evt);
            }
        });

        btnAlterarAlunoEdit.setText("Alterar");
        btnAlterarAlunoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarAlunoEditActionPerformed(evt);
            }
        });

        btnCancelarAlunoEdit.setText("Cancelar");
        btnCancelarAlunoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarAlunoEditActionPerformed(evt);
            }
        });

        lRAAlunoEdit.setText("RA:");

        try {
            tfRAAlunoEdit.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##.###.###-#-UU")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        tfRAAlunoEdit.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tfRAAlunoEditFocusLost(evt);
            }
        });

        lCursoAlunoEdit.setText("Curso:");

        cbCursoAlunoEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbCursoAlunoEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCursoAlunoEditActionPerformed(evt);
            }
        });

        lTurmaAlunoEdit.setText("Turma:");

        cbTurmaAlunoEdit.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout pDadosAlunoEditLayout = new javax.swing.GroupLayout(pDadosAlunoEdit);
        pDadosAlunoEdit.setLayout(pDadosAlunoEditLayout);
        pDadosAlunoEditLayout.setHorizontalGroup(
            pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoEditLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAlterarAlunoEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelarAlunoEdit))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoEditLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator24)
                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                        .addComponent(lNmResAluno2Edit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tRespAluno2Edit))
                                    .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                        .addComponent(lNmResAlunoEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tRespAlunoEdit))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosAlunoEditLayout.createSequentialGroup()
                                        .addComponent(lCidadeAlunoEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tCidadeAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lEndAlunoEdit)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tEndAlunoEdit))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosAlunoEditLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lDtNasAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfDtNasAlunoEdit))
                                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lTelAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfTelAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lSexoAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbSexoAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lCelAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfCelularAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lCEPAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfCEPAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                        .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lRAAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tfRAAlunoEdit))
                                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lNumAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(spinnerNumChamadaAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lNomeAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(tNomeAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                                .addComponent(lCursoAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbCursoAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(27, 27, 27)
                                                .addComponent(lTurmaAlunoEdit)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(cbTurmaAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)))))
                                .addGap(6, 6, 6)
                                .addComponent(jSeparator23, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnImagemAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lImagemAlunoEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btnAddDigitalAlunoEdit, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pDadosAlunoEditLayout.createSequentialGroup()
                                            .addGap(25, 25, 25)
                                            .addComponent(lDigitalAlunoEdit))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)))))
                .addContainerGap(1, Short.MAX_VALUE))
        );
        pDadosAlunoEditLayout.setVerticalGroup(
            pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator23)
                    .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                        .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                .addComponent(lImagemAlunoEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnImagemAlunoEdit)
                                .addGap(18, 18, 18)
                                .addComponent(lDigitalAlunoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(pDadosAlunoEditLayout.createSequentialGroup()
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lRAAlunoEdit)
                                    .addComponent(lCursoAlunoEdit)
                                    .addComponent(lTurmaAlunoEdit)
                                    .addComponent(tfRAAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbCursoAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbTurmaAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNomeAlunoEdit)
                                    .addComponent(lNumAlunoEdit)
                                    .addComponent(spinnerNumChamadaAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tNomeAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lDtNasAlunoEdit)
                                    .addComponent(tfDtNasAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lSexoAlunoEdit)
                                    .addComponent(cbSexoAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lTelAlunoEdit)
                                    .addComponent(tfTelAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCelAlunoEdit)
                                    .addComponent(tfCelularAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lCEPAlunoEdit)
                                    .addComponent(tfCEPAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lCidadeAlunoEdit)
                                    .addComponent(lEndAlunoEdit)
                                    .addComponent(tEndAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tCidadeAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNmResAlunoEdit)
                                    .addComponent(tRespAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lNmResAluno2Edit)
                                    .addComponent(tRespAluno2Edit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAddDigitalAlunoEdit)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator24, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAlterarAlunoEdit)
                    .addComponent(btnCancelarAlunoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout pDadosAlunoPrincipal1Layout = new javax.swing.GroupLayout(pDadosAlunoPrincipal1);
        pDadosAlunoPrincipal1.setLayout(pDadosAlunoPrincipal1Layout);
        pDadosAlunoPrincipal1Layout.setHorizontalGroup(
            pDadosAlunoPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoPrincipal1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pDadosAlunoPrincipal1Layout.setVerticalGroup(
            pDadosAlunoPrincipal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosAlunoPrincipal1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosAlunoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Dados Gerais", pDadosAlunoPrincipal1);

        pCursosCadastradosAlunoEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Cursos"));

        tbCursCadAlunoEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbCursCadAlunoEdit.setSelectionBackground(new java.awt.Color(63, 138, 217));
        jScrollPane12.setViewportView(tbCursCadAlunoEdit);

        javax.swing.GroupLayout pCursosCadastradosAlunoEditLayout = new javax.swing.GroupLayout(pCursosCadastradosAlunoEdit);
        pCursosCadastradosAlunoEdit.setLayout(pCursosCadastradosAlunoEditLayout);
        pCursosCadastradosAlunoEditLayout.setHorizontalGroup(
            pCursosCadastradosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCursosCadastradosAlunoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
        );
        pCursosCadastradosAlunoEditLayout.setVerticalGroup(
            pCursosCadastradosAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCursosCadastradosAlunoEditLayout.createSequentialGroup()
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 351, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pCursosCadastradosAluno1Layout = new javax.swing.GroupLayout(pCursosCadastradosAluno1);
        pCursosCadastradosAluno1.setLayout(pCursosCadastradosAluno1Layout);
        pCursosCadastradosAluno1Layout.setHorizontalGroup(
            pCursosCadastradosAluno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCursosCadastradosAluno1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pCursosCadastradosAlunoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pCursosCadastradosAluno1Layout.setVerticalGroup(
            pCursosCadastradosAluno1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCursosCadastradosAluno1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pCursosCadastradosAlunoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Cursos Cadastrados", pCursosCadastradosAluno1);

        pMateriasCadastradasAlunoEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Status das Materias"));

        tbAlunoMateriaEdit.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane13.setViewportView(tbAlunoMateriaEdit);

        javax.swing.GroupLayout pMateriasCadastradasAlunoEditLayout = new javax.swing.GroupLayout(pMateriasCadastradasAlunoEdit);
        pMateriasCadastradasAlunoEdit.setLayout(pMateriasCadastradasAlunoEditLayout);
        pMateriasCadastradasAlunoEditLayout.setHorizontalGroup(
            pMateriasCadastradasAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMateriasCadastradasAlunoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 696, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMateriasCadastradasAlunoEditLayout.setVerticalGroup(
            pMateriasCadastradasAlunoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMateriasCadastradasAlunoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout pMateriasCadAlunEditLayout = new javax.swing.GroupLayout(pMateriasCadAlunEdit);
        pMateriasCadAlunEdit.setLayout(pMateriasCadAlunEditLayout);
        pMateriasCadAlunEditLayout.setHorizontalGroup(
            pMateriasCadAlunEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMateriasCadAlunEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pMateriasCadastradasAlunoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pMateriasCadAlunEditLayout.setVerticalGroup(
            pMateriasCadAlunEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pMateriasCadAlunEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pMateriasCadastradasAlunoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Materias Cadastradas", pMateriasCadAlunEdit);

        javax.swing.GroupLayout jInternalFrameAlterarAlunoLayout = new javax.swing.GroupLayout(jInternalFrameAlterarAluno.getContentPane());
        jInternalFrameAlterarAluno.getContentPane().setLayout(jInternalFrameAlterarAlunoLayout);
        jInternalFrameAlterarAlunoLayout.setHorizontalGroup(
            jInternalFrameAlterarAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarAlunoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 753, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrameAlterarAlunoLayout.setVerticalGroup(
            jInternalFrameAlterarAlunoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarAlunoLayout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jInternalFrameAlterarAluno.setBounds(30, 30, 775, 501);
        jdeskTelas.add(jInternalFrameAlterarAluno, javax.swing.JLayeredPane.DEFAULT_LAYER);

        jInternalFrameAlterarCurso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jInternalFrameAlterarCurso.setVisible(false);

        pDadosCursoEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Curso"));

        lNomeCursoEdit.setText("Nome:");

        lModuloCursoEdit.setText("Modulos:");

        btnCursoAlterarEdit.setText("Editar");
        btnCursoAlterarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCursoAlterarEditActionPerformed(evt);
            }
        });

        btnCursoCancelarEdit.setText("Cancelar");
        btnCursoCancelarEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCursoCancelarEditActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pDadosCursoEditLayout = new javax.swing.GroupLayout(pDadosCursoEdit);
        pDadosCursoEdit.setLayout(pDadosCursoEditLayout);
        pDadosCursoEditLayout.setHorizontalGroup(
            pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                                .addComponent(lNomeCursoEdit)
                                .addGap(18, 18, 18)
                                .addComponent(tNomeCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                                .addComponent(lModuloCursoEdit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tModCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                        .addComponent(jSeparator17)
                        .addContainerGap())))
            .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(btnCursoAlterarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCursoCancelarEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pDadosCursoEditLayout.setVerticalGroup(
            pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDadosCursoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lNomeCursoEdit)
                    .addComponent(tNomeCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lModuloCursoEdit)
                    .addComponent(tModCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pDadosCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCursoAlterarEdit)
                    .addComponent(btnCursoCancelarEdit))
                .addContainerGap())
        );

        pTurmaCursoEdit.setBorder(javax.swing.BorderFactory.createTitledBorder("Turmas Ativas"));

        jtTurmasAtivas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane8.setViewportView(jtTurmasAtivas);

        javax.swing.GroupLayout pTurmaCursoEditLayout = new javax.swing.GroupLayout(pTurmaCursoEdit);
        pTurmaCursoEdit.setLayout(pTurmaCursoEditLayout);
        pTurmaCursoEditLayout.setHorizontalGroup(
            pTurmaCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pTurmaCursoEditLayout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pTurmaCursoEditLayout.setVerticalGroup(
            pTurmaCursoEditLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTurmaCursoEditLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pTurmaCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pDadosCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDadosCursoEdit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pTurmaCursoEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrameAlterarCursoLayout = new javax.swing.GroupLayout(jInternalFrameAlterarCurso.getContentPane());
        jInternalFrameAlterarCurso.getContentPane().setLayout(jInternalFrameAlterarCursoLayout);
        jInternalFrameAlterarCursoLayout.setHorizontalGroup(
            jInternalFrameAlterarCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrameAlterarCursoLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jInternalFrameAlterarCursoLayout.setVerticalGroup(
            jInternalFrameAlterarCursoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrameAlterarCursoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jInternalFrameAlterarCurso.setBounds(90, 40, 540, 520);
        jdeskTelas.add(jInternalFrameAlterarCurso, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pBanner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jdeskTelas, javax.swing.GroupLayout.DEFAULT_SIZE, 677, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pBanner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 30, Short.MAX_VALUE)
                        .addComponent(pMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 30, Short.MAX_VALUE))
                    .addComponent(jdeskTelas, javax.swing.GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbProfessorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbProfessorActionPerformed
        pesquisarProfessor();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbProfessorActionPerformed

    private void rbAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbAlunoActionPerformed
        pesquisarAluno();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbAlunoActionPerformed

    private void rbSecretariaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbSecretariaActionPerformed
        pesquisarSecretaria();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbSecretariaActionPerformed

    private void rbMateriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMateriasActionPerformed
        pesquisarMaterias();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbMateriasActionPerformed

    private void rbTurmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbTurmasActionPerformed
        pesquisarTurmas();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbTurmasActionPerformed

    private void rbCursosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbCursosActionPerformed
        pesquisarCurso();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbCursosActionPerformed

    private void rbHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbHorariosActionPerformed
        pesquisarHorario();
        // TODO add your handling code here:
    }//GEN-LAST:event_rbHorariosActionPerformed

    private void btnAddImgEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddImgEditUserActionPerformed
        jFileChooser.setFileSelectionMode(jFileChooser.FILES_AND_DIRECTORIES);
        int i = jFileChooser.showOpenDialog(null);
        if (i == 1) {
            System.out.println("Clicou em cancelar");
        } else {
            File arquivo = jFileChooser.getSelectedFile();
            System.out.println(arquivo.getPath().replace("\\", "/"));
        }


        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddImgEditUserActionPerformed

    private void btn_pesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pesquisaActionPerformed
        jtResultadoAtivo.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtResultadoInativos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        escondeTudo();
        limparPesquisa();
        jInternalFramePesquisa.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_pesquisaActionPerformed

    private void btn_chamadaBiometricaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chamadaBiometricaActionPerformed

        escondeTudo();
        jInternalFrameChamadaBiometrica.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_chamadaBiometricaActionPerformed

    private void btn_chamadaManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chamadaManualActionPerformed

        escondeTudo();
        jInternalFrameChamadaManual.setVisible(true);
        turmaChamada();
        setAulaChamada();
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_chamadaManualActionPerformed

    private void btnConfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfActionPerformed

        escondeTudo();
        jInternalFrameConfiguracoesGerais.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConfActionPerformed

    private void btn_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_addActionPerformed

        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        // TODO add your handling code here:   
    }//GEN-LAST:event_btn_addActionPerformed

    private void btnCadastrarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarUserActionPerformed
        criarUsuario();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarUserActionPerformed

    private void btn_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_sairActionPerformed
        encerrarConexao();
        System.exit(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_sairActionPerformed

    private void btn_LogoffActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_LogoffActionPerformed
        encerrarConexao();
        Login lf = new Login();
        this.dispose();
        lf.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_LogoffActionPerformed

    private void btnLimparCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparCursoActionPerformed
        limparCurso();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparCursoActionPerformed

    private void btnCadCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadCursoActionPerformed
        criarCurso();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadCursoActionPerformed

    private void btnLimparMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMateriaActionPerformed
        limparMateria();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparMateriaActionPerformed

    private void btnCadastrarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarMateriaActionPerformed
        criarMateria();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarMateriaActionPerformed

    private void btnCadTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadTurmaActionPerformed
        criarTurma();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadTurmaActionPerformed

    private void btnLimparTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparTurmaActionPerformed
        limparTurma();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparTurmaActionPerformed

    private void btnCancelarUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUserActionPerformed
        limparUser();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarUserActionPerformed

    private void btnCadastrarAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarAlunoActionPerformed
        criarAluno();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCadastrarAlunoActionPerformed

    private void btnAddDigitalAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDigitalAlunoActionPerformed

        MainForm mf = new MainForm();
        mf.setVisible(false);
        mf.onEnroll();
        mf.onVerify();


        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddDigitalAlunoActionPerformed

    private void btnLimparAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparAlunoActionPerformed
        limparAluno();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparAlunoActionPerformed

    private void lImageBannerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lImageBannerMouseClicked

        JOptionPane.showMessageDialog(null, "Criado pela: Equipe Pindorama");
        // TODO add your handling code here:
    }//GEN-LAST:event_lImageBannerMouseClicked

    private void btnAlterarHorariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarHorariosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarHorariosActionPerformed

    private void cbNomeCursoMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNomeCursoMateriaActionPerformed
        setTurmaMateria();
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNomeCursoMateriaActionPerformed

    private void cbCursoTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoTurmaActionPerformed
        addModuloTurma();
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoTurmaActionPerformed

    private void tPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPesquisaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPesquisaActionPerformed

    private void btnPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisaActionPerformed
        pesquisa();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnPesquisaActionPerformed

    private void lCadAlunoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lCadAlunoMouseClicked
        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        jInternalFrameCadAluno.setVisible(true);
        try {
            limparArrays();
            lerCursoCb();
            lerTurmaCb();
            addModuloTurma();
            setTurmaCurso();
        } catch (Exception e) {
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_lCadAlunoMouseClicked

    private void lCadFuncionarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lCadFuncionarioMouseClicked
        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        jInternalFrameCadUser.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_lCadFuncionarioMouseClicked

    private void lCadMateriasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lCadMateriasMouseClicked
        try {
            limparArrays();
            lerCursoCb();
            lerProfCb();
            setTurmaMateria();
        } catch (Exception e) {
        }
        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        jInternalFrameCadMateria.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lCadMateriasMouseClicked

    private void lCadTurmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lCadTurmasMouseClicked
        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        jInternalFrameCadTurmas.setVisible(true);
        try {
            limparArrays();
            lerCursoCb();
            addModuloTurma();
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_lCadTurmasMouseClicked

    private void lCadCursoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lCadCursoMouseClicked
        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        jInternalFrameCadCurso.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_lCadCursoMouseClicked

    private void tfRAAlunoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfRAAlunoFocusLost
        verfAluno();
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRAAlunoFocusLost

    private void cbCursoAlunoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoAlunoActionPerformed
        setTurmaCurso();
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoAlunoActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterarSenha();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void pfSenhaAtualFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pfSenhaAtualFocusLost
        if (!(new String(pfSenhaAtual.getPassword())).equals(usuarioLogado.getSenha())) {
            JOptionPane.showMessageDialog(null, "A senha do usuário atual não corresponde", "Erro", 0);
            pfSenhaAtual.setText("");
            pfSenhaAtual.addFocusListener(null);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_pfSenhaAtualFocusLost

    private void btnResetSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetSenhaActionPerformed
        resetSenha();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetSenhaActionPerformed

    private void lHorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lHorarioMouseClicked
        escondeTudo();
        jInternalFrameCadastros.setVisible(true);
        jInternalFrameCadHorario.setVisible(true);
        try {
            limparArrays();
            lerCursoCb();
            setTurmaHorario();
        } catch (Exception e) {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_lHorarioMouseClicked

    private void jcbTurmaHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbTurmaHorarioActionPerformed
        setComboBoxHorario();
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbTurmaHorarioActionPerformed

    private void jcbCursoHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbCursoHorarioActionPerformed
        setTurmaHorario();
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbCursoHorarioActionPerformed

    private void btnSalvarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarHorarioActionPerformed
        salvarHorario();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalvarHorarioActionPerformed

    private void btnEditUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserActionPerformed
        alterarDados();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditUserActionPerformed

    private void btnEditUser1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUser1ActionPerformed
        alterarProfessor();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditUser1ActionPerformed

    private void btnCancelarUserEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarUserEditActionPerformed
        cancelarEditProfessor();
        //TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarUserEditActionPerformed

    private void btnEditMateria1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditMateria1ActionPerformed
        alterarMateria();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditMateria1ActionPerformed

    private void btnLimparMateriaEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparMateriaEditActionPerformed
        cancelarEditMateria();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparMateriaEditActionPerformed

    private void cbNomeCursoMateriaEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNomeCursoMateriaEditActionPerformed
        // TODO add your handling code here:
        setTurmaMateriaEdit();
    }//GEN-LAST:event_cbNomeCursoMateriaEditActionPerformed

    private void cbCursoTurmaEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoTurmaEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoTurmaEditActionPerformed

    private void btnAlterarTurmaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarTurmaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarTurmaActionPerformed

    private void btnLimparTurmaEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparTurmaEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLimparTurmaEditActionPerformed

    private void btnAddDigitalAlunoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddDigitalAlunoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddDigitalAlunoEditActionPerformed

    private void btnAlterarAlunoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarAlunoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAlterarAlunoEditActionPerformed

    private void btnCancelarAlunoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarAlunoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarAlunoEditActionPerformed

    private void tfRAAlunoEditFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tfRAAlunoEditFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_tfRAAlunoEditFocusLost

    private void cbCursoAlunoEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCursoAlunoEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCursoAlunoEditActionPerformed

    private void btnVerDetalhesPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerDetalhesPesquisaActionPerformed
        editarPesquisa();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerDetalhesPesquisaActionPerformed

    private void btnVariosPesquisaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVariosPesquisaActionPerformed
        inativar();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVariosPesquisaActionPerformed

    private void jtResultadoAtivoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtResultadoAtivoKeyPressed

        jtResultadoAtivo.isCellEditable(jtResultadoAtivo.getSelectedRow(), jtResultadoAtivo.getSelectedColumn());
        // TODO add your handling code here:
    }//GEN-LAST:event_jtResultadoAtivoKeyPressed
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    private void jtResultadoInativosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jtResultadoInativosKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtResultadoInativosKeyPressed

    private void jtpResultadoPesquisaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jtpResultadoPesquisaStateChanged
        if (jtpResultadoPesquisa.getSelectedIndex() == 0) {
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            btnVerDetalhesPesquisa.setEnabled(true);
        } else if (jtpResultadoPesquisa.getSelectedIndex() == 1) {
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-open.png")));
            pesquisaBtn = 0;
            btnVerDetalhesPesquisa.setEnabled(false);
        } else {
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jtpResultadoPesquisaStateChanged

    private void btnCursoAlterarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCursoAlterarEditActionPerformed

        alterarCurso();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCursoAlterarEditActionPerformed

    private void btnCursoCancelarEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCursoCancelarEditActionPerformed
        cancelarEditCurso();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCursoCancelarEditActionPerformed

    private void cbNomeCursoMateriaEditMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbNomeCursoMateriaEditMouseClicked
        setTurmaMateria();
        // TODO add your handling code here:
    }//GEN-LAST:event_cbNomeCursoMateriaEditMouseClicked

    private void btnIniciarChamadaManualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarChamadaManualActionPerformed
        buscarAlunos();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnIniciarChamadaManualActionPerformed

    private void btnCancelarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarHorarioActionPerformed
        jcbCursoHorario.setSelectedIndex(0);
        jcbTurmaHorario.setSelectedIndex(0);
        for (int c = 0; c < 5; c++) {
            for (int c1 = 0; c1 < 5; c1++) {
                jtHorario.setValueAt("", c1, c);
            }
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelarHorarioActionPerformed

    private void btnVoltarHorarioEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarHorarioEditActionPerformed
        escondeTudo();
        jInternalFramePesquisa.setVisible(true);
        pesquisarHorario();
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVoltarHorarioEditActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new Principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgPesquisa;
    private javax.swing.ButtonGroup bgTipoCadastroUser;
    private javax.swing.JButton btnAddDigitalAluno;
    private javax.swing.JButton btnAddDigitalAlunoEdit;
    private javax.swing.JButton btnAddImgEditUser;
    private javax.swing.JButton btnAddImgUser;
    private javax.swing.JButton btnAddImgUserEdit;
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAlterarAlunoEdit;
    private javax.swing.JButton btnAlterarHorarios;
    private javax.swing.JButton btnAlterarTurma;
    private javax.swing.JButton btnCadCurso;
    private javax.swing.JButton btnCadTurma;
    private javax.swing.JButton btnCadastrarAluno;
    private javax.swing.JButton btnCadastrarMateria;
    private javax.swing.JButton btnCadastrarUser;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCancelarAlunoEdit;
    private javax.swing.JButton btnCancelarEditUser;
    private javax.swing.JButton btnCancelarHorario;
    private javax.swing.JButton btnCancelarUser;
    private javax.swing.JButton btnCancelarUserEdit;
    private javax.swing.JButton btnConf;
    private javax.swing.JButton btnCursoAlterarEdit;
    private javax.swing.JButton btnCursoCancelarEdit;
    private javax.swing.JButton btnEditMateria1;
    private javax.swing.JButton btnEditUser;
    private javax.swing.JButton btnEditUser1;
    private javax.swing.JButton btnFinalizarChamadaBio;
    private javax.swing.JButton btnFinalizarChamadaManual;
    private javax.swing.JButton btnImagemAluno;
    private javax.swing.JButton btnImagemAlunoEdit;
    private javax.swing.JButton btnIniciarChamadaBio;
    private javax.swing.JButton btnIniciarChamadaManual;
    private javax.swing.JButton btnLimparAluno;
    private javax.swing.JButton btnLimparCurso;
    private javax.swing.JButton btnLimparMateria;
    private javax.swing.JButton btnLimparMateriaEdit;
    private javax.swing.JButton btnLimparTurma;
    private javax.swing.JButton btnLimparTurmaEdit;
    private javax.swing.JButton btnPesquisa;
    private javax.swing.JButton btnResetSenha;
    private javax.swing.JButton btnSalvarHorario;
    private javax.swing.JButton btnVariosPesquisa;
    private javax.swing.JButton btnVerDetalhesPesquisa;
    private javax.swing.JButton btnVoltarHorarioEdit;
    private javax.swing.JButton btn_Logoff;
    private javax.swing.JButton btn_add;
    private javax.swing.JButton btn_chamadaBiometrica;
    private javax.swing.JButton btn_chamadaManual;
    private javax.swing.JButton btn_pesquisa;
    private javax.swing.JButton btn_sair;
    private javax.swing.JComboBox cbAulaChamadaBio;
    private javax.swing.JComboBox cbAulaChamadaManual;
    private javax.swing.JComboBox cbCursoAluno;
    private javax.swing.JComboBox cbCursoAlunoEdit;
    private javax.swing.JComboBox cbCursoTurma;
    private javax.swing.JComboBox cbCursoTurmaEdit;
    private javax.swing.JComboBox cbDivisaoMateria;
    private javax.swing.JComboBox cbDivisaoMateriaEdit;
    private javax.swing.JComboBox cbModuloTurma;
    private javax.swing.JComboBox cbModuloTurmaEdit;
    private javax.swing.JComboBox cbNomeCursoMateria;
    private javax.swing.JComboBox cbNomeCursoMateriaEdit;
    private javax.swing.JComboBox cbPeriodoTurma;
    private javax.swing.JComboBox cbPeriodoTurmaEdit;
    private javax.swing.JComboBox cbPesquisa;
    private javax.swing.JComboBox cbProfessorMateria;
    private javax.swing.JComboBox cbProfessorMateriaEdit;
    private javax.swing.JComboBox cbSexoAluno;
    private javax.swing.JComboBox cbSexoAlunoEdit;
    private javax.swing.JComboBox cbTurmaAluno;
    private javax.swing.JComboBox cbTurmaAlunoEdit;
    private javax.swing.JComboBox cbTurmaChamadaBio;
    private javax.swing.JComboBox cbTurmaChamadaManual;
    private javax.swing.JComboBox cbTurmaMateria;
    private javax.swing.JComboBox cbTurmaMateriaEdit;
    private javax.swing.JPanel jConf;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JInternalFrame jInternalFrameAlterarAluno;
    private javax.swing.JInternalFrame jInternalFrameAlterarCurso;
    private javax.swing.JInternalFrame jInternalFrameAlterarFuncionario;
    private javax.swing.JInternalFrame jInternalFrameAlterarHorario;
    private javax.swing.JInternalFrame jInternalFrameAlterarMaterias;
    private javax.swing.JInternalFrame jInternalFrameAlterarTurma;
    private javax.swing.JInternalFrame jInternalFrameCadAluno;
    private javax.swing.JInternalFrame jInternalFrameCadCurso;
    private javax.swing.JInternalFrame jInternalFrameCadHorario;
    private javax.swing.JInternalFrame jInternalFrameCadMateria;
    private javax.swing.JInternalFrame jInternalFrameCadTurmas;
    private javax.swing.JInternalFrame jInternalFrameCadUser;
    private javax.swing.JInternalFrame jInternalFrameCadastros;
    private javax.swing.JInternalFrame jInternalFrameChamadaBiometrica;
    private javax.swing.JInternalFrame jInternalFrameChamadaManual;
    private javax.swing.JInternalFrame jInternalFrameConfiguracoesGerais;
    private javax.swing.JInternalFrame jInternalFramePesquisa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButtonProfessor;
    private javax.swing.JRadioButton jRadioButtonSecretaria;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator23;
    private javax.swing.JSeparator jSeparator24;
    private javax.swing.JSeparator jSeparator25;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTabbedPane jTabbedPane;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTableChamadaBio;
    private javax.swing.JTable jTableChamadaManual;
    private javax.swing.JComboBox jcbCursoHorario;
    private javax.swing.JComboBox jcbTurmaHorario;
    private javax.swing.JDesktopPane jdeskTelas;
    private javax.swing.JDesktopPane jdeskTelasCadastro;
    private javax.swing.JPanel jpAtivos;
    private javax.swing.JPanel jpInativos;
    private javax.swing.JTable jtHorario;
    private javax.swing.JTable jtHorarioEdit;
    private javax.swing.JTable jtResultadoAtivo;
    private javax.swing.JTable jtResultadoInativos;
    private javax.swing.JTable jtTurmasAtivas;
    private javax.swing.JTabbedPane jtpResultadoPesquisa;
    private javax.swing.JLabel lAnoTurma;
    private javax.swing.JLabel lAnoTurmaEdit;
    private javax.swing.JLabel lAulaChamadaBio;
    private javax.swing.JLabel lAulaChamadaManual;
    private javax.swing.JLabel lAvisosEditUser;
    private javax.swing.JLabel lAvisosUser;
    private javax.swing.JLabel lAvisosUser1;
    private javax.swing.JLabel lCEPAluno;
    private javax.swing.JLabel lCEPAlunoEdit;
    private javax.swing.JLabel lCPFEditUser;
    private javax.swing.JLabel lCPFUser;
    private javax.swing.JLabel lCPFUserEdit;
    private javax.swing.JLabel lCadAlun;
    private javax.swing.JLabel lCadAluno;
    private javax.swing.JLabel lCadCurso;
    private javax.swing.JLabel lCadFuncionario;
    private javax.swing.JLabel lCadMat;
    private javax.swing.JLabel lCadMaterias;
    private javax.swing.JLabel lCadTurm;
    private javax.swing.JLabel lCadTurmas;
    private javax.swing.JLabel lCadUser;
    private javax.swing.JLabel lCelAluno;
    private javax.swing.JLabel lCelAlunoEdit;
    private javax.swing.JLabel lCidadeAluno;
    private javax.swing.JLabel lCidadeAlunoEdit;
    private javax.swing.JLabel lCursoAluno;
    private javax.swing.JLabel lCursoAlunoEdit;
    private javax.swing.JLabel lCursoMateria;
    private javax.swing.JLabel lCursoMateriaEdit;
    private javax.swing.JLabel lDigitalAluno;
    private javax.swing.JLabel lDigitalAlunoEdit;
    private javax.swing.JLabel lDigitalChamadaBio;
    private javax.swing.JLabel lDivisaoTurma;
    private javax.swing.JLabel lDivisaoTurmaEdit;
    private javax.swing.JLabel lDtNasAluno;
    private javax.swing.JLabel lDtNasAlunoEdit;
    private javax.swing.JLabel lEmailEditUser;
    private javax.swing.JLabel lEmailUser;
    private javax.swing.JLabel lEmailUserEdit;
    private javax.swing.JLabel lEndAluno;
    private javax.swing.JLabel lEndAlunoEdit;
    private javax.swing.JLabel lFotoAlunoChamadaBio;
    private javax.swing.JLabel lHorario;
    private javax.swing.JLabel lImageBanner;
    private javax.swing.JLabel lImagem;
    private javax.swing.JLabel lImagemAluno;
    private javax.swing.JLabel lImagemAlunoEdit;
    private javax.swing.JLabel lImagemEdit;
    private javax.swing.JLabel lImagemEditUser;
    private javax.swing.JLabel lModuloCursoEdit;
    private javax.swing.JLabel lModuloTurma;
    private javax.swing.JLabel lModuloTurmaEdit;
    private javax.swing.JLabel lNivelAcesso;
    private javax.swing.JLabel lNmResAluno1;
    private javax.swing.JLabel lNmResAluno2;
    private javax.swing.JLabel lNmResAluno2Edit;
    private javax.swing.JLabel lNmResAlunoEdit;
    private javax.swing.JLabel lNomeAluno;
    private javax.swing.JLabel lNomeAlunoEdit;
    private javax.swing.JLabel lNomeChamadaBio;
    private javax.swing.JLabel lNomeCompletoEditUser;
    private javax.swing.JLabel lNomeCompletoUser;
    private javax.swing.JLabel lNomeCompletoUserEdit;
    private javax.swing.JLabel lNomeCurso;
    private javax.swing.JLabel lNomeCursoEdit;
    private javax.swing.JLabel lNomeCursoTurma;
    private javax.swing.JLabel lNomeCursoTurmaEdit;
    private javax.swing.JLabel lNomeMateria;
    private javax.swing.JLabel lNomeMateriaEdit;
    private javax.swing.JLabel lNumAluno;
    private javax.swing.JLabel lNumAlunoEdit;
    private javax.swing.JLabel lNumeroChamadaBio;
    private javax.swing.JLabel lPeriodoTurma;
    private javax.swing.JLabel lPeriodoTurmaEdit;
    private javax.swing.JLabel lProfChamadaBio;
    private javax.swing.JLabel lProfChamadaManual;
    private javax.swing.JLabel lProfessorMateria;
    private javax.swing.JLabel lProfessorMateriaEdit;
    private javax.swing.JLabel lQtdModCurso;
    private javax.swing.JLabel lRAAluno;
    private javax.swing.JLabel lRAAlunoEdit;
    private javax.swing.JLabel lRGEditUser;
    private javax.swing.JLabel lRGUser;
    private javax.swing.JLabel lRGUser1Edit;
    private javax.swing.JLabel lSalaTurma;
    private javax.swing.JLabel lSalaTurmaEdit;
    private javax.swing.JLabel lSenhaAtual;
    private javax.swing.JLabel lSenhaNova;
    private javax.swing.JLabel lSenhaNovaRepetir;
    private javax.swing.JLabel lSexoAluno;
    private javax.swing.JLabel lSexoAlunoEdit;
    private javax.swing.JLabel lSiglaMateria;
    private javax.swing.JLabel lSiglaMateriaEdit;
    private javax.swing.JLabel lSiglaTurma;
    private javax.swing.JLabel lSiglaTurmaEdit;
    private javax.swing.JLabel lTelAluno;
    private javax.swing.JLabel lTelAlunoEdit;
    private javax.swing.JLabel lTelCelEditUser;
    private javax.swing.JLabel lTelCelUser;
    private javax.swing.JLabel lTelCelUserEdit;
    private javax.swing.JLabel lTelFixoEditUser;
    private javax.swing.JLabel lTelFixoUser;
    private javax.swing.JLabel lTelFixoUserEdit;
    private javax.swing.JLabel lTempo;
    private javax.swing.JLabel lTipoCad;
    private javax.swing.JLabel lTurmaAluno;
    private javax.swing.JLabel lTurmaAlunoEdit;
    private javax.swing.JLabel lTurmaChamadaBio;
    private javax.swing.JLabel lTurmaChamadaManual;
    private javax.swing.JLabel lTurmaMateria;
    private javax.swing.JLabel lTurmaMateriaEdit;
    private javax.swing.JLabel lUser;
    private javax.swing.JLabel lUserImagem;
    private javax.swing.JLabel ldtNasEditUser;
    private javax.swing.JLabel ldtNasUser;
    private javax.swing.JLabel ldtNasUserEdit;
    private javax.swing.JLabel lhorariocurso;
    private javax.swing.JLabel lhorarioturma;
    private javax.swing.JLabel lsemTurma;
    private javax.swing.JLabel lsemTurmaEdit;
    private javax.swing.JPanel pAlunoChamadaBio;
    private javax.swing.JPanel pAlunosChamadaBio;
    private javax.swing.JPanel pAlunosChamadaManual;
    private javax.swing.JPanel pBanner;
    private javax.swing.JPanel pCadCurso;
    private javax.swing.JPanel pCadMateria;
    private javax.swing.JPanel pCadTurma;
    private javax.swing.JPanel pCadastroUser;
    private javax.swing.JPanel pCampoPesquisa;
    private javax.swing.JPanel pChamadaBio;
    private javax.swing.JPanel pChamadaBioInicio;
    private javax.swing.JPanel pChamadaManual;
    private javax.swing.JPanel pChamadaManualInicio;
    private javax.swing.JPanel pCursosCadastradosAluno;
    private javax.swing.JPanel pCursosCadastradosAluno1;
    private javax.swing.JPanel pCursosCadastradosAlunoEdit;
    private javax.swing.JPanel pDadosAluno;
    private javax.swing.JPanel pDadosAlunoEdit;
    private javax.swing.JPanel pDadosAlunoPrincipal;
    private javax.swing.JPanel pDadosAlunoPrincipal1;
    private javax.swing.JPanel pDadosCursoEdit;
    private javax.swing.JPanel pDadosFuncionarioEdit;
    private javax.swing.JPanel pEditFuncionario;
    private javax.swing.JPanel pEditMateria1;
    private javax.swing.JPanel pEditTurma;
    private javax.swing.JPanel pEditUser;
    private javax.swing.JPanel pEditarSenha;
    private javax.swing.JPanel pHorario;
    private javax.swing.JPanel pHorarioEdit;
    private javax.swing.JPanel pHorarios;
    private javax.swing.JPanel pMateriasCadAlunEdit;
    private javax.swing.JPanel pMateriasCadastradasAlunoEdit;
    private javax.swing.JPanel pMenu;
    private javax.swing.JPanel pMenuInternal;
    private javax.swing.JPanel pPresencaAtualEdit;
    private javax.swing.JPanel pResultado;
    private javax.swing.JPanel pTurmaCursoEdit;
    private javax.swing.JPasswordField pfSenhaAtual;
    private javax.swing.JPasswordField pfSenhaNova;
    private javax.swing.JPasswordField pfSenhaNovaRepetir;
    private javax.swing.JRadioButton rbAluno;
    private javax.swing.JRadioButton rbCursos;
    private javax.swing.JRadioButton rbHorarios;
    private javax.swing.JRadioButton rbMaterias;
    private javax.swing.JRadioButton rbProfessor;
    private javax.swing.JRadioButton rbSecretaria;
    private javax.swing.JRadioButton rbTurmas;
    private javax.swing.JSpinner spQtdModCurso;
    private javax.swing.JSpinner spinnerNumChamadaAluno;
    private javax.swing.JSpinner spinnerNumChamadaAlunoEdit;
    private javax.swing.JTextField tCidadeAluno;
    private javax.swing.JTextField tCidadeAlunoEdit;
    private javax.swing.JTextField tEmailEditUser;
    private javax.swing.JTextField tEmailUser;
    private javax.swing.JTextField tEmailUserEdit;
    private javax.swing.JTextField tEndAluno;
    private javax.swing.JTextField tEndAlunoEdit;
    private javax.swing.JTable tHorarios;
    private javax.swing.JTextField tModCursoEdit;
    private javax.swing.JTextField tNomeAluno;
    private javax.swing.JTextField tNomeAlunoEdit;
    private javax.swing.JTextField tNomeChamadaBio;
    private javax.swing.JTextField tNomeCurso;
    private javax.swing.JTextField tNomeCursoEdit;
    private javax.swing.JTextField tNomeEditUser;
    private javax.swing.JTextField tNomeMateria;
    private javax.swing.JTextField tNomeMateriaEdit;
    private javax.swing.JTextField tNomeUser;
    private javax.swing.JTextField tNomeUserEdit;
    private javax.swing.JTextField tNumeroChamadaBio;
    private javax.swing.JTextField tPesquisa;
    private javax.swing.JTextField tProfChamadaBio;
    private javax.swing.JTextField tProfChamadaManual;
    private javax.swing.JTextField tRespAluno1;
    private javax.swing.JTextField tRespAluno2;
    private javax.swing.JTextField tRespAluno2Edit;
    private javax.swing.JTextField tRespAlunoEdit;
    private javax.swing.JTextField tSalaTurma;
    private javax.swing.JTextField tSalaTurmaEdit;
    private javax.swing.JTextField tSiglaMateria;
    private javax.swing.JTextField tSiglaMateriaEdit;
    private javax.swing.JTextField tSiglaTurma;
    private javax.swing.JTextField tSiglaTurmaEdit;
    private javax.swing.JTable tbAlunoMateriaEdit;
    private javax.swing.JTable tbCursCadAluno;
    private javax.swing.JTable tbCursCadAlunoEdit;
    private javax.swing.JTable tbPresencaEdit;
    private javax.swing.JFormattedTextField tfAnoTurma;
    private javax.swing.JFormattedTextField tfAnoTurmaEdit;
    private javax.swing.JFormattedTextField tfCEPAluno;
    private javax.swing.JFormattedTextField tfCEPAlunoEdit;
    private javax.swing.JFormattedTextField tfCPFEditUser;
    private javax.swing.JFormattedTextField tfCPFUser;
    private javax.swing.JFormattedTextField tfCPFUserEdit;
    private javax.swing.JFormattedTextField tfCelularAluno;
    private javax.swing.JFormattedTextField tfCelularAlunoEdit;
    private javax.swing.JFormattedTextField tfDataNasEditUser;
    private javax.swing.JFormattedTextField tfDataNasUser;
    private javax.swing.JFormattedTextField tfDataNasUserEdit;
    private javax.swing.JFormattedTextField tfDtNasAluno;
    private javax.swing.JFormattedTextField tfDtNasAlunoEdit;
    private javax.swing.JFormattedTextField tfRAAluno;
    private javax.swing.JFormattedTextField tfRAAlunoEdit;
    private javax.swing.JFormattedTextField tfRGEditUser;
    private javax.swing.JFormattedTextField tfRGUser;
    private javax.swing.JFormattedTextField tfRGUserEdit;
    private javax.swing.JFormattedTextField tfSemTurma;
    private javax.swing.JFormattedTextField tfSemTurmaEdit;
    private javax.swing.JFormattedTextField tfTelAluno;
    private javax.swing.JFormattedTextField tfTelAlunoEdit;
    private javax.swing.JFormattedTextField tfTelCelEditUser;
    private javax.swing.JFormattedTextField tfTelCelUser;
    private javax.swing.JFormattedTextField tfTelCelUserEdit;
    private javax.swing.JFormattedTextField tfTelFixoEditUser;
    private javax.swing.JFormattedTextField tfTelFixoUser;
    private javax.swing.JFormattedTextField tfTelFixoUserEdit;
    // End of variables declaration//GEN-END:variables

    //INICIALIZACAO DE COMPONENTES
    private void initComponents2() {
        //ativar isso pra tirar a barra e colocar uma borda com linha
        setRootPaneCheckingEnabled(false);

        javax.swing.plaf.InternalFrameUI ui = jInternalFramePesquisa.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui1 = jInternalFrameCadCurso.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui1).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui2 = jInternalFrameConfiguracoesGerais.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui2).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui3 = jInternalFrameCadUser.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui3).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui4 = jInternalFrameCadAluno.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui4).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui5 = jInternalFrameChamadaBiometrica.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui5).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui6 = jInternalFrameChamadaManual.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui6).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui7 = jInternalFrameCadastros.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui7).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui8 = jInternalFrameCadMateria.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui8).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui9 = jInternalFrameCadTurmas.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui9).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui10 = jInternalFrameCadHorario.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui10).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui11 = jInternalFrameAlterarCurso.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui11).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui12 = jInternalFrameAlterarFuncionario.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui12).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui13 = jInternalFrameAlterarMaterias.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui13).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui14 = jInternalFrameAlterarTurma.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui14).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui15 = jInternalFrameAlterarAluno.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui15).setNorthPane(null);

        javax.swing.plaf.InternalFrameUI ui16 = jInternalFrameAlterarHorario.getUI();
        ((javax.swing.plaf.basic.BasicInternalFrameUI) ui16).setNorthPane(null);

        //seta o valor minimo no sppiner pra n�o ter valor negativo
        spQtdModCurso.setModel(new SpinnerNumberModel(3, 0, 20, 1));
        spinnerNumChamadaAluno.setModel(new SpinnerNumberModel(0, 0, 50, 1));

        //iniciar maximizado
        try {
            jInternalFramePesquisa.setMaximum(true);
            jInternalFrameCadCurso.setMaximum(true);
            jInternalFrameConfiguracoesGerais.setMaximum(true);
            jInternalFrameCadUser.setMaximum(true);
            jInternalFrameCadAluno.setMaximum(true);
            jInternalFrameChamadaBiometrica.setMaximum(true);
            jInternalFrameChamadaManual.setMaximum(true);
            jInternalFrameCadastros.setMaximum(true);
            jInternalFrameCadMateria.setMaximum(true);
            jInternalFrameCadTurmas.setMaximum(true);
            jInternalFrameCadHorario.setMaximum(true);
            jInternalFrameAlterarCurso.setMaximum(true);
            jInternalFrameAlterarFuncionario.setMaximum(true);
            jInternalFrameAlterarMaterias.setMaximum(true);
            jInternalFrameAlterarTurma.setMaximum(true);
            jInternalFrameAlterarAluno.setMaximum(true);
            jInternalFrameAlterarHorario.setMaximum(true);

        } catch (PropertyVetoException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //ECONDER OS INTERNALS FRAME
    private void escondeTudo() {
        jInternalFramePesquisa.setVisible(false);
        jInternalFrameCadCurso.setVisible(false);
        jInternalFrameConfiguracoesGerais.setVisible(false);
        jInternalFrameCadUser.setVisible(false);
        jInternalFrameCadAluno.setVisible(false);
        jInternalFrameChamadaBiometrica.setVisible(false);
        jInternalFrameChamadaManual.setVisible(false);
        jInternalFrameCadastros.setVisible(false);
        jInternalFrameCadMateria.setVisible(false);
        jInternalFrameCadTurmas.setVisible(false);
        jInternalFrameCadHorario.setVisible(false);
        jInternalFrameAlterarCurso.setVisible(false);
        jInternalFrameAlterarFuncionario.setVisible(false);
        jInternalFrameAlterarMaterias.setVisible(false);
        jInternalFrameAlterarTurma.setVisible(false);
        jInternalFrameAlterarAluno.setVisible(false);
        jInternalFrameAlterarHorario.setVisible(false);

        initComponents2();
    }

    //DISTRIBUIÇAO DA INFORMACAO QUE VEM DO USUARIO/LOGIN
    private void distribuicaoInformacao() {

        //nome na principal
        lUser.setText(usuarioLogado.getNomeCompleto());

        //tela de edi��o de usuario
        btnResetSenha.setVisible(false);
        tfDataNasEditUser.setText(usuarioLogado.getDataNascimento());
        tfCPFEditUser.setText(usuarioLogado.getCpf());
        tfRGEditUser.setText(usuarioLogado.getRg().replace(".", "").replace("-", ""));
        tfTelCelEditUser.setText(usuarioLogado.getTelCelular().replace(".", "").replace("-", "").replace("9", ""));
        tfTelFixoEditUser.setText(usuarioLogado.getTelResidencial());
        tEmailEditUser.setText(usuarioLogado.getEmail());
        tNomeEditUser.setText(usuarioLogado.getNomeCompleto());

        //nivel de usuario
        if (usuarioLogado.getNivel() == 1) {
            lNivelAcesso.setText("Professor(a)");
            btn_add.setVisible(false);
        } else if (usuarioLogado.getNivel() == 2) {
            lNivelAcesso.setText("Secretaria");
        } else if (usuarioLogado.getNivel() == 3) {
            lNivelAcesso.setText("Administrador");
            btnResetSenha.setVisible(true);
        } else {
            lNivelAcesso.setText("Desconhecido");
            pMenu.setVisible(false);

        }

        if ((usuarioLogado.getSenha().equals("123")) && (usuarioLogado.getNivel() < 4)) {
            JOptionPane.showMessageDialog(null, "Troque a senha para garantir a segurança!", "Senha Padrão", 1);
            escondeTudo();
            jInternalFrameConfiguracoesGerais.setVisible(true);
        }

        //conex�o bd
        try {
            con = conexaoBanco.conexao();
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //BOTOES DE CRIAR
    private void criarUsuario() {
        if ((!tNomeUser.getText().equals("")) && (!tfCPFUser.getText().replace(".", "").equals("")) && ((jRadioButtonProfessor.isSelected() == true) || (jRadioButtonSecretaria.isSelected() == true))) {
            if ((vcpf.CPF(tfCPFUser.getText().replace(".", "").replace("-", "")) == true)) {
                user = new Usuario(tNomeUser.getText(), tfDataNasUser.getText(), tfCPFUser.getText(), tfRGUser.getText(), tfTelFixoUser.getText(), tfTelCelUser.getText(), tEmailUser.getText(), jRadioButtonProfessor.isSelected(), 2, "123", 1, 0);
                usuarioBanco.cadastrarUsuario(con, user);
                limparUser();
                user.limpar();
            } else {
                JOptionPane.showMessageDialog(null, "CPF Inválido", "Erro", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
        }
    }

    private void criarCurso() {
        if (!tNomeCurso.getText().equals("")) {
            cc = new Curso(tNomeCurso.getText(), Integer.parseInt(spQtdModCurso.getValue().toString()), 1);
            cursoBanco.cadastrarCurso(con, cc);
            limparCurso();
            cc.limpar();
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
        }
    }

    private void criarTurma() {
        if ((!tfAnoTurma.getText().equals("")) && (!tSiglaTurma.getText().equals("")) && (!tSalaTurma.getText().equals("")) && (!tfSemTurma.getText().equals(""))) {
            if ((Integer.parseInt(tfAnoTurma.getText()) > 2012) && ((Integer.parseInt(tfSemTurma.getText()) > 0) && Integer.parseInt(tfSemTurma.getText()) < 3)) {
                ct = new Turma(cursosLista.get(cbCursoTurma.getSelectedIndex()).getCodigo(), cbModuloTurma.getSelectedIndex(), cbPeriodoTurma.getSelectedItem().toString(), tSiglaTurma.getText(), tSalaTurma.getText(), tfAnoTurma.getText(), tfSemTurma.getText(), 0, 1, "");
                turmaBanco.cadastrarTurma(con, ct);
                limparTurma();
                ct.limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
        }
    }

    private void criarMateria() {
        if ((!tNomeMateria.getText().equals("")) && (!tSiglaMateria.getText().equals("")) && (professores.get(cbProfessorMateria.getSelectedIndex()) != professores.get(cbDivisaoMateria.getSelectedIndex()))) {
            cm = new Materia(cursosLista.get(cbNomeCursoMateria.getSelectedIndex()).getCodigo(), turma.get(cbTurmaMateria.getSelectedIndex()).getCodigo(), tNomeMateria.getText(), tSiglaMateria.getText(), professores.get(cbProfessorMateria.getSelectedIndex()).getCodigo(), professores.get(cbDivisaoMateria.getSelectedIndex()).getCodigo());
            materiaBanco.cadastrarMateria(con, cm);
            limparMateria();
            cm.limpar();
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
        }
    }

    private void criarAluno() {
        if (cadAl == 0) {
            if (!(tfRAAluno.getText().replace(".", "").replace("-", "").equals("")) && ((Integer.parseInt(spinnerNumChamadaAluno.getValue().toString())) != 0) && (!tNomeAluno.getText().equals("") && (!(tfDtNasAluno.getText().replace("/", "").replace(" ", ""
                    + "")).equals(""))) && (!tfTelAluno.getText().replace("(", "").replace(")", "").replace("-", "").equals("")) && (!tRespAluno1.getText().equals(""))) {
                al = new Aluno(tfRAAluno.getText(), Integer.parseInt(spinnerNumChamadaAluno.getValue().toString()), tfDtNasAluno.getText(), tfTelAluno.getText(), tCidadeAluno.getText(), tRespAluno1.getText(), tRespAluno2.getText(), cursosLista.get(cbCursoAluno.getSelectedIndex()).getCodigo(), turma.get(cbTurmaAluno.getSelectedIndex()).getCodigo(), tNomeAluno.getText(), cbSexoAluno.getSelectedIndex(), tfCelularAluno.getText(), tfCEPAluno.getText(), tEndAluno.getText());
                alunoBanco.cadastrarAluno(con, al);
                limparAluno();
                al.limpar();
            } else {
                JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
            }
        } else if (cadAl == 1) {
            try {
                if (!(tfRAAluno.getText().replace(".", "").replace("-", "").equals("")) && ((Integer.parseInt(spinnerNumChamadaAluno.getValue().toString())) != 0) && (!tNomeAluno.getText().equals("") && (!(tfDtNasAluno.getText().replace("/", "").replace(" ", ""
                        + "")).equals(""))) && (!tfTelAluno.getText().replace("(", "").replace(")", "").replace("-", "").equals("")) && (!tRespAluno1.getText().equals(""))) {
                    al = new Aluno(tfRAAluno.getText(), Integer.parseInt(spinnerNumChamadaAluno.getValue().toString()), tfDtNasAluno.getText(), tfTelAluno.getText(), tCidadeAluno.getText(), tRespAluno1.getText(), tRespAluno2.getText(), cursosLista.get(cbCursoAluno.getSelectedIndex()).getCodigo(), turma.get(cbTurmaAluno.getSelectedIndex()).getCodigo(), tNomeAluno.getText(), cbSexoAluno.getSelectedIndex(), tfCelularAluno.getText(), tfCEPAluno.getText(), tEndAluno.getText());
                    alunoBanco.cadastrarAlunoContinuacao(con, al, alunoTemporario.getCodigo());
                    limparAluno();
                    al.limpar();
                    JOptionPane.showMessageDialog(null, "O aluno foi cadastrado com sucesso!", "Status do Cadastro", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
                }
            } catch (Exception e) {
                System.err.println(e);
                JOptionPane.showMessageDialog(null, "Não foi possivel realizar o cadastro!", "Status do Cadastro", 0);
            }
        } else {
            System.err.println("Erro");
        }
    }

    //ROTINAS DE ALTERAR COMBOBOX
    private void addModuloTurma() {
        String[] modulos2 = new String[cursosLista.get(cbCursoTurma.getSelectedIndex()).getQuantidadeModulos()];
        int c = 0;
        while (cursosLista.get(cbCursoTurma.getSelectedIndex()).getQuantidadeModulos() > c) {
            modulos2[c] = (c + 1) + "";
            c++;
        }
        DefaultComboBoxModel mod2 = new DefaultComboBoxModel(modulos2);
        cbModuloTurma.setModel(mod2);
    }

    //ROTINAR DE LER
    private void lerCursoCb() {
        cursosLista = cursoBanco.ler(cursoAtivo, con);
        cursoArray = new String[cursosLista.size()];
        int c = 0;
        while (cursosLista.size() > c) {
            cursoArray[c] = cursosLista.get(c).getNomeCurso();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel model = new DefaultComboBoxModel(cursoArray);
        cbNomeCursoMateria.setModel(model);
        DefaultComboBoxModel model1 = new DefaultComboBoxModel(cursoArray);
        cbCursoAluno.setModel(model1);
        DefaultComboBoxModel model2 = new DefaultComboBoxModel(cursoArray);
        cbCursoTurma.setModel(model2);
        DefaultComboBoxModel model3 = new DefaultComboBoxModel(cursoArray);
        jcbCursoHorario.setModel(model3);
        DefaultComboBoxModel model4 = new DefaultComboBoxModel(cursoArray);
        cbNomeCursoMateriaEdit.setModel(model4);
    }

    private void cursosCursandoAluno() {
        tbCursCadAluno.setModel(new javax.swing.table.DefaultTableModel(new Object[cursosListaCadastrado.size()][cursosListaCadastrado.size()], new String[]{
            "Curso"
        }));
        int cont = 0;
        while (cursosListaCadastrado.size() > cont) {
            tbCursCadAluno.setValueAt(cursosListaCadastrado.get(cont).getNomeCurso(), cont, 0);
            cont++;
        }
    }

    private void lerCursoAlunoCb() {
        cursosListaCadastrado = cursoBanco.lerCursoAluno(cursoAluno.replace("ID", alunoTemporario.getCodigo() + ""), con);//pesquisa cursos já cadastrados
        cursosLista = cursoBanco.ler(cursoAtivo, con);

        for (int c1 = 0; c1 < cursosListaCadastrado.size(); c1++) {
            for (int c2 = 0; c2 < cursosLista.size(); c2++) {
                if (cursosListaCadastrado.get(c1).getCodigo() == cursosLista.get(c2).getCodigo()) {
                    cursosLista.remove(c2);
                }
            }
        }
        //transforma em array
        cursoArray = new String[cursosLista.size()];
        int c = 0;
        while (cursosLista.size() > c) {
            cursoArray[c] = cursosLista.get(c).getNomeCurso();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel model1 = new DefaultComboBoxModel(cursoArray);
        cbCursoAluno.setModel(model1);
    }

    private void lerProfCb() {
        professores = usuarioBanco.ler(profAtivoSql, con);
        professoresArray = new String[professores.size()];
        int c = 0;
        while (professores.size() > c) {
            professoresArray[c] = professores.get(c).getNomeCompleto();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel professor1 = new DefaultComboBoxModel(professoresArray);
        cbProfessorMateria.setModel(professor1);
        DefaultComboBoxModel professor2 = new DefaultComboBoxModel(professoresArray);
        cbDivisaoMateria.setModel(professor2);

        DefaultComboBoxModel professor3 = new DefaultComboBoxModel(professoresArray);
        cbProfessorMateriaEdit.setModel(professor3);
        DefaultComboBoxModel professor4 = new DefaultComboBoxModel(professoresArray);
        cbDivisaoMateriaEdit.setModel(professor4);
    }

    private void lerTurmaCb() {
        turma = turmaBanco.ler(turmaAtiva, con);

        //eliminação de cursos já cadastrados
        for (int c1 = 0; c1 < cursosListaCadastrado.size(); c1++) {
            for (int c2 = 0; c2 < turma.size(); c2++) {
                if (cursosListaCadastrado.get(c1).getCodigo() == turma.get(c2).getCurso()) {
                    turma.remove(c2);
                }
            }
        }

        turmaArray = new String[turma.size()];
        int c = 0;
        while (turma.size() > c) {
            turmaArray[c] = turma.get(c).getSigla();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel turma1 = new DefaultComboBoxModel(turmaArray);
        cbTurmaAluno.setModel(turma1);
    }

    //ROTINAS DE PESQUISAR
    private void pesquisa() {
        this.txtSql = tPesquisa.getText();

        if (rbProfessor.isSelected()) {
            switch (cbPesquisa.getSelectedIndex()) {
                case 0:
                    limparArrays();
                    professores = usuarioBanco.ler(profNomeAtivoSql.replace("txtSql", txtSql), con);
                    professores2 = usuarioBanco.ler(profNomeInativoSql.replace("txtSql", txtSql), con);
                    setPesquisaProfessor();
                    break;
                case 1:
                    limparArrays();
                    professores = usuarioBanco.ler(profCPFAtivoSql.replace("txtSql", txtSql), con);
                    professores2 = usuarioBanco.ler(profCPFInativoSql.replace("txtSql", txtSql), con);
                    setPesquisaProfessor();
                    break;
                default:
                    break;
            }
        } else if (rbSecretaria.isSelected()) {
            switch (cbPesquisa.getSelectedIndex()) {
                case 0:
                    limparArrays();
                    funcionario = usuarioBanco.ler(funcNomeAtivoSql.replace("txtSql", txtSql), con);
                    funcionario2 = usuarioBanco.ler(funcNomeInativoSql.replace("txtSql", txtSql), con);
                    setPesquisaSecretaria();
                    break;
                case 1:
                    limparArrays();
                    funcionario = usuarioBanco.ler(funcCPFAtivoSql.replace("txtSql", txtSql), con);
                    funcionario2 = usuarioBanco.ler(funcCPFInativoSql.replace("txtSql", txtSql), con);
                    setPesquisaSecretaria();
                    break;
                default:
                    break;
            }
        } else if (rbAluno.isSelected()) {
            switch (cbPesquisa.getSelectedIndex()) {
                case 0:
                    limparArrays();
                    aluno = alunoBanco.ler(alunoNomeAtivo.replace("txtSql", txtSql), con);
                    aluno2 = alunoBanco.ler(alunoNomeInativo.replace("txtSql", txtSql), con);
                    setPesquisaAluno();
                    break;
                case 1:
                    limparArrays();
                    aluno = alunoBanco.ler(alunoRAAtivo.replace("txtSql", txtSql), con);
                    aluno2 = alunoBanco.ler(alunoRAInativo.replace("txtSql", txtSql), con);
                    setPesquisaAluno();
                    break;
                default:
                    break;
            }
        } else if (rbHorarios.isSelected()) {
            JOptionPane.showMessageDialog(null, "HOR");
        } else if (rbTurmas.isSelected()) {
            switch (cbPesquisa.getSelectedIndex()) {
                case 0:
                    limparArrays();
                    turma = turmaBanco.ler(turmaSiglaAtivo.replace("txtSql", txtSql), con);
                    turma2 = turmaBanco.ler(turmaSiglaInativo.replace("txtSql", txtSql), con);
                    setPesquisaTurma();
                    break;
                case 1:
                    limparArrays();
                    turma = turmaBanco.ler(turmaSalaAtivo.replace("txtSql", txtSql), con);
                    turma2 = turmaBanco.ler(turmaSalaInativo.replace("txtSql", txtSql), con);
                    setPesquisaTurma();
                    break;
                case 2:
                    limparArrays();
                    turma = turmaBanco.ler(turmaCursoAtivo.replace("txtSql", txtSql), con);
                    turma2 = turmaBanco.ler(turmaCursoInativo.replace("txtSql", txtSql), con);
                    setPesquisaTurma();
                    break;
                case 3:
                    limparArrays();
                    turma = turmaBanco.ler(turmaModuloAtivo.replace("txtSql", txtSql), con);
                    turma2 = turmaBanco.ler(turmaModuloInativo.replace("txtSql", txtSql), con);
                    setPesquisaTurma();
                    break;
                case 4:
                    limparArrays();
                    turma = turmaBanco.ler(turmaPeriodoAtivo.replace("txtSql", txtSql), con);
                    turma2 = turmaBanco.ler(turmaPeriodoInativo.replace("txtSql", txtSql), con);
                    setPesquisaTurma();
                    break;
                case 5:
                    limparArrays();
                    turma = turmaBanco.ler(turmaAnoAtivo.replace("txtSql", txtSql), con);
                    turma2 = turmaBanco.ler(turmaAnoInativo.replace("txtSql", txtSql), con);
                    setPesquisaTurma();
                    break;
                default:
                    break;
            }
        } else if (rbMaterias.isSelected()) {
            switch (cbPesquisa.getSelectedIndex()) {
                case 0:
                    limparArrays();
                    materia = materiaBanco.ler(materiaNome.replace("txtSql", txtSql), con);
                    setPesquisaMateria();
                    break;
                case 1:
                    limparArrays();
                    materia = materiaBanco.ler(materiaSigla.replace("txtSql", txtSql), con);
                    setPesquisaMateria();
                    break;
                case 2:
                    limparArrays();
                    materia = materiaBanco.ler(materiaCurso.replace("txtSql", txtSql), con);
                    setPesquisaMateria();
                    break;
                case 3:
                    limparArrays();
                    materia = materiaBanco.ler(materiaModulo.replace("txtSql", txtSql), con);
                    setPesquisaMateria();
                    break;
                default:
                    break;
            }
        } else if (rbCursos.isSelected()) {
            switch (cbPesquisa.getSelectedIndex()) {
                case 0:
                    limparArrays();
                    cursosLista = cursoBanco.ler(cursoNomeAtivo.replace("txtSql", txtSql), con);
                    cursosLista2 = cursoBanco.ler(cursoNomeInativo.replace("txtSql", txtSql), con);
                    setPesquisaCurso();
                    break;
                case 1:
                    limparArrays();
                    cursosLista = cursoBanco.ler(cursoModuloAtivo.replace("txtSql", txtSql), con);
                    cursosLista2 = cursoBanco.ler(cursoModuloInativo.replace("txtSql", txtSql), con);
                    setPesquisaCurso();
                    break;
                default:
                    break;
            }
        } else {
        }
    }

    private void pesquisarSecretaria() {
        ativarPesquisa();
        try {
            limparArrays();
            funcionario = usuarioBanco.ler(funcAtivoSql, con);
            funcionario2 = usuarioBanco.ler(funcInativoSql, con);
            jtpResultadoPesquisa.setEnabledAt(1, true);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            jtpResultadoPesquisa.setSelectedIndex(0);
            btnResetSenha.setEnabled(true);
            setPesquisaSecretaria();
        } catch (Exception e) {
            System.out.println(e);
        }
        //combobox
        String[] sec = new String[2];
        sec[0] = "Nome";
        sec[1] = "CPF";
        DefaultComboBoxModel pesquisa = new DefaultComboBoxModel(sec);
        cbPesquisa.setModel(pesquisa);
    }

    private void pesquisarCurso() {
        ativarPesquisa();
        try {
            limparArrays();
            cursosLista = cursoBanco.ler(cursoAtivo, con);//pegando resultados do banco
            cursosLista2 = cursoBanco.ler(cursoInativo, con);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            jtpResultadoPesquisa.setSelectedIndex(0);
            jtpResultadoPesquisa.setEnabledAt(1, true);
            btnResetSenha.setEnabled(false);
            setPesquisaCurso();
        } catch (Exception e) {
            System.out.println(e);
        }
        //combobox
        String[] sec = new String[2];
        sec[0] = "Nome";
        sec[1] = "Quant. Modulo";
        DefaultComboBoxModel pesquisa = new DefaultComboBoxModel(sec);
        cbPesquisa.setModel(pesquisa);
    }

    private void pesquisarProfessor() {
        ativarPesquisa();
        try {
            limparArrays();
            professores = usuarioBanco.ler(profAtivoSql, con);
            professores2 = usuarioBanco.ler(profInativoSql, con);
            jtpResultadoPesquisa.setEnabledAt(1, true);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            jtpResultadoPesquisa.setSelectedIndex(0);
            btnResetSenha.setEnabled(true);
            setPesquisaProfessor();
        } catch (Exception e) {
            System.out.println(e);
        }
        //combobox
        String[] sec = new String[2];
        sec[0] = "Nome";
        sec[1] = "CPF";
        DefaultComboBoxModel pesquisa = new DefaultComboBoxModel(sec);
        cbPesquisa.setModel(pesquisa);
    }

    private void pesquisarMaterias() {
        ativarPesquisa();
        try {
            limparArrays();
            materia = materiaBanco.ler(materiaTodas, con);
            jtpResultadoPesquisa.setEnabledAt(1, false);
            btnResetSenha.setEnabled(false);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/delete.png")));
            pesquisaBtn = 2;
            jtpResultadoPesquisa.setSelectedIndex(0);
            btnVariosPesquisa.setEnabled(true);
            setPesquisaMateria();
        } catch (Exception e) {
            System.out.println(e);
        }
        //combobox
        String[] sec = new String[4];
        sec[0] = "Nome";
        sec[1] = "Sigla";
        sec[2] = "Curso";
        sec[3] = "Modulo";
        DefaultComboBoxModel pesquisa = new DefaultComboBoxModel(sec);
        cbPesquisa.setModel(pesquisa);
    }

    private void pesquisarTurmas() {
        ativarPesquisa();
        try {
            limparArrays();
            turma = turmaBanco.ler(turmaAtiva, con);
            turma2 = turmaBanco.ler(turmaInativa, con);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            jtpResultadoPesquisa.setSelectedIndex(0);
            jtpResultadoPesquisa.setEnabledAt(1, true);
            btnResetSenha.setEnabled(false);
            setPesquisaTurma();
        } catch (Exception e) {
            System.out.println(e);
        }
        //combobox
        String[] sec = new String[6];
        sec[0] = "Sigla";
        sec[1] = "Sala";
        sec[2] = "Curso";
        sec[3] = "Modulo";
        sec[4] = "Período";
        sec[5] = "Ano";
        DefaultComboBoxModel pesquisa = new DefaultComboBoxModel(sec);
        cbPesquisa.setModel(pesquisa);
    }

    private void pesquisarAluno() {
        ativarPesquisa();
        try {
            limparArrays();
            aluno = alunoBanco.ler(alunoAtivo, con);
            aluno2 = alunoBanco.ler(alunoInativo, con);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            jtpResultadoPesquisa.setSelectedIndex(0);
            jtpResultadoPesquisa.setEnabledAt(1, true);
            btnResetSenha.setEnabled(false);
            setPesquisaAluno();
        } catch (Exception e) {
        }
        //combobox
        String[] sec = new String[2];
        sec[0] = "Nome";
        sec[1] = "RA";
        DefaultComboBoxModel pesquisa = new DefaultComboBoxModel(sec);
        cbPesquisa.setModel(pesquisa);
    }

    private void pesquisarHorario() {
        ativarPesquisa();
        try {
            limparArrays();
            horario = horarioBanco.ler(horarioTodos, con);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/delete.png")));
            pesquisaBtn = 2;
            jtpResultadoPesquisa.setSelectedIndex(0);
            jtpResultadoPesquisa.setEnabledAt(1, false);
            btnResetSenha.setEnabled(false);
            btnVariosPesquisa.setEnabled(true);
            setPesquisaHorario();
        } catch (Exception e) {
        }
    }

    //BOTOES DE LIMPAR
    private void limparCurso() {
        tNomeCurso.setText("");
        spQtdModCurso.setValue(3);
    }

    private void limparUser() {
        tNomeUser.setText("");
        tfDataNasUser.setText("");
        tfCPFUser.setText("");
        tfRGUser.setText("");
        tfTelFixoUser.setText("");
        tfTelCelUser.setText("");
        tEmailUser.setText("");
        bgTipoCadastroUser.clearSelection();
    }

    private void limparTurma() {
        cbCursoTurma.setSelectedIndex(0);
        cbModuloTurma.setSelectedIndex(0);
        cbPeriodoTurma.setSelectedIndex(0);
        tSiglaTurma.setText("");
        tSalaTurma.setText("");
        tfAnoTurma.setText("");
        tfSemTurma.setText("");
    }

    private void limparMateria() {
        cbNomeCursoMateria.setSelectedIndex(0);
        cbTurmaMateria.setSelectedIndex(0);
        tNomeMateria.setText("");
        tSiglaMateria.setText("");
        cbProfessorMateria.setSelectedIndex(0);
        cbDivisaoMateria.setSelectedIndex(0);
    }

    private void limparAluno() {
        tfCEPAluno.setText("");
        tfCelularAluno.setText("");
        tfDtNasAluno.setText("");
        tfRAAluno.setText("");
        tfTelAluno.setText("");
        tCidadeAluno.setText("");
        tEndAluno.setText("");
        tNomeAluno.setText("");
        cbCursoAluno.setSelectedIndex(0);
        cbTurmaAluno.setSelectedIndex(0);
        cbSexoAluno.setSelectedIndex(0);
        tRespAluno1.setText("");
        tRespAluno2.setText("");
    }

    //EVENTOS DE WINDOWS LISTENER
    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        encerrarConexao();
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }

    private void encerrarConexao() {
        try {
            con.close();
            System.out.println("Encerrando Conexão Com o Banco");
        } catch (SQLException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setPesquisaHorario() {
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[horario.size()][horario.size()], new String[]{
            "Turma",
            "Curso",
            "Periodo"
        }));
        int cont = 0;
        while (horario.size() > cont) {
            jtResultadoAtivo.setValueAt(horario.get(cont).getNomeTurma(), cont, 0);
            jtResultadoAtivo.setValueAt(horario.get(cont).getNomeCurso(), cont, 1);
            jtResultadoAtivo.setValueAt(horario.get(cont).getNomePeriodo(), cont, 2);
            cont++;
        }
    }

    private void setPesquisaProfessor() {
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[professores.size()][professores.size()], new String[]{
            "Nome", "CPF", "Telefone", "E-mail"
        }));
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[professores2.size()][professores2.size()], new String[]{
            "Nome", "CPF", "Telefone", "E-mail"
        }));
        int cont = 0;
        while (professores.size() > cont) {
            jtResultadoAtivo.setValueAt(professores.get(cont).getNomeCompleto(), cont, 0);
            jtResultadoAtivo.setValueAt(professores.get(cont).getCpf(), cont, 1);
            jtResultadoAtivo.setValueAt(professores.get(cont).getTelCelular(), cont, 2);
            jtResultadoAtivo.setValueAt(professores.get(cont).getEmail(), cont, 3);
            cont++;
        }
        cont = 0;
        while (professores2.size() > cont) {
            jtResultadoInativos.setValueAt(professores2.get(cont).getNomeCompleto(), cont, 0);
            jtResultadoInativos.setValueAt(professores2.get(cont).getCpf(), cont, 1);
            jtResultadoInativos.setValueAt(professores2.get(cont).getTelCelular(), cont, 2);
            jtResultadoInativos.setValueAt(professores2.get(cont).getEmail(), cont, 3);
            cont++;
        }
    }

    private void setPesquisaSecretaria() {
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[funcionario.size()][funcionario.size()], new String[]{
            "Nome", "CPF", "Telefone", "E-mail"
        }));
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[funcionario2.size()][funcionario2.size()], new String[]{
            "Nome", "CPF", "Telefone", "E-mail"
        }));
        int cont = 0;
        while (funcionario.size() > cont) {
            jtResultadoAtivo.setValueAt(funcionario.get(cont).getNomeCompleto(), cont, 0);
            jtResultadoAtivo.setValueAt(funcionario.get(cont).getCpf(), cont, 1);
            jtResultadoAtivo.setValueAt(funcionario.get(cont).getTelCelular(), cont, 2);
            jtResultadoAtivo.setValueAt(funcionario.get(cont).getEmail(), cont, 3);
            cont++;
        }
        cont = 0;
        while (funcionario2.size() > cont) {
            jtResultadoInativos.setValueAt(funcionario2.get(cont).getNomeCompleto(), cont, 0);
            jtResultadoInativos.setValueAt(funcionario2.get(cont).getCpf(), cont, 1);
            jtResultadoInativos.setValueAt(funcionario2.get(cont).getTelCelular(), cont, 2);
            jtResultadoInativos.setValueAt(funcionario2.get(cont).getEmail(), cont, 3);
            cont++;
        }
    }

    private void setPesquisaCurso() {
        int cont = 0;
        //arrumando a jtable de ativos
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[cursosLista.size()][cursosLista.size()], new String[]{
            "Nome",
            "Quant. Modulo"
        }));
        jtResultadoAtivo.getColumnModel().getColumn(0).setPreferredWidth(700);
        jtResultadoAtivo.getColumnModel().getColumn(0).setResizable(false);
        //arrumando a jtable de inativos
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[cursosLista2.size()][cursosLista2.size()], new String[]{
            "Nome",
            "Quant. Modulo"
        }));
        jtResultadoInativos.getColumnModel().getColumn(0).setPreferredWidth(700);
        jtResultadoInativos.getColumnModel().getColumn(0).setResizable(false);

        //adicionando itens nos ativos
        while (cursosLista.size() > cont) {
            jtResultadoAtivo.setValueAt(cursosLista.get(cont).getNomeCurso(), cont, 0);
            jtResultadoAtivo.setValueAt(cursosLista.get(cont).getQuantidadeModulos(), cont, 1);
            cont++;
        }
        cont = 0;
        while (cursosLista2.size() > cont) {
            jtResultadoInativos.setValueAt(cursosLista2.get(cont).getNomeCurso(), cont, 0);
            jtResultadoInativos.setValueAt(cursosLista2.get(cont).getQuantidadeModulos(), cont, 1);
            cont++;
        }
    }

    private void setPesquisaMateria() {
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[materia.size()][materia.size()], new String[]{
            "Nome",
            "Sigla",
            "Profº",
            "Profº",
            "Curso",
            "Módulo"
        }));

        int cont = 0;
        while (materia.size() > cont) {
            jtResultadoAtivo.setValueAt(materia.get(cont).getNomeMateria(), cont, 0);
            jtResultadoAtivo.setValueAt(materia.get(cont).getSigla(), cont, 1);
            jtResultadoAtivo.setValueAt(materia.get(cont).getProfessorPrincipalS(), cont, 2);
            jtResultadoAtivo.setValueAt(materia.get(cont).getDivisaoS(), cont, 3);
            jtResultadoAtivo.setValueAt(materia.get(cont).getNomeCurso(), cont, 4);
            jtResultadoAtivo.setValueAt(materia.get(cont).getModulo(), cont, 5);
            cont++;
        }
    }

    private void setPesquisaAluno() {
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[aluno.size()][aluno.size()], new String[]{
            "Nome",
            "RA",
            "Data Nascimento"
        }));
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[aluno2.size()][aluno2.size()], new String[]{
            "Nome",
            "RA",
            "Data Nascimento"
        }));
        int cont = 0;
        while (aluno.size() > cont) {
            jtResultadoAtivo.setValueAt(aluno.get(cont).getNomeCompleto(), cont, 0);
            jtResultadoAtivo.setValueAt(aluno.get(cont).getRa(), cont, 1);
            jtResultadoAtivo.setValueAt(aluno.get(cont).getDataNascimento(), cont, 2);
            cont++;
        }
        cont = 0;
        while (aluno2.size() > cont) {
            jtResultadoInativos.setValueAt(aluno2.get(cont).getNomeCompleto(), cont, 0);
            jtResultadoInativos.setValueAt(aluno2.get(cont).getRa(), cont, 1);
            jtResultadoInativos.setValueAt(aluno2.get(cont).getDataNascimento(), cont, 2);
            cont++;
        }
    }

    private void setPesquisaTurma() {
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[turma.size()][turma.size()], new String[]{
            "Sigla",
            "Módulo",
            "Sala",
            "Ano/Semestre",
            "Período",
            "Curso"
        }));
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[turma2.size()][turma2.size()], new String[]{
            "Sigla",
            "Módulo",
            "Sala",
            "Ano/Semestre",
            "Período",
            "Curso"
        }));
        int cont = 0;
        while (turma.size() > cont) {
            jtResultadoAtivo.setValueAt(turma.get(cont).getSigla(), cont, 0);
            jtResultadoAtivo.setValueAt(turma.get(cont).getModulo(), cont, 1);
            jtResultadoAtivo.setValueAt(turma.get(cont).getSala(), cont, 2);
            jtResultadoAtivo.setValueAt(turma.get(cont).getAno() + "/" + turma.get(cont).getSemestre(), cont, 3);
            jtResultadoAtivo.setValueAt(turma.get(cont).getPeriodo(), cont, 4);
            jtResultadoAtivo.setValueAt(turma.get(cont).getNomeCurso(), cont, 5);
            cont++;
        }
        cont = 0;
        while (turma2.size() > cont) {
            jtResultadoInativos.setValueAt(turma2.get(cont).getSigla(), cont, 0);
            jtResultadoInativos.setValueAt(turma2.get(cont).getModulo(), cont, 1);
            jtResultadoInativos.setValueAt(turma2.get(cont).getSala(), cont, 2);
            jtResultadoInativos.setValueAt(turma2.get(cont).getAno() + "/" + turma2.get(cont).getSemestre(), cont, 3);
            jtResultadoInativos.setValueAt(turma2.get(cont).getPeriodo(), cont, 4);
            jtResultadoInativos.setValueAt(turma2.get(cont).getNomeCurso(), cont, 5);
            cont++;
        }
    }

    private void buscarAlunos() {
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[turma2.size()][turma2.size()], new String[]{
            "Numero", "Nome", "Presente?"
        }));
        int cont = 0;
        while (turma.size() > cont) {
            jtResultadoAtivo.setValueAt(turma.get(cont).getSigla(), cont, 0);
            jtResultadoAtivo.setValueAt(turma.get(cont).getModulo(), cont, 1);
            jtResultadoAtivo.setValueAt(turma.get(cont).getSala(), cont, 2);
            cont++;
        }
        cont = 0;
    }

    private void limparArrays() {
        professores.clear();
        professores2.clear();
        cursosLista.clear();
        cursosLista2.clear();
        turma.clear();
        turma2.clear();
        funcionario.clear();
        funcionario2.clear();
        materia.clear();
        aluno.clear();
        aluno2.clear();
        cursosListaCadastrado.clear();
        horario.clear();

    }

    private void ativarPesquisa() {
        btnVerDetalhesPesquisa.setEnabled(true);
        jtpResultadoPesquisa.setEnabled(true);
        btnPesquisa.setEnabled(true);
        tPesquisa.setEnabled(true);
        cbPesquisa.setEnabled(true);
        tPesquisa.setText("");
        btnVariosPesquisa.setEnabled(true);
    }

    private void verfAluno() {
        limparArrays();
        aluno = alunoBanco.ler(alunoTodos.replace("sql", tfRAAluno.getText()), con);

        try {
            if (aluno.size() == 1) {
                tNomeAluno.setText(aluno.get(0).getNomeCompleto());
                tfDtNasAluno.setText(aluno.get(0).getDataNascimento());
                cbSexoAluno.setSelectedIndex(aluno.get(0).getSexo());
                tfTelAluno.setText(aluno.get(0).getTelResidencial());
                tfCelularAluno.setText(aluno.get(0).getTelCelular());
                tfCEPAluno.setText(aluno.get(0).getCep());
                tCidadeAluno.setText(aluno.get(0).getCidade());
                tRespAluno1.setText(aluno.get(0).getNomeResponsavel1());
                tRespAluno2.setText(aluno.get(0).getNomeResponsavel2());
                tEndAluno.setText(aluno.get(0).getEndereco());
                cadAl = 1;
                alunoTemporario.setCodigo(aluno.get(0).getCodigo());
                lerCursoAlunoCb();
                lerTurmaCb();
                setTurmaCurso();
            } else {
                cadAl = 0;
                lerCursoCb();
                lerTurmaCb();
                setTurmaCurso();
            }
        } catch (Exception e) {
            System.err.println("ERRO NO VERIFALUNO" + e);
        } finally {
            cursosCursandoAluno();
        }
    }

    private void setTurmaCurso() {
        turma = turmaBanco.ler(turmaCursoAtivoCodigo.replace("%txtSql%", "" + cursosLista.get(cbCursoAluno.getSelectedIndex()).getCodigo()), con);
        //eliminação de cursos já cadastrados
        for (int c1 = 0; c1 < cursosListaCadastrado.size(); c1++) {
            for (int c2 = 0; c2 < turma.size(); c2++) {
                if (cursosListaCadastrado.get(c1).getCodigo() == turma.get(c2).getCurso()) {
                    turma.remove(c2);
                }
            }
        }

        turmaArray = new String[turma.size()];
        int c = 0;
        while (turma.size() > c) {
            turmaArray[c] = turma.get(c).getSigla();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel turma2 = new DefaultComboBoxModel(turmaArray);
        cbTurmaAluno.setModel(turma2);
    }

    private void setTurmaMateria() {
        turma = turmaBanco.ler(turmaCursoAtivoCodigo.replace("%txtSql%", "" + cursosLista.get(cbNomeCursoMateria.getSelectedIndex()).getCodigo()), con);
        turmaArray = new String[turma.size()];
        int c = 0;
        while (turma.size() > c) {
            turmaArray[c] = turma.get(c).getSigla();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel turma0 = new DefaultComboBoxModel(turmaArray);
        cbTurmaMateria.setModel(turma0);
    }

    private void setTurmaMateriaEdit() {
        turma.clear();
        turma = turmaBanco.ler(turmaCursoAtivoCodigo.replace("%txtSql%", "" + cursosLista.get(cbNomeCursoMateriaEdit.getSelectedIndex()).getCodigo()), con);
        turmaArray = new String[turma.size()];
        int c = 0;
        while (turma.size() > c) {
            turmaArray[c] = turma.get(c).getSigla();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel turma1 = new DefaultComboBoxModel(turmaArray);
        cbTurmaMateriaEdit.setModel(turma1);
    }

    private void limparPesquisa() {
        bgPesquisa.clearSelection();
        btnResetSenha.setEnabled(false);
        btnVerDetalhesPesquisa.setEnabled(false);
        jtpResultadoPesquisa.setEnabled(false);
        btnPesquisa.setEnabled(false);
        tPesquisa.setEnabled(false);
        cbPesquisa.setEnabled(false);
        tPesquisa.setText("");
        jtResultadoAtivo.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], new String[]{}));
        jtResultadoInativos.setModel(new javax.swing.table.DefaultTableModel(new Object[0][0], new String[]{}));
        DefaultComboBoxModel pesq = new DefaultComboBoxModel(new String[]{});
        cbPesquisa.setModel(pesq);
        btnVariosPesquisa.setEnabled(false);
        btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
    }

    private void alterarSenha() {
        if ((new String(pfSenhaNova.getPassword())).equals(new String(pfSenhaNovaRepetir.getPassword()))) {
            usuarioLogado.setSenhaNova(new String(pfSenhaNova.getPassword()));
            if (usuarioBanco.alterarSenha(con, usuarioLogado) == 1) {
                encerrarConexao();
                Login lf = new Login();
                this.dispose();
                lf.setVisible(true);
            }
            pfSenhaAtual.setText("");
            pfSenhaNova.setText("");
            pfSenhaNovaRepetir.setText("");
        } else {
            JOptionPane.showMessageDialog(null, "As senhas não correspondem!", "Status da Alteração", 0);
        }
    }

    private void resetSenha() {
        try {
            if (rbProfessor.isSelected()) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja Padronizar Senha?");
                if (e == 0) {
                    usuarioBanco.resetarSenha(con, professores.get(jtResultadoAtivo.getSelectedRow()));
                    jtResultadoAtivo.getSelectionModel().clearSelection();
                } else {
                    JOptionPane.showMessageDialog(null, "Padronização Cancelada");
                }
            } else if (rbSecretaria.isSelected()) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja Padronizar Senha?");
                if (e == 0) {
                    usuarioBanco.resetarSenha(con, funcionario.get(jtResultadoAtivo.getSelectedRow()));
                    jtResultadoAtivo.getSelectionModel().clearSelection();
                } else {
                    JOptionPane.showMessageDialog(null, "Padronização Cancelada");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Selecione um funcionário!", "Status da Padronização", 1);
        }
    }

    private void setComboBoxHorario() {
        JComboBox jcb = new JComboBox();


        TableCellEditor tce = new DefaultCellEditor(jcb);


        TableColumn tc = jtHorario.getColumnModel().getColumn(0);
        tc.setCellEditor(tce);

        TableColumn tc1 = jtHorario.getColumnModel().getColumn(1);
        tc1.setCellEditor(tce);

        TableColumn tc2 = jtHorario.getColumnModel().getColumn(2);
        tc2.setCellEditor(tce);

        TableColumn tc3 = jtHorario.getColumnModel().getColumn(3);
        tc3.setCellEditor(tce);

        TableColumn tc4 = jtHorario.getColumnModel().getColumn(4);
        tc4.setCellEditor(tce);

        //setar materias nas combobox's
        materia.clear();
        jcb.removeAllItems();

        materia = materiaBanco.ler(materiaTurma.replace("%txtSql%", "" + turma.get(jcbTurmaHorario.getSelectedIndex()).getSigla()), con);

        int c = 0;
        while (materia.size() > c) {
            jcb.addItem(materia.get(c).getNomeMateria());
            c++;
        }
    }

    private void turmaChamada() {
        turma = turmaBanco.ler(turmaAtiva, con);
        turmaArray = new String[turma.size()];
        int c = 0;
        while (turma.size() > c) {
            turmaArray[c] = turma.get(c).getSigla();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel chamada1 = new DefaultComboBoxModel(turmaArray);
        cbTurmaChamadaManual.setModel(chamada1);
    }

    private void setAulaChamada() {
        materia = materiaBanco.ler(materiaTurma.replace("%txtSql%", "" + cbTurmaChamadaManual.getSelectedItem()), con);
        aulasChamada = new String[materia.size()];
        int c = 0;
        while (materia.size() > c) {
            aulasChamada[c] = materia.get(c).getNomeMateria();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel chamadamat = new DefaultComboBoxModel(aulasChamada);
        cbAulaChamadaManual.setModel(chamadamat);
        setNomeProfs();
    }

    private void setNomeProfs() {
        if (materia.get(cbAulaChamadaManual.getSelectedIndex()).getDivisaoS().equals("Sem Professor")) {
            tProfChamadaManual.setText(materia.get(cbAulaChamadaManual.getSelectedIndex()).getProfessorPrincipalS());
            lProfChamadaManual.setText("Professor:");
        } else {
            tProfChamadaManual.setText(materia.get(cbAulaChamadaManual.getSelectedIndex()).getProfessorPrincipalS() + " e " + materia.get(cbAulaChamadaManual.getSelectedIndex()).getDivisaoS());
        }
    }

    private void setTurmaHorario() {
        turma.clear();
        turma = turmaBanco.ler(turmaCursoAtivoCodigo.replace("%txtSql%", "" + cursosLista.get(jcbCursoHorario.getSelectedIndex()).getCodigo()), con);
        turmaArray = new String[turma.size()];
        int c = 0;
        while (turma.size() > c) {
            turmaArray[c] = turma.get(c).getSigla();
            c++;
        }
        //CRIACAO DE MODELS
        DefaultComboBoxModel turma1 = new DefaultComboBoxModel(turmaArray);
        jcbTurmaHorario.setModel(turma1);
    }

    private void salvarHorario() {

        //GRAVAR
        int cod = 0;
        for (int c = 0; c < 5; c++) {
            for (int c1 = 0; c1 < 5; c1++) {
                try {
                    int cont = 0;
                    while (materia.size() > cont) {
                        if ((materia.get(cont).getNomeMateria()).equals((jtHorario.getValueAt(c1, c).toString()))) {
                            cod = materia.get(cont).getCodigo();
                        }
                        cont++;
                    }
                    horario.add(new Horario(c1, c, cod));
                } catch (Exception e) {
                }
            }
        }
        horarioBanco.cadastrarHorario(con, horario);


        /*
         //LER
         int c = 0;
         while (horario.size() > c) {
         JOptionPane.showMessageDialog(null, "dia semana: " + horario.get(c).getDiaSemana() + "\n"
         + "ordem: " + horario.get(c).getOrdem() + "\n"
         + "cod: " + horario.get(c).getCdMat());
         c++;
         }
         */
    }

    private void alterarDados() {
        usuarioBanco.alterar(new Usuario(tNomeEditUser.getText(), tfDataNasEditUser.getText(), tfCPFEditUser.getText(), tfRGEditUser.getText(), tfTelFixoEditUser.getText(), tfTelCelEditUser.getText(), tEmailEditUser.getText()), usuarioLogado, con);
    }

    private void setPesquisaEdit(int row) {
        if (cadFunc == 0) {
            tfDataNasUserEdit.setText(professores.get(row).getDataNascimento());
            tfCPFUserEdit.setText(professores.get(row).getCpf());
            tfRGUserEdit.setText(professores.get(row).getRg().replace(".", "").replace("-", ""));
            tfTelCelUserEdit.setText(professores.get(row).getTelCelular().replace(".", "").replace("-", "").replace("9", "").replace("(", "").replace(")", "").replace("1", "").replace("3", ""));
            tfTelFixoUserEdit.setText(professores.get(row).getTelResidencial());
            tEmailUserEdit.setText(professores.get(row).getEmail());
            tNomeUserEdit.setText(professores.get(row).getNomeCompleto());
        } else if (cadFunc == 1) {
            tfDataNasUserEdit.setText(funcionario.get(row).getDataNascimento());
            tfCPFUserEdit.setText(funcionario.get(row).getCpf());
            tfRGUserEdit.setText(funcionario.get(row).getRg().replace(".", "").replace("-", ""));
            tfTelCelUserEdit.setText(funcionario.get(row).getTelCelular().replace(".", "").replace("-", "").replace("9", "").replace("(", "").replace(")", "").replace("1", "").replace("3", ""));
            tfTelFixoUserEdit.setText(funcionario.get(row).getTelResidencial());
            tEmailUserEdit.setText(funcionario.get(row).getEmail());
            tNomeUserEdit.setText(funcionario.get(row).getNomeCompleto());
        } else {
        }
    }

    public void alterarProfessor() {
        try {
            if (cadFunc == 0) {
                usuarioBanco.alterar(new Usuario(tNomeUserEdit.getText(), tfDataNasUserEdit.getText(), tfCPFUserEdit.getText(), tfRGUserEdit.getText(), tfTelFixoUserEdit.getText(), tfTelCelUserEdit.getText(), tEmailUserEdit.getText(), false, 2, "123", 1, 0), professores.get(row), con);
            } else if (cadFunc == 1) {
                usuarioBanco.alterar(new Usuario(tNomeUserEdit.getText(), tfDataNasUserEdit.getText(), tfCPFUserEdit.getText(), tfRGUserEdit.getText(), tfTelFixoUserEdit.getText(), tfTelCelUserEdit.getText(), tEmailUserEdit.getText(), false, 2, "123", 1, 0), funcionario.get(row), con);
            } else {
            }
            cancelarEditProfessor();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    private void cancelarEditProfessor() {
        escondeTudo();
        if (cadFunc == 0) {
            jInternalFramePesquisa.setVisible(true);
            rbProfessor.setSelected(true);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            pesquisarProfessor();
        } else if (cadFunc == 1) {
            jInternalFramePesquisa.setVisible(true);
            rbSecretaria.setSelected(true);
            btnVariosPesquisa.setEnabled(true);
            btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
            pesquisaBtn = 1;
            pesquisarSecretaria();
        } else {
        }
    }

    private void setPesquisaEditCurso(int row) {
        tNomeCursoEdit.setText(cursosLista.get(row).getNomeCurso());
        tModCursoEdit.setText("" + cursosLista.get(row).getQuantidadeModulos());
    }

    private void alterarCurso() {
        try {
            Curso cursoAlt = new Curso(tNomeCursoEdit.getText(), Integer.parseInt(tModCursoEdit.getText()), guardaCod);
            cursoBanco.alterar(cursoAlt, con);
            cancelarEditCurso();
        } catch (Exception e) {
        }
    }

    private void cancelarEditCurso() {
        escondeTudo();
        jInternalFramePesquisa.setVisible(true);
        rbCursos.setSelected(true);
        btnVariosPesquisa.setEnabled(true);
        btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
        pesquisaBtn = 1;
        pesquisarCurso();
    }

    private void setPesquisaEditMateria(int linha) {
        try {
            cbNomeCursoMateriaEdit.setSelectedItem(materia.get(linha).getNomeCurso());
            setTurmaMateriaEdit();
            tNomeMateriaEdit.setText(materia.get(linha).getNomeMateria());
            tSiglaMateriaEdit.setText(materia.get(linha).getSigla());
            cbProfessorMateriaEdit.setSelectedItem(materia.get(linha).getProfessorPrincipalS());
            cbDivisaoMateriaEdit.setSelectedItem(materia.get(linha).getDivisaoS());
        } catch (Exception e) {
            System.err.println(e + "SETEDITMAT" + linha);
        }
    }

    private void alterarMateria() {
        if ((!tNomeMateriaEdit.getText().equals("")) && (!tSiglaMateriaEdit.getText().equals("")) && (professores.get(cbProfessorMateriaEdit.getSelectedIndex()) != professores.get(cbDivisaoMateriaEdit.getSelectedIndex()))) {
            materiaBanco.alterar(new Materia(cursosLista.get(cbNomeCursoMateriaEdit.getSelectedIndex()).getCodigo(), turma.get(cbTurmaMateriaEdit.getSelectedIndex()).getCodigo(), tNomeMateriaEdit.getText(), tSiglaMateriaEdit.getText(), professores.get(cbProfessorMateriaEdit.getSelectedIndex()).getCodigo(), professores.get(cbDivisaoMateriaEdit.getSelectedIndex()).getCodigo()), materia.get(jtResultadoAtivo.getSelectedRow()).getCodigo(), con);
            cancelarEditMateria();
        } else {
            JOptionPane.showMessageDialog(null, "Preencha os campos corretamente!", "Erro", 0);
        }
    }

    private void cancelarEditMateria() {
        escondeTudo();
        jInternalFramePesquisa.setVisible(true);
        rbMaterias.setSelected(true);
        btnVariosPesquisa.setEnabled(true);
        btnVariosPesquisa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/NewIcons/eye-close.png")));
        pesquisaBtn = 1;
        pesquisarMaterias();
    }

    private void editarPesquisa() {
        if ((rbProfessor.isSelected())) {
            try {
                cadFunc = 0;
                if (jtResultadoAtivo.getSelectedRow() != -1) {
                    escondeTudo();
                    jInternalFrameAlterarFuncionario.setVisible(true);
                    setPesquisaEdit(jtResultadoAtivo.getSelectedRow());
                    row = jtResultadoAtivo.getSelectedRow();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
                }
            } catch (Exception e) {
            }
        } else if ((rbSecretaria.isSelected())) {
            try {
                cadFunc = 1;
                if (jtResultadoAtivo.getSelectedRow() != -1) {
                    escondeTudo();
                    jInternalFrameAlterarFuncionario.setVisible(true);
                    setPesquisaEdit(jtResultadoAtivo.getSelectedRow());
                    row = jtResultadoAtivo.getSelectedRow();
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
                }
            } catch (Exception e) {
            }
        } else if (rbAluno.isSelected()) {
            if (jtResultadoAtivo.getSelectedRow() != -1) {
                jInternalFrameAlterarAluno.setVisible(true);
                row = jtResultadoAtivo.getSelectedRow();
                try {
                    limparArrays();
                    lerCursoCb();
                    lerTurmaCb();
                    addModuloTurma();
                    setTurmaCurso();
                } catch (Exception e) {
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
        } else if (rbMaterias.isSelected()) {
            try {
                //JOptionPane.showMessageDialog(null, jtResultadoAtivo.getSelectedRow());
                if (jtResultadoAtivo.getSelectedRow() != -1) {
                    lerCursoCb();
                    lerProfCb();
                    setTurmaMateriaEdit();
                    guardaCod = materia.get(jtResultadoAtivo.getSelectedRow()).getCodigo();
                    row = jtResultadoAtivo.getSelectedRow();
                    escondeTudo();
                    jInternalFrameAlterarMaterias.setVisible(true);
                    setPesquisaEditMateria(jtResultadoAtivo.getSelectedRow());

                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
                }
            } catch (Exception e) {
                System.err.println(e + "ERRO NA ALT DE MAT");
            }
        } else if (rbTurmas.isSelected()) {
            jInternalFrameAlterarTurma.setVisible(true);
            row = jtResultadoAtivo.getSelectedRow();
            try {
                limparArrays();
                lerCursoCb();
                addModuloTurma();

            } catch (Exception e) {
            }
        } else if (rbCursos.isSelected()) {
            try {
                if (jtResultadoAtivo.getSelectedRow() != -1) {
                    row = jtResultadoAtivo.getSelectedRow();
                    guardaCod = cursosLista.get(jtResultadoAtivo.getSelectedRow()).getCodigo();
                    escondeTudo();
                    jInternalFrameAlterarCurso.setVisible(true);
                    setPesquisaEditCurso(jtResultadoAtivo.getSelectedRow());
                    turma = turmaBanco.ler(turmaCursoAtivo.replace("txtSql", cursosLista.get(jtResultadoAtivo.getSelectedRow()).getNomeCurso()), con);
                    jtTurmasAtivas.setModel(new javax.swing.table.DefaultTableModel(new Object[turma.size()][turma.size()], new String[]{
                        "Turmas"
                    }));
                    int cont = 0;
                    while (turma.size() > cont) {
                        jtTurmasAtivas.setValueAt(turma.get(cont).getSigla(), cont, 0);
                        cont++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
                }
            } catch (Exception e) {
            }
        } else if (rbHorarios.isSelected()) {
            if (jtResultadoAtivo.getSelectedRow() != -1) {
                escondeTudo();
                horario = horarioBanco.ler(horarioMateria.replace("%txtSql%", horario.get(jtResultadoAtivo.getSelectedRow()).getNomeTurma()), con);
                jInternalFrameAlterarHorario.setVisible(true);
                row = jtResultadoAtivo.getSelectedRow();
                int cont = 0;
                while (horario.size() > cont) {
                    jtHorarioEdit.setValueAt(horario.get(cont).getNomeMateria(), horario.get(cont).getOrdem(), horario.get(cont).getDiaSemana());
                    cont++;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");

            }
        } else {
        }
    }

    private void inativar() {
        if (pesquisaBtn == 0) {
            ativarCadastro();
        } else if (pesquisaBtn == 1) {
            inativarCadastro();
        } else if (pesquisaBtn == 2) {
            excluirCadastro();
        } else {
        }

    }

    private void inativarCadastro() {
        if ((rbProfessor.isSelected())) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja inativar este cadastro?");
                if (e == 0) {
                    //implementar codigo pra ir pro banco
                    usuarioBanco.various("update funcionario set ativo = 0 where cd_func = '" + professores.get(jtResultadoAtivo.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarProfessor();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if ((rbSecretaria.isSelected())) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja inativar este cadastro?");
                if (e == 0) {
                    //implementar codigo pra ir pro banco
                    usuarioBanco.various("update funcionario set ativo = 0 where cd_func = '" + funcionario.get(jtResultadoAtivo.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarSecretaria();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbAluno.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja inativar este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    alunoBanco.various("update aluno set ativo_al = 0 where cd_al = '" + aluno.get(jtResultadoAtivo.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarAluno();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbTurmas.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja inativar este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    turmaBanco.various("update turma set at_turma = 0 where cd_turma = '" + turma.get(jtResultadoAtivo.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarTurmas();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbCursos.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja inativar este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    cursoBanco.various("update curso set at_curso = 0 where cd_curso = '" + cursosLista.get(jtResultadoAtivo.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarCurso();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else {
        }
    }

    private void ativarCadastro() {
        if ((rbProfessor.isSelected())) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja ativar este cadastro?");
                if (e == 0) {
                    //implementar codigo pra ir pro banco
                    usuarioBanco.various("update funcionario set ativo = 1 where cd_func = '" + professores2.get(jtResultadoInativos.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarProfessor();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if ((rbSecretaria.isSelected())) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja ativar este cadastro?");
                if (e == 0) {
                    //implementar codigo pra ir pro banco
                    usuarioBanco.various("update funcionario set ativo = 1 where cd_func = '" + funcionario2.get(jtResultadoInativos.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarSecretaria();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbAluno.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja ativar este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    alunoBanco.various("update aluno set ativo_al = 1 where cd_al = '" + aluno2.get(jtResultadoInativos.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarAluno();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbTurmas.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja ativar este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    turmaBanco.various("update turma set at_turma = 1 where cd_turma = '" + turma2.get(jtResultadoInativos.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarTurmas();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbCursos.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja ativar este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    cursoBanco.various("update curso set at_curso = 1 where cd_curso = '" + cursosLista2.get(jtResultadoInativos.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarCurso();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else {
        }
    }

    private void excluirCadastro() {
        if (rbMaterias.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja excluir este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    materiaBanco.various("delete from materia where cd_mat ='" + materia.get(jtResultadoAtivo.getSelectedRow()).getCodigo() + "'", con);
                    pesquisarMaterias();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        } else if (rbHorarios.isSelected()) {
            if ((jtResultadoAtivo.getSelectedRow() > -1) || (jtResultadoInativos.getSelectedRow() > -1)) {
                int e = JOptionPane.showConfirmDialog(null, "Deseja excluir este cadastro?");
                if (e == 0) {
                    //implementar codigo para ir pro banco
                    horario = horarioBanco.ler(horarioMateria.replace("%txtSql%", horario.get(jtResultadoAtivo.getSelectedRow()).getNomeTurma()), con);
                    int c = 0;
                    try {
                        while (horario.size() > c) {
                            horarioBanco.various("delete from horario_aula where cd_hr = '" + horario.get(c).getCodigo() + "'", con);
                            c++;
                        }
                        JOptionPane.showMessageDialog(null, "Excluido com sucesso!", "Status da Exclusão", 1);
                    } catch (Exception a) {
                        JOptionPane.showMessageDialog(null, "Erro ao excluir cadastro!", "Status da Exclusão", 0);
                    }
                    pesquisarHorario();
                } else {
                    JOptionPane.showMessageDialog(null, "Operação Cancelada");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione um Cadastro");
            }
            jtResultadoAtivo.clearSelection();
        }
    }

    public class Contador implements Runnable {

        private int horarioAtivo = 300;

        @Override
        public void run() {
            while (true) {
                lTempo.setText("Tempo Restante: " + ((horarioAtivo / 60) / 60) + " Hora(s) " + ((horarioAtivo / 60) % 60) + " Minuto(s) " + (horarioAtivo % 60) + " Segundo(s)");
                if (horarioAtivo != 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.err.println(e);
                    }
                    lTempo.setText("Tempo Restante: " + ((horarioAtivo / 60) / 60) + " Hora(s) " + ((horarioAtivo / 60) % 60) + " Minuto(s) " + (horarioAtivo % 60) + " Segundo(s)");
                    horarioAtivo--;
                } else {
                    try {
                        Object[] opcoes = {"Não", "30 Minutos", "50 Minutos", "100 Minutos", "125 Minutos"};
                        Object res = JOptionPane.showInputDialog(null, "Deseja mais tempo?", "Tempo Esgotado", 1, null, opcoes, "tres");
                        if (res.equals("30 Minutos")) {
                            if (testaSenha()) {
                                horarioAtivo = 1800;
                            }
                        } else if (res.equals("50 Minutos")) {
                            if (testaSenha()) {
                                horarioAtivo = 3000;
                            }
                        } else if (res.equals("100 Minutos")) {
                            if (testaSenha()) {
                                horarioAtivo = 6000;
                            }
                        } else if (res.equals("125 Minutos")) {
                            if (testaSenha()) {
                                horarioAtivo = 7500;
                            }
                        } else if (res.equals("Não")) {
                            encerrarConexao();
                            System.exit(0);
                        }
                    } catch (Exception e) {
                        System.err.println(e);
                        encerrarConexao();
                        System.exit(0);
                    }
                }
            }
        }

        public boolean testaSenha() {
            JLabel label = new JLabel("Digite a senha do usuário atual:");
            JPasswordField jpf = new JPasswordField();
            int rest = JOptionPane.showConfirmDialog(null, new Object[]{label, jpf}, "Verificação", JOptionPane.OK_CANCEL_OPTION, 3);
            if (rest == 0) {
                String password = new String(jpf.getPassword());
                if (password.equals(usuarioLogado.getSenha())) {
                    return true;
                } else {
                    encerrarConexao();
                    System.exit(0);
                }
            } else {
                encerrarConexao();
                System.exit(0);
            }
            return true;
        }
    }
}
