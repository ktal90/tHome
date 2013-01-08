<?php
require_once ("php_serial.class.php"); // include the php serial class
$data = "c";

//$data= (int)$_POST["message"]; // get the message from the phone
//echo $data;

if (isset($_POST['message']) && $_POST['message'] == 'a'){
	$data="a"; // get the message from the phone
	echo "Door is unlocked";
}
else {
	echo "Door is locked \n";
}
echo $data;

$serial = new phpSerial();// create a serial object
 // specify where you want to send the data to (where the arduino is connected in the usb ports)
$serial->deviceSet("COM3");
$serial->confBaudRate(9600);// the transfer data rate of your arduino
$serial->confCharacterLength(8);
$serial->deviceOpen();// start the communication
for ($i = 1; $i <= 10; $i++) {
	$serial->sendMessage($data); // send message to arduino
}
//echo $serial->readPort();
$serial->deviceClose();// close the serial connection
?>