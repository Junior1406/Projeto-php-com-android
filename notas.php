<?php
header("Content-type:application/json");

require_once('conexao.php');

$query = mysqli_query($conn, "SELECT * FROM `notas`");

$response = array();

while ($row = mysqli_fetch_assoc($query)){
    
    array_push($response,
    array(
        'id' =>$row['id'],
        'titulo' =>$row['titulo'],
        'nota' =>$row['nota'],
        'cor' =>$row['cor'],
        'data' =>$row['data'])
        );
    
}
echo json_encode($response)
?>