package model.utilities.transaction_formatter;

public class Pagination {
    private Pagination() {
    } // private constructor



    public static String getJs(){
        return("<script>"+
                "$('#first-datatable-output table').datatable({"+
                "    pageSize: 5,"+
                "    sort: [true, true, false],"+
                "    filters: [true, false, 'select'],"+
                "    filterText: 'Type to filter... ',"+
                "    pagingDivSelector: \"#paging-first-datatable\""+
                "});"+
                ""+
                "var datatable = new DataTable(document.querySelector('#first-datatable-output table'), {"+
                "    pageSize: 5,"+
                "    sort: [true, true, false],"+
                "    filters: [true, false, 'select'],"+
                "    filterText: 'Type to filter... ',"+
                "    pagingDivSelector: \"#paging-first-datatable\""+
                "});"+
                ""+
                "</script>");

    }

    public static String getBootStrap(){
        return("<!-- Bootstrap CSS v5.2.1 -->"+
                "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css\" rel=\"stylesheet\""+
                "integrity=\"sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT\" crossorigin=\"anonymous\">"+
                ""+
                "<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js\""+
                "integrity=\"sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3\" crossorigin=\"anonymous\">"+
                "</script>"+
                ""+
                "<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js\""+
                "integrity=\"sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz\" crossorigin=\"anonymous\">"+
                "</script>");
    }

    public static String getPaginationPlugin(){
        return("<!-- CSS files (include only one of the two files!) -->"+
                "<!-- If you are not using bootstrap: -->"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/datatable.min.css\" media=\"screen\">"+
                "<!-- If you are using bootstrap: -->"+
                "<link rel=\"stylesheet\" type=\"text/css\" href=\"css/datatable-bootstrap.min.css\" media=\"screen\">"+
                "                        "+
                "<!-- JS files -->"+
                "<script type=\"text/javascript\" src=\"js/datatable.min.js\"></script>"+
                "                        "+
                "<!-- Add the following if you want to use the jQuery wrapper (you still need datatable.min.js): -->"+
                "<script type=\"text/javascript\" src=\"js/jquery.min.js\"></script>"+
                "<script type=\"text/javascript\" src=\"js/datatable.jquery.min.js\"></script>");
    }

    public static String getPagination(){
        return("\"<div id=\\\"paging-first-datatable\\\"></div>\";");
    }
}
