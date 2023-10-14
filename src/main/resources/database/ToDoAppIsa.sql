DROP DATABASE todoapp;
CREATE DATABASE IF NOT EXISTS todoapp;
USE todoapp;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `todoapp`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`%` PROCEDURE `checkIn` (IN `user_username` VARCHAR(90), IN `user_userPassword` VARCHAR(12), OUT `log_idLog` INT)   BEGIN
  SET @encrypted_userPsswd = SHA2(user_userPassword, 256);
  Set @current_time =  CURRENT_TIMESTAMP;
  set @user_userId = (select id_user from `user` where BINARY `user_userPassword` = @encrypted_userPsswd AND user.username = user_username);
  set @active = 1;
  UPDATE `user` SET user_enable = @active WHERE @user_userId = user.id_user;
  Insert into usersLog( usersLog.user_idUser, usersLog.log_date) values ( @user_userId, @current_time);
  set log_idLog = (Select usersLog.id_log from usersLog where 	usersLog.user_idUser =  @user_userId and usersLog.log_date = @current_time );
END$$

CREATE DEFINER=`root`@`%` PROCEDURE `checkNotUniqueUsername` (IN `posible_username` VARCHAR(90), OUT `is_not_unique` INT)   BEGIN
set is_not_unique = (SELECT COUNT(id_user) from `user` where username = posible_username);
END$$

CREATE DEFINER=`root`@`%` PROCEDURE `createCategory` (IN `NameCategory` VARCHAR(90))   BEGIN
    INSERT INTO category (nameCategory)
    VALUES (nameCategory);
END$$

CREATE DEFINER=`root`@`%` PROCEDURE `createTask` (IN `task_taskName` VARCHAR(255), IN `task_details` VARCHAR(255), IN `task_startDate` TIMESTAMP, IN `task_dueDate` TIMESTAMP, IN `task_taskStatus` VARCHAR(90), IN `task_priority` INT, IN `task_categoryId` INT, IN `task_userId` INT)   BEGIN
    
    
    INSERT INTO task (name_task, details, start_date, due_date, task_status, priority, category_idCategory, user_idUser)
    
    
    VALUES (task_taskName, task_details,task_startDate, task_dueDate, task_taskStatus, task_priority, task_categoryId,task_userId );
END$$

CREATE DEFINER=`root`@`%` PROCEDURE `createUser` (IN `user_username` VARCHAR(90), IN `user_email` VARCHAR(120), IN `user_userPassword` VARCHAR(12), IN `user_firstname` VARCHAR(90), IN `user_lastname` VARCHAR(120), IN `user_userEnable` BOOLEAN)   BEGIN
    INSERT INTO `user` (username, email, `user_password` , firstname, lastname, user_enable)
    VALUES (user_username, user_email, user_userPassword , user_firstname, user_lastname, user_userEnable);
END$$

CREATE DEFINER=`root`@`%` PROCEDURE `logout` (IN `user_username` VARCHAR(90), IN `log_idLog` INT)   BEGIN
set @user_idUser = (select id_user from `user` where username = user_username);
update `user` set user_enable = 0  where id_user = @user_idUser;

update usersLog set log_out_date = CURRENT_TIMESTAMP where usersLog.id_log like log_idLog;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id_category` int NOT NULL,
  `name` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `task`
--

CREATE TABLE `task` (
  `id_task` int NOT NULL,
  `task_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `details` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `start_date` timestamp NOT NULL,
  `due_date` timestamp NOT NULL,
  `task_status` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `task_priority` int NOT NULL,
  `category_id_category` int NOT NULL,
  `user_id_user` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int NOT NULL,
  `username` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_password` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `firstname` varchar(90) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `lastname` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_enable` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `email`, `user_password`, `firstname`, `lastname`, `user_enable`) VALUES
(112, 'lulilu', 'lulilu@gmail.com', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'luz', 'lupilu', 0);

--
-- Triggers `user`
--
DELIMITER $$
CREATE TRIGGER `encryptPassword` BEFORE INSERT ON `user` FOR EACH ROW BEGIN
    SET NEW.`user_password` = SHA2(NEW.`user_password`, 256);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `usersLog`
--

CREATE TABLE `usersLog` (
  `id_log` int NOT NULL,
  `user_id_user` int DEFAULT NULL,
  `log_date` timestamp NULL DEFAULT NULL,
  `log_out_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `usersLog`
--

INSERT INTO `usersLog` (`id_log`, `user_id_user`, `log_date`, `log_out_date`) VALUES
(24, NULL, '2023-10-12 03:51:27', NULL),
(25, NULL, '2023-10-12 03:51:27', NULL),
(26, NULL, '2023-10-12 03:51:27', NULL),
(27, NULL, '2023-10-12 03:51:41', NULL),
(28, NULL, '2023-10-12 03:51:42', NULL),
(29, NULL, '2023-10-12 03:51:42', NULL),
(30, NULL, '2023-10-12 03:55:55', NULL),
(31, NULL, '2023-10-12 03:56:14', NULL),
(32, NULL, '2023-10-12 04:01:51', NULL),
(33, NULL, '2023-10-12 04:04:22', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id_category`),
  ADD UNIQUE KEY `idx_unique_category` (`name`) USING BTREE;

--
-- Indexes for table `task`
--
ALTER TABLE `task`
  ADD PRIMARY KEY (`id_task`),
  ADD KEY `Tasks_Category` (`category_id_category`),
  ADD KEY `Tasks_User` (`user_id_user`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `idx_unique_user_username` (`username`) USING BTREE;
ALTER TABLE `user` ADD FULLTEXT KEY `idx_fulltext_user_name_lastname` (`firstname`,`lastname`);

--
-- Indexes for table `usersLog`
--
ALTER TABLE `usersLog`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `UsersLog_User` (`user_id_user`),
  ADD KEY `idx_log_date` (`log_date`),
  ADD KEY `idx_log_out_date` (`log_out_date`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id_category` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `task`
--
ALTER TABLE `task`
  MODIFY `id_task` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=113;

--
-- AUTO_INCREMENT for table `usersLog`
--
ALTER TABLE `usersLog`
  MODIFY `id_log` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `task`
--
ALTER TABLE `task`
  ADD CONSTRAINT `tasks_category` FOREIGN KEY (`category_id_category`) REFERENCES `category` (`id_category`),
  ADD CONSTRAINT `tasks_user` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `usersLog`
--
ALTER TABLE `usersLog`
  ADD CONSTRAINT `usersLog_User` FOREIGN KEY (`user_id_user`) REFERENCES `user` (`id_user`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

