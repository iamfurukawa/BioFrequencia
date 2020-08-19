CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `alunoxmateria`
--

DROP TABLE IF EXISTS `alunoxmateria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alunoxmateria` (
  `cd_al_mat` int(11) NOT NULL auto_increment,
  `status_al_mat` varchar(10) default NULL,
  `cd_mat` int(11) default NULL,
  `cd_al` int(11) default NULL,
  PRIMARY KEY  (`cd_al_mat`),
  KEY `fk_alunoXmateria_aluno` (`cd_al`),
  KEY `fk_alunoXmateria_materia` (`cd_mat`),
  CONSTRAINT `fk_alunoXmateria_materia` FOREIGN KEY (`cd_mat`) REFERENCES `materia` (`cd_mat`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_alunoXmateria_aluno` FOREIGN KEY (`cd_al`) REFERENCES `aluno` (`cd_al`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alunoxmateria`
--

LOCK TABLES `alunoxmateria` WRITE;
/*!40000 ALTER TABLE `alunoxmateria` DISABLE KEYS */;
INSERT INTO `alunoxmateria` VALUES (1,'Ativo',1,1),(2,'Ativo',2,1),(3,'Ativo',3,1),(4,'Ativo',4,1),(5,'Ativo',5,1),(6,'Ativo',6,1),(7,'Ativo',7,1),(8,'Ativo',8,1),(9,'Ativo',1,3),(10,'Ativo',2,3),(11,'Ativo',3,3),(12,'Ativo',4,3),(13,'Ativo',5,3),(14,'Ativo',6,3),(15,'Ativo',7,3),(16,'Ativo',8,3),(17,'Ativo',1,4),(18,'Ativo',2,4),(19,'Ativo',3,4),(20,'Ativo',4,4),(21,'Ativo',5,4),(22,'Ativo',6,4),(23,'Ativo',7,4),(24,'Ativo',8,4),(25,'Ativo',1,5),(26,'Ativo',2,5),(27,'Ativo',3,5),(28,'Ativo',4,5),(29,'Ativo',5,5),(30,'Ativo',6,5),(31,'Ativo',7,5),(32,'Ativo',8,5),(33,'Ativo',1,6),(34,'Ativo',2,6),(35,'Ativo',3,6),(36,'Ativo',4,6),(37,'Ativo',5,6),(38,'Ativo',6,6),(39,'Ativo',7,6),(40,'Ativo',8,6),(41,'Ativo',1,7),(42,'Ativo',2,7),(43,'Ativo',3,7),(44,'Ativo',4,7),(45,'Ativo',5,7),(46,'Ativo',6,7),(47,'Ativo',7,7),(48,'Ativo',8,7);
/*!40000 ALTER TABLE `alunoxmateria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funcionario` (
  `cd_func` int(11) NOT NULL auto_increment,
  `nm_func` varchar(50) default NULL,
  `dt_nas_func` varchar(10) default NULL,
  `cpf_func` varchar(15) default NULL,
  `rg_func` varchar(15) default NULL,
  `tel_fix_func` varchar(15) default NULL,
  `tel_cel_func` varchar(15) default NULL,
  `email_func` varchar(30) default NULL,
  `nivel_acesso_func` int(11) default NULL,
  `senha_func` varchar(30) default NULL,
  `ativo` int(11) default NULL,
  PRIMARY KEY  (`cd_func`),
  UNIQUE KEY `cpf_func` (`cpf_func`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
INSERT INTO `funcionario` VALUES (15,'Sem Professor',NULL,'09897706305',NULL,NULL,NULL,NULL,1,NULL,1),(16,'Root','06/12/1996','53616546206','44.340.580.3-SP','(13)3493-6987','(13)98152-6987','root@root.com.br',3,'root',1),(17,'Cristina Morishita','17/05/1968','34526929301','44.963.741.3-SP','(13)3469-6987','(13)98152-6987','cristina_morishita@hotmail.com',1,'123',0),(18,'Dejanira Domingas de Azevedo Lira','06/05/1965','45132238041','44.330.540.3-SP','(13)3493-9851','(13)94569-6987','djanira@live.com',2,'123',1),(19,'Lucas da Silva Santos','05/03/1996','70102179204','44.340.580.3-SP','(13)3493-6987','(13)98152-6987','professor@professor.com.br',9,'123',1),(20,'Graciete Henriques','03/04/1963','47408655212','44.526.271-9-SP','(13)3427-2628','9-9141-4522','gracietehenriques@hotmail.com',1,'123',1),(21,'Beatriz Rocha','02/02/1979','33278353811','44.145.525-1-SP','(13)3466-7726','9-9666-6172','beatrizrocha@gmail.com',1,'123',1),(22,'Diogenes Pereira','14/05/1970','67529940805','44.134.233-3-SP','(13)3331-5235','9-9124-4244','diogenespereira@hotmail.com',1,'123',0),(23,'Andrea Garcia','12/09/1969','08497029925','42.352.566-6-SP','(13)3456-4646','9-9915-2552','andreagarcia@hotmail.com',1,'123',1),(24,'Joelma','12/12/1980','99778120803','14.425.525-3-SP','(13)3462-6666','9-9414-5525','Joelma@gmail.com',1,'123',1),(25,'Alexandre','05/06/1975','05806805506','41.424.525-5-SP','(13)3466-7588','9-9177-2663','alexandre@hotmail.com',1,'123',1),(26,'Fabiana','30/03/1970','02980565342','56.363.662-7-SP','(13)3452-5252','9-9991-7252','fabiana@gmail.com',1,'123',1),(27,'Oswaldo','12/12/1978','24294354038','18.272.677-2-SP','(13)3455-6266','9-9666-6162','oswaldinho@gmail.com',1,'123',1),(28,'Israel','03/03/1969','02749620392','52.552.662-5-SP','(13)3452-5562','9-9925-6776','israel@live.com',1,'123',1),(29,'Cristiane','04/05/1990','56773578124','41.425.662-6-SP','(13)3467-8849','9-9982-6766','cristiane@live.com',1,'123',1);
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `horario_aula`
--

DROP TABLE IF EXISTS `horario_aula`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horario_aula` (
  `cd_hr` int(11) NOT NULL auto_increment,
  `ordem_hr` varchar(10) default NULL,
  `dia_sem_hr` varchar(15) default NULL,
  `cd_mat` int(11) default NULL,
  PRIMARY KEY  (`cd_hr`),
  KEY `fk_horarioA_materia` (`cd_mat`),
  CONSTRAINT `fk_horarioA_materia` FOREIGN KEY (`cd_mat`) REFERENCES `materia` (`cd_mat`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horario_aula`
--

LOCK TABLES `horario_aula` WRITE;
/*!40000 ALTER TABLE `horario_aula` DISABLE KEYS */;
/*!40000 ALTER TABLE `horario_aula` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `turma`
--

DROP TABLE IF EXISTS `turma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `turma` (
  `cd_turma` int(11) NOT NULL auto_increment,
  `sigla_turma` varchar(10) default NULL,
  `mod_turma` int(11) default NULL,
  `sala_turma` varchar(50) default NULL,
  `ano_turma` varchar(4) default NULL,
  `sem_turma` varchar(1) default NULL,
  `per_turma` varchar(15) default NULL,
  `at_turma` int(11) default NULL,
  `cd_curso` int(11) default NULL,
  PRIMARY KEY  (`cd_turma`),
  KEY `fk_turma_curso` (`cd_curso`),
  CONSTRAINT `fk_turma_curso` FOREIGN KEY (`cd_curso`) REFERENCES `curso` (`cd_curso`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `turma`
--

LOCK TABLES `turma` WRITE;
/*!40000 ALTER TABLE `turma` DISABLE KEYS */;
INSERT INTO `turma` VALUES (1,'1I2',1,'05','2014','1','Tarde',1,1),(2,'2I2',2,'11','2013','2','Tarde',0,1),(3,'3I2',3,'12','2013','1','Tarde',1,1),(4,'2ms2',2,'02','2013','2','Tarde',1,2),(5,'1ms3',1,'03','2014','1','Noite',0,1),(6,'2ms3',3,'06','2014','2','Tarde',1,2),(7,'1ENF3',1,'08','2013','2','Noite',0,3),(8,'2ENF2',2,'05','2014','1','Tarde',1,3),(9,'3ENF3',3,'12','2013','1','Noite',1,3),(10,'1ED3',1,'02','2013','1','Noite',1,4),(11,'2ED3',2,'07','2013','2','Noite',0,4),(12,'1AD3',1,'10','2014','1','Noite',1,6);
/*!40000 ALTER TABLE `turma` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `materia`
--

DROP TABLE IF EXISTS `materia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materia` (
  `cd_mat` int(11) NOT NULL auto_increment,
  `nm_mat` varchar(50) default NULL,
  `sigla_mat` varchar(10) default NULL,
  `cd_principal_mat` int(11) default NULL,
  `cd_divisao_mat` int(11) default NULL,
  `cd_turma` int(11) default NULL,
  PRIMARY KEY  (`cd_mat`),
  KEY `fk_materia_turma` (`cd_turma`),
  KEY `fk_materia_divisao` (`cd_divisao_mat`),
  KEY `fk_materia_principal` (`cd_principal_mat`),
  CONSTRAINT `fk_materia_principal` FOREIGN KEY (`cd_principal_mat`) REFERENCES `funcionario` (`cd_func`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_materia_divisao` FOREIGN KEY (`cd_divisao_mat`) REFERENCES `funcionario` (`cd_func`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_materia_turma` FOREIGN KEY (`cd_turma`) REFERENCES `turma` (`cd_turma`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materia`
--

LOCK TABLES `materia` WRITE;
/*!40000 ALTER TABLE `materia` DISABLE KEYS */;
INSERT INTO `materia` VALUES (1,'REDES','RD',23,20,3),(2,'TECNOLOGIA PARA BANCO DE DADOS 3','TLBD3',27,26,3),(3,'PROGRAMAÇÃO PARA COMPUTADORES 2','PC2',27,28,3),(4,'TECNOLOGIA PARA MOBILIDADE','TM',25,21,3),(5,'ETICA ORGANIZACIONAL','ET',29,15,3),(6,'SEGURANÇA DIGITAL','SD',20,15,3),(7,'DESENVOLVIMENTO DE TRABALHO DE CONCLUSÃO DE CURSO','DTCC',20,15,3),(8,'DESENVOLVIMENTO DE SOFTWARE 2','DS 2',24,15,3);
/*!40000 ALTER TABLE `materia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `aluno`
--

DROP TABLE IF EXISTS `aluno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aluno` (
  `cd_al` int(11) NOT NULL auto_increment,
  `num_al` int(11) default NULL,
  `nm_al` varchar(50) default NULL,
  `ra_al` varchar(15) default NULL,
  `sexo_al` int(11) default NULL,
  `dt_nas_al` varchar(10) default NULL,
  `tel_fixo_al` varchar(15) default NULL,
  `tel_cel_al` varchar(15) default NULL,
  `cep_al` varchar(10) default NULL,
  `cidade_al` varchar(30) default NULL,
  `endereco_al` varchar(50) default NULL,
  `nm_resp1_al` varchar(50) default NULL,
  `nm_resp2_al` varchar(50) default NULL,
  `ativo_al` int(11) default NULL,
  PRIMARY KEY  (`cd_al`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aluno`
--

LOCK TABLES `aluno` WRITE;
/*!40000 ALTER TABLE `aluno` DISABLE KEYS */;
INSERT INTO `aluno` VALUES (1,33,'Vinicius Furukawa Carvalho','44.340.580-3-SP',0,'06/12/1996','(13)3493-6543','(13)99815-2114','11708-220','Praia Grande','Rua Dos Crisântemos, 511','José Lucio de Carvalho','Emico Furukawa Carvalho',1),(2,17,'Isabella Proença','45.695.178-9-SP',1,'02/07/1997','(13)3494-9562','(13)94899-8655','98489-949','Monguaguá','Rua alguma rua','Claudio Soares de Proença','Eliana Gonçalves Fumero ',1),(3,21,'Lucas da Silva Santos','49.165.652-3-SP',0,'05/03/1996','(13)9841-2362','(13)99915-2333','65115-323','Praia Grande','Rua extra','Nome do Pai','Nome da Mãe',1),(4,20,'Kayque Gabriel Afra Ribeiro','98.498.948-9-SP',0,'09/05/1996','(13)1986-5654','(13)94989-8498','94894-898','Mongaguá','Rua algumacoisa','Fabrício Roberto Suzana','Mywrka Afra Ribeiro ',1),(5,8,'Caio dos Santos de Araujo','41.156.161-6-SP',0,'10/08/1997','(13)9489-9844','(13)98998-4894','11718-416','Itanhaém','Rua whatislove','Lourival Mendes de Araújo','Marcia dos Santos Silva de Araújo ',1),(6,9,'Caique Assis','98.449.849-8-SP',0,'25/07/1997','(13)6699-4654','(13)94984-6665','11706-226','Mongaguá','Rua da Ostentação','Alexandre Riso','Helena Santos ',1),(7,15,'Wesley','99.849.849-8-SP',0,'29/01/1997','(13)2465-1946','(13)94654-5645','11709-506','Mongaguá','Rua desaparecidos da silva','Não informado','Não informado',0);
/*!40000 ALTER TABLE `aluno` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `cd_curso` int(11) NOT NULL auto_increment,
  `nm_curso` varchar(100) default NULL,
  `mod_curso` int(11) default NULL,
  `at_curso` int(11) default NULL,
  PRIMARY KEY  (`cd_curso`),
  UNIQUE KEY `nm_curso` (`nm_curso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (1,'Informática',3,1),(2,'Manutenção',3,1),(3,'Enfermagem',4,1),(4,'Edificações',3,1),(5,'Logistica',3,0),(6,'Administração',3,1),(7,'Meio Ambiente',3,0);
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
CREATE DATABASE  IF NOT EXISTS `biofrequencia` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `biofrequencia`;
-- MySQL dump 10.13  Distrib 5.1.40, for Win32 (ia32)
--
-- Host: 127.0.0.1    Database: biofrequencia
-- ------------------------------------------------------
-- Server version	5.0.24-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `frequencia`
--

DROP TABLE IF EXISTS `frequencia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `frequencia` (
  `cd_fre` int(11) NOT NULL auto_increment,
  `dr_fre` varchar(15) default NULL,
  `cd_al_mat` int(11) default NULL,
  PRIMARY KEY  (`cd_fre`),
  KEY `fk_frequencia_alunoXmateria` (`cd_al_mat`),
  CONSTRAINT `fk_frequencia_alunoXmateria` FOREIGN KEY (`cd_al_mat`) REFERENCES `alunoxmateria` (`cd_al_mat`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequencia`
--

LOCK TABLES `frequencia` WRITE;
/*!40000 ALTER TABLE `frequencia` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequencia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-04-24 11:03:33
