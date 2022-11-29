<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        
        $id = $_POST['id'];
        
        require_once("conexao.php");
        
        $query = "DELETE FROM `notas` WHERE id='$id' ";
        
        if(mysqli_query($conn, $query)){
            
            $response['sucesso'] = true;
            $response['menssagem'] = "Sucesso!";
        }else{
            $response['sucesso'] = false;
            $response['menssagem'] = "Falha!";
        }
        }else{
            $response['sucesso'] = false;
            $response["menssagem"] = "Erro!";
        }
        
    echo json_encode($response);
    
?>