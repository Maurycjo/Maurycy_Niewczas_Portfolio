module XMLParsers {

    requires jakarta.xml.bind;

    exports pwr.edu.pl.football to jakarta.xml.bind;
    opens  pwr.edu.pl.football to jakarta.xml.bind;
    exports pwr.edu.pl.parser;
}