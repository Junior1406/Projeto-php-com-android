<?php

    if($_SERVER['REQUEST_METHOD'] == 'POST'){
        
        $id = $_POST['id'];
        $titulo = $_POST['titulo'];
        $nota = $_POST['nota'];
        $cor = $_POST['cor'];
        
        require_once("conexao.php");
        
        $query = "UPDATE `notas` SET titulo='$titulo', nota='$nota', cor='$cor' WHERE id='$id'";
        
        if(mysqli_query($conn, $query)){
            
            $response['sucesso'] = true;
            $response['menssagem'] = "Sucesso!";
        }else{
            $response['sucesso'] = false;
            $response['menssagem'] = "Falha!";
        }
    }else{
            $response['sucesso'] = false;
            $response['menssagem'] = "Error!";
        }
    echo json_encode($response);
    
?>