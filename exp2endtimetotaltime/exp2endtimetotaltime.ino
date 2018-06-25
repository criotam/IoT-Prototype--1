#include <ESP8266WiFi.h>
#include <WebSocketClient.h>
#define IR A0
int currentVal;
int previous_val;
int starttime;
const char* ssid     = "RasPiVirus";
const char* password = "brainlara";
char path[] = "/IOTGateway/gateway2";
char host[] = "192.168.137.1";
int timestamp;
WebSocketClient webSocketClient;
int LED1 = D0;
int LED2 = D1;
// Use WiFiClient class to create TCP connections
WiFiClient client;
String macId;
void setup() {
  Serial.begin(9600);
  delay(10);
pinMode(LED1,OUTPUT);
pinMode(LED2,OUTPUT);
  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    digitalWrite(LED1,HIGH);
    delay(500);
    digitalWrite(LED1,LOW);
    delay(500);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
digitalWrite(LED1,HIGH);
  delay(5000);
  

  // Connect to the websocket server
  if (client.connect("192.168.137.1", 8088)) {
    Serial.println("Connected");
    digitalWrite(LED2,HIGH);
  } else {
    Serial.println("Connection failed.");
    while(1) {
      // Hang on failure
    }
  }

  // Handshake with the server
  webSocketClient.path = path;
  webSocketClient.host = host;
  if (webSocketClient.handshake(client)) {
    Serial.println("Handshake successful");
    macId = WiFi.macAddress();
    Serial.println(macId);
    webSocketClient.sendData(macId);
  } else {
    Serial.println("Handshake failed.");
    while(1) {
      // Hang on failure
    }  
  }
//previous_val=analogRead(IR);
}
int flag=0;
void loop() {
  

  if (client.connected()) {
    
    currentVal=analogRead(IR);
  Serial.println(currentVal);
  
  if(currentVal==1024&& flag==0){
    
    starttime = millis();
    delay(50);
    if(analogRead(IR)==1024){
      webSocketClient.sendData("identifier_exp2lc:end_time");
    Serial.println("race started");
    flag=1;
    }
    
  }
  //previous_val=currentVal;
    
  } else {
    Serial.println("Client disconnected.");
    while (1) {
      // Hang on disconnect.
    }
  }
  
  // wait to fully let the client disconnect
  delay(10);
  
}
