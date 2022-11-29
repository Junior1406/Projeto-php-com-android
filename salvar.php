<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST'){
    
    $titulo = $_POST['titulo'];
    $nota = $_POST['nota'];
    $cor = $_POST['cor'];
    
    require_once("conexao.php");
    
    $query = "INSERT INTO `notas` (titulo, nota, cor) VALUES ('$titulo', '$nota', '$cor')";
    
    if(mysqli_query($conn, $query)){
        
        $response['sucesso'] = true;
        $response["menssagem"] = "Sucesso!";
        
    }else{
        
        $response['sucesso'] = false;
        $response["menssagem"] = "Falha!";
        
    }
    
}else{
    
    $response['sucesso'] = false;
    $response["menssagem"] = "Erro!";
    
}

echo json_encode($response);
?>