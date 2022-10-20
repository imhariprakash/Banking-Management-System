package model.utilities.customer_details_formatter;

public class Build {
    private Build() {
    } // private constructor

    public static String build(String account_number){
        String page = "<!doctype html>\n" +
                "<html>\n" +
                " <head>\n" +
                "  <title>Transactions</title>\n" +
                "  <!-- CSS files (include only one of the two files!) -->\n" +
                "  <!-- If you are not using bootstrap: -->\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/datatable.min.css\" media=\"screen\">\n" +
                "  <!-- If you are using bootstrap: -->\n" +
                "  <link rel=\"stylesheet\" type=\"text/css\" href=\"css/datatable-bootstrap.min.css\" media=\"screen\"> \n" +
                "  <!-- JS files -->\n" +
                "  <script type=\"text/javascript\" src=\"js/datatable.min.js\"></script> \n" +
                "  <!-- Add the following if you want to use the jQuery wrapper (you still need datatable.min.js): -->\n" +
                "  <script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>\n" +
                "  <script type=\"text/javascript\" src=\"js/datatable.jquery.min.js\"></script>\n" +
                "  <!-- Bootstrap CSS v5.2.1 -->\n" +
                "  <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT\" crossorigin=\"anonymous\">\n" +
                "  <script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js\" integrity=\"sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3\" crossorigin=\"anonymous\"></script>\n" +
                "  <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js\" integrity=\"sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz\" crossorigin=\"anonymous\"></script>\n" +
                "  <script>$('#first-datatable-output table').datatable({    pageSize: 5,    sort: [true, true, false],    filters: [true, false, 'select'],    filterText: 'Type to filter... ',    pagingDivSelector: \"#paging-first-datatable\"});var datatable = new DataTable(document.querySelector('#first-datatable-output table'), {    pageSize: 5,    sort: [true, true, false],    filters: [true, false, 'select'],    filterText: 'Type to filter... ',    pagingDivSelector: \"#paging-first-datatable\"});</script>\n" +
                "  <style>            .center{                position: absolute;                top: 50%;                left: 50%;                transform: translate(-50%, -50%);                padding: 60px;                width:30%;            }            .center-text{                text-align: center;            }            .body-color{                background: #e9faff;            }            .btn{                margin : 10px;            }            .inner {                width: 50%;                position:relative;                top:100px;                left:370px;                margin: 0 auto;            }        </style>\n" +
                " </head>\n" +
                " <body class=\"body-color\">\n" +
                "  <nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\"> \n" +
                "   <a class=\"navbar-brand\" href=\"#\">ABC Bank</a> \n" +
                "   <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\" data-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"> <span class=\"navbar-toggler-icon\"></span> </button> \n" +
                "   <div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\"> \n" +
                "    <div class=\"navbar-nav nav-fill\"> \n" +
                "     <a class=\"nav-item nav-link active\" href=\"http://localhost:8080/abc-bank/home\">Home </a> \n" +
                "     <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/transaction\">Transaction</a> \n" +
                "     <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/atm\">ATM</a> \n" +
                "     <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/login\">Login</a> \n" +
                "     <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/register\">Register</a> \n" +
                "     <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/about\">Application Status</a> \n" +
                "    </div> \n" +
                "   </div> \n" +
                "  </nav>\n" +
                "  <br>\n" +
                "  <br>\n" +
                "  <div>\n" +
                "   <table class=\"table table-bordered table-hover\">\n" +
                "    <thead> \n" +
                "     <tr> \n" +
                "      <th scope=\"col\">Customer id</th> \n" +
                "      <th scope=\"col\">Account number</th> \n" +
                "      <th scope=\"col\">IFSC code</th> \n" +
                "      <th scope=\"col\">Card number</th> \n" +
                "      <th scope=\"col\">Card type</th> \n" +
                "      <th scope=\"col\">Pin</th> \n" +
                "      <th scope=\"col\">CVV</th> \n" +
                "      <th scope=\"col\">Expiry date</th> \n" +
                "      <th scope=\"col\">Status</th> \n" +
                "      <th scope=\"col\">Customer name</th> \n" +
                "      <th scope=\"col\">Father name</th> \n" +
                "      <th scope=\"col\">Email</th> \n" +
                "      <th scope=\"col\">Phone</th> \n" +
                "      <th scope=\"col\">Aadhar</th> \n" +
                "      <th scope=\"col\">Pan</th> \n" +
                "      <th scope=\"col\">Address</th> \n" +
                "      <th scope=\"col\">Pincode</th> \n" +
                "      <th scope=\"col\">DOB</th> \n" +
                "      <th scope=\"col\">Balance</th> \n" +
                "     </tr> \n" +
                "    </thead>\n" +
                "    <tbody>";

        return model.utilities.PrettyPrintHtml.prettyPrint(page);
    }
}
