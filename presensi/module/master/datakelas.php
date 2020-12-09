<?php
    include '../../config/connection.php';

    if($_SERVER['REQUEST_METHOD'] == 'GET') {

        $total_kelas = "SELECT COUNT(*) 'total_kelas' FROM tbl_kelas";
        $exe_totalkelas = mysqli_query($_AUTH, $total_kelas);
        $get_totalkelas = mysqli_fetch_assoc($exe_totalkelas);

        $list_kelas = "SELECT kode_kelas, kelas, deskripsi FROM tbl_kelas";
        $exe_listkelas = mysqli_query($_AUTH, $list_kelas);

        $response["message"] = trim("Data kelas berhasil ditampilkan.");
        $response["code"] = 200;
        $response["status"] = true;
        $response["total_datakelas"] = $get_totalkelas['total_kelas'];
        $response["data_kelas"] = array();

        while ($row = mysqli_fetch_array($exe_listkelas)) {
            $data = array();
            $data["kode_kelas"] = $row["kode_kelas"];
            $data["kelas"] = $row["kelas"];
            $data["deskripsi"] = $row["deskripsi"];
            array_push($response["data_kelas"], $data);
        }
        echo json_encode($response);
    } else {
        $_response["message"] = trim("Oops! Sory, Request API ini membutuhkan parameter!.");
        $_response["code"] = 400;
        $_response["status"] = false;

        echo json_encode($response);
    }

?>