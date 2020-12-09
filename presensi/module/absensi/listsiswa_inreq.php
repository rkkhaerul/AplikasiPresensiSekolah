<?php

    include '../../config/connection.php';

    if($_SERVER['REQUEST_METHOD'] == 'POST') {
        if(isset($_POST['tahunajaran']) && isset($_POST['kode_kelas']) && isset($_POST['kode_jurusan'])) {
            $kode_ta = $_POST['tahunajaran'];
            $kode_kelas = $_POST['kode_kelas'];
            $kode_jurusan = $_POST['kode_jurusan'];

            // // MODULE 1 : TOTAL SISWA [FILTER]
            $QUERY_TOTALSISWA = "
            SELECT COUNT(tbl_rombelkelas.NIS) 'total_siswa'

            FROM tbl_rombelkelas
            
            JOIN tbl_datasiswa ON tbl_datasiswa.NIS=tbl_rombelkelas.NIS
            JOIN tbl_tahunajaran_baru ON tbl_tahunajaran_baru.kode_ta_baru=tbl_rombelkelas.kode_ta_baru
            JOIN tbl_jurusan ON tbl_jurusan.kode_jurusan=tbl_rombelkelas.kode_jurusan
            JOIN tbl_kelas ON tbl_kelas.kode_kelas=tbl_tahunajaran_baru.kode_kelas
            JOIN tbl_tahunajaran ON tbl_tahunajaran.kode_tahun_ajaran= tbl_tahunajaran_baru.kode_tahun_ajaran
            
            WHERE tbl_tahunajaran.tahunajaran = '$kode_ta' AND tbl_tahunajaran_baru.kode_kelas = '$kode_kelas' AND tbl_jurusan.kode_jurusan = '$kode_jurusan'
            
            ORDER BY tbl_rombelkelas.NIS ASC";
            $execute_totalsiswa = mysqli_query($_AUTH, $QUERY_TOTALSISWA);
            $totalsiswa = mysqli_fetch_assoc($execute_totalsiswa);  
            
            // MODULE 2 : LIST SISWA [FILTER]
            $QUERY_LISTSISWA = "
            SELECT tbl_tahunajaran.tahunajaran 'siswa_teregister', tbl_rombelkelas.details 'keterangan', tbl_tahunajaran_baru.kode_guru 'wali_kelas', tbl_datasiswa.NIS, tbl_datasiswa.nama_siswa, tbl_rombelkelas.jenis_anggota 'jenis', tbl_rombelkelas.periode, tbl_kelas.kode_kelas 'kelas', tbl_kelas.kelas 'desk_kelas', tbl_rombelkelas.kode_jurusan 'jurusan', tbl_tahunajaran_baru.angkatan_tahun 'siswa_angkatan'

            FROM tbl_rombelkelas
            
            JOIN tbl_datasiswa ON tbl_datasiswa.NIS=tbl_rombelkelas.NIS
            JOIN tbl_tahunajaran_baru ON tbl_tahunajaran_baru.kode_ta_baru=tbl_rombelkelas.kode_ta_baru
            JOIN tbl_jurusan ON tbl_jurusan.kode_jurusan=tbl_rombelkelas.kode_jurusan
            JOIN tbl_kelas ON tbl_kelas.kode_kelas=tbl_tahunajaran_baru.kode_kelas
            JOIN tbl_tahunajaran ON tbl_tahunajaran.kode_tahun_ajaran= tbl_tahunajaran_baru.kode_tahun_ajaran
            
            WHERE tbl_tahunajaran.tahunajaran = '$kode_ta' AND tbl_tahunajaran_baru.kode_kelas = '$kode_kelas' AND tbl_jurusan.kode_jurusan = '$kode_jurusan'
            
            ORDER BY tbl_rombelkelas.NIS ASC";
            $response = array();
            $execute_listsiswa = mysqli_query($_AUTH, $QUERY_LISTSISWA);
            $listsiswa = mysqli_num_rows($execute_listsiswa);

            // MODULE 3 : PASSING SINGLE DATA [FILTER]
            $PASSDATA = "
            SELECT tbl_tahunajaran.tahunajaran 'rombel_teregister', tbl_tahunajaran_baru.kode_guru 'wali_kelas', tbl_datasiswa.nama_siswa, tbl_rombelkelas.jenis_anggota 'jenis', tbl_rombelkelas.periode, tbl_rombelkelas.kode_jurusan 'jurusan', tbl_jurusan.deskripsi, tbl_jenjang.jenjang_pendidikan, tbl_jenjang.terpadu, tbl_jenjang.detail, tbl_jenjang.instansi, tbl_tahunajaran_baru.kode_kelas, tbl_kelas.kelas

            FROM tbl_rombelkelas
            
            JOIN tbl_datasiswa ON tbl_datasiswa.NIS=tbl_rombelkelas.NIS
            JOIN tbl_tahunajaran_baru ON tbl_tahunajaran_baru.kode_ta_baru=tbl_rombelkelas.kode_ta_baru
            JOIN tbl_jurusan ON tbl_jurusan.kode_jurusan=tbl_rombelkelas.kode_jurusan
            JOIN tbl_kelas ON tbl_kelas.kode_kelas=tbl_tahunajaran_baru.kode_kelas
            JOIN tbl_tahunajaran ON tbl_tahunajaran.kode_tahun_ajaran= tbl_tahunajaran_baru.kode_tahun_ajaran
            JOIN tbl_jenjang ON tbl_jenjang.kode_jenjang=tbl_tahunajaran_baru.kode_jenjang
            
            WHERE tbl_tahunajaran.tahunajaran = '$kode_ta' AND tbl_tahunajaran_baru.kode_kelas = '$kode_kelas' AND tbl_rombelkelas.jenis_anggota = 'Ketua Kelas'
            
            ORDER BY tbl_rombelkelas.NIS ASC";
            $execute_passdata = mysqli_query($_AUTH, $PASSDATA);
            $passdata = mysqli_fetch_assoc($execute_passdata);

            if ($listsiswa > 0) {
                $response['rombel_terdaftar'] = $passdata['rombel_teregister'];
                $response['wali_kelas'] = $passdata['wali_kelas'];
                $response['ketua_kelas'] = $passdata['nama_siswa'];
                $response['periode'] = $passdata['periode'];
                $response['kejuruan'] = $passdata['jenjang_pendidikan'] . "-" . $passdata['terpadu'];
                $response['instansi'] = $passdata['instansi'];
                $response['pendidikan'] = $passdata['detail'];
                $response['jurusan'] = $passdata['jurusan'] . " (" .$passdata['deskripsi']. ")";
                $response['kelas'] = $passdata['kode_kelas'] . " (" .$passdata['kelas']. ")";
                $response["totalsiswa"] = $totalsiswa['total_siswa'];
                $response['datasiswa'] = array();
                $response["code"] = 201;
                $response["status"] = true;
                $response["message"] = trim("Data siswa TIDAK tersedia atau TIDAK teregister pada kelas " .$kode_kelas. " dan tahun ajaran " .$kode_ta);
    
                while ($row = mysqli_fetch_array($execute_listsiswa)) {
                    $datasiswalist = array();

                        $datasiswalist["NIS"] = $row["NIS"];
                        $datasiswalist["nama_siswa"] = $row["nama_siswa"];

                    array_push($response["datasiswa"], $datasiswalist);
                }
            } else {
                $response["code"] = 403;
                $response["status"] = false;
                $response["message"] = trim("Data siswa TIDAK tersedia atau TIDAK teregister pada kelas " .$kode_kelas. " dan tahun ajaran " .$kode_ta);
            }
        } else if(isset($_POST['tahunajaran']) && isset($_POST['kode_kelas'])) {
            $kode_ta = $_POST['tahunajaran'];
            $kode_kelas = $_POST['kode_kelas'];
            $kode_jurusan = $_POST['kode_jurusan'];

            // // MODULE 1 : TOTAL SISWA [FILTER]
            $QUERY_TOTALSISWA = "
            SELECT COUNT(tbl_rombelkelas.NIS) 'total_siswa'

            FROM tbl_rombelkelas
            
            JOIN tbl_datasiswa ON tbl_datasiswa.NIS=tbl_rombelkelas.NIS
            JOIN tbl_tahunajaran_baru ON tbl_tahunajaran_baru.kode_ta_baru=tbl_rombelkelas.kode_ta_baru
            JOIN tbl_jurusan ON tbl_jurusan.kode_jurusan=tbl_rombelkelas.kode_jurusan
            JOIN tbl_kelas ON tbl_kelas.kode_kelas=tbl_tahunajaran_baru.kode_kelas
            JOIN tbl_tahunajaran ON tbl_tahunajaran.kode_tahun_ajaran= tbl_tahunajaran_baru.kode_tahun_ajaran
            
            WHERE tbl_tahunajaran.tahunajaran = '$kode_ta' AND tbl_tahunajaran_baru.kode_kelas = '$kode_kelas'
            
            ORDER BY tbl_rombelkelas.NIS ASC";
            $execute_totalsiswa = mysqli_query($_AUTH, $QUERY_TOTALSISWA);
            $totalsiswa = mysqli_fetch_assoc($execute_totalsiswa);  
            
            // MODULE 2 : LIST SISWA [FILTER]
            $QUERY_LISTSISWA = "
            SELECT tbl_tahunajaran.tahunajaran 'siswa_teregister', tbl_rombelkelas.details 'keterangan', tbl_tahunajaran_baru.kode_guru 'wali_kelas', tbl_datasiswa.NIS, tbl_datasiswa.nama_siswa, tbl_rombelkelas.jenis_anggota 'jenis', tbl_rombelkelas.periode, tbl_kelas.kode_kelas 'kelas', tbl_kelas.kelas 'desk_kelas', tbl_rombelkelas.kode_jurusan 'jurusan', tbl_tahunajaran_baru.angkatan_tahun 'siswa_angkatan'

            FROM tbl_rombelkelas
            
            JOIN tbl_datasiswa ON tbl_datasiswa.NIS=tbl_rombelkelas.NIS
            JOIN tbl_tahunajaran_baru ON tbl_tahunajaran_baru.kode_ta_baru=tbl_rombelkelas.kode_ta_baru
            JOIN tbl_jurusan ON tbl_jurusan.kode_jurusan=tbl_rombelkelas.kode_jurusan
            JOIN tbl_kelas ON tbl_kelas.kode_kelas=tbl_tahunajaran_baru.kode_kelas
            JOIN tbl_tahunajaran ON tbl_tahunajaran.kode_tahun_ajaran= tbl_tahunajaran_baru.kode_tahun_ajaran
            
            WHERE tbl_tahunajaran.tahunajaran = '$kode_ta' AND tbl_tahunajaran_baru.kode_kelas = '$kode_kelas'
            
            ORDER BY tbl_rombelkelas.NIS ASC";
            $response = array();
            $execute_listsiswa = mysqli_query($_AUTH, $QUERY_LISTSISWA);
            $listsiswa = mysqli_num_rows($execute_listsiswa);

            // MODULE 3 : PASSING SINGLE DATA [FILTER]
            $PASSDATA = "
            SELECT tbl_tahunajaran.tahunajaran 'rombel_teregister', tbl_tahunajaran_baru.kode_guru 'wali_kelas', tbl_datasiswa.nama_siswa, tbl_rombelkelas.jenis_anggota 'jenis', tbl_rombelkelas.periode, tbl_rombelkelas.kode_jurusan 'jurusan', tbl_jurusan.deskripsi, tbl_jenjang.jenjang_pendidikan, tbl_jenjang.terpadu, tbl_jenjang.detail, tbl_jenjang.instansi, tbl_tahunajaran_baru.kode_kelas, tbl_kelas.kelas

            FROM tbl_rombelkelas
            
            JOIN tbl_datasiswa ON tbl_datasiswa.NIS=tbl_rombelkelas.NIS
            JOIN tbl_tahunajaran_baru ON tbl_tahunajaran_baru.kode_ta_baru=tbl_rombelkelas.kode_ta_baru
            JOIN tbl_jurusan ON tbl_jurusan.kode_jurusan=tbl_rombelkelas.kode_jurusan
            JOIN tbl_kelas ON tbl_kelas.kode_kelas=tbl_tahunajaran_baru.kode_kelas
            JOIN tbl_tahunajaran ON tbl_tahunajaran.kode_tahun_ajaran= tbl_tahunajaran_baru.kode_tahun_ajaran
            JOIN tbl_jenjang ON tbl_jenjang.kode_jenjang=tbl_tahunajaran_baru.kode_jenjang
            
            WHERE tbl_tahunajaran.tahunajaran = '$kode_ta' AND tbl_tahunajaran_baru.kode_kelas = '$kode_kelas' AND tbl_rombelkelas.jenis_anggota = 'Ketua Kelas'
            
            ORDER BY tbl_rombelkelas.NIS ASC";
            $execute_passdata = mysqli_query($_AUTH, $PASSDATA);
            $passdata = mysqli_fetch_assoc($execute_passdata);

            if ($listsiswa > 0) {
                $response['rombel_terdaftar'] = $passdata['rombel_teregister'];
                $response['wali_kelas'] = $passdata['wali_kelas'];
                $response['ketua_kelas'] = $passdata['nama_siswa'];
                $response['periode'] = $passdata['periode'];
                $response['kejuruan'] = $passdata['jenjang_pendidikan'] . "-" . $passdata['terpadu'];
                $response['instansi'] = $passdata['instansi'];
                $response['pendidikan'] = $passdata['detail'];
                $response['jurusan'] = $passdata['jurusan'] . " (" .$passdata['deskripsi']. ")";
                $response['kelas'] = $passdata['kode_kelas'] . " (" .$passdata['kelas']. ")";
                $response["totalsiswa"] = $totalsiswa['total_siswa'];
                $response['siswainrombel'] = array();
                $response["code"] = 201;
                $response["status"] = true;
                $response["message"] = trim("Data siswa TIDAK tersedia atau TIDAK teregister pada kelas " .$kode_kelas. " dan tahun ajaran " .$kode_ta);
    
                while ($row = mysqli_fetch_array($execute_listsiswa)) {
                    $datasiswalist = array();

                        $datasiswalist["NIS"] = $row["NIS"];
                        $datasiswalist["nama_siswa"] = $row["nama_siswa"];

                    array_push($response["siswainrombel"], $datasiswalist);
                }
            } else {
                $response["code"] = 403;
                $response["status"] = false;
                $response["message"] = trim("Data siswa TIDAK tersedia atau TIDAK teregister pada kelas " .$kode_kelas. " dan tahun ajaran " .$kode_ta);
            }
        } else {
            $response["code"] = 404;
            $response["status"] = false;
            $response["message"] = "Harap mengatur parameter untuk Request ini, dan silahkan sesuiakan data yang ingin anda tampilkan.";
        }
        echo json_encode($response);
    } else {
        $response["code"] = 404;
        $response["status"] = false;
        $response["message"] = "Request ini membutuhkan parameter, gunakan method POST untuk mengeksekusinya";
        echo json_encode($response);
    }
?>