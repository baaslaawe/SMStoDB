<?php
	
	require('connection.php');
	
	$inputJSON = file_get_contents('php://input');
	$input = json_decode($inputJSON);
	
	$sender = $input->sender;
	$message = $input->message;
	$bankName = $input->bank_name;
	$dateAdded = date('Y-m-d H:i:s');
	
	$user->saveMessage($sender, $message, $bankName, $dateAdded);
	
	$response['sender'] = $sender;
	$response['message'] = $message;
	$response['bankName'] = $bankName;
	$response['dateAdded'] = $dateAdded;
	$response['statusCode'] = 1;
	
	echo json_encode($response);
	
?>