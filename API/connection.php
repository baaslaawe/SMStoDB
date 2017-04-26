<?php
    
    @session_start();
    date_default_timezone_set("Asia/Kuala_Lumpur");

    define('DB_USER', "user"); // db user
    define('DB_PASSWORD', "password"); // db password (mention your db password here)
    define('DB_DATABASE', "mysql:dbname=db_name;host=localhost"); // database name

    try {
        $db = new PDO(DB_DATABASE, DB_USER, DB_PASSWORD);
        $db->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
        $db->setAttribute(PDO::ATTR_EMULATE_PREPARES, false);
    }
    catch(PDOException $e) {
        echo 'Connection failed '. $e->getMessage();
    }

    include_once 'user.class.php';
    $user = new User($db);

?>



