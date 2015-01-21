<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['titulo_ocorrencia']) && isset($_POST['genero_ocorrencia']) && isset($_POST['descricao_ocorrencia'])) {
    
    $titulo_ocorrencia = $_POST['titulo_ocorrencia'];
    $genero_ocorrencia = $_POST['genero_ocorrencia'];
    $descricao_ocorrencia = $_POST['descricao_ocorrencia'];

    // include db connect class
    require_once dirname(__FILE__) . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO ocorrencia(titulo_ocorrencia, genero_ocorrencia, descricao_ocorrencia, qtdApoio_ocorrencia) VALUES('$titulo_ocorrencia', '$genero_ocorrencia', '$descricao_ocorrencia', 1)");

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Ocorrência gerada com sucesso.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Opa! Ocorreu um erro.";
        $response["titulo_ocorrencia"] = $titulo_ocorrencia;
        $response["genero_ocorrencia"] = $genero_ocorrenciaice;
        $response["descricao_ocorrencia"] = $descricao_ocorrencia;
        $response["query"] = $result;
        
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Campo(s) obrigatório(s) faltando.";

    // echoing JSON response
    echo json_encode($response);
}
?>