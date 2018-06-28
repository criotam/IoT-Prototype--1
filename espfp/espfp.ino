
#include <ESP8266WiFi.h>
#include <WebSocketClient.h>

const char* ssid     = "RasPiVirus";            /*Enter hotspot name*/
const char* password = "brainlara";             /*Enter Password*/
char path[] = "/IOTGateway/gateway3";
char host[] = "192.168.137.1";
  
WebSocketClient webSocketClient;

// Use WiFiClient class to create TCP connections
WiFiClient client;

const int BUTTON=2;
const int LED=0  ;
int BUTTONState=0;
String macId;

void setup() {
  
  pinMode(0, OUTPUT);
  pinMode(2,INPUT);
  
  Serial.begin(9600);
  Serial.println("SETUP");
  delay(10);

  // We start by connecting to a WiFi network

  Serial.println();
  Serial.println();
  Serial.print("Connecting to ");
  Serial.println(ssid);
  
  WiFi.begin(ssid, password);
  
  while (WiFi.status() != WL_CONNECTED) {
     delay(250);
    digitalWrite(LED,LOW);
    delay(250);
    Serial.print(".");
    digitalWrite(LED,HIGH);
  }
digitalWrite(LED,HIGH);
delay(500);
digitalWrite(LED,LOW);
  Serial.println("");
  Serial.println("WiFi connected");  
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());

  delay(5000);


  // Connect to the websocket server
  if (client.connect(host, 8088)) {
    Serial.println("Connected");
    digitalWrite(LED,HIGH);
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
    webSocketClient.sendData("identifier_exp2fp:mac_id:"+macId);
  } else {
    Serial.println("Handshake failed.");
    while(1) {
      // Hang on failure
    }  
  }


}

union cvt{
  byte b[4];
  float f;
  };

  float lc1;
  float lc2;
  float lc3;
  
void loop() {

  cvt c;

  BUTTONState=digitalRead(BUTTON);
  if(BUTTONState==HIGH)
  {
  //digitalWrite(LED,HIGH);
  }
  else
  {
  Serial.println("OFF");
  setup();
  //digitalWrite(LED,LOW);
  }
  
  if (client.connected()) {
    if(Serial.available()){
    char inByte = Serial.read();
    Serial.println(inByte);
    if(inByte=='f'){
      Serial.read();
      byte inData[4];

    Serial.readBytes(c.b,4);
    lc1 = c.f;
    Serial.println(c.f);

    Serial.readBytes(c.b,4);
    lc2 = c.f;
    Serial.println(c.f);

    Serial.readBytes(c.b,4);
    lc3 = c.f;
    Serial.println(c.f);
    
    webSocketClient.sendData("identifier_exp3fp:"+String(lc1)+":"+String(lc2)+":"+String(lc3)+":"+String(millis()));
    }
   }

    String data;
    webSocketClient.getData(data);
    if (data.length() > 0) {
      Serial.print("Received data: ");
      Serial.println(data);
      webSocketClient.sendData("Message received");
    }  
    
  }
  // wait to fully let the client disconnect
//  delay(10);
}
