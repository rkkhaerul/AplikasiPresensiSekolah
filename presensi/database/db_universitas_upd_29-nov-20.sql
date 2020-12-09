-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 29, 2020 at 10:34 AM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_universitas`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_datasiswa`
--

CREATE TABLE `tbl_datasiswa` (
  `NIS` varchar(60) NOT NULL,
  `nama_siswa` text DEFAULT NULL,
  `ditambahkan` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_datasiswa`
--

INSERT INTO `tbl_datasiswa` (`NIS`, `nama_siswa`, `ditambahkan`) VALUES
('17180410002', 'Fatchan Muhammad Hakim', '2020-11-03 04:15:46'),
('17180410003', 'Muhammad Nur Irsaddul Ma\'ruf Brk', '2020-11-07 14:01:55'),
('17180410004', 'Muhamad Ridwan Alazzam', '2020-11-03 04:15:48'),
('17180410005', 'Raga Murtadha Muthahari', '2020-11-03 04:15:50'),
('17180410006', 'Ramadhani Bagus Satrio Wibowo', '2020-11-03 04:15:51'),
('17180410007', 'Ridho Fitra Palasa', '2020-11-03 04:15:56'),
('18190410001', 'Abdullah Al Hasan Mustafa', '2020-11-03 04:16:00'),
('18190410003', 'Fauzi Albir Maulana', '2020-11-03 04:16:02'),
('18190410004', 'Gilar Wahibul Azhar', '2020-11-03 04:16:04'),
('18190410005', 'M. Fauzul Adhim', '2020-11-03 04:16:07'),
('18190410006', 'M. Mursyid Ibadurrahman', '2020-11-03 04:16:11'),
('18190410007', 'M. Rizki Khaerul Muhaemin', '2020-11-03 04:16:14'),
('18190410008', 'Muhammad Hilmi', '2020-11-03 04:16:15'),
('18190410009', 'Nafizul Izzata Insan', '2020-11-03 04:16:17'),
('18190410010', 'Muhammad Dawwam', '2020-11-03 04:16:18'),
('19200410001', 'Muhammad Hikaru Naufal Zahraan', '2020-11-03 04:16:24'),
('19200410002', 'Mochammad Putra Ravi Mauludy', '2020-11-03 04:16:27'),
('19200410003', 'Muhammad Azka Yasakha', '2020-11-03 04:16:33'),
('19200410004', 'Afif Amar', '2020-11-03 04:16:36'),
('19200410005', 'Hasan Assyauqi', '2020-11-03 04:16:37'),
('19200410006', 'Muhammad Takayuki Aqila Zahraan', '2020-11-03 04:16:38'),
('19200410007', 'Abdullah Azzam Rabbani', '2020-11-03 04:25:22'),
('20210410001', 'Abid Zhafran Arifain', '2020-11-07 00:43:37'),
('20210410002', 'Ahmad Khairul Zahid', '2020-11-07 00:43:49'),
('20210410003', 'Fakhrul Abdullah', '2020-11-07 00:42:06'),
('20210410004', 'Farhan Muhammad Iqbal', '2020-11-07 00:42:35'),
('20210410005', 'Ikhwanul Muslimin Siregar', '2020-11-07 00:43:32'),
('20210410006', 'Muhammad Ilham Zulfaishal', '2020-11-07 00:42:23'),
('20210410007', 'Muhammad Ridho Alfarrel', '2020-11-07 00:43:11'),
('20210410008', 'Raka Muhamad Al-Hafidz', '2020-11-07 00:43:25'),
('20210410009', 'Yoga Helmi Ilham Saputra', '2020-11-07 00:42:54'),
('20210410010', 'Muhammad Nadhif Islamy', '2020-11-07 00:41:29');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_jenjang`
--

CREATE TABLE `tbl_jenjang` (
  `kode_jenjang` varchar(50) NOT NULL,
  `jenjang_pendidikan` text DEFAULT NULL,
  `terpadu` text NOT NULL,
  `detail` text NOT NULL,
  `instansi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_jenjang`
--

INSERT INTO `tbl_jenjang` (`kode_jenjang`, `jenjang_pendidikan`, `terpadu`, `detail`, `instansi`) VALUES
('SD-1', 'SD', 'IT', 'Islam Terpadu', 'Rabbaanii Islamic School'),
('SMK-1', 'SMK', 'I', 'Islam', 'Rabbaanii Islamic School'),
('SMP-1', 'SMP', 'I', 'Islam', 'Rabbaanii Islamic School'),
('TK-1', 'TK', 'IT', 'Islam Terpadu', 'Rabbaanii Islamic School');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_jurusan`
--

CREATE TABLE `tbl_jurusan` (
  `kode_jurusan` varchar(50) NOT NULL,
  `jurusan` text DEFAULT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_jurusan`
--

INSERT INTO `tbl_jurusan` (`kode_jurusan`, `jurusan`, `deskripsi`) VALUES
('RPL', 'RPL', 'Rekayasa Perangkat Lunak'),
('TKJ', 'TKJ', 'Tekhnik Komputer & Jaringan');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_kelas`
--

CREATE TABLE `tbl_kelas` (
  `kode_kelas` varchar(60) NOT NULL,
  `kelas` text DEFAULT NULL,
  `deskripsi` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_kelas`
--

INSERT INTO `tbl_kelas` (`kode_kelas`, `kelas`, `deskripsi`) VALUES
('X', 'Sepuluh', 'Tingkat SMK Kelas Pertama'),
('XI', 'Sebelas', 'Tingkat SMK Kelas Kedua'),
('XII', 'Dua Belas', 'Tingkat SMK Kelas Akhir');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_rombelkelas`
--

CREATE TABLE `tbl_rombelkelas` (
  `kode_rombelkelas` varchar(50) NOT NULL,
  `kode_ta_baru` varchar(60) NOT NULL,
  `NIS` varchar(60) NOT NULL,
  `kode_jurusan` varchar(60) DEFAULT NULL,
  `jenis_anggota` text DEFAULT NULL,
  `menjabat` text DEFAULT NULL,
  `periode` text DEFAULT NULL,
  `details` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_rombelkelas`
--

INSERT INTO `tbl_rombelkelas` (`kode_rombelkelas`, `kode_ta_baru`, `NIS`, `kode_jurusan`, `jenis_anggota`, `menjabat`, `periode`, `details`) VALUES
('AGK-1-X-2017', 'X-1718-SMK-L-1', '17180410002', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2017'),
('AGK-1-X-2017', 'X-1718-SMK-L-1', '17180410003', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2017'),
('AGK-1-X-2017', 'X-1718-SMK-L-1', '17180410004', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2017'),
('AGK-1-X-2017', 'X-1718-SMK-L-1', '17180410005', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2017'),
('AGK-1-X-2017', 'X-1718-SMK-L-1', '17180410006', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2017'),
('AGK-1-X-2017', 'X-1718-SMK-L-1', '17180410007', 'TKJ', 'Ketua Kelas', 'Ketua', 'Pertama', 'Kelas X Angkatan 2017'),
('AGK-1-XI-2017', 'XI-1718-SMK-L-2', '17180410002', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2017'),
('AGK-1-XI-2017', 'XI-1718-SMK-L-2', '17180410003', 'TKJ', 'Ketua Kelas', 'Ketua', 'Kedua', 'Kelas XI Angkatan 2017'),
('AGK-1-XI-2017', 'XI-1718-SMK-L-2', '17180410004', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2017'),
('AGK-1-XI-2017', 'XI-1718-SMK-L-2', '17180410005', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2017'),
('AGK-1-XI-2017', 'XI-1718-SMK-L-2', '17180410006', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2017'),
('AGK-1-XI-2017', 'XI-1718-SMK-L-2', '17180410007', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2017'),
('AGK-1-XII-2017', 'XII-1718-SMK-L-3', '17180410002', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2017'),
('AGK-1-XII-2017', 'XII-1718-SMK-L-3', '17180410003', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2017'),
('AGK-1-XII-2017', 'XII-1718-SMK-L-3', '17180410004', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2017'),
('AGK-1-XII-2017', 'XII-1718-SMK-L-3', '17180410005', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2017'),
('AGK-1-XII-2017', 'XII-1718-SMK-L-3', '17180410006', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2017'),
('AGK-1-XII-2017', 'XII-1718-SMK-L-3', '17180410007', 'TKJ', 'Ketua Kelas', 'Ketua', 'Ketiga', 'Kelas XII Angkatan 2017'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410001', 'RPL', 'Ketua Kelas', 'Ketua', 'Pertama', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410003', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410004', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410005', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410006', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410007', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410008', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410009', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-X-2018', 'X-1819-SMK-L-1', '18190410010', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas X Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410001', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410003', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410004', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410005', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410006', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410007', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410008', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410009', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XI Angkatan 2018'),
('AGK-2-XI-2018', 'XI-1920-SMK-L-2', '18190410010', 'TKJ', 'Ketua Kelas', 'Ketua', 'Kedua', 'Kelas XI Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410001', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410003', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410004', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410005', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410006', 'RPL', 'Ketua Kelas', 'Ketua', 'Ketiga', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410007', 'RPL', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410008', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410009', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018'),
('AGK-2-XII-2018', 'XII-1819-SMK-L-3', '18190410010', 'TKJ', 'Anggota Kelas', 'Anggota', '-', 'Kelas XII Angkatan 2018');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tahunajaran`
--

CREATE TABLE `tbl_tahunajaran` (
  `kode_tahun_ajaran` varchar(50) NOT NULL,
  `tahunajaran` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_tahunajaran`
--

INSERT INTO `tbl_tahunajaran` (`kode_tahun_ajaran`, `tahunajaran`) VALUES
('TA1718', '2017/2018'),
('TA1819', '2018/2019'),
('TA1920', '2019/2020'),
('TA2021', '2020/2021');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_tahunajaran_baru`
--

CREATE TABLE `tbl_tahunajaran_baru` (
  `kode_ta_baru` varchar(60) NOT NULL,
  `angkatan_tahun` text NOT NULL,
  `kode_guru` varchar(50) NOT NULL,
  `kode_tahun_ajaran` varchar(50) DEFAULT NULL,
  `kode_jenjang` varchar(50) NOT NULL,
  `kode_kelas` varchar(60) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_tahunajaran_baru`
--

INSERT INTO `tbl_tahunajaran_baru` (`kode_ta_baru`, `angkatan_tahun`, `kode_guru`, `kode_tahun_ajaran`, `kode_jenjang`, `kode_kelas`) VALUES
('X-1718-SMK-L-1', '2017/2018', 'Asep Septiadi, S.Kom.', 'TA1718', 'SMK-1', 'X'),
('X-1819-SMK-L-1', '2018/2019', 'Yogi Suprayogi, A.Md.', 'TA1819', 'SMK-1', 'X'),
('XI-1718-SMK-L-2', '2017/2018', 'Slamet Nurhadi, S.Kom.', 'TA1819', 'SMK-1', 'XI'),
('XI-1920-SMK-L-2', '2018/2019', 'Yogi Suprayogi, A.Md.', 'TA1920', 'SMK-1', 'XI'),
('XII-1718-SMK-L-3', '2017/2018', 'Asep Septiadi, S.Kom.', 'TA1920', 'SMK-1', 'XII'),
('XII-1819-SMK-L-3', '2018/2019', 'Nur Aminuddin, S.T.', 'TA2021', 'SMK-1', 'XII');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_datasiswa`
--
ALTER TABLE `tbl_datasiswa`
  ADD PRIMARY KEY (`NIS`);

--
-- Indexes for table `tbl_jenjang`
--
ALTER TABLE `tbl_jenjang`
  ADD PRIMARY KEY (`kode_jenjang`);

--
-- Indexes for table `tbl_jurusan`
--
ALTER TABLE `tbl_jurusan`
  ADD PRIMARY KEY (`kode_jurusan`);

--
-- Indexes for table `tbl_kelas`
--
ALTER TABLE `tbl_kelas`
  ADD PRIMARY KEY (`kode_kelas`);

--
-- Indexes for table `tbl_rombelkelas`
--
ALTER TABLE `tbl_rombelkelas`
  ADD PRIMARY KEY (`kode_rombelkelas`,`kode_ta_baru`,`NIS`),
  ADD KEY `fkrombel_angkatan` (`kode_ta_baru`),
  ADD KEY `fkrombel_siswa` (`NIS`),
  ADD KEY `fkrombel_jurusan` (`kode_jurusan`);

--
-- Indexes for table `tbl_tahunajaran`
--
ALTER TABLE `tbl_tahunajaran`
  ADD PRIMARY KEY (`kode_tahun_ajaran`);

--
-- Indexes for table `tbl_tahunajaran_baru`
--
ALTER TABLE `tbl_tahunajaran_baru`
  ADD PRIMARY KEY (`kode_ta_baru`),
  ADD KEY `fkvkelas_ta` (`kode_tahun_ajaran`),
  ADD KEY `fkvkelas_jenjang` (`kode_jenjang`),
  ADD KEY `fkvkelas_kelas` (`kode_kelas`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tbl_rombelkelas`
--
ALTER TABLE `tbl_rombelkelas`
  ADD CONSTRAINT `fkrombel_angkatan` FOREIGN KEY (`kode_ta_baru`) REFERENCES `tbl_tahunajaran_baru` (`kode_ta_baru`),
  ADD CONSTRAINT `fkrombel_jurusan` FOREIGN KEY (`kode_jurusan`) REFERENCES `tbl_jurusan` (`kode_jurusan`),
  ADD CONSTRAINT `fkrombel_siswa` FOREIGN KEY (`NIS`) REFERENCES `tbl_datasiswa` (`NIS`);

--
-- Constraints for table `tbl_tahunajaran_baru`
--
ALTER TABLE `tbl_tahunajaran_baru`
  ADD CONSTRAINT `fkvkelas_jenjang` FOREIGN KEY (`kode_jenjang`) REFERENCES `tbl_jenjang` (`kode_jenjang`),
  ADD CONSTRAINT `fkvkelas_kelas` FOREIGN KEY (`kode_kelas`) REFERENCES `tbl_kelas` (`kode_kelas`),
  ADD CONSTRAINT `fkvkelas_ta` FOREIGN KEY (`kode_tahun_ajaran`) REFERENCES `tbl_tahunajaran` (`kode_tahun_ajaran`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
