package model.utilities.transaction_formatter;

public class GetHtml {
    private GetHtml() {
    } // private constructor

    public static String init(){
        return("<!DOCTYPE html>");
    }

    public static String end(){
        return("</html>");
    }

    public static String getHead(){
        return("<head><title>Transactions</title>");
    }

    public static String getEndHead(){
        return("</head>");
    }

    public static String getBody(){
        return("<body class=\"body-color\">");
    }

    public static String getEndBody(){
        return("</body>");
    }

    public static String getDiv(){
        return("<div>");
    }

    public static String getEndDiv(){
        return("</div class=\"center login-block-color padding\">");
    }

    public static String getMenu(){
        return("<nav class=\"navbar navbar-expand-lg navbar-dark bg-dark\">\n" +
                "            <a class=\"navbar-brand\" href=\"#\">ABC Bank</a>\n" +
                "            <button class=\"navbar-toggler\" type=\"button\" data-toggle=\"collapse\"\n" +
                "                data-target=\"#navbarNavAltMarkup\"\n" +
                "                aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\"\n" +
                "                aria-label=\"Toggle navigation\">\n" +
                "                <span class=\"navbar-toggler-icon\"></span>\n" +
                "            </button>\n" +
                "            <div class=\"collapse navbar-collapse\" id=\"navbarNavAltMarkup\">\n" +
                "                <div class=\"navbar-nav nav-fill\">\n" +
                "                    <a class=\"nav-item nav-link active\" href=\"http://localhost:8080/abc-bank/home\">Home </a>\n" +
                "                    <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/transaction\">Transaction</a>\n" +
                "                    <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/atm\">ATM</a>\n" +
                "                    <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/login\">Login</a>\n" +
                "                    <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/register\">Register</a>\n" +
                "                    <a class=\"nav-item nav-link\" href=\"http://localhost:8080/abc-bank/about\">Application Status</a>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </nav>");
    }

    public static String getCss(){
        return("<style>"+
                "            .center{"+
                "                position: absolute;"+
                "                top: 50%;"+
                "                left: 50%;"+
                "                transform: translate(-50%, -50%);"+
                "                padding: 60px;"+
                "                width:30%;"+
                "            }"+
                "            .center-text{"+
                "                text-align: center;"+
                "            }"+
                "            .body-color{"+
                "                background: #e9faff;"+
                "            }"+
                ""+
                "            .btn{"+
                "                margin : 10px;"+
                "            }"+
                ""+
                "            #inner {"+
                "                width: 50%;"+
                "                position:relative;"+
                "                top:100px;"+
                "                left:270px;"+
                "                margin: 0 auto;"+
                "            }"+
                ""+
                "        </style>");
    }

    public static String getBr(){
        return("<br>");
    }
}
