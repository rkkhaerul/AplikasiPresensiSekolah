<?php

    $_SERVER_DB = "sql110.epizy.com";
    $_USERNAME_DB = "epiz_27412565";
    $_PASSWORD_DB = "";
    $_DATABASE_DB = "epiz_27412565_db_aplikasipresensisekolah";

    $_AUTH = mysqli_connect($_SERVER_DB, $_USERNAME_DB, $_PASSWORD_DB, $_DATABASE_DB);

    if ($_AUTH) {
        // echo json_encode(array(
        //     "message" => "Congratulation!, your connection is successfully. To database ".$_DATABASE_DB, 
        //     "code" => 200, 
        //     "status" => true)
        // );
    }
?>