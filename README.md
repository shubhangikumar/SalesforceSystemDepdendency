# SystemDependency
Project for creating, evaluating and removing System dependencies

Run : java -jar ./target/systemdependencies-1.0-SNAPSHOT.jar /Users/saumitra.shukla/IdeaProjects/SystemDependency/test1_input.dat

Output : 

DEPEND TELNET TCPIP NETCARD
DEPEND TCPIP NETCARD
DEPEND DNS TCPIP NETCARD
DEPEND BROWSER TCPIP HTML
INSTALL NETCARD
         Installing NETCARD
INSTALL TELNET
         Installing TCPIP
         Installing TELNET
INSTALL foo
         Installing foo
REMOVE NETCARD
        NETCARD is still needed
INSTALL BROWSER
         Installing HTML
         Installing BROWSER
INSTALL DNS
         Installing DNS
LIST
        NETCARD
        TCPIP
        TELNET
        foo
        HTML
        BROWSER
        DNS
REMOVE TELNET
        Removing TELNET
REMOVE NETCARD
        NETCARD is still needed
REMOVE DNS
        Removing DNS
REMOVE NETCARD
        NETCARD is still needed
INSTALL NETCARD
        NETCARD is already Installed 
REMOVE TCPIP
        TCPIP is still needed
REMOVE BROWSER
        Removing BROWSER
        Removing TCPIP
        Removing HTML
REMOVE TCPIP
        TCPIP is not installed.
LIST
        NETCARD
        foo
END

