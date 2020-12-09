<?php
    include '../../config/connection.php';

    if($_SERVER['REQUEST_METHOD'] == 'GET') {

        $total_jenjang = "SELECT COUNT(*) 'total_jenjang' FROM tbl_jenjang";
        $exe_totaljenjang = mysqli_query($_AUTH, $total_jenjang);
        $get_totaljenjang = mysqli_fetch_assoc($exe_totaljenjang);

        $list_jenjang = "SELECT jenjang_pendidikan, instansi, terpadu, detail FROM tbl_jenjang";
        $exe_listjenjang = mysqli_query($_AUTH, $list_jenjang);

        $response["message"] = trim("Data jenjang berhasil ditampilkan.");
        $response["code"] = 200;
        $response["status"] = true;
        $response["total_datajenjang"] = $get_totaljenjang['total_jenjang'];
        $response["data_jenjang"] = array();

        while ($row = mysqli_fetch_array($exe_listjenjang)) {
            $data = array();
            $data["jenjang_pendidikan"] = $row["jenjang_pendidikan"];
            $data["instansi"] = $row["instansi"];
            $data["terpadu"] = $row["terpadu"];
            $data["detail"] = $row["detail"];
            array_push($response["data_jenjang"], $data);
        }
        echo json_encode($response);
    } else {
        $_response["message"] = trim("Oops! Sory, Request API ini membutuhkan parameter!.");
        $_response["code"] = 400;
        $_response["status"] = false;

        echo json_encode($response);
    }

?>