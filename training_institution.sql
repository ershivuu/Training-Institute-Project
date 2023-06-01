-- phpMyAdmin SQL Dump
-- version 4.5.4.1deb2ubuntu2.1
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Aug 20, 2022 at 03:54 PM
-- Server version: 5.7.33-0ubuntu0.16.04.1
-- PHP Version: 7.0.33-0ubuntu0.16.04.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `training_institution`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `adminID` varchar(20) NOT NULL DEFAULT 'admin',
  `adminPassword` varchar(15) NOT NULL DEFAULT 'admin123'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`adminID`, `adminPassword`) VALUES
('admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `admissionrequest`
--

CREATE TABLE `admissionrequest` (
  `requestID` int(11) NOT NULL,
  `userID` varchar(20) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `requestDate` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `courseID` int(11) NOT NULL,
  `courseName` varchar(20) NOT NULL,
  `instituteID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE `faculty` (
  `facultyID` int(11) NOT NULL,
  `facultyName` varchar(20) NOT NULL,
  `instituteID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedbackID` int(11) NOT NULL,
  `userID` varchar(20) DEFAULT NULL,
  `instituteID` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `feedbackDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `institute`
--

CREATE TABLE `institute` (
  `instituteID` int(11) NOT NULL,
  `instituteName` varchar(50) NOT NULL,
  `institutePassword` varchar(15) NOT NULL,
  `affiliationDate` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `seats` int(11) NOT NULL,
  `filledseats` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `institute`
--

INSERT INTO `institute` (`instituteID`, `instituteName`, `institutePassword`, `affiliationDate`, `address`, `seats`, `filledseats`) VALUES
(2, 'universal', 'Uni@12345', '2018-02-12', 'bhawarkua', 30, 0);

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `userID` varchar(20) NOT NULL,
  `emailID` varchar(20) NOT NULL,
  `studentName` varchar(40) NOT NULL,
  `qualification` varchar(20) NOT NULL,
  `studentPassword` varchar(15) NOT NULL,
  `number` varchar(13) NOT NULL,
  `address` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`userID`, `emailID`, `studentName`, `qualification`, `studentPassword`, `number`, `address`) VALUES
('shailesh', 'sh@gmail.com', 'Shailesh', 'PostGraduate', 'Sh@12345', '9619554392', 'Indore');

-- --------------------------------------------------------

--
-- Table structure for table `stud_institute`
--

CREATE TABLE `stud_institute` (
  `studentID` varchar(20) NOT NULL,
  `instituteID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`),
  ADD UNIQUE KEY `adminPassword` (`adminPassword`);

--
-- Indexes for table `admissionrequest`
--
ALTER TABLE `admissionrequest`
  ADD PRIMARY KEY (`requestID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `courseID` (`courseID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`courseID`),
  ADD UNIQUE KEY `courseName` (`courseName`),
  ADD KEY `instituteID` (`instituteID`);

--
-- Indexes for table `faculty`
--
ALTER TABLE `faculty`
  ADD PRIMARY KEY (`facultyID`),
  ADD KEY `instituteID` (`instituteID`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `instituteID` (`instituteID`);

--
-- Indexes for table `institute`
--
ALTER TABLE `institute`
  ADD PRIMARY KEY (`instituteID`),
  ADD UNIQUE KEY `instituteName` (`instituteName`),
  ADD UNIQUE KEY `institutePassword` (`institutePassword`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `emailID` (`emailID`),
  ADD UNIQUE KEY `studentPassword` (`studentPassword`),
  ADD UNIQUE KEY `number` (`number`);

--
-- Indexes for table `stud_institute`
--
ALTER TABLE `stud_institute`
  ADD KEY `studentID` (`studentID`),
  ADD KEY `instituteID` (`instituteID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admissionrequest`
--
ALTER TABLE `admissionrequest`
  MODIFY `requestID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `faculty`
--
ALTER TABLE `faculty`
  MODIFY `facultyID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedbackID` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `institute`
--
ALTER TABLE `institute`
  MODIFY `instituteID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `admissionrequest`
--
ALTER TABLE `admissionrequest`
  ADD CONSTRAINT `admissionrequest_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `student` (`userID`),
  ADD CONSTRAINT `admissionrequest_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`);

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

--
-- Constraints for table `faculty`
--
ALTER TABLE `faculty`
  ADD CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

--
-- Constraints for table `feedback`
--
ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `student` (`userID`),
  ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

--
-- Constraints for table `stud_institute`
--
ALTER TABLE `stud_institute`
  ADD CONSTRAINT `stud_institute_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`),
  ADD CONSTRAINT `stud_institute_ibfk_2` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
