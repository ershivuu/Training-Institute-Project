
--
-- Database name : training_institution

CREATE TABLE `admin` (
  `adminID` varchar(20) NOT NULL DEFAULT 'admin',
  `adminPassword` varchar(15) NOT NULL DEFAULT 'admin123'
);

INSERT INTO `admin` (`adminID`, `adminPassword`) VALUES
('admin', 'admin123');

CREATE TABLE `admissionrequest` (
  `requestID` int(11) NOT NULL,
  `userID` varchar(20) DEFAULT NULL,
  `courseID` int(11) DEFAULT NULL,
  `requestDate` date DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL
);

CREATE TABLE `course` (
  `courseID` int(11) NOT NULL,
  `courseName` varchar(20) NOT NULL,
  `instituteID` int(11) DEFAULT NULL
);


CREATE TABLE `faculty` (
  `facultyID` int(11) NOT NULL,
  `facultyName` varchar(20) NOT NULL,
  `instituteID` int(11) DEFAULT NULL
);

CREATE TABLE `feedback` (
  `feedbackID` int(11) NOT NULL,
  `userID` varchar(20) DEFAULT NULL,
  `instituteID` int(11) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `feedbackDate` date DEFAULT NULL
);


CREATE TABLE `institute` (
  `instituteID` int(11) NOT NULL,
  `instituteName` varchar(50) NOT NULL,
  `institutePassword` varchar(15) NOT NULL,
  `affiliationDate` date NOT NULL,
  `address` varchar(100) NOT NULL,
  `seats` int(11) NOT NULL,
  `filledseats` int(11) NOT NULL DEFAULT '0'
);


CREATE TABLE `student` (
  `userID` varchar(20) NOT NULL,
  `emailID` varchar(20) NOT NULL,
  `studentName` varchar(40) NOT NULL,
  `qualification` varchar(20) NOT NULL,
  `studentPassword` varchar(15) NOT NULL,
  `number` varchar(13) NOT NULL,
  `address` varchar(100) NOT NULL
);

CREATE TABLE `stud_institute` (
  `studentID` varchar(20) NOT NULL,
  `instituteID` int(11) NOT NULL
);

ALTER TABLE `admin`
  ADD PRIMARY KEY (`adminID`),
  ADD UNIQUE KEY `adminPassword` (`adminPassword`);

ALTER TABLE `admissionrequest`
  ADD PRIMARY KEY (`requestID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `courseID` (`courseID`);

ALTER TABLE `course`
  ADD PRIMARY KEY (`courseID`),
  ADD UNIQUE KEY `courseName` (`courseName`),
  ADD KEY `instituteID` (`instituteID`);

ALTER TABLE `faculty`
  ADD PRIMARY KEY (`facultyID`),
  ADD KEY `instituteID` (`instituteID`);

ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedbackID`),
  ADD KEY `userID` (`userID`),
  ADD KEY `instituteID` (`instituteID`);

ALTER TABLE `institute`
  ADD PRIMARY KEY (`instituteID`),
  ADD UNIQUE KEY `instituteName` (`instituteName`),
  ADD UNIQUE KEY `institutePassword` (`institutePassword`);

ALTER TABLE `student`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `emailID` (`emailID`),
  ADD UNIQUE KEY `studentPassword` (`studentPassword`),
  ADD UNIQUE KEY `number` (`number`);

ALTER TABLE `stud_institute`
  ADD KEY `studentID` (`studentID`),
  ADD KEY `instituteID` (`instituteID`);

ALTER TABLE `admissionrequest`
  MODIFY `requestID` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `course`
  MODIFY `courseID` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `faculty`
  MODIFY `facultyID` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `feedback`
  MODIFY `feedbackID` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `institute`
  MODIFY `instituteID` int(11) NOT NULL AUTO_INCREMENT;

ALTER TABLE `admissionrequest`
  ADD CONSTRAINT `admissionrequest_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `student` (`userID`),
  ADD CONSTRAINT `admissionrequest_ibfk_2` FOREIGN KEY (`courseID`) REFERENCES `course` (`courseID`);

ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

ALTER TABLE `faculty`
  ADD CONSTRAINT `faculty_ibfk_1` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

ALTER TABLE `feedback`
  ADD CONSTRAINT `feedback_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `student` (`userID`),
  ADD CONSTRAINT `feedback_ibfk_2` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);

ALTER TABLE `stud_institute`
  ADD CONSTRAINT `stud_institute_ibfk_1` FOREIGN KEY (`studentID`) REFERENCES `student` (`userID`),
  ADD CONSTRAINT `stud_institute_ibfk_2` FOREIGN KEY (`instituteID`) REFERENCES `institute` (`instituteID`);
