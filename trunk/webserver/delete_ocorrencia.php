<?php

/*
 * Following code will delete a product from table
 * A product is identified by product id (id_ocorrencia)
 */

// array for JSON response
$response = array();

// check for required fields
if (isset($_POST['id_ocorrencia'])) {
    $id_ocorrencia = $_POST['id_ocorrencia'];

    // include db connect class
    require_once dirname(__FILE__) . '/db_connect.php';

    // connecting to db
    $db = new DB_CONNECT();

    // mysql update row with matched id_ocorrencia
    $result = mysql_query("DELETE FROM ocorrencia WHERE id_ocorrencia = $id_ocorrencia");
    
    // check if row deleted or not
    if (mysql_affected_rows() > 0) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Ocorrencia excluida com sucesso";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "Nenhuma ocorrencia encontrada";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Campos obrigatorios estao faltando";

    // echoing JSON response
    echo json_encode($response);
}
?>