create database biofrequencia;
use biofrequencia;

create table aluno(
    cd_al int primary key auto_increment not null,
    num_al int,
    nm_al varchar(50),
    ra_al varchar(15) unique,
    sexo_al int,
    dt_nas_al varchar(10),
    tel_fixo_al varchar(15),
    tel_cel_al varchar(15),
    cep_al varchar(10),
    cidade_al varchar(30),
    endereco_al varchar(50),
    nm_resp1_al varchar(50),
    nm_resp2_al varchar(50),
    ativo_al int
    -- falta adicionar foto e digital
);

create table alunoXmateria(
    cd_al_mat int primary key not null auto_increment,
    status_al_mat varchar(10),
    cd_mat int,
    cd_al int
);

create table frequencia(
    cd_fre int primary key not null auto_increment,
    dr_fre varchar(15),
    cd_al_mat int
);

create table materia(
    cd_mat int primary key not null auto_increment,
    nm_mat varchar(50),
    sigla_mat varchar(10),
    cd_principal_mat int,
    cd_divisao_mat int,
    cd_turma int
);

create table curso(
    cd_curso int primary key not null auto_increment,
    nm_curso varchar(100) unique,
    mod_curso int,
    at_curso int
);

create table turma(
    cd_turma int primary key not null auto_increment,
    sigla_turma varchar(10),
    mod_turma int,
    sala_turma varchar(50),
    ano_turma varchar(4),
    sem_turma varchar(1),
    per_turma varchar(15),
    at_turma int,
    cd_curso int
);

create table funcionario(
    cd_func int primary key auto_increment not null,
    nm_func varchar(50),
    dt_nas_func varchar(10),
    cpf_func varchar(15) unique,
    rg_func varchar(15),
    tel_fix_func varchar(15),
    tel_cel_func varchar(15),
    email_func varchar(30),
    nivel_acesso_func int,
    senha_func varchar(30),
    ativo int
);

create table horario_aula(
    cd_hr int primary key not null auto_increment,
    ordem_hr varchar(10),
    dia_sem_hr varchar(15),
    cd_mat int
);

-- ALTER TABLE

	-- ALTER TABLE (FREQUENCIA)
		alter table frequencia 
		add constraint fk_frequencia_alunoXmateria
		foreign key (cd_al_mat)
		references alunoXmateria (cd_al_mat)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (FREQUENCIA)

	-- ALTER TABLE (ALUNOXMATERIA)
		alter table alunoXmateria 
		add constraint fk_alunoXmateria_aluno
		foreign key (cd_al)
		references aluno (cd_al)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (ALUNOXMATERIA)

	-- ALTER TABLE (ALUNOXMATERIA)
		alter table alunoXmateria 
		add constraint fk_alunoXmateria_materia
		foreign key (cd_mat)
		references materia (cd_mat)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (ALUNOXMATERIA)

	-- ALTER TABLE (MATERIA)
		alter table materia 
		add constraint fk_materia_turma
		foreign key (cd_turma)
		references turma (cd_turma)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (MATERIA)

	-- ALTER TABLE (MATERIA)
		alter table materia 
		add constraint fk_materia_divisao
		foreign key (cd_divisao_mat)
		references funcionario (cd_func)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (MATERIA)

	-- ALTER TABLE (MATERIA)
		alter table materia 
		add constraint fk_materia_principal
		foreign key (cd_principal_mat)
		references funcionario (cd_func)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (MATERIA)

	-- ALTER TABLE (TURMA)
		alter table turma 
		add constraint fk_turma_curso
		foreign key (cd_curso)
		references curso (cd_curso)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (TURMA)

	-- ALTER TABLE (HORARIO_AULA)
		alter table horario_aula 
		add constraint fk_horarioA_materia
		foreign key (cd_mat)
		references materia (cd_mat)
		on update cascade on delete set null;
	-- FIM ALTER TABLE (HORARIO_AULA)

-- FIM ALTER TABLE

insert into funcionario values(null,'Root','06/12/1996','53616546206','44.340.580.3-SP','(13)3493-6987',
'(13)98152-6987','root@root.com.br','3','root','1');

insert into funcionario values(null,'Sem Professor',null,'09897706305',null,null,null,null,'1',null,'1');

insert into funcionario values(null,'Cristina Morishita','17/05/1968','34526929301','44.963.741.3-SP','(13)3469-6987',
'(13)98152-6987','cristina_morishita@hotmail.com','1','123','1');

insert into funcionario values(null,'Dejanira Domingas de Azevedo Lira','06/05/1965','45132238041','44.330.540.3-SP','(13)3493-9851',
'(13)94569-6987','djanira@live.com','2','123','1');

insert into funcionario values(null,'Lucas','06/05/1965','70102179204','44.330.540.3-SP','(13)3493-9851',
'(13)94569-6987','djanira@live.com','9','123','1');

/*
select horario_aula.cd_hr, horario_aula.ordem_hr, horario_aula.dia_sem_hr, horario_aula.cd_mat,materia.nm_mat, curso.nm_curso, turma.sigla_turma, turma.per_turma 
from horario_aula natural join turma natural join curso natural join materia group by turma.sigla_turma;



select curso.nm_curso, turma.sigla_turma, turma.per_turma from horario_aula natural join turma natural join curso group by turma.sigla_turma;

select * from alunoxmateria;
select * from materia natural join turma natural join curso ;
select * from horario_aula;
select * from curso;
select * from funcionario;
select * from materia;
select * from turma;
select * from alunoxmateria natural join turma where alunoxmateria.cd_al = 1;
update curso set nm_curso = 'oi', mod_curso = '1' where cd_curso = '1';
-- select de cursos cadastrados
select * from alunoxmateria natural join materia natural join turma natural join 
curso where alunoxmateria.cd_al = 1 group by nm_curso;
-- select de cursos não cadastrados
select * from curso where cd_curso != 1;
select * from turma natural join curso where at_turma = 1;
/*
uPDATE aluno SET ativo_al = '0' where cd_al = 3;
'(13)98152-6987','professor@professor.com.br','9','123','1');

use biofrequencia;
select * from funcionario;
-- update funcionario set senha_func = 'root' where cd_func = '1';

select * from funcionario ;
select * from materia natural join turma natural join curso;
select * from materia;
select * from aluno;

select * from funcionario where cpf_func = "53616546206";

select * from funcionario;	

-- SET SQL_SAFE_UPDATES=0;
-- UPDATE funcionario SET ativo = '1' where cd_func = 2;
-- UPDATE funcionario SET notificacoes_func = '1';

update curso set at_curso = 1 where cd_curso = 3;
insert into periodo values (null,'Manhã','7h30 às 8h20','8h20 às 9h10','9h10 às 10h00','10h20 às 11h10','11h10 às 12h00','12h00 às 12h50','10h00 ÀS 10h20','');
insert into periodo values (null,'Tarde','13h30 às 14h20','14h20 às 15h10','15h10 às 16h00','16h20 às 17h10','17h10 às 18h00','18h00 às 18h50','15h35 ÀS 15h55','16h00 ÀS 16h20');
insert into periodo values (null,'Noite','19h00 às 19h55','19h55 às 20h50','21h10 às 22h05','22h05 às 23h00','','','20h50 ÀS 21h10','');

select * from materia;
select * from curso;
select * from turma;
select * from aluno;
select * from funcionario;
select * from periodo;


use biofrequencia;
UPDATE curso SET at_curso = '0' where cd_curso =  3;

select * from curso where at_curso = 1 && nm_curso like '&a&';

select cd_mat,nm_mat,sigla_mat,cd_principal_mat,(select nm_func from funcionario natural join materia where cd_principal_mat = cd_func),cd_divisao_mat,(select nm_func from funcionario natural join materia where cd_divisao_mat = cd_func),cd_curso,(select curso.nm_curso from curso inner join materia where materia.cd_curso = curso.cd_curso), mod_curso from materia;




set @princ := 0;
set @divisao := 0;
set @cdcurso := 2;

select cd_mat,nm_mat,sigla_mat, 
@princ:=cd_principal_mat,
(select nm_func from funcionario natural join materia where cd_principal_mat = cd_func && cd_principal_mat = @princ),
@divisao:= cd_divisao_mat,
(select nm_func from funcionario natural join materia where cd_divisao_mat = cd_func && cd_divisao_mat = @divisao),
@cdcurso:= cd_curso,
(select curso.nm_curso from curso natural join materia where materia.cd_curso = curso.cd_curso && materia.cd_curso = @cdcurso),
mod_curso from materia;

select curso.nm_curso from curso natural join materia where materia.cd_curso = @cdcurso;

select nm_func from funcionario natural join materia where cd_principal_mat = cd_func && cd_principal_mat = @cdcurso=;
select funcionario.nm_func from funcionario inner join materia on materia.cd_divisao_mat = funcionario.cd_func;

select @princ:=cd_principal_mat,@princ from materia;
select nm_func from funcionario natural join materia where cd_principal_mat = cd_func group by nm_func;



select (select nm_per from periodo natural join turma where cd_periodo = cd_per) from turma natural join curso where at_turma = 1;
select * from turma natural join curso where at_turma =  && sigla_turma like '%1%';
*/

select * from aluno;