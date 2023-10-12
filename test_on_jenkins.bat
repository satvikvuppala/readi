echo "Parameter 0 : %0"
echo "Parameter 1 : %1"
echo "Parameter 1 : %2"

gradlew test -Ddevicename="%2" -DCucumber.options="-tags %1"
