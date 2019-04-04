package pl.coderslab.utils;

public class SendMailSSL {
    public static void main(String[] args) {
        //from,password,to,subject,message

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<!DOCTYPE html>");
        stringBuilder.append("<html lang=\"pl\">");
        stringBuilder.append("<head>");
        stringBuilder.append("<meta charset=\"UTF-8\">");
        stringBuilder.append("<title>Odzyskiwanie hasla</title>");
        stringBuilder.append("<meta http-equiv=\"X-Ua-Compatible\" content=\"IE=edge,chrome=1\">");
        stringBuilder.append("</head>");
        stringBuilder.append("<body>");
        stringBuilder.append("<div style=\"background-color: #f9c910; text-align: center\"><h1>Oddaj rzeczy</h1></div>");
        stringBuilder.append("<p style=\"text-align: center\">W celu odzyskania hasla do aplikacji \"Oddaj rzeczy\" kliknij w link:</p>");
        stringBuilder.append("<p style=\"text-align: center;\"><a style=\"color: #2c7021; text-decoration: none; font-size: 30px\" href=\"http://www.ameliaweb.pl/gifts\" target=\"_blank\">Odzyskaj haslo</a></p>");
        stringBuilder.append("<p style=\"margin-top: 50px; text-align: center\">Jezeli ten mail to pomylka, skasuj wiadomosc.</p>");
        stringBuilder.append("<p style=\"margin-top: 50px; text-align: center\">Pozdrawiamy</p>");
        stringBuilder.append("<p style=\"text-align: center\"><a href=\"http://www.ameliaweb.pl/gifts\">Link do aplikacji.</a></p>");
        stringBuilder.append("</body>");
        stringBuilder.append("</html>");
//        stringBuilder.append("");


        String message= stringBuilder.toString();
        String subject = "Aplikacja \"Oddaj rzeczy\". Resetowanie hasla";
        Mailer mailer = new Mailer();
        mailer.send("stan.zapalny.band@gmail.com","halina07033","statek.kosmiczny@gmail.com",subject,message);





//                mailer.send("stan.zapalny.band@gmail.com","halina07033","statek.kosmiczny@gmail.com","hello javatpoint",message);
//                Mailer.send("stan.zapalny.band@gmail.com","halina07033","statek.kosmiczny@gmail.com","hello javatpoint",message);
//        Mailer.send("stan.zapalny.band@gmail.com","halina07033","statek.kosmiczny@gmail.com","hello javatpoint","How r u?");
        //change from, password and to

    }
}
