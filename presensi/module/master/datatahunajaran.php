<?php
    include '../../config/connection.php';

    if($_SERVER['REQUEST_METHOD'] == 'GET') {

        $total_angkatan = "SELECT COUNT(*) 'total_angkatan' FROM tbl_tahunajaran";
        $exe_totalangkatan = mysqli_query($_AUTH, $total_angkatan);
        $get_totalangkatan = mysqli_fetch_assoc($exe_totalangkatan);

        $list_tahunajaran = "SELECT kode_tahun_ajaran, tahunajaran, identifikasi FROM tbl_tahunajaran";
        $exe_list_tahunajaran = mysqli_query($_AUTH, $list_tahunajaran);

        $response["message"] = trim("Data tahun ajaran berhasil ditampilkan.");
        $response["code"] = 200;
        $response["status"] = true;
        $response["total_data"] = $get_totalangkatan['total_angkatan'];
        $response["data_tahunajaran"] = array();

        while ($row = mysqli_fetch_array($exe_list_tahunajaran)) {
            $data = array();
            $data["kode_tahun_ajaran"] = $row["kode_tahun_ajaran"];
            $data["tahunajaran"] = $row["tahunajaran"];
            $data["identifikasi"] = $row["identifikasi"];
            array_push($response["data_tahunajaran"], $data);
        }
        echo json_encode($response);
    } else {
        $_response["message"] = trim("Oops! Sory, Request API ini membutuhkan parameter!.");
        $_response["code"] = 400;
        $_response["status"] = false;

        echo json_encode($response);
    }

?>