#include <ESP8266WiFi.h>
#include <WebSocketClient.h>
#define emgpin A0
const char* ssid     = "RasPiVirus";
const char* password = "brainlara";
char path[] = "/IOTGateway/gateway2";
char host[] = "192.168.137.1";
float emgdata;
int timestamp;
WebSocketClient webSocketClient;

// Use WiFiClient class to create TCP connections
WiFiClient client;
String macId;
const int LED1 = D0;
const int LED2 = D1;
void setup() {
  Serial.begin(9600);
  delay(10);
pinMode(D0,OUTPUT);
pinMode(D1,OUTPUT);
  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
    
    digitalWrite(LED1,HIGH);
    delay(250);
    Serial.print(".");
    digitalWrite(LED1,LOW);
    delay(250);
  }
digitalWrite(LED1,HIGH);
  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

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
    webSocketClient.sendData("identifier_exp2emg:mac_id:"+macId);
  } else {
    Serial.println("Handshake failed.");
    while(1) {
      // Hang on failure
    }  
  }

}


void loop() {
  

  if (client.connected()) {
    
    emgdata = analogRead(A0);
    Serial.println(emgdata);
    timestamp = millis();
    webSocketClient.sendData("identifier_exp2emg:"+String(emgdata)+":"+String(timestamp));
    
  } else {
    //Serial.println("Client disconnected.");
    digitalWrite(LED1,LOW);
    digitalWrite(LED2,LOW);
    while (1) {
      // Hang on disconnect.
    }
  }
  
  // wait to fully let the client disconnect
  //delay(10);
  
}
