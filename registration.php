<?
	include_once("connection.php");
	if(isset($_POST))
	{
		
		
		$email 		= $_POST['post_email'];
		$username	= $_POST['post_username'];
		$pass		= $_POST['post_passwd'];
		
		$check = mysql_query("SELECT * FROM user_table WHERE EMAIL='$email'");
		if(mysql_num_rows($check)>0)
		{
			$arr = array("REGISTER"=>"FAILED","MSG"=>"User has registered");
			echo json_encode($arr);
		}else{
			mysql_query("INSERT into user_table(USERNAME,EMAIL,PASSWD) VALUES('$username','$email','$pass')");
			$arr = array("REGISTER"=>"OK","MSG"=>"Registration successful");
			echo json_encode($arr);
		}
		
	}	
	
?>