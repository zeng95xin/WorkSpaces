set b=%~dp0
set d=%~d0
%d%
cd %b%
mvn dependency:sources -DdownloadSources=true -DdownloadJavadocs=true