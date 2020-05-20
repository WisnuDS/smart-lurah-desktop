-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: May 18, 2020 at 08:26 PM
-- Server version: 5.7.30-0ubuntu0.18.04.1
-- PHP Version: 7.2.24-0ubuntu0.18.04.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `DBsmartlurah`
--

-- --------------------------------------------------------

--
-- Table structure for table `api_arrangement`
--

CREATE TABLE `api_arrangement` (
  `id` int(11) NOT NULL,
  `date` date DEFAULT NULL,
  `service_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `status` enum('verified','not verified','rejected','finished') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_arrangement`
--

INSERT INTO `api_arrangement` (`id`, `date`, `service_id`, `user_id`, `status`) VALUES
(1, '1996-12-21', 1, 1, 'verified'),
(2, '1974-03-25', 2, 2, 'finished'),
(3, '2017-05-14', 3, 3, 'rejected'),
(4, '1992-01-21', 4, 1, 'verified'),
(5, '1982-08-16', 5, 2, 'not verified'),
(6, '1980-10-24', 6, 3, 'verified'),
(7, '1982-06-07', 7, 1, 'verified'),
(8, '1981-06-22', 8, 2, 'not verified'),
(9, '1970-05-29', 1, 3, 'rejected'),
(10, '1986-01-02', 2, 1, 'not verified');

-- --------------------------------------------------------

--
-- Table structure for table `api_detailrequirements`
--

CREATE TABLE `api_detailrequirements` (
  `id` int(11) NOT NULL,
  `requirement_id` int(11) NOT NULL,
  `service_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_detailrequirements`
--

INSERT INTO `api_detailrequirements` (`id`, `requirement_id`, `service_id`) VALUES
(1, 1, 1),
(2, 2, 1),
(5, 2, 2),
(7, 4, 2);

-- --------------------------------------------------------

--
-- Table structure for table `api_filerequirement`
--

CREATE TABLE `api_filerequirement` (
  `id` int(11) NOT NULL,
  `url_file` varchar(300) NOT NULL,
  `arrangement_id` int(11) NOT NULL,
  `requirement_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_filerequirement`
--

INSERT INTO `api_filerequirement` (`id`, `url_file`, `arrangement_id`, `requirement_id`) VALUES
(1, '/home/infraspinatus/Python/test/1.jpg', 2, 1),
(2, '/home/infraspinatus/Python/test/2.jpg', 2, 2),
(3, '/home/infraspinatus/Python/test/3.jpg', 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `api_servicerequirements`
--

CREATE TABLE `api_servicerequirements` (
  `id` int(11) NOT NULL,
  `name_requirement` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_servicerequirements`
--

INSERT INTO `api_servicerequirements` (`id`, `name_requirement`) VALUES
(1, 'Foto KTP'),
(2, 'Foto Kartu Keluarga'),
(3, 'Foto Surat Pengantar RT'),
(4, 'Foto Diri');

-- --------------------------------------------------------

--
-- Table structure for table `api_services`
--

CREATE TABLE `api_services` (
  `id` int(11) NOT NULL,
  `type_service` varchar(100) NOT NULL,
  `status` enum('active','inactive') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_services`
--

INSERT INTO `api_services` (`id`, `type_service`, `status`) VALUES
(1, 'Surat Keterangan Pembuatan KTP bagi pemula', 'active'),
(2, 'Surat Keterangan Kematian', 'active'),
(3, 'Surat Keterangan Pindah', 'active'),
(4, 'Surat Keterangan Datang', 'active'),
(5, 'Surat Perubahan KK', 'active'),
(6, 'Surat Pengantar SKCK', 'active'),
(7, 'Surat Pengantar Nikah', 'active'),
(8, 'Surat Keterangan Lahir', 'active'),
(9, 'Surat Pelayanan Terpadu', 'active'),
(10, 'Surat Ijin Kegiatan', 'active'),
(11, 'Surat Perjalanan', 'active'),
(12, 'Surat Jalan Jalan', 'inactive'),
(13, 'Surat Tanah', 'inactive');

-- --------------------------------------------------------

--
-- Table structure for table `api_user`
--

CREATE TABLE `api_user` (
  `id` int(11) NOT NULL,
  `telegram_id` varchar(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `url_ktp_photo` varchar(300) NOT NULL,
  `url_self_photo` varchar(300) NOT NULL,
  `status` enum('verified','unverified','rejected') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `api_user`
--

INSERT INTO `api_user` (`id`, `telegram_id`, `name`, `url_ktp_photo`, `url_self_photo`, `status`) VALUES
(1, '7673682', 'Wisnu', '/home/infraspinatus/Python/test/1.jpg', '/home/infraspinatus/Python/test/2.jpg', 'unverified'),
(2, '12345', 'Wisnu', 'hahah', 'hahaha', 'rejected'),
(3, '0987867', 'wisnu', 'www.url.com', 'www.url.vom', 'verified'),
(6, '602478502', 'Wisnu Dewa', '/home/infraspinatus/Python/SmartLurahBot/602478502/registration/ktp_1589580443_7783172.jpg', '/home/infraspinatus/Python/SmartLurahBot/602478502/registration/self_1589580451_1493423.jpg', 'verified');

-- --------------------------------------------------------

--
-- Table structure for table `auth_group`
--

CREATE TABLE `auth_group` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_group_permissions`
--

CREATE TABLE `auth_group_permissions` (
  `id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_permission`
--

CREATE TABLE `auth_permission` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content_type_id` int(11) NOT NULL,
  `codename` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_permission`
--

INSERT INTO `auth_permission` (`id`, `name`, `content_type_id`, `codename`) VALUES
(1, 'Can add log entry', 1, 'add_logentry'),
(2, 'Can change log entry', 1, 'change_logentry'),
(3, 'Can delete log entry', 1, 'delete_logentry'),
(4, 'Can view log entry', 1, 'view_logentry'),
(5, 'Can add permission', 2, 'add_permission'),
(6, 'Can change permission', 2, 'change_permission'),
(7, 'Can delete permission', 2, 'delete_permission'),
(8, 'Can view permission', 2, 'view_permission'),
(9, 'Can add group', 3, 'add_group'),
(10, 'Can change group', 3, 'change_group'),
(11, 'Can delete group', 3, 'delete_group'),
(12, 'Can view group', 3, 'view_group'),
(13, 'Can add user', 4, 'add_user'),
(14, 'Can change user', 4, 'change_user'),
(15, 'Can delete user', 4, 'delete_user'),
(16, 'Can view user', 4, 'view_user'),
(17, 'Can add content type', 5, 'add_contenttype'),
(18, 'Can change content type', 5, 'change_contenttype'),
(19, 'Can delete content type', 5, 'delete_contenttype'),
(20, 'Can view content type', 5, 'view_contenttype'),
(21, 'Can add session', 6, 'add_session'),
(22, 'Can change session', 6, 'change_session'),
(23, 'Can delete session', 6, 'delete_session'),
(24, 'Can view session', 6, 'view_session'),
(25, 'Can add detail requirements', 7, 'add_detailrequirements'),
(26, 'Can change detail requirements', 7, 'change_detailrequirements'),
(27, 'Can delete detail requirements', 7, 'delete_detailrequirements'),
(28, 'Can view detail requirements', 7, 'view_detailrequirements'),
(29, 'Can add user', 8, 'add_user'),
(30, 'Can change user', 8, 'change_user'),
(31, 'Can delete user', 8, 'delete_user'),
(32, 'Can view user', 8, 'view_user'),
(33, 'Can add service requirements', 9, 'add_servicerequirements'),
(34, 'Can change service requirements', 9, 'change_servicerequirements'),
(35, 'Can delete service requirements', 9, 'delete_servicerequirements'),
(36, 'Can view service requirements', 9, 'view_servicerequirements'),
(37, 'Can add services', 10, 'add_services'),
(38, 'Can change services', 10, 'change_services'),
(39, 'Can delete services', 10, 'delete_services'),
(40, 'Can view services', 10, 'view_services'),
(41, 'Can add file requirement', 11, 'add_filerequirement'),
(42, 'Can change file requirement', 11, 'change_filerequirement'),
(43, 'Can delete file requirement', 11, 'delete_filerequirement'),
(44, 'Can view file requirement', 11, 'view_filerequirement'),
(45, 'Can add arrangement', 12, 'add_arrangement'),
(46, 'Can change arrangement', 12, 'change_arrangement'),
(47, 'Can delete arrangement', 12, 'delete_arrangement'),
(48, 'Can view arrangement', 12, 'view_arrangement');

-- --------------------------------------------------------

--
-- Table structure for table `auth_user`
--

CREATE TABLE `auth_user` (
  `id` int(11) NOT NULL,
  `password` varchar(128) NOT NULL,
  `last_login` datetime(6) DEFAULT NULL,
  `is_superuser` tinyint(1) NOT NULL,
  `username` varchar(150) NOT NULL,
  `first_name` varchar(30) NOT NULL,
  `last_name` varchar(150) NOT NULL,
  `email` varchar(254) NOT NULL,
  `is_staff` tinyint(1) NOT NULL,
  `is_active` tinyint(1) NOT NULL,
  `date_joined` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `auth_user`
--

INSERT INTO `auth_user` (`id`, `password`, `last_login`, `is_superuser`, `username`, `first_name`, `last_name`, `email`, `is_staff`, `is_active`, `date_joined`) VALUES
(1, 'pbkdf2_sha256$180000$piUjfsMfd87f$ofye4puRmT1WxS22At0Gm/NMIZ25hQZgFV1v8EMj1Tc=', '2020-03-25 09:19:38.000000', 1, 'infraspinatus', 'Wisnu', 'Dewa', 'wisnuudewa5@gmail.com', 1, 1, '2020-03-17 09:41:42.000000');

-- --------------------------------------------------------

--
-- Table structure for table `auth_user_groups`
--

CREATE TABLE `auth_user_groups` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `group_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `auth_user_user_permissions`
--

CREATE TABLE `auth_user_user_permissions` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `django_admin_log`
--

CREATE TABLE `django_admin_log` (
  `id` int(11) NOT NULL,
  `action_time` datetime(6) NOT NULL,
  `object_id` longtext,
  `object_repr` varchar(200) NOT NULL,
  `action_flag` smallint(5) UNSIGNED NOT NULL,
  `change_message` longtext NOT NULL,
  `content_type_id` int(11) DEFAULT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_admin_log`
--

INSERT INTO `django_admin_log` (`id`, `action_time`, `object_id`, `object_repr`, `action_flag`, `change_message`, `content_type_id`, `user_id`) VALUES
(1, '2020-03-17 11:02:11.664637', '1', 'Wisnu', 1, '[{\"added\": {}}]', 8, 1),
(2, '2020-03-18 06:55:22.718517', '1', 'Surat Keterangan Pembuatan KTP bagi pemula', 1, '[{\"added\": {}}]', 10, 1),
(3, '2020-03-18 06:55:32.053005', '2', 'Surat Keterangan Kematian', 1, '[{\"added\": {}}]', 10, 1),
(4, '2020-03-18 06:55:43.370669', '3', 'Surat Keterangan Pindah', 1, '[{\"added\": {}}]', 10, 1),
(5, '2020-03-18 06:55:52.107290', '4', 'Surat Keterangan Datang', 1, '[{\"added\": {}}]', 10, 1),
(6, '2020-03-18 06:56:06.372659', '5', 'Surat Perubahan KK', 1, '[{\"added\": {}}]', 10, 1),
(7, '2020-03-18 06:56:13.440838', '6', 'Surat Pengantar SKCK', 1, '[{\"added\": {}}]', 10, 1),
(8, '2020-03-18 06:56:20.048076', '7', 'Surat Pengantar Nikah', 1, '[{\"added\": {}}]', 10, 1),
(9, '2020-03-18 06:56:26.926021', '8', 'Surat Keterangan Lahir', 1, '[{\"added\": {}}]', 10, 1),
(10, '2020-03-25 12:17:14.042966', '1', 'infraspinatus', 2, '[{\"changed\": {\"fields\": [\"First name\", \"Last name\"]}}]', 4, 1);

-- --------------------------------------------------------

--
-- Table structure for table `django_content_type`
--

CREATE TABLE `django_content_type` (
  `id` int(11) NOT NULL,
  `app_label` varchar(100) NOT NULL,
  `model` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_content_type`
--

INSERT INTO `django_content_type` (`id`, `app_label`, `model`) VALUES
(1, 'admin', 'logentry'),
(12, 'api', 'arrangement'),
(7, 'api', 'detailrequirements'),
(11, 'api', 'filerequirement'),
(9, 'api', 'servicerequirements'),
(10, 'api', 'services'),
(8, 'api', 'user'),
(3, 'auth', 'group'),
(2, 'auth', 'permission'),
(4, 'auth', 'user'),
(5, 'contenttypes', 'contenttype'),
(6, 'sessions', 'session');

-- --------------------------------------------------------

--
-- Table structure for table `django_migrations`
--

CREATE TABLE `django_migrations` (
  `id` int(11) NOT NULL,
  `app` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `applied` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_migrations`
--

INSERT INTO `django_migrations` (`id`, `app`, `name`, `applied`) VALUES
(1, 'contenttypes', '0001_initial', '2020-03-17 09:31:17.956628'),
(2, 'auth', '0001_initial', '2020-03-17 09:31:19.981377'),
(3, 'admin', '0001_initial', '2020-03-17 09:31:33.554318'),
(4, 'admin', '0002_logentry_remove_auto_add', '2020-03-17 09:31:37.562158'),
(5, 'admin', '0003_logentry_add_action_flag_choices', '2020-03-17 09:31:37.640695'),
(6, 'contenttypes', '0002_remove_content_type_name', '2020-03-17 09:31:39.800588'),
(7, 'auth', '0002_alter_permission_name_max_length', '2020-03-17 09:31:40.030002'),
(8, 'auth', '0003_alter_user_email_max_length', '2020-03-17 09:31:40.272007'),
(9, 'auth', '0004_alter_user_username_opts', '2020-03-17 09:31:40.365565'),
(10, 'auth', '0005_alter_user_last_login_null', '2020-03-17 09:31:41.777248'),
(11, 'auth', '0006_require_contenttypes_0002', '2020-03-17 09:31:41.849133'),
(12, 'auth', '0007_alter_validators_add_error_messages', '2020-03-17 09:31:41.989128'),
(13, 'auth', '0008_alter_user_username_max_length', '2020-03-17 09:31:42.281522'),
(14, 'auth', '0009_alter_user_last_name_max_length', '2020-03-17 09:31:42.569369'),
(15, 'auth', '0010_alter_group_name_max_length', '2020-03-17 09:31:42.841481'),
(16, 'auth', '0011_update_proxy_permissions', '2020-03-17 09:31:42.958983'),
(17, 'sessions', '0001_initial', '2020-03-17 09:31:43.986790'),
(18, 'api', '0001_initial', '2020-03-17 09:34:48.413757'),
(19, 'api', '0002_auto_20200317_0936', '2020-03-17 09:36:50.408047'),
(20, 'api', '0003_auto_20200321_0402', '2020-03-21 04:02:57.268578'),
(21, 'api', '0004_arrangement_rejected', '2020-05-09 14:04:21.112548'),
(22, 'api', '0005_auto_20200509_1614', '2020-05-09 16:14:14.685364'),
(23, 'api', '0006_auto_20200510_0748', '2020-05-10 07:48:28.904462'),
(24, 'api', '0007_services_status', '2020-05-12 04:24:55.067915');

-- --------------------------------------------------------

--
-- Table structure for table `django_session`
--

CREATE TABLE `django_session` (
  `session_key` varchar(40) NOT NULL,
  `session_data` longtext NOT NULL,
  `expire_date` datetime(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `django_session`
--

INSERT INTO `django_session` (`session_key`, `session_data`, `expire_date`) VALUES
('yjgw4ndqvzso50uw9afwlxkb5lgp9ilr', 'ZjVmNjJlMWRmYzQ5OWYxOGQ0ZTFhOGJlMGE3YjExYWI3YzcyZTI2OTp7Il9hdXRoX3VzZXJfaWQiOiIxIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiZGphbmdvLmNvbnRyaWIuYXV0aC5iYWNrZW5kcy5Nb2RlbEJhY2tlbmQiLCJfYXV0aF91c2VyX2hhc2giOiI3M2ZjYWRjMWFkMzg5MWZmYmU2ODc5MWU0NWVjMjM3M2I5ODIxMzhkIn0=', '2020-04-08 09:19:38.843661');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `api_arrangement`
--
ALTER TABLE `api_arrangement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `api_arrangement_service_id_d558f71a_fk_api_services_id` (`service_id`),
  ADD KEY `api_arrangement_user_id_504ec656_fk_api_user_id` (`user_id`);

--
-- Indexes for table `api_detailrequirements`
--
ALTER TABLE `api_detailrequirements`
  ADD PRIMARY KEY (`id`),
  ADD KEY `api_detailrequiremen_requirement_id_195b6bb7_fk_api_servi` (`requirement_id`),
  ADD KEY `api_detailrequirements_service_id_4e351106_fk_api_services_id` (`service_id`);

--
-- Indexes for table `api_filerequirement`
--
ALTER TABLE `api_filerequirement`
  ADD PRIMARY KEY (`id`),
  ADD KEY `api_filerequirement_arrangement_id_102041f7_fk_api_arran` (`arrangement_id`),
  ADD KEY `api_filerequirement_requirement_id_b03f2418_fk_api_servi` (`requirement_id`);

--
-- Indexes for table `api_servicerequirements`
--
ALTER TABLE `api_servicerequirements`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `api_services`
--
ALTER TABLE `api_services`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `api_user`
--
ALTER TABLE `api_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `auth_group`
--
ALTER TABLE `auth_group`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- Indexes for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_group_permissions_group_id_permission_id_0cd325b0_uniq` (`group_id`,`permission_id`),
  ADD KEY `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` (`permission_id`);

--
-- Indexes for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_permission_content_type_id_codename_01ab375a_uniq` (`content_type_id`,`codename`);

--
-- Indexes for table `auth_user`
--
ALTER TABLE `auth_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_groups_user_id_group_id_94350c0c_uniq` (`user_id`,`group_id`),
  ADD KEY `auth_user_groups_group_id_97559544_fk_auth_group_id` (`group_id`);

--
-- Indexes for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `auth_user_user_permissions_user_id_permission_id_14a6b632_uniq` (`user_id`,`permission_id`),
  ADD KEY `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` (`permission_id`);

--
-- Indexes for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD PRIMARY KEY (`id`),
  ADD KEY `django_admin_log_content_type_id_c4bce8eb_fk_django_co` (`content_type_id`),
  ADD KEY `django_admin_log_user_id_c564eba6_fk_auth_user_id` (`user_id`);

--
-- Indexes for table `django_content_type`
--
ALTER TABLE `django_content_type`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `django_content_type_app_label_model_76bd3d3b_uniq` (`app_label`,`model`);

--
-- Indexes for table `django_migrations`
--
ALTER TABLE `django_migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `django_session`
--
ALTER TABLE `django_session`
  ADD PRIMARY KEY (`session_key`),
  ADD KEY `django_session_expire_date_a5c62663` (`expire_date`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `api_arrangement`
--
ALTER TABLE `api_arrangement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `api_detailrequirements`
--
ALTER TABLE `api_detailrequirements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `api_filerequirement`
--
ALTER TABLE `api_filerequirement`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `api_servicerequirements`
--
ALTER TABLE `api_servicerequirements`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `api_services`
--
ALTER TABLE `api_services`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `api_user`
--
ALTER TABLE `api_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `auth_group`
--
ALTER TABLE `auth_group`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_permission`
--
ALTER TABLE `auth_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT for table `auth_user`
--
ALTER TABLE `auth_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `django_content_type`
--
ALTER TABLE `django_content_type`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `django_migrations`
--
ALTER TABLE `django_migrations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `api_arrangement`
--
ALTER TABLE `api_arrangement`
  ADD CONSTRAINT `api_arrangement_service_id_d558f71a_fk_api_services_id` FOREIGN KEY (`service_id`) REFERENCES `api_services` (`id`),
  ADD CONSTRAINT `api_arrangement_user_id_504ec656_fk_api_user_id` FOREIGN KEY (`user_id`) REFERENCES `api_user` (`id`);

--
-- Constraints for table `api_detailrequirements`
--
ALTER TABLE `api_detailrequirements`
  ADD CONSTRAINT `api_detailrequiremen_requirement_id_195b6bb7_fk_api_servi` FOREIGN KEY (`requirement_id`) REFERENCES `api_servicerequirements` (`id`),
  ADD CONSTRAINT `api_detailrequirements_service_id_4e351106_fk_api_services_id` FOREIGN KEY (`service_id`) REFERENCES `api_services` (`id`);

--
-- Constraints for table `api_filerequirement`
--
ALTER TABLE `api_filerequirement`
  ADD CONSTRAINT `api_filerequirement_arrangement_id_102041f7_fk_api_arran` FOREIGN KEY (`arrangement_id`) REFERENCES `api_arrangement` (`id`),
  ADD CONSTRAINT `api_filerequirement_requirement_id_b03f2418_fk_api_servi` FOREIGN KEY (`requirement_id`) REFERENCES `api_servicerequirements` (`id`);

--
-- Constraints for table `auth_group_permissions`
--
ALTER TABLE `auth_group_permissions`
  ADD CONSTRAINT `auth_group_permissio_permission_id_84c5c92e_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_group_permissions_group_id_b120cbf9_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`);

--
-- Constraints for table `auth_permission`
--
ALTER TABLE `auth_permission`
  ADD CONSTRAINT `auth_permission_content_type_id_2f476e4b_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`);

--
-- Constraints for table `auth_user_groups`
--
ALTER TABLE `auth_user_groups`
  ADD CONSTRAINT `auth_user_groups_group_id_97559544_fk_auth_group_id` FOREIGN KEY (`group_id`) REFERENCES `auth_group` (`id`),
  ADD CONSTRAINT `auth_user_groups_user_id_6a12ed8b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `auth_user_user_permissions`
--
ALTER TABLE `auth_user_user_permissions`
  ADD CONSTRAINT `auth_user_user_permi_permission_id_1fbb5f2c_fk_auth_perm` FOREIGN KEY (`permission_id`) REFERENCES `auth_permission` (`id`),
  ADD CONSTRAINT `auth_user_user_permissions_user_id_a95ead1b_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

--
-- Constraints for table `django_admin_log`
--
ALTER TABLE `django_admin_log`
  ADD CONSTRAINT `django_admin_log_content_type_id_c4bce8eb_fk_django_co` FOREIGN KEY (`content_type_id`) REFERENCES `django_content_type` (`id`),
  ADD CONSTRAINT `django_admin_log_user_id_c564eba6_fk_auth_user_id` FOREIGN KEY (`user_id`) REFERENCES `auth_user` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
