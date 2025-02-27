-- MySQL dump 10.13  Distrib 9.0.1, for Win64 (x86_64)
--
-- Host: localhost    Database: crs
-- ------------------------------------------------------
-- Server version	9.0.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `courseId` varchar(10) NOT NULL,
  `courseTitle` varchar(255) DEFAULT NULL,
  `creditHours` int NOT NULL,
  `enrollmentCapacity` int NOT NULL,
  `department_departmentId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`courseId`),
  KEY `FK20j20poc1btxrri02ii1okixb` (`department_departmentId`),
  CONSTRAINT `FK20j20poc1btxrri02ii1okixb` FOREIGN KEY (`department_departmentId`) REFERENCES `department` (`departmentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('C001',' Introduction to Information Technology',3,100,'D001'),('C002',' Programming Fundamentals',3,200,'D001'),('C003','Data Structures & Algorithms',6,100,'D001');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departmentId` varchar(10) NOT NULL,
  `departmentName` varchar(255) NOT NULL,
  `faculty_facultyId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`departmentId`),
  KEY `FKllrd0qk4m28ntck1rm00k3q5i` (`faculty_facultyId`),
  CONSTRAINT `FKllrd0qk4m28ntck1rm00k3q5i` FOREIGN KEY (`faculty_facultyId`) REFERENCES `faculty` (`facultyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES ('D001',' Information Technology ','F001'),('D002','Artificial Intelligence & Machine Learning ','F001');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enrollment`
--

DROP TABLE IF EXISTS `enrollment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enrollment` (
  `grade` int NOT NULL,
  `status` varchar(255) DEFAULT NULL,
  `semester_faculty_facultyId` varchar(10) NOT NULL,
  `semester_partOfSemester` varchar(255) NOT NULL,
  `semester_year` int NOT NULL,
  `course_courseId` varchar(10) NOT NULL,
  `student_studentId` varchar(255) NOT NULL,
  PRIMARY KEY (`course_courseId`,`semester_faculty_facultyId`,`semester_partOfSemester`,`semester_year`,`student_studentId`),
  KEY `FKrhc1webcnk4m8ro0id6ypvr5c` (`semester_faculty_facultyId`,`semester_partOfSemester`,`semester_year`),
  KEY `FKr4wsthvc0g24pl8hlfr31fntv` (`student_studentId`),
  CONSTRAINT `FK1ecx7twn3hyetc1k4s73kfl75` FOREIGN KEY (`course_courseId`) REFERENCES `course` (`courseId`),
  CONSTRAINT `FKr4wsthvc0g24pl8hlfr31fntv` FOREIGN KEY (`student_studentId`) REFERENCES `student` (`studentId`),
  CONSTRAINT `FKrhc1webcnk4m8ro0id6ypvr5c` FOREIGN KEY (`semester_faculty_facultyId`, `semester_partOfSemester`, `semester_year`) REFERENCES `semester` (`faculty_facultyId`, `partOfSemester`, `year`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enrollment`
--

LOCK TABLES `enrollment` WRITE;
/*!40000 ALTER TABLE `enrollment` DISABLE KEYS */;
INSERT INTO `enrollment` VALUES (0,'ENROLLED','F001','FIRST',2025,'C001','S001'),(0,'ENROLLED','F001','FIRST',2025,'C002','S001');
/*!40000 ALTER TABLE `enrollment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `faculty` (
  `facultyId` varchar(10) NOT NULL,
  `facultyName` varchar(255) NOT NULL,
  PRIMARY KEY (`facultyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES ('F001','Faculty of Computer Science');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prerequisites`
--

DROP TABLE IF EXISTS `prerequisites`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prerequisites` (
  `prerequisitesCourse_courseId` varchar(10) NOT NULL,
  `course_courseId` varchar(10) NOT NULL,
  PRIMARY KEY (`course_courseId`,`prerequisitesCourse_courseId`),
  KEY `FKqnbouiqcb4em98s9fekedc0th` (`prerequisitesCourse_courseId`),
  CONSTRAINT `FKeg409oq0cnhtv9b05qx437saf` FOREIGN KEY (`course_courseId`) REFERENCES `course` (`courseId`),
  CONSTRAINT `FKqnbouiqcb4em98s9fekedc0th` FOREIGN KEY (`prerequisitesCourse_courseId`) REFERENCES `course` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prerequisites`
--

LOCK TABLES `prerequisites` WRITE;
/*!40000 ALTER TABLE `prerequisites` DISABLE KEYS */;
INSERT INTO `prerequisites` VALUES ('C001','C003');
/*!40000 ALTER TABLE `prerequisites` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program`
--

DROP TABLE IF EXISTS `program`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program` (
  `programId` varchar(255) NOT NULL,
  `programTitle` varchar(255) NOT NULL,
  `totalSemester` int NOT NULL,
  `faculty_facultyId` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`programId`),
  KEY `FKfv0huhau8t4v2hx2urpe404r7` (`faculty_facultyId`),
  CONSTRAINT `FKfv0huhau8t4v2hx2urpe404r7` FOREIGN KEY (`faculty_facultyId`) REFERENCES `faculty` (`facultyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program`
--

LOCK TABLES `program` WRITE;
/*!40000 ALTER TABLE `program` DISABLE KEYS */;
INSERT INTO `program` VALUES ('P001',' Bachelor’s in Computer Science',8,'F001');
/*!40000 ALTER TABLE `program` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `program_details`
--

DROP TABLE IF EXISTS `program_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `program_details` (
  `semester` int NOT NULL,
  `program_programId` varchar(255) NOT NULL,
  `course_courseId` varchar(10) NOT NULL,
  PRIMARY KEY (`course_courseId`,`program_programId`),
  KEY `FK2i7w6eqecq29lbkg2awp6dnto` (`program_programId`),
  CONSTRAINT `FK2i7w6eqecq29lbkg2awp6dnto` FOREIGN KEY (`program_programId`) REFERENCES `program` (`programId`),
  CONSTRAINT `FKffmjgai8enhtfvda90f6x3ic8` FOREIGN KEY (`course_courseId`) REFERENCES `course` (`courseId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `program_details`
--

LOCK TABLES `program_details` WRITE;
/*!40000 ALTER TABLE `program_details` DISABLE KEYS */;
INSERT INTO `program_details` VALUES (1,'P001','C001'),(1,'P001','C002'),(2,'P001','C003');
/*!40000 ALTER TABLE `program_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `semester`
--

DROP TABLE IF EXISTS `semester`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `semester` (
  `partOfSemester` varchar(255) NOT NULL,
  `year` int NOT NULL,
  `endDate` date DEFAULT NULL,
  `startDate` date DEFAULT NULL,
  `faculty_facultyId` varchar(10) NOT NULL,
  PRIMARY KEY (`faculty_facultyId`,`partOfSemester`,`year`),
  CONSTRAINT `FK9x9ws7xxk1ywxu2fynmclvpv3` FOREIGN KEY (`faculty_facultyId`) REFERENCES `faculty` (`facultyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `semester`
--

LOCK TABLES `semester` WRITE;
/*!40000 ALTER TABLE `semester` DISABLE KEYS */;
INSERT INTO `semester` VALUES ('FIRST',2025,'2025-08-26','2025-02-26','F001');
/*!40000 ALTER TABLE `semester` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `studentId` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `dob` date NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `studentName` varchar(255) DEFAULT NULL,
  `year` int NOT NULL,
  `program_programId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`studentId`),
  KEY `FKs4t90hl48w38i7nyc7vcerf8r` (`program_programId`),
  CONSTRAINT `FKs4t90hl48w38i7nyc7vcerf8r` FOREIGN KEY (`program_programId`) REFERENCES `program` (`programId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('S001','Elpitiya,\nGalle.','2025-01-14','win@email.com','Winod Shalinda',2025,'P001');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `faculty_facultyId` varchar(10) DEFAULT NULL,
  `student_studentId` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userName`),
  KEY `FK203bvhtn54tphga8pie22nafv` (`faculty_facultyId`),
  KEY `FKhj4fumrqh5x9pxsfih54kpile` (`student_studentId`),
  CONSTRAINT `FK203bvhtn54tphga8pie22nafv` FOREIGN KEY (`faculty_facultyId`) REFERENCES `faculty` (`facultyId`),
  CONSTRAINT `FKhj4fumrqh5x9pxsfih54kpile` FOREIGN KEY (`student_studentId`) REFERENCES `student` (`studentId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('Admin1','Admin1','ADMIN',NULL,NULL),('Admin2','Admin2','ADMIN',NULL,NULL),('F001','Faculty1','FACULTY','F001',NULL),('S001','S1','STUDENT',NULL,'S001');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-27 22:40:10
