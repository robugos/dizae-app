<?php

/*
 * Following code will get single product details
 * A product is identified by product id (pid)
 */

// array for JSON response
$response = array();


// include db connect class
require_once dirname(__FILE__) . '/db_connect.php';

// connecting to db
$db = new DB_CONNECT();

// check for post data
if (isset($_GET["id_ocorrencia"])) {
    $pid = $_GET['id_ocorrencia'];

    // get a product from products table
    $result = mysql_query("SELECT *FROM ocorrencia WHERE id_ocorrencia = $pid");

    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {

            $result = mysql_fetch_array($result);

            $product = array();
            $product["id_ocorrencia"] = $result["id_ocorrencia"];
            $product["titulo_ocorrencia"] = $result["titulo_ocorrencia"];
            $product["genero_ocorrencia"] = $result["genero_ocorrencia"];
            $product["descricao_ocorrencia"] = $result["descricao_ocorrencia"];
            $product["data_ocorrencia"] = $result["data_ocorrencia"];
            $product["atualizacao_ocorrencia"] = $result["atualizacao_ocorrencia"];
            // success
            $response["success"] = 1;

            // user node
            $response["product"] = array();

            array_push($response["product"], $product);

            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";

            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";

        // echo no users JSON
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
}
?>