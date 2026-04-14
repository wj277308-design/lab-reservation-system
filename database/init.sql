-- 创建数据库
CREATE DATABASE IF NOT EXISTS lab_reservation CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE lab_reservation;

-- 用户表
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT 'Username',
  `password` varchar(100) NOT NULL COMMENT 'Password',
  `real_name` varchar(50) DEFAULT NULL COMMENT 'Real Name',
  `role` varchar(20) DEFAULT 'STUDENT' COMMENT 'Role: STUDENT, TEACHER, ADMIN',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='User Table';

-- 实验室分类表
CREATE TABLE IF NOT EXISTS `lab_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT 'Category Name',
  `description` text COMMENT 'Description',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Laboratory Category Table';

-- 实验室表
CREATE TABLE IF NOT EXISTS `lab` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT 'Lab Name',
  `category_id` bigint(20) DEFAULT NULL COMMENT 'Category ID',
  `manager_id` bigint(20) DEFAULT NULL COMMENT 'Manager ID (Teacher)',
  `location` varchar(100) DEFAULT NULL COMMENT 'Location',
  `capacity` int(11) DEFAULT 30 COMMENT 'Capacity',
  `description` text COMMENT 'Description',
  `status` varchar(20) DEFAULT 'AVAILABLE' COMMENT 'Status: AVAILABLE, MAINTENANCE, IN_USE',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_manager_id` (`manager_id`),
  CONSTRAINT `fk_lab_category` FOREIGN KEY (`category_id`) REFERENCES `lab_category` (`id`),
  CONSTRAINT `fk_lab_manager` FOREIGN KEY (`manager_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Laboratory Table';

-- 预约记录表
CREATE TABLE IF NOT EXISTS `reservation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT 'User ID',
  `lab_id` bigint(20) NOT NULL COMMENT 'Lab ID',
  `reserve_date` date NOT NULL COMMENT 'Reservation Date',
  `time_slot` varchar(20) NOT NULL COMMENT 'Time Slot: MORNING, AFTERNOON, EVENING',
  `reason` varchar(255) DEFAULT NULL COMMENT 'Reason',
  `status` varchar(20) DEFAULT 'PENDING' COMMENT 'Status: PENDING, APPROVED, REJECTED, COMPLETED',
  `audit_time` datetime DEFAULT NULL COMMENT 'Audit Time',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_lab_id` (`lab_id`),
  CONSTRAINT `fk_reservation_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `fk_reservation_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Reservation Table';

-- 报修记录表
CREATE TABLE IF NOT EXISTS `repair` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT 'Reporter ID',
  `lab_id` bigint(20) NOT NULL COMMENT 'Lab ID',
  `description` text NOT NULL COMMENT 'Problem Description',
  `repair_unit` varchar(100) DEFAULT NULL COMMENT 'Repair Unit',
  `status` varchar(20) DEFAULT 'PENDING' COMMENT 'Status: PENDING, ASSIGNED, COMPLETED',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_repair_user` (`user_id`),
  KEY `idx_repair_lab` (`lab_id`),
  CONSTRAINT `fk_repair_user` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `fk_repair_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Repair Record Table';

-- 检修记录表
CREATE TABLE IF NOT EXISTS `maintenance` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `lab_id` bigint(20) NOT NULL COMMENT 'Lab ID',
  `maintainer` varchar(50) NOT NULL COMMENT 'Maintainer Name',
  `content` text NOT NULL COMMENT 'Maintenance Content',
  `maintenance_date` date NOT NULL COMMENT 'Maintenance Date',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_maintenance_lab` (`lab_id`),
  CONSTRAINT `fk_maintenance_lab` FOREIGN KEY (`lab_id`) REFERENCES `lab` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='Maintenance Record Table';

-- 系统公告表
CREATE TABLE IF NOT EXISTS `announcement` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL COMMENT 'Title',
  `content` text NOT NULL COMMENT 'Content',
  `publisher_id` bigint(20) NOT NULL COMMENT 'Publisher ID (Admin)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_announcement_publisher` (`publisher_id`),
  CONSTRAINT `fk_announcement_publisher` FOREIGN KEY (`publisher_id`) REFERENCES `sys_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='System Announcement Table';

-- 插入初始用户数据
INSERT INTO `sys_user` (`username`, `password`, `real_name`, `role`) VALUES 
('admin', '$2a$10$9.t.v./8/S.j9.p./8/S.u3U0s4S5E6F7G8H9I0J1K2L3M4N5O6', 'Administrator', 'ADMIN'), -- 密码已加密
('student', '$2a$10$9.t.v./8/S.j9.p./8/S.u3U0s4S5E6F7G8H9I0J1K2L3M4N5O6', 'Zhang San', 'STUDENT'), -- 密码已加密
('teacher', '$2a$10$9.t.v./8/S.j9.p./8/S.u3U0s4S5E6F7G8H9I0J1K2L3M4N5O6', 'Li Si', 'TEACHER'), -- 密码已加密
('teacher2', '$2a$10$9.t.v./8/S.j9.p./8/S.u3U0s4S5E6F7G8H9I0J1K2L3M4N5O6', 'Wang Wu', 'TEACHER'); -- 密码已加密

-- 插入初始分类数据
INSERT INTO `lab_category` (`name`, `description`) VALUES 
('Computer Science', 'Labs for CS department'),
('Physics', 'Labs for Physics department'),
('Chemistry', 'Labs for Chemistry department'),
('Biology', 'Labs for Biology department');

-- 插入初始实验室数据
INSERT INTO `lab` (`name`, `category_id`, `manager_id`, `location`, `capacity`, `description`, `status`) VALUES 
('Computer Lab 101', 1, 3, 'Building A, Room 101', 50, 'General purpose computer lab with 50 workstations.', 'AVAILABLE'),
('Physics Lab 202', 2, 4, 'Building B, Room 202', 30, 'Equipped for mechanics and optics experiments.', 'AVAILABLE'),
('Chemistry Lab 303', 3, 3, 'Building C, Room 303', 25, 'Standard chemistry lab with fume hoods.', 'MAINTENANCE'),
('AI Research Lab', 1, 3, 'Building A, Room 404', 20, 'High-performance computing cluster for AI research.', 'AVAILABLE'),
('Biology Lab 505', 4, 4, 'Building B, Room 505', 35, 'Microscopes and biological sample storage.', 'AVAILABLE');

-- 插入初始公告数据
INSERT INTO `announcement` (`title`, `content`, `publisher_id`) VALUES 
('System Maintenance Notice', 'The system will undergo maintenance this Sunday.', 1),
('Welcome New Students', 'Welcome to the new semester! Please check lab schedules.', 1);

-- 插入初始预约数据
INSERT INTO `reservation` (`user_id`, `lab_id`, `reserve_date`, `time_slot`, `reason`, `status`) VALUES 
(2, 1, DATE_ADD(CURDATE(), INTERVAL 1 DAY), 'MORNING', 'Class project work', 'APPROVED'),
(2, 4, DATE_ADD(CURDATE(), INTERVAL 2 DAY), 'AFTERNOON', 'Deep learning experiment', 'PENDING');
