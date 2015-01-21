<?php

/*
 * Following code will update a product information
 * A product is identified by product id (id_ocorrencia)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['id_ocorrencia']) && isset($_POST['titulo_ocorrencia']) && isset($_POST['genero_ocorrencia']) && isset($_POST['descricao_ocorrencia'])) {
    
    $id_ocorrencia = $_POST['id_ocorrencia'];
    $titulo_ocorrencia = $_POST['titulo_ocorrencia'];
    $genero_ocorrencia = $_POST['genero_ocorrencia'];
    $descricao_ocorrencia = $_POST['descricao_ocorrencia'];

    // include db connect class
    require_once dirname(__FILE__) . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched id_ocorrencia
    $result = mysql_query("UPDATE ocorrencia SET titulo_ocorrencia = '$titulo_ocorrencia', genero_ocorrencia = '$genero_ocorrencia', descricao_ocorrencia = '$descricao_ocorrencia' WHERE id_ocorrencia = $id_ocorrencia");

    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Ocorrencia atualizada com sucesso.";
        
        // echoing JSON response
        echo json_encode($response);
    } else {
        
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Campos obrigatorios estao faltando.";

    // echoing JSON response
    echo json_encode($response);
}
?>
