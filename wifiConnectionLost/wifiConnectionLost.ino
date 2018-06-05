#include <ESP8266WiFi.h>

const char* ssid     = "Argha";
const char* password = "arghasen10";
int flag = 0;
void setup()
{
  Serial.begin(115200);
    Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}
void loop()
{
  if(WiFi.status() != WL_CONNECTED){
    if(flag == 0){
      Serial.print(ssid);
      Serial.println(" Not found");
      Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
      flag=1;
    }
    delay(500);
    Serial.print(".");
  }
  else{
    if(flag==1){
        Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
  Serial.println();
    }
    flag=0;
  }
}

