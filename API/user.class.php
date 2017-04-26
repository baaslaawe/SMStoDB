<?php
	
	class User {
		var $db;
		
		function __construct($db) {
            $this->db = $db;
        }

		function saveMessage($sender, $message, $bankName, $dateAdded) {
        	
            try {
                $stmt = $this->db->prepare("
                	INSERT INTO message (sender, message, bank_name, date_added) VALUES (:sender, :message, :bank_name, :date_added)");
              
              	$stmt->bindparam(":sender", $sender);
              	$stmt->bindparam(":message", $message);
              	$stmt->bindparam(":bank_name", $bankName);
              	$stmt->bindparam(":date_added", $dateAdded);
                $stmt->execute();

                return $this->db->lastInsertId;
            }
            catch (PDOException $e) {
                echo $e->getMessage();
            }
        }
    }
    
?>